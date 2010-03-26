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

/**
 * 
 */
package de.ovgu.cide.configuration.jdt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.ChildPropertyDescriptor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;
import org.eclipse.jdt.core.dom.InfixExpression.Operator;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;

import de.ovgu.cide.configuration.ConfigurationException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.jdt.JDTParserWrapper;

public class DeleteHiddenNodesVisitor extends ASTVisitor {

	public static String hideCode(ColoredSourceFile source,
			Collection<IFeature> selectedColors) throws ConfigurationException {
		try {
			ICompilationUnit compUnit = JDTParserWrapper
					.getICompilationUnit(source.getResource());
			assert compUnit != null;

			CompilationUnit ast = JDTParserWrapper.parseJavaFile(source
					.getResource());
			assert ast != null;

			return hideCode(compUnit.getBuffer().getContents(), ast,
					new JDTColorManagerBridge(source.getColorManager(), source
							.getResource()), selectedColors);
		} catch (Exception e) {
			throw new ConfigurationException(e);
		}
	}

	public static String hideCode(String buffer, CompilationUnit ast,
			JDTColorManagerBridge nodeColors, Collection<IFeature> visibleColors)
			throws JavaModelException, IllegalArgumentException {
		Set<IFeature> compUnitColors = nodeColors.getColors(ast);
		for (IFeature color : compUnitColors)
			if (!visibleColors.contains(color))
				return "";

		ASTRewrite rewrite = ASTRewrite.create(ast.getAST());
		ast.accept(new DeleteHiddenNodesVisitor(rewrite, nodeColors,
				visibleColors));
		TextEdit r = rewrite.rewriteAST();

		Document document = new Document(buffer);
		try {
			r.apply(document);
		} catch (MalformedTreeException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return document.get();
	}

	private ASTRewrite rewrite;

	protected Collection<IFeature> selectedColors;

	protected JDTColorManagerBridge colorManager;

	public DeleteHiddenNodesVisitor(ASTRewrite rewrite,
			JDTColorManagerBridge nodeColors,
			Collection<IFeature> selectedColors) {
		this.rewrite = rewrite;
		this.colorManager = nodeColors;
		this.selectedColors = selectedColors;
	}

	@Override
	public boolean visit(InfixExpression node) {
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	public void postVisit(ASTNode node) {
		if (shouldHide(node)) {
			// are there children that should not be deleted as well?
			List<ASTNode> replacements = new ArrayList<ASTNode>();
			for (Object prop : node.structuralPropertiesForType()) {
				if (ASTColorInheritance.notInheritedProperties.contains(prop)) {
					Object replace = rewrite.get(node,
							(StructuralPropertyDescriptor) prop);
					if (replace instanceof ASTNode)
						replacements.add((ASTNode) replace);
				}
			}
			remove(node, replacements);
		}
	}

	private boolean shouldHide(ASTNode node) {
		Set<IFeature> nodeColors = colorManager.getOwnColors(node);
		for (IFeature color : nodeColors)
			if (!selectedColors.contains(color))
				return true;

		return false;
	}

	/**
	 * deletes the node and replaced it with the replacement-list if not empty
	 * or null if only one single property can be replaced only the first of the
	 * list is inserted.
	 * 
	 * @param node
	 *            not null
	 * @param replacements
	 *            not null
	 */
	private void remove(ASTNode node, List<ASTNode> replacements) {
		ASTNode parent = node.getParent();
		StructuralPropertyDescriptor prop = node.getLocationInParent();
		if (prop instanceof ChildListPropertyDescriptor) {
			rewriteListChild(node, parent, (ChildListPropertyDescriptor) prop,
					replacements);
		}
		if (prop instanceof ChildPropertyDescriptor) {
			if (ASTColorInheritance.notInheritedProperties.contains(prop)) {
				rewriteIfWhileEtc(node, parent, prop, replacements);
			} else if (parent instanceof InfixExpression) {
				rewriteInfix(node, (InfixExpression) parent, prop, replacements);

			}
		}

	}

	private void rewriteInfix(ASTNode node, InfixExpression parent,
			StructuralPropertyDescriptor prop, List<ASTNode> replacements) {

		ASTNode defaultValue = null;
		AST ast = rewrite.getAST();
		if (parent.getOperator() == Operator.CONDITIONAL_AND)
			defaultValue = ast.newBooleanLiteral(true);
		else if (parent.getOperator() == Operator.CONDITIONAL_OR)
			defaultValue = ast.newBooleanLiteral(false);
		else if (parent.getOperator() == Operator.PLUS
				|| parent.getOperator() == Operator.MINUS
				|| parent.getOperator() == Operator.LEFT_SHIFT
				|| parent.getOperator() == Operator.RIGHT_SHIFT_SIGNED
				|| parent.getOperator() == Operator.RIGHT_SHIFT_UNSIGNED)
			defaultValue = ast.newNumberLiteral("0");
		if (defaultValue != null)
			rewrite.set(parent, prop, defaultValue, null);
	}

	private void rewriteIfWhileEtc(ASTNode node, ASTNode parent,
			StructuralPropertyDescriptor prop, List<ASTNode> replacements) {
		Block replacement = node.getAST().newBlock();
		rewrite.set(parent, prop, replacement, null);
		if (replacements.size() > 0) {
			ListRewrite blockRewriteList = getRewriteList(replacement,
					Block.STATEMENTS_PROPERTY);
			for (ASTNode s : replacements)
				for (ASTNode n : resolveBlock(s)) {
					blockRewriteList.insertLast(move(n), null);
				}
		} else if (prop == IfStatement.ELSE_STATEMENT_PROPERTY)
			rewrite.set(parent, prop, null, null);
	}

	private void rewriteListChild(ASTNode node, ASTNode parent,
			ChildListPropertyDescriptor prop, List<ASTNode> replacements) {
		ListRewrite statementsListRewrite = getRewriteList(parent, prop);
		int position = statementsListRewrite.getRewrittenList().indexOf(node);
		statementsListRewrite.remove(node, null);
		// replacements?
		if (replacements.size() > 0) {
			boolean parentBlock = parent instanceof Block;
			for (ASTNode repl : replacements) {
				if (!parentBlock) {
					statementsListRewrite
							.insertAt(move(repl), ++position, null);
				} else {
					for (ASTNode s : resolveBlock(repl))
						statementsListRewrite.insertAt(move(s), ++position,
								null);
				}
			}
		}

	}

	private ASTNode move(ASTNode s) {
		if (s.getStartPosition() != -1)
			return rewrite.createMoveTarget(s);
		return s;
	}

	private List<ASTNode> resolveBlock(ASTNode replacement) {
		if (replacement instanceof Block) {
			ListRewrite rewrittenBlock = getRewriteList(replacement,
					Block.STATEMENTS_PROPERTY);
			List l = rewrittenBlock.getRewrittenList();
			if (replacement.getStartPosition() == -1)
				return new ArrayList<ASTNode>();// TODO debugging only
			return l;

		}
		return Collections.singletonList(replacement);
	}

	private final HashMap<ASTNode, HashMap<ChildListPropertyDescriptor, ListRewrite>> knownRewriteLists = new HashMap<ASTNode, HashMap<ChildListPropertyDescriptor, ListRewrite>>();

	private ListRewrite getRewriteList(ASTNode parent,
			ChildListPropertyDescriptor descriptor) {

		HashMap<ChildListPropertyDescriptor, ListRewrite> known = knownRewriteLists
				.get(parent);
		ListRewrite knownList = null;
		if (known != null) {
			knownList = known.get(descriptor);
			if (knownList != null)
				return knownList;
		}

		knownList = rewrite.getListRewrite(parent, descriptor);
		if (known == null) {
			known = new HashMap<ChildListPropertyDescriptor, ListRewrite>();
			known.put(descriptor, knownList);
		}
		knownRewriteLists.put(parent, known);

		return knownList;
	}

}