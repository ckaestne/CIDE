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
