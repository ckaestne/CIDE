package de.ovgu.cide.export.virtual.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import de.ovgu.cide.features.IFeature;

public class MungeExportOptions implements IPPExportOptions {

	@Override
	public String getStartInstruction(Set<IFeature> features) {
		String result = "";
		ArrayList<IFeature> sortedFeatures = new ArrayList<IFeature>(features);
		Collections.sort(sortedFeatures);
		for (IFeature f : sortedFeatures)
			result += "/*if[" + getFeatureToken(f) + "]*/";
		return result;
	}

	@Override
	public boolean inNewLine() {
		return false;
	}

	private String getFeatureToken(IFeature f) {
		String featureName = f.getName().toUpperCase();
		StringBuffer result = new StringBuffer();
		for (int idx = 0; idx < featureName.length(); idx++)
			if (Character.isLetter(featureName.charAt(idx)))
				result.append(featureName.charAt(idx));
		return result.toString();
	}

	@Override
	public String getEndInstruction(Set<IFeature> features) {
		String result = "";
		ArrayList<IFeature> sortedFeatures = new ArrayList<IFeature>(features);
		Collections.sort(sortedFeatures);
		Collections.reverse(sortedFeatures);
		for (IFeature f : sortedFeatures)
			result += "/*end[" + getFeatureToken(f) + "]*/";
		return result;
	}

}
