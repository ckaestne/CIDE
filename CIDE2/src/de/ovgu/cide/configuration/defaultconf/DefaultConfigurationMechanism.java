package de.ovgu.cide.configuration.defaultconf;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import de.ovgu.cide.configuration.ConfigurationException;
import de.ovgu.cide.configuration.IConfigurationMechanism;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;

/**
 * default mechanisms using AST transformations and rendering using the IASTNode
 * interfaces...
 * 
 * used by all languages generated from a gCIDE grammar
 * 
 * @author ckaestne
 * 
 */
public class DefaultConfigurationMechanism implements IConfigurationMechanism {

	/**
	 * can configure any file by default
	 */
	public boolean canConfigureFile(ColoredSourceFile file) {
		return true;
	}

	public String configureFile(ColoredSourceFile sourceFile,
			Collection<IFeature> selectedFeatures)
			throws ConfigurationException {
		Set<IFeature> hiddenColors = new HashSet<IFeature>();
		hiddenColors.addAll(sourceFile.getFeatureModel().getVisibleFeatures());
		hiddenColors.removeAll(selectedFeatures);
		return new ConfigureASTHelper().hideCode(sourceFile, hiddenColors);
	}

	/**
	 * lowest priority
	 */
	public int getPriority() {
		return 0;
	}

}
