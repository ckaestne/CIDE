package de.ovgu.cide.fm.purevariants;

import org.eclipse.swt.widgets.Shell;

import com.ps.consul.eclipse.core.model.IPVElement;
import com.ps.consul.eclipse.ui.dialogs.ICustomElementEditor;

public class ColorEditor implements ICustomElementEditor {

	Shell m_Shell;
	IPVElement m_Parent, m_Element;


	public void init(Shell parentShell, IPVElement parent, IPVElement element) {
		m_Shell = parentShell;
		m_Parent = parent;
		m_Element = element;

	}


	public int open() {
		ColorDialog dia = new ColorDialog(m_Shell, m_Parent, m_Element);

		return dia.open();
	}
}
