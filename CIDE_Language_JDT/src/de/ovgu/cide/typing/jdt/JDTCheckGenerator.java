/**
    Copyright 2010 Christian Kästner

    This file is part of CIDE.

    CIDE is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.

    CIDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CIDE.  If not, see <http://www.gnu.org/licenses/>.

    See http://www.fosd.de/cide/ for further information.
*/

package de.ovgu.cide.typing.jdt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.WildcardType;

import cide.gast.IASTNode;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.jdt.ASTBridge;
import de.ovgu.cide.typing.jdt.checks.FieldAccessCheck;
import de.ovgu.cide.typing.jdt.checks.FinalFieldAssignmentCheck;
import de.ovgu.cide.typing.jdt.checks.ImportTargetCheck;
import de.ovgu.cide.typing.jdt.checks.InheritedMethodExceptionCheck;
import de.ovgu.cide.typing.jdt.checks.InheritedMethodCheck;
import de.ovgu.cide.typing.jdt.checks.LocalVariableReferenceCheck;
import de.ovgu.cide.typing.jdt.checks.MethodImplementationExceptionCheck;
import de.ovgu.cide.typing.jdt.checks.MethodImplementationNameCheck;
import de.ovgu.cide.typing.jdt.checks.MethodImplementationParameterCheck;
import de.ovgu.cide.typing.jdt.checks.MethodInvocationCheck;
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
 * TODO: several other checks from ASE paper maybe missing
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
		List args = node.arguments();
		handleMethodCall(node, binding, args);

		return super.visit(node);
	}

	@Override
	public boolean visit(ConstructorInvocation node) {
		IMethodBinding binding = node.resolveConstructorBinding();
		List args = node.arguments();
		handleMethodCall(node, binding, args);
		return super.visit(node);
	}

	@Override
	public boolean visit(ClassInstanceCreation node) {
		IMethodBinding binding = node.resolveConstructorBinding();
		List args = node.arguments();
		handleMethodCall(node, binding, args);
		return super.visit(node);
	}

	@Override
	public boolean visit(SuperConstructorInvocation node) {
		IMethodBinding binding = node.resolveConstructorBinding();
		List args = node.arguments();
		handleMethodCall(node, binding, args);
		return super.visit(node);
	}

	@Override
	public boolean visit(SuperMethodInvocation node) {
		IMethodBinding binding = node.resolveMethodBinding();
		List args = node.arguments();
		handleMethodCall(node, binding, args);
		return super.visit(node);
	}

	private void handleMethodCall(ASTNode node, IMethodBinding methodBinding,
			List arguments) {
		if (methodBinding == null)
			return;

		// name check
		checks.add(new MethodInvocationCheck(file, jdtTypingProvider,
				bridge(node), CheckUtils.getIASTNodeList(arguments),
				methodBinding));

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
		// currently deactivated, see Bug #74
		// // add check for method name
		// checks.add(new MethodImplementationNameCheck(file, jdtTypingProvider,
		// bridge(node), methodBinding, inhMethods));
		//
		// //add checks for parameters
		// List parameterList = node.parameters();
		//
		// for (int j = 0; j < parameterList.size(); j++) {
		//
		// checks.add(new MethodImplementationParameterCheck(file,
		// jdtTypingProvider,
		// bridge((SingleVariableDeclaration) parameterList.get(j)),
		// methodBinding, j, inhMethods));
		//
		// }
		//
		// // add checks for exceptions
		// List exceptionList = node.thrownExceptions();
		// Name curExcNode;
		// for (int i = 0; i < exceptionList.size(); i++) {
		//
		// curExcNode = (Name) exceptionList.get(i);
		// ITypeBinding curExcBinding = curExcNode.resolveTypeBinding();
		//
		// if (curExcBinding == null)
		// continue;
		//
		// checks.add(new MethodImplementationExceptionCheck(file,
		// jdtTypingProvider, bridge(curExcNode), methodBinding,
		// curExcBinding.getKey(), inhMethods));
		//
		// }

	}

	private void handleOverridingRelationshipViolation(MethodDeclaration node,
			IMethodBinding methodBinding, List<MethodPathItem> inhMethods) {

		// get first overridden item
		MethodPathItem superItem = CheckUtils
				.getFirstNonAbstractItem(inhMethods);

		if (superItem == null)
			return;

		// add check for method name and params
		checks.add(new InheritedMethodCheck(file, jdtTypingProvider,
				bridge(node), CheckUtils.getIASTNodeList(node.parameters()),
				methodBinding.getName(), superItem));

		// TODO does not work correctly (requires context like parameters). fix
		// later, temporarily deactived
		// // get all keys of method exceptions in super classes which are cast
		// // compatible to exceptions of "node"
		// // the list should contain at least one overridden exception key
		// List exceptionList = node.thrownExceptions();
		// Name curExcNode;
		// for (int i = 0; i < exceptionList.size(); i++) {
		//
		// curExcNode = (Name) exceptionList.get(i);
		// ITypeBinding curExcBinding = curExcNode.resolveTypeBinding();
		//
		// if (curExcBinding == null)
		// continue;
		//
		// checks.add(new InheritedMethodExceptionCheck(file,
		// jdtTypingProvider, bridge(curExcNode), curExcBinding
		// .getName(), superItem.getInheritedExceptionKeys(
		// methodBinding).get(curExcBinding.getKey())));
		//
		// }

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

		if (binding != null && binding instanceof IVariableBinding
				&& ((IVariableBinding) binding).isField()) {
			handleFieldChecks(node, (IVariableBinding) binding);
		}

		super.visitName(node);
	}

	private void handleFieldChecks(Name fieldRef, IVariableBinding fieldBinding) {

		// add field access check
		checks.add(new FieldAccessCheck(file, jdtTypingProvider,
				bridge(fieldRef), fieldBinding));

		// @ckaestne: versteh ich nicht, see Bug #74, leads to misleading bug
		// reports
		// //add assignment check for final fields
		// if (Modifier.isFinal((fieldBinding).getModifiers())) {
		//			
		// ITypeBinding declClass = fieldBinding.getDeclaringClass();
		//			
		// if (declClass != null && declClass.isFromSource())
		// checks.add(new FinalFieldAssignmentCheck(file, jdtTypingProvider,
		// bridge(fieldRef), fieldBinding));
		//			
		// }

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
