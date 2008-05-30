package cide.gast;

/**
 * artificial token to be used for ASTNodes that represent no tokens at all
 * (e.g., empty sequence)
 * 
 * @author ckaestne
 * 
 */
public class NoToken implements IToken {

	private int offset;

	public NoToken(int offset) {
		this.offset = offset;
	}

	public int getLength() {
		return 0;
	}

	public int getOffset() {
		return offset;
	}

}
