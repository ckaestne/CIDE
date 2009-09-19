package de.ovgu.cide.typing.jdt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.QualifiedType;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.WildcardType;

import cide.gast.IASTNode;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.jdt.ASTBridge;
import de.ovgu.cide.typing.jdt.checks.FieldAccessCheck;
import de.ovgu.cide.typing.jdt.checks.ImportTargetCheck;
import de.ovgu.cide.typing.jdt.checks.InheritedMethodExceptionCheck;
import de.ovgu.cide.typing.jdt.checks.InheritedMethodNameCheck;
import de.ovgu.cide.typing.jdt.checks.LocalVariableReferenceCheck;
import de.ovgu.cide.typing.jdt.checks.MethodExceptionImplementationCheck;
import de.ovgu.cide.typing.jdt.checks.MethodNameImplementationCheck;
import de.ovgu.cide.typing.jdt.checks.MethodInvocationCheck;
import de.ovgu.cide.typing.jdt.checks.InheritedMethodParameterCheck;
import de.ovgu.cide.typing.jdt.checks.MethodParameterImplementationCheck;
import de.ovgu.cide.typing.jdt.checks.TypeImportedCheck;
import de.ovgu.cide.typing.jdt.checks.TypeReferenceCheck;
import de.ovgu.cide.typing.jdt.checks.util.CheckUtils;
import de.ovgu.cide.typing.jdt.checks.util.MethodPathItem;
import de.ovgu.cide.typing.model.ITypingCheck;

/**
 * visitor for an eclipse AST that generates all kinds of checks for that
 * specific file
 * 
 * split into subclasses for different concerns
 * 
 * TODO: overriding and parameters currently not implemented, several other
 * checks from ASE paper maybe missing
 * 
 * @author cKaestner, aDreiling
 * 
 */

class JDTCheckGenerator extends JDTCheckGenerator_TypeReferences {

	public JDTCheckGenerator(ColoredSourceFile file,
			JDTTypingProvider jdtTypingProvider, Set<ITypingCheck> checks) {
		super(file, jdtTypingProvider, checks);
	}

}

class JDTCheckGenerator_TypeReferences extends JDTCheckGenerator_LocalVariables {

	public JDTCheckGenerator_TypeReferences(ColoredSourceFile file,
			JDTTypingProvider jdtTypingProvider, Set<ITypingCheck> checks) {
		super(file, jdtTypingProvider, checks);
	}

	@Override
	public void visitType(Type node) {
		ITypeBinding binding = node.resolveBinding();
		if (binding != null)
			checks.add(new TypeReferenceCheck(file, jdtTypingProvider,
					bridge(node), binding));
		super.visitType(node);
	}
}

class JDTCheckGenerator_LocalVariables extends JDTCheckGenerator_Imports {

	public JDTCheckGenerator_LocalVariables(ColoredSourceFile file,
			JDTTypingProvider jdtTypingProvider, Set<ITypingCheck> checks) {
		super(file, jdtTypingProvider, checks);
	}

	private final HashMap<IVariableBinding, IASTNode> knownVariableDeclarations = new HashMap<IVariableBinding, IASTNode>();

