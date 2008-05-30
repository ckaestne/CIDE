package coloredide.features.source;

import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ExpressionStatement;

import coloredide.features.Feature;

public interface IColorManager {

	public abstract Set<Feature> getOwnColors(ASTNode node);

	public abstract boolean addColor(ASTNode node, Feature color);

	public abstract void addColors(ASTNode node, Set<Feature> colors);

	public abstract boolean removeColor(ASTNode node, Feature color);

	public abstract boolean hasColor(ASTNode node, Feature color);

	public abstract Set<Feature> getColors(ASTNode node);

	public abstract Set<Feature> getInheritedColors(ASTNode node);

	public abstract void beginBatch();

	public abstract void endBatch();

	public abstract boolean clearColor(ASTNode node);

	public abstract boolean hasColors();

	/**
	 * in temporary mode changes will not be saved and as soon as temporary mode
	 * is disabled all changes will be discarded
	 * 
	 * @param enable
	 */
	public abstract void setTemporaryMode(boolean enable);

	public abstract void setColors(ASTNode node, Set<Feature> newColors);

	// <TESTING
	public abstract IColoredJavaSourceFile getSource();
	// >

}