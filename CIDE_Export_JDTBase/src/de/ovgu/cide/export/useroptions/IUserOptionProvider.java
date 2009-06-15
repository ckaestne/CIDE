package de.ovgu.cide.export.useroptions;

import java.util.List;

public interface IUserOptionProvider {
	/**
	 * a list of options that can be changed by the user in a separate dialog
	 * 
	 * @return
	 */
	List<IUserOption> getUserOptions();
}
