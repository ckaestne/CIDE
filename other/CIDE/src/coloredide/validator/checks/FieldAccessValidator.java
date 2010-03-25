package coloredide.validator.checks;

import java.util.Set;

import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;

import coloredide.features.Feature;
import coloredide.features.source.IColoredJavaSourceFile;
import coloredide.validator.ValidationErrorCallback;
import coloredide.validator.ValidationVisitor;

/**
 * checks that every access to a field belongs to the same feature as it's
 * declaration. field colors are stored (cached) by the AST2Feature class
 * 
 * 
 * @author cKaestner
 * 
 */
public class FieldAccessValidator extends ValidationVisitor {

	public FieldAccessValidator(ValidationErrorCallback callback,
			IColoredJavaSourceFile source) {
		super(callback, source);
		// TODO Auto-generated constructor stub
	}

	public boolean visit(SimpleName node) {
		visitName(node);
		return super.visit(node);
	}

	public boolean visit(QualifiedName node) {
		visitName(node);
		return super.visit(node);
	}

	public void visitName(Name node) {
		IBinding binding = node.resolveBinding();

		if (binding instanceof IVariableBinding
				&& ((IVariableBinding) binding).isField()) {
			Set<Feature> declColors = javaElementColors().getColors(project(),
					(IVariableBinding) binding);
			Set<Feature> callColors = nodeColors().getColors(node);
			if (!callColors.containsAll(declColors))
				callback.errorVariableAccessMustHaveDeclarationColor(node,
						callColors, (IVariableBinding) binding, declColors);

		}
	}
}
