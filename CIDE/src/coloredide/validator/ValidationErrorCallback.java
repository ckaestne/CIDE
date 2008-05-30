package coloredide.validator;

import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;

import coloredide.features.Feature;

public interface ValidationErrorCallback {

	/**
	 * the node must have the same color as it's parent and cannot be colored
	 * individually.
	 * 
	 * for example the return type of a method must have the same color as the
	 * method
	 * 
	 * @param node
	 * @param parent
	 */
	public void nodeMustHaveParentsColor(ASTNode node, Set<Feature> callColors,
			ASTNode parent);

	public void errorCallMustHaveDeclarationColor(ASTNode call,
			Set<Feature> callColors, IMethodBinding declaration,
			Set<Feature> declColors);

	public void warningCannotResolveBinding(ASTNode node);

	public void errorVariableAccessMustHaveDeclarationColor(ASTNode node,
			Set<Feature> callColors, IVariableBinding binding,
			Set<Feature> declColors);

	public void errorCallParamMustHaveDeclarationColor(Expression param,
			Set<Feature> paramdeclColors, String note);

	public void errorTypeRefMustHaveTypeColor(Type node,
			Set<Feature> typeColors);

	public void errorImportMustHaveTargetColor(ImportDeclaration node,
			Set<Feature> typeColors);

	public void errorImportedTypeMustHaveImportColor(ASTNode type,
			Set<Feature> typeColors, Set<Feature> importColors);

	public void errorOverridingParamMustHaveDeclarationColor(
			SingleVariableDeclaration param, Set<Feature> ownSuperParamColors,
			String string);

}
