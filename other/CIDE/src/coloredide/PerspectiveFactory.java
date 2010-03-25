package coloredide;

import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class PerspectiveFactory implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		// Get the editor area.
		String editorArea = layout.getEditorArea();

		// Top left: Resource Navigator view and Bookmarks view placeholder
		IFolderLayout topLeft = layout.createFolder("topLeft",
				IPageLayout.LEFT, 0.25f, editorArea);
		topLeft.addView(JavaUI.ID_PACKAGES);

		// Bottom left: Outline view and Property Sheet view
		IFolderLayout bottomLeft = layout.createFolder("bottomLeft",
				IPageLayout.BOTTOM, 0.8f, editorArea);
		bottomLeft.addView(IPageLayout.ID_PROBLEM_VIEW);
		bottomLeft.addView(ColoredIDEPlugin.ID_PREVIEW);
		bottomLeft.addView(ColoredIDEPlugin.ID_INTERACTION);

		// Bottom right: Task List view
		IFolderLayout right = layout.createFolder("right",
				IPageLayout.RIGHT, 0.75f, editorArea);
		right.addView(IPageLayout.ID_OUTLINE);
		right.addView(ColoredIDEPlugin.ID_ASTVIEW);
	}

}
