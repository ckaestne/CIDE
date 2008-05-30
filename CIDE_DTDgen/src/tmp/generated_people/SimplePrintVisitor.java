package tmp.generated_people;

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
			{
				Prolog v=n.getProlog();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				RootElement v=n.getRootElement();
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
				Whitespace v=n.getWhitespace();
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
		if (node instanceof Whitespace) {
			Whitespace n = (Whitespace)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof CMisc1) {
			CMisc1 n = (CMisc1)node;
			{
				PI v=n.getPI();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof CMisc2) {
			CMisc2 n = (CMisc2)node;
			{
				Comment v=n.getComment();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof CMisc3) {
			CMisc3 n = (CMisc3)node;
			{
				Whitespace v=n.getWhitespace();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof RootElement) {
			RootElement n = (RootElement)node;
			{
				Element_people_list v=n.getElement_people_list();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_people_list1) {
			Element_people_list1 n = (Element_people_list1)node;
			{
				EmptyTag_people_list v=n.getEmptyTag_people_list();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_people_list2) {
			Element_people_list2 n = (Element_people_list2)node;
			{
				STag_people_list v=n.getSTag_people_list();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				Content_people_list_Seq1 v=n.getContent_people_list_Seq1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ETag_people_list v=n.getETag_people_list();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_people_list) {
			EmptyTag_people_list n = (EmptyTag_people_list)node;
			printToken("<");
			printToken("people_list");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_people_list) {
			STag_people_list n = (STag_people_list)node;
			printToken("<");
			printToken("people_list");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_people_list) {
			ETag_people_list n = (ETag_people_list)node;
			printToken("</");
			printToken("people_list");
			printToken(">");
			return false;
		}
		if (node instanceof Content_people_list_Seq1) {
			Content_people_list_Seq1 n = (Content_people_list_Seq1)node;
			for (Element_person v : n.getElement_person()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_person1) {
			Element_person1 n = (Element_person1)node;
			{
				EmptyTag_person v=n.getEmptyTag_person();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_person2) {
			Element_person2 n = (Element_person2)node;
			{
				STag_person v=n.getSTag_person();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				Content_person_Seq1 v=n.getContent_person_Seq1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ETag_person v=n.getETag_person();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_person) {
			EmptyTag_person n = (EmptyTag_person)node;
			printToken("<");
			printToken("person");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_person) {
			STag_person n = (STag_person)node;
			printToken("<");
			printToken("person");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_person) {
			ETag_person n = (ETag_person)node;
			printToken("</");
			printToken("person");
			printToken(">");
			return false;
		}
		if (node instanceof Content_person_Seq1) {
			Content_person_Seq1 n = (Content_person_Seq1)node;
			{
				Element_name v=n.getElement_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Element_birthdate v=n.getElement_birthdate();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Element_gender v=n.getElement_gender();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Element_socialsecuritynumber v=n.getElement_socialsecuritynumber();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_name1) {
			Element_name1 n = (Element_name1)node;
			{
				EmptyTag_name v=n.getEmptyTag_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_name2) {
			Element_name2 n = (Element_name2)node;
			{
				STag_name v=n.getSTag_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				Content_name_Seq1 v=n.getContent_name_Seq1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ETag_name v=n.getETag_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_name) {
			EmptyTag_name n = (EmptyTag_name)node;
			printToken("<");
			printToken("name");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_name) {
			STag_name n = (STag_name)node;
			printToken("<");
			printToken("name");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_name) {
			ETag_name n = (ETag_name)node;
			printToken("</");
			printToken("name");
			printToken(">");
			return false;
		}
		if (node instanceof Content_name_Seq1) {
			Content_name_Seq1 n = (Content_name_Seq1)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_birthdate1) {
			Element_birthdate1 n = (Element_birthdate1)node;
			{
				EmptyTag_birthdate v=n.getEmptyTag_birthdate();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_birthdate2) {
			Element_birthdate2 n = (Element_birthdate2)node;
			{
				STag_birthdate v=n.getSTag_birthdate();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				Content_birthdate_Seq1 v=n.getContent_birthdate_Seq1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ETag_birthdate v=n.getETag_birthdate();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_birthdate) {
			EmptyTag_birthdate n = (EmptyTag_birthdate)node;
			printToken("<");
			printToken("birthdate");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_birthdate) {
			STag_birthdate n = (STag_birthdate)node;
			printToken("<");
			printToken("birthdate");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_birthdate) {
			ETag_birthdate n = (ETag_birthdate)node;
			printToken("</");
			printToken("birthdate");
			printToken(">");
			return false;
		}
		if (node instanceof Content_birthdate_Seq1) {
			Content_birthdate_Seq1 n = (Content_birthdate_Seq1)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_gender1) {
			Element_gender1 n = (Element_gender1)node;
			{
				EmptyTag_gender v=n.getEmptyTag_gender();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_gender2) {
			Element_gender2 n = (Element_gender2)node;
			{
				STag_gender v=n.getSTag_gender();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				Content_gender_Seq1 v=n.getContent_gender_Seq1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ETag_gender v=n.getETag_gender();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_gender) {
			EmptyTag_gender n = (EmptyTag_gender)node;
			printToken("<");
			printToken("gender");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_gender) {
			STag_gender n = (STag_gender)node;
			printToken("<");
			printToken("gender");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_gender) {
			ETag_gender n = (ETag_gender)node;
			printToken("</");
			printToken("gender");
			printToken(">");
			return false;
		}
		if (node instanceof Content_gender_Seq1) {
			Content_gender_Seq1 n = (Content_gender_Seq1)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_socialsecuritynumber1) {
			Element_socialsecuritynumber1 n = (Element_socialsecuritynumber1)node;
			{
				EmptyTag_socialsecuritynumber v=n.getEmptyTag_socialsecuritynumber();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_socialsecuritynumber2) {
			Element_socialsecuritynumber2 n = (Element_socialsecuritynumber2)node;
			{
				STag_socialsecuritynumber v=n.getSTag_socialsecuritynumber();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				Content_socialsecuritynumber_Seq1 v=n.getContent_socialsecuritynumber_Seq1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ETag_socialsecuritynumber v=n.getETag_socialsecuritynumber();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_socialsecuritynumber) {
			EmptyTag_socialsecuritynumber n = (EmptyTag_socialsecuritynumber)node;
			printToken("<");
			printToken("socialsecuritynumber");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_socialsecuritynumber) {
			STag_socialsecuritynumber n = (STag_socialsecuritynumber)node;
			printToken("<");
			printToken("socialsecuritynumber");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_socialsecuritynumber) {
			ETag_socialsecuritynumber n = (ETag_socialsecuritynumber)node;
			printToken("</");
			printToken("socialsecuritynumber");
			printToken(">");
			return false;
		}
		if (node instanceof Content_socialsecuritynumber_Seq1) {
			Content_socialsecuritynumber_Seq1 n = (Content_socialsecuritynumber_Seq1)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any1) {
			Content_Any1 n = (Content_Any1)node;
			{
				Element_people_list v=n.getElement_people_list();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any2) {
			Content_Any2 n = (Content_Any2)node;
			{
				Element_person v=n.getElement_person();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any3) {
			Content_Any3 n = (Content_Any3)node;
			{
				Element_name v=n.getElement_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any4) {
			Content_Any4 n = (Content_Any4)node;
			{
				Element_birthdate v=n.getElement_birthdate();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any5) {
			Content_Any5 n = (Content_Any5)node;
			{
				Element_gender v=n.getElement_gender();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any6) {
			Content_Any6 n = (Content_Any6)node;
			{
				Element_socialsecuritynumber v=n.getElement_socialsecuritynumber();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		return true;
	}
}
