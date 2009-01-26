package de.ovgu.cide.features.source;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
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

import de.ovgu.cide.af.Alternative;
import de.ovgu.cide.features.Feature;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.IFeatureModelWithID;
import de.ovgu.cide.features.IFeatureWithID;

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
@SuppressWarnings("deprecation")
public class DefaultStorageProvider implements IStorageProvider {

	class DefaultStorageProviderProxy extends StorageProviderProxy {

		public DefaultStorageProviderProxy() {
			super();
			name = "Default";
			id = "default";
		}

		protected void loadTarget() {
			target = DefaultStorageProvider.this;
		}
	}

	protected static IFile getColorFile(IFile javaFile) {
		IPath colorFilePath = javaFile.getFullPath().addFileExtension("color");
		return ResourcesPlugin.getWorkspace().getRoot().getFile(colorFilePath);
	}

	protected static IFile getColorFile(IContainer directory) {
		return directory.getFile(new Path(DIRCOLOR_FILENAME));
	}

	private final static long serialVersionUID = 2l;

	private static final long LEGACY_SERIALIZED_VERSION = 1l;
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
				return emptyMap();

			InputStream is = colorFile.getContents(true);
			ObjectInputStream out = new ObjectInputStream(is);
			try {
				long version = out.readLong();
				if (version == LEGACY_SERIALIZED_VERSION)
					return loadLegacySerialization(out,
							(IFeatureModelWithID) featureModel);
				else if (version != serialVersionUID)
					return emptyMap();
				else
					return loadFeatureMap(out,
							(IFeatureModelWithID) featureModel);
			} finally {
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return emptyMap();
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

	private HashMap<String, Set<IFeature>> emptyMap() {
		return new HashMap<String, Set<IFeature>>();
	}

	public boolean storeAnnotations(IProject project, Object annotatedResource, Map<String, Set<IFeature>> annotations, 
			Map<String, List<String>> parentIDs, IProgressMonitor monitor)
			throws CoreException {
		assert annotatedResource instanceof IFile
				|| annotatedResource instanceof IContainer;
		assert project != null;
		IFile colorFile = getColorFile(annotatedResource);

		try {
			boolean skipStorage = annotations.isEmpty() && !colorFile.exists();
			if (!skipStorage) {
				ByteArrayOutputStream b = new ByteArrayOutputStream();
				ObjectOutputStream out = new ObjectOutputStream(b);
				out.writeLong(serialVersionUID);
				out.writeObject(getIdMap(annotations));
				out.close();
				ByteArrayInputStream source = new ByteArrayInputStream(b
						.toByteArray());
				if (!colorFile.exists())
					colorFile.create(source, true, monitor);
				else
					colorFile.setContents(source, true, true, monitor);
				// System.out.println("saving color file " + colorFile);
			} else {
				if (colorFile.exists())
					colorFile.delete(true, monitor);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * the features themselfs are not serialized, only their IDs. this method
	 * does the serialization
	 * 
	 * @param out
	 * @param featureModel
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws FeatureModelNotFoundException
	 */
	@SuppressWarnings("unchecked")
	private HashMap<String, Set<IFeature>> loadFeatureMap(
			ObjectInputStream out, IFeatureModelWithID featureModel)
			throws IOException, ClassNotFoundException {
		HashMap<String, Set<Long>> storedMap = (HashMap<String, Set<Long>>) out
				.readObject();

		HashMap<String, Set<IFeature>> result = emptyMap();
		for (Map.Entry<String, Set<Long>> entry : storedMap.entrySet()) {
			Set<Long> colorIds = entry.getValue();
			if (!colorIds.isEmpty()) {
				Set<IFeature> features = new HashSet<IFeature>();
				for (long id : colorIds) {
					IFeature feature = featureModel.getFeature(id);
					if (feature != null)
						features.add(feature);
				}
				if (!features.isEmpty())
					result.put(entry.getKey(), features);
			}
		}
		return result;
	}

	/**
	 * old serialization, where a feature class was directly serialized. needed
	 * to be able to load old files
	 * 
	 * @param out
	 * @param project
	 * @param featureModel
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws FeatureModelNotFoundException
	 */
	@SuppressWarnings("unchecked")
	private HashMap<String, Set<IFeature>> loadLegacySerialization(
			ObjectInputStream out, IFeatureModelWithID featureModel)
			throws IOException, ClassNotFoundException {

		HashMap<String, Set<Feature>> storedMap = (HashMap<String, Set<Feature>>) out
				.readObject();

		HashMap<String, Set<IFeature>> result = emptyMap();
		for (Map.Entry<String, Set<Feature>> entry : storedMap.entrySet()) {
			Set<Feature> colorIds = entry.getValue();
			if (!colorIds.isEmpty()) {
				Set<IFeature> features = new HashSet<IFeature>();
				for (Feature id : colorIds) {
					IFeature feature = featureModel.getFeature(id.getId());
					if (feature != null)
						features.add(feature);
				}
				if (!features.isEmpty())
					result.put(entry.getKey(), features);
			}
		}
		return result;
	}

	/**
	 * transforms the id2colors map into a form that is serializable with
	 * standard API objects (Long instead of IFeature)
	 * 
	 * @param annotations
	 * 
	 * @return
	 */
	private Map<String, Set<Long>> getIdMap(
			Map<String, Set<IFeature>> annotations) {
		HashMap<String, Set<Long>> result = new HashMap<String, Set<Long>>();
		for (Map.Entry<String, Set<IFeature>> entry : annotations.entrySet()) {
			Set<IFeature> features = entry.getValue();
			if (!features.isEmpty()) {
				Set<Long> ids = new HashSet<Long>();
				for (IFeature feature : features) {
					assert feature instanceof IFeatureWithID;
					ids.add(((IFeatureWithID) feature).getId());
				}
				if (!ids.isEmpty())
					result.put(entry.getKey(), ids);
			}
		}
		return result;
	}

	public boolean isCompatible(IFeatureModel featureModel) {
		return featureModel instanceof IFeatureModelWithID;
	}
	
	public boolean activateAlternative(IProject project, Object annotatedResource, Alternative alternative, String oldText) {
		return false;
	}

	@Override
	public boolean storeNewAlternative(IProject project, Object annotatedResource, Alternative alternative, String oldText) {
		return false;
	}

	@Override
	public Map<String, List<Alternative>> getAlternatives(IProject project, Object annotatedResource, List<String> ids) {
		return null;
	}
}
