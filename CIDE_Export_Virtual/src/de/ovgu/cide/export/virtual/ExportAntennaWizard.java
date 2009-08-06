package de.ovgu.cide.export.virtual;

import org.eclipse.core.resources.IProject;

import de.ovgu.cide.export.BaseExportJob;
import de.ovgu.cide.export.CIDEExportTargetWizard;
import de.ovgu.cide.export.virtual.internal.AntennaExportOptions;
import de.ovgu.cide.export.virtual.internal.Export2PPJob;
import de.ovgu.cide.export.virtual.internal.IPPExportOptions;
import de.ovgu.cide.features.FeatureModelNotFoundException;

public class ExportAntennaWizard extends CIDEExportTargetWizard<IPPExportOptions>{

	public ExportAntennaWizard() {
		super(new AntennaExportOptions());
	}

	@Override
	protected BaseExportJob createExportJob(IProject sourceProject,
			IProject targetProject) {
		try {
			return new Export2PPJob("Exporting CIDE -> Antenna", sourceProject,
					targetProject, options);
		} catch (FeatureModelNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}
