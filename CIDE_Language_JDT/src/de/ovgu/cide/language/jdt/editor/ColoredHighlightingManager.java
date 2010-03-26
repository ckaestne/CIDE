/**
    Copyright 2010 Christian Kästner

    This file is part of CIDE.

    CIDE is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.

    CIDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CIDE.  If not, see <http://www.gnu.org/licenses/>.

    See http://www.fosd.de/cide/ for further information.
*/

package de.ovgu.cide.language.jdt.editor;

import org.eclipse.jdt.internal.ui.javaeditor.JavaSourceViewer;
import org.eclipse.jdt.internal.ui.javaeditor.SemanticHighlighting;
import org.eclipse.jdt.internal.ui.javaeditor.SemanticHighlightings;
import org.eclipse.jdt.internal.ui.text.JavaPresentationReconciler;
import org.eclipse.jdt.ui.text.IColorManager;
import org.eclipse.jdt.ui.text.IJavaPartitions;
import org.eclipse.jdt.ui.text.JavaSourceViewerConfiguration;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

import de.ovgu.cide.configuration.jdt.JDTColorManagerBridge;
import de.ovgu.cide.editor.CodeSegment;

@SuppressWarnings("restriction")
public class ColoredHighlightingManager implements IPropertyChangeListener {

	/**
	 * Highlighted ranges.
	 */
	public static class HighlightedRange extends Region {
		/**
		 * The highlighting key as returned by
		 * {@link SemanticHighlighting#getPreferenceKey()}.
		 */
		private String fKey;

		/**
		 * Initialize with the given offset, length and highlighting key.
		 * 
		 * @param offset
		 * @param length
		 * @param key
		 *            the highlighting key as returned by
		 *            {@link SemanticHighlighting#getPreferenceKey()}
		 */
		public HighlightedRange(int offset, int length, String key) {
			super(offset, length);
			fKey = key;
		}

		/**
		 * @return the highlighting key as returned by
		 *         {@link SemanticHighlighting#getPreferenceKey()}
		 */
		public String getKey() {
			return fKey;
		}

		/*
		 * @see org.eclipse.jface.text.Region#equals(java.lang.Object)
		 */
		public boolean equals(Object o) {
			return super.equals(o) && o instanceof HighlightedRange
					&& fKey.equals(((HighlightedRange) o).getKey());
		}

		/*
		 * @see org.eclipse.jface.text.Region#hashCode()
		 */
		public int hashCode() {
			return super.hashCode() | fKey.hashCode();
		}
	}

	/** Semantic highlighting presenter */
	private ColoredHighlightingPresenter fPresenter;

	/** Semantic highlighting reconciler */
	ColoredHighlightingReconciler fReconciler;

	/** The editor */
	private ColoredCompilationUnitEditor fEditor;

	/** The source viewer */
	private JavaSourceViewer fSourceViewer;

	/** The color manager */
//	private IColorManager fColorManager;

	/** The preference store */
	private IPreferenceStore fPreferenceStore;

	/** The source viewer configuration */
	private JavaSourceViewerConfiguration fConfiguration;

	/** The presentation reconciler */
	private JavaPresentationReconciler fPresentationReconciler;

	private JDTColorManagerBridge compUnitColorManager;

	/**
	 * Install the semantic highlighting on the given editor infrastructure
	 * 
	 * @param editor
	 *            The Java editor
	 * @param sourceViewer
	 *            The source viewer
	 * @param colorManager
	 *            The color manager
	 * @param preferenceStore
	 *            The preference store
	 * @param manager
	 */
	public void install(ColoredCompilationUnitEditor editor,
			JavaSourceViewer sourceViewer, IColorManager colorManager,
			IPreferenceStore preferenceStore,
			JDTColorManagerBridge compUnitColorManager) {
		fEditor = editor;
		fSourceViewer = sourceViewer;
//		fColorManager = colorManager;
		fPreferenceStore = preferenceStore;
		this.compUnitColorManager = compUnitColorManager;
		if (fEditor != null) {
			fConfiguration = new JavaSourceViewerConfiguration(colorManager,
					preferenceStore, editor, IJavaPartitions.JAVA_PARTITIONING);
			fPresentationReconciler = (JavaPresentationReconciler) fConfiguration
					.getPresentationReconciler(sourceViewer);
		} else {
			fConfiguration = null;
			fPresentationReconciler = null;
		}

		fPreferenceStore.addPropertyChangeListener(this);

		if (isEnabled())
			enable();
	}

