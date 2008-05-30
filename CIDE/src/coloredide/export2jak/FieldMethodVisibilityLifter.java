package coloredide.export2jak;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class FieldMethodVisibilityLifter extends ASTVisitor {

	private CompilationUnit compUnit;

	FieldMethodVisibilityLifter(CompilationUnit compUnit) {
		this.compUnit = compUnit;
	}

	private boolean visitName(Name node) {
		IBinding binding = node.resolveBinding();
		if (binding == null)
			return true;

		ASTNode declaration = compUnit.findDeclaringNode(binding);
		if (declaration instanceof MethodDeclaration) {
			makeProtected((MethodDeclaration) declaration);
		} else if (declaration instanceof VariableDeclarationFragment) {
			VariableDeclarationFragment variableFragment = (VariableDeclarationFragment) declaration;

			if (variableFragment.getParent() instanceof FieldDeclaration) {
				FieldDeclaration fieldDecl = (FieldDeclaration) variableFragment
						.getParent();
				makeProtected(fieldDecl);
			}
		}

		return false;
	}

	private void makeProtected(BodyDeclaration bodyDecl) {
		List<Modifier> modifierList = bodyDecl.modifiers();
		for (Iterator<Modifier> iter = modifierList.iterator(); iter.hasNext();) {
			Modifier modifier = iter.next();
			if (modifier.isPrivate()) {
				iter.remove();
				Modifier protectedModifier = bodyDecl.getAST().newModifier(
						Modifier.ModifierKeyword.PROTECTED_KEYWORD);
				modifierList.add(protectedModifier);
			}
		}

	}

	@Override
	public boolean visit(QualifiedName node) {

		return visitName(node);
	}

	public boolean visit(SimpleName node) {
		return visitName(node);
	}

}
