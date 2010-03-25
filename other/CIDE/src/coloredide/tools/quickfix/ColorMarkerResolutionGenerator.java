package coloredide.tools.quickfix;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.CorrectionEngine;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaModelMarker;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.internal.ui.text.correction.AssistContext;
import org.eclipse.jdt.internal.ui.text.correction.ProblemLocation;
import org.eclipse.jdt.ui.text.java.IInvocationContext;
import org.eclipse.jdt.ui.text.java.IProblemLocation;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator;

import coloredide.features.source.ColoredJavaSourceFile;
import coloredide.features.source.IColoredJavaSourceFile;
import coloredide.utils.EditorUtility;
import coloredide.validator.ColorProblem;
import coloredide.validator.ColorProblem.Kind;

@SuppressWarnings("restriction")
public class ColorMarkerResolutionGenerator implements
		IMarkerResolutionGenerator {

	private static final IMarkerResolution[] NO_RESOLUTIONS = new IMarkerResolution[0];

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IMarkerResolutionGenerator2#hasResolutions(org.eclipse.core.resources.IMarker)
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see IMarkerResolutionGenerator#getResolutions(IMarker)
	 */
	public IMarkerResolution[] getResolutions(IMarker marker) {
		return internalGetResolutions(marker);
	}

	private static IMarkerResolution[] internalGetResolutions(IMarker marker) {
		Kind kind = getColorProblemKind(marker);
		if (kind == null)
			return NO_RESOLUTIONS;
		if (kind == ColorProblem.OTHER)
			return NO_RESOLUTIONS;

		try {
			ICompilationUnit cu = getCompilationUnit(marker);
			if (cu != null) {
				IEditorInput input = EditorUtility.getEditorInput(cu);
				if (input != null) {
					IProblemLocation location = findProblemLocation(input,
							marker);
					if (location != null) {
						List<IMarkerResolution> resolutions = new ArrayList<IMarkerResolution>();
						IInvocationContext context = new AssistContext(cu,
								location.getOffset(), location.getLength());
						ASTNode node = findNode(context, getASTId(marker));
						IColoredJavaSourceFile source = ColoredJavaSourceFile.getColoredJavaSourceFile(context.getCompilationUnit());
						if (node == null||source==null)
							return NO_RESOLUTIONS;

						if (kind == ColorProblem.METHODCALL) {
							resolutions.addAll(MethodMissmatchResolutionGenerator.getResolutions(node, source));
						}
						if (kind == ColorProblem.FIELDACCESS) {
							resolutions.addAll(FieldMissmatchResolutionGenerator.getResolutions(node, source));
						}
						if (kind == ColorProblem.IMPORT) {
							resolutions.add(new OrganizeImportColorsResolution(source));
						}

						return resolutions
								.toArray(new IMarkerResolution[resolutions
										.size()]);
					}
				}
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		return NO_RESOLUTIONS;
	}

	public static ASTNode findNode(IInvocationContext context, String id) {
		CompilationUnit root = context.getASTRoot();
		if (root == null)
			return null;
		ASTIDFinder astIDFinder = new ASTIDFinder(id);
		root.accept(astIDFinder);
		return astIDFinder.getResult();
	}

	public static String getASTId(IMarker marker) {
		if (marker == null)
			return null;
		String id = marker.getAttribute(ColorProblem.PARAM_PROBLEMDATA, "");
		if (id.length() <= 0)
			return null;
		return id;
	}

	private static Kind getColorProblemKind(IMarker marker) {
		if (marker == null)
			return null;
		int colorProblemKindId = marker.getAttribute(
				ColorProblem.PARAM_PROBLEMTYPE, 0);
		for (Kind kind : ColorProblem.getKinds()) {
			if (kind.getID() == colorProblemKindId)
				return kind;
		}
		return null;
	}

	public static ICompilationUnit getCompilationUnit(IMarker marker) {
		IResource res = marker.getResource();
		if (res instanceof IFile && res.isAccessible()) {
			IJavaElement element = JavaCore.create((IFile) res);
			if (element instanceof ICompilationUnit)
				return (ICompilationUnit) element;
		}
		return null;
	}

	public static IProblemLocation findProblemLocation(IEditorInput input,
			IMarker marker) {

		ICompilationUnit cu = getCompilationUnit(marker);
		return createFromMarker(marker, cu);
	}

	private static IProblemLocation createFromMarker(IMarker marker,
			ICompilationUnit cu) {
		try {
			int id = marker.getAttribute(IJavaModelMarker.ID, -1);
			int start = marker.getAttribute(IMarker.CHAR_START, -1);
			int end = marker.getAttribute(IMarker.CHAR_END, -1);
			int severity = marker.getAttribute(IMarker.SEVERITY,
					IMarker.SEVERITY_INFO);
			String[] arguments = CorrectionEngine.getProblemArguments(marker);
			String markerType = marker.getType();
			if (cu != null && id != -1 && start != -1 && end != -1
					&& arguments != null) {
				boolean isError = (severity == IMarker.SEVERITY_ERROR);
				return new ProblemLocation(start, end - start, id, arguments,
						isError, markerType);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return null;
	}

}
