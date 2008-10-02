package de.ovgu.cide.storage.purevariants;

import java.nio.MappedByteBuffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import coloredide.features.FeatureModelManager;
import coloredide.features.FeatureModelNotFoundException;
import coloredide.features.IFeature;
import coloredide.features.IFeatureModel;
import coloredide.features.source.IStorageProvider;

import com.ps.consul.eclipse.ui.mapping.MappedObject;
import com.ps.consul.eclipse.ui.mapping.Mapping;
import com.ps.consul.eclipse.ui.mapping.Rule;
import com.ps.consul.eclipse.ui.mapping.Util;
import com.ps.xml.ID;

import de.ovgu.cide.fm.purevariants.PVFeatureModel;
import de.ovgu.cide.fm.purevariants.RuleAdapter;

public class PVStorage implements IStorageProvider {

	public boolean isCompatible(IFeatureModel featureModel) {
		return featureModel instanceof PVFeatureModel;
	}

	public Map<String, Set<IFeature>> readAnnotations(IProject project,
			Object annotatedResource, IFeatureModel featureModel) {
		assert featureModel instanceof PVFeatureModel;
		Mapping mapping = ((PVFeatureModel) featureModel).getMapping();

		Map<String, Set<IFeature>> annotations = new HashMap<String, Set<IFeature>>();
		String resourceId = getResourceId(annotatedResource);

		for (Rule rule : mapping.getRules()) {
			for (MappedObject mappedObject : rule.getMappedObjects()) {
				if (mappedObject.getName().equals(resourceId)) {
					readAnnotations(annotations, rule, mappedObject);
				}
			}
		}

		return annotations;
	}

	private void readAnnotations(Map<String, Set<IFeature>> annotations,
			Rule rule, MappedObject mappedObject) {
		IFeature feature = new RuleAdapter(rule);
		Set<String> keys = deserialize(mappedObject.getAttributeValue(
				"fragmentIds", null));
		for (String key : keys)
			addF(annotations, key, feature);
	}

	private void addF(Map<String, Set<IFeature>> annotations, String key,
			IFeature feature) {
		Set<IFeature> features = annotations.get(key);
		if (features == null) {
			features = new HashSet<IFeature>();
			annotations.put(key, features);
		}
		features.add(feature);
	}

	private Set<String> deserialize(String attributeValue) {
		Set<String> result = new HashSet<String>();
		int p;
		while ((p = attributeValue.indexOf('#')) >= 0) {
			result.add(attributeValue.substring(0, p - 1));
			attributeValue = attributeValue.substring(p + 1);
		}
		return result;
	}

	public boolean storeAnnotations(IProject project, Object annotatedResource,
			Map<String, Set<IFeature>> annotations, IProgressMonitor monitor)
			throws CoreException {
		// IFeatureModel featureModel;
		// try {
		// featureModel = FeatureModelManager.getInstance().getFeatureModel(
		// project);
		// } catch (FeatureModelNotFoundException e) {
		// e.printStackTrace();
		// return false;
		// }
		// assert featureModel instanceof PVFeatureModel;
		String resourceId = getResourceId(annotatedResource);
		Map<IFeature, Set<String>> transformedAnnotations = transformAnnotations(annotations);

		for (Entry<IFeature, Set<String>> entry : transformedAnnotations
				.entrySet()) {
			storeAnnotation(resourceId, entry.getKey(), entry.getValue());
		}

		return true;
	}

	private String getResourceId(Object annotatedResource) {
		assert annotatedResource instanceof IResource;
		String resourceId = "CIDE://"
				+ ((IResource) annotatedResource).getProjectRelativePath()
						.toPortableString();
		return resourceId;
	}

	private void storeAnnotation(String fileId, IFeature key, Set<String> value)
			throws CoreException {
		Rule rule = ((RuleAdapter) key).getRule();

		for (MappedObject mappedObject : rule.getMappedObjects()) {
			if (mappedObject.getName().equals(fileId))
				rule.deleteMappedObject(mappedObject);
		}
		Map<String, String> attr = new HashMap<String, String>();
		attr.put("fragmentIds", serialize(value));
		rule.createMappedObject(Util.makeID(fileId), fileId, attr);
	}

	private String serialize(Set<String> value) {
		String result = "";
		for (String v : value)
			result += v + "#";
		return result;
	}

	private Map<IFeature, Set<String>> transformAnnotations(
			Map<String, Set<IFeature>> annotations) {
		HashMap<IFeature, Set<String>> result = new HashMap<IFeature, Set<String>>();

		for (Entry<String, Set<IFeature>> annotation : annotations.entrySet()) {
			for (IFeature feature : annotation.getValue())
				addK(result, feature, annotation.getKey());
		}

		return result;

	}

	private void addK(HashMap<IFeature, Set<String>> result, IFeature f,
			String key) {
		Set<String> locations = result.get(f);
		if (locations == null) {
			locations = new HashSet<String>();
			result.put(f, locations);
		}
		locations.add(key);
	}

}
