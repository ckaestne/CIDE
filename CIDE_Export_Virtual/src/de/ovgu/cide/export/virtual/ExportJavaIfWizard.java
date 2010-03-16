package de.ovgu.cide.export.virtual;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import de.ovgu.cide.export.BaseExportJob;
import de.ovgu.cide.export.CIDEExportTargetWizard;
import de.ovgu.cide.export.useroptions.IUserOptionProvider;
import de.ovgu.cide.export.virtual.internal.Export2PPJob;
import de.ovgu.cide.export.virtual.internal.JavaIfExportOptions;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeature;

public class ExportJavaIfWizard extends
		CIDEExportTargetWizard<IUserOptionProvider> {

	public ExportJavaIfWizard() {
		super(new JavaIfExportOptions());
	}

	@Override
	public BaseExportJob createExportJob(IProject sourceProject,
			IProject targetProject) {
		try {
			return new Export2PPJob("Exporting CIDE -> Java If", sourceProject,
					targetProject, new JavaIfExportOptions()) {
				protected void finishExport(IProgressMonitor monitor)
						throws CoreException {
					String fileContent = "public class SPLConfiguration { \n";
					for (IFeature f : features)
						fileContent += "\tpublic static final boolean "
								+ f.getName().toUpperCase() + " = false;\n";
					fileContent += "}";
					InputStream fileContentStream = new ByteArrayInputStream(
							fileContent.getBytes());
					createFile(targetProject.getFolder("java").getFile(
							"SPLConfiguration.java"), fileContentStream, monitor);
				}
			};
		} catch (FeatureModelNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}
