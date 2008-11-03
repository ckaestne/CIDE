package de.ovgu.cide.fm.list;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.RGB;

import coloredide.features.IFeature;
import coloredide.features.IFeatureWithID;

/**
 * this class can export an .colors file to a pair of model.m and model.colors
 * files for the guidsl plugin.
 * 
 * this is experimental and will probably not be released.
 * 
 * @author ckaestne
 * 
 */
public class GuidslExport {

	public void exportToGuidsl(ListFeatureModel listModel,
			IProject targetProject) throws CoreException, IOException {
		try {
			IFile grammarFile = targetProject.getFile("model.m");
			assert !grammarFile.exists();
			IFile grammarColorFile = targetProject.getFile("model.colors");
			assert !grammarColorFile.exists();

			String grammarContent = "SPL: ";
			Map<String, Long> featureIds = new HashMap<String, Long>();
			Map<Long, RGB> featureColors = new HashMap<Long, RGB>();
			Map<Long, Boolean> featureVisibility = new HashMap<Long, Boolean>();
			for (IFeature f : listModel.getFeatures()) {
				String name = f.getName().replace(' ', '_');
				grammarContent += "[" + name + "] ";
				featureIds.put(name, ((IFeatureWithID) f).getId());
				featureColors.put(((IFeatureWithID) f).getId(), f.getRGB());
				featureVisibility.put(((IFeatureWithID) f).getId(), f
						.isVisible());
			}
			grammarContent += "::generatedSPL;";
			grammarFile.create(new ByteArrayInputStream(grammarContent
					.getBytes()), true, null);

			ByteArrayOutputStream b = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(b);
			out.writeLong(1l);
			out.writeObject(featureIds);
			out.writeObject(featureColors);
			out.writeObject(featureVisibility);
			out.close();
			ByteArrayInputStream source = new ByteArrayInputStream(b
					.toByteArray());
			assert !grammarColorFile.exists();
			grammarColorFile.create(source, true, null);

		} catch (NoClassDefFoundError e) {
			throw new RuntimeException("Guidsl Feature Model plugin not loaded");
		}

	}

}
