package de.ovgu.cide.export.useroptions;

import java.util.List;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;

public class UserOptionsPage extends WizardPage implements IWizardPage {

	private List<IUserOption> options;

	public UserOptionsPage(List<IUserOption> userOptions) {
		super("Export Options");
		this.options = userOptions;
		setTitle("Export Options");
	}

	// TODO layout
	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		setControl(composite);
		composite.setLayout(new RowLayout(SWT.VERTICAL));

		for (IUserOption uo : options)
			uo.createUI(composite);
	}

}
