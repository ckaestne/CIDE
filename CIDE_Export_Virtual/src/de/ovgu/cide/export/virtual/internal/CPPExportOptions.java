package de.ovgu.cide.export.virtual.internal;

import java.util.List;
import java.util.Set;

import de.ovgu.cide.export.useroptions.IUserOption;
import de.ovgu.cide.features.IFeature;

public class CPPExportOptions implements IPPExportOptions {
	@Override
	public String getStartInstruction(Set<IFeature> features) {
		String result = "";
		for (IFeature f : features)
			result += "#ifdef " + getFeatureToken(f) + "\n";
		return result;
	}

	@Override
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

	@Override
	public String getEndInstruction(Set<IFeature> features) {
		String result = "";
		for (int i = 0; i < features.size(); i++)
			result += "#endif\n";
		return result;
	}

	@Override
	public List<IUserOption> getUserOptions() {
		return null;
	}

}
