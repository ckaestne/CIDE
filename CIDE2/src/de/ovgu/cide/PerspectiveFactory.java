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

package de.ovgu.cide;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.navigator.resources.ProjectExplorer;

import de.ovgu.cide.tools.featureview.FeatureView;

public class PerspectiveFactory implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		// Get the editor area.
		String editorArea = layout.getEditorArea();

		// Top left: Resource Navigator view and Bookmarks view placeholder
		IFolderLayout topLeft = layout.createFolder("topLeft",
				IPageLayout.LEFT, 0.25f, editorArea);
		topLeft.addView(ProjectExplorer.VIEW_ID);

		// Bottom left: Outline view and Property Sheet view
		IFolderLayout bottomLeft = layout.createFolder("bottomLeft",
				IPageLayout.BOTTOM, 0.8f, editorArea);
		bottomLeft.addView(IPageLayout.ID_PROBLEM_VIEW);
		bottomLeft.addView(CIDECorePlugin.ID_PREVIEW);
		bottomLeft.addView(CIDECorePlugin.ID_INTERACTION);

		// Bottom right: Task List view
		IFolderLayout right = layout.createFolder("right",
				IPageLayout.RIGHT, 0.75f, editorArea);
		right.addView(FeatureView.VIEW_ID);
		right.addView(IPageLayout.ID_OUTLINE);
//		right.addView(CIDECorePlugin.ID_ASTVIEW);
	}

}
