package de.ovgu.cide.export.useroptions;

public abstract class AbstractUserOption implements IUserOption {

	private String name;

	public AbstractUserOption(String name) {
		this.name = name;

	}

	public String getName() {
		return name;
	}

}
