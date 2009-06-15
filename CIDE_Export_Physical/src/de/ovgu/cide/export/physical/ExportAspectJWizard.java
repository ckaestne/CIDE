package de.ovgu.cide.export.physical;

import org.eclipse.core.resources.IProject;

import de.ovgu.cide.export.BaseExportJob;
import de.ovgu.cide.export.CIDEExportWizard;
import de.ovgu.cide.export.physical.aspectj.Export2AspectJJob;
import de.ovgu.cide.export.physical.internal.AspectJOptions;
import de.ovgu.cide.export.physical.internal.IPhysicalOptions;
import de.ovgu.cide.features.FeatureModelNotFoundException;

public class ExportAspectJWizard extends CIDEExportWizard<IPhysicalOptions> {

	public ExportAspectJWizard() {
		super(new AspectJOptions());
	}

	@Override
	protected BaseExportJob createExportJob(IProject sourceProject,
			IProject targetProject) {
		try {
			return new Export2AspectJJob("Exporting CIDE -> AspectJ",
					sourceProject, targetProject,options);
		} catch (FeatureModelNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}
