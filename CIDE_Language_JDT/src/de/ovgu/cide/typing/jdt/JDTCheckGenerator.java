package de.ovgu.cide.typing.jdt;

import java.util.Set;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;

import de.ovgu.cide.features.Feature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.jdt.ASTBridge;
import de.ovgu.cide.typing.jdt.checks.FieldAccessCheck;
import de.ovgu.cide.typing.model.ITypingCheck;

/**
 * visitor for an eclipse AST that generates all kinds of checks for that
 * specific file
 * 
 * @author cKaestner
 * 
 */
public class JDTCheckGenerator extends ASTVisitor {

	private final ColoredSourceFile file;
	private final Set<ITypingCheck> checks;
	private final JDTTypingProvider jdtTypingProvider;

	public JDTCheckGenerator(ColoredSourceFile file,
			JDTTypingProvider jdtTypingProvider, Set<ITypingCheck> checks) {
		this.file = file;
		this.jdtTypingProvider = jdtTypingProvider;
		this.checks = checks;
	}

	public boolean visit(SimpleName node) {
		visitName(node);
		return super.visit(node);
	}

	public boolean visit(QualifiedName node) {
		visitName(node);
		return super.visit(node);
	}

	public void visitName(Name node) {
		IBinding binding = node.resolveBinding();

		if (binding instanceof IVariableBinding
				&& ((IVariableBinding) binding).isField()) {

			checks.add(new FieldAccessCheck(file, jdtTypingProvider,
					new ASTBridge().getASTNode(
							(CompilationUnit) node.getRoot(), node, false),
					(IVariableBinding) binding));
		}
	}
}
