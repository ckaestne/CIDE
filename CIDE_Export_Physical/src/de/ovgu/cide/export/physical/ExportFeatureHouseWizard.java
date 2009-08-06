package de.ovgu.cide.export.physical;

import org.eclipse.core.resources.IProject;

import de.ovgu.cide.export.BaseExportJob;
import de.ovgu.cide.export.CIDEExportTargetWizard;
import de.ovgu.cide.export.physical.ahead.Export2JakJob;
import de.ovgu.cide.export.physical.internal.FeatureHouseOptions;
import de.ovgu.cide.export.physical.internal.IPhysicalOptions;
import de.ovgu.cide.features.FeatureModelNotFoundException;

public class ExportFeatureHouseWizard extends
CIDEExportTargetWizard<IPhysicalOptions> {
	public ExportFeatureHouseWizard() {
		super(new FeatureHouseOptions());
	}

	@Override
	protected BaseExportJob createExportJob(IProject sourceProject,
			IProject targetProject) {
		try {
			return new Export2JakJob("Exporting CIDE -> FeatureHouse (Java)",
					sourceProject, targetProject, options);
		} catch (FeatureModelNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}
