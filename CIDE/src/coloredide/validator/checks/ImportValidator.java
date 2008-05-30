package coloredide.validator.checks;

import java.util.HashMap;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.Type;

import coloredide.features.Feature;
import coloredide.features.source.IColoredJavaSourceFile;
import coloredide.validator.ValidationErrorCallback;
import coloredide.validator.ValidationVisitor;

/**
 * checks that used types have the same colors as declared ones.
 * 
 * @author cKaestner
 * 
 */

public class ImportValidator extends ValidationVisitor {

	private final HashMap<ITypeBinding, Set<Feature>> importedTypes = new HashMap<ITypeBinding, Set<Feature>>();

	public ImportValidator(ValidationErrorCallback callback,
			IColoredJavaSourceFile source) {
		super(callback, source);
		// TODO Auto-generated constructor stub
	}

	/**
	 * checks that the import declaration match the target types
	 * 
	 * (import vs target types)
	 */
	public boolean visit(ImportDeclaration node) {
		Set<Feature> importColors = nodeColors().getColors(node);

		IBinding binding = node.resolveBinding();
		Set<Feature> targetColors = null;
		if (binding instanceof ITypeBinding) {
			targetColors = javaElementColors().getColors(project(),
					(ITypeBinding) binding);
			importedTypes.put((ITypeBinding) binding, importColors);
		}
		if (binding instanceof IMethodBinding) {
			targetColors = javaElementColors().getColors(project(),
					(IMethodBinding) binding);
		}
		if (binding instanceof IVariableBinding) {
			targetColors = javaElementColors().getColors(project(),
					(IVariableBinding) binding);
		}
		if (targetColors != null) {

			if (!importColors.containsAll(targetColors))
				callback.errorImportMustHaveTargetColor(node, targetColors);
		}
		return super.visit(node);
	}

	/**
	 * checks that imported types use the correct color for the import
	 * declaration
	 * 
	 * (local types vs imports)
	 */
	public void preVisit(ASTNode node) {
		if (node instanceof Type) {
			Type type = (Type) node;
			ITypeBinding binding = type.resolveBinding();
			if (binding != null) {
				if (importedTypes.containsKey(binding)) {
					Set<Feature> importColors = importedTypes.get(binding);
					Set<Feature> nodeColors = nodeColors().getColors(node);
					if (!nodeColors.containsAll(importColors))
						callback.errorImportedTypeMustHaveImportColor(node,
								nodeColors, importColors);
				}
			}
		}
		super.preVisit(node);
	}

}
