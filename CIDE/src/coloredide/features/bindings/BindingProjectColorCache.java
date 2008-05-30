package coloredide.features.bindings;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import coloredide.features.Feature;
import coloredide.features.source.IColorManager;

/**
 * this class caches the assignment of colors to JavaElement features.
 * 
 * otherwise only nodes inside one file are colored. but to check bindings it is
 * necessary to know the colors of the target methods in other classes. to not
 * need to read the colors for the whole ast of the target classes, this
 * JavaElementColorManager caches the colors for all elements that are potential
 * targets for bindings.
 * 
 * the data is stored between eclipse runs and can be updated asynchronously
 * with a builder.
 * 
 * @author cKaestner
 * 
 */
class BindingProjectColorCache implements Serializable {

	private static final long serialVersionUID = 3L;

	private final HashMap<String, Set<Feature>> bindingKeys2colors = new HashMap<String, Set<Feature>>();


	String project;

	BindingProjectColorCache(String project) {
		this.project = project;
	}

	/**
	 * called after an item's color is changed. cycles through all children an
	 * searches for java elements that need to be updated.
	 * 
	 * @param nodes
	 * @param file
	 */
	void updateASTColors(ASTNode node, final IColorManager nodeColors) {
		node.accept(new ASTVisitor() {
			public boolean visit(MethodDeclaration node) {
				String key = null;
				IMethodBinding binding = node.resolveBinding();
				if (binding != null) {
					IJavaElement javaElement = binding.getJavaElement();
					if (javaElement instanceof IMethod)
						key = ((IMethod) javaElement).getKey();
				}
				if (key != null) {
					Set<Feature> colors = nodeColors.getColors(node);
					update(bindingKeys2colors, key, colors);

					for (int paramIdx = 0; paramIdx < node.parameters().size(); paramIdx++) {
						ASTNode param = (ASTNode) node.parameters().get(
								paramIdx);
						update(bindingKeys2colors, getParamKey(key, paramIdx),
								nodeColors.getColors(param));
					}

				}
				return super.visit(node);
			}

			private void update(HashMap<String, Set<Feature>> map, String key,
					Set<Feature> colors) {
				if (colors != null && colors.size() > 0)
					map.put(key, colors);
				else
					map.remove(key);
			}

			public boolean visit(VariableDeclarationFragment node) {
				visitVD(node);
				return super.visit(node);
			}

			public boolean visit(SingleVariableDeclaration node) {
				visitVD(node);
				return super.visit(node);
			}

			public void visitVD(VariableDeclaration node) {
				String key = null;
				IVariableBinding binding = node.resolveBinding();
				if (binding != null) {
					IJavaElement javaElement = binding.getJavaElement();
					if (javaElement instanceof IField)
						key = ((IField) javaElement).getKey();
				}
				if (key != null)
					update(bindingKeys2colors, key, nodeColors.getColors(node));
			}

			@Override
			public boolean visit(TypeDeclaration node) {
				ITypeBinding binding = node.resolveBinding();
				if (binding != null) {
					update(bindingKeys2colors, binding.getKey(), nodeColors
							.getColors(node));

				}
				return super.visit(node);
			}
		});
	}

	private final static Set<Feature> NOCOLORS = Collections
			.unmodifiableSet(new HashSet<Feature>());

	Set<Feature> getColors(String bindingKey) {
		Set<Feature> colors = bindingKeys2colors.get(bindingKey);
		if (colors != null)
			return colors;
		return NOCOLORS;
	}

	Set<Feature> getColors(IMethodBinding method) {
		return getColors(method.getKey());
	}

	Set<Feature> getColors(IVariableBinding field) {
		return getColors(field.getKey());
	}

	// colors for a parameter
	Set<Feature> getColors(IMethodBinding method, int paramIdx) {
		return getColors(getParamKey(method.getKey(), paramIdx));
	}

	// colors for a parameter
	Set<Feature> getColors(String methodKey, int paramIdx) {
		return getColors(getParamKey(methodKey, paramIdx));
	}

	private String getParamKey(String methodKey, int paramIdx) {
		return methodKey + "/" + paramIdx;
	}

	Set<Feature> getColors(ITypeBinding type) {
		return getColors(type.getKey());

	}

}
