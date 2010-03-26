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

package de.ovgu.cide.importjak.featurehouseextension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.prop4j.And;
import org.prop4j.Node;
import org.prop4j.Not;

import de.ovgu.cide.fstgen.ast.FSTNode;
import de.ovgu.cide.fstgen.ast.FSTNonTerminal;
import de.ovgu.cide.fstgen.ast.FSTTerminal;

public class FeatureJavaMethodOverriding {
	public final static String COMPOSITION_RULE_NAME = "JavaMethodOverriding";

	public static void compose(FSTTerminal terminalA, FSTTerminal terminalB,
			FSTTerminal terminalComp, FSTNonTerminal nonterminalParent) {

		if (terminalA.getBody().contains("original")) {
			composeWrapper(terminalA, terminalB, terminalComp,
					nonterminalParent);

		} else {
			composeConcatenate(terminalA, terminalB, terminalComp,
					nonterminalParent);
		}
	}

	/**
	 * merging two mutually exclusive method introductions
	 * 
	 * @param terminalA
	 * @param terminalB
	 * @param terminalComp
	 * @param nonterminalParent
	 */
	private static void composeConcatenate(FSTTerminal terminalA,
			FSTTerminal terminalB, FSTTerminal terminalComp,
			FSTNonTerminal nonterminalParent) {

		// concatenation is only allowed if both features are mutually
		// exclusive. otherwise method refinement must be used

		boolean mutuallyExclusive = GuidslFileLoader.featureModel
				.checkCondition(new Not(new And(FeatureExpressions
						.get(terminalA), FeatureExpressions.get(terminalB))));
		// assert mutuallyExclusive;

		String constructorA = terminalA.getBody();
		String constructorB = terminalB.getBody();

		if (whitespaceEqual(constructorA, constructorB))
			return;

		String constructorBHeader = constructorB.substring(0, constructorB
				.indexOf("{") + 1);
		String constructorBBody = constructorB.substring(constructorB
				.indexOf("{") + 1, constructorB.lastIndexOf("}"));

		// AbstractFSTPrintVisitor
		// .startIfdef(terminalB.featureExpression)
		// + AbstractFSTPrintVisitor.endIfdef(terminalB.featureExpression);
		String constructorABody = constructorA.substring(constructorA
				.indexOf("{") + 1, constructorA.lastIndexOf("}"));
		String constructorAEnd = constructorA.substring(constructorA
				.lastIndexOf("}"), constructorA.length());

		// TODO: unsauber. wenn erst ein wrapper kamm, dann ueberschreiben
		// erkennt dieser mechanismus das nicht richtig. trotzdem temporaer
		// verwendet um unnoetige schachtelung zu verhindern
		if (!constructorBBody.startsWith("/*IF")) {
			constructorBBody = FeatureJavaPrintVisitor
					.startIfdef(FeatureExpressions.get(terminalB))
					+ "if (true) {"
					+ constructorBBody
					+ "}"
					+ FeatureJavaPrintVisitor.endIfdef(FeatureExpressions
							.get(terminalB));
		}

		if (!mutuallyExclusive) {
			String warning = "Two conflicting method introductions (or wrong composition order): "
					+ terminalA.getName() + " -- "
					+ FeatureExpressions.get(terminalA) + " vs. "
					+ FeatureExpressions.get(terminalB)
					+ " (can occur in the same variant)";
			System.err.println(warning);
			constructorBBody = "// WARNING: " + warning + "\n"
					+ constructorBBody;
		}

		terminalComp.setBody(constructorBHeader
				+ constructorBBody
				+ FeatureJavaPrintVisitor.startIfdef(FeatureExpressions
						.get(terminalA))
				+ "if (true) {"
				+ constructorABody
				+ "}"
				+ FeatureJavaPrintVisitor.endIfdef(FeatureExpressions
						.get(terminalA)) + constructorAEnd);
	}

	/**
	 * are the two strings equal when we normalize all whitespace?
	 * 
	 * @param constructorA
	 * @param constructorB
	 * @return
	 */
	static boolean whitespaceEqual(String constructorA, String constructorB) {
		return constructorA.replaceAll("\\s*", "").equals(
				constructorB.replaceAll("\\s*", ""));
	}

	public enum WrapperCase {
		topLevelCall, topLevelAssignment, complex
	};

