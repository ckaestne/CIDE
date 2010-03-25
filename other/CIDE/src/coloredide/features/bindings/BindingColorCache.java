package coloredide.features.bindings;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;

import coloredide.ColorChangedEvent;
import coloredide.IColorChangeListener;
import coloredide.features.Feature;
import coloredide.features.source.IColorManager;

/**
 * color cache for JavaElements JavaElements are not directly colored (only AST
 * Nodes are) but the color of java elements is needed for validation or similar
 * things where bindings are resolved and colors of the target javaelement of a
 * node (eg. the method declaration of a method call) must be aquired.
 * 
 * 
 * @author cKaestner
 * 
 */
public class BindingColorCache implements Serializable, IColorChangeListener {

	private static final long serialVersionUID = 1L;

	private final HashMap<String, BindingProjectColorCache> project2ColorCache = new HashMap<String, BindingProjectColorCache>();

	public void updateASTColors(IProject project, ASTNode ast,
			final IColorManager nodeColors) {
		getColorCache(project).updateASTColors(ast, nodeColors);
	}

	public Set<Feature> getColors(IProject project, IMethodBinding method) {
		return getColorCache(project).getColors(method);
	}

	public Set<Feature> getColors(IProject project, IVariableBinding field) {
		return getColorCache(project).getColors(field);
	}

	public Set<Feature> getColors(IProject project, String bindingKey) {
		return getColorCache(project).getColors(bindingKey);
	}

	// colors for a parameter
	public Set<Feature> getColors(IProject project, IMethodBinding method,
			int paramIdx) {
		return getColorCache(project).getColors(method, paramIdx);
	}
	// colors for a parameter
	public Set<Feature> getColors(IProject project, String methodKey,
			int paramIdx) {
		return getColorCache(project).getColors(methodKey, paramIdx);
	}

	public Set<Feature> getColors(IProject project, ITypeBinding type) {
		return getColorCache(project).getColors(type);
	}

	// TODO observe this myself
	public void renameProject(String oldName, String newName) {
		BindingProjectColorCache colorCache = project2ColorCache
				.remove(oldName);
		if (colorCache != null) {
			colorCache.project = newName;
			project2ColorCache.put(newName, colorCache);
		}
	}

	private BindingProjectColorCache getColorCache(IProject project) {
		BindingProjectColorCache colorCache = project2ColorCache.get(project
				.getName());
		if (colorCache == null) {
			colorCache = new BindingProjectColorCache(project.getName());
			project2ColorCache.put(project.getName(), colorCache);
		}
		return colorCache;
	}

	public void colorChanged(ColorChangedEvent event) {
		ICompilationUnit compUnit = event.getColoredJavaSourceFile()
				.getCompilationUnit();
		IProject project = compUnit.getJavaProject().getProject();

		for (ASTNode node : event.getAffectedNodes())
			updateASTColors(project, node, event.getColoredJavaSourceFile()
					.getColorManager());
	}

	public void cleanCache(IProject project) {
		project2ColorCache.remove(project.getName());
	}
}
