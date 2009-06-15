package de.ovgu.cide.export.virtual;

import org.eclipse.core.resources.IProject;

import de.ovgu.cide.export.BaseExportJob;
import de.ovgu.cide.export.CIDEExportWizard;
import de.ovgu.cide.export.virtual.internal.Export2PPJob;
import de.ovgu.cide.export.virtual.internal.IPPExportOptions;
import de.ovgu.cide.export.virtual.internal.MungeExportOptions;
import de.ovgu.cide.features.FeatureModelNotFoundException;

public class ExportMungeWizard extends CIDEExportWizard<IPPExportOptions> {

	public ExportMungeWizard() {
		super(new MungeExportOptions());
	}

	@Override
	protected BaseExportJob createExportJob(IProject sourceProject,
			IProject targetProject) {
		try {
			return new Export2PPJob("Exporting CIDE -> Munge", sourceProject,
					targetProject, options);
		} catch (FeatureModelNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}