	/**
	 * compose method with a wrapper. inline the original method
	 * 
	 * 
	 * @param terminalA
	 * @param terminalB
	 * @param terminalComp
	 * @param nonterminalParent
	 */
	private static void composeWrapper(FSTTerminal terminalRefinement,
			FSTTerminal terminalBase, FSTTerminal terminalComp,
			FSTNonTerminal nonterminalParent) {

		/**
		 * there are three basic cases we want to cover
		 * 
		 * a) there is a single top-level original call that is not part of an
		 * assignment. in this case, the statements before and after can be
		 * annotated.
		 * 
		 * b) there is a single top-level original call, the result of which is
		 * assigned to something or used as a parameter. in this case, the
		 * result is written in a temporary variable. the wrapper code is
		 * annotated and original replaced by this variable.
		 * 
		 * c) non-top-level calls, multiple original calls, and some special
		 * cases where one call can be made multiple times (e.g. in
		 * for-loop-header). in this case, we do not attempt a complex
		 * annotation but have to code blocks with disjoint annotations, one
		 * simply calling original and the wrapper code.
		 * 
		 */

		WrapperCase wcase = getWrapperCase(terminalRefinement.getBody());

		String newBody;

		String oldMethodName = getMethodSig(terminalBase.getBody());
		// if (oldMethodName.indexOf("__wrappee__")>0)
		// oldMethodName=oldMethodName.substring(0,oldMethodName.indexOf("__wrappee__"));
		String targetMethodName = oldMethodName + "__wrappee__"
				+ getFeatureName(terminalRefinement);
		switch (wcase) {
		case topLevelCall:
			newBody = composeWrapperTopLevelCall(terminalRefinement.getBody(),
					targetMethodName, FeatureExpressions
							.get(terminalRefinement));
			break;
		case topLevelAssignment:
			newBody = composeWrapperTopLevelAssignment(terminalRefinement
					.getBody(), targetMethodName, FeatureExpressions
					.get(terminalRefinement));
			break;
		default:
			newBody = composeWrapperComplex(terminalRefinement.getBody(),
					targetMethodName, FeatureExpressions
							.get(terminalRefinement));
		}

		terminalComp.setBody(renameMethod(terminalBase.getBody(),
				targetMethodName));
		terminalComp.setName(targetMethodName);

		FSTTerminal terminalComp2 = (FSTTerminal) terminalBase.getDeepClone();
		FeatureExpressions.cloneFE(terminalComp2, terminalBase);
		nonterminalParent.addChild(terminalComp2);
		terminalComp2.setBody(newBody);
		terminalComp2.setName(terminalBase.getName());
	}

	private static String renameMethod(String body, String targetMethodName) {
		ASTNode ast = parseMethod(body);
		WrapperCaseVisitor wcv = new WrapperCaseVisitor();
		ast.accept(wcv);

		SimpleName name = wcv.methodDeclaration.getName();
		return body.substring(0, name.getStartPosition()) + targetMethodName
				+ body.substring(name.getStartPosition() + name.getLength());

	}

	private static String getMethodSig(String body) {
		ASTNode ast = parseMethod(body);
		WrapperCaseVisitor wcv = new WrapperCaseVisitor();
		ast.accept(wcv);

		if (wcv.methodDeclaration == null)
			return "error";

		return wcv.methodDeclaration.getName().getIdentifier();
	}

	public static String composeWrapperTopLevelCall(String wrapper,
			String targetMethodName, Node featureExpression) {

		ASTNode originalCallNode = getOriginalInvocation(wrapper).getParent();

		String wrapperHeader = wrapper.substring(0, wrapper.indexOf("{") + 1);
		String wrapperTail = wrapper.substring(wrapper.lastIndexOf("}"));
		String codeBeforeOriginal = wrapper.substring(wrapper.indexOf("{") + 1,
				originalCallNode.getStartPosition());
		String originalCall = wrapper.substring(originalCallNode
				.getStartPosition(), originalCallNode.getStartPosition()
				+ originalCallNode.getLength());
		String codeAfterOriginal = wrapper.substring(originalCallNode
				.getStartPosition()
				+ originalCallNode.getLength(), wrapper.lastIndexOf("}"));

		originalCall = originalCall.replace("original", targetMethodName);

		String ifdef = FeatureJavaPrintVisitor.startIfdef(featureExpression);
		String endif = FeatureJavaPrintVisitor.endIfdef(featureExpression);

		if (!codeBeforeOriginal.trim().isEmpty())
			codeBeforeOriginal = ifdef + codeBeforeOriginal + endif;
		if (!codeAfterOriginal.trim().isEmpty())
			codeAfterOriginal = ifdef + codeAfterOriginal + endif;

		return wrapperHeader + codeBeforeOriginal + originalCall
				+ codeAfterOriginal + wrapperTail;
	}

