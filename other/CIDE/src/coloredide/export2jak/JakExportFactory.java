package coloredide.export2jak;

import org.eclipse.core.resources.IProject;

import coloredide.export.BaseExportJob;
import coloredide.export.ExportFactory;

public class JakExportFactory extends ExportFactory {

	public BaseExportJob createJob(IProject sourceProject,
			IProject targetProject) {
		return new Export2JakJob("Creating AHEAD Project", sourceProject,
				targetProject);
	}

	public String getName() {
		return "Jak";
	}

	
}
