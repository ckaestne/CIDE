package tmp.generated_manifest;

import java.util.*;
import cide.gast.*;

import java.io.PrintStream;

import cide.languages.*;

/** package visibility. use only via ASTNode.render() **/
public class SimplePrintVisitor extends AbstractPrintVisitor {
	public SimplePrintVisitor(PrintStream out) {
		super(out);
	}
	public SimplePrintVisitor() {
		super();
	}
	public boolean visit(IASTNode node) {
		if (node instanceof ASTStringNode){
			printToken(((ASTStringNode)node).getValue());
			return false;
		}
		if (node instanceof ASTTextNode){
			return false;
		}
		if (node instanceof File) {
			File n = (File)node;
			for (Line v : n.getLine()) {
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
		if (node instanceof Line1) {
			Line1 n = (Line1)node;
			{
				Header v=n.getHeader();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Line2) {
			Line2 n = (Line2)node;
			{
				Export v=n.getExport();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Header) {
			Header n = (Header)node;
			{
				ASTStringNode v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				ASTStringNode v=n.getValue();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (ASTStringNode v : n.getValue1()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Export) {
			Export n = (Export)node;
			Iterator<ASTStringNode> listElements = n.getPackagedcl().iterator();
			printToken("Export-Package: ");
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken(",");
				listElements.next().accept(this);
			}
			{
				ASTStringNode v=n.getPackageend();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		return true;
	}
}
