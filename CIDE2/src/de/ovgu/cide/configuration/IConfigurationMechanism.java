package de.ovgu.cide.configuration;

import java.util.Collection;

import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;

/**
 * this is the general interface that all configuration mechanisms (i.e. all
 * mechanisms that transform, remove or serialize ASTs during the
 * configuration/generation process implement.
 * 
 * configuration mechanisms can decide for themselves whether they handle a
 * certain file. configuration mechanisms have a priority. mechanisms with the
 * highest priority are asked first. priority 0 is reserved for the default
 * generation mechanism that handles all remaining files.
 * 
 * @author ckaestne
 * 
 */
public interface IConfigurationMechanism {
	/**
	 * priority of the mechanism. mechanisms with the highest priority are asked
	 * first whether they can handle a file
	 * 
	 * @return priority > 0, or 0 for default mechanism
	 */
	int getPriority();

	/**
	 * returns whether this mechanism can configure the file
	 * 
	 * @param file
	 * @return
	 */
	boolean canConfigureFile(ColoredSourceFile file);

	/**
	 * performs the actual configuration and returns the content of the target
	 * file.
	 * 
	 * @param sourceFile
	 *            source file
	 * @param selectedFeatures
	 *            selected features
	 * @return content of the configured target file
	 * @throws ConfigurationException
	 *             in case the file cannot be parsed or there is another
	 *             configuration problem
	 */
	String configureFile(ColoredSourceFile sourceFile,
			Collection<IFeature> selectedFeatures)
			throws ConfigurationException;
}
