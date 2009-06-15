package de.ovgu.cide.export.useroptions;

import org.eclipse.swt.widgets.Composite;

public interface IUserOption {

	/**
	 * create a composite with buttons, fields or whatever to represent and
	 * modify this field
	 * 
	 * @param composite
	 */
	void createUI(Composite composite);

}
