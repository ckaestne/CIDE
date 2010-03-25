package coloredide.validator.checks;

import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.Type;

import coloredide.features.Feature;
import coloredide.features.source.IColoredJavaSourceFile;
import coloredide.validator.ValidationErrorCallback;
import coloredide.validator.ValidationVisitor;

/**
 * checks that used types have the same colors as declared ones.
 * 
 * @author cKaestner
 * 
 */

public class TypeValidator extends ValidationVisitor {

	public TypeValidator(ValidationErrorCallback callback,
			IColoredJavaSourceFile source) {
		super(callback, source);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void preVisit(ASTNode node) {
		if (node instanceof Type) {
			Type type = (Type) node;
			ITypeBinding binding = type.resolveBinding();
			if (binding != null) {
				Set<Feature> typeColors = javaElementColors().getColors(
						project(), binding);
				Set<Feature> nodeColors = nodeColors().getColors(node);
				if (!nodeColors.containsAll(typeColors))
					callback.errorTypeRefMustHaveTypeColor((Type) node, typeColors);
			}
		}
		super.preVisit(node);
	}

}
