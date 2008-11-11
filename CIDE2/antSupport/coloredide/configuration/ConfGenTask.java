package coloredide.configuration;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.ant.core.AntCorePlugin;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import de.ovgu.cide.configuration.CreateConfigurationJob;
import de.ovgu.cide.features.FeatureModelManager;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;

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
			IFeatureModel featureModel;
			try {
				featureModel = FeatureModelManager.getInstance()
						.getFeatureModel(sourceProject);
			} catch (FeatureModelNotFoundException e) {
				throw new BuildException("Feature Model for project "
						+ inputProject + " not found.", e);
			}

			Collection<IFeature> visibleFeatures = featureModel
					.getVisibleFeatures();
			if (visibleFeatures.size() == 0)
				throw new BuildException("Source Project " + inputProject
						+ " does not contain any (visible) features.");
			System.out.println("Available Features:");
			for (IFeature f : visibleFeatures)
				System.out.println("\t" + f.getName());

			String featureList = ("," + featureSelection + ",").toLowerCase();
			Set<IFeature> features = new HashSet<IFeature>();
			for (IFeature f : visibleFeatures) {
				if (featureList.indexOf("," + f.getName().toLowerCase() + ",") >= 0)
					features.add(f);
			}
			System.out.println("Generating configuration for features: ");
			for (IFeature f : features)
				System.out.println("\t" + f.getName());
			if (features.size() == 0)
				System.out.println("\t[NONE]");

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
