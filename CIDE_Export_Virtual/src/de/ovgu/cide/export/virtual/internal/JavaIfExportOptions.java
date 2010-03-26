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

package de.ovgu.cide.export.virtual.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import de.ovgu.cide.export.CopiedNaiveASTFlattener;
import de.ovgu.cide.export.useroptions.IUserOption;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.jdt.AstidWrapper;
import de.ovgu.cide.language.jdt.AstidWrapperWithParents;

public class JavaIfExportOptions implements IPPExportOptions {

	public CopiedNaiveASTFlattener getPrettyPrinter(ColoredSourceFile sourceFile) {
		return new PP2JavaPrettyPrinter(sourceFile.getColorManager(),
				sourceFile.getProject(), this);
	}

	public String getEndInstruction(Set<IFeature> f) {
		return "}\n";
	}

	public String getStartInstruction(Set<IFeature> fs) {
		String result = "if (";
		boolean first = true;
		for (IFeature f : fs) {
			if (!first)
				result += " && ";
			result += "SPLConfiguration." + f.getName().toUpperCase();
			first = false;
		}
		return result + ") {\n";
	}

	public boolean inNewLine() {
		return false;
	}

	public List<IUserOption> getUserOptions() {
		return null;
	}

	/**
	 * change all annotated local variable declarations into fields
	 */
	public CompilationUnit refactorAST(CompilationUnit root,
			final ColoredSourceFile source) {

		root.accept(new ASTVisitor() {

			/**
			 * from bindingKey to new name
			 */
			Map<String, String> localVariableNames = new HashMap<String, String>();
			int counter = 0;

			@SuppressWarnings("unchecked")
			@Override
			public void postVisit(ASTNode node) {
				IBinding binding;
				AST ast = node.getAST();
				if (node instanceof SimpleName) {
					binding = ((Name) node).resolveBinding();
					if ((binding instanceof IVariableBinding)
							&& !((IVariableBinding) binding).isField()) {
						// local variable
						((SimpleName) node).setIdentifier(getLocalVariableName(
								(SimpleName) node, ((IVariableBinding) binding)
										.getKey()));
					}
				}
				if (node instanceof VariableDeclarationFragment) {
					if (((VariableDeclarationFragment) node).resolveBinding() == null)
						return;

					Set<IFeature> colors = source.getColorManager().getColors(
							new AstidWrapperWithParents(node));

					String fieldName = getLocalVariableName(
							((VariableDeclarationFragment) node).getName(),
							((VariableDeclarationFragment) node)
									.resolveBinding().getKey());

					TypeDeclaration outerClass = getOuterClass(node);
					VariableDeclarationFragment fieldDeclFrg = ast
							.newVariableDeclarationFragment();
					fieldDeclFrg.setName(ast.newSimpleName(fieldName));
					FieldDeclaration fieldDecl = ast
							.newFieldDeclaration(fieldDeclFrg);
					outerClass.bodyDeclarations().add(fieldDecl);

					source.getColorManager().setColors(new AstidWrapper(node),
							colors);
				}
				if (node instanceof VariableDeclarationStatement) {
					// replace variable declarations by assignments
					VariableDeclarationStatement vds = (VariableDeclarationStatement) node;
					assert vds.fragments().size() <= 1;
					
					Set<IFeature> colors = source.getColorManager().getColors(
							new AstidWrapperWithParents(node));
					
					for (VariableDeclarationFragment fragment : (List<VariableDeclarationFragment>) vds
							.fragments()) {

						if (fragment.getInitializer() != null) {
							Assignment assignment = ast.newAssignment();
							assignment.setLeftHandSide(ast
									.newSimpleName(fragment.getName()
											.getIdentifier()));
							assignment
									.setRightHandSide((Expression) ASTNode
											.copySubtree(ast, fragment
													.getInitializer()));
							ExpressionStatement assignmentStmt = ast
									.newExpressionStatement(assignment);
							if (node.getLocationInParent().isChildProperty())
								node.getParent().setStructuralProperty(
										node.getLocationInParent(),
										assignmentStmt);
							if (node.getLocationInParent()
									.isChildListProperty()) {
								List list = (List) node.getParent()
										.getStructuralProperty(
												node.getLocationInParent());
								int idx = list.indexOf(node);
								list.set(idx, assignmentStmt);
							}

							source.getColorManager().setColors(new AstidWrapper(assignmentStmt),
									colors);
						} else {
							List list = (List) node.getParent()
									.getStructuralProperty(
											node.getLocationInParent());
							list.remove(node);
						}
					}
				}

				super.postVisit(node);
			}

			private TypeDeclaration getOuterClass(ASTNode node) {
				while (node != null) {
					if (node instanceof TypeDeclaration)
						return (TypeDeclaration) node;
					node = node.getParent();
				}
				return null;
			}

			private String getLocalVariableName(SimpleName node, String key) {
				String name = localVariableNames.get(key);
				if (name == null) {
					name = "_" + node.getIdentifier() + (++counter);
					localVariableNames.put(key, name);
				}
				return name;
			}

		});

		return root;
	}
}
