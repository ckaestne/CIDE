package de.ovgu.cide.typing.jdt;

import java.util.HashMap;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
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
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.WildcardType;

import cide.gast.IASTNode;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.jdt.ASTBridge;
import de.ovgu.cide.typing.jdt.checks.FieldAccessCheck;
import de.ovgu.cide.typing.jdt.checks.ImportTargetCheck;
import de.ovgu.cide.typing.jdt.checks.LocalVariableReferenceCheck;
import de.ovgu.cide.typing.jdt.checks.MethodInvocationCheck;
import de.ovgu.cide.typing.jdt.checks.TypeImportedCheck;
import de.ovgu.cide.typing.jdt.checks.TypeReferenceCheck;
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
 * @author cKaestner
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
