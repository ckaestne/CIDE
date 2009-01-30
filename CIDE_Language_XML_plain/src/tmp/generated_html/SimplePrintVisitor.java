package tmp.generated_html;

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
		if (node instanceof HtmlDocument) {
			HtmlDocument n = (HtmlDocument)node;
			{
				ElementSequence v=n.getElementSequence();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getEof();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ElementSequence) {
			ElementSequence n = (ElementSequence)node;
			for (Element v : n.getElement()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element1) {
			Element1 n = (Element1)node;
			{
				Tag v=n.getTag();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element2) {
			Element2 n = (Element2)node;
			{
				EndTag v=n.getEndTag();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element3) {
			Element3 n = (Element3)node;
			{
				CommentTag v=n.getCommentTag();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element4) {
			Element4 n = (Element4)node;
			{
				DeclTag v=n.getDeclTag();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element5) {
			Element5 n = (Element5)node;
			{
				ASTStringNode v=n.getTag_start();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getLst_error();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element6) {
			Element6 n = (Element6)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element7) {
			Element7 n = (Element7)node;
			{
				ASTStringNode v=n.getPcdata_qs();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element8) {
			Element8 n = (Element8)node;
			{
				ASTStringNode v=n.getPcdata_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element9) {
			Element9 n = (Element9)node;
			{
				ASTStringNode v=n.getEol();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Attribute) {
			Attribute n = (Attribute)node;
			{
				ASTStringNode v=n.getAttr_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getAttr_val();
				if (v!=null) {
					printToken("");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AttributeList) {
			AttributeList n = (AttributeList)node;
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Tag) {
			Tag n = (Tag)node;
			printToken("<");
			{
				ASTStringNode v=n.getTag_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				AttributeList v=n.getAttributeList();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getSlash();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(">");
			return false;
		}
		if (node instanceof EndTag) {
			EndTag n = (EndTag)node;
			printToken("</");
			{
				ASTStringNode v=n.getTag_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(">");
			return false;
		}
		if (node instanceof CommentTag) {
			CommentTag n = (CommentTag)node;
			printToken("<!--");
			for (CommentContent v : n.getCommentContent()) {
				v.accept(this);
			}
			{
				CommentEnd v=n.getCommentEnd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof CommentContent1) {
			CommentContent1 n = (CommentContent1)node;
			{
				ASTStringNode v=n.getDash();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof CommentContent2) {
			CommentContent2 n = (CommentContent2)node;
			{
				ASTStringNode v=n.getComment_eol();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof CommentContent3) {
			CommentContent3 n = (CommentContent3)node;
			{
				ASTStringNode v=n.getComment_word();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof CommentEnd1) {
			CommentEnd1 n = (CommentEnd1)node;
			{
				ASTStringNode v=n.getEof();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof CommentEnd2) {
			CommentEnd2 n = (CommentEnd2)node;
			{
				ASTStringNode v=n.getComment_end();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof DeclTag) {
			DeclTag n = (DeclTag)node;
			printToken("<!");
			{
				ASTStringNode v=n.getDecl_any();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(">");
			return false;
		}
		return true;
	}
}
