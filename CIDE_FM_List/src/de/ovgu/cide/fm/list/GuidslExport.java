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

import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureWithID;

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
