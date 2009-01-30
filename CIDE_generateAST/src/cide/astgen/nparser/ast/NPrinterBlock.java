package cide.astgen.nparser.ast;

import cide.astgen.nparser.parser.Token;

public class NPrinterBlock {
	public NPrinterBlock() {
		printerClass = "SimplePrintVisitor";
		printerPackage = null;
	}

	public NPrinterBlock(Token node, Token pkg) {
		printerClass = node.image.substring(1, node.image.length() - 1);
		if (pkg != null)
			printerPackage = pkg.image.substring(1, pkg.image.length() - 1);
		else
			printerPackage = null;
	}

	public final String printerClass;
	public final String printerPackage;
}
