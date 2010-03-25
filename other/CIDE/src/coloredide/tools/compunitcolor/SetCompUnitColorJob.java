package coloredide.tools.compunitcolor;

import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import coloredide.features.Feature;
import coloredide.features.FeatureManager;
import coloredide.features.source.ColoredJavaSourceFile;
import coloredide.features.source.IColoredJavaSourceFile;

public class SetCompUnitColorJob extends WorkspaceJob {

	private ICompilationUnit[] compUnits;

	private Set<Feature> features;

	private IProject project;

	public SetCompUnitColorJob(ICompilationUnit[] compunits,
			Set<Feature> feature, IProject project) {
		super("Set Compilation Unit Colors");
		this.compUnits = compunits;
		this.features = feature;
		this.project = project;
	}


	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor)
			throws CoreException {

		monitor.beginTask("coloring", compUnits.length);
		
		for (ICompilationUnit cu : compUnits) {
			IColoredJavaSourceFile source = ColoredJavaSourceFile
					.getColoredJavaSourceFile(cu);
			CompilationUnit root = source.getAST();
			source.getColorManager().beginBatch();
			for (Feature f : FeatureManager.getVisibleFeatures(project)) {
				source.getColorManager().removeColor(root, f);
			}
			for (Feature f : features) {
				source.getColorManager().addColor(root, f);
			}
			source.getColorManager().endBatch();
			monitor.worked(1);
		}
		monitor.done();

		return Status.OK_STATUS;
	}

}
