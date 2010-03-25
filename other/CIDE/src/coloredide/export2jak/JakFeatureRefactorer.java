package coloredide.export2jak;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import coloredide.export.Formal;
import coloredide.export.LocalVariableHelper;
import coloredide.export2jak.ast.JakClassRefinement;
import coloredide.export2jak.ast.JakCompilationUnit;
import coloredide.features.Feature;
import coloredide.features.source.IColorManager;

/**
 * starts on a TypeDeclaration node on the type to refactor. (parent is
 * compilation unit)
 * 
 * note, this does not use method objects. if method objects should be used they
 * must be created prior to applying this visitor
 * 
 * @author cKaestner
 * 
 */
public class JakFeatureRefactorer extends BaseFeatureRefactorer {

	private JakCompilationUnit targetCompUnit;

	protected Stack<TypeDeclaration> mainType = new Stack<TypeDeclaration>();

	private Set<MethodDeclaration> ignoredMethodsForAroundAdvice = new HashSet<MethodDeclaration>();

	public JakFeatureRefactorer(Set<Feature> derivative,
			JakCompilationUnit targetCompUnit, IColorManager colorManager) {
		super(derivative, colorManager);
		this.targetCompUnit = targetCompUnit;
	}

	@Override
	public void endVisit(TypeDeclaration node) {
		mainType.pop();
		super.endVisit(node);
	}

	/**
	 * refactoring whole methods is easy.
	 */
	public boolean visit(TypeDeclaration type) {
		mainType.push(type);

		for (MethodDeclaration method : type.getMethods()) {
			if (derivative.equals(colorManager.getColors(method)))

				refactorMethod(type, method);

		}
		for (FieldDeclaration field : type.getFields()) {
			if (derivative.equals(colorManager.getColors(field)))

				refactorField(type, field);

		}
		List<BodyDeclaration> bodyDeclarations = type.bodyDeclarations();
		for (int idx = bodyDeclarations.size() - 1; idx >= 0; idx--) {
			if (derivative.equals(colorManager.getColors(bodyDeclarations
					.get(idx))))

				refactorOtherMember(type, bodyDeclarations.get(idx));

		}

		Type superType = type.getSuperclassType();
		if (superType != null
				&& derivative.equals(colorManager.getColors(superType)))
			refactorSuperType(type, superType);

		List<Type> interfaces = new ArrayList<Type>(type.superInterfaceTypes());
		for (Type implementsType : interfaces) {
			if (derivative.equals(colorManager.getColors(implementsType)))
				refactorImplementsType(type, implementsType);
		}

		return true;
	}

	private void refactorImplementsType(TypeDeclaration type,
			Type implementsType) {
		type.superInterfaceTypes().remove(implementsType);

		targetCompUnit.getRefinement().addSuperInterfaceType(implementsType);

	}

	private void refactorSuperType(TypeDeclaration type, Type superType) {
		type.setSuperclassType(null);

		targetCompUnit.getRefinement().setSuperclassType(superType);

	}

	private void refactorOtherMember(TypeDeclaration type,
			BodyDeclaration membertype) {
		type.bodyDeclarations().remove(membertype);

		targetCompUnit.getRefinement().addOtherMember(membertype);
	}

	private void refactorField(TypeDeclaration type, FieldDeclaration field) {
		type.bodyDeclarations().remove(field);

		targetCompUnit.getRefinement().addFieldIntroductionForType(type, field);
	}

	public boolean visit(MethodDeclaration node) {
		if (!ignoredMethodsForAroundAdvice.contains(node))
			if (node.getBody() != null) {

				boolean success = node.getBody().statements().isEmpty();
				if (!success)
					success = refactorAllStatements(node);
				if (!success)
					refactorAroundAdvice(node);
			}
		return super.visit(node);
	}

	private boolean refactorAllStatements(MethodDeclaration method) {
		if (!RefactoringUtils.canRefactorAllStatements(method, colorManager,
				derivative))
			return false;

		AST ast = method.getAST();
		MethodDeclaration refinement = (MethodDeclaration) ASTNode.copySubtree(
				ast, method);

		List<Statement> baseBody = method.getBody().statements();
		List<Statement> refinementBody = refinement.getBody().statements();

		Statement exception = RefactoringUtils.findSubtreeRuleException(
				baseBody, colorManager, derivative);

		// if an exception to the subtree rule was done
		if (exception != null) {
			// move this back into the original method
			// place a Super call in the old position
			Statement superCall = createSuperCall(method, ast, false, null);
			replaceStatement(exception, superCall);

		}

		List<Statement> baseStatements = new ArrayList(baseBody);
		baseBody.clear();
		refinementBody.clear();
		// create a refinement
		// move all statements to a refinement
		refinementBody.addAll(baseStatements);

		if (exception != null) {
			RefactoringUtils.addStatementOrBlockContent(exception, baseBody);
		} else {
			// otherwise add a Super call to the end
			Statement superCall = createSuperCall(method, ast, true, null);
			if (superCall != null)
				refinementBody.add(superCall);
		}

		targetCompUnit.getRefinement().addRefinementForMethod(method,
				refinement);

		return true;
	}

