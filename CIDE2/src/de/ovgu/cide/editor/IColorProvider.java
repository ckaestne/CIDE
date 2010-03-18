package de.ovgu.cide.editor;

import java.util.Set;

import cide.gast.IASTNode;
import de.ovgu.cide.features.IFeature;

/**
 * subset of the colormanager interface
 * 
 * @author ckaestne
 * 
 */
public interface IColorProvider {

	public Set<IFeature> getColors(IASTNode node);
}
