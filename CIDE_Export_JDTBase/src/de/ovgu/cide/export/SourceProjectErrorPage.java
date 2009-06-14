package de.ovgu.cide.export;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

/**
 * todo: should be replaced by a select-project dialog
 * 
 * @author ckaestne
 * 
 */
public class SourceProjectErrorPage extends WizardPage {

	private IProject[] source;

	public SourceProjectErrorPage(IProject[] sourceProject) {
		super("Error");
		this.source = sourceProject;
	}

	@Override
	public void createControl(Composite parent) {

		setTitle("Cannot export");
		if (source == null || source.length == 0)
			setErrorMessage("Select a project to export");
		else if (source.length > 1)
			setErrorMessage("Select only a single project to export");
		else
			assert false;
		setPageComplete(false);
		setControl(parent);
	}
}
