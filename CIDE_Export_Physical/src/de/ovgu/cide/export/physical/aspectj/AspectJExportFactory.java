package de.ovgu.cide.export.physical.aspectj;

import org.eclipse.core.resources.IProject;

import de.ovgu.cide.export.BaseExportJob;

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