	@Override
	public boolean visit(VariableDeclarationFragment node) {
		visitVD(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(SingleVariableDeclaration node) {
		visitVD(node);
		return super.visit(node);
	}

	private void visitVD(VariableDeclaration node) {
		IVariableBinding binding = node.resolveBinding();

		if (binding != null)
			knownVariableDeclarations.put(binding, bridge(node));
	}

	@Override
	protected void visitName(Name node) {
		IBinding binding = node.resolveBinding();

		if (binding instanceof IVariableBinding
				&& !((IVariableBinding) binding).isField()) {
			if (knownVariableDeclarations.get(binding) != null)
				checks
						.add(new LocalVariableReferenceCheck(file,
								jdtTypingProvider, bridge(node),
								knownVariableDeclarations.get(binding), node
										.toString()));
		}
		super.visitName(node);
	}
}

class JDTCheckGenerator_Imports extends JDTCheckGenerator_Methods {

	public JDTCheckGenerator_Imports(ColoredSourceFile file,
			JDTTypingProvider jdtTypingProvider, Set<ITypingCheck> checks) {
		super(file, jdtTypingProvider, checks);
	}

	private final HashMap<ITypeBinding, IASTNode> importedTypes = new HashMap<ITypeBinding, IASTNode>();

	/**
	 * checks that the import declaration match the target types
	 * 
	 * (import vs target types)
	 */
	@Override
	public boolean visit(ImportDeclaration node) {
		IBinding binding = node.resolveBinding();
		if (binding instanceof ITypeBinding) {
			importedTypes.put((ITypeBinding) binding, bridge(node));
		}
		if (binding != null) {
			checks.add(new ImportTargetCheck(file, jdtTypingProvider,
					bridge(node), binding));
		}
		return super.visit(node);
	}

	/**
	 * checks that imported types use the correct color for the import
	 * declaration
	 * 
	 * (local types vs imports)
	 */
	@Override
	public void visitType(Type node) {
		ITypeBinding binding = node.resolveBinding();
		if (binding != null) {
			if (importedTypes.containsKey(binding)) {
				checks.add(new TypeImportedCheck(file, jdtTypingProvider,
						bridge(node), importedTypes.get(binding), node
								.toString()));

			}
		}
		super.visitType(node);
	}
}

class JDTCheckGenerator_Methods extends JDTCheckGenerator_FieldAccess {
	public JDTCheckGenerator_Methods(ColoredSourceFile file,
			JDTTypingProvider jdtTypingProvider, Set<ITypingCheck> checks) {
		super(file, jdtTypingProvider, checks);
	}

	/* METHOD INVOCATION PART */
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
		if (binding != null) {
			checks.add(new MethodInvocationCheck(file, jdtTypingProvider,
					bridge(node), binding));

		}
	}

	/* METHOD DECLARATION PART */
	@Override
	public boolean visit(MethodDeclaration node) {
		IMethodBinding binding = node.resolveBinding();

		List<MethodPathItem> inhMethods = new ArrayList<MethodPathItem>();

		boolean foundInhMethods = initializeAndCollectInhData(binding,
				inhMethods);

		if (foundInhMethods) {

			handleInheritedAbstractMethodImplementation(node, binding,
					inhMethods);
			handleOverridingRelationshipViolation(node, binding, inhMethods);
		}

		return super.visit(node);
	}

	private boolean initializeAndCollectInhData(IMethodBinding binding,
			List<MethodPathItem> inhMethods) {
		if (binding == null)
			return false;

		// check if method is abstract
		if (Modifier.isAbstract(binding.getModifiers()))
			return false;

		ITypeBinding declTypeBinding = binding.getDeclaringClass();

		// check only for classes
		if (declTypeBinding == null || !declTypeBinding.isClass())
			return false;

		Set<String> checkedInterfaces = new HashSet<String>();

		// (recursively) collects all keys of methods in abstract classes which
		// belongs to this declaration
		CheckUtils.collectSimilarMethodKeysInSuperClasses(binding,
				declTypeBinding.getSuperclass(), inhMethods, checkedInterfaces);

		// (recursively) collects all keys of methods in interfaces which
		// belongs to this declaration
		CheckUtils.collectSimilarMethodKeysInInterfaces(binding,
				declTypeBinding.getInterfaces(), inhMethods, checkedInterfaces);

		// the set should contain at least one inherited method
		if (inhMethods.size() == 0)
			return false;

		return true;
	}

	/**
	 * generate checks accordingly.
	 * 
	 * @param node
	 * @param binding
	 * @param declTypeBinding
	 */
	private void handleInheritedAbstractMethodImplementation(
			MethodDeclaration node, IMethodBinding methodBinding,
			List<MethodPathItem> inhMethods) {

		// add check for method name
		checks.add(new MethodNameImplementationCheck(file, jdtTypingProvider,
				bridge(node), methodBinding, inhMethods));

		//add checks for parameters
		List parameterList = node.parameters();

		for (int j = 0; j < parameterList.size(); j++) {

			checks.add(new MethodParameterImplementationCheck(file,
					jdtTypingProvider,
					bridge((SingleVariableDeclaration) parameterList.get(j)),
					methodBinding, j, inhMethods));

		}

		// add checks for exceptions
		List exceptionList = node.thrownExceptions();
		Name curExcNode;
		for (int i = 0; i < exceptionList.size(); i++) {

			curExcNode = (Name) exceptionList.get(i);
			ITypeBinding curExcBinding = curExcNode.resolveTypeBinding();

			if (curExcBinding == null)
				continue;

			checks.add(new MethodExceptionImplementationCheck(file,
					jdtTypingProvider, bridge(curExcNode), methodBinding,
					curExcBinding.getKey(), inhMethods));

		}

	}

	private void handleOverridingRelationshipViolation(MethodDeclaration node,
			IMethodBinding methodBinding, List<MethodPathItem> inhMethods) {

		// add check for method name
		checks.add(new InheritedMethodNameCheck(file, jdtTypingProvider,
				bridge(node), methodBinding.getName(), inhMethods));

		// add checks for exceptions
		List parameterList = node.parameters();

		// build for each parameter a list of paramters keys which are
		// overridden
		for (int j = 0; j < parameterList.size(); j++) {

			List<String> curParamList = new ArrayList<String>();
			for (MethodPathItem curItem : inhMethods) {

				if (curItem.isAbstract())
					continue;

				curParamList.add(curItem.getInheritedParamKeys().get(j));

			}

			checks.add(new InheritedMethodParameterCheck(file,
					jdtTypingProvider,
					bridge((SingleVariableDeclaration) parameterList.get(j)),
					methodBinding.getName(), methodBinding, curParamList));

		}

		// get all keys of method exceptions in super classes which are cast
		// compatible to exceptions of "node"

		// get first overridden item
		MethodPathItem superItem = CheckUtils
				.getFirstNonAbstractItem(inhMethods);

		if (superItem == null)
			return;

		// the list should contain at least one overridden exception key
		List exceptionList = node.thrownExceptions();
		Name curExcNode;
		for (int i = 0; i < exceptionList.size(); i++) {

			curExcNode = (Name) exceptionList.get(i);
			ITypeBinding curExcBinding = curExcNode.resolveTypeBinding();

			if (curExcBinding == null)
				continue;

			checks.add(new InheritedMethodExceptionCheck(file,
					jdtTypingProvider, bridge(curExcNode), curExcBinding
							.getName(), superItem.getInheritedExceptionKeys(
							methodBinding).get(curExcBinding.getKey())));

		}

	}

	/*	*//**
	 * util function
	 * 
	 * returns a super method or null of there is none
	 */
	/*
	 * private static IMethod findOverriddenMethod(IMethod method) { try { IType
	 * type = method.getDeclaringType(); ITypeHierarchy hierarchy =
	 * type.newSupertypeHierarchy(null);
	 * 
	 * while ((type = hierarchy.getSuperclass(type)) != null) { IMethod[]
	 * overriddenMethods = type.findMethods(method); if (overriddenMethods !=
	 * null && overriddenMethods.length > 0) return overriddenMethods[0]; }
	 * 
	 * return null; } catch (JavaModelException e) { return null; } }
	 */
}

class JDTCheckGenerator_FieldAccess extends JDTCheckGenerator_Base {
	public JDTCheckGenerator_FieldAccess(ColoredSourceFile file,
			JDTTypingProvider jdtTypingProvider, Set<ITypingCheck> checks) {
		super(file, jdtTypingProvider, checks);
	}

