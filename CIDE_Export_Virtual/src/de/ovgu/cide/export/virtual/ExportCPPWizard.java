package de.ovgu.cide.export.virtual;

import org.eclipse.core.resources.IProject;

import de.ovgu.cide.export.BaseExportJob;
import de.ovgu.cide.export.CIDEExportTargetWizard;
import de.ovgu.cide.export.virtual.internal.CPPExportOptions;
import de.ovgu.cide.export.virtual.internal.Export2PPJob;
import de.ovgu.cide.export.virtual.internal.IPPExportOptions;
import de.ovgu.cide.features.FeatureModelNotFoundException;

public class ExportCPPWizard extends CIDEExportTargetWizard<IPPExportOptions>{

	public ExportCPPWizard() {
		super(new CPPExportOptions());
	}

	@Override
	protected BaseExportJob createExportJob(IProject sourceProject,
			IProject targetProject) {
		try {
			return new Export2PPJob("Exporting CIDE -> CPP", sourceProject,
					targetProject, options);
		} catch (FeatureModelNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}