	static void replaceStatement(Statement target, Statement replacement) {
		ASTNode p = target.getParent();

		if (target instanceof Block && !(replacement instanceof Block)) {
			Block b = replacement.getAST().newBlock();
			b.statements().add(replacement);
			replacement = b;
		}

		StructuralPropertyDescriptor prop = target.getLocationInParent();
		if (prop.isSimpleProperty() || prop.isChildProperty()) {
			p.setStructuralProperty(prop, replacement);
		} else if (prop.isChildListProperty()) {
			assert false;
		}

	}

	/**
	 * refactoring the first and last statements per method
	 * 
	 * conditions: * first statements do not expose variables used later * last
	 * statements to not consume local variables
	 */
	protected boolean refactorAroundAdvice(MethodDeclaration node) {
		Block body = node.getBody();
		if (body == null)
			return false;
		List<Statement> statements = body.statements();
		List<Statement> refactorFirst = RefactoringUtils.findBeforeStatements(
				node, colorManager, derivative);
		List<Statement> refactorLast = RefactoringUtils.findAfterStatements(
				node, colorManager, derivative);

		boolean canRefactor = RefactoringUtils
				.canRefactorStatementsBeforeAfter(node, refactorFirst,
						refactorLast, colorManager, derivative);

		if (canRefactor) {
			statements.removeAll(refactorFirst);
			statements.removeAll(refactorLast);
			extractAroundMethodRefinement(node, refactorFirst, refactorLast);
			return true;
		}
		return false;
	}

	protected void visitMethodDeclParameters(MethodDeclaration method,
			List<SingleVariableDeclaration> parameters) {
		for (SingleVariableDeclaration parameter : parameters) {
			if (derivative.equals(colorManager.getColors(parameter))) {
				refactorParameterDeclaration(method, parameter);
			}
		}
	}

	// public boolean visit(MethodInvocation methodInvoc) {
	// for (Expression arg : (List<Expression>) methodInvoc.arguments()) {
	// if (derivative.equals(colorManager.getColors(arg))) {
	//				
	// }
	// }
	//
	// return true;
	// }

	/**
	 * removes parameter from method declaration, creates a threadsafe field
	 * instead changes all local access inside the body to field access
	 * 
	 * @param method
	 * @param parameter
	 */
	private void refactorParameterDeclaration(MethodDeclaration method,
			SingleVariableDeclaration parameter) {
		if (method.parameters().remove(parameter)) {
			FieldDeclaration parameterField = createParamterField(method,
					parameter);
			replaceParamterAccess(method, parameter, parameterField);
		}
	}

	private void replaceParamterAccess(final MethodDeclaration method,
			final SingleVariableDeclaration parameter,
			FieldDeclaration parameterField) {
		final IVariableBinding oldParameterBinding = parameter.resolveBinding();
		method.getBody().accept(new ASTVisitor() {
			public boolean visit(SimpleName node) {
				if (oldParameterBinding.isEqualTo(node.resolveBinding()))
					node
							.setIdentifier(getParameterFieldName(method,
									parameter));
				return true;
			}
		});

	}

	private FieldDeclaration createParamterField(MethodDeclaration method,
			SingleVariableDeclaration parameter) {
		String parameterFieldName = getParameterFieldName(method, parameter);
		AST ast = parameter.getAST();
		VariableDeclarationFragment fragment = ast
				.newVariableDeclarationFragment();
		fragment.setName(ast.newSimpleName(parameterFieldName));
		FieldDeclaration parameterField = ast.newFieldDeclaration(fragment);

		// TODO: use a threadlocal instead of an ordinary variable (is more
		// difficult to replace in source code, as we have to distinguish
		// between read and write access
		// SimpleType threadLocalType = ast.newSimpleType(ast
		// .newSimpleName("ThreadLocal"));
		// ParameterizedType parameterizedThreadLocal = ast
		// .newParameterizedType(threadLocalType);
		// parameterizedThreadLocal.typeArguments().add(parameter.getType());
		//
		// parameterField.setType(parameterizedThreadLocal);
		parameterField.setType(parameter.getType());
		parameterField.modifiers()
				.addAll(getScopeModifiers(method.modifiers()));
		targetCompUnit.getRefinement().addField(parameterField);
		return parameterField;
	}

