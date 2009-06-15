package de.ovgu.cide.export.physical.aspectj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import de.ovgu.cide.export.physical.ahead.BaseFeatureRefactorer;
import de.ovgu.cide.export.physical.ahead.RefactoringUtils;
import de.ovgu.cide.export.physical.internal.LocalVariableAnalyzer;
import de.ovgu.cide.export.physical.internal.RefactoringColorManager;
import de.ovgu.cide.features.IFeature;

public class AspectJFeatureRefactorer extends BaseFeatureRefactorer {

	Set<String> seenMembers;

	private HashMap<Set<IFeature>, AspectJCompilationUnit> allAspects;

	private AspectJCompilationUnit targetCompUnit;

	private TypeDeclaration mainType;

	public AspectJFeatureRefactorer(Set<IFeature> derivative, String layer,
			HashMap<Set<IFeature>, AspectJCompilationUnit> allAspects,
			RefactoringColorManager colorManager) {
		super(derivative, colorManager);
		// this.targetCompUnit = targetCompUnit;
		this.allAspects = allAspects;
		// targetCompUnit.setLayer(layer);
		seenMembers = new HashSet();
	}

	public boolean visit(CompilationUnit node) {
		if (node.types().size() > 0) {
			mainType = (TypeDeclaration) node.types().get(0);
			targetCompUnit = allAspects.get(derivative);
			// .setName(mainType.getName());
		}
		return true;
	}

	/**
	 * refactoring whole methods is easy.
	 */
	public boolean visit(TypeDeclaration type) {
		for (MethodDeclaration method : type.getMethods()) {
			if (derivative.equals(colorManager.getColors(method)))
				refactorMethod(type, method);
		}

		for (FieldDeclaration field : type.getFields()) {
			if (derivative.equals(colorManager.getColors(field))) {
				refactorField(type, field);
			}
		}

		return true;
	}

	private void refactorField(TypeDeclaration type, FieldDeclaration field) {

		VariableDeclarationFragment fieldName = (VariableDeclarationFragment) field
				.fragments().get(0);
		seenMembers.add(fieldName.resolveBinding().getKey());
		String oldName = fieldName.getName().toString();

		fieldName.setName(type.getAST().newSimpleName(
				type.getName() + "_dot_" + oldName)); // field.fragments().store.elementData[0].variableName.identifier

		targetCompUnit.addIntroduction(type, field);
		type.bodyDeclarations().remove(field);
	}

	/**
	 * refactoring the first and last statements per method
	 */
	public boolean visit(MethodDeclaration node) {
		Block body = node.getBody();
		if (body == null)
			return false;
		List<Statement> statements = body.statements();
		List<Statement> refactorFirst = new ArrayList<Statement>();
		List<Statement> refactorLast = new ArrayList<Statement>();

		boolean ignoreLast = false;
		if (statements.size() > 0) {
			Statement lastStmt = statements.get(statements.size() - 1);
			if (RefactoringUtils.isSimpleReturnStmt(lastStmt))
				ignoreLast = true;
		}

		int forwardIdx = 0;
		while (forwardIdx < statements.size()
				&& derivative.equals(colorManager.getColors(statements
						.get(forwardIdx)))) {
			Statement stmtToExtract = statements.get(forwardIdx);
			stmtToExtract.accept(new ResolveMemberVisitor(
					(TypeDeclaration) node.getParent()));
			refactorFirst.add(stmtToExtract);
			forwardIdx++;
		}

		int backwardIdx = statements.size() - (ignoreLast ? 1 : 0) - 1;
		while (backwardIdx >= 0
				&& backwardIdx > forwardIdx
				&& derivative.equals(colorManager.getColors(statements
						.get(backwardIdx)))) {
			Statement stmtToExtract = statements.get(backwardIdx);
			stmtToExtract.accept(new ResolveMemberVisitor(
					(TypeDeclaration) node.getParent()));
			refactorLast.add(0, stmtToExtract);
			backwardIdx--;
		}

		boolean canRefactor = refactorFirst.size() > 0
				|| refactorLast.size() > 0;
		// check that there are no variables defined (otherwise just do not
		// refactor this, but wait for hook methods)
		if (canRefactor && refactorLast.size() > 0) {
			LocalVariableAnalyzer lva = new LocalVariableAnalyzer(node,
					refactorLast, refactorFirst, colorManager);
			lva.setIgnoreMethodParameters(true);
			lva.execute();
			if (lva.getParameters().size() > 0)
				canRefactor = false;
		}
		// cannot refactor first statements in constructor, only the end
		if (canRefactor && node.isConstructor() && refactorFirst.size() > 0)
			canRefactor = false;

		if (canRefactor) {
			statements.removeAll(refactorFirst);
			statements.removeAll(refactorLast);
			extractAdvice(node, refactorFirst, refactorLast);
		}

		return super.visit(node);
	}

