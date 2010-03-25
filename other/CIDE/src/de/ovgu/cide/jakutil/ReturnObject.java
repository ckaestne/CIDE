package de.ovgu.cide.jakutil;

public class ReturnObject extends RuntimeException {
	public ReturnObject(Object v) {
		value=v;
	}

	public Object value;
}