	/**
	 * Enable code coloring
	 */
	private void enable() {
		// initializeCodeColors();

		fPresenter = new ColoredHighlightingPresenter();
		fPresenter.install(fSourceViewer, fPresentationReconciler);

		if (fEditor != null) {
			fReconciler = new ColoredHighlightingReconciler();
			fReconciler.setColorManager(compUnitColorManager);
			fReconciler.install(fEditor, fSourceViewer, fPresenter);
		} else {
			fPresenter.updatePresentation(null, new CodeSegment[0],
					new CodeSegment[0]);
		}
	}

	/**
	 * Uninstall the semantic highlighting
	 */
	public void uninstall() {
		disable();

		if (fPreferenceStore != null) {
			fPreferenceStore.removePropertyChangeListener(this);
			fPreferenceStore = null;
		}

		fEditor = null;
		fSourceViewer = null;
//		fColorManager = null;
		fConfiguration = null;
		fPresentationReconciler = null;
	}

	/**
	 * Disable semantic highlighting.
	 */
	private void disable() {
		if (fReconciler != null) {
			fReconciler.uninstall();
			fReconciler = null;
		}

		if (fPresenter != null) {
			fPresenter.uninstall();
			fPresenter = null;
		}

	}

	/**
	 * @return <code>true</code> iff semantic highlighting is enabled in the
	 *         preferences
	 */
	private boolean isEnabled() {
		return true;
		// return SemanticHighlightings.isEnabled(fPreferenceStore);
	}

	/*
	 * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent event) {
		handlePropertyChangeEvent(event);
	}

	/**
	 * Handle the given property change event
	 * 
	 * @param event
	 *            The event
	 */
	private void handlePropertyChangeEvent(PropertyChangeEvent event) {
		if (fPreferenceStore == null)
			return; // Uninstalled during event notification

		if (fConfiguration != null)
			fConfiguration.handlePropertyChangeEvent(event);

		if (SemanticHighlightings.affectsEnablement(fPreferenceStore, event)) {
			if (isEnabled())
				enable();
			else
				disable();
		}

		if (!isEnabled())
			return;

		boolean refreshNeeded = false;

		/*
		 * for (int i= 0, n= fSemanticHighlightings.length; i < n; i++) {
		 * SemanticHighlighting semanticHighlighting= fSemanticHighlightings[i];
		 * 
		 * String colorKey=
		 * SemanticHighlightings.getColorPreferenceKey(semanticHighlighting); if
		 * (colorKey.equals(event.getProperty())) {
		 * adaptToTextForegroundChange(fHighlightings[i], event);
		 * fPresenter.highlightingStyleChanged(fHighlightings[i]);
		 * refreshNeeded= true; continue; }
		 * 
		 * String boldKey=
		 * SemanticHighlightings.getBoldPreferenceKey(semanticHighlighting); if
		 * (boldKey.equals(event.getProperty())) {
		 * adaptToTextStyleChange(fHighlightings[i], event, SWT.BOLD);
		 * fPresenter.highlightingStyleChanged(fHighlightings[i]);
		 * refreshNeeded= true; continue; }
		 * 
		 * String italicKey=
		 * SemanticHighlightings.getItalicPreferenceKey(semanticHighlighting);
		 * if (italicKey.equals(event.getProperty())) {
		 * adaptToTextStyleChange(fHighlightings[i], event, SWT.ITALIC);
		 * fPresenter.highlightingStyleChanged(fHighlightings[i]);
		 * refreshNeeded= true; continue; }
		 * 
		 * String strikethroughKey=
		 * SemanticHighlightings.getStrikethroughPreferenceKey(semanticHighlighting);
		 * if (strikethroughKey.equals(event.getProperty())) {
		 * adaptToTextStyleChange(fHighlightings[i], event,
		 * TextAttribute.STRIKETHROUGH);
		 * fPresenter.highlightingStyleChanged(fHighlightings[i]);
		 * refreshNeeded= true; continue; }
		 * 
		 * String underlineKey=
		 * SemanticHighlightings.getUnderlinePreferenceKey(semanticHighlighting);
		 * if (underlineKey.equals(event.getProperty())) {
		 * adaptToTextStyleChange(fHighlightings[i], event,
		 * TextAttribute.UNDERLINE);
		 * fPresenter.highlightingStyleChanged(fHighlightings[i]);
		 * refreshNeeded= true; continue; }
		 * 
		 * String enabledKey=
		 * SemanticHighlightings.getEnabledPreferenceKey(semanticHighlighting);
		 * if (enabledKey.equals(event.getProperty())) {
		 * adaptToEnablementChange(fHighlightings[i], event);
		 * fPresenter.highlightingStyleChanged(fHighlightings[i]);
		 * refreshNeeded= true; continue; } }
		 */

		if (refreshNeeded && fReconciler != null)
			fReconciler.refresh();
	}

