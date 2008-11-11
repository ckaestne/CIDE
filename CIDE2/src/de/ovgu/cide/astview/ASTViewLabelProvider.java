/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package de.ovgu.cide.astview;

import java.util.Set;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import cide.gast.ASTStringNode;
import cide.gast.IASTNode;
import cide.gast.Property;
import cide.gast.PropertyOptionalWithDefault;
import cide.gast.PropertyZeroOrMore;
import cide.gast.PropertyZeroOrOne;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.utils.ColorHelper;

public class ASTViewLabelProvider extends LabelProvider implements
		IColorProvider, IFontProvider {
	private int fSelectionStart;

	private int fSelectionLength;

	private final Color fBlue, fRed, fDarkGray, fDarkGreen, fDarkRed;

	private final Font fBold;

	// to dispose:
	private final Font fAllocatedBoldItalic;

	private final Color fLightBlue, fLightRed;

	private ASTView fView;

	public ASTViewLabelProvider(ASTView view) {
		this.fView = view;
		fSelectionStart = -1;
		fSelectionLength = -1;

		Display display = Display.getCurrent();

		fRed = display.getSystemColor(SWT.COLOR_RED);
		fDarkGray = display.getSystemColor(SWT.COLOR_DARK_GRAY);
		fBlue = display.getSystemColor(SWT.COLOR_DARK_BLUE);
		fDarkGreen = display.getSystemColor(SWT.COLOR_DARK_GREEN);
		fDarkRed = display.getSystemColor(SWT.COLOR_DARK_RED);

		fLightBlue = new Color(display, 232, 242, 254); // default for
		// AbstractDecoratedTextEditorPreferenceConstants.EDITOR_CURRENT_LINE_COLOR
		fLightRed = new Color(display, 255, 190, 190);

		fBold = PlatformUI.getWorkbench().getThemeManager().getCurrentTheme()
				.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);
		FontData[] fontData = fBold.getFontData();
		for (int i = 0; i < fontData.length; i++) {
			fontData[i].setStyle(fontData[i].getStyle() | SWT.ITALIC);
		}
		fAllocatedBoldItalic = new Font(display, fontData);
	}

	public void setSelectedRange(int start, int length) {
		fSelectionStart = start;
		fSelectionLength = length;
		// could be made more efficient by only updating selected node and
		// parents (of old and new selection)
		fireLabelProviderChanged(new LabelProviderChangedEvent(this));
	}

	public String getText(Object obj) {
		StringBuffer buf = new StringBuffer();
		if (obj instanceof ASTStringNode) {
			buf.append(shorten(((ASTStringNode) obj).getValue()));
			appendOffset((IASTNode) obj, buf);
		} else if (obj instanceof IASTNode) {
			buf.append(((IASTNode)obj).getDisplayName());
			appendOffset((IASTNode) obj, buf);
		} else if (obj instanceof PropertyZeroOrOne) {
			return "[" + ((Property) obj).getName() + "]";
		} else if (obj instanceof PropertyZeroOrMore) {
			return ((Property) obj).getName() + "*";
		} else if (obj instanceof PropertyOptionalWithDefault) {
			return ((Property) obj).getName() + "?";
		} else if (obj instanceof Property) {
			return ((Property) obj).getName();
		}
		return buf.toString();
	}

	private String shorten(String value) {
		if (value.length() <= 33)
			return value;
		return value.substring(0, 30) + " ...";
	}

	private void appendOffset(IASTNode node, StringBuffer buf) {
		buf.append(" ["); //$NON-NLS-1$
		buf.append(node.getStartPosition());
		buf.append(", "); //$NON-NLS-1$
		buf.append(node.getLength());
		buf.append(']');
		// if ((node.getFlags() & IASTNode.MALFORMED) != 0) {
		// buf.append(" (malformed)"); //$NON-NLS-1$
		// }
		// if ((node.getFlags() & IASTNode.RECOVERED) != 0) {
		// buf.append(" (recovered)"); //$NON-NLS-1$
		// }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
	 */
	public Color getForeground(Object element) {

		if (element instanceof ASTStringNode) {
			return fRed;
		} else if (element instanceof IASTNode) {
			// IASTNode node = (IASTNode) element;
			// if ((node.getFlags() & IASTNode.MALFORMED) != 0) {
			// return fRed;
			// }
			return null;
		} else if (element instanceof Property) {
			return fDarkGray; // normal color
		}
		return fDarkRed; // all extra properties
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IColorProvider#getBackground(java.lang.Object)
	 */
	public Color getBackground(Object element) {
		IASTNode node = null;
		if (element instanceof IASTNode)
			node = (IASTNode) element;
		if (element instanceof Property
				&& ((Property) element).getNode() instanceof IASTNode)
			node = (IASTNode) ((Property) element).getNode();

		if (node != null) {
			ColoredSourceFile source = fView.getColoredJavaSourceFile();
			Set<IFeature> colors = source.getColorManager().getColors(node);
			if (colors.size() > 0) {
				RGB rgb = ColorHelper.getCombinedRGB(colors);
				return new Color(Display.getCurrent(), rgb);
			}
		}
		if (isNotProperlyNested(element)) {
			return fLightRed;
		}
		if (fSelectionStart != -1 && isInside(element)) {
			return fLightBlue;
		}
		return null;
	}

	private boolean isNotProperlyNested(Object element) {
		if (element instanceof IASTNode) {
			IASTNode node = (IASTNode) element;
			int start = node.getStartPosition();
			int end = start + node.getLength();

			IASTNode parent = node.getParent();
			if (parent != null) {
				int parentstart = parent.getStartPosition();
				int parentend = parentstart + parent.getLength();

				if (start < parentstart || end > parentend) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isInsideNode(IASTNode node) {
		int start = node.getStartPosition();
		int end = start + node.getLength();
		if (start <= fSelectionStart
				&& (fSelectionStart + fSelectionLength) < end) {
			return true;
		}
		return false;
	}

	private boolean isInside(Object element) {
		if (element instanceof IASTNode) {
			return isInsideNode((IASTNode) element);
		} else if (element instanceof Property) {
			Property property = (Property) element;
			Object object = property.getNode();
			if (object instanceof IASTNode) {
				return isInsideNode((IASTNode) object);
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IFontProvider#getFont(java.lang.Object)
	 */
	public Font getFont(Object element) {
		if (element instanceof IASTNode)
			if (((IASTNode) element).isOptional())
				return fBold;
		return null;
	}

	public void dispose() {
		super.dispose();
		fLightBlue.dispose();
		fLightRed.dispose();
		fAllocatedBoldItalic.dispose();
	}

}
