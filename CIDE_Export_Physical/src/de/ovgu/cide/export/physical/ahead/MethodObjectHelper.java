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

package de.ovgu.cide.export.physical.ahead;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import de.ovgu.cide.export.physical.internal.LocalVariableHelper;
import de.ovgu.cide.export.physical.internal.RefactoringColorManager;
import de.ovgu.cide.features.IFeature;

/**
 * TODO: colors of parameters are not preserved! (just in case anybody wants to
 * implement refactoring of parameters
 * 
 * @author cKaestner
 * 
 */
public class MethodObjectHelper {

	private final static String METHODCLASS_ANNOTATION = "MethodObject";

	public static TypeDeclaration moveMethodToMethodObject(
			MethodDeclaration method, RefactoringColorManager colorManager) {
		return moveMethodToMethodObject(method, colorManager, false);
	}

	public static TypeDeclaration moveMethodToMethodObject(
			MethodDeclaration method, RefactoringColorManager colorManager,
			boolean isStatic) {
		debug_numberOfMessageObjects++;

		AST ast = method.getAST();
		assert method.getParent() instanceof TypeDeclaration;
		TypeDeclaration containingClass = (TypeDeclaration) method.getParent();

		String moName = getMethodObjectName(containingClass, method);
		// System.out.println(moName);
		boolean isSourceMethodStatic = isMethodStatic(method);
		if (isStatic && !isSourceMethodStatic)
			makeThisAccessExplicit(method);

		// create inner class and execute method
		TypeDeclaration methodObjectClass = ast.newTypeDeclaration();
		methodObjectClass.setName(ast.newSimpleName(moName));
		if (isStatic) {
			Modifier staticModifier = ast
					.newModifier(Modifier.ModifierKeyword.STATIC_KEYWORD);
			methodObjectClass.modifiers().add(staticModifier);

		}

		MethodDeclaration executeMethod = ast.newMethodDeclaration();
		executeMethod.setName(ast.newSimpleName("execute"));
		executeMethod.setReturnType2((Type) ASTNode.copySubtree(ast, method
				.getReturnType2()));
		executeMethod.thrownExceptions().addAll(
				ASTNode.copySubtrees(ast, method.thrownExceptions()));

		methodObjectClass.bodyDeclarations().add(executeMethod);
		containingClass.bodyDeclarations().add(methodObjectClass);

		createConstructor(methodObjectClass, method, isStatic
				&& !isSourceMethodStatic);

		// move body to MO
		Block oldMethodBody = method.getBody();
		Block newMethodBody = ast.newBlock();
		method.setBody(newMethodBody);

		executeMethod.setBody(oldMethodBody);

		if (isStatic && !isSourceMethodStatic)
			changeThisAccessToField(oldMethodBody);

		// call method object
		ClassInstanceCreation moConstructorCall = ast
				.newClassInstanceCreation();
		moConstructorCall.setType(ast.newSimpleType(ast.newSimpleName(moName)));
		addParamters(moConstructorCall, method, isStatic
				&& !isSourceMethodStatic);
		MethodInvocation moCall = ast.newMethodInvocation();
		moCall.setExpression(moConstructorCall);
		moCall.setName(ast.newSimpleName("execute"));
		if (RefactoringUtils.isVoid(method.getReturnType2())) {
			newMethodBody.statements().add(ast.newExpressionStatement(moCall));
		} else {
			ReturnStatement returnStmt = ast.newReturnStatement();
			returnStmt.setExpression(moCall);
			newMethodBody.statements().add(returnStmt);
		}

		// adjustColors
		colorManager.setColors(methodObjectClass, colorManager
				.getOwnColors(method));

		moveLocalVariableDeclarationsToFields(executeMethod, methodObjectClass,
				colorManager);

		addMethodObjectAnnotation(methodObjectClass);

		return methodObjectClass;
	}

	private static boolean isMethodStatic(MethodDeclaration method) {
		for (Object m : method.modifiers()) {
			if (m instanceof Modifier) {
				if (((Modifier) m).getKeyword() == Modifier.ModifierKeyword.STATIC_KEYWORD)
					return true;
			}
		}
		return false;
	}

