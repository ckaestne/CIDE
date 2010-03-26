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

package de.ovgu.cide.storage.def;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

import de.ovgu.cide.alternativefeatures.Alternative;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.IFeatureModelWithID;
import de.ovgu.cide.features.IFeatureWithID;
import de.ovgu.cide.features.source.IStorageProvider;

/**
 * default storage provider, uses .color files for each annotated file and
 * .dircolors for annotated directories.
 * 
 * can only store features of the list or guidsl feature model that implement
 * the {@link IFeatureWithID} interface
 * 
 * annotatedResources have to be {@link IFile} or {@link IContainer}
 * 
 * @author ckaestne
 * 
 */
public class DefaultStorageProvider implements IStorageProvider {

	protected static IFile getColorFile(IFile javaFile) {
		IPath colorFilePath = javaFile.getFullPath().addFileExtension("color");
		return ResourcesPlugin.getWorkspace().getRoot().getFile(colorFilePath);
	}

	protected static IFile getColorFile(IContainer directory) {
		return directory.getFile(new Path(DIRCOLOR_FILENAME));
	}

	public static final String DIRCOLOR_FILENAME = ".dircolors";

	public Map<String, Set<IFeature>> readAnnotations(IProject project,
			Object annotatedResource, IFeatureModel featureModel) {
		assert annotatedResource instanceof IFile
				|| annotatedResource instanceof IContainer;
		assert project != null;
		assert featureModel instanceof IFeatureModelWithID;
		IFile colorFile = getColorFile(annotatedResource);

		try {
			if (!colorFile.exists())
				return new HashMap<String, Set<IFeature>>();

			InputStream is = colorFile.getContents(true);
			boolean isXML = is.read() == '<';

			is = colorFile.getContents(true);
			// check for XML file
			if (isXML)
				return CIDEXMLReader.loadFeatureMap(new InputStreamReader(is),
						(IFeatureModelWithID) featureModel);

			// otherwise legacy formats
			ObjectInputStream out = new ObjectInputStream(is);
			try {
				long version = out.readLong();
				if (version == CIDELegacyReader.LEGACY_SERIALIZED_VERSION)
					return CIDELegacyReader.loadLegacySerialization(out,
							(IFeatureModelWithID) featureModel);
				else if (version != CIDESerializedReader.serialVersionUID)
					return new HashMap<String, Set<IFeature>>();
				else
					return CIDESerializedReader.loadFeatureMap(out,
							(IFeatureModelWithID) featureModel);
			} finally {
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new HashMap<String, Set<IFeature>>();
		}
	}

	private IFile getColorFile(Object annotatedResource) {
		IFile colorFile;
		if (annotatedResource instanceof IFile)
			colorFile = getColorFile((IFile) annotatedResource);
		else if (annotatedResource instanceof IContainer)
			colorFile = getColorFile((IContainer) annotatedResource);
		else
			throw new RuntimeException(
					"annotated resource is not a file or directory");
		return colorFile;
	}

	public boolean storeAnnotations(IProject project, Object annotatedResource,
			Map<String, Set<IFeature>> annotations,
			Map<String, Boolean> isOptional,
			Map<String, List<String>> parentIDs, IProgressMonitor monitor)
			throws CoreException {
		assert annotatedResource instanceof IFile
				|| annotatedResource instanceof IContainer;
		assert project != null;
		IFile colorFile = getColorFile(annotatedResource);

		boolean skipStorage = !containsColors(annotations);
		if (!skipStorage || colorFile.exists()) {
			InputStream source = new CIDEXMLWriter()
					.serializeAnnotations(annotations);
			if (!colorFile.exists())
				colorFile.create(source, true, monitor);
			else
				colorFile.setContents(source, true, true, monitor);
			// System.out.println("saving color file " + colorFile);
		}
		if (skipStorage && colorFile.exists())
			colorFile.delete(true, false, monitor);

		return true;
	}

	private boolean containsColors(Map<String, Set<IFeature>> annotations) {
		for (Set<IFeature> colors : annotations.values())
			if (!colors.isEmpty())
				return true;
		return false;
	}

	public boolean isCompatible(IFeatureModel featureModel) {
		return featureModel instanceof IFeatureModelWithID;
	}

	public boolean activateAlternative(IProject project,
			Object annotatedResource, Alternative alternative,
			Map<String, String> id2oldText) {
		return false;
	}

	public boolean storeNewAlternative(IProject project,
			Object annotatedResource, Alternative alternative,
			Map<String, String> id2oldText) {
		return false;
	}

	public Map<String, List<Alternative>> getAllAlternatives(IProject project,
			Object annotatedResource, IFeatureModelWithID featureModel) {
		return null;
	}

	public boolean canHandleAlternatives() {
		return false;
	}

	public boolean isColorStorageFile(IFile file) {
		if ("color".equals(file.getFileExtension()))
			return true;
		if (DefaultStorageProvider.DIRCOLOR_FILENAME.equals(file.getName()))
			return true;
		return false;
	}
}
