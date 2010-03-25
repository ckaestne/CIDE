package coloredide.tools.compunitcolor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import coloredide.features.Feature;
import coloredide.utils.SelectFeatureWizard;

public class SetCompUnitColorAction implements IWorkbenchWindowActionDelegate,
		IObjectActionDelegate {

	private final List<ICompilationUnit> compUnits = new ArrayList<ICompilationUnit>();

	protected IWorkbenchPart part;

	/**
	 * The action has been activated. The argument of the method represents the
	 * 'real' action sitting in the workbench UI.
	 * 
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		ICompilationUnit[] p;

		if (!compUnits.isEmpty()) {
			IProject project = compUnits.get(0).getResource().getProject();
			SelectFeatureWizard wizard = new SelectFeatureWizard(project, null);
			WizardDialog dialog = new WizardDialog(new Shell(), wizard);
			dialog.create();
			dialog.open();
			Set<Feature> feature = wizard.getSelectedFeatures();
			p = compUnits.toArray(new ICompilationUnit[compUnits.size()]);
			WorkspaceJob op = new SetCompUnitColorJob(p, feature, project);
			op.setUser(true);
			op.schedule();
		}
		
	   
	}

	public void selectionChanged(IAction action, ISelection selection) {
		compUnits.clear();
		if (selection instanceof IStructuredSelection) {
			for (Object selected : ((IStructuredSelection) selection).toArray()) {
				if (selected instanceof ICompilationUnit) {
					compUnits.add((ICompilationUnit) selected);
				}

			}
		}
	}

	public void dispose() {
	}

	public void init(IWorkbenchWindow window) {
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.part = targetPart;
	}

}