	private static void changeThisAccessToField(Block oldMethodBody) {
		final AST ast = oldMethodBody.getAST();
		oldMethodBody.accept(new ASTVisitor() {
			@Override
			public void endVisit(ThisExpression node) {
				assert node.getQualifier() == null;
				Expression _thisExpr = ast.newSimpleName("_this");
				RefactoringUtils.replaceASTNode(node, _thisExpr);
				super.endVisit(node);
			}
		});
	}

	/**
	 * creates a constructor with the parameter of the methods arguments. all
	 * parameters are assigned to fields with the same name that are created as
	 * well
	 * 
	 * @param methodObjectClass
	 * @param method
	 * @param isStatic
	 */
	private static void createConstructor(TypeDeclaration moClass,
			MethodDeclaration method, boolean isStatic) {
		if (method.parameters().isEmpty() && !isStatic)
			return;
		AST ast = moClass.getAST();

		MethodDeclaration constructorDecl = ast.newMethodDeclaration();
		constructorDecl.setConstructor(true);
		constructorDecl.setName(ast.newSimpleName(moClass.getName()
				.getIdentifier()));
		constructorDecl.setBody(ast.newBlock());

		List<SingleVariableDeclaration> parameterList = new ArrayList<SingleVariableDeclaration>(
				method.parameters());
		if (isStatic) {
			SingleVariableDeclaration _thisVar = ast
					.newSingleVariableDeclaration();
			_thisVar.setName(ast.newSimpleName("_this"));
			TypeDeclaration mainClass = RefactoringUtils
					.getContainingType(method);
			_thisVar.setType(ast.newSimpleType(ast.newSimpleName(mainClass
					.getName().getIdentifier())));
			parameterList.add(0, _thisVar);
		}

		for (SingleVariableDeclaration p : parameterList) {
			String varName = p.getName().getIdentifier();
			// add parameter to constructor
			constructorDecl.parameters().add(ASTNode.copySubtree(ast, p));
			// create field
			addField(moClass, p.getType(), varName, null);

			FieldAccess fieldAccess = ast.newFieldAccess();
			fieldAccess.setExpression(ast.newThisExpression());
			fieldAccess.setName(ast.newSimpleName(varName));
			Assignment assignment = ast.newAssignment();
			assignment.setLeftHandSide(fieldAccess);
			assignment.setRightHandSide(ast.newSimpleName(varName));
			constructorDecl.getBody().statements().add(
					ast.newExpressionStatement(assignment));
		}
		moClass.bodyDeclarations().add(0, constructorDecl);
	}

	private static void addParamters(ClassInstanceCreation call,
			MethodDeclaration method, boolean isStatic) {
		if (isStatic)
			call.arguments().add(call.getAST().newThisExpression());
		for (SingleVariableDeclaration p : (List<SingleVariableDeclaration>) method
				.parameters()) {
			// call.arguments()
			String varName = p.getName().getIdentifier();
			call.arguments().add(call.getAST().newSimpleName(varName));
		}
	}

	private static void moveLocalVariableDeclarationsToFields(
			final MethodDeclaration executeMethod,
			final TypeDeclaration methodObjectClass,
			final RefactoringColorManager colorManager) {

		final AST ast = executeMethod.getAST();
		final Set<String> localVariableNames = new HashSet<String>();
		executeMethod.accept(new ASTVisitor() {
			@Override
			public boolean visit(VariableDeclarationExpression node) {
				assert colorManager.getColors(node).equals(
						colorManager.getColors(executeMethod)) : "Colored VariableDeclarationExpression not handled yet";
				return super.visit(node);
			}

			@Override
			public void endVisit(VariableDeclarationStatement node) {
				assert node.getLocationInParent().isChildListProperty();
				Set<IFeature> colors = colorManager.getColors(node);
				Block replacementBlock = ast.newBlock();
				Iterator<VariableDeclarationFragment> fragmentIterator = node
						.fragments().iterator();
				while (fragmentIterator.hasNext()) {
					VariableDeclarationFragment fragment = fragmentIterator
							.next();
					checkDuplicate(fragment);
					addField(methodObjectClass, node.getType(), fragment
							.getName().getIdentifier(), colors);
					Expression init = fragment.getInitializer();
					if (init == null)
						fragmentIterator.remove();
					else {
						fragment.setInitializer(null);
						Assignment assignment = ast.newAssignment();
						assignment.setLeftHandSide(ast.newSimpleName(fragment
								.getName().getIdentifier()));
						assignment.setRightHandSide(init);
						ExpressionStatement assignExpr = ast
								.newExpressionStatement(assignment);
						replacementBlock.statements().add(assignExpr);
						colorManager.addColors(assignExpr, colorManager
								.getOwnColors(fragment));
						colorManager.addColors(assignExpr, colorManager
								.getOwnColors(node));
					}
				}
				Statement replacement = replacementBlock;
				if (replacementBlock.statements().size() == 1) {
					replacement = (Statement) replacementBlock.statements()
							.get(0);
					replacementBlock.statements().clear();
				}
				RefactoringUtils.replaceASTNode(node, replacement);

				/*
				 * remove reference from LocalVariableHelper, because this now
				 * no longer references a local variable, but a field.
				 */
			}

			private void checkDuplicate(VariableDeclarationFragment fragment) {
				String variableName = fragment.getName().getIdentifier();
				assert !localVariableNames.contains(variableName) : "Duplicate local variable name not supported";
				localVariableNames.add(variableName);
			}

			@Override
			public boolean visit(SimpleName node) {
				return visitName(node);
			}

			@Override
			public boolean visit(QualifiedName node) {
				return visitName(node);
			}

			private boolean visitName(Name name) {
				LocalVariableHelper.removeName(name);
				return true;
			}
		});

	}

