package coloredide.validator;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.dom.ASTVisitor;

import coloredide.ColoredIDEPlugin;
import coloredide.features.bindings.BindingColorCache;
import coloredide.features.source.IColorManager;
import coloredide.features.source.IColoredJavaSourceFile;

public abstract class ValidationVisitor extends ASTVisitor {
	protected ValidationErrorCallback callback;

	protected IColoredJavaSourceFile source;

	public ValidationVisitor(ValidationErrorCallback callback,
			IColoredJavaSourceFile source) {
		this.callback = callback;
		this.source = source;
	}

	protected IColorManager nodeColors() {
		return source.getColorManager();
	}

	protected BindingColorCache javaElementColors() {
		return ColoredIDEPlugin.getDefault().colorCache;
	}

	protected IProject project() {
		return source.getCompilationUnit().getJavaProject().getProject();
	}
}
