package coloredide.validator.checks;

import java.util.HashMap;
import java.util.Set;

import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import coloredide.features.Feature;
import coloredide.features.source.IColoredJavaSourceFile;
import coloredide.validator.ValidationErrorCallback;
import coloredide.validator.ValidationVisitor;

/**
 * checks that every access to a local variable belongs to the same feature as
 * it's declaration. can be resolved locally because a variable is always
 * defined before it's used. *
 * 
 * @author cKaestner
 * 
 */

public class LocalVariableValidator extends ValidationVisitor {

	public LocalVariableValidator(ValidationErrorCallback callback,
			IColoredJavaSourceFile source) {
		super(callback, source);
		// TODO Auto-generated constructor stub
	}

	private final HashMap<IVariableBinding, Set<Feature>> knownVariableDeclarations = new HashMap<IVariableBinding, Set<Feature>>();

	public boolean visit(VariableDeclarationFragment node) {
		visitVD(node);
		return super.visit(node);
	}

	public boolean visit(SingleVariableDeclaration node) {
		visitVD(node);
		return super.visit(node);
	}

	private void visitVD(VariableDeclaration node) {
		IVariableBinding binding = node.resolveBinding();
		if (binding != null)
			knownVariableDeclarations
					.put(binding, nodeColors().getColors(node));
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
				&& !((IVariableBinding) binding).isField()) {
			Set<Feature> callColors = nodeColors().getColors(node);
			Set<Feature> declColors = knownVariableDeclarations.get(binding);

			if (declColors != null && !callColors.containsAll(declColors))
				callback.errorVariableAccessMustHaveDeclarationColor(node,
						callColors, (IVariableBinding) binding, declColors);
		}
	}

}