	/**
	 * helper function, filters all modifiers and returns only private public
	 * and protected
	 * 
	 * @param modifiers
	 * @return
	 */
	private Collection<Modifier> getScopeModifiers(
			List<IExtendedModifier> modifiers) {
		Set<Modifier> result = new HashSet<Modifier>();
		for (IExtendedModifier modifier : modifiers)
			if (modifier.isModifier()) {
				Modifier m = (Modifier) modifier;
				if (m.isPrivate() || m.isProtected() || m.isPublic())
					result.add(m);
			}
		return result;
	}

	private ThreadLocal<Object> a;

	private String getParameterFieldName(MethodDeclaration method,
			SingleVariableDeclaration parameter) {
		a.get();
		return "param_" + method.getName().getIdentifier() + "_"
				+ parameter.getName().getIdentifier();
	}

	private void extractAroundMethodRefinement(MethodDeclaration method,
			List<Statement> refactorFirst, List<Statement> refactorLast) {
		AST ast = method.getAST();
		MethodDeclaration copiedMethod = (MethodDeclaration) ASTNode
				.copySubtree(ast, method);
		Statement superCall = createSuperCall(method, ast, refactorLast
				.isEmpty(), null);

		List<Statement> targetBody = copiedMethod.getBody().statements();
		targetBody.clear();
		targetBody.addAll(refactorFirst);
		if (superCall != null)
			targetBody.add(superCall);
		targetBody.addAll(refactorLast);

		if (!refactorLast.isEmpty()
				&& !RefactoringUtils.isVoid(method.getReturnType2())) {
			targetBody.add(createReturnStatement(ast));
		}

		targetCompUnit.getRefinement().addRefinementForMethod(method,
				copiedMethod);
	}

	private void addStatements(List<Statement> newStatements,
			List<Statement> target) {
		for (Statement stmt : newStatements) {
			ASTNode excluded = RefactoringUtils.getSubtreeRuleExceptionNode(
					stmt, colorManager, derivative);

			if (excluded != null) {
				// move excluded into a new hook method and
			}

			target.add(stmt);
		}
	}

	/**
	 * refactore all statements in the middle of a method by introducing hooks
	 * 
	 * @param block
	 */
	public boolean visit(Block block) {
		findStatementsSequencesInBlockForRefactoring(block);

		return super.visit(block);
	}

	private void findStatementsSequencesInBlockForRefactoring(Block block) {
		List<Statement> statements = block.statements();
		int idx = statements.size() - 1;

		List<Statement> statementsToReplace = new ArrayList<Statement>();
		// grouping statements from back to front and refactoring groups with
		// hooks
		while (idx >= 0) {
			Statement stmt = statements.get(idx);
			boolean isColoredStmt = derivative.equals(colorManager
					.getColors(stmt));
			boolean isSubtreeRuleException = false, isNextSubtreeRuleException = false;
			if (isColoredStmt) {
				isSubtreeRuleException = RefactoringUtils
						.isSubtreeRuleException(stmt, colorManager, derivative);
				if (isSubtreeRuleException && idx > 0) {
					isNextSubtreeRuleException = derivative.equals(colorManager
							.getColors(statements.get(idx - 1)))
							&& RefactoringUtils.isSubtreeRuleException(
									statements.get(idx - 1), colorManager,
									derivative);
					;
				}
				statementsToReplace.add(0, stmt);
			}

			boolean refactorCollectedStatements = false;
			if (idx == 0)
				refactorCollectedStatements = true;
			// group by colored statements
			if (!isColoredStmt)
				refactorCollectedStatements = true;
			// only one subtree rule exception per block possible
			if (isSubtreeRuleException && isNextSubtreeRuleException)
				refactorCollectedStatements = true;
			if (refactorCollectedStatements && statementsToReplace.size() > 0) {

				refactorStatementSequenceUsingHook(statementsToReplace, block,
						idx + (isColoredStmt ? 0 : 1));

				statementsToReplace = new ArrayList<Statement>();
			}
			idx--;

		}
		assert statementsToReplace.size() == 0;

	}

