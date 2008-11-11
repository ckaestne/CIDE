package de.ovgu.cide.navigator;

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

import de.ovgu.cide.features.FeatureModelManager;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.DirectoryColorManager;
import de.ovgu.cide.utils.ColorHelper;

/**
 * provides colors for the project explorer
 * 
 * registration apparently works only for the project explorer not for the
 * resource navigator
 * 
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
		try {
			if (element instanceof IFile) {
				IFile file = (IFile) element;
				IFeatureModel fm = FeatureModelManager.getInstance()
						.getFeatureModel(file.getProject());
				DirectoryColorManager colorManager = DirectoryColorManager
						.getColoredDirectoryManagerS(file.getParent(), fm);
				Set<IFeature> colors = colorManager.getColors(file);
				if (colors != null && !colors.isEmpty())
					return ColorHelper.getCombinedColor(colors);
			}
			if (element instanceof IFolder || element instanceof IProject) {
				IContainer folder = (IContainer) element;
				if (folder.getProject().isOpen()) {
					IFeatureModel fm = FeatureModelManager.getInstance()
							.getFeatureModel(folder.getProject());
					DirectoryColorManager colorManager = DirectoryColorManager
							.getColoredDirectoryManagerS(folder, fm);
					Set<IFeature> colors = colorManager.getFolderColors();
					if (colors != null && !colors.isEmpty())
						return ColorHelper.getCombinedColor(colors);
				}
			}
		} catch (FeatureModelNotFoundException e) {
			// ignore problems, just don't add colors to those projects
		}
		return null;
	}

	public Color getForeground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

}
