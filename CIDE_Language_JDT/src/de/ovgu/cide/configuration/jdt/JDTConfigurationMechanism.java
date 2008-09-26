package de.ovgu.cide.configuration.jdt;

import java.util.Collection;

import coloredide.configuration.ConfigurationException;
import coloredide.configuration.IConfigurationMechanism;
import coloredide.features.IFeature;
import coloredide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.jdt.JDTParserWrapper;

public class JDTConfigurationMechanism implements IConfigurationMechanism {

	public JDTConfigurationMechanism() {
		// TODO Auto-generated constructor stub
	}

	public boolean canConfigureFile(ColoredSourceFile file) {
		return JDTParserWrapper.isJavaFile(file.getResource());
	}

	public String configureFile(ColoredSourceFile sourceFile,
			Collection<IFeature> selectedFeatures)
			throws ConfigurationException {

		return DeleteHiddenNodesVisitor.hideCode(sourceFile, selectedFeatures);
	}

	public int getPriority() {
		return 10;
	}

}
