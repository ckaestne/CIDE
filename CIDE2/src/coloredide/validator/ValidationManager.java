package coloredide.validator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import cide.gast.ISourceFile;
import cide.gparser.ParseException;
import cide.greferences.IReference;
import cide.greferences.IReferenceType;
import cide.greferences.IValidationRule;
import cide.languages.ILanguageValidator;
import coloredide.ASTColorChangedEvent;
import coloredide.ColorListChangedEvent;
import coloredide.FileColorChangedEvent;
import coloredide.IColorChangeListener;
import coloredide.features.source.ColoredSourceFile;

import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

/**
 * singleton, access via ColoredIDEPlugin.getValidator()
 * 
 * @author cKaestner
 * 
 */
@SuppressWarnings("restriction")
public class ValidationManager extends DefaultErrorCreator implements
		IColorChangeListener {

	void runValidation(ColoredSourceFile source) throws CoreException {
		cleanProblems(source.getResource());
		// no color problems on uncolored files
		if (!source.isColored())
			return;

		ILanguageValidator validator = source.getLanguageExtension()
				.getValidator();
		// no color problems if no validator to check
		if (validator == null)
			return;
		IValidationRule[] rules = validator.getValidationRules();
		// no color problems if no rules to check
		if (rules.length == 0)
			return;

		Set<IReferenceType> requiredReferences = new HashSet<IReferenceType>();
		for (IValidationRule rule : rules)
			for (IReferenceType type : rule.getRequiredReferences())
				requiredReferences.add(type);
		// no color problems if no references to be checked
		if (requiredReferences.isEmpty())
			return;

		ISourceFile ast = null;
		try {
			ast = source.getAST();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assert ast != null : "no ast created, parser problem?";

		assert validator.getResolver() != null;
		ReferenceCollector referenceCollector = new ReferenceCollector(
				validator.getResolver(), requiredReferences);
		ast.accept(referenceCollector);

		HashMap<IReferenceType, Set<IReference>> references = referenceCollector
				.getReferences();

		for (IValidationRule rule : rules) {
//		TODO	rule.validate(source.getColorManager(), references, this);
		}

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
					problem.setMarkerAttributes(marker);
				}
			}
		};
		try {
			ResourcesPlugin.getWorkspace().run(operation, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	private void cleanProblems(IFile file2) {
		problems.clear();
		try {
			file2.deleteMarkers(ColorProblem.TYPEID, true,
					IResource.DEPTH_INFINITE);
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void astColorChanged(ASTColorChangedEvent event) {
		try {
			runValidation(event.getColoredSourceFile());
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	public void fileColorChanged(FileColorChangedEvent event) {
		// TODO update when coloring whole files or directories...
	}

	public void colorListChanged(ColorListChangedEvent event) {
		// TODO redo entire validation on structural changes
	}

}
