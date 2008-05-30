package de.ovgu.cide.jakutil;

public class ReturnBoolean extends RuntimeException {
	public ReturnBoolean(boolean v) {
		value = v;
	}

	public boolean value;
}
