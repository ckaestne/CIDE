package coloredide.tools.quickfix;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.ui.IMarkerResolution;

import coloredide.ColoredIDEPlugin;
import coloredide.features.Feature;
import coloredide.features.bindings.BindingColorCache;
import coloredide.features.source.ColoredJavaSourceFile;
import coloredide.features.source.IColorManager;
import coloredide.features.source.IColoredJavaSourceFile;

public class MethodMissmatchResolutionGenerator {

	protected static final List<IMarkerResolution> NO_RESOLUTIONS = new ArrayList<IMarkerResolution>();

	public static List<IMarkerResolution> getResolutions(ASTNode node,
			IColoredJavaSourceFile source) {
		if (node instanceof MethodInvocation) {
			IMethodBinding binding = ((MethodInvocation) node)
					.resolveMethodBinding();
			if (binding == null) {
				return NO_RESOLUTIONS;
			}

			Set<Feature> declColors = javaElementColors().getColors(
					project(source), binding);
			Set<Feature> callColors = nodeColors(source).getColors(node);

			HashSet<Feature> colorDiff = new HashSet<Feature>();
			colorDiff.addAll(declColors);
			colorDiff.removeAll(callColors);
			if (colorDiff.isEmpty())
				return NO_RESOLUTIONS;

			ArrayList<IMarkerResolution> resolutions = new ArrayList<IMarkerResolution>();
			resolutions.addAll(createChangeNodeColorResolution(source, node,
					colorDiff, true, "method call", 20));
			resolutions.addAll(createChangeNodeColorResolution(source,
					findCallingMethod(node), colorDiff, true, "calling method",
					18));
			resolutions
					.addAll(createChangeNodeColorResolution(source,
							findCallingType(node), colorDiff, true,
							"calling type", 16));
			ASTNode methodDecl = getMethodDecl(binding);
			if (methodDecl instanceof MethodDeclaration)
				resolutions.addAll(createChangeNodeColorResolution(
						getSource(binding), methodDecl, colorDiff, false,
						"method declaration", 14));

			return resolutions;
		}
		return NO_RESOLUTIONS;
	}

	private static IColoredJavaSourceFile getSource(IBinding binding) {
		IJavaElement element = binding.getJavaElement();
		while (element != null && !(element instanceof ICompilationUnit))
			element = element.getParent();
		if (element == null)
			return null;
		ICompilationUnit compUnit = (ICompilationUnit) element;
		return ColoredJavaSourceFile.getColoredJavaSourceFile(compUnit);
	}

	private static List<IMarkerResolution> createChangeNodeColorResolution(
			IColoredJavaSourceFile source, ASTNode node,
			HashSet<Feature> colorDiff, boolean add, String string,
			int relevance) {

		if (node == null)
			return NO_RESOLUTIONS;
		ArrayList<IMarkerResolution> resolutions = new ArrayList<IMarkerResolution>();

		if (add) {
			if (colorDiff.size() > 1)
				for (Feature color : colorDiff) {

					resolutions.add(new ChangeNodeColorResolution(source, node,
							colorSet(color), add, string, relevance));
				}
			resolutions.add(new ChangeNodeColorResolution(source, node,
					colorDiff, add, string, relevance + 1));
		}

		if (!add) {
			// find declaring node
			for (Feature color : colorDiff) {
				ASTNode currentNode = node;
				while (currentNode != null
						&& !source.getColorManager().hasColor(currentNode,
								color)) {
					currentNode = currentNode.getParent();
				}

				if (source.getColorManager().hasColor(currentNode, color))
					resolutions.add(new ChangeNodeColorResolution(source,
							currentNode, colorSet(color), add,
							node == currentNode ? string : currentNode
									.getClass().getSimpleName(), relevance));
			}
		}

		return resolutions;
	}

	private static Set<Feature> colorSet(Feature color) {
		HashSet<Feature> colors = new HashSet<Feature>();
		colors.add(color);
		return colors;
	}

	private static ASTNode getMethodDecl(IBinding binding) {
		IColoredJavaSourceFile source = getSource(binding);
		if (source == null)
			return null;

		ASTBindingFinder bindingFinder = new ASTBindingFinder(binding.getKey());
		try {
			source.getAST().accept(bindingFinder);
		} catch (JavaModelException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return bindingFinder.getResult();
	}

	protected static IColorManager nodeColors(
			IColoredJavaSourceFile source) {
		return source.getColorManager();
	}

	protected static BindingColorCache javaElementColors() {
		return ColoredIDEPlugin.getDefault().colorCache;
	}

	protected static IProject project(IColoredJavaSourceFile source) {
		return source.getCompilationUnit().getJavaProject().getProject();
	}

	protected static TypeDeclaration findCallingType(ASTNode node) {
		while (node != null && !(node instanceof TypeDeclaration))
			node = node.getParent();
		return (TypeDeclaration) node;
	}

	protected static MethodDeclaration findCallingMethod(ASTNode node) {
		while (node != null && !(node instanceof MethodDeclaration))
			node = node.getParent();
		return (MethodDeclaration) node;
	}
}
