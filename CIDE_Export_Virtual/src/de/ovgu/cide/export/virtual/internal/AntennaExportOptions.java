package de.ovgu.cide.export.virtual.internal;

import java.util.List;
import java.util.Set;

import de.ovgu.cide.export.useroptions.IUserOption;
import de.ovgu.cide.features.IFeature;

public class AntennaExportOptions implements IPPExportOptions {
	public String getStartInstruction(Set<IFeature> features) {
		String result = "";
		for (IFeature f : features)
			result += "//#ifdef " + getFeatureToken(f) + "\n";
		return result;
	}

	public boolean inNewLine() {
		return true;
	}

	private String getFeatureToken(IFeature f) {
		String featureName = f.getName().toUpperCase();
		StringBuffer result = new StringBuffer();
		for (int idx = 0; idx < featureName.length(); idx++)
			if (Character.isLetter(featureName.charAt(idx)))
				result.append(featureName.charAt(idx));
		return result.toString();
	}

	public String getEndInstruction(Set<IFeature> features) {
		String result = "";
		for (int i = 0; i < features.size(); i++)
			result += "//#endif\n";
		return result;
	}

	public List<IUserOption> getUserOptions() {
		return null;
	}

}
