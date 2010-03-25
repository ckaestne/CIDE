package coloredide.tools.organizeimports;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.ImportDeclaration;

import coloredide.ColorChangedEvent;
import coloredide.ColoredIDEPlugin;
import coloredide.features.Feature;
import coloredide.features.source.IColorManager;
import coloredide.features.source.IColoredJavaSourceFile;
import coloredide.validator.ColoredSourceFileIteratorJob;

public class OrganizeAllImportsJob extends ColoredSourceFileIteratorJob {

	public OrganizeAllImportsJob(IProject[] project) {
		super(project, "Organizing imports", "Organizing");
	}

	protected void processSource(IColoredJavaSourceFile source)
			throws JavaModelException, CoreException {
		CompilationUnit ast = source.getAST();
		IColorManager nodeColors = source.getColorManager();
		nodeColors.beginBatch();
		for (Object i : ast.imports()) {
			if (i instanceof ImportDeclaration) {
				ImportDeclaration imp = (ImportDeclaration) i;
				IBinding binding = imp.resolveBinding();

				Set<Feature> impColors = nodeColors.getOwnColors(imp);
				Set<Feature> targetColors = new HashSet<Feature>();
				if (binding instanceof ITypeBinding) {
					targetColors = ColoredIDEPlugin.getDefault().colorCache
							.getColors(source.getProject(),
									(ITypeBinding) binding);
				}

				if (!targetColors.equals( impColors)) {
					for (Feature color : targetColors) {
						if (!impColors.contains(color))
							nodeColors.addColor(imp, color);
					}
					for (Feature color : impColors) {
						if (!targetColors.contains(color))
							nodeColors.removeColor(imp, color);
					}
					ColoredIDEPlugin.getDefault().notifyListeners(
							new ColorChangedEvent(this, imp, source));
				}
			}
		}
		nodeColors.endBatch();
	}

}
