package coloredide.validator.checks;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;

import coloredide.features.Feature;
import coloredide.features.source.IColoredJavaSourceFile;
import coloredide.validator.ValidationErrorCallback;
import coloredide.validator.ValidationVisitor;

/**
 * checks that every access to a methods belongs to the same feature as it's
 * declaration. method colors are stored (cached) by the AST2Feature class
 * 
 * paramter colors are a little difficult because the number and types of
 * parameters must match in any case:
 * 
 * colors that are defined directly on the parameter must also be defined in the
 * call and vice versa. (bug#12: fix according to cFJ paper, the colors each
 * minus the colors declared on the method invocation must match)
 * 
 * in contrast inherited colors share the known behavior of methods, the
 * declaration color must be a subset of the call colors.
 * 
 * 
 * @author cKaestner
 * 
 */
public class MethodParamValidator extends ValidationVisitor {

	public MethodParamValidator(ValidationErrorCallback callback,
			IColoredJavaSourceFile source) {
		super(callback, source);
		// TODO Auto-generated constructor stub
	}

	public boolean visit(MethodInvocation node) {
		IMethodBinding binding = node.resolveMethodBinding();
		if (binding == null) {
			return false;
		}
		handleMethod(node, node.arguments(), binding);
		return super.visit(node);
	}

	@Override
	public boolean visit(ConstructorInvocation node) {
		IMethodBinding binding = node.resolveConstructorBinding();
		if (binding == null) {
			return false;
		}
		handleMethod(node, node.arguments(), binding);
		return super.visit(node);
	}

	@Override
	public boolean visit(ClassInstanceCreation node) {
		IMethodBinding binding = node.resolveConstructorBinding();
		if (binding == null) {
			return false;
		}
		handleMethod(node, node.arguments(), binding);
		return super.visit(node);
	}

	@Override
	public boolean visit(SuperConstructorInvocation node) {
		IMethodBinding binding = node.resolveConstructorBinding();
		if (binding == null) {
			return false;
		}
		handleMethod(node, node.arguments(), binding);
		return super.visit(node);
	}

	@Override
	public boolean visit(SuperMethodInvocation node) {
		IMethodBinding binding = node.resolveMethodBinding();
		if (binding == null) {
			return false;
		}
		handleMethod(node, node.arguments(), binding);
		return super.visit(node);
	}

	private void handleMethod(ASTNode node, List params, IMethodBinding binding) {

		// Set<Feature> methodDeclColors = javaElementColors().getColors(
		// project(), binding);
		Set<Feature> methodCallColors = nodeColors().getColors(node);

		for (int paramIdx = 0; paramIdx < params.size(); paramIdx++) {
			Expression param = (Expression) params.get(paramIdx);
			/*
			 * cf. bug #12: only those colors that differ from the method
			 * invocations colors are compared
			 */

			// determine the direct colors of the parameter
			Set<Feature> ownParamDeclColors = new HashSet<Feature>(
					javaElementColors().getColors(project(), binding, paramIdx));
			ownParamDeclColors.removeAll(methodCallColors);

			Set<Feature> allParamCallColors = nodeColors().getColors(param);
			Set<Feature> ownParamCallColors = new HashSet<Feature>(
					allParamCallColors);
			ownParamCallColors.removeAll(methodCallColors);

			if (!ownParamDeclColors.equals(ownParamCallColors))
				callback.errorCallParamMustHaveDeclarationColor(param,
						ownParamDeclColors,"");
		}
	}

}
