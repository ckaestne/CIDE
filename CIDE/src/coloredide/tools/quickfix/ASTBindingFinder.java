package coloredide.tools.quickfix;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.TypeParameter;
import org.eclipse.jdt.core.dom.VariableDeclaration;


public class ASTBindingFinder extends ASTVisitor {

	ASTBindingFinder(String bindingKey) {
		this.target = bindingKey;
	}

	private ASTNode result = null;

	private final String target;

	public boolean visit(TypeDeclaration node) {
		if (result != null)
			return false;
		IBinding binding = node.resolveBinding();
		if (binding != null && binding.getKey().equals(target))
			result = node;
		return super.visit(node);
	}

	public boolean visit(MethodDeclaration node) {
		if (result != null)
			return false;
		IBinding binding = node.resolveBinding();
		if (binding != null && binding.getKey().equals(target))
			result = node;
		return super.visit(node);
	}

	public boolean visit(ImportDeclaration node) {
		if (result != null)
			return false;
		IBinding binding = node.resolveBinding();
		if (binding != null && binding.getKey().equals(target))
			result = node;
		return super.visit(node);
	}

	public boolean visit(PackageDeclaration node) {
		if (result != null)
			return false;
		IBinding binding = node.resolveBinding();
		if (binding != null && binding.getKey().equals(target))
			result = node;
		return super.visit(node);
	}

	public boolean visit(TypeParameter node) {
		if (result != null)
			return false;
		IBinding binding = node.resolveBinding();
		if (binding != null && binding.getKey().equals(target))
			result = node;
		return super.visit(node);
	}
	public void preVisit(ASTNode node) {
		if (node instanceof Type) {
			if (result != null)
				return;
			IBinding binding = ((Type) node).resolveBinding();
			if (binding != null && binding.getKey().equals(target))
				result = node;
		}
		if (node instanceof VariableDeclaration) {
			if (result != null)
				return;
			IBinding binding = ((VariableDeclaration) node).resolveBinding();
			if (binding != null && binding.getKey().equals(target))
				result = node;
		}
		if (node instanceof AbstractTypeDeclaration) {
			if (result != null)
				return;
			IBinding binding = ((AbstractTypeDeclaration) node).resolveBinding();
			if (binding != null && binding.getKey().equals(target))
				result = node;
		}
		super.preVisit(node);
	}

	public ASTNode getResult() {
		return result;
	}

}
