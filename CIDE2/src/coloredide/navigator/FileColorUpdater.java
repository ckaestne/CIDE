package coloredide.navigator;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.resources.ProjectExplorer;

import coloredide.ASTColorChangedEvent;
import coloredide.ChangeType;
import coloredide.ColorListChangedEvent;
import coloredide.FileColorChangedEvent;
import coloredide.IColorChangeListener;

/**
 * listens to changes to the .dircolor files and publishes them as
 * FileColorChangedEvent events (which are used to update the colors and filters
 * in the view)
 * 
 * @author ckaestne
 * 
 */
public class FileColorUpdater implements IColorChangeListener {

	public void astColorChanged(ASTColorChangedEvent event) {
		// ignore, does not affect resource view
	}

	public void fileColorChanged(FileColorChangedEvent event) {
		refresh(event.getAffectedFolders());
	}

	/**
	 * 
	 * @param items
	 *            null-> draw all
	 */
	private void refresh(final Collection<? extends IResource> items) {
		Display.getDefault().syncExec(new Runnable() {

			public void run() {

				IWorkbench wb = PlatformUI.getWorkbench();
				IWorkbenchWindow ww = wb.getActiveWorkbenchWindow();
				IWorkbenchPage ap = ww.getActivePage();
				IViewPart view = ap.findView(ProjectExplorer.VIEW_ID);
				if (view instanceof CommonNavigator) {
					CommonViewer viewer = ((CommonNavigator) view)
							.getCommonViewer();
					try {
						viewer.getControl().setRedraw(false);
						if (items == null)
							viewer.refresh(true);
						else
							for (IResource folder : items)
								viewer.refresh(folder, true);
					} finally {
						viewer.getControl().setRedraw(true);
					}
				}
			}
		});
	}

	public void colorListChanged(ColorListChangedEvent event) {
		if (event.anyChangeOf(ChangeType.COLOR))
			refresh(Collections.singleton(event.getProject()));
	}

}
