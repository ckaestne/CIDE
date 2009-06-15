package de.ovgu.cide.export.useroptions;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class StringUserOption extends AbstractUserOption implements IUserOption {

	private String value;

	public StringUserOption(String name, String defaultValue) {
		super(name);
		this.value = defaultValue;
	}

	public String getValue() {
		return value;
	}

	@Override
	public void createUI(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		Label label = new Label(composite, SWT.NONE);
		label.setText(getName());
		final Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
		text.setText(getValue());
		text.setEnabled(true);
		text.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				value = text.getText();
			}
		});
	}

}
