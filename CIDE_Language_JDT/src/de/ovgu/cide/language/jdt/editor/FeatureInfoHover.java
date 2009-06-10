package de.ovgu.cide.language.jdt.editor;

import org.eclipse.jdt.ui.text.java.hover.IJavaEditorTextHover;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.ui.IEditorPart;

import de.ovgu.cide.editor.ColoredTextHover;

public class FeatureInfoHover extends ColoredTextHover implements
		IJavaEditorTextHover {

	public FeatureInfoHover() {
		super(null);
		NL="<BR>";
	}

	public void setEditor(IEditorPart editor) {
		if (editor instanceof ColoredCompilationUnitEditor) {
			setColoredSourceFile(((ColoredCompilationUnitEditor) editor)
					.getSourceFile());
		} else {
			setColoredSourceFile(null);
		}
	}

	@Override
	public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion) {
		String info = super.getHoverInfo(textViewer, hoverRegion);
		if (info == NOT_COLORED)
			return null;
		return info;
	}

}
