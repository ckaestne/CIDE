package de.ovgu.cide.typing.jdt.checks.util;

public class InterfacePathItem {
	
	final private String key;
	final private boolean isAbstract;
	
	public InterfacePathItem(String key, boolean isAbstract) {
		this.key = key;
		this.isAbstract = isAbstract;
	}

	public String getKey() {
		return key;
	}

	public boolean isAbstract() {
		return isAbstract;
	}

}