	@Override
	protected void visitName(Name node) {
		IBinding binding = node.resolveBinding();

		if (binding instanceof IVariableBinding
				&& ((IVariableBinding) binding).isField()) {

			checks.add(new FieldAccessCheck(file, jdtTypingProvider,
					bridge(node), (IVariableBinding) binding));
		}
		super.visitName(node);
	}
}

class JDTCheckGenerator_Base extends ASTVisitor {

	protected final ColoredSourceFile file;
	protected final Set<ITypingCheck> checks;
	protected final JDTTypingProvider jdtTypingProvider;

	public JDTCheckGenerator_Base(ColoredSourceFile file,
			JDTTypingProvider jdtTypingProvider, Set<ITypingCheck> checks) {
		this.file = file;
		this.jdtTypingProvider = jdtTypingProvider;
		this.checks = checks;
	}

	@Override
	public boolean visit(SimpleName node) {
		visitName(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(QualifiedName node) {
		visitName(node);
		return super.visit(node);
	}

	protected void visitName(Name node) {

	}

	protected void visitType(Type node) {

	}

	@Override
	public boolean visit(ArrayType node) {
		visitType(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(ParameterizedType node) {
		visitType(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(PrimitiveType node) {
		visitType(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(QualifiedType node) {
		visitType(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(SimpleType node) {
		visitType(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(WildcardType node) {
		visitType(node);
		return super.visit(node);
	}

	protected static IASTNode bridge(ASTNode node) {
		return ASTBridge.bridge(node);
	}
}