	private void refactorStatementSequenceUsingHook(
			List<Statement> statementSequence, Block containingBlock,
			int replacementPosition) {
		Statement exception = RefactoringUtils.findSubtreeRuleException(
				statementSequence, colorManager, derivative);
		JakHookMethodHelper hook = new JakHookMethodHelper(statementSequence,
				RefactoringUtils.getMethodDeclaration(containingBlock),
				exception, colorManager);
		containingBlock.statements().removeAll(statementSequence);
		Statement hookCall = hook.getHookCall();
		containingBlock.statements().add(replacementPosition, hookCall);
		mainType.peek().bodyDeclarations().add(hook.getHookDeclaration());
		JakClassRefinement ref = targetCompUnit.getRefinement();
		ref.addRefinementForMethod(RefactoringUtils
				.getMethodDeclaration(containingBlock), hook.getRefinement());
		// color hook method with the same color as the call (if the
		// call inherits any colors)
		Set<Feature> hookColors = colorManager.getInheritedColors(hookCall);
		if (hookColors.size() > 0)
			colorManager.setColors(hook.getHookDeclaration(), hookColors);

		ignoredMethodsForAroundAdvice.add(hook.getHookDeclaration());
	}

	/**
	 * creates a Super call (actually a super call that is replaced by Super
	 * when printing the Jak file with the JakPrettyPrinter).
	 * 
	 * @param currentMethod
	 * @param ast
	 * @param withReturn
	 *            if true then the result of the super invocation is returned
	 *            (return Super...), otherwise it is placed in a local variable
	 *            called result
	 * @param formal
	 *            local variable to which the result (if any) should be
	 *            assigned. if formal==null, then a new variable is created with
	 *            the name result (ChK: is there a usecase for this?)
	 * @return
	 */
	static Statement createSuperCall(MethodDeclaration currentMethod, AST ast,
			boolean withReturn, Formal formal) {
		Statement superCall;
		if (currentMethod.isConstructor()) {
			assert !withReturn;
			/**
			 * super constructor calls are implicit in Jak. so there is no
			 * statement to call a super constructor.
			 */
			superCall = null;

		} else {
			SuperMethodInvocation superCallExpr = ast
					.newSuperMethodInvocation();
			SuperCallHelper.addSuperLayerCall(superCallExpr);
			superCallExpr.setName(ast.newSimpleName(currentMethod.getName()
					.getIdentifier()));
			String types = "";
			for (Iterator<SingleVariableDeclaration> iter = currentMethod
					.parameters().iterator(); iter.hasNext();) {
				SingleVariableDeclaration param = iter.next();
				SimpleName v = ast.newSimpleName(param.getName()
						.getIdentifier());
				superCallExpr.arguments().add(v);

				VariableDeclaration decl = LocalVariableHelper
						.findVariableDeclaration(param.getName());
				if (decl != null)
					LocalVariableHelper.addLocalVariableAccess(v, decl);

				types += getTypeString(param.getType());
				if (iter.hasNext())
					types += ", ";
			}
			SuperTypeHelper.cacheTypes(superCallExpr, types);
			if (RefactoringUtils.isVoid(currentMethod.getReturnType2())) {
				superCall = ast.newExpressionStatement(superCallExpr);
			} else {
				if (withReturn) {
					ReturnStatement returnStatement = ast.newReturnStatement();
					returnStatement.setExpression(superCallExpr);
					superCall = returnStatement;
				} else {
					if (formal == null) {
						VariableDeclarationFragment returnVariable = ast
								.newVariableDeclarationFragment();
						returnVariable.setName(ast.newSimpleName("result"));
						VariableDeclarationStatement variableDecl = ast
								.newVariableDeclarationStatement(returnVariable);
						variableDecl.setType((Type) ASTNode.copySubtree(
								currentMethod.getAST(), currentMethod
										.getReturnType2()));
						returnVariable.setInitializer(superCallExpr);
						superCall = variableDecl;
					} else {
						Assignment assign = ast.newAssignment();
						SimpleName v = ast.newSimpleName(formal.name);
						assign.setLeftHandSide(v);
						LocalVariableHelper.addLocalVariableAccess(v, formal);
						assign.setRightHandSide(superCallExpr);
						superCall = ast.newExpressionStatement(assign);
					}

				}
			}
		}
		return superCall;
	}

	private static String getTypeString(Type type) {
		ITypeBinding binding = type.resolveBinding();
		if (binding != null)
			return binding.getName();
		if (type.isPrimitiveType())
			return ((PrimitiveType) type).getPrimitiveTypeCode().toString();
		if (type.isSimpleType())
			return ((SimpleType) type).getName().toString();

		assert false : "unable to resolve type";
		return type.toString();
	}

	private void refactorMethod(TypeDeclaration type, MethodDeclaration method) {
		type.bodyDeclarations().remove(method);

		targetCompUnit.getRefinement().addMethodIntroductionForType(type,
				method);
	}
}
