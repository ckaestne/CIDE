package tmp.generated_xml;

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
		if (node instanceof Document) {
			Document n = (Document)node;
			{
				Prolog v=n.getProlog();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Element v=n.getElement();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (Misc v : n.getMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Misc1) {
			Misc1 n = (Misc1)node;
			{
				Comment v=n.getComment();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Misc2) {
			Misc2 n = (Misc2)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Misc3) {
			Misc3 n = (Misc3)node;
			{
				PI v=n.getPI();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PI) {
			PI n = (PI)node;
			printToken("<?");
			{
				ASTStringNode v=n.getPi_end();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Prolog) {
			Prolog n = (Prolog)node;
			{
				XMLDecl v=n.getXMLDecl();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (Misc v : n.getMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof XMLDecl) {
			XMLDecl n = (XMLDecl)node;
			printToken("<?xml");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("?>");
			return false;
		}
		if (node instanceof CDSect) {
			CDSect n = (CDSect)node;
			printToken("<![CDATA[");
			{
				ASTStringNode v=n.getCdend();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Comment) {
			Comment n = (Comment)node;
			printToken("<!--");
			{
				ASTStringNode v=n.getComment_end();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element1) {
			Element1 n = (Element1)node;
			{
				EmptyElemTag v=n.getEmptyElemTag();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element2) {
			Element2 n = (Element2)node;
			{
				STag v=n.getSTag();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (Content v : n.getContent()) {
				v.accept(this);
			}
			{
				ETag v=n.getETag();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof EmptyElemTag) {
			EmptyElemTag n = (EmptyElemTag)node;
			printToken("<");
			{
				ASTStringNode v=n.getElement_id();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag) {
			STag n = (STag)node;
			printToken("<");
			{
				ASTStringNode v=n.getElement_id();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof Attribute) {
			Attribute n = (Attribute)node;
			hintSingleSpace();
			{
				ASTStringNode v=n.getAttr_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("=");
			{
				ASTStringNode v=n.getAttr_val();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ETag) {
			ETag n = (ETag)node;
			printToken("</");
			{
				ASTStringNode v=n.getElement_id();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(">");
			return false;
		}
		if (node instanceof Content1) {
			Content1 n = (Content1)node;
			{
				Element v=n.getElement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content2) {
			Content2 n = (Content2)node;
			{
				Comment v=n.getComment();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content3) {
			Content3 n = (Content3)node;
			{
				CDSect v=n.getCDSect();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content4) {
			Content4 n = (Content4)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		return true;
	}
}
