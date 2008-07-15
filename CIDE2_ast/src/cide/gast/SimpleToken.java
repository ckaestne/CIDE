package cide.gast;


public class SimpleToken implements IToken {

	private int offset;
	private int length;

	public SimpleToken(int offset, int length) {
		this.offset = offset;
		this.length = length;
	}

	public int getLength() {
		return length;
	}

	public int getOffset() {
		return offset;
	}
}
