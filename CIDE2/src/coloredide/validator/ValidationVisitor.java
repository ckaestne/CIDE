//package coloredide.validator;
//
//import org.eclipse.core.resources.IProject;
//import org.eclipse.jdt.core.dom.ASTVisitor;
//
//import coloredide.ColoredIDEPlugin;
//import coloredide.features.bindings.BindingColorCache;
//import coloredide.features.source.ColoredJavaSourceFile;
//import coloredide.features.source.CompilationUnitColorManager;
//
//public abstract class ValidationVisitor extends ASTVisitor {
//	protected ValidationErrorCallback callback;
//
//	protected ColoredJavaSourceFile source;
//
//	public ValidationVisitor(ValidationErrorCallback callback,
//			ColoredJavaSourceFile source) {
//		this.callback = callback;
//		this.source = source;
//	}
//
//	protected CompilationUnitColorManager nodeColors() {
//		return source.getColorManager();
//	}
//
//	protected BindingColorCache javaElementColors() {
//		return ColoredIDEPlugin.getDefault().colorCache;
//	}
//
//	protected IProject project() {
//		return source.getCompilationUnit().getJavaProject().getProject();
//	}
//}