	/**
	 * 
	 * @param methodObjectClass
	 * @param type
	 *            original ASTNode is sufficient (is copied internally)
	 * @param name
	 * @param colors
	 *            null if not used
	 */
	private static void addField(TypeDeclaration methodObjectClass, Type type,
			String name, Set<IFeature> colors) {
		AST ast = type.getAST();
		VariableDeclarationFragment fragment = ast
				.newVariableDeclarationFragment();
		fragment.setName(ast.newSimpleName(name));
		FieldDeclaration field = ast.newFieldDeclaration(fragment);
		field.setType((Type) ASTNode.copySubtree(ast, type));
		Modifier modifier = ast
				.newModifier(Modifier.ModifierKeyword.PROTECTED_KEYWORD);
		field.modifiers().add(modifier);
		methodObjectClass.bodyDeclarations().add(field);
	}

	static HashMap<String, Integer> knownMethodObjectNames = new HashMap<String, Integer>();
	public static int debug_numberOfMessageObjects = 0;

	private static String getMethodObjectName(TypeDeclaration aClass,
			MethodDeclaration method) {

		String name = aClass.getName().getIdentifier() + "_"
				+ method.getName().getIdentifier();

		Integer known = knownMethodObjectNames.get(name);
		if (known == null)
			knownMethodObjectNames.put(name, new Integer(1));
		else {
			known = new Integer(known.intValue() + 1);
			knownMethodObjectNames.put(name, known);
			name += known;
		}

		return name;
	}

	public static boolean isMethodObjectClass(TypeDeclaration methodClass) {
		for (IExtendedModifier modifier : (List<IExtendedModifier>) methodClass
				.modifiers()) {
			if (modifier.isAnnotation()) {
				String annotationName = ((Annotation) modifier).getTypeName()
						.toString();
				if (METHODCLASS_ANNOTATION.equals(annotationName))
					return true;
			}
		}

		return false;
	}

	private static void addMethodObjectAnnotation(
			TypeDeclaration methodObjectClass) {

		AST ast = methodObjectClass.getAST();
		MarkerAnnotation annotation = ast.newMarkerAnnotation();
		annotation.setTypeName(ast.newSimpleName(METHODCLASS_ANNOTATION));

		methodObjectClass.modifiers().add(0, annotation);

	}

	public static void stripMethodObjectAnnotation(TypeDeclaration methodClass) {
		stripMethodObjectAnnotation(methodClass.modifiers());
	}

	public static void stripMethodObjectAnnotation(
			List<IExtendedModifier> modifiers) {
		Iterator<IExtendedModifier> modifierIterator = modifiers.iterator();
		while (modifierIterator.hasNext()) {
			IExtendedModifier modifier = modifierIterator.next();
			if (modifier.isAnnotation()) {
				String annotationName = ((Annotation) modifier).getTypeName()
						.toString();
				if (METHODCLASS_ANNOTATION.equals(annotationName))
					modifierIterator.remove();
			}
		}
	}

