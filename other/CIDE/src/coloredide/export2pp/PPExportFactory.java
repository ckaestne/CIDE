package coloredide.export2pp;

import org.eclipse.core.resources.IProject;

import coloredide.export.BaseExportJob;
import coloredide.export.ExportFactory;

public class PPExportFactory extends ExportFactory {

	public BaseExportJob createJob(IProject sourceProject,
			IProject targetProject) {
		return new Export2PPJob("Creating Preprocessor/Java Project", sourceProject,
				targetProject);
	}

	public String getName() {
		return "Preprocessor";
	}

	
}
