package coloredide.configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.ant.core.AntCorePlugin;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import coloredide.features.Feature;
import coloredide.features.FeatureManager;

/**
 * task to automate the generation of program variants from colored projects.
 * 
 * as input the source project name is needed
 * 
 * the target project name is also required, if the project already exists it is
 * removed!
 * 
 * finally, the feature selection is specified in a comma-separated list of
 * feature names (no additional spaces)
 * 
 * 
 * @author ckaestne
 * 
 */
public class ConfGenTask extends Task {

	private String inputProject;
	private String featureSelection;
	private String outputProject;

	public void execute() throws BuildException {

		try {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

			IProject sourceProject = root.getProject(inputProject);
			if (sourceProject == null)
				throw new BuildException("Source Project " + inputProject
						+ " not found.");

			List<Feature> visibleFeatures = FeatureManager
					.getVisibleFeatures(sourceProject);
			if (visibleFeatures.size() == 0)
				throw new BuildException("Source Project " + inputProject
						+ " does not contain any (visible) features.");
			System.out.println("Available Features:");
			for (Feature f : visibleFeatures)
				System.out.println("\t" + f.getShortName(sourceProject));

			String featureList = ("," + featureSelection + ",").toLowerCase();
			Set<Feature> features = new HashSet<Feature>();
			for (Feature f : visibleFeatures) {
				if (featureList.indexOf("," + f.getShortName(sourceProject).toLowerCase() + ",")>=0)
					features.add(f);
			}
			System.out.println("Generating configuration for features: ");
			for (Feature f : features)
				System.out.println("\t" + f.getShortName(sourceProject));
			if (features.size()==0) System.out.println("\t[NONE]");
			
			CreateConfigurationJob job = new CreateConfigurationJob(
					sourceProject, features, outputProject);
			IProgressMonitor monitor = (IProgressMonitor) getProject()
					.getReferences()
					.get(AntCorePlugin.ECLIPSE_PROGRESS_MONITOR);
			job.runInWorkspace(monitor);
		} catch (CoreException e) {
			throw new BuildException(e);
		}

		// Main m = new Main(grammarFileName.substring(0, grammarFileName
		// .lastIndexOf('.')));
		// try {
		// m.runGenerator(grammarFileName, new File(targetDirectory),
		// targetPackage);
		// } catch (Throwable e) {
		// e.printStackTrace();
		// throw new BuildException(e);
		// }
		System.out.println("input: " + inputProject + "; output: "
				+ outputProject + "; features:" + featureSelection);
	}

	public void setInputProject(String g) {
		this.inputProject = g;
	}

	public void setOutputProject(String td) {
		this.outputProject = td;
	}

	public void setFeatureSelection(String tp) {
		this.featureSelection = tp;
	}
}
