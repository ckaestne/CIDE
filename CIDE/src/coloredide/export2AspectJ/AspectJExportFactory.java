package coloredide.export2AspectJ;

import org.eclipse.core.resources.IProject;

import coloredide.export.BaseExportJob;
import coloredide.export.ExportFactory;

public class AspectJExportFactory extends ExportFactory {

	public BaseExportJob createJob(IProject sourceProject,
			IProject targetProject) {
		return new Export2AspectJJob("Creating AspectJ Project", sourceProject,
				targetProject);
	}

	public String getName() {
		return "AspectJ";
	}

}
