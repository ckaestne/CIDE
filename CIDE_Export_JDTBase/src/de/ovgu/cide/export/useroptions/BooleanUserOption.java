package de.ovgu.cide.export.useroptions;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class BooleanUserOption extends AbstractUserOption implements
		IUserOption {

	private boolean value;

	public BooleanUserOption(String name, boolean defaultValue) {
		super(name);
		this.value = defaultValue;
	}

	public boolean getValue() {
		return value;
	}

	public void createUI(Composite parent) {
		final Button checkbox = new Button(parent, SWT.CHECK);
		checkbox.setText(getName());
		checkbox.setSelection(value);
		checkbox.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				value = checkbox.getSelection();
			}

			public void widgetSelected(SelectionEvent e) {
				value = checkbox.getSelection();
			}
		});
	}

}
