package tmp.generated_property;

import java.util.*;
import cide.gast.*;

import java.io.PrintStream;

import cide.languages.*;

public class SimplePrintVisitor extends AbstractPrintVisitor implements ILanguagePrintVisitor {
	public SimplePrintVisitor(PrintStream out) {
		super(out);
	}
	public SimplePrintVisitor() {
		super();
	}
	public boolean visit(ASTNode node) {
		if (node instanceof ASTStringNode){
			printToken(((ASTStringNode)node).getValue());
			return false;
		}
		if (node instanceof ASTTextNode){
			return false;
		}
		if (node instanceof Document) {
			Document n = (Document)node;
			for (ASTStringNode v : n.getLine()) {
				v.accept(this);
			}
			{
				ASTStringNode v=n.getEof();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		return true;
	}
}
