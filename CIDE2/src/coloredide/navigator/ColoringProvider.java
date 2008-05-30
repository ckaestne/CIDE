package coloredide.navigator;

import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import coloredide.features.Feature;
import coloredide.features.FeatureManager;
import coloredide.features.source.DirectoryColorManager;

/**
 * registration apparently works only for the project explorer not for the resource navigator
 * @author ckaestne
 *
 */
public class ColoringProvider implements ILabelProvider, IColorProvider {
	private Color color_red = Display.getCurrent()
			.getSystemColor(SWT.COLOR_RED);

	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getText(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	public void dispose() {
		color_red.dispose();
	}

	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	public Color getBackground(Object element) {
		if (element instanceof IFile){
			IFile file=(IFile) element;
			DirectoryColorManager colorManager = DirectoryColorManager.getColoredDirectoryManagerS(file.getParent());
			Set<Feature> colors = colorManager.getColors(file);
			if (colors!=null && !colors.isEmpty())
				return FeatureManager.getCombinedColor(colors,file.getProject());
		}
		if (element instanceof IFolder || element instanceof IProject){
			IContainer folder=(IContainer) element;
			DirectoryColorManager colorManager = DirectoryColorManager.getColoredDirectoryManagerS(folder);
			Set<Feature> colors = colorManager.getFolderColors();
			if (colors!=null && !colors.isEmpty())
				return FeatureManager.getCombinedColor(colors,folder.getProject());
		}
		return null;
	}

	public Color getForeground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

}