	private void extractAdvice(MethodDeclaration node,
			List<Statement> refactorFirst, List<Statement> refactorLast) {
		AspectJExecutionAdvice advice = new AspectJExecutionAdvice(node
				.getAST().newBlock(), (TypeDeclaration) node.getParent(), node
				.parameters(), node.getReturnType2(), node.getName());
		AST ast = node.getAST();
		// MethodDeclaration copiedMethod = (MethodDeclaration)
		// ASTNode.copySubtree(ast, node);
		Statement proceedCall = createProceedCall(node, ast);

		List<Statement> targetBody = advice.getAdviceBody().statements();
		targetBody.clear();
		targetBody.addAll(refactorFirst);
		if (proceedCall != null) // if it null, we advice a constructor with
			// after-advice
			targetBody.add(proceedCall);
		targetBody.addAll(refactorLast);

		if (!RefactoringUtils.isVoid(node.getReturnType2())) {
			targetBody.add(createReturnStatement(ast));
			if (refactorLast.size() == 0)
				combineReturn(targetBody);
		}

		// targetCompUnit.getRefinement().addRefinement(copiedMethod);

		targetCompUnit.getAdviceList().add(advice);
	}

	private class ResolveMemberVisitor extends ASTVisitor {
		FieldDeclaration[] allMembers;

		// CompilationUnitColorManager colormanager;
		// Set<IFeature> derivative;
		TypeDeclaration type;

		// public ResolveMemberVisitor(TypeDeclaration type,
		// CompilationUnitColorManager colormanager, Set<IFeature> derivative) {
		public ResolveMemberVisitor(TypeDeclaration type) {
			// this.derivative = derivative;
			allMembers = type.getFields();
			this.type = type;
		}

		public boolean visit(SimpleName node) {
			IBinding binding = node.resolveBinding();
			String key = null;
			if (binding != null)
				key = binding.getKey();

			Boolean insideClass = false;
			for (FieldDeclaration field : type.getFields()) {
				VariableDeclarationFragment fieldName = (VariableDeclarationFragment) field
						.fragments().get(0);
				if (fieldName.resolveBinding().getKey().equals(key)) {
					insideClass = true;
				}
			}

			// in advice a classmember is referred
			if ((seenMembers.contains(key)) || (insideClass)) {
				SimpleName newName = node.getAST().newSimpleName(
						"this__dot_" + node.toString());
				StructuralPropertyDescriptor location = node
						.getLocationInParent();
				// node.getParent().setProperty(id, newName);
				if (location.isChildListProperty()) { // instanceof
					// ChildListPropertyDescriptor)
					// ChildListPropertyDescriptor loc1 =
					// (ChildListPropertyDescriptor) location;
					List operands = (List) node.getParent()
							.getStructuralProperty(location);
					int idx = operands.indexOf(node);
					operands.add(idx, newName);
					operands.remove(idx + 1);
				} else {
					node.getParent().setStructuralProperty(location, newName);
				}
			}
			return true;
		}

		public boolean visit(MethodInvocation method) {
			for (MethodDeclaration classMethod : type.getMethods()) {
				SimpleName classMethodName = classMethod.getName();
				IBinding classMethodBinding = null;
				String key = null;
				if (classMethodName != null)
					classMethodBinding = classMethodName.resolveBinding();
				if (classMethodBinding != null)
					key = classMethodBinding.getKey();

				IBinding binding = method.getName().resolveBinding();
				String myKey = "";
				if (binding != null)
					myKey = binding.getKey();
				if (myKey.equals(key) || seenMembers.contains(myKey)) {
					method.setName(method.getAST().newSimpleName(
							"this__dot_" + method.getName().toString()));
					return true;
				}

			}
			return true;
		}
	}

	// @Override
	// public void preVisit(ASTNode node) {
	// if (node instanceof Statement)
	// visitStatement((Statement) node);
	// super.preVisit(node);
	// }

