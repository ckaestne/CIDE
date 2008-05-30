package coloredide.validator.checks;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import coloredide.features.Feature;
import coloredide.features.source.IColoredJavaSourceFile;
import coloredide.validator.ValidationErrorCallback;
import coloredide.validator.ValidationVisitor;

public class OverridingValidator extends ValidationVisitor {

	public OverridingValidator(ValidationErrorCallback callback,
			IColoredJavaSourceFile source) {
		super(callback, source);
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		/*
		 * bug #12: check parameters against overridden methods: when both are
		 * present the parameter must have the same colors, i.e. p_1 - D_1 - D_2 ==
		 * p_2 - D_1 - D_2 (p = parameter color; D = method delcaration color)
		 */

		IMethodBinding binding = node.resolveBinding();
		if (binding == null)
			return false;
		if (!(binding.getJavaElement() instanceof IMethod))
			return false;

		IMethod method = (IMethod) binding.getJavaElement();
		IMethod overriddenMethod = MethodCallValidator
				.findOverriddenMethod(method);
		if (overriddenMethod != null) {
			checkOverridingParameters(node, node.parameters(), overriddenMethod
					.getKey());
		}

		return super.visit(node);
	}

	private void checkOverridingParameters(ASTNode node,
			List<SingleVariableDeclaration> params, String overriddenMethodKey) {

		// Set<Feature> methodDeclColors = javaElementColors().getColors(
		// project(), binding);
		Set<Feature> methodDeclColors = nodeColors().getColors(node);
		Set<Feature> superMethodDeclColors = javaElementColors().getColors(
				project(), overriddenMethodKey);

		for (int paramIdx = 0; paramIdx < params.size(); paramIdx++) {
			SingleVariableDeclaration param = params.get(paramIdx);
			/*
			 * cf. bug #12: only those colors that differ from the method
			 * invocations colors are compared
			 */

			// determine the direct colors of the parameter
			Set<Feature> ownSuperParamColors = new HashSet<Feature>(
					javaElementColors().getColors(project(),
							overriddenMethodKey, paramIdx));
			ownSuperParamColors.removeAll(methodDeclColors);
			ownSuperParamColors.removeAll(superMethodDeclColors);

			Set<Feature> allParamColors = nodeColors().getColors(param);
			Set<Feature> ownParamColors = new HashSet<Feature>(
					allParamColors);
			ownParamColors.removeAll(methodDeclColors);
			ownParamColors.removeAll(superMethodDeclColors);

			if (!ownSuperParamColors.equals(ownParamColors))
				callback.errorOverridingParamMustHaveDeclarationColor(param,
						ownSuperParamColors, "");
		}
	}
}
