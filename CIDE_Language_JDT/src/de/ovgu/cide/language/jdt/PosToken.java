package de.ovgu.cide.language.jdt;

import cide.gast.IToken;

public class PosToken implements IToken {

	private int pos;

	PosToken(int pos){
		this.pos=pos;
	}
	
	public int getLength() {
		return 0;
	}

	public int getOffset() {
		return pos;
	}

}