	public static String composeWrapperTopLevelAssignment(String wrapper,
			String targetMethodName, Node featureExpression) {
		String body = getBody(wrapper);
		if (body.trim().matches("return\\s+original\\s*\\(.*"))
			return wrapper
					.replaceAll("original\\s*\\(", targetMethodName + "(");

		ASTNode ast = parseMethod(wrapper);
		String varName = getFreshName(ast);
		String returnType = getReturnType(ast);
		ASTNode originalInvocation = getOriginalInvocation(wrapper);
		String originalInvocationStr = wrapper.substring(originalInvocation
				.getStartPosition(), originalInvocation.getStartPosition()
				+ originalInvocation.getLength());
		ASTNode originalCallNode = getOuterStatement(originalInvocation);
		ReturnStatement returnStatement = getReturnStatement(ast);

		String wrapperHeader = wrapper.substring(0, wrapper.indexOf("{") + 1);
		String wrapperTail = wrapper.substring(wrapper.lastIndexOf("}"));

		String codeBeforeOriginal = wrapper.substring(wrapper.indexOf("{") + 1,
				originalCallNode.getStartPosition());
		String codeAfterWithOriginal = wrapper.substring(originalCallNode
				.getStartPosition(), wrapper.lastIndexOf("}"));

		// replace return statement
		if (returnStatement != null
				&& returnStatement.getStartPosition() > originalCallNode
						.getStartPosition()) {
			codeAfterWithOriginal = codeAfterWithOriginal.substring(0,
					returnStatement.getStartPosition()
							- originalCallNode.getStartPosition())
					+ varName
					+ " = "
					+ codeAfterWithOriginal.substring(returnStatement
							.getExpression().getStartPosition()
							- originalCallNode.getStartPosition());
		}
		// replace original call
		codeAfterWithOriginal = codeAfterWithOriginal.replace(
				originalInvocationStr, varName);

		String ifdef = FeatureJavaPrintVisitor.startIfdef(featureExpression);
		String endif = FeatureJavaPrintVisitor.endIfdef(featureExpression);

		if (!codeBeforeOriginal.trim().isEmpty())
			codeBeforeOriginal = ifdef + codeBeforeOriginal + endif;
		if (!codeAfterWithOriginal.trim().isEmpty())
			codeAfterWithOriginal = ifdef + codeAfterWithOriginal + endif;

		String newAssignment = returnType + " " + varName + " = "
				+ originalInvocationStr.replace("original", targetMethodName)
				+ ";";
		String newReturn = "return " + varName + ";";
		return wrapperHeader + codeBeforeOriginal + newAssignment
				+ codeAfterWithOriginal + newReturn + wrapperTail;
	}

	private static ReturnStatement getReturnStatement(ASTNode ast) {
		WrapperCaseVisitor wcv = new WrapperCaseVisitor();
		ast.accept(wcv);
		return wcv.lastReturnStmt;
	}

	private static ASTNode getOuterStatement(ASTNode node) {
		while (node != null) {
			if (node instanceof Statement)
				return node;
			node = node.getParent();
		}
		return null;
	}

	private static String getBody(String wrapper) {
		return wrapper.substring(wrapper.indexOf("{") + 1, wrapper
				.lastIndexOf("}"));
	}

	private static String getFreshName(ASTNode ast) {
		final Set<String> usedNames = new HashSet<String>();
		// result.add("result");

		ast.accept(new ASTVisitor() {
			@Override
			public boolean visit(SimpleName node) {
				usedNames.add(node.getIdentifier());
				return super.visit(node);
			}
		});
		String result = "result";
		while (usedNames.contains(result))
			result = result + "_";
		return result;
	}

	private static String getReturnType(ASTNode ast) {
		WrapperCaseVisitor wcv = new WrapperCaseVisitor();
		ast.accept(wcv);
		return wcv.returnType;
	}

	static private ASTNode getOriginalInvocation(String wrapper) {
		ASTNode ast = parseMethod(wrapper);
		WrapperCaseVisitor wcv = new WrapperCaseVisitor();
		ast.accept(wcv);
		return wcv.lastOriginalInvocation;
	}

	private static class WrapperCaseVisitor extends ASTVisitor {
		private WrapperCase result = WrapperCase.complex;
		ASTNode lastOriginalInvocation;
		ReturnStatement lastReturnStmt;
		String returnType;
		List<String> parameterNames = new ArrayList<String>();
		int originalCount = 0;
		int returnCount = 0;
		MethodDeclaration methodDeclaration;