	/**
	 * replaces all implicit access to "this" with explicit access (i.e. writes
	 * a this. before every method invocation or field access where possible)
	 * 
	 * 
	 * @param targetMethod
	 * @param colorManager
	 */
	public static void makeThisAccessExplicit(
			final MethodDeclaration targetMethod) {
		final AST ast = targetMethod.getAST();
		final TypeDeclaration thisClass = RefactoringUtils
				.getContainingType(targetMethod);
		targetMethod.accept(new ASTVisitor() {
			@Override
			public boolean visit(MethodInvocation node) {
				if (node.getExpression() == null)
					node.setExpression(ast.newThisExpression());
				return super.visit(node);
			}

			@Override
			public boolean visit(FieldAccess node) {
				if (node.getExpression() == null)
					node.setExpression(ast.newThisExpression());
				return super.visit(node);
			}

			@Override
			public void endVisit(SimpleName node) {
				if (node.getLocationInParent() == FieldAccess.NAME_PROPERTY)
					return;
				if (node.getLocationInParent() == QualifiedName.NAME_PROPERTY)
					return;

				IBinding binding = ((Name) node).resolveBinding();
				if (binding instanceof IVariableBinding) {
					IVariableBinding vBinding = (IVariableBinding) binding;
					if (vBinding.isField()) {
						ITypeBinding bc = vBinding.getDeclaringClass();
						if (thisClass.resolveBinding() == bc) {
							/*
							 * workaround if a field access is represented by a
							 * qualified name (this qualified name must be first
							 * replaced by a field access
							 */
							if (node.getLocationInParent() == QualifiedName.QUALIFIER_PROPERTY) {
								QualifiedName parent = (QualifiedName) node
										.getParent();
								parent.setQualifier(ast
										.newSimpleName("notUsed"));
								FieldAccess fieldAccess = ast.newFieldAccess();
								RefactoringUtils.replaceASTNode(parent,
										fieldAccess);
								fieldAccess.setExpression(node);
								fieldAccess.setName(ast.newSimpleName(parent
										.getName().getIdentifier()));
							}

							FieldAccess fieldAccess = ast.newFieldAccess();
							RefactoringUtils.replaceASTNode(node, fieldAccess);
							fieldAccess.setExpression(ast.newThisExpression());
							fieldAccess.setName(node);
						}

					}
				}

			}
		});
	}

	static class MethodObjectCreator extends ASTVisitor {

		private RefactoringColorManager colorManager;
		private Set<IFeature> methodColors;
		private boolean foundColorVariableDeclaration;
		private boolean inMethodObject;
		private boolean isStatic;

		MethodObjectCreator(RefactoringColorManager colorManager, boolean isStatic) {
			this.colorManager = colorManager;
			this.isStatic = isStatic;
		}

		@Override
		public boolean visit(MethodDeclaration node) {
			methodColors = colorManager.getColors(node);
			foundColorVariableDeclaration = false;
			inMethodObject = (node.getParent() instanceof TypeDeclaration && isMethodObjectClass((TypeDeclaration) node
					.getParent()));
			if (inMethodObject)
				return false;

			return super.visit(node);
		}

		@Override
		public boolean visit(VariableDeclarationFragment node) {
			if (!colorManager.getColors(node).equals(methodColors)) {
				foundColorVariableDeclaration = true;
				// if (inMethodObject)
				// System.out.println("problem");
			}
			return super.visit(node);
		}

		@Override
		public void endVisit(MethodDeclaration node) {

			if (foundColorVariableDeclaration) {
				assert node.getParent() instanceof TypeDeclaration
						&& !isMethodObjectClass((TypeDeclaration) node
								.getParent());
				moveMethodToMethodObject(node, colorManager, isStatic);
			}
			super.endVisit(node);
		}
	}

	public static void stripStaticModifier(List<IExtendedModifier> modifiers) {
		Iterator<IExtendedModifier> modifierIterator = modifiers.iterator();
		while (modifierIterator.hasNext()) {
			IExtendedModifier modifier = modifierIterator.next();
			if (modifier.isModifier()) {
				if (((Modifier) modifier).isStatic())
					modifierIterator.remove();
			}
		}
	}

}
