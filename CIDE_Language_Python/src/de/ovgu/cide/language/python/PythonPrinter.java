package de.ovgu.cide.language.python;

import java.io.PrintStream;

import tmp.generated_python.SimplePrintVisitor;

public class PythonPrinter extends SimplePrintVisitor {

	public PythonPrinter() {
	}

	@Override
	protected void printToken(String token) {
		if (token.equals("\n"))
			hintNewLine();
		else if (!(token.trim().equals("")||token.equals("<EOF>")))
			super.printToken(token);
	}

}
