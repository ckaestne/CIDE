/**
    Copyright 2010 Christian Kästner

    This file is part of CIDE.

    CIDE is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.

    CIDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CIDE.  If not, see <http://www.gnu.org/licenses/>.

    See http://www.fosd.de/cide/ for further information.
*/

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