	/**
	 * refactore all statements in the middle of a method by introducing hooks
	 * 
	 * @param block
	 */
	public boolean visit(Block block) {
		List<Statement> statements = block.statements();
		int idx = statements.size() - 1;

		List<Statement> statementsToReplace = new ArrayList<Statement>();
		while (idx >= 0) {
			Statement stmt = statements.get(idx);
			boolean isTargetStmt = derivative.equals(colorManager
					.getColors(stmt));
			if (isTargetStmt) {
				Statement stmnt = statements.get(idx);
				stmnt.accept(this);
				stmnt.accept(new ResolveMemberVisitor(
						(TypeDeclaration) RefactoringUtils.getCompilationUnit(
								block).types().get(0)));
				statementsToReplace.add(0, stmnt);
			}

			if ((idx == 0 || !isTargetStmt) && statementsToReplace.size() > 0) {
				AjHookMethodHelper hook = new AjHookMethodHelper(
						statementsToReplace, RefactoringUtils
								.getMethodDeclaration(block), RefactoringUtils
								.getCompilationUnit(block), colorManager);
				statements.removeAll(statementsToReplace);
				Statement hookCall = hook.createHookCall();
				statements.add((isTargetStmt ? idx : idx + 1), hookCall);
				mainType.bodyDeclarations().add(hook.getHookDeclaration());
				targetCompUnit.getAdviceList().add(hook.getAdvice());
				statementsToReplace = new ArrayList<Statement>();

				// color hook method with the same color as the call (if the
				// call inherits any colors)
				Set<IFeature> hookColors = colorManager.getInheritedColors(hook
						.createHookCall());
				if (hookColors.size() > 0)
					colorManager.setColors(hook.getHookDeclaration(),
							hookColors);
			}
			idx--;
		}
		assert statementsToReplace.size() == 0;

		return super.visit(block);
	}

	private Statement createProceedCall(MethodDeclaration currentMethod, AST ast) {
		if (currentMethod.getReturnType2() == null)
			return null;

		Statement proceedCall;
		/*
		 * if (currentMethod.isConstructor()) { proceedCall =
		 * ast.newproceedConstructorInvocation(); for (SingleVariableDeclaration
		 * param : (List<SingleVariableDeclaration>) currentMethod
		 * .parameters()) { ((proceedConstructorInvocation)
		 * proceedCall).arguments().add(
		 * ast.newSimpleName(param.getName().getIdentifier())); } } else {
		 */
		MethodInvocation proceedCallExpr = ast.newMethodInvocation();
		proceedCallExpr.setName(ast.newSimpleName("proceed"));
		for (SingleVariableDeclaration param : (List<SingleVariableDeclaration>) currentMethod
				.parameters()) {
			proceedCallExpr.arguments().add(
					ast.newSimpleName(param.getName().getIdentifier()));
		}
		proceedCallExpr.arguments().add(ast.newSimpleName("this_"));
		if (RefactoringUtils.isVoid(currentMethod.getReturnType2())) {
			proceedCall = ast.newExpressionStatement(proceedCallExpr);
		} else {
			VariableDeclarationFragment returnVariable = ast
					.newVariableDeclarationFragment();
			returnVariable.setName(ast.newSimpleName("result"));
			VariableDeclarationStatement variableDecl = ast
					.newVariableDeclarationStatement(returnVariable);
			variableDecl.setType((Type) ASTNode.copySubtree(currentMethod
					.getAST(), currentMethod.getReturnType2()));
			returnVariable.setInitializer(proceedCallExpr);
			proceedCall = variableDecl;
		}
		// }
		return proceedCall;
	}

	// Methods into ITD
	private void refactorMethod(TypeDeclaration type, MethodDeclaration method) {
		// imports
		CompilationUnit classFile = RefactoringUtils.getCompilationUnit(method);
		targetCompUnit.imports.addAll(classFile.imports());
		IBinding binding = method.getName().resolveBinding();
		if ((binding != null) && (binding.getKey() != null))
			seenMembers.add(binding.getKey());
		type.bodyDeclarations().remove(method);

		AST node = method.getName().getAST();
		String oldName = method.getName().toString();
		SimpleName newName = node.newSimpleName(type.getName() + "_dot_"
				+ oldName);
		method.setName(newName);
		int m = method.getModifiers();
		if (Modifier.isPrivate(m) || Modifier.isProtected(m))
			System.out.println("make "
					+ method.toString().replace("_dot_", ".") + " public!");

		targetCompUnit.addIntroduction(type, method);
	}

}
