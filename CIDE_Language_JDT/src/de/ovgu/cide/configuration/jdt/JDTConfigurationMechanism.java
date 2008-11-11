package de.ovgu.cide.configuration.jdt;

import java.util.Collection;

import de.ovgu.cide.configuration.ConfigurationException;
import de.ovgu.cide.configuration.IConfigurationMechanism;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
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
