package tmp.generated_dtd;

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
		if (node instanceof DTD) {
			DTD n = (DTD)node;
			for (DTDEntry v : n.getDTDEntry()) {
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
		if (node instanceof DTDEntry1) {
			DTDEntry1 n = (DTDEntry1)node;
			{
				ElementDecl v=n.getElementDecl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof DTDEntry2) {
			DTDEntry2 n = (DTDEntry2)node;
			{
				AttListDecl v=n.getAttListDecl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ElementDecl) {
			ElementDecl n = (ElementDecl)node;
			printToken("<!ELEMENT");
			{
				ASTStringNode v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ContentSpec v=n.getContentSpec();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(">");
			return false;
		}
		if (node instanceof ContentSpec1) {
			ContentSpec1 n = (ContentSpec1)node;
			printToken("EMPTY");
			return false;
		}
		if (node instanceof ContentSpec2) {
			ContentSpec2 n = (ContentSpec2)node;
			printToken("ANY");
			return false;
		}
		if (node instanceof ContentSpec3) {
			ContentSpec3 n = (ContentSpec3)node;
			{
				Children v=n.getChildren();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Children) {
			Children n = (Children)node;
			{
				SeqOrChoice v=n.getSeqOrChoice();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Modifier v=n.getModifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof SeqOrChoice1) {
			SeqOrChoice1 n = (SeqOrChoice1)node;
			{
				Choice v=n.getChoice();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof SeqOrChoice2) {
			SeqOrChoice2 n = (SeqOrChoice2)node;
			{
				Seq v=n.getSeq();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Choice) {
			Choice n = (Choice)node;
			Iterator<Cp> listElements = n.getCp().iterator();
			printToken("(");
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken("|");
				listElements.next().accept(this);
			}
			printToken(")");
			return false;
		}
		if (node instanceof Seq) {
			Seq n = (Seq)node;
			Iterator<Cp> listElements = n.getCp().iterator();
			printToken("(");
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken(",");
				listElements.next().accept(this);
			}
			printToken(")");
			return false;
		}
		if (node instanceof Cp1) {
			Cp1 n = (Cp1)node;
			printToken("#PCDATA");
			return false;
		}
		if (node instanceof Cp2) {
			Cp2 n = (Cp2)node;
			{
				ASTStringNode v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Modifier v=n.getModifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Cp3) {
			Cp3 n = (Cp3)node;
			{
				SeqOrChoice v=n.getSeqOrChoice();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Modifier v=n.getModifier1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Modifier1) {
			Modifier1 n = (Modifier1)node;
			printToken("*");
			return false;
		}
		if (node instanceof Modifier2) {
			Modifier2 n = (Modifier2)node;
			printToken("+");
			return false;
		}
		if (node instanceof Modifier3) {
			Modifier3 n = (Modifier3)node;
			printToken("?");
			return false;
		}
		if (node instanceof AttListDecl) {
			AttListDecl n = (AttListDecl)node;
			printToken("<!ATTLIST");
			{
				ASTStringNode v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (AttribDef v : n.getAttribDef()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof AttribDef) {
			AttribDef n = (AttribDef)node;
			{
				ASTStringNode v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				AttribType v=n.getAttribType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				DefaultDecl v=n.getDefaultDecl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AttribType1) {
			AttribType1 n = (AttribType1)node;
			{
				StringType v=n.getStringType();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AttribType2) {
			AttribType2 n = (AttribType2)node;
			{
				TokenizedType v=n.getTokenizedType();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AttribType3) {
			AttribType3 n = (AttribType3)node;
			{
				EnumeratedType v=n.getEnumeratedType();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StringType) {
			StringType n = (StringType)node;
			printToken("CDATA");
			return false;
		}
		if (node instanceof TokenizedType1) {
			TokenizedType1 n = (TokenizedType1)node;
			printToken("ID");
			return false;
		}
		if (node instanceof TokenizedType2) {
			TokenizedType2 n = (TokenizedType2)node;
			printToken("IDREF");
			return false;
		}
		if (node instanceof TokenizedType3) {
			TokenizedType3 n = (TokenizedType3)node;
			printToken("IDREFS");
			return false;
		}
		if (node instanceof TokenizedType4) {
			TokenizedType4 n = (TokenizedType4)node;
			printToken("ENTITY");
			return false;
		}
		if (node instanceof TokenizedType5) {
			TokenizedType5 n = (TokenizedType5)node;
			printToken("ENTITIES");
			return false;
		}
		if (node instanceof TokenizedType6) {
			TokenizedType6 n = (TokenizedType6)node;
			printToken("NMTOKEN");
			return false;
		}
		if (node instanceof TokenizedType7) {
			TokenizedType7 n = (TokenizedType7)node;
			printToken("NMTOKENS");
			return false;
		}
		if (node instanceof EnumeratedType1) {
			EnumeratedType1 n = (EnumeratedType1)node;
			{
				NotationType v=n.getNotationType();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof EnumeratedType2) {
			EnumeratedType2 n = (EnumeratedType2)node;
			{
				Enumeration v=n.getEnumeration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof NotationType) {
			NotationType n = (NotationType)node;
			printToken("NOTATION");
			printToken("(");
			{
				ASTStringNode v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (ASTStringNode v : n.getName1()) {
				printToken("|");
				v.accept(this);
			}
			printToken(")");
			return false;
		}
		if (node instanceof Enumeration) {
			Enumeration n = (Enumeration)node;
			printToken("(");
			{
				ASTStringNode v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (ASTStringNode v : n.getName1()) {
				printToken("|");
				v.accept(this);
			}
			printToken(")");
			return false;
		}
		if (node instanceof DefaultDecl1) {
			DefaultDecl1 n = (DefaultDecl1)node;
			printToken("#REQUIRED");
			return false;
		}
		if (node instanceof DefaultDecl2) {
			DefaultDecl2 n = (DefaultDecl2)node;
			printToken("#IMPLIED");
			return false;
		}
		if (node instanceof DefaultDecl3) {
			DefaultDecl3 n = (DefaultDecl3)node;
			{
				ASTTextNode v=n.getText17();
				if (v!=null) {
					printToken("#FIXED");
					v.accept(this);
				}
			}
			{
				AttribValue v=n.getAttribValue();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AttribValue) {
			AttribValue n = (AttribValue)node;
			{
				ASTStringNode v=n.getQuotedstr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		return true;
	}
}
