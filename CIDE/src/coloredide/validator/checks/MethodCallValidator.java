package coloredide.validator.checks;

import java.util.Set;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
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
 * 
 * @author cKaestner
 * 
 */
public class MethodCallValidator extends ValidationVisitor {

	public MethodCallValidator(ValidationErrorCallback callback,
			IColoredJavaSourceFile source) {
		super(callback, source);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean visit(MethodInvocation node) {
		IMethodBinding binding = node.resolveMethodBinding();
		handleMethodCall(node, binding);

		return super.visit(node);
	}

	@Override
	public boolean visit(ConstructorInvocation node) {
		IMethodBinding binding = node.resolveConstructorBinding();
		handleMethodCall(node, binding);
		return super.visit(node);
	}

	@Override
	public boolean visit(ClassInstanceCreation node) {
		IMethodBinding binding = node.resolveConstructorBinding();
		handleMethodCall(node, binding);
		return super.visit(node);
	}

	@Override
	public boolean visit(SuperConstructorInvocation node) {
		IMethodBinding binding = node.resolveConstructorBinding();
		handleMethodCall(node, binding);
		return super.visit(node);
	}

	@Override
	public boolean visit(SuperMethodInvocation node) {
		IMethodBinding binding = node.resolveMethodBinding();
		handleMethodCall(node, binding);
		return super.visit(node);
	}

	private void handleMethodCall(ASTNode node, IMethodBinding binding) {
		if (binding == null) {
			callback.warningCannotResolveBinding(node);
			return;
		}

		Set<Feature> declColors = javaElementColors().getColors(project(),
				binding);
		Set<Feature> callColors = nodeColors().getColors(node);

		if (callColors.containsAll(declColors))
			return;

		// bug #12: also look for overridden methods (method declarations in
		// parent classes with same signature)
		assert binding.getJavaElement() instanceof IMethod;
		IMethod method = (IMethod) binding.getJavaElement();
		while ((method = findOverriddenMethod(method)) != null) {
			Set<Feature> superMethodDeclColors = javaElementColors().getColors(
					project(), method.getKey());
			if (callColors.containsAll(superMethodDeclColors))
				return;
		}

		callback.errorCallMustHaveDeclarationColor(node, callColors, binding,
				declColors);
	}

	/**
	 * util function
	 * 
	 * returns a super method or null of there is none
	 */
	public static IMethod findOverriddenMethod(IMethod method) {
		try {
			IType type = method.getDeclaringType();
			ITypeHierarchy hierarchy = type.newSupertypeHierarchy(null);

			while ((type = hierarchy.getSuperclass(type)) != null) {
				IMethod[] overriddenMethods = type.findMethods(method);
				if (overriddenMethods!=null && overriddenMethods.length>0)
					return overriddenMethods[0];
			}

			return null;
		} catch (JavaModelException e) {
			return null;
		}
	}

}
