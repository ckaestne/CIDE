package coloredide.export;

import org.eclipse.core.resources.IProject;

public abstract class ExportFactory {

	public abstract BaseExportJob createJob(IProject sourceProject,
			IProject targetProject);

	public abstract String getName();

}
