package de.ovgu.cide.jakutil;

public class ReturnInt extends RuntimeException {
	public ReturnInt(int v) {
		value = v;
	}

	public int value;
}