		public boolean visit(SimpleName node) {
			if (node.getIdentifier().equals("original")
					&& node.getParent() instanceof MethodInvocation) {
				originalCount++;
				lastOriginalInvocation = node.getParent();
				if (node.getParent().getParent() instanceof ExpressionStatement) {
					result = WrapperCase.topLevelCall;
				} else if (node.getParent().getParent() instanceof ForStatement
						|| node.getParent().getParent() instanceof WhileStatement
						|| node.getParent().getParent() instanceof DoStatement)
					result = WrapperCase.complex;
				else
					result = WrapperCase.topLevelAssignment;
				if (parentStatements(node) != 2)
					result = WrapperCase.complex;
				if (originalCount > 1)
					result = WrapperCase.complex;
			}
			return super.visit(node);
		}

		public boolean visit(ReturnStatement node) {
			returnCount++;
			lastReturnStmt = node;
			return super.visit(node);
		}

		@Override
		public boolean visit(MethodDeclaration node) {
			methodDeclaration = node;
			returnType = node.getReturnType2().toString();
			for (SingleVariableDeclaration parameter : (List<SingleVariableDeclaration>) node
					.parameters()) {
				parameterNames.add(parameter.getName().getIdentifier());
			}
			return super.visit(node);
		}

		private int parentStatements(ASTNode node) {
			int result = 0;
			while (node != null) {
				if (node instanceof Statement)
					result++;
				node = node.getParent();
			}
			return result;
		}

		public WrapperCase getResult() {
			if (result == WrapperCase.topLevelAssignment && returnCount > 1)
				result = WrapperCase.complex;
			return result;
		}
	}

	public static WrapperCase getWrapperCase(String wrapper) {
		ASTNode ast = parseMethod(wrapper);
		// System.out.println(ast);
		WrapperCaseVisitor wcv = new WrapperCaseVisitor();
		ast.accept(wcv);

		return wcv.getResult();

	}

	private static ASTNode parseMethod(String wrapper) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		Hashtable<String,String> options = JavaCore.getOptions();
		options.put(JavaCore.COMPILER_COMPLIANCE, "1.5");
		options.put(JavaCore.COMPILER_SOURCE, "1.5");
		
		parser.setCompilerOptions(options);
		parser.setKind(ASTParser.K_CLASS_BODY_DECLARATIONS);
		parser.setSource(wrapper.toCharArray());
		ASTNode ast = parser.createAST(null);

		if (ast instanceof CompilationUnit) {
			if (((CompilationUnit) ast).getProblems().length > 0)
				for (IProblem problem : ((CompilationUnit) ast).getProblems())
					if (problem.isError())
						System.err.println(problem.getMessage() + "\n" + wrapper);
		}

		return ast;
	}

	private static String getFeatureName(FSTNode node) {
		if (node.getType().equals("Feature"))
			return node.getName();
		else
			return getFeatureName(node.getParent());
	}

	public static String composeWrapperComplex(String wrapper,
			String targetMethodName, Node featureExpression) {

		String toReplace = "original\\s*\\(";

		wrapper = wrapper.replaceAll(toReplace, targetMethodName + "(");

		ASTNode ast = parseMethod(wrapper);
		String returnType = getReturnType(ast);
		List<String> parameterNames = getParameterNames(ast);

		String wrapperHeader = wrapper.substring(0, wrapper.indexOf("{") + 1);
		String wrapperTail = wrapper.substring(wrapper.lastIndexOf("}"));
		String wrapperBody = getBody(wrapper);

		String ifdef = FeatureJavaPrintVisitor.startIfdef(featureExpression);
		String endif = FeatureJavaPrintVisitor.endIfdef(featureExpression);
		String negifdef = FeatureJavaPrintVisitor.startIfdef(new Not(
				featureExpression));
		String negendif = FeatureJavaPrintVisitor.endIfdef(new Not(
				featureExpression));

		String directOriginal = targetMethodName + "(";
		for (int i = 0; i < parameterNames.size(); i++) {
			if (i != 0)
				directOriginal += ", ";
			directOriginal += parameterNames.get(i);
		}
		directOriginal += ");";
		if (!returnType.equals("void")) {
			directOriginal = "if (true) return " + directOriginal;
		}

		return wrapperHeader + negifdef + directOriginal + negendif + ifdef
				+ wrapperBody + endif + wrapperTail;
	}

	private static List<String> getParameterNames(ASTNode ast) {
		WrapperCaseVisitor wcv = new WrapperCaseVisitor();
		ast.accept(wcv);
		return wcv.parameterNames;
	}
}