	// private void adaptToEnablementChange(Highlighting highlighting,
	// PropertyChangeEvent event) {
	// Object value= event.getNewValue();
	// boolean eventValue;
	// if (value instanceof Boolean)
	// eventValue= ((Boolean) value).booleanValue();
	// else if (IPreferenceStore.TRUE.equals(value))
	// eventValue= true;
	// else
	// eventValue= false;
	// highlighting.setEnabled(eventValue);
	// }
	//
	// private void adaptToTextForegroundChange(Highlighting highlighting,
	// PropertyChangeEvent event) {
	// RGB rgb= null;
	//
	// Object value= event.getNewValue();
	// if (value instanceof RGB)
	// rgb= (RGB) value;
	// else if (value instanceof String)
	// rgb= StringConverter.asRGB((String) value);
	//
	// if (rgb != null) {
	//
	// String property= event.getProperty();
	// Color color= fColorManager.getColor(property);
	//
	// if ((color == null || !rgb.equals(color.getRGB())) && fColorManager
	// instanceof IColorManagerExtension) {
	// IColorManagerExtension ext= (IColorManagerExtension) fColorManager;
	// ext.unbindColor(property);
	// ext.bindColor(property, rgb);
	// color= fColorManager.getColor(property);
	// }
	//
	// TextAttribute oldAttr= highlighting.getTextAttribute();
	// highlighting.setTextAttribute(new TextAttribute(color,
	// oldAttr.getBackground(), oldAttr.getStyle()));
	// }
	// }
	//
	// private void adaptToTextStyleChange(Highlighting highlighting,
	// PropertyChangeEvent event, int styleAttribute) {
	// boolean eventValue= false;
	// Object value= event.getNewValue();
	// if (value instanceof Boolean)
	// eventValue= ((Boolean) value).booleanValue();
	// else if (IPreferenceStore.TRUE.equals(value))
	// eventValue= true;
	//
	// TextAttribute oldAttr= highlighting.getTextAttribute();
	// boolean activeValue= (oldAttr.getStyle() & styleAttribute) ==
	// styleAttribute;
	//
	// if (activeValue != eventValue)
	// highlighting.setTextAttribute(new TextAttribute(oldAttr.getForeground(),
	// oldAttr.getBackground(), eventValue ? oldAttr.getStyle() | styleAttribute
	// : oldAttr.getStyle() & ~styleAttribute));
	// }
	//
	// private void addColor(String colorKey) {
	// if (fColorManager != null && colorKey != null &&
	// fColorManager.getColor(colorKey) == null) {
	// RGB rgb= PreferenceConverter.getColor(fPreferenceStore, colorKey);
	// if (fColorManager instanceof IColorManagerExtension) {
	// IColorManagerExtension ext= (IColorManagerExtension) fColorManager;
	// ext.unbindColor(colorKey);
	// ext.bindColor(colorKey, rgb);
	// }
	// }
	// }
	//
	// private void removeColor(String colorKey) {
	// if (fColorManager instanceof IColorManagerExtension)
	// ((IColorManagerExtension) fColorManager).unbindColor(colorKey);
	// }
}
