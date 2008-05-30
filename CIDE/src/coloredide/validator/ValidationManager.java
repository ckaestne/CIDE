package coloredide.validator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.internal.core.util.Util;

import coloredide.ColorChangedEvent;
import coloredide.IColorChangeListener;
import coloredide.features.source.ColoredJavaSourceFile;
import coloredide.features.source.IColoredJavaSourceFile;
import coloredide.validator.checks.FieldAccessValidator;
import coloredide.validator.checks.ImportValidator;
import coloredide.validator.checks.LocalVariableValidator;
import coloredide.validator.checks.MethodCallValidator;
import coloredide.validator.checks.MethodParamValidator;
import coloredide.validator.checks.OverridingValidator;
import coloredide.validator.checks.TypeValidator;

@SuppressWarnings("restriction")
public class ValidationManager extends DefaultErrorCreator implements
		IColorChangeListener {

	public ValidationManager() {
		super(null);
	}

	protected void runValidation(IColoredJavaSourceFile source)
			throws JavaModelException, CoreException {
		project=source.getProject();
		CompilationUnit ast = source.getAST();
		cleanProblems(ast);
		ast.accept(new MethodCallValidator(this, source));
		ast.accept(new FieldAccessValidator(this, source));
		ast.accept(new LocalVariableValidator(this, source));
		ast.accept(new OverridingValidator(this, source));
		ast.accept(new MethodParamValidator(this, source));
		ast.accept(new TypeValidator(this, source));
		ast.accept(new ImportValidator(this, source));
		createMarkers();
	}

	private void createMarkers() {
		IWorkspaceRunnable operation = new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				for (int i = 0, l = problems.size(); i < l; i++) {
					ColorProblem problem = problems.get(i);
					IResource resource = problem.resource;

					IMarker marker = resource.createMarker(problem
							.getMarkerType());

					// standard attributes
					marker.setAttributes(ColorProblem.ATTRIBUTE_NAMES,
						new Object[] {
									problem.getMessage(), // message
									new Integer(IMarker.SEVERITY_ERROR), // severity
									new Integer(problem.getID()), // ID
									new Integer(problem.getSourceStart()), // start
									new Integer(problem.getSourceEnd() + 1), // end
									new Integer(problem.getSourceLineNumber()), // line
									Util.getProblemArgumentsForMarker(problem
											.getArguments()), // arguments
									new Integer(problem.getCategoryID()), // categoryID
									new Integer(problem.getKind().getID()),
									problem.getData()
							});
					// optional extra attributes
					String[] extraAttributeNames = problem
							.getExtraMarkerAttributeNames();
					int extraLength = extraAttributeNames == null ? 0
							: extraAttributeNames.length;
					if (extraLength > 0) {
						marker.setAttributes(extraAttributeNames, problem
								.getExtraMarkerAttributeValues());
					}
				}
			}
		};
		try {
			ResourcesPlugin.getWorkspace().run(operation, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	private void cleanProblems(CompilationUnit ast) {
		problems.clear();
		IFile file = ColoredJavaSourceFile.getResource(ast);
		try {
			file.deleteMarkers(ColorProblem.TYPEID, true,
					IResource.DEPTH_INFINITE);
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void colorChanged(ColorChangedEvent event) {
		try {
			runValidation(event.getColoredJavaSourceFile());
		} catch (JavaModelException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

}
