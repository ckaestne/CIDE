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

	public void createUI(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		Label label = new Label(composite, SWT.NONE);
		label.setText(getName());
		final Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
		text.setText(getValue());
		text.setEnabled(true);
		text.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				value = text.getText();
			}
		});
	}

}
