package tmp.generated_xhtml;

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
				Element_html v=n.getElement_html();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_html1) {
			Element_html1 n = (Element_html1)node;
			{
				EmptyTag_html v=n.getEmptyTag_html();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_html2) {
			Element_html2 n = (Element_html2)node;
			{
				STag_html v=n.getSTag_html();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				Content_html_Seq1 v=n.getContent_html_Seq1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ETag_html v=n.getETag_html();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_html) {
			EmptyTag_html n = (EmptyTag_html)node;
			printToken("<");
			printToken("html");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_html) {
			STag_html n = (STag_html)node;
			printToken("<");
			printToken("html");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_html) {
			ETag_html n = (ETag_html)node;
			printToken("</");
			printToken("html");
			printToken(">");
			return false;
		}
		if (node instanceof Content_html_Seq1) {
			Content_html_Seq1 n = (Content_html_Seq1)node;
			{
				Element_head v=n.getElement_head();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Element_body v=n.getElement_body();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_button1) {
			Element_button1 n = (Element_button1)node;
			{
				EmptyTag_button v=n.getEmptyTag_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_button2) {
			Element_button2 n = (Element_button2)node;
			{
				STag_button v=n.getSTag_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_button_Choice1 v : n.getContent_button_Choice1()) {
				v.accept(this);
			}
			{
				ETag_button v=n.getETag_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_button) {
			EmptyTag_button n = (EmptyTag_button)node;
			printToken("<");
			printToken("button");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_button) {
			STag_button n = (STag_button)node;
			printToken("<");
			printToken("button");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_button) {
			ETag_button n = (ETag_button)node;
			printToken("</");
			printToken("button");
			printToken(">");
			return false;
		}
		if (node instanceof Content_button_Choice11) {
			Content_button_Choice11 n = (Content_button_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice12) {
			Content_button_Choice12 n = (Content_button_Choice12)node;
			{
				Element_p v=n.getElement_p();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice13) {
			Content_button_Choice13 n = (Content_button_Choice13)node;
			{
				Element_h1 v=n.getElement_h1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice14) {
			Content_button_Choice14 n = (Content_button_Choice14)node;
			{
				Element_h2 v=n.getElement_h2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice15) {
			Content_button_Choice15 n = (Content_button_Choice15)node;
			{
				Element_h3 v=n.getElement_h3();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice16) {
			Content_button_Choice16 n = (Content_button_Choice16)node;
			{
				Element_h4 v=n.getElement_h4();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice17) {
			Content_button_Choice17 n = (Content_button_Choice17)node;
			{
				Element_h5 v=n.getElement_h5();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice18) {
			Content_button_Choice18 n = (Content_button_Choice18)node;
			{
				Element_h6 v=n.getElement_h6();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice19) {
			Content_button_Choice19 n = (Content_button_Choice19)node;
			{
				Element_div v=n.getElement_div();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice110) {
			Content_button_Choice110 n = (Content_button_Choice110)node;
			{
				Element_ul v=n.getElement_ul();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice111) {
			Content_button_Choice111 n = (Content_button_Choice111)node;
			{
				Element_ol v=n.getElement_ol();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice112) {
			Content_button_Choice112 n = (Content_button_Choice112)node;
			{
				Element_dl v=n.getElement_dl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice113) {
			Content_button_Choice113 n = (Content_button_Choice113)node;
			{
				Element_pre v=n.getElement_pre();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice114) {
			Content_button_Choice114 n = (Content_button_Choice114)node;
			{
				Element_hr v=n.getElement_hr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice115) {
			Content_button_Choice115 n = (Content_button_Choice115)node;
			{
				Element_blockquote v=n.getElement_blockquote();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice116) {
			Content_button_Choice116 n = (Content_button_Choice116)node;
			{
				Element_address v=n.getElement_address();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice117) {
			Content_button_Choice117 n = (Content_button_Choice117)node;
			{
				Element_table v=n.getElement_table();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice118) {
			Content_button_Choice118 n = (Content_button_Choice118)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice119) {
			Content_button_Choice119 n = (Content_button_Choice119)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice120) {
			Content_button_Choice120 n = (Content_button_Choice120)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice121) {
			Content_button_Choice121 n = (Content_button_Choice121)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice122) {
			Content_button_Choice122 n = (Content_button_Choice122)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice123) {
			Content_button_Choice123 n = (Content_button_Choice123)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice124) {
			Content_button_Choice124 n = (Content_button_Choice124)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice125) {
			Content_button_Choice125 n = (Content_button_Choice125)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice126) {
			Content_button_Choice126 n = (Content_button_Choice126)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice127) {
			Content_button_Choice127 n = (Content_button_Choice127)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice128) {
			Content_button_Choice128 n = (Content_button_Choice128)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice129) {
			Content_button_Choice129 n = (Content_button_Choice129)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice130) {
			Content_button_Choice130 n = (Content_button_Choice130)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice131) {
			Content_button_Choice131 n = (Content_button_Choice131)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice132) {
			Content_button_Choice132 n = (Content_button_Choice132)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice133) {
			Content_button_Choice133 n = (Content_button_Choice133)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice134) {
			Content_button_Choice134 n = (Content_button_Choice134)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice135) {
			Content_button_Choice135 n = (Content_button_Choice135)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice136) {
			Content_button_Choice136 n = (Content_button_Choice136)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice137) {
			Content_button_Choice137 n = (Content_button_Choice137)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice138) {
			Content_button_Choice138 n = (Content_button_Choice138)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice139) {
			Content_button_Choice139 n = (Content_button_Choice139)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice140) {
			Content_button_Choice140 n = (Content_button_Choice140)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice141) {
			Content_button_Choice141 n = (Content_button_Choice141)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice142) {
			Content_button_Choice142 n = (Content_button_Choice142)node;
			{
				Element_noscript v=n.getElement_noscript();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice143) {
			Content_button_Choice143 n = (Content_button_Choice143)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice144) {
			Content_button_Choice144 n = (Content_button_Choice144)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_button_Choice145) {
			Content_button_Choice145 n = (Content_button_Choice145)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_textarea1) {
			Element_textarea1 n = (Element_textarea1)node;
			{
				EmptyTag_textarea v=n.getEmptyTag_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_textarea2) {
			Element_textarea2 n = (Element_textarea2)node;
			{
				STag_textarea v=n.getSTag_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				Content_textarea_Seq1 v=n.getContent_textarea_Seq1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ETag_textarea v=n.getETag_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_textarea) {
			EmptyTag_textarea n = (EmptyTag_textarea)node;
			printToken("<");
			printToken("textarea");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_textarea) {
			STag_textarea n = (STag_textarea)node;
			printToken("<");
			printToken("textarea");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_textarea) {
			ETag_textarea n = (ETag_textarea)node;
			printToken("</");
			printToken("textarea");
			printToken(">");
			return false;
		}
		if (node instanceof Content_textarea_Seq1) {
			Content_textarea_Seq1 n = (Content_textarea_Seq1)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_em1) {
			Element_em1 n = (Element_em1)node;
			{
				EmptyTag_em v=n.getEmptyTag_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_em2) {
			Element_em2 n = (Element_em2)node;
			{
				STag_em v=n.getSTag_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_em_Choice1 v : n.getContent_em_Choice1()) {
				v.accept(this);
			}
			{
				ETag_em v=n.getETag_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_em) {
			EmptyTag_em n = (EmptyTag_em)node;
			printToken("<");
			printToken("em");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_em) {
			STag_em n = (STag_em)node;
			printToken("<");
			printToken("em");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_em) {
			ETag_em n = (ETag_em)node;
			printToken("</");
			printToken("em");
			printToken(">");
			return false;
		}
		if (node instanceof Content_em_Choice11) {
			Content_em_Choice11 n = (Content_em_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice12) {
			Content_em_Choice12 n = (Content_em_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice13) {
			Content_em_Choice13 n = (Content_em_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice14) {
			Content_em_Choice14 n = (Content_em_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice15) {
			Content_em_Choice15 n = (Content_em_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice16) {
			Content_em_Choice16 n = (Content_em_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice17) {
			Content_em_Choice17 n = (Content_em_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice18) {
			Content_em_Choice18 n = (Content_em_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice19) {
			Content_em_Choice19 n = (Content_em_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice110) {
			Content_em_Choice110 n = (Content_em_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice111) {
			Content_em_Choice111 n = (Content_em_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice112) {
			Content_em_Choice112 n = (Content_em_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice113) {
			Content_em_Choice113 n = (Content_em_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice114) {
			Content_em_Choice114 n = (Content_em_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice115) {
			Content_em_Choice115 n = (Content_em_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice116) {
			Content_em_Choice116 n = (Content_em_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice117) {
			Content_em_Choice117 n = (Content_em_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice118) {
			Content_em_Choice118 n = (Content_em_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice119) {
			Content_em_Choice119 n = (Content_em_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice120) {
			Content_em_Choice120 n = (Content_em_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice121) {
			Content_em_Choice121 n = (Content_em_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice122) {
			Content_em_Choice122 n = (Content_em_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice123) {
			Content_em_Choice123 n = (Content_em_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice124) {
			Content_em_Choice124 n = (Content_em_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice125) {
			Content_em_Choice125 n = (Content_em_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice126) {
			Content_em_Choice126 n = (Content_em_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice127) {
			Content_em_Choice127 n = (Content_em_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice128) {
			Content_em_Choice128 n = (Content_em_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice129) {
			Content_em_Choice129 n = (Content_em_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice130) {
			Content_em_Choice130 n = (Content_em_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice131) {
			Content_em_Choice131 n = (Content_em_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice132) {
			Content_em_Choice132 n = (Content_em_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice133) {
			Content_em_Choice133 n = (Content_em_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_em_Choice134) {
			Content_em_Choice134 n = (Content_em_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_small1) {
			Element_small1 n = (Element_small1)node;
			{
				EmptyTag_small v=n.getEmptyTag_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_small2) {
			Element_small2 n = (Element_small2)node;
			{
				STag_small v=n.getSTag_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_small_Choice1 v : n.getContent_small_Choice1()) {
				v.accept(this);
			}
			{
				ETag_small v=n.getETag_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_small) {
			EmptyTag_small n = (EmptyTag_small)node;
			printToken("<");
			printToken("small");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_small) {
			STag_small n = (STag_small)node;
			printToken("<");
			printToken("small");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_small) {
			ETag_small n = (ETag_small)node;
			printToken("</");
			printToken("small");
			printToken(">");
			return false;
		}
		if (node instanceof Content_small_Choice11) {
			Content_small_Choice11 n = (Content_small_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice12) {
			Content_small_Choice12 n = (Content_small_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice13) {
			Content_small_Choice13 n = (Content_small_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice14) {
			Content_small_Choice14 n = (Content_small_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice15) {
			Content_small_Choice15 n = (Content_small_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice16) {
			Content_small_Choice16 n = (Content_small_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice17) {
			Content_small_Choice17 n = (Content_small_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice18) {
			Content_small_Choice18 n = (Content_small_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice19) {
			Content_small_Choice19 n = (Content_small_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice110) {
			Content_small_Choice110 n = (Content_small_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice111) {
			Content_small_Choice111 n = (Content_small_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice112) {
			Content_small_Choice112 n = (Content_small_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice113) {
			Content_small_Choice113 n = (Content_small_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice114) {
			Content_small_Choice114 n = (Content_small_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice115) {
			Content_small_Choice115 n = (Content_small_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice116) {
			Content_small_Choice116 n = (Content_small_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice117) {
			Content_small_Choice117 n = (Content_small_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice118) {
			Content_small_Choice118 n = (Content_small_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice119) {
			Content_small_Choice119 n = (Content_small_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice120) {
			Content_small_Choice120 n = (Content_small_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice121) {
			Content_small_Choice121 n = (Content_small_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice122) {
			Content_small_Choice122 n = (Content_small_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice123) {
			Content_small_Choice123 n = (Content_small_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice124) {
			Content_small_Choice124 n = (Content_small_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice125) {
			Content_small_Choice125 n = (Content_small_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice126) {
			Content_small_Choice126 n = (Content_small_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice127) {
			Content_small_Choice127 n = (Content_small_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice128) {
			Content_small_Choice128 n = (Content_small_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice129) {
			Content_small_Choice129 n = (Content_small_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice130) {
			Content_small_Choice130 n = (Content_small_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice131) {
			Content_small_Choice131 n = (Content_small_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice132) {
			Content_small_Choice132 n = (Content_small_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice133) {
			Content_small_Choice133 n = (Content_small_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_small_Choice134) {
			Content_small_Choice134 n = (Content_small_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_area1) {
			Element_area1 n = (Element_area1)node;
			{
				EmptyTag_area v=n.getEmptyTag_area();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_area2) {
			Element_area2 n = (Element_area2)node;
			{
				STag_area v=n.getSTag_area();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				ETag_area v=n.getETag_area();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_area) {
			EmptyTag_area n = (EmptyTag_area)node;
			printToken("<");
			printToken("area");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_area) {
			STag_area n = (STag_area)node;
			printToken("<");
			printToken("area");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_area) {
			ETag_area n = (ETag_area)node;
			printToken("</");
			printToken("area");
			printToken(">");
			return false;
		}
		if (node instanceof Element_bdo1) {
			Element_bdo1 n = (Element_bdo1)node;
			{
				EmptyTag_bdo v=n.getEmptyTag_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_bdo2) {
			Element_bdo2 n = (Element_bdo2)node;
			{
				STag_bdo v=n.getSTag_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_bdo_Choice1 v : n.getContent_bdo_Choice1()) {
				v.accept(this);
			}
			{
				ETag_bdo v=n.getETag_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_bdo) {
			EmptyTag_bdo n = (EmptyTag_bdo)node;
			printToken("<");
			printToken("bdo");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_bdo) {
			STag_bdo n = (STag_bdo)node;
			printToken("<");
			printToken("bdo");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_bdo) {
			ETag_bdo n = (ETag_bdo)node;
			printToken("</");
			printToken("bdo");
			printToken(">");
			return false;
		}
		if (node instanceof Content_bdo_Choice11) {
			Content_bdo_Choice11 n = (Content_bdo_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice12) {
			Content_bdo_Choice12 n = (Content_bdo_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice13) {
			Content_bdo_Choice13 n = (Content_bdo_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice14) {
			Content_bdo_Choice14 n = (Content_bdo_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice15) {
			Content_bdo_Choice15 n = (Content_bdo_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice16) {
			Content_bdo_Choice16 n = (Content_bdo_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice17) {
			Content_bdo_Choice17 n = (Content_bdo_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice18) {
			Content_bdo_Choice18 n = (Content_bdo_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice19) {
			Content_bdo_Choice19 n = (Content_bdo_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice110) {
			Content_bdo_Choice110 n = (Content_bdo_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice111) {
			Content_bdo_Choice111 n = (Content_bdo_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice112) {
			Content_bdo_Choice112 n = (Content_bdo_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice113) {
			Content_bdo_Choice113 n = (Content_bdo_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice114) {
			Content_bdo_Choice114 n = (Content_bdo_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice115) {
			Content_bdo_Choice115 n = (Content_bdo_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice116) {
			Content_bdo_Choice116 n = (Content_bdo_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice117) {
			Content_bdo_Choice117 n = (Content_bdo_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice118) {
			Content_bdo_Choice118 n = (Content_bdo_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice119) {
			Content_bdo_Choice119 n = (Content_bdo_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice120) {
			Content_bdo_Choice120 n = (Content_bdo_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice121) {
			Content_bdo_Choice121 n = (Content_bdo_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice122) {
			Content_bdo_Choice122 n = (Content_bdo_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice123) {
			Content_bdo_Choice123 n = (Content_bdo_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice124) {
			Content_bdo_Choice124 n = (Content_bdo_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice125) {
			Content_bdo_Choice125 n = (Content_bdo_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice126) {
			Content_bdo_Choice126 n = (Content_bdo_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice127) {
			Content_bdo_Choice127 n = (Content_bdo_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice128) {
			Content_bdo_Choice128 n = (Content_bdo_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice129) {
			Content_bdo_Choice129 n = (Content_bdo_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice130) {
			Content_bdo_Choice130 n = (Content_bdo_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice131) {
			Content_bdo_Choice131 n = (Content_bdo_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice132) {
			Content_bdo_Choice132 n = (Content_bdo_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice133) {
			Content_bdo_Choice133 n = (Content_bdo_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_bdo_Choice134) {
			Content_bdo_Choice134 n = (Content_bdo_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_form1) {
			Element_form1 n = (Element_form1)node;
			{
				EmptyTag_form v=n.getEmptyTag_form();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_form2) {
			Element_form2 n = (Element_form2)node;
			{
				STag_form v=n.getSTag_form();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_form_Choice1 v : n.getContent_form_Choice1()) {
				v.accept(this);
			}
			{
				ETag_form v=n.getETag_form();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_form) {
			EmptyTag_form n = (EmptyTag_form)node;
			printToken("<");
			printToken("form");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_form) {
			STag_form n = (STag_form)node;
			printToken("<");
			printToken("form");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_form) {
			ETag_form n = (ETag_form)node;
			printToken("</");
			printToken("form");
			printToken(">");
			return false;
		}
		if (node instanceof Content_form_Choice11) {
			Content_form_Choice11 n = (Content_form_Choice11)node;
			{
				Element_p v=n.getElement_p();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_form_Choice12) {
			Content_form_Choice12 n = (Content_form_Choice12)node;
			{
				Element_h1 v=n.getElement_h1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_form_Choice13) {
			Content_form_Choice13 n = (Content_form_Choice13)node;
			{
				Element_h2 v=n.getElement_h2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_form_Choice14) {
			Content_form_Choice14 n = (Content_form_Choice14)node;
			{
				Element_h3 v=n.getElement_h3();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_form_Choice15) {
			Content_form_Choice15 n = (Content_form_Choice15)node;
			{
				Element_h4 v=n.getElement_h4();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_form_Choice16) {
			Content_form_Choice16 n = (Content_form_Choice16)node;
			{
				Element_h5 v=n.getElement_h5();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_form_Choice17) {
			Content_form_Choice17 n = (Content_form_Choice17)node;
			{
				Element_h6 v=n.getElement_h6();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_form_Choice18) {
			Content_form_Choice18 n = (Content_form_Choice18)node;
			{
				Element_div v=n.getElement_div();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_form_Choice19) {
			Content_form_Choice19 n = (Content_form_Choice19)node;
			{
				Element_ul v=n.getElement_ul();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_form_Choice110) {
			Content_form_Choice110 n = (Content_form_Choice110)node;
			{
				Element_ol v=n.getElement_ol();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_form_Choice111) {
			Content_form_Choice111 n = (Content_form_Choice111)node;
			{
				Element_dl v=n.getElement_dl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_form_Choice112) {
			Content_form_Choice112 n = (Content_form_Choice112)node;
			{
				Element_pre v=n.getElement_pre();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_form_Choice113) {
			Content_form_Choice113 n = (Content_form_Choice113)node;
			{
				Element_hr v=n.getElement_hr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_form_Choice114) {
			Content_form_Choice114 n = (Content_form_Choice114)node;
			{
				Element_blockquote v=n.getElement_blockquote();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_form_Choice115) {
			Content_form_Choice115 n = (Content_form_Choice115)node;
			{
				Element_address v=n.getElement_address();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_form_Choice116) {
			Content_form_Choice116 n = (Content_form_Choice116)node;
			{
				Element_fieldset v=n.getElement_fieldset();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_form_Choice117) {
			Content_form_Choice117 n = (Content_form_Choice117)node;
			{
				Element_table v=n.getElement_table();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_form_Choice118) {
			Content_form_Choice118 n = (Content_form_Choice118)node;
			{
				Element_noscript v=n.getElement_noscript();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_form_Choice119) {
			Content_form_Choice119 n = (Content_form_Choice119)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_form_Choice120) {
			Content_form_Choice120 n = (Content_form_Choice120)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_form_Choice121) {
			Content_form_Choice121 n = (Content_form_Choice121)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_link1) {
			Element_link1 n = (Element_link1)node;
			{
				EmptyTag_link v=n.getEmptyTag_link();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_link2) {
			Element_link2 n = (Element_link2)node;
			{
				STag_link v=n.getSTag_link();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				ETag_link v=n.getETag_link();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_link) {
			EmptyTag_link n = (EmptyTag_link)node;
			printToken("<");
			printToken("link");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_link) {
			STag_link n = (STag_link)node;
			printToken("<");
			printToken("link");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_link) {
			ETag_link n = (ETag_link)node;
			printToken("</");
			printToken("link");
			printToken(">");
			return false;
		}
		if (node instanceof Element_label1) {
			Element_label1 n = (Element_label1)node;
			{
				EmptyTag_label v=n.getEmptyTag_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_label2) {
			Element_label2 n = (Element_label2)node;
			{
				STag_label v=n.getSTag_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_label_Choice1 v : n.getContent_label_Choice1()) {
				v.accept(this);
			}
			{
				ETag_label v=n.getETag_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_label) {
			EmptyTag_label n = (EmptyTag_label)node;
			printToken("<");
			printToken("label");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_label) {
			STag_label n = (STag_label)node;
			printToken("<");
			printToken("label");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_label) {
			ETag_label n = (ETag_label)node;
			printToken("</");
			printToken("label");
			printToken(">");
			return false;
		}
		if (node instanceof Content_label_Choice11) {
			Content_label_Choice11 n = (Content_label_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice12) {
			Content_label_Choice12 n = (Content_label_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice13) {
			Content_label_Choice13 n = (Content_label_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice14) {
			Content_label_Choice14 n = (Content_label_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice15) {
			Content_label_Choice15 n = (Content_label_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice16) {
			Content_label_Choice16 n = (Content_label_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice17) {
			Content_label_Choice17 n = (Content_label_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice18) {
			Content_label_Choice18 n = (Content_label_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice19) {
			Content_label_Choice19 n = (Content_label_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice110) {
			Content_label_Choice110 n = (Content_label_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice111) {
			Content_label_Choice111 n = (Content_label_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice112) {
			Content_label_Choice112 n = (Content_label_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice113) {
			Content_label_Choice113 n = (Content_label_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice114) {
			Content_label_Choice114 n = (Content_label_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice115) {
			Content_label_Choice115 n = (Content_label_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice116) {
			Content_label_Choice116 n = (Content_label_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice117) {
			Content_label_Choice117 n = (Content_label_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice118) {
			Content_label_Choice118 n = (Content_label_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice119) {
			Content_label_Choice119 n = (Content_label_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice120) {
			Content_label_Choice120 n = (Content_label_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice121) {
			Content_label_Choice121 n = (Content_label_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice122) {
			Content_label_Choice122 n = (Content_label_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice123) {
			Content_label_Choice123 n = (Content_label_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice124) {
			Content_label_Choice124 n = (Content_label_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice125) {
			Content_label_Choice125 n = (Content_label_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice126) {
			Content_label_Choice126 n = (Content_label_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice127) {
			Content_label_Choice127 n = (Content_label_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice128) {
			Content_label_Choice128 n = (Content_label_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice129) {
			Content_label_Choice129 n = (Content_label_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice130) {
			Content_label_Choice130 n = (Content_label_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice131) {
			Content_label_Choice131 n = (Content_label_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice132) {
			Content_label_Choice132 n = (Content_label_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice133) {
			Content_label_Choice133 n = (Content_label_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_label_Choice134) {
			Content_label_Choice134 n = (Content_label_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_dt1) {
			Element_dt1 n = (Element_dt1)node;
			{
				EmptyTag_dt v=n.getEmptyTag_dt();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_dt2) {
			Element_dt2 n = (Element_dt2)node;
			{
				STag_dt v=n.getSTag_dt();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_dt_Choice1 v : n.getContent_dt_Choice1()) {
				v.accept(this);
			}
			{
				ETag_dt v=n.getETag_dt();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_dt) {
			EmptyTag_dt n = (EmptyTag_dt)node;
			printToken("<");
			printToken("dt");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_dt) {
			STag_dt n = (STag_dt)node;
			printToken("<");
			printToken("dt");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_dt) {
			ETag_dt n = (ETag_dt)node;
			printToken("</");
			printToken("dt");
			printToken(">");
			return false;
		}
		if (node instanceof Content_dt_Choice11) {
			Content_dt_Choice11 n = (Content_dt_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice12) {
			Content_dt_Choice12 n = (Content_dt_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice13) {
			Content_dt_Choice13 n = (Content_dt_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice14) {
			Content_dt_Choice14 n = (Content_dt_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice15) {
			Content_dt_Choice15 n = (Content_dt_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice16) {
			Content_dt_Choice16 n = (Content_dt_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice17) {
			Content_dt_Choice17 n = (Content_dt_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice18) {
			Content_dt_Choice18 n = (Content_dt_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice19) {
			Content_dt_Choice19 n = (Content_dt_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice110) {
			Content_dt_Choice110 n = (Content_dt_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice111) {
			Content_dt_Choice111 n = (Content_dt_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice112) {
			Content_dt_Choice112 n = (Content_dt_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice113) {
			Content_dt_Choice113 n = (Content_dt_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice114) {
			Content_dt_Choice114 n = (Content_dt_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice115) {
			Content_dt_Choice115 n = (Content_dt_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice116) {
			Content_dt_Choice116 n = (Content_dt_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice117) {
			Content_dt_Choice117 n = (Content_dt_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice118) {
			Content_dt_Choice118 n = (Content_dt_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice119) {
			Content_dt_Choice119 n = (Content_dt_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice120) {
			Content_dt_Choice120 n = (Content_dt_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice121) {
			Content_dt_Choice121 n = (Content_dt_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice122) {
			Content_dt_Choice122 n = (Content_dt_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice123) {
			Content_dt_Choice123 n = (Content_dt_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice124) {
			Content_dt_Choice124 n = (Content_dt_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice125) {
			Content_dt_Choice125 n = (Content_dt_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice126) {
			Content_dt_Choice126 n = (Content_dt_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice127) {
			Content_dt_Choice127 n = (Content_dt_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice128) {
			Content_dt_Choice128 n = (Content_dt_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice129) {
			Content_dt_Choice129 n = (Content_dt_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice130) {
			Content_dt_Choice130 n = (Content_dt_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice131) {
			Content_dt_Choice131 n = (Content_dt_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice132) {
			Content_dt_Choice132 n = (Content_dt_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice133) {
			Content_dt_Choice133 n = (Content_dt_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dt_Choice134) {
			Content_dt_Choice134 n = (Content_dt_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_span1) {
			Element_span1 n = (Element_span1)node;
			{
				EmptyTag_span v=n.getEmptyTag_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_span2) {
			Element_span2 n = (Element_span2)node;
			{
				STag_span v=n.getSTag_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_span_Choice1 v : n.getContent_span_Choice1()) {
				v.accept(this);
			}
			{
				ETag_span v=n.getETag_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_span) {
			EmptyTag_span n = (EmptyTag_span)node;
			printToken("<");
			printToken("span");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_span) {
			STag_span n = (STag_span)node;
			printToken("<");
			printToken("span");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_span) {
			ETag_span n = (ETag_span)node;
			printToken("</");
			printToken("span");
			printToken(">");
			return false;
		}
		if (node instanceof Content_span_Choice11) {
			Content_span_Choice11 n = (Content_span_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice12) {
			Content_span_Choice12 n = (Content_span_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice13) {
			Content_span_Choice13 n = (Content_span_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice14) {
			Content_span_Choice14 n = (Content_span_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice15) {
			Content_span_Choice15 n = (Content_span_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice16) {
			Content_span_Choice16 n = (Content_span_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice17) {
			Content_span_Choice17 n = (Content_span_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice18) {
			Content_span_Choice18 n = (Content_span_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice19) {
			Content_span_Choice19 n = (Content_span_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice110) {
			Content_span_Choice110 n = (Content_span_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice111) {
			Content_span_Choice111 n = (Content_span_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice112) {
			Content_span_Choice112 n = (Content_span_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice113) {
			Content_span_Choice113 n = (Content_span_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice114) {
			Content_span_Choice114 n = (Content_span_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice115) {
			Content_span_Choice115 n = (Content_span_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice116) {
			Content_span_Choice116 n = (Content_span_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice117) {
			Content_span_Choice117 n = (Content_span_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice118) {
			Content_span_Choice118 n = (Content_span_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice119) {
			Content_span_Choice119 n = (Content_span_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice120) {
			Content_span_Choice120 n = (Content_span_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice121) {
			Content_span_Choice121 n = (Content_span_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice122) {
			Content_span_Choice122 n = (Content_span_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice123) {
			Content_span_Choice123 n = (Content_span_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice124) {
			Content_span_Choice124 n = (Content_span_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice125) {
			Content_span_Choice125 n = (Content_span_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice126) {
			Content_span_Choice126 n = (Content_span_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice127) {
			Content_span_Choice127 n = (Content_span_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice128) {
			Content_span_Choice128 n = (Content_span_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice129) {
			Content_span_Choice129 n = (Content_span_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice130) {
			Content_span_Choice130 n = (Content_span_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice131) {
			Content_span_Choice131 n = (Content_span_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice132) {
			Content_span_Choice132 n = (Content_span_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice133) {
			Content_span_Choice133 n = (Content_span_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_span_Choice134) {
			Content_span_Choice134 n = (Content_span_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_title1) {
			Element_title1 n = (Element_title1)node;
			{
				EmptyTag_title v=n.getEmptyTag_title();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_title2) {
			Element_title2 n = (Element_title2)node;
			{
				STag_title v=n.getSTag_title();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				Content_title_Seq1 v=n.getContent_title_Seq1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ETag_title v=n.getETag_title();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_title) {
			EmptyTag_title n = (EmptyTag_title)node;
			printToken("<");
			printToken("title");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_title) {
			STag_title n = (STag_title)node;
			printToken("<");
			printToken("title");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_title) {
			ETag_title n = (ETag_title)node;
			printToken("</");
			printToken("title");
			printToken(">");
			return false;
		}
		if (node instanceof Content_title_Seq1) {
			Content_title_Seq1 n = (Content_title_Seq1)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_strong1) {
			Element_strong1 n = (Element_strong1)node;
			{
				EmptyTag_strong v=n.getEmptyTag_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_strong2) {
			Element_strong2 n = (Element_strong2)node;
			{
				STag_strong v=n.getSTag_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_strong_Choice1 v : n.getContent_strong_Choice1()) {
				v.accept(this);
			}
			{
				ETag_strong v=n.getETag_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_strong) {
			EmptyTag_strong n = (EmptyTag_strong)node;
			printToken("<");
			printToken("strong");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_strong) {
			STag_strong n = (STag_strong)node;
			printToken("<");
			printToken("strong");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_strong) {
			ETag_strong n = (ETag_strong)node;
			printToken("</");
			printToken("strong");
			printToken(">");
			return false;
		}
		if (node instanceof Content_strong_Choice11) {
			Content_strong_Choice11 n = (Content_strong_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice12) {
			Content_strong_Choice12 n = (Content_strong_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice13) {
			Content_strong_Choice13 n = (Content_strong_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice14) {
			Content_strong_Choice14 n = (Content_strong_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice15) {
			Content_strong_Choice15 n = (Content_strong_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice16) {
			Content_strong_Choice16 n = (Content_strong_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice17) {
			Content_strong_Choice17 n = (Content_strong_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice18) {
			Content_strong_Choice18 n = (Content_strong_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice19) {
			Content_strong_Choice19 n = (Content_strong_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice110) {
			Content_strong_Choice110 n = (Content_strong_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice111) {
			Content_strong_Choice111 n = (Content_strong_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice112) {
			Content_strong_Choice112 n = (Content_strong_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice113) {
			Content_strong_Choice113 n = (Content_strong_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice114) {
			Content_strong_Choice114 n = (Content_strong_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice115) {
			Content_strong_Choice115 n = (Content_strong_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice116) {
			Content_strong_Choice116 n = (Content_strong_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice117) {
			Content_strong_Choice117 n = (Content_strong_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice118) {
			Content_strong_Choice118 n = (Content_strong_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice119) {
			Content_strong_Choice119 n = (Content_strong_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice120) {
			Content_strong_Choice120 n = (Content_strong_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice121) {
			Content_strong_Choice121 n = (Content_strong_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice122) {
			Content_strong_Choice122 n = (Content_strong_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice123) {
			Content_strong_Choice123 n = (Content_strong_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice124) {
			Content_strong_Choice124 n = (Content_strong_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice125) {
			Content_strong_Choice125 n = (Content_strong_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice126) {
			Content_strong_Choice126 n = (Content_strong_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice127) {
			Content_strong_Choice127 n = (Content_strong_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice128) {
			Content_strong_Choice128 n = (Content_strong_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice129) {
			Content_strong_Choice129 n = (Content_strong_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice130) {
			Content_strong_Choice130 n = (Content_strong_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice131) {
			Content_strong_Choice131 n = (Content_strong_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice132) {
			Content_strong_Choice132 n = (Content_strong_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice133) {
			Content_strong_Choice133 n = (Content_strong_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_strong_Choice134) {
			Content_strong_Choice134 n = (Content_strong_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_script1) {
			Element_script1 n = (Element_script1)node;
			{
				EmptyTag_script v=n.getEmptyTag_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_script2) {
			Element_script2 n = (Element_script2)node;
			{
				STag_script v=n.getSTag_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				Content_script_Seq1 v=n.getContent_script_Seq1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ETag_script v=n.getETag_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_script) {
			EmptyTag_script n = (EmptyTag_script)node;
			printToken("<");
			printToken("script");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_script) {
			STag_script n = (STag_script)node;
			printToken("<");
			printToken("script");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_script) {
			ETag_script n = (ETag_script)node;
			printToken("</");
			printToken("script");
			printToken(">");
			return false;
		}
		if (node instanceof Content_script_Seq1) {
			Content_script_Seq1 n = (Content_script_Seq1)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_div1) {
			Element_div1 n = (Element_div1)node;
			{
				EmptyTag_div v=n.getEmptyTag_div();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_div2) {
			Element_div2 n = (Element_div2)node;
			{
				STag_div v=n.getSTag_div();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_div_Choice1 v : n.getContent_div_Choice1()) {
				v.accept(this);
			}
			{
				ETag_div v=n.getETag_div();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_div) {
			EmptyTag_div n = (EmptyTag_div)node;
			printToken("<");
			printToken("div");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_div) {
			STag_div n = (STag_div)node;
			printToken("<");
			printToken("div");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_div) {
			ETag_div n = (ETag_div)node;
			printToken("</");
			printToken("div");
			printToken(">");
			return false;
		}
		if (node instanceof Content_div_Choice11) {
			Content_div_Choice11 n = (Content_div_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice12) {
			Content_div_Choice12 n = (Content_div_Choice12)node;
			{
				Element_p v=n.getElement_p();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice13) {
			Content_div_Choice13 n = (Content_div_Choice13)node;
			{
				Element_h1 v=n.getElement_h1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice14) {
			Content_div_Choice14 n = (Content_div_Choice14)node;
			{
				Element_h2 v=n.getElement_h2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice15) {
			Content_div_Choice15 n = (Content_div_Choice15)node;
			{
				Element_h3 v=n.getElement_h3();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice16) {
			Content_div_Choice16 n = (Content_div_Choice16)node;
			{
				Element_h4 v=n.getElement_h4();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice17) {
			Content_div_Choice17 n = (Content_div_Choice17)node;
			{
				Element_h5 v=n.getElement_h5();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice18) {
			Content_div_Choice18 n = (Content_div_Choice18)node;
			{
				Element_h6 v=n.getElement_h6();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice19) {
			Content_div_Choice19 n = (Content_div_Choice19)node;
			{
				Element_div v=n.getElement_div();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice110) {
			Content_div_Choice110 n = (Content_div_Choice110)node;
			{
				Element_ul v=n.getElement_ul();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice111) {
			Content_div_Choice111 n = (Content_div_Choice111)node;
			{
				Element_ol v=n.getElement_ol();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice112) {
			Content_div_Choice112 n = (Content_div_Choice112)node;
			{
				Element_dl v=n.getElement_dl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice113) {
			Content_div_Choice113 n = (Content_div_Choice113)node;
			{
				Element_pre v=n.getElement_pre();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice114) {
			Content_div_Choice114 n = (Content_div_Choice114)node;
			{
				Element_hr v=n.getElement_hr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice115) {
			Content_div_Choice115 n = (Content_div_Choice115)node;
			{
				Element_blockquote v=n.getElement_blockquote();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice116) {
			Content_div_Choice116 n = (Content_div_Choice116)node;
			{
				Element_address v=n.getElement_address();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice117) {
			Content_div_Choice117 n = (Content_div_Choice117)node;
			{
				Element_fieldset v=n.getElement_fieldset();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice118) {
			Content_div_Choice118 n = (Content_div_Choice118)node;
			{
				Element_table v=n.getElement_table();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice119) {
			Content_div_Choice119 n = (Content_div_Choice119)node;
			{
				Element_form v=n.getElement_form();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice120) {
			Content_div_Choice120 n = (Content_div_Choice120)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice121) {
			Content_div_Choice121 n = (Content_div_Choice121)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice122) {
			Content_div_Choice122 n = (Content_div_Choice122)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice123) {
			Content_div_Choice123 n = (Content_div_Choice123)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice124) {
			Content_div_Choice124 n = (Content_div_Choice124)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice125) {
			Content_div_Choice125 n = (Content_div_Choice125)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice126) {
			Content_div_Choice126 n = (Content_div_Choice126)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice127) {
			Content_div_Choice127 n = (Content_div_Choice127)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice128) {
			Content_div_Choice128 n = (Content_div_Choice128)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice129) {
			Content_div_Choice129 n = (Content_div_Choice129)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice130) {
			Content_div_Choice130 n = (Content_div_Choice130)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice131) {
			Content_div_Choice131 n = (Content_div_Choice131)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice132) {
			Content_div_Choice132 n = (Content_div_Choice132)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice133) {
			Content_div_Choice133 n = (Content_div_Choice133)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice134) {
			Content_div_Choice134 n = (Content_div_Choice134)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice135) {
			Content_div_Choice135 n = (Content_div_Choice135)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice136) {
			Content_div_Choice136 n = (Content_div_Choice136)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice137) {
			Content_div_Choice137 n = (Content_div_Choice137)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice138) {
			Content_div_Choice138 n = (Content_div_Choice138)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice139) {
			Content_div_Choice139 n = (Content_div_Choice139)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice140) {
			Content_div_Choice140 n = (Content_div_Choice140)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice141) {
			Content_div_Choice141 n = (Content_div_Choice141)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice142) {
			Content_div_Choice142 n = (Content_div_Choice142)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice143) {
			Content_div_Choice143 n = (Content_div_Choice143)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice144) {
			Content_div_Choice144 n = (Content_div_Choice144)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice145) {
			Content_div_Choice145 n = (Content_div_Choice145)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice146) {
			Content_div_Choice146 n = (Content_div_Choice146)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice147) {
			Content_div_Choice147 n = (Content_div_Choice147)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice148) {
			Content_div_Choice148 n = (Content_div_Choice148)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice149) {
			Content_div_Choice149 n = (Content_div_Choice149)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice150) {
			Content_div_Choice150 n = (Content_div_Choice150)node;
			{
				Element_noscript v=n.getElement_noscript();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice151) {
			Content_div_Choice151 n = (Content_div_Choice151)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice152) {
			Content_div_Choice152 n = (Content_div_Choice152)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_div_Choice153) {
			Content_div_Choice153 n = (Content_div_Choice153)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_dl1) {
			Element_dl1 n = (Element_dl1)node;
			{
				EmptyTag_dl v=n.getEmptyTag_dl();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_dl2) {
			Element_dl2 n = (Element_dl2)node;
			{
				STag_dl v=n.getSTag_dl();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_dl_Choice1 v : n.getContent_dl_Choice1()) {
				v.accept(this);
			}
			{
				ETag_dl v=n.getETag_dl();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_dl) {
			EmptyTag_dl n = (EmptyTag_dl)node;
			printToken("<");
			printToken("dl");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_dl) {
			STag_dl n = (STag_dl)node;
			printToken("<");
			printToken("dl");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_dl) {
			ETag_dl n = (ETag_dl)node;
			printToken("</");
			printToken("dl");
			printToken(">");
			return false;
		}
		if (node instanceof Content_dl_Choice11) {
			Content_dl_Choice11 n = (Content_dl_Choice11)node;
			{
				Element_dt v=n.getElement_dt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dl_Choice12) {
			Content_dl_Choice12 n = (Content_dl_Choice12)node;
			{
				Element_dd v=n.getElement_dd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_blockquote1) {
			Element_blockquote1 n = (Element_blockquote1)node;
			{
				EmptyTag_blockquote v=n.getEmptyTag_blockquote();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_blockquote2) {
			Element_blockquote2 n = (Element_blockquote2)node;
			{
				STag_blockquote v=n.getSTag_blockquote();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_blockquote_Choice1 v : n.getContent_blockquote_Choice1()) {
				v.accept(this);
			}
			{
				ETag_blockquote v=n.getETag_blockquote();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_blockquote) {
			EmptyTag_blockquote n = (EmptyTag_blockquote)node;
			printToken("<");
			printToken("blockquote");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_blockquote) {
			STag_blockquote n = (STag_blockquote)node;
			printToken("<");
			printToken("blockquote");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_blockquote) {
			ETag_blockquote n = (ETag_blockquote)node;
			printToken("</");
			printToken("blockquote");
			printToken(">");
			return false;
		}
		if (node instanceof Content_blockquote_Choice11) {
			Content_blockquote_Choice11 n = (Content_blockquote_Choice11)node;
			{
				Element_p v=n.getElement_p();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_blockquote_Choice12) {
			Content_blockquote_Choice12 n = (Content_blockquote_Choice12)node;
			{
				Element_h1 v=n.getElement_h1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_blockquote_Choice13) {
			Content_blockquote_Choice13 n = (Content_blockquote_Choice13)node;
			{
				Element_h2 v=n.getElement_h2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_blockquote_Choice14) {
			Content_blockquote_Choice14 n = (Content_blockquote_Choice14)node;
			{
				Element_h3 v=n.getElement_h3();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_blockquote_Choice15) {
			Content_blockquote_Choice15 n = (Content_blockquote_Choice15)node;
			{
				Element_h4 v=n.getElement_h4();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_blockquote_Choice16) {
			Content_blockquote_Choice16 n = (Content_blockquote_Choice16)node;
			{
				Element_h5 v=n.getElement_h5();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_blockquote_Choice17) {
			Content_blockquote_Choice17 n = (Content_blockquote_Choice17)node;
			{
				Element_h6 v=n.getElement_h6();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_blockquote_Choice18) {
			Content_blockquote_Choice18 n = (Content_blockquote_Choice18)node;
			{
				Element_div v=n.getElement_div();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_blockquote_Choice19) {
			Content_blockquote_Choice19 n = (Content_blockquote_Choice19)node;
			{
				Element_ul v=n.getElement_ul();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_blockquote_Choice110) {
			Content_blockquote_Choice110 n = (Content_blockquote_Choice110)node;
			{
				Element_ol v=n.getElement_ol();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_blockquote_Choice111) {
			Content_blockquote_Choice111 n = (Content_blockquote_Choice111)node;
			{
				Element_dl v=n.getElement_dl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_blockquote_Choice112) {
			Content_blockquote_Choice112 n = (Content_blockquote_Choice112)node;
			{
				Element_pre v=n.getElement_pre();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_blockquote_Choice113) {
			Content_blockquote_Choice113 n = (Content_blockquote_Choice113)node;
			{
				Element_hr v=n.getElement_hr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_blockquote_Choice114) {
			Content_blockquote_Choice114 n = (Content_blockquote_Choice114)node;
			{
				Element_blockquote v=n.getElement_blockquote();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_blockquote_Choice115) {
			Content_blockquote_Choice115 n = (Content_blockquote_Choice115)node;
			{
				Element_address v=n.getElement_address();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_blockquote_Choice116) {
			Content_blockquote_Choice116 n = (Content_blockquote_Choice116)node;
			{
				Element_fieldset v=n.getElement_fieldset();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_blockquote_Choice117) {
			Content_blockquote_Choice117 n = (Content_blockquote_Choice117)node;
			{
				Element_table v=n.getElement_table();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_blockquote_Choice118) {
			Content_blockquote_Choice118 n = (Content_blockquote_Choice118)node;
			{
				Element_form v=n.getElement_form();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_blockquote_Choice119) {
			Content_blockquote_Choice119 n = (Content_blockquote_Choice119)node;
			{
				Element_noscript v=n.getElement_noscript();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_blockquote_Choice120) {
			Content_blockquote_Choice120 n = (Content_blockquote_Choice120)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_blockquote_Choice121) {
			Content_blockquote_Choice121 n = (Content_blockquote_Choice121)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_blockquote_Choice122) {
			Content_blockquote_Choice122 n = (Content_blockquote_Choice122)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_kbd1) {
			Element_kbd1 n = (Element_kbd1)node;
			{
				EmptyTag_kbd v=n.getEmptyTag_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_kbd2) {
			Element_kbd2 n = (Element_kbd2)node;
			{
				STag_kbd v=n.getSTag_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_kbd_Choice1 v : n.getContent_kbd_Choice1()) {
				v.accept(this);
			}
			{
				ETag_kbd v=n.getETag_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_kbd) {
			EmptyTag_kbd n = (EmptyTag_kbd)node;
			printToken("<");
			printToken("kbd");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_kbd) {
			STag_kbd n = (STag_kbd)node;
			printToken("<");
			printToken("kbd");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_kbd) {
			ETag_kbd n = (ETag_kbd)node;
			printToken("</");
			printToken("kbd");
			printToken(">");
			return false;
		}
		if (node instanceof Content_kbd_Choice11) {
			Content_kbd_Choice11 n = (Content_kbd_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice12) {
			Content_kbd_Choice12 n = (Content_kbd_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice13) {
			Content_kbd_Choice13 n = (Content_kbd_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice14) {
			Content_kbd_Choice14 n = (Content_kbd_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice15) {
			Content_kbd_Choice15 n = (Content_kbd_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice16) {
			Content_kbd_Choice16 n = (Content_kbd_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice17) {
			Content_kbd_Choice17 n = (Content_kbd_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice18) {
			Content_kbd_Choice18 n = (Content_kbd_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice19) {
			Content_kbd_Choice19 n = (Content_kbd_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice110) {
			Content_kbd_Choice110 n = (Content_kbd_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice111) {
			Content_kbd_Choice111 n = (Content_kbd_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice112) {
			Content_kbd_Choice112 n = (Content_kbd_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice113) {
			Content_kbd_Choice113 n = (Content_kbd_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice114) {
			Content_kbd_Choice114 n = (Content_kbd_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice115) {
			Content_kbd_Choice115 n = (Content_kbd_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice116) {
			Content_kbd_Choice116 n = (Content_kbd_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice117) {
			Content_kbd_Choice117 n = (Content_kbd_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice118) {
			Content_kbd_Choice118 n = (Content_kbd_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice119) {
			Content_kbd_Choice119 n = (Content_kbd_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice120) {
			Content_kbd_Choice120 n = (Content_kbd_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice121) {
			Content_kbd_Choice121 n = (Content_kbd_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice122) {
			Content_kbd_Choice122 n = (Content_kbd_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice123) {
			Content_kbd_Choice123 n = (Content_kbd_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice124) {
			Content_kbd_Choice124 n = (Content_kbd_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice125) {
			Content_kbd_Choice125 n = (Content_kbd_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice126) {
			Content_kbd_Choice126 n = (Content_kbd_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice127) {
			Content_kbd_Choice127 n = (Content_kbd_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice128) {
			Content_kbd_Choice128 n = (Content_kbd_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice129) {
			Content_kbd_Choice129 n = (Content_kbd_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice130) {
			Content_kbd_Choice130 n = (Content_kbd_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice131) {
			Content_kbd_Choice131 n = (Content_kbd_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice132) {
			Content_kbd_Choice132 n = (Content_kbd_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice133) {
			Content_kbd_Choice133 n = (Content_kbd_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_kbd_Choice134) {
			Content_kbd_Choice134 n = (Content_kbd_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_body1) {
			Element_body1 n = (Element_body1)node;
			{
				EmptyTag_body v=n.getEmptyTag_body();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_body2) {
			Element_body2 n = (Element_body2)node;
			{
				STag_body v=n.getSTag_body();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_body_Choice1 v : n.getContent_body_Choice1()) {
				v.accept(this);
			}
			{
				ETag_body v=n.getETag_body();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_body) {
			EmptyTag_body n = (EmptyTag_body)node;
			printToken("<");
			printToken("body");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_body) {
			STag_body n = (STag_body)node;
			printToken("<");
			printToken("body");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_body) {
			ETag_body n = (ETag_body)node;
			printToken("</");
			printToken("body");
			printToken(">");
			return false;
		}
		if (node instanceof Content_body_Choice11) {
			Content_body_Choice11 n = (Content_body_Choice11)node;
			{
				Element_p v=n.getElement_p();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_body_Choice12) {
			Content_body_Choice12 n = (Content_body_Choice12)node;
			{
				Element_h1 v=n.getElement_h1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_body_Choice13) {
			Content_body_Choice13 n = (Content_body_Choice13)node;
			{
				Element_h2 v=n.getElement_h2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_body_Choice14) {
			Content_body_Choice14 n = (Content_body_Choice14)node;
			{
				Element_h3 v=n.getElement_h3();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_body_Choice15) {
			Content_body_Choice15 n = (Content_body_Choice15)node;
			{
				Element_h4 v=n.getElement_h4();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_body_Choice16) {
			Content_body_Choice16 n = (Content_body_Choice16)node;
			{
				Element_h5 v=n.getElement_h5();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_body_Choice17) {
			Content_body_Choice17 n = (Content_body_Choice17)node;
			{
				Element_h6 v=n.getElement_h6();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_body_Choice18) {
			Content_body_Choice18 n = (Content_body_Choice18)node;
			{
				Element_div v=n.getElement_div();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_body_Choice19) {
			Content_body_Choice19 n = (Content_body_Choice19)node;
			{
				Element_ul v=n.getElement_ul();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_body_Choice110) {
			Content_body_Choice110 n = (Content_body_Choice110)node;
			{
				Element_ol v=n.getElement_ol();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_body_Choice111) {
			Content_body_Choice111 n = (Content_body_Choice111)node;
			{
				Element_dl v=n.getElement_dl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_body_Choice112) {
			Content_body_Choice112 n = (Content_body_Choice112)node;
			{
				Element_pre v=n.getElement_pre();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_body_Choice113) {
			Content_body_Choice113 n = (Content_body_Choice113)node;
			{
				Element_hr v=n.getElement_hr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_body_Choice114) {
			Content_body_Choice114 n = (Content_body_Choice114)node;
			{
				Element_blockquote v=n.getElement_blockquote();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_body_Choice115) {
			Content_body_Choice115 n = (Content_body_Choice115)node;
			{
				Element_address v=n.getElement_address();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_body_Choice116) {
			Content_body_Choice116 n = (Content_body_Choice116)node;
			{
				Element_fieldset v=n.getElement_fieldset();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_body_Choice117) {
			Content_body_Choice117 n = (Content_body_Choice117)node;
			{
				Element_table v=n.getElement_table();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_body_Choice118) {
			Content_body_Choice118 n = (Content_body_Choice118)node;
			{
				Element_form v=n.getElement_form();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_body_Choice119) {
			Content_body_Choice119 n = (Content_body_Choice119)node;
			{
				Element_noscript v=n.getElement_noscript();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_body_Choice120) {
			Content_body_Choice120 n = (Content_body_Choice120)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_body_Choice121) {
			Content_body_Choice121 n = (Content_body_Choice121)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_body_Choice122) {
			Content_body_Choice122 n = (Content_body_Choice122)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_ins1) {
			Element_ins1 n = (Element_ins1)node;
			{
				EmptyTag_ins v=n.getEmptyTag_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_ins2) {
			Element_ins2 n = (Element_ins2)node;
			{
				STag_ins v=n.getSTag_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_ins_Choice1 v : n.getContent_ins_Choice1()) {
				v.accept(this);
			}
			{
				ETag_ins v=n.getETag_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_ins) {
			EmptyTag_ins n = (EmptyTag_ins)node;
			printToken("<");
			printToken("ins");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_ins) {
			STag_ins n = (STag_ins)node;
			printToken("<");
			printToken("ins");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_ins) {
			ETag_ins n = (ETag_ins)node;
			printToken("</");
			printToken("ins");
			printToken(">");
			return false;
		}
		if (node instanceof Content_ins_Choice11) {
			Content_ins_Choice11 n = (Content_ins_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice12) {
			Content_ins_Choice12 n = (Content_ins_Choice12)node;
			{
				Element_p v=n.getElement_p();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice13) {
			Content_ins_Choice13 n = (Content_ins_Choice13)node;
			{
				Element_h1 v=n.getElement_h1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice14) {
			Content_ins_Choice14 n = (Content_ins_Choice14)node;
			{
				Element_h2 v=n.getElement_h2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice15) {
			Content_ins_Choice15 n = (Content_ins_Choice15)node;
			{
				Element_h3 v=n.getElement_h3();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice16) {
			Content_ins_Choice16 n = (Content_ins_Choice16)node;
			{
				Element_h4 v=n.getElement_h4();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice17) {
			Content_ins_Choice17 n = (Content_ins_Choice17)node;
			{
				Element_h5 v=n.getElement_h5();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice18) {
			Content_ins_Choice18 n = (Content_ins_Choice18)node;
			{
				Element_h6 v=n.getElement_h6();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice19) {
			Content_ins_Choice19 n = (Content_ins_Choice19)node;
			{
				Element_div v=n.getElement_div();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice110) {
			Content_ins_Choice110 n = (Content_ins_Choice110)node;
			{
				Element_ul v=n.getElement_ul();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice111) {
			Content_ins_Choice111 n = (Content_ins_Choice111)node;
			{
				Element_ol v=n.getElement_ol();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice112) {
			Content_ins_Choice112 n = (Content_ins_Choice112)node;
			{
				Element_dl v=n.getElement_dl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice113) {
			Content_ins_Choice113 n = (Content_ins_Choice113)node;
			{
				Element_pre v=n.getElement_pre();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice114) {
			Content_ins_Choice114 n = (Content_ins_Choice114)node;
			{
				Element_hr v=n.getElement_hr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice115) {
			Content_ins_Choice115 n = (Content_ins_Choice115)node;
			{
				Element_blockquote v=n.getElement_blockquote();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice116) {
			Content_ins_Choice116 n = (Content_ins_Choice116)node;
			{
				Element_address v=n.getElement_address();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice117) {
			Content_ins_Choice117 n = (Content_ins_Choice117)node;
			{
				Element_fieldset v=n.getElement_fieldset();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice118) {
			Content_ins_Choice118 n = (Content_ins_Choice118)node;
			{
				Element_table v=n.getElement_table();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice119) {
			Content_ins_Choice119 n = (Content_ins_Choice119)node;
			{
				Element_form v=n.getElement_form();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice120) {
			Content_ins_Choice120 n = (Content_ins_Choice120)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice121) {
			Content_ins_Choice121 n = (Content_ins_Choice121)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice122) {
			Content_ins_Choice122 n = (Content_ins_Choice122)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice123) {
			Content_ins_Choice123 n = (Content_ins_Choice123)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice124) {
			Content_ins_Choice124 n = (Content_ins_Choice124)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice125) {
			Content_ins_Choice125 n = (Content_ins_Choice125)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice126) {
			Content_ins_Choice126 n = (Content_ins_Choice126)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice127) {
			Content_ins_Choice127 n = (Content_ins_Choice127)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice128) {
			Content_ins_Choice128 n = (Content_ins_Choice128)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice129) {
			Content_ins_Choice129 n = (Content_ins_Choice129)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice130) {
			Content_ins_Choice130 n = (Content_ins_Choice130)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice131) {
			Content_ins_Choice131 n = (Content_ins_Choice131)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice132) {
			Content_ins_Choice132 n = (Content_ins_Choice132)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice133) {
			Content_ins_Choice133 n = (Content_ins_Choice133)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice134) {
			Content_ins_Choice134 n = (Content_ins_Choice134)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice135) {
			Content_ins_Choice135 n = (Content_ins_Choice135)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice136) {
			Content_ins_Choice136 n = (Content_ins_Choice136)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice137) {
			Content_ins_Choice137 n = (Content_ins_Choice137)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice138) {
			Content_ins_Choice138 n = (Content_ins_Choice138)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice139) {
			Content_ins_Choice139 n = (Content_ins_Choice139)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice140) {
			Content_ins_Choice140 n = (Content_ins_Choice140)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice141) {
			Content_ins_Choice141 n = (Content_ins_Choice141)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice142) {
			Content_ins_Choice142 n = (Content_ins_Choice142)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice143) {
			Content_ins_Choice143 n = (Content_ins_Choice143)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice144) {
			Content_ins_Choice144 n = (Content_ins_Choice144)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice145) {
			Content_ins_Choice145 n = (Content_ins_Choice145)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice146) {
			Content_ins_Choice146 n = (Content_ins_Choice146)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice147) {
			Content_ins_Choice147 n = (Content_ins_Choice147)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice148) {
			Content_ins_Choice148 n = (Content_ins_Choice148)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice149) {
			Content_ins_Choice149 n = (Content_ins_Choice149)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice150) {
			Content_ins_Choice150 n = (Content_ins_Choice150)node;
			{
				Element_noscript v=n.getElement_noscript();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice151) {
			Content_ins_Choice151 n = (Content_ins_Choice151)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice152) {
			Content_ins_Choice152 n = (Content_ins_Choice152)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_ins_Choice153) {
			Content_ins_Choice153 n = (Content_ins_Choice153)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_map1) {
			Element_map1 n = (Element_map1)node;
			{
				EmptyTag_map v=n.getEmptyTag_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_map2) {
			Element_map2 n = (Element_map2)node;
			{
				STag_map v=n.getSTag_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				Content_map_Choice1 v=n.getContent_map_Choice1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ETag_map v=n.getETag_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_map) {
			EmptyTag_map n = (EmptyTag_map)node;
			printToken("<");
			printToken("map");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_map) {
			STag_map n = (STag_map)node;
			printToken("<");
			printToken("map");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_map) {
			ETag_map n = (ETag_map)node;
			printToken("</");
			printToken("map");
			printToken(">");
			return false;
		}
		if (node instanceof Content_map_Choice21) {
			Content_map_Choice21 n = (Content_map_Choice21)node;
			{
				Element_p v=n.getElement_p();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_map_Choice22) {
			Content_map_Choice22 n = (Content_map_Choice22)node;
			{
				Element_h1 v=n.getElement_h1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_map_Choice23) {
			Content_map_Choice23 n = (Content_map_Choice23)node;
			{
				Element_h2 v=n.getElement_h2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_map_Choice24) {
			Content_map_Choice24 n = (Content_map_Choice24)node;
			{
				Element_h3 v=n.getElement_h3();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_map_Choice25) {
			Content_map_Choice25 n = (Content_map_Choice25)node;
			{
				Element_h4 v=n.getElement_h4();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_map_Choice26) {
			Content_map_Choice26 n = (Content_map_Choice26)node;
			{
				Element_h5 v=n.getElement_h5();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_map_Choice27) {
			Content_map_Choice27 n = (Content_map_Choice27)node;
			{
				Element_h6 v=n.getElement_h6();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_map_Choice28) {
			Content_map_Choice28 n = (Content_map_Choice28)node;
			{
				Element_div v=n.getElement_div();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_map_Choice29) {
			Content_map_Choice29 n = (Content_map_Choice29)node;
			{
				Element_ul v=n.getElement_ul();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_map_Choice210) {
			Content_map_Choice210 n = (Content_map_Choice210)node;
			{
				Element_ol v=n.getElement_ol();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_map_Choice211) {
			Content_map_Choice211 n = (Content_map_Choice211)node;
			{
				Element_dl v=n.getElement_dl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_map_Choice212) {
			Content_map_Choice212 n = (Content_map_Choice212)node;
			{
				Element_pre v=n.getElement_pre();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_map_Choice213) {
			Content_map_Choice213 n = (Content_map_Choice213)node;
			{
				Element_hr v=n.getElement_hr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_map_Choice214) {
			Content_map_Choice214 n = (Content_map_Choice214)node;
			{
				Element_blockquote v=n.getElement_blockquote();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_map_Choice215) {
			Content_map_Choice215 n = (Content_map_Choice215)node;
			{
				Element_address v=n.getElement_address();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_map_Choice216) {
			Content_map_Choice216 n = (Content_map_Choice216)node;
			{
				Element_fieldset v=n.getElement_fieldset();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_map_Choice217) {
			Content_map_Choice217 n = (Content_map_Choice217)node;
			{
				Element_table v=n.getElement_table();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_map_Choice218) {
			Content_map_Choice218 n = (Content_map_Choice218)node;
			{
				Element_form v=n.getElement_form();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_map_Choice219) {
			Content_map_Choice219 n = (Content_map_Choice219)node;
			{
				Element_noscript v=n.getElement_noscript();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_map_Choice220) {
			Content_map_Choice220 n = (Content_map_Choice220)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_map_Choice221) {
			Content_map_Choice221 n = (Content_map_Choice221)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_map_Choice222) {
			Content_map_Choice222 n = (Content_map_Choice222)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_map_Choice11) {
			Content_map_Choice11 n = (Content_map_Choice11)node;
			for (Content_map_Choice2 v : n.getContent_map_Choice2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Content_map_Choice12) {
			Content_map_Choice12 n = (Content_map_Choice12)node;
			for (Element_area v : n.getElement_area()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_dd1) {
			Element_dd1 n = (Element_dd1)node;
			{
				EmptyTag_dd v=n.getEmptyTag_dd();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_dd2) {
			Element_dd2 n = (Element_dd2)node;
			{
				STag_dd v=n.getSTag_dd();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_dd_Choice1 v : n.getContent_dd_Choice1()) {
				v.accept(this);
			}
			{
				ETag_dd v=n.getETag_dd();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_dd) {
			EmptyTag_dd n = (EmptyTag_dd)node;
			printToken("<");
			printToken("dd");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_dd) {
			STag_dd n = (STag_dd)node;
			printToken("<");
			printToken("dd");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_dd) {
			ETag_dd n = (ETag_dd)node;
			printToken("</");
			printToken("dd");
			printToken(">");
			return false;
		}
		if (node instanceof Content_dd_Choice11) {
			Content_dd_Choice11 n = (Content_dd_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice12) {
			Content_dd_Choice12 n = (Content_dd_Choice12)node;
			{
				Element_p v=n.getElement_p();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice13) {
			Content_dd_Choice13 n = (Content_dd_Choice13)node;
			{
				Element_h1 v=n.getElement_h1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice14) {
			Content_dd_Choice14 n = (Content_dd_Choice14)node;
			{
				Element_h2 v=n.getElement_h2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice15) {
			Content_dd_Choice15 n = (Content_dd_Choice15)node;
			{
				Element_h3 v=n.getElement_h3();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice16) {
			Content_dd_Choice16 n = (Content_dd_Choice16)node;
			{
				Element_h4 v=n.getElement_h4();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice17) {
			Content_dd_Choice17 n = (Content_dd_Choice17)node;
			{
				Element_h5 v=n.getElement_h5();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice18) {
			Content_dd_Choice18 n = (Content_dd_Choice18)node;
			{
				Element_h6 v=n.getElement_h6();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice19) {
			Content_dd_Choice19 n = (Content_dd_Choice19)node;
			{
				Element_div v=n.getElement_div();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice110) {
			Content_dd_Choice110 n = (Content_dd_Choice110)node;
			{
				Element_ul v=n.getElement_ul();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice111) {
			Content_dd_Choice111 n = (Content_dd_Choice111)node;
			{
				Element_ol v=n.getElement_ol();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice112) {
			Content_dd_Choice112 n = (Content_dd_Choice112)node;
			{
				Element_dl v=n.getElement_dl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice113) {
			Content_dd_Choice113 n = (Content_dd_Choice113)node;
			{
				Element_pre v=n.getElement_pre();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice114) {
			Content_dd_Choice114 n = (Content_dd_Choice114)node;
			{
				Element_hr v=n.getElement_hr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice115) {
			Content_dd_Choice115 n = (Content_dd_Choice115)node;
			{
				Element_blockquote v=n.getElement_blockquote();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice116) {
			Content_dd_Choice116 n = (Content_dd_Choice116)node;
			{
				Element_address v=n.getElement_address();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice117) {
			Content_dd_Choice117 n = (Content_dd_Choice117)node;
			{
				Element_fieldset v=n.getElement_fieldset();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice118) {
			Content_dd_Choice118 n = (Content_dd_Choice118)node;
			{
				Element_table v=n.getElement_table();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice119) {
			Content_dd_Choice119 n = (Content_dd_Choice119)node;
			{
				Element_form v=n.getElement_form();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice120) {
			Content_dd_Choice120 n = (Content_dd_Choice120)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice121) {
			Content_dd_Choice121 n = (Content_dd_Choice121)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice122) {
			Content_dd_Choice122 n = (Content_dd_Choice122)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice123) {
			Content_dd_Choice123 n = (Content_dd_Choice123)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice124) {
			Content_dd_Choice124 n = (Content_dd_Choice124)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice125) {
			Content_dd_Choice125 n = (Content_dd_Choice125)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice126) {
			Content_dd_Choice126 n = (Content_dd_Choice126)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice127) {
			Content_dd_Choice127 n = (Content_dd_Choice127)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice128) {
			Content_dd_Choice128 n = (Content_dd_Choice128)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice129) {
			Content_dd_Choice129 n = (Content_dd_Choice129)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice130) {
			Content_dd_Choice130 n = (Content_dd_Choice130)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice131) {
			Content_dd_Choice131 n = (Content_dd_Choice131)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice132) {
			Content_dd_Choice132 n = (Content_dd_Choice132)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice133) {
			Content_dd_Choice133 n = (Content_dd_Choice133)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice134) {
			Content_dd_Choice134 n = (Content_dd_Choice134)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice135) {
			Content_dd_Choice135 n = (Content_dd_Choice135)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice136) {
			Content_dd_Choice136 n = (Content_dd_Choice136)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice137) {
			Content_dd_Choice137 n = (Content_dd_Choice137)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice138) {
			Content_dd_Choice138 n = (Content_dd_Choice138)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice139) {
			Content_dd_Choice139 n = (Content_dd_Choice139)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice140) {
			Content_dd_Choice140 n = (Content_dd_Choice140)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice141) {
			Content_dd_Choice141 n = (Content_dd_Choice141)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice142) {
			Content_dd_Choice142 n = (Content_dd_Choice142)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice143) {
			Content_dd_Choice143 n = (Content_dd_Choice143)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice144) {
			Content_dd_Choice144 n = (Content_dd_Choice144)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice145) {
			Content_dd_Choice145 n = (Content_dd_Choice145)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice146) {
			Content_dd_Choice146 n = (Content_dd_Choice146)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice147) {
			Content_dd_Choice147 n = (Content_dd_Choice147)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice148) {
			Content_dd_Choice148 n = (Content_dd_Choice148)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice149) {
			Content_dd_Choice149 n = (Content_dd_Choice149)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice150) {
			Content_dd_Choice150 n = (Content_dd_Choice150)node;
			{
				Element_noscript v=n.getElement_noscript();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice151) {
			Content_dd_Choice151 n = (Content_dd_Choice151)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice152) {
			Content_dd_Choice152 n = (Content_dd_Choice152)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dd_Choice153) {
			Content_dd_Choice153 n = (Content_dd_Choice153)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_fieldset1) {
			Element_fieldset1 n = (Element_fieldset1)node;
			{
				EmptyTag_fieldset v=n.getEmptyTag_fieldset();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_fieldset2) {
			Element_fieldset2 n = (Element_fieldset2)node;
			{
				STag_fieldset v=n.getSTag_fieldset();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_fieldset_Choice1 v : n.getContent_fieldset_Choice1()) {
				v.accept(this);
			}
			{
				ETag_fieldset v=n.getETag_fieldset();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_fieldset) {
			EmptyTag_fieldset n = (EmptyTag_fieldset)node;
			printToken("<");
			printToken("fieldset");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_fieldset) {
			STag_fieldset n = (STag_fieldset)node;
			printToken("<");
			printToken("fieldset");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_fieldset) {
			ETag_fieldset n = (ETag_fieldset)node;
			printToken("</");
			printToken("fieldset");
			printToken(">");
			return false;
		}
		if (node instanceof Content_fieldset_Choice11) {
			Content_fieldset_Choice11 n = (Content_fieldset_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice12) {
			Content_fieldset_Choice12 n = (Content_fieldset_Choice12)node;
			{
				Element_legend v=n.getElement_legend();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice13) {
			Content_fieldset_Choice13 n = (Content_fieldset_Choice13)node;
			{
				Element_p v=n.getElement_p();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice14) {
			Content_fieldset_Choice14 n = (Content_fieldset_Choice14)node;
			{
				Element_h1 v=n.getElement_h1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice15) {
			Content_fieldset_Choice15 n = (Content_fieldset_Choice15)node;
			{
				Element_h2 v=n.getElement_h2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice16) {
			Content_fieldset_Choice16 n = (Content_fieldset_Choice16)node;
			{
				Element_h3 v=n.getElement_h3();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice17) {
			Content_fieldset_Choice17 n = (Content_fieldset_Choice17)node;
			{
				Element_h4 v=n.getElement_h4();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice18) {
			Content_fieldset_Choice18 n = (Content_fieldset_Choice18)node;
			{
				Element_h5 v=n.getElement_h5();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice19) {
			Content_fieldset_Choice19 n = (Content_fieldset_Choice19)node;
			{
				Element_h6 v=n.getElement_h6();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice110) {
			Content_fieldset_Choice110 n = (Content_fieldset_Choice110)node;
			{
				Element_div v=n.getElement_div();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice111) {
			Content_fieldset_Choice111 n = (Content_fieldset_Choice111)node;
			{
				Element_ul v=n.getElement_ul();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice112) {
			Content_fieldset_Choice112 n = (Content_fieldset_Choice112)node;
			{
				Element_ol v=n.getElement_ol();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice113) {
			Content_fieldset_Choice113 n = (Content_fieldset_Choice113)node;
			{
				Element_dl v=n.getElement_dl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice114) {
			Content_fieldset_Choice114 n = (Content_fieldset_Choice114)node;
			{
				Element_pre v=n.getElement_pre();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice115) {
			Content_fieldset_Choice115 n = (Content_fieldset_Choice115)node;
			{
				Element_hr v=n.getElement_hr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice116) {
			Content_fieldset_Choice116 n = (Content_fieldset_Choice116)node;
			{
				Element_blockquote v=n.getElement_blockquote();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice117) {
			Content_fieldset_Choice117 n = (Content_fieldset_Choice117)node;
			{
				Element_address v=n.getElement_address();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice118) {
			Content_fieldset_Choice118 n = (Content_fieldset_Choice118)node;
			{
				Element_fieldset v=n.getElement_fieldset();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice119) {
			Content_fieldset_Choice119 n = (Content_fieldset_Choice119)node;
			{
				Element_table v=n.getElement_table();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice120) {
			Content_fieldset_Choice120 n = (Content_fieldset_Choice120)node;
			{
				Element_form v=n.getElement_form();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice121) {
			Content_fieldset_Choice121 n = (Content_fieldset_Choice121)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice122) {
			Content_fieldset_Choice122 n = (Content_fieldset_Choice122)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice123) {
			Content_fieldset_Choice123 n = (Content_fieldset_Choice123)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice124) {
			Content_fieldset_Choice124 n = (Content_fieldset_Choice124)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice125) {
			Content_fieldset_Choice125 n = (Content_fieldset_Choice125)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice126) {
			Content_fieldset_Choice126 n = (Content_fieldset_Choice126)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice127) {
			Content_fieldset_Choice127 n = (Content_fieldset_Choice127)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice128) {
			Content_fieldset_Choice128 n = (Content_fieldset_Choice128)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice129) {
			Content_fieldset_Choice129 n = (Content_fieldset_Choice129)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice130) {
			Content_fieldset_Choice130 n = (Content_fieldset_Choice130)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice131) {
			Content_fieldset_Choice131 n = (Content_fieldset_Choice131)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice132) {
			Content_fieldset_Choice132 n = (Content_fieldset_Choice132)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice133) {
			Content_fieldset_Choice133 n = (Content_fieldset_Choice133)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice134) {
			Content_fieldset_Choice134 n = (Content_fieldset_Choice134)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice135) {
			Content_fieldset_Choice135 n = (Content_fieldset_Choice135)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice136) {
			Content_fieldset_Choice136 n = (Content_fieldset_Choice136)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice137) {
			Content_fieldset_Choice137 n = (Content_fieldset_Choice137)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice138) {
			Content_fieldset_Choice138 n = (Content_fieldset_Choice138)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice139) {
			Content_fieldset_Choice139 n = (Content_fieldset_Choice139)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice140) {
			Content_fieldset_Choice140 n = (Content_fieldset_Choice140)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice141) {
			Content_fieldset_Choice141 n = (Content_fieldset_Choice141)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice142) {
			Content_fieldset_Choice142 n = (Content_fieldset_Choice142)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice143) {
			Content_fieldset_Choice143 n = (Content_fieldset_Choice143)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice144) {
			Content_fieldset_Choice144 n = (Content_fieldset_Choice144)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice145) {
			Content_fieldset_Choice145 n = (Content_fieldset_Choice145)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice146) {
			Content_fieldset_Choice146 n = (Content_fieldset_Choice146)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice147) {
			Content_fieldset_Choice147 n = (Content_fieldset_Choice147)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice148) {
			Content_fieldset_Choice148 n = (Content_fieldset_Choice148)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice149) {
			Content_fieldset_Choice149 n = (Content_fieldset_Choice149)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice150) {
			Content_fieldset_Choice150 n = (Content_fieldset_Choice150)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice151) {
			Content_fieldset_Choice151 n = (Content_fieldset_Choice151)node;
			{
				Element_noscript v=n.getElement_noscript();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice152) {
			Content_fieldset_Choice152 n = (Content_fieldset_Choice152)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice153) {
			Content_fieldset_Choice153 n = (Content_fieldset_Choice153)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_fieldset_Choice154) {
			Content_fieldset_Choice154 n = (Content_fieldset_Choice154)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_head1) {
			Element_head1 n = (Element_head1)node;
			{
				EmptyTag_head v=n.getEmptyTag_head();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_head2) {
			Element_head2 n = (Element_head2)node;
			{
				STag_head v=n.getSTag_head();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				Content_head_Seq1 v=n.getContent_head_Seq1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ETag_head v=n.getETag_head();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_head) {
			EmptyTag_head n = (EmptyTag_head)node;
			printToken("<");
			printToken("head");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_head) {
			STag_head n = (STag_head)node;
			printToken("<");
			printToken("head");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_head) {
			ETag_head n = (ETag_head)node;
			printToken("</");
			printToken("head");
			printToken(">");
			return false;
		}
		if (node instanceof Content_head_Choice11) {
			Content_head_Choice11 n = (Content_head_Choice11)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice12) {
			Content_head_Choice12 n = (Content_head_Choice12)node;
			{
				Element_style v=n.getElement_style();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice13) {
			Content_head_Choice13 n = (Content_head_Choice13)node;
			{
				Element_meta v=n.getElement_meta();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice14) {
			Content_head_Choice14 n = (Content_head_Choice14)node;
			{
				Element_link v=n.getElement_link();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice15) {
			Content_head_Choice15 n = (Content_head_Choice15)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice31) {
			Content_head_Choice31 n = (Content_head_Choice31)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice32) {
			Content_head_Choice32 n = (Content_head_Choice32)node;
			{
				Element_style v=n.getElement_style();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice33) {
			Content_head_Choice33 n = (Content_head_Choice33)node;
			{
				Element_meta v=n.getElement_meta();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice34) {
			Content_head_Choice34 n = (Content_head_Choice34)node;
			{
				Element_link v=n.getElement_link();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice35) {
			Content_head_Choice35 n = (Content_head_Choice35)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice41) {
			Content_head_Choice41 n = (Content_head_Choice41)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice42) {
			Content_head_Choice42 n = (Content_head_Choice42)node;
			{
				Element_style v=n.getElement_style();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice43) {
			Content_head_Choice43 n = (Content_head_Choice43)node;
			{
				Element_meta v=n.getElement_meta();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice44) {
			Content_head_Choice44 n = (Content_head_Choice44)node;
			{
				Element_link v=n.getElement_link();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice45) {
			Content_head_Choice45 n = (Content_head_Choice45)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Seq3) {
			Content_head_Seq3 n = (Content_head_Seq3)node;
			{
				Element_base v=n.getElement_base();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (Content_head_Choice4 v : n.getContent_head_Choice4()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Content_head_Seq2) {
			Content_head_Seq2 n = (Content_head_Seq2)node;
			{
				Element_title v=n.getElement_title();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (Content_head_Choice3 v : n.getContent_head_Choice3()) {
				v.accept(this);
			}
			{
				Content_head_Seq3 v=n.getContent_head_Seq3();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice51) {
			Content_head_Choice51 n = (Content_head_Choice51)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice52) {
			Content_head_Choice52 n = (Content_head_Choice52)node;
			{
				Element_style v=n.getElement_style();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice53) {
			Content_head_Choice53 n = (Content_head_Choice53)node;
			{
				Element_meta v=n.getElement_meta();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice54) {
			Content_head_Choice54 n = (Content_head_Choice54)node;
			{
				Element_link v=n.getElement_link();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice55) {
			Content_head_Choice55 n = (Content_head_Choice55)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice61) {
			Content_head_Choice61 n = (Content_head_Choice61)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice62) {
			Content_head_Choice62 n = (Content_head_Choice62)node;
			{
				Element_style v=n.getElement_style();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice63) {
			Content_head_Choice63 n = (Content_head_Choice63)node;
			{
				Element_meta v=n.getElement_meta();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice64) {
			Content_head_Choice64 n = (Content_head_Choice64)node;
			{
				Element_link v=n.getElement_link();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice65) {
			Content_head_Choice65 n = (Content_head_Choice65)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Seq5) {
			Content_head_Seq5 n = (Content_head_Seq5)node;
			{
				Element_title v=n.getElement_title();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (Content_head_Choice6 v : n.getContent_head_Choice6()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Content_head_Seq4) {
			Content_head_Seq4 n = (Content_head_Seq4)node;
			{
				Element_base v=n.getElement_base();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (Content_head_Choice5 v : n.getContent_head_Choice5()) {
				v.accept(this);
			}
			{
				Content_head_Seq5 v=n.getContent_head_Seq5();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice21) {
			Content_head_Choice21 n = (Content_head_Choice21)node;
			{
				Content_head_Seq2 v=n.getContent_head_Seq2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Choice22) {
			Content_head_Choice22 n = (Content_head_Choice22)node;
			{
				Content_head_Seq4 v=n.getContent_head_Seq4();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_head_Seq1) {
			Content_head_Seq1 n = (Content_head_Seq1)node;
			for (Content_head_Choice1 v : n.getContent_head_Choice1()) {
				v.accept(this);
			}
			{
				Content_head_Choice2 v=n.getContent_head_Choice2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_col1) {
			Element_col1 n = (Element_col1)node;
			{
				EmptyTag_col v=n.getEmptyTag_col();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_col2) {
			Element_col2 n = (Element_col2)node;
			{
				STag_col v=n.getSTag_col();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				ETag_col v=n.getETag_col();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_col) {
			EmptyTag_col n = (EmptyTag_col)node;
			printToken("<");
			printToken("col");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_col) {
			STag_col n = (STag_col)node;
			printToken("<");
			printToken("col");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_col) {
			ETag_col n = (ETag_col)node;
			printToken("</");
			printToken("col");
			printToken(">");
			return false;
		}
		if (node instanceof Element_base1) {
			Element_base1 n = (Element_base1)node;
			{
				EmptyTag_base v=n.getEmptyTag_base();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_base2) {
			Element_base2 n = (Element_base2)node;
			{
				STag_base v=n.getSTag_base();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				ETag_base v=n.getETag_base();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_base) {
			EmptyTag_base n = (EmptyTag_base)node;
			printToken("<");
			printToken("base");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_base) {
			STag_base n = (STag_base)node;
			printToken("<");
			printToken("base");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_base) {
			ETag_base n = (ETag_base)node;
			printToken("</");
			printToken("base");
			printToken(">");
			return false;
		}
		if (node instanceof Element_big1) {
			Element_big1 n = (Element_big1)node;
			{
				EmptyTag_big v=n.getEmptyTag_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_big2) {
			Element_big2 n = (Element_big2)node;
			{
				STag_big v=n.getSTag_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_big_Choice1 v : n.getContent_big_Choice1()) {
				v.accept(this);
			}
			{
				ETag_big v=n.getETag_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_big) {
			EmptyTag_big n = (EmptyTag_big)node;
			printToken("<");
			printToken("big");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_big) {
			STag_big n = (STag_big)node;
			printToken("<");
			printToken("big");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_big) {
			ETag_big n = (ETag_big)node;
			printToken("</");
			printToken("big");
			printToken(">");
			return false;
		}
		if (node instanceof Content_big_Choice11) {
			Content_big_Choice11 n = (Content_big_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice12) {
			Content_big_Choice12 n = (Content_big_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice13) {
			Content_big_Choice13 n = (Content_big_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice14) {
			Content_big_Choice14 n = (Content_big_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice15) {
			Content_big_Choice15 n = (Content_big_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice16) {
			Content_big_Choice16 n = (Content_big_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice17) {
			Content_big_Choice17 n = (Content_big_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice18) {
			Content_big_Choice18 n = (Content_big_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice19) {
			Content_big_Choice19 n = (Content_big_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice110) {
			Content_big_Choice110 n = (Content_big_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice111) {
			Content_big_Choice111 n = (Content_big_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice112) {
			Content_big_Choice112 n = (Content_big_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice113) {
			Content_big_Choice113 n = (Content_big_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice114) {
			Content_big_Choice114 n = (Content_big_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice115) {
			Content_big_Choice115 n = (Content_big_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice116) {
			Content_big_Choice116 n = (Content_big_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice117) {
			Content_big_Choice117 n = (Content_big_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice118) {
			Content_big_Choice118 n = (Content_big_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice119) {
			Content_big_Choice119 n = (Content_big_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice120) {
			Content_big_Choice120 n = (Content_big_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice121) {
			Content_big_Choice121 n = (Content_big_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice122) {
			Content_big_Choice122 n = (Content_big_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice123) {
			Content_big_Choice123 n = (Content_big_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice124) {
			Content_big_Choice124 n = (Content_big_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice125) {
			Content_big_Choice125 n = (Content_big_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice126) {
			Content_big_Choice126 n = (Content_big_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice127) {
			Content_big_Choice127 n = (Content_big_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice128) {
			Content_big_Choice128 n = (Content_big_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice129) {
			Content_big_Choice129 n = (Content_big_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice130) {
			Content_big_Choice130 n = (Content_big_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice131) {
			Content_big_Choice131 n = (Content_big_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice132) {
			Content_big_Choice132 n = (Content_big_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice133) {
			Content_big_Choice133 n = (Content_big_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_big_Choice134) {
			Content_big_Choice134 n = (Content_big_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_meta1) {
			Element_meta1 n = (Element_meta1)node;
			{
				EmptyTag_meta v=n.getEmptyTag_meta();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_meta2) {
			Element_meta2 n = (Element_meta2)node;
			{
				STag_meta v=n.getSTag_meta();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				ETag_meta v=n.getETag_meta();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_meta) {
			EmptyTag_meta n = (EmptyTag_meta)node;
			printToken("<");
			printToken("meta");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_meta) {
			STag_meta n = (STag_meta)node;
			printToken("<");
			printToken("meta");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_meta) {
			ETag_meta n = (ETag_meta)node;
			printToken("</");
			printToken("meta");
			printToken(">");
			return false;
		}
		if (node instanceof Element_code1) {
			Element_code1 n = (Element_code1)node;
			{
				EmptyTag_code v=n.getEmptyTag_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_code2) {
			Element_code2 n = (Element_code2)node;
			{
				STag_code v=n.getSTag_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_code_Choice1 v : n.getContent_code_Choice1()) {
				v.accept(this);
			}
			{
				ETag_code v=n.getETag_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_code) {
			EmptyTag_code n = (EmptyTag_code)node;
			printToken("<");
			printToken("code");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_code) {
			STag_code n = (STag_code)node;
			printToken("<");
			printToken("code");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_code) {
			ETag_code n = (ETag_code)node;
			printToken("</");
			printToken("code");
			printToken(">");
			return false;
		}
		if (node instanceof Content_code_Choice11) {
			Content_code_Choice11 n = (Content_code_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice12) {
			Content_code_Choice12 n = (Content_code_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice13) {
			Content_code_Choice13 n = (Content_code_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice14) {
			Content_code_Choice14 n = (Content_code_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice15) {
			Content_code_Choice15 n = (Content_code_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice16) {
			Content_code_Choice16 n = (Content_code_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice17) {
			Content_code_Choice17 n = (Content_code_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice18) {
			Content_code_Choice18 n = (Content_code_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice19) {
			Content_code_Choice19 n = (Content_code_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice110) {
			Content_code_Choice110 n = (Content_code_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice111) {
			Content_code_Choice111 n = (Content_code_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice112) {
			Content_code_Choice112 n = (Content_code_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice113) {
			Content_code_Choice113 n = (Content_code_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice114) {
			Content_code_Choice114 n = (Content_code_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice115) {
			Content_code_Choice115 n = (Content_code_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice116) {
			Content_code_Choice116 n = (Content_code_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice117) {
			Content_code_Choice117 n = (Content_code_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice118) {
			Content_code_Choice118 n = (Content_code_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice119) {
			Content_code_Choice119 n = (Content_code_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice120) {
			Content_code_Choice120 n = (Content_code_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice121) {
			Content_code_Choice121 n = (Content_code_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice122) {
			Content_code_Choice122 n = (Content_code_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice123) {
			Content_code_Choice123 n = (Content_code_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice124) {
			Content_code_Choice124 n = (Content_code_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice125) {
			Content_code_Choice125 n = (Content_code_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice126) {
			Content_code_Choice126 n = (Content_code_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice127) {
			Content_code_Choice127 n = (Content_code_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice128) {
			Content_code_Choice128 n = (Content_code_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice129) {
			Content_code_Choice129 n = (Content_code_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice130) {
			Content_code_Choice130 n = (Content_code_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice131) {
			Content_code_Choice131 n = (Content_code_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice132) {
			Content_code_Choice132 n = (Content_code_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice133) {
			Content_code_Choice133 n = (Content_code_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_code_Choice134) {
			Content_code_Choice134 n = (Content_code_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_tbody1) {
			Element_tbody1 n = (Element_tbody1)node;
			{
				EmptyTag_tbody v=n.getEmptyTag_tbody();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_tbody2) {
			Element_tbody2 n = (Element_tbody2)node;
			{
				STag_tbody v=n.getSTag_tbody();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_tbody_Seq1 v : n.getContent_tbody_Seq1()) {
				v.accept(this);
			}
			{
				ETag_tbody v=n.getETag_tbody();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_tbody) {
			EmptyTag_tbody n = (EmptyTag_tbody)node;
			printToken("<");
			printToken("tbody");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_tbody) {
			STag_tbody n = (STag_tbody)node;
			printToken("<");
			printToken("tbody");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_tbody) {
			ETag_tbody n = (ETag_tbody)node;
			printToken("</");
			printToken("tbody");
			printToken(">");
			return false;
		}
		if (node instanceof Content_tbody_Seq1) {
			Content_tbody_Seq1 n = (Content_tbody_Seq1)node;
			{
				Element_tr v=n.getElement_tr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_option1) {
			Element_option1 n = (Element_option1)node;
			{
				EmptyTag_option v=n.getEmptyTag_option();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_option2) {
			Element_option2 n = (Element_option2)node;
			{
				STag_option v=n.getSTag_option();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				Content_option_Seq1 v=n.getContent_option_Seq1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ETag_option v=n.getETag_option();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_option) {
			EmptyTag_option n = (EmptyTag_option)node;
			printToken("<");
			printToken("option");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_option) {
			STag_option n = (STag_option)node;
			printToken("<");
			printToken("option");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_option) {
			ETag_option n = (ETag_option)node;
			printToken("</");
			printToken("option");
			printToken(">");
			return false;
		}
		if (node instanceof Content_option_Seq1) {
			Content_option_Seq1 n = (Content_option_Seq1)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_q1) {
			Element_q1 n = (Element_q1)node;
			{
				EmptyTag_q v=n.getEmptyTag_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_q2) {
			Element_q2 n = (Element_q2)node;
			{
				STag_q v=n.getSTag_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_q_Choice1 v : n.getContent_q_Choice1()) {
				v.accept(this);
			}
			{
				ETag_q v=n.getETag_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_q) {
			EmptyTag_q n = (EmptyTag_q)node;
			printToken("<");
			printToken("q");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_q) {
			STag_q n = (STag_q)node;
			printToken("<");
			printToken("q");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_q) {
			ETag_q n = (ETag_q)node;
			printToken("</");
			printToken("q");
			printToken(">");
			return false;
		}
		if (node instanceof Content_q_Choice11) {
			Content_q_Choice11 n = (Content_q_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice12) {
			Content_q_Choice12 n = (Content_q_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice13) {
			Content_q_Choice13 n = (Content_q_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice14) {
			Content_q_Choice14 n = (Content_q_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice15) {
			Content_q_Choice15 n = (Content_q_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice16) {
			Content_q_Choice16 n = (Content_q_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice17) {
			Content_q_Choice17 n = (Content_q_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice18) {
			Content_q_Choice18 n = (Content_q_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice19) {
			Content_q_Choice19 n = (Content_q_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice110) {
			Content_q_Choice110 n = (Content_q_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice111) {
			Content_q_Choice111 n = (Content_q_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice112) {
			Content_q_Choice112 n = (Content_q_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice113) {
			Content_q_Choice113 n = (Content_q_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice114) {
			Content_q_Choice114 n = (Content_q_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice115) {
			Content_q_Choice115 n = (Content_q_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice116) {
			Content_q_Choice116 n = (Content_q_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice117) {
			Content_q_Choice117 n = (Content_q_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice118) {
			Content_q_Choice118 n = (Content_q_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice119) {
			Content_q_Choice119 n = (Content_q_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice120) {
			Content_q_Choice120 n = (Content_q_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice121) {
			Content_q_Choice121 n = (Content_q_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice122) {
			Content_q_Choice122 n = (Content_q_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice123) {
			Content_q_Choice123 n = (Content_q_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice124) {
			Content_q_Choice124 n = (Content_q_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice125) {
			Content_q_Choice125 n = (Content_q_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice126) {
			Content_q_Choice126 n = (Content_q_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice127) {
			Content_q_Choice127 n = (Content_q_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice128) {
			Content_q_Choice128 n = (Content_q_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice129) {
			Content_q_Choice129 n = (Content_q_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice130) {
			Content_q_Choice130 n = (Content_q_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice131) {
			Content_q_Choice131 n = (Content_q_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice132) {
			Content_q_Choice132 n = (Content_q_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice133) {
			Content_q_Choice133 n = (Content_q_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_q_Choice134) {
			Content_q_Choice134 n = (Content_q_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_p1) {
			Element_p1 n = (Element_p1)node;
			{
				EmptyTag_p v=n.getEmptyTag_p();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_p2) {
			Element_p2 n = (Element_p2)node;
			{
				STag_p v=n.getSTag_p();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_p_Choice1 v : n.getContent_p_Choice1()) {
				v.accept(this);
			}
			{
				ETag_p v=n.getETag_p();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_p) {
			EmptyTag_p n = (EmptyTag_p)node;
			printToken("<");
			printToken("p");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_p) {
			STag_p n = (STag_p)node;
			printToken("<");
			printToken("p");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_p) {
			ETag_p n = (ETag_p)node;
			printToken("</");
			printToken("p");
			printToken(">");
			return false;
		}
		if (node instanceof Content_p_Choice11) {
			Content_p_Choice11 n = (Content_p_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice12) {
			Content_p_Choice12 n = (Content_p_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice13) {
			Content_p_Choice13 n = (Content_p_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice14) {
			Content_p_Choice14 n = (Content_p_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice15) {
			Content_p_Choice15 n = (Content_p_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice16) {
			Content_p_Choice16 n = (Content_p_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice17) {
			Content_p_Choice17 n = (Content_p_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice18) {
			Content_p_Choice18 n = (Content_p_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice19) {
			Content_p_Choice19 n = (Content_p_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice110) {
			Content_p_Choice110 n = (Content_p_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice111) {
			Content_p_Choice111 n = (Content_p_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice112) {
			Content_p_Choice112 n = (Content_p_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice113) {
			Content_p_Choice113 n = (Content_p_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice114) {
			Content_p_Choice114 n = (Content_p_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice115) {
			Content_p_Choice115 n = (Content_p_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice116) {
			Content_p_Choice116 n = (Content_p_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice117) {
			Content_p_Choice117 n = (Content_p_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice118) {
			Content_p_Choice118 n = (Content_p_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice119) {
			Content_p_Choice119 n = (Content_p_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice120) {
			Content_p_Choice120 n = (Content_p_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice121) {
			Content_p_Choice121 n = (Content_p_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice122) {
			Content_p_Choice122 n = (Content_p_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice123) {
			Content_p_Choice123 n = (Content_p_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice124) {
			Content_p_Choice124 n = (Content_p_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice125) {
			Content_p_Choice125 n = (Content_p_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice126) {
			Content_p_Choice126 n = (Content_p_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice127) {
			Content_p_Choice127 n = (Content_p_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice128) {
			Content_p_Choice128 n = (Content_p_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice129) {
			Content_p_Choice129 n = (Content_p_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice130) {
			Content_p_Choice130 n = (Content_p_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice131) {
			Content_p_Choice131 n = (Content_p_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice132) {
			Content_p_Choice132 n = (Content_p_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice133) {
			Content_p_Choice133 n = (Content_p_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_p_Choice134) {
			Content_p_Choice134 n = (Content_p_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_ol1) {
			Element_ol1 n = (Element_ol1)node;
			{
				EmptyTag_ol v=n.getEmptyTag_ol();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_ol2) {
			Element_ol2 n = (Element_ol2)node;
			{
				STag_ol v=n.getSTag_ol();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_ol_Seq1 v : n.getContent_ol_Seq1()) {
				v.accept(this);
			}
			{
				ETag_ol v=n.getETag_ol();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_ol) {
			EmptyTag_ol n = (EmptyTag_ol)node;
			printToken("<");
			printToken("ol");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_ol) {
			STag_ol n = (STag_ol)node;
			printToken("<");
			printToken("ol");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_ol) {
			ETag_ol n = (ETag_ol)node;
			printToken("</");
			printToken("ol");
			printToken(">");
			return false;
		}
		if (node instanceof Content_ol_Seq1) {
			Content_ol_Seq1 n = (Content_ol_Seq1)node;
			{
				Element_li v=n.getElement_li();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_thead1) {
			Element_thead1 n = (Element_thead1)node;
			{
				EmptyTag_thead v=n.getEmptyTag_thead();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_thead2) {
			Element_thead2 n = (Element_thead2)node;
			{
				STag_thead v=n.getSTag_thead();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_thead_Seq1 v : n.getContent_thead_Seq1()) {
				v.accept(this);
			}
			{
				ETag_thead v=n.getETag_thead();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_thead) {
			EmptyTag_thead n = (EmptyTag_thead)node;
			printToken("<");
			printToken("thead");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_thead) {
			STag_thead n = (STag_thead)node;
			printToken("<");
			printToken("thead");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_thead) {
			ETag_thead n = (ETag_thead)node;
			printToken("</");
			printToken("thead");
			printToken(">");
			return false;
		}
		if (node instanceof Content_thead_Seq1) {
			Content_thead_Seq1 n = (Content_thead_Seq1)node;
			{
				Element_tr v=n.getElement_tr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_ul1) {
			Element_ul1 n = (Element_ul1)node;
			{
				EmptyTag_ul v=n.getEmptyTag_ul();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_ul2) {
			Element_ul2 n = (Element_ul2)node;
			{
				STag_ul v=n.getSTag_ul();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_ul_Seq1 v : n.getContent_ul_Seq1()) {
				v.accept(this);
			}
			{
				ETag_ul v=n.getETag_ul();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_ul) {
			EmptyTag_ul n = (EmptyTag_ul)node;
			printToken("<");
			printToken("ul");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_ul) {
			STag_ul n = (STag_ul)node;
			printToken("<");
			printToken("ul");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_ul) {
			ETag_ul n = (ETag_ul)node;
			printToken("</");
			printToken("ul");
			printToken(">");
			return false;
		}
		if (node instanceof Content_ul_Seq1) {
			Content_ul_Seq1 n = (Content_ul_Seq1)node;
			{
				Element_li v=n.getElement_li();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_i1) {
			Element_i1 n = (Element_i1)node;
			{
				EmptyTag_i v=n.getEmptyTag_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_i2) {
			Element_i2 n = (Element_i2)node;
			{
				STag_i v=n.getSTag_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_i_Choice1 v : n.getContent_i_Choice1()) {
				v.accept(this);
			}
			{
				ETag_i v=n.getETag_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_i) {
			EmptyTag_i n = (EmptyTag_i)node;
			printToken("<");
			printToken("i");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_i) {
			STag_i n = (STag_i)node;
			printToken("<");
			printToken("i");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_i) {
			ETag_i n = (ETag_i)node;
			printToken("</");
			printToken("i");
			printToken(">");
			return false;
		}
		if (node instanceof Content_i_Choice11) {
			Content_i_Choice11 n = (Content_i_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice12) {
			Content_i_Choice12 n = (Content_i_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice13) {
			Content_i_Choice13 n = (Content_i_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice14) {
			Content_i_Choice14 n = (Content_i_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice15) {
			Content_i_Choice15 n = (Content_i_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice16) {
			Content_i_Choice16 n = (Content_i_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice17) {
			Content_i_Choice17 n = (Content_i_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice18) {
			Content_i_Choice18 n = (Content_i_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice19) {
			Content_i_Choice19 n = (Content_i_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice110) {
			Content_i_Choice110 n = (Content_i_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice111) {
			Content_i_Choice111 n = (Content_i_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice112) {
			Content_i_Choice112 n = (Content_i_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice113) {
			Content_i_Choice113 n = (Content_i_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice114) {
			Content_i_Choice114 n = (Content_i_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice115) {
			Content_i_Choice115 n = (Content_i_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice116) {
			Content_i_Choice116 n = (Content_i_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice117) {
			Content_i_Choice117 n = (Content_i_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice118) {
			Content_i_Choice118 n = (Content_i_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice119) {
			Content_i_Choice119 n = (Content_i_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice120) {
			Content_i_Choice120 n = (Content_i_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice121) {
			Content_i_Choice121 n = (Content_i_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice122) {
			Content_i_Choice122 n = (Content_i_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice123) {
			Content_i_Choice123 n = (Content_i_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice124) {
			Content_i_Choice124 n = (Content_i_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice125) {
			Content_i_Choice125 n = (Content_i_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice126) {
			Content_i_Choice126 n = (Content_i_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice127) {
			Content_i_Choice127 n = (Content_i_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice128) {
			Content_i_Choice128 n = (Content_i_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice129) {
			Content_i_Choice129 n = (Content_i_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice130) {
			Content_i_Choice130 n = (Content_i_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice131) {
			Content_i_Choice131 n = (Content_i_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice132) {
			Content_i_Choice132 n = (Content_i_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice133) {
			Content_i_Choice133 n = (Content_i_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_i_Choice134) {
			Content_i_Choice134 n = (Content_i_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_pre1) {
			Element_pre1 n = (Element_pre1)node;
			{
				EmptyTag_pre v=n.getEmptyTag_pre();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_pre2) {
			Element_pre2 n = (Element_pre2)node;
			{
				STag_pre v=n.getSTag_pre();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_pre_Choice1 v : n.getContent_pre_Choice1()) {
				v.accept(this);
			}
			{
				ETag_pre v=n.getETag_pre();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_pre) {
			EmptyTag_pre n = (EmptyTag_pre)node;
			printToken("<");
			printToken("pre");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_pre) {
			STag_pre n = (STag_pre)node;
			printToken("<");
			printToken("pre");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_pre) {
			ETag_pre n = (ETag_pre)node;
			printToken("</");
			printToken("pre");
			printToken(">");
			return false;
		}
		if (node instanceof Content_pre_Choice11) {
			Content_pre_Choice11 n = (Content_pre_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice12) {
			Content_pre_Choice12 n = (Content_pre_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice13) {
			Content_pre_Choice13 n = (Content_pre_Choice13)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice14) {
			Content_pre_Choice14 n = (Content_pre_Choice14)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice15) {
			Content_pre_Choice15 n = (Content_pre_Choice15)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice16) {
			Content_pre_Choice16 n = (Content_pre_Choice16)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice17) {
			Content_pre_Choice17 n = (Content_pre_Choice17)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice18) {
			Content_pre_Choice18 n = (Content_pre_Choice18)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice19) {
			Content_pre_Choice19 n = (Content_pre_Choice19)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice110) {
			Content_pre_Choice110 n = (Content_pre_Choice110)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice111) {
			Content_pre_Choice111 n = (Content_pre_Choice111)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice112) {
			Content_pre_Choice112 n = (Content_pre_Choice112)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice113) {
			Content_pre_Choice113 n = (Content_pre_Choice113)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice114) {
			Content_pre_Choice114 n = (Content_pre_Choice114)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice115) {
			Content_pre_Choice115 n = (Content_pre_Choice115)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice116) {
			Content_pre_Choice116 n = (Content_pre_Choice116)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice117) {
			Content_pre_Choice117 n = (Content_pre_Choice117)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice118) {
			Content_pre_Choice118 n = (Content_pre_Choice118)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice119) {
			Content_pre_Choice119 n = (Content_pre_Choice119)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice120) {
			Content_pre_Choice120 n = (Content_pre_Choice120)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice121) {
			Content_pre_Choice121 n = (Content_pre_Choice121)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice122) {
			Content_pre_Choice122 n = (Content_pre_Choice122)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice123) {
			Content_pre_Choice123 n = (Content_pre_Choice123)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice124) {
			Content_pre_Choice124 n = (Content_pre_Choice124)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice125) {
			Content_pre_Choice125 n = (Content_pre_Choice125)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice126) {
			Content_pre_Choice126 n = (Content_pre_Choice126)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice127) {
			Content_pre_Choice127 n = (Content_pre_Choice127)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice128) {
			Content_pre_Choice128 n = (Content_pre_Choice128)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice129) {
			Content_pre_Choice129 n = (Content_pre_Choice129)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice130) {
			Content_pre_Choice130 n = (Content_pre_Choice130)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice131) {
			Content_pre_Choice131 n = (Content_pre_Choice131)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_pre_Choice132) {
			Content_pre_Choice132 n = (Content_pre_Choice132)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_optgroup1) {
			Element_optgroup1 n = (Element_optgroup1)node;
			{
				EmptyTag_optgroup v=n.getEmptyTag_optgroup();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_optgroup2) {
			Element_optgroup2 n = (Element_optgroup2)node;
			{
				STag_optgroup v=n.getSTag_optgroup();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_optgroup_Seq1 v : n.getContent_optgroup_Seq1()) {
				v.accept(this);
			}
			{
				ETag_optgroup v=n.getETag_optgroup();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_optgroup) {
			EmptyTag_optgroup n = (EmptyTag_optgroup)node;
			printToken("<");
			printToken("optgroup");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_optgroup) {
			STag_optgroup n = (STag_optgroup)node;
			printToken("<");
			printToken("optgroup");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_optgroup) {
			ETag_optgroup n = (ETag_optgroup)node;
			printToken("</");
			printToken("optgroup");
			printToken(">");
			return false;
		}
		if (node instanceof Content_optgroup_Seq1) {
			Content_optgroup_Seq1 n = (Content_optgroup_Seq1)node;
			{
				Element_option v=n.getElement_option();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_img1) {
			Element_img1 n = (Element_img1)node;
			{
				EmptyTag_img v=n.getEmptyTag_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_img2) {
			Element_img2 n = (Element_img2)node;
			{
				STag_img v=n.getSTag_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				ETag_img v=n.getETag_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_img) {
			EmptyTag_img n = (EmptyTag_img)node;
			printToken("<");
			printToken("img");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_img) {
			STag_img n = (STag_img)node;
			printToken("<");
			printToken("img");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_img) {
			ETag_img n = (ETag_img)node;
			printToken("</");
			printToken("img");
			printToken(">");
			return false;
		}
		if (node instanceof Element_caption1) {
			Element_caption1 n = (Element_caption1)node;
			{
				EmptyTag_caption v=n.getEmptyTag_caption();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_caption2) {
			Element_caption2 n = (Element_caption2)node;
			{
				STag_caption v=n.getSTag_caption();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_caption_Choice1 v : n.getContent_caption_Choice1()) {
				v.accept(this);
			}
			{
				ETag_caption v=n.getETag_caption();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_caption) {
			EmptyTag_caption n = (EmptyTag_caption)node;
			printToken("<");
			printToken("caption");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_caption) {
			STag_caption n = (STag_caption)node;
			printToken("<");
			printToken("caption");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_caption) {
			ETag_caption n = (ETag_caption)node;
			printToken("</");
			printToken("caption");
			printToken(">");
			return false;
		}
		if (node instanceof Content_caption_Choice11) {
			Content_caption_Choice11 n = (Content_caption_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice12) {
			Content_caption_Choice12 n = (Content_caption_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice13) {
			Content_caption_Choice13 n = (Content_caption_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice14) {
			Content_caption_Choice14 n = (Content_caption_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice15) {
			Content_caption_Choice15 n = (Content_caption_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice16) {
			Content_caption_Choice16 n = (Content_caption_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice17) {
			Content_caption_Choice17 n = (Content_caption_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice18) {
			Content_caption_Choice18 n = (Content_caption_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice19) {
			Content_caption_Choice19 n = (Content_caption_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice110) {
			Content_caption_Choice110 n = (Content_caption_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice111) {
			Content_caption_Choice111 n = (Content_caption_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice112) {
			Content_caption_Choice112 n = (Content_caption_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice113) {
			Content_caption_Choice113 n = (Content_caption_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice114) {
			Content_caption_Choice114 n = (Content_caption_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice115) {
			Content_caption_Choice115 n = (Content_caption_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice116) {
			Content_caption_Choice116 n = (Content_caption_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice117) {
			Content_caption_Choice117 n = (Content_caption_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice118) {
			Content_caption_Choice118 n = (Content_caption_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice119) {
			Content_caption_Choice119 n = (Content_caption_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice120) {
			Content_caption_Choice120 n = (Content_caption_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice121) {
			Content_caption_Choice121 n = (Content_caption_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice122) {
			Content_caption_Choice122 n = (Content_caption_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice123) {
			Content_caption_Choice123 n = (Content_caption_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice124) {
			Content_caption_Choice124 n = (Content_caption_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice125) {
			Content_caption_Choice125 n = (Content_caption_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice126) {
			Content_caption_Choice126 n = (Content_caption_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice127) {
			Content_caption_Choice127 n = (Content_caption_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice128) {
			Content_caption_Choice128 n = (Content_caption_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice129) {
			Content_caption_Choice129 n = (Content_caption_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice130) {
			Content_caption_Choice130 n = (Content_caption_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice131) {
			Content_caption_Choice131 n = (Content_caption_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice132) {
			Content_caption_Choice132 n = (Content_caption_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice133) {
			Content_caption_Choice133 n = (Content_caption_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_caption_Choice134) {
			Content_caption_Choice134 n = (Content_caption_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_b1) {
			Element_b1 n = (Element_b1)node;
			{
				EmptyTag_b v=n.getEmptyTag_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_b2) {
			Element_b2 n = (Element_b2)node;
			{
				STag_b v=n.getSTag_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_b_Choice1 v : n.getContent_b_Choice1()) {
				v.accept(this);
			}
			{
				ETag_b v=n.getETag_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_b) {
			EmptyTag_b n = (EmptyTag_b)node;
			printToken("<");
			printToken("b");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_b) {
			STag_b n = (STag_b)node;
			printToken("<");
			printToken("b");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_b) {
			ETag_b n = (ETag_b)node;
			printToken("</");
			printToken("b");
			printToken(">");
			return false;
		}
		if (node instanceof Content_b_Choice11) {
			Content_b_Choice11 n = (Content_b_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice12) {
			Content_b_Choice12 n = (Content_b_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice13) {
			Content_b_Choice13 n = (Content_b_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice14) {
			Content_b_Choice14 n = (Content_b_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice15) {
			Content_b_Choice15 n = (Content_b_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice16) {
			Content_b_Choice16 n = (Content_b_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice17) {
			Content_b_Choice17 n = (Content_b_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice18) {
			Content_b_Choice18 n = (Content_b_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice19) {
			Content_b_Choice19 n = (Content_b_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice110) {
			Content_b_Choice110 n = (Content_b_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice111) {
			Content_b_Choice111 n = (Content_b_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice112) {
			Content_b_Choice112 n = (Content_b_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice113) {
			Content_b_Choice113 n = (Content_b_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice114) {
			Content_b_Choice114 n = (Content_b_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice115) {
			Content_b_Choice115 n = (Content_b_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice116) {
			Content_b_Choice116 n = (Content_b_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice117) {
			Content_b_Choice117 n = (Content_b_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice118) {
			Content_b_Choice118 n = (Content_b_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice119) {
			Content_b_Choice119 n = (Content_b_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice120) {
			Content_b_Choice120 n = (Content_b_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice121) {
			Content_b_Choice121 n = (Content_b_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice122) {
			Content_b_Choice122 n = (Content_b_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice123) {
			Content_b_Choice123 n = (Content_b_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice124) {
			Content_b_Choice124 n = (Content_b_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice125) {
			Content_b_Choice125 n = (Content_b_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice126) {
			Content_b_Choice126 n = (Content_b_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice127) {
			Content_b_Choice127 n = (Content_b_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice128) {
			Content_b_Choice128 n = (Content_b_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice129) {
			Content_b_Choice129 n = (Content_b_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice130) {
			Content_b_Choice130 n = (Content_b_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice131) {
			Content_b_Choice131 n = (Content_b_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice132) {
			Content_b_Choice132 n = (Content_b_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice133) {
			Content_b_Choice133 n = (Content_b_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_b_Choice134) {
			Content_b_Choice134 n = (Content_b_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_a1) {
			Element_a1 n = (Element_a1)node;
			{
				EmptyTag_a v=n.getEmptyTag_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_a2) {
			Element_a2 n = (Element_a2)node;
			{
				STag_a v=n.getSTag_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_a_Choice1 v : n.getContent_a_Choice1()) {
				v.accept(this);
			}
			{
				ETag_a v=n.getETag_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_a) {
			EmptyTag_a n = (EmptyTag_a)node;
			printToken("<");
			printToken("a");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_a) {
			STag_a n = (STag_a)node;
			printToken("<");
			printToken("a");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_a) {
			ETag_a n = (ETag_a)node;
			printToken("</");
			printToken("a");
			printToken(">");
			return false;
		}
		if (node instanceof Content_a_Choice11) {
			Content_a_Choice11 n = (Content_a_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice12) {
			Content_a_Choice12 n = (Content_a_Choice12)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice13) {
			Content_a_Choice13 n = (Content_a_Choice13)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice14) {
			Content_a_Choice14 n = (Content_a_Choice14)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice15) {
			Content_a_Choice15 n = (Content_a_Choice15)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice16) {
			Content_a_Choice16 n = (Content_a_Choice16)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice17) {
			Content_a_Choice17 n = (Content_a_Choice17)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice18) {
			Content_a_Choice18 n = (Content_a_Choice18)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice19) {
			Content_a_Choice19 n = (Content_a_Choice19)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice110) {
			Content_a_Choice110 n = (Content_a_Choice110)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice111) {
			Content_a_Choice111 n = (Content_a_Choice111)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice112) {
			Content_a_Choice112 n = (Content_a_Choice112)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice113) {
			Content_a_Choice113 n = (Content_a_Choice113)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice114) {
			Content_a_Choice114 n = (Content_a_Choice114)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice115) {
			Content_a_Choice115 n = (Content_a_Choice115)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice116) {
			Content_a_Choice116 n = (Content_a_Choice116)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice117) {
			Content_a_Choice117 n = (Content_a_Choice117)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice118) {
			Content_a_Choice118 n = (Content_a_Choice118)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice119) {
			Content_a_Choice119 n = (Content_a_Choice119)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice120) {
			Content_a_Choice120 n = (Content_a_Choice120)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice121) {
			Content_a_Choice121 n = (Content_a_Choice121)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice122) {
			Content_a_Choice122 n = (Content_a_Choice122)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice123) {
			Content_a_Choice123 n = (Content_a_Choice123)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice124) {
			Content_a_Choice124 n = (Content_a_Choice124)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}return visit2(node);
	}
	public boolean visit2(ASTNode node) {

		if (node instanceof Content_a_Choice125) {
			Content_a_Choice125 n = (Content_a_Choice125)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice126) {
			Content_a_Choice126 n = (Content_a_Choice126)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice127) {
			Content_a_Choice127 n = (Content_a_Choice127)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice128) {
			Content_a_Choice128 n = (Content_a_Choice128)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice129) {
			Content_a_Choice129 n = (Content_a_Choice129)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice130) {
			Content_a_Choice130 n = (Content_a_Choice130)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice131) {
			Content_a_Choice131 n = (Content_a_Choice131)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice132) {
			Content_a_Choice132 n = (Content_a_Choice132)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_a_Choice133) {
			Content_a_Choice133 n = (Content_a_Choice133)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_br1) {
			Element_br1 n = (Element_br1)node;
			{
				EmptyTag_br v=n.getEmptyTag_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_br2) {
			Element_br2 n = (Element_br2)node;
			{
				STag_br v=n.getSTag_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				ETag_br v=n.getETag_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_br) {
			EmptyTag_br n = (EmptyTag_br)node;
			printToken("<");
			printToken("br");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_br) {
			STag_br n = (STag_br)node;
			printToken("<");
			printToken("br");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_br) {
			ETag_br n = (ETag_br)node;
			printToken("</");
			printToken("br");
			printToken(">");
			return false;
		}
		if (node instanceof Element_style1) {
			Element_style1 n = (Element_style1)node;
			{
				EmptyTag_style v=n.getEmptyTag_style();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_style2) {
			Element_style2 n = (Element_style2)node;
			{
				STag_style v=n.getSTag_style();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				Content_style_Seq1 v=n.getContent_style_Seq1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ETag_style v=n.getETag_style();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_style) {
			EmptyTag_style n = (EmptyTag_style)node;
			printToken("<");
			printToken("style");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_style) {
			STag_style n = (STag_style)node;
			printToken("<");
			printToken("style");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_style) {
			ETag_style n = (ETag_style)node;
			printToken("</");
			printToken("style");
			printToken(">");
			return false;
		}
		if (node instanceof Content_style_Seq1) {
			Content_style_Seq1 n = (Content_style_Seq1)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_hr1) {
			Element_hr1 n = (Element_hr1)node;
			{
				EmptyTag_hr v=n.getEmptyTag_hr();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_hr2) {
			Element_hr2 n = (Element_hr2)node;
			{
				STag_hr v=n.getSTag_hr();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				ETag_hr v=n.getETag_hr();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_hr) {
			EmptyTag_hr n = (EmptyTag_hr)node;
			printToken("<");
			printToken("hr");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_hr) {
			STag_hr n = (STag_hr)node;
			printToken("<");
			printToken("hr");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_hr) {
			ETag_hr n = (ETag_hr)node;
			printToken("</");
			printToken("hr");
			printToken(">");
			return false;
		}
		if (node instanceof Element_param1) {
			Element_param1 n = (Element_param1)node;
			{
				EmptyTag_param v=n.getEmptyTag_param();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_param2) {
			Element_param2 n = (Element_param2)node;
			{
				STag_param v=n.getSTag_param();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				ETag_param v=n.getETag_param();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_param) {
			EmptyTag_param n = (EmptyTag_param)node;
			printToken("<");
			printToken("param");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_param) {
			STag_param n = (STag_param)node;
			printToken("<");
			printToken("param");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_param) {
			ETag_param n = (ETag_param)node;
			printToken("</");
			printToken("param");
			printToken(">");
			return false;
		}
		if (node instanceof Element_table1) {
			Element_table1 n = (Element_table1)node;
			{
				EmptyTag_table v=n.getEmptyTag_table();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_table2) {
			Element_table2 n = (Element_table2)node;
			{
				STag_table v=n.getSTag_table();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				Content_table_Seq1 v=n.getContent_table_Seq1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ETag_table v=n.getETag_table();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_table) {
			EmptyTag_table n = (EmptyTag_table)node;
			printToken("<");
			printToken("table");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_table) {
			STag_table n = (STag_table)node;
			printToken("<");
			printToken("table");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_table) {
			ETag_table n = (ETag_table)node;
			printToken("</");
			printToken("table");
			printToken(">");
			return false;
		}
		if (node instanceof Content_table_Choice11) {
			Content_table_Choice11 n = (Content_table_Choice11)node;
			for (Element_col v : n.getElement_col()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Content_table_Choice12) {
			Content_table_Choice12 n = (Content_table_Choice12)node;
			for (Element_colgroup v : n.getElement_colgroup()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Content_table_Choice21) {
			Content_table_Choice21 n = (Content_table_Choice21)node;
			for (Element_tbody v : n.getElement_tbody()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Content_table_Choice22) {
			Content_table_Choice22 n = (Content_table_Choice22)node;
			for (Element_tr v : n.getElement_tr()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Content_table_Seq1) {
			Content_table_Seq1 n = (Content_table_Seq1)node;
			{
				Element_caption v=n.getElement_caption();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Content_table_Choice1 v=n.getContent_table_Choice1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Element_thead v=n.getElement_thead();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Element_tfoot v=n.getElement_tfoot();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Content_table_Choice2 v=n.getContent_table_Choice2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_tt1) {
			Element_tt1 n = (Element_tt1)node;
			{
				EmptyTag_tt v=n.getEmptyTag_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_tt2) {
			Element_tt2 n = (Element_tt2)node;
			{
				STag_tt v=n.getSTag_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_tt_Choice1 v : n.getContent_tt_Choice1()) {
				v.accept(this);
			}
			{
				ETag_tt v=n.getETag_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_tt) {
			EmptyTag_tt n = (EmptyTag_tt)node;
			printToken("<");
			printToken("tt");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_tt) {
			STag_tt n = (STag_tt)node;
			printToken("<");
			printToken("tt");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_tt) {
			ETag_tt n = (ETag_tt)node;
			printToken("</");
			printToken("tt");
			printToken(">");
			return false;
		}
		if (node instanceof Content_tt_Choice11) {
			Content_tt_Choice11 n = (Content_tt_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice12) {
			Content_tt_Choice12 n = (Content_tt_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice13) {
			Content_tt_Choice13 n = (Content_tt_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice14) {
			Content_tt_Choice14 n = (Content_tt_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice15) {
			Content_tt_Choice15 n = (Content_tt_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice16) {
			Content_tt_Choice16 n = (Content_tt_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice17) {
			Content_tt_Choice17 n = (Content_tt_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice18) {
			Content_tt_Choice18 n = (Content_tt_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice19) {
			Content_tt_Choice19 n = (Content_tt_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice110) {
			Content_tt_Choice110 n = (Content_tt_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice111) {
			Content_tt_Choice111 n = (Content_tt_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice112) {
			Content_tt_Choice112 n = (Content_tt_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice113) {
			Content_tt_Choice113 n = (Content_tt_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice114) {
			Content_tt_Choice114 n = (Content_tt_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice115) {
			Content_tt_Choice115 n = (Content_tt_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice116) {
			Content_tt_Choice116 n = (Content_tt_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice117) {
			Content_tt_Choice117 n = (Content_tt_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice118) {
			Content_tt_Choice118 n = (Content_tt_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice119) {
			Content_tt_Choice119 n = (Content_tt_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice120) {
			Content_tt_Choice120 n = (Content_tt_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice121) {
			Content_tt_Choice121 n = (Content_tt_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice122) {
			Content_tt_Choice122 n = (Content_tt_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice123) {
			Content_tt_Choice123 n = (Content_tt_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice124) {
			Content_tt_Choice124 n = (Content_tt_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice125) {
			Content_tt_Choice125 n = (Content_tt_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice126) {
			Content_tt_Choice126 n = (Content_tt_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice127) {
			Content_tt_Choice127 n = (Content_tt_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice128) {
			Content_tt_Choice128 n = (Content_tt_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice129) {
			Content_tt_Choice129 n = (Content_tt_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice130) {
			Content_tt_Choice130 n = (Content_tt_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice131) {
			Content_tt_Choice131 n = (Content_tt_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice132) {
			Content_tt_Choice132 n = (Content_tt_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice133) {
			Content_tt_Choice133 n = (Content_tt_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tt_Choice134) {
			Content_tt_Choice134 n = (Content_tt_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_tr1) {
			Element_tr1 n = (Element_tr1)node;
			{
				EmptyTag_tr v=n.getEmptyTag_tr();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_tr2) {
			Element_tr2 n = (Element_tr2)node;
			{
				STag_tr v=n.getSTag_tr();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_tr_Choice1 v : n.getContent_tr_Choice1()) {
				v.accept(this);
			}
			{
				ETag_tr v=n.getETag_tr();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_tr) {
			EmptyTag_tr n = (EmptyTag_tr)node;
			printToken("<");
			printToken("tr");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_tr) {
			STag_tr n = (STag_tr)node;
			printToken("<");
			printToken("tr");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_tr) {
			ETag_tr n = (ETag_tr)node;
			printToken("</");
			printToken("tr");
			printToken(">");
			return false;
		}
		if (node instanceof Content_tr_Choice11) {
			Content_tr_Choice11 n = (Content_tr_Choice11)node;
			{
				Element_th v=n.getElement_th();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_tr_Choice12) {
			Content_tr_Choice12 n = (Content_tr_Choice12)node;
			{
				Element_td v=n.getElement_td();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_th1) {
			Element_th1 n = (Element_th1)node;
			{
				EmptyTag_th v=n.getEmptyTag_th();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_th2) {
			Element_th2 n = (Element_th2)node;
			{
				STag_th v=n.getSTag_th();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_th_Choice1 v : n.getContent_th_Choice1()) {
				v.accept(this);
			}
			{
				ETag_th v=n.getETag_th();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_th) {
			EmptyTag_th n = (EmptyTag_th)node;
			printToken("<");
			printToken("th");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_th) {
			STag_th n = (STag_th)node;
			printToken("<");
			printToken("th");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_th) {
			ETag_th n = (ETag_th)node;
			printToken("</");
			printToken("th");
			printToken(">");
			return false;
		}
		if (node instanceof Content_th_Choice11) {
			Content_th_Choice11 n = (Content_th_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice12) {
			Content_th_Choice12 n = (Content_th_Choice12)node;
			{
				Element_p v=n.getElement_p();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice13) {
			Content_th_Choice13 n = (Content_th_Choice13)node;
			{
				Element_h1 v=n.getElement_h1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice14) {
			Content_th_Choice14 n = (Content_th_Choice14)node;
			{
				Element_h2 v=n.getElement_h2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice15) {
			Content_th_Choice15 n = (Content_th_Choice15)node;
			{
				Element_h3 v=n.getElement_h3();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice16) {
			Content_th_Choice16 n = (Content_th_Choice16)node;
			{
				Element_h4 v=n.getElement_h4();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice17) {
			Content_th_Choice17 n = (Content_th_Choice17)node;
			{
				Element_h5 v=n.getElement_h5();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice18) {
			Content_th_Choice18 n = (Content_th_Choice18)node;
			{
				Element_h6 v=n.getElement_h6();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice19) {
			Content_th_Choice19 n = (Content_th_Choice19)node;
			{
				Element_div v=n.getElement_div();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice110) {
			Content_th_Choice110 n = (Content_th_Choice110)node;
			{
				Element_ul v=n.getElement_ul();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice111) {
			Content_th_Choice111 n = (Content_th_Choice111)node;
			{
				Element_ol v=n.getElement_ol();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice112) {
			Content_th_Choice112 n = (Content_th_Choice112)node;
			{
				Element_dl v=n.getElement_dl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice113) {
			Content_th_Choice113 n = (Content_th_Choice113)node;
			{
				Element_pre v=n.getElement_pre();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice114) {
			Content_th_Choice114 n = (Content_th_Choice114)node;
			{
				Element_hr v=n.getElement_hr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice115) {
			Content_th_Choice115 n = (Content_th_Choice115)node;
			{
				Element_blockquote v=n.getElement_blockquote();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice116) {
			Content_th_Choice116 n = (Content_th_Choice116)node;
			{
				Element_address v=n.getElement_address();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice117) {
			Content_th_Choice117 n = (Content_th_Choice117)node;
			{
				Element_fieldset v=n.getElement_fieldset();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice118) {
			Content_th_Choice118 n = (Content_th_Choice118)node;
			{
				Element_table v=n.getElement_table();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice119) {
			Content_th_Choice119 n = (Content_th_Choice119)node;
			{
				Element_form v=n.getElement_form();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice120) {
			Content_th_Choice120 n = (Content_th_Choice120)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice121) {
			Content_th_Choice121 n = (Content_th_Choice121)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice122) {
			Content_th_Choice122 n = (Content_th_Choice122)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice123) {
			Content_th_Choice123 n = (Content_th_Choice123)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice124) {
			Content_th_Choice124 n = (Content_th_Choice124)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice125) {
			Content_th_Choice125 n = (Content_th_Choice125)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice126) {
			Content_th_Choice126 n = (Content_th_Choice126)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice127) {
			Content_th_Choice127 n = (Content_th_Choice127)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice128) {
			Content_th_Choice128 n = (Content_th_Choice128)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice129) {
			Content_th_Choice129 n = (Content_th_Choice129)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice130) {
			Content_th_Choice130 n = (Content_th_Choice130)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice131) {
			Content_th_Choice131 n = (Content_th_Choice131)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice132) {
			Content_th_Choice132 n = (Content_th_Choice132)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice133) {
			Content_th_Choice133 n = (Content_th_Choice133)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice134) {
			Content_th_Choice134 n = (Content_th_Choice134)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice135) {
			Content_th_Choice135 n = (Content_th_Choice135)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice136) {
			Content_th_Choice136 n = (Content_th_Choice136)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice137) {
			Content_th_Choice137 n = (Content_th_Choice137)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice138) {
			Content_th_Choice138 n = (Content_th_Choice138)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice139) {
			Content_th_Choice139 n = (Content_th_Choice139)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice140) {
			Content_th_Choice140 n = (Content_th_Choice140)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice141) {
			Content_th_Choice141 n = (Content_th_Choice141)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice142) {
			Content_th_Choice142 n = (Content_th_Choice142)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice143) {
			Content_th_Choice143 n = (Content_th_Choice143)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice144) {
			Content_th_Choice144 n = (Content_th_Choice144)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice145) {
			Content_th_Choice145 n = (Content_th_Choice145)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice146) {
			Content_th_Choice146 n = (Content_th_Choice146)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice147) {
			Content_th_Choice147 n = (Content_th_Choice147)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice148) {
			Content_th_Choice148 n = (Content_th_Choice148)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice149) {
			Content_th_Choice149 n = (Content_th_Choice149)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice150) {
			Content_th_Choice150 n = (Content_th_Choice150)node;
			{
				Element_noscript v=n.getElement_noscript();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice151) {
			Content_th_Choice151 n = (Content_th_Choice151)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice152) {
			Content_th_Choice152 n = (Content_th_Choice152)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_th_Choice153) {
			Content_th_Choice153 n = (Content_th_Choice153)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_td1) {
			Element_td1 n = (Element_td1)node;
			{
				EmptyTag_td v=n.getEmptyTag_td();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_td2) {
			Element_td2 n = (Element_td2)node;
			{
				STag_td v=n.getSTag_td();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_td_Choice1 v : n.getContent_td_Choice1()) {
				v.accept(this);
			}
			{
				ETag_td v=n.getETag_td();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_td) {
			EmptyTag_td n = (EmptyTag_td)node;
			printToken("<");
			printToken("td");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_td) {
			STag_td n = (STag_td)node;
			printToken("<");
			printToken("td");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_td) {
			ETag_td n = (ETag_td)node;
			printToken("</");
			printToken("td");
			printToken(">");
			return false;
		}
		if (node instanceof Content_td_Choice11) {
			Content_td_Choice11 n = (Content_td_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice12) {
			Content_td_Choice12 n = (Content_td_Choice12)node;
			{
				Element_p v=n.getElement_p();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice13) {
			Content_td_Choice13 n = (Content_td_Choice13)node;
			{
				Element_h1 v=n.getElement_h1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice14) {
			Content_td_Choice14 n = (Content_td_Choice14)node;
			{
				Element_h2 v=n.getElement_h2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice15) {
			Content_td_Choice15 n = (Content_td_Choice15)node;
			{
				Element_h3 v=n.getElement_h3();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice16) {
			Content_td_Choice16 n = (Content_td_Choice16)node;
			{
				Element_h4 v=n.getElement_h4();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice17) {
			Content_td_Choice17 n = (Content_td_Choice17)node;
			{
				Element_h5 v=n.getElement_h5();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice18) {
			Content_td_Choice18 n = (Content_td_Choice18)node;
			{
				Element_h6 v=n.getElement_h6();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice19) {
			Content_td_Choice19 n = (Content_td_Choice19)node;
			{
				Element_div v=n.getElement_div();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice110) {
			Content_td_Choice110 n = (Content_td_Choice110)node;
			{
				Element_ul v=n.getElement_ul();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice111) {
			Content_td_Choice111 n = (Content_td_Choice111)node;
			{
				Element_ol v=n.getElement_ol();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice112) {
			Content_td_Choice112 n = (Content_td_Choice112)node;
			{
				Element_dl v=n.getElement_dl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice113) {
			Content_td_Choice113 n = (Content_td_Choice113)node;
			{
				Element_pre v=n.getElement_pre();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice114) {
			Content_td_Choice114 n = (Content_td_Choice114)node;
			{
				Element_hr v=n.getElement_hr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice115) {
			Content_td_Choice115 n = (Content_td_Choice115)node;
			{
				Element_blockquote v=n.getElement_blockquote();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice116) {
			Content_td_Choice116 n = (Content_td_Choice116)node;
			{
				Element_address v=n.getElement_address();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice117) {
			Content_td_Choice117 n = (Content_td_Choice117)node;
			{
				Element_fieldset v=n.getElement_fieldset();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice118) {
			Content_td_Choice118 n = (Content_td_Choice118)node;
			{
				Element_table v=n.getElement_table();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice119) {
			Content_td_Choice119 n = (Content_td_Choice119)node;
			{
				Element_form v=n.getElement_form();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice120) {
			Content_td_Choice120 n = (Content_td_Choice120)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice121) {
			Content_td_Choice121 n = (Content_td_Choice121)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice122) {
			Content_td_Choice122 n = (Content_td_Choice122)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice123) {
			Content_td_Choice123 n = (Content_td_Choice123)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice124) {
			Content_td_Choice124 n = (Content_td_Choice124)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice125) {
			Content_td_Choice125 n = (Content_td_Choice125)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice126) {
			Content_td_Choice126 n = (Content_td_Choice126)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice127) {
			Content_td_Choice127 n = (Content_td_Choice127)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice128) {
			Content_td_Choice128 n = (Content_td_Choice128)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice129) {
			Content_td_Choice129 n = (Content_td_Choice129)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice130) {
			Content_td_Choice130 n = (Content_td_Choice130)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice131) {
			Content_td_Choice131 n = (Content_td_Choice131)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice132) {
			Content_td_Choice132 n = (Content_td_Choice132)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice133) {
			Content_td_Choice133 n = (Content_td_Choice133)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice134) {
			Content_td_Choice134 n = (Content_td_Choice134)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice135) {
			Content_td_Choice135 n = (Content_td_Choice135)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice136) {
			Content_td_Choice136 n = (Content_td_Choice136)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice137) {
			Content_td_Choice137 n = (Content_td_Choice137)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice138) {
			Content_td_Choice138 n = (Content_td_Choice138)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice139) {
			Content_td_Choice139 n = (Content_td_Choice139)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice140) {
			Content_td_Choice140 n = (Content_td_Choice140)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice141) {
			Content_td_Choice141 n = (Content_td_Choice141)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice142) {
			Content_td_Choice142 n = (Content_td_Choice142)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice143) {
			Content_td_Choice143 n = (Content_td_Choice143)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice144) {
			Content_td_Choice144 n = (Content_td_Choice144)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice145) {
			Content_td_Choice145 n = (Content_td_Choice145)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice146) {
			Content_td_Choice146 n = (Content_td_Choice146)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice147) {
			Content_td_Choice147 n = (Content_td_Choice147)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice148) {
			Content_td_Choice148 n = (Content_td_Choice148)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice149) {
			Content_td_Choice149 n = (Content_td_Choice149)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice150) {
			Content_td_Choice150 n = (Content_td_Choice150)node;
			{
				Element_noscript v=n.getElement_noscript();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice151) {
			Content_td_Choice151 n = (Content_td_Choice151)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice152) {
			Content_td_Choice152 n = (Content_td_Choice152)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_td_Choice153) {
			Content_td_Choice153 n = (Content_td_Choice153)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_samp1) {
			Element_samp1 n = (Element_samp1)node;
			{
				EmptyTag_samp v=n.getEmptyTag_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_samp2) {
			Element_samp2 n = (Element_samp2)node;
			{
				STag_samp v=n.getSTag_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_samp_Choice1 v : n.getContent_samp_Choice1()) {
				v.accept(this);
			}
			{
				ETag_samp v=n.getETag_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_samp) {
			EmptyTag_samp n = (EmptyTag_samp)node;
			printToken("<");
			printToken("samp");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_samp) {
			STag_samp n = (STag_samp)node;
			printToken("<");
			printToken("samp");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_samp) {
			ETag_samp n = (ETag_samp)node;
			printToken("</");
			printToken("samp");
			printToken(">");
			return false;
		}
		if (node instanceof Content_samp_Choice11) {
			Content_samp_Choice11 n = (Content_samp_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice12) {
			Content_samp_Choice12 n = (Content_samp_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice13) {
			Content_samp_Choice13 n = (Content_samp_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice14) {
			Content_samp_Choice14 n = (Content_samp_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice15) {
			Content_samp_Choice15 n = (Content_samp_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice16) {
			Content_samp_Choice16 n = (Content_samp_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice17) {
			Content_samp_Choice17 n = (Content_samp_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice18) {
			Content_samp_Choice18 n = (Content_samp_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice19) {
			Content_samp_Choice19 n = (Content_samp_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice110) {
			Content_samp_Choice110 n = (Content_samp_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice111) {
			Content_samp_Choice111 n = (Content_samp_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice112) {
			Content_samp_Choice112 n = (Content_samp_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice113) {
			Content_samp_Choice113 n = (Content_samp_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice114) {
			Content_samp_Choice114 n = (Content_samp_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice115) {
			Content_samp_Choice115 n = (Content_samp_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice116) {
			Content_samp_Choice116 n = (Content_samp_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice117) {
			Content_samp_Choice117 n = (Content_samp_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice118) {
			Content_samp_Choice118 n = (Content_samp_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice119) {
			Content_samp_Choice119 n = (Content_samp_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice120) {
			Content_samp_Choice120 n = (Content_samp_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice121) {
			Content_samp_Choice121 n = (Content_samp_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice122) {
			Content_samp_Choice122 n = (Content_samp_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice123) {
			Content_samp_Choice123 n = (Content_samp_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice124) {
			Content_samp_Choice124 n = (Content_samp_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice125) {
			Content_samp_Choice125 n = (Content_samp_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice126) {
			Content_samp_Choice126 n = (Content_samp_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice127) {
			Content_samp_Choice127 n = (Content_samp_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice128) {
			Content_samp_Choice128 n = (Content_samp_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice129) {
			Content_samp_Choice129 n = (Content_samp_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice130) {
			Content_samp_Choice130 n = (Content_samp_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice131) {
			Content_samp_Choice131 n = (Content_samp_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice132) {
			Content_samp_Choice132 n = (Content_samp_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice133) {
			Content_samp_Choice133 n = (Content_samp_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_samp_Choice134) {
			Content_samp_Choice134 n = (Content_samp_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_tfoot1) {
			Element_tfoot1 n = (Element_tfoot1)node;
			{
				EmptyTag_tfoot v=n.getEmptyTag_tfoot();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_tfoot2) {
			Element_tfoot2 n = (Element_tfoot2)node;
			{
				STag_tfoot v=n.getSTag_tfoot();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_tfoot_Seq1 v : n.getContent_tfoot_Seq1()) {
				v.accept(this);
			}
			{
				ETag_tfoot v=n.getETag_tfoot();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_tfoot) {
			EmptyTag_tfoot n = (EmptyTag_tfoot)node;
			printToken("<");
			printToken("tfoot");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_tfoot) {
			STag_tfoot n = (STag_tfoot)node;
			printToken("<");
			printToken("tfoot");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_tfoot) {
			ETag_tfoot n = (ETag_tfoot)node;
			printToken("</");
			printToken("tfoot");
			printToken(">");
			return false;
		}
		if (node instanceof Content_tfoot_Seq1) {
			Content_tfoot_Seq1 n = (Content_tfoot_Seq1)node;
			{
				Element_tr v=n.getElement_tr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_dfn1) {
			Element_dfn1 n = (Element_dfn1)node;
			{
				EmptyTag_dfn v=n.getEmptyTag_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_dfn2) {
			Element_dfn2 n = (Element_dfn2)node;
			{
				STag_dfn v=n.getSTag_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_dfn_Choice1 v : n.getContent_dfn_Choice1()) {
				v.accept(this);
			}
			{
				ETag_dfn v=n.getETag_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_dfn) {
			EmptyTag_dfn n = (EmptyTag_dfn)node;
			printToken("<");
			printToken("dfn");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_dfn) {
			STag_dfn n = (STag_dfn)node;
			printToken("<");
			printToken("dfn");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_dfn) {
			ETag_dfn n = (ETag_dfn)node;
			printToken("</");
			printToken("dfn");
			printToken(">");
			return false;
		}
		if (node instanceof Content_dfn_Choice11) {
			Content_dfn_Choice11 n = (Content_dfn_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice12) {
			Content_dfn_Choice12 n = (Content_dfn_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice13) {
			Content_dfn_Choice13 n = (Content_dfn_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice14) {
			Content_dfn_Choice14 n = (Content_dfn_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice15) {
			Content_dfn_Choice15 n = (Content_dfn_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice16) {
			Content_dfn_Choice16 n = (Content_dfn_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice17) {
			Content_dfn_Choice17 n = (Content_dfn_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice18) {
			Content_dfn_Choice18 n = (Content_dfn_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice19) {
			Content_dfn_Choice19 n = (Content_dfn_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice110) {
			Content_dfn_Choice110 n = (Content_dfn_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice111) {
			Content_dfn_Choice111 n = (Content_dfn_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice112) {
			Content_dfn_Choice112 n = (Content_dfn_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice113) {
			Content_dfn_Choice113 n = (Content_dfn_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice114) {
			Content_dfn_Choice114 n = (Content_dfn_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice115) {
			Content_dfn_Choice115 n = (Content_dfn_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice116) {
			Content_dfn_Choice116 n = (Content_dfn_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice117) {
			Content_dfn_Choice117 n = (Content_dfn_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice118) {
			Content_dfn_Choice118 n = (Content_dfn_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice119) {
			Content_dfn_Choice119 n = (Content_dfn_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice120) {
			Content_dfn_Choice120 n = (Content_dfn_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice121) {
			Content_dfn_Choice121 n = (Content_dfn_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice122) {
			Content_dfn_Choice122 n = (Content_dfn_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice123) {
			Content_dfn_Choice123 n = (Content_dfn_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice124) {
			Content_dfn_Choice124 n = (Content_dfn_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice125) {
			Content_dfn_Choice125 n = (Content_dfn_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice126) {
			Content_dfn_Choice126 n = (Content_dfn_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice127) {
			Content_dfn_Choice127 n = (Content_dfn_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice128) {
			Content_dfn_Choice128 n = (Content_dfn_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice129) {
			Content_dfn_Choice129 n = (Content_dfn_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice130) {
			Content_dfn_Choice130 n = (Content_dfn_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice131) {
			Content_dfn_Choice131 n = (Content_dfn_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice132) {
			Content_dfn_Choice132 n = (Content_dfn_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice133) {
			Content_dfn_Choice133 n = (Content_dfn_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_dfn_Choice134) {
			Content_dfn_Choice134 n = (Content_dfn_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_noscript1) {
			Element_noscript1 n = (Element_noscript1)node;
			{
				EmptyTag_noscript v=n.getEmptyTag_noscript();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_noscript2) {
			Element_noscript2 n = (Element_noscript2)node;
			{
				STag_noscript v=n.getSTag_noscript();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_noscript_Choice1 v : n.getContent_noscript_Choice1()) {
				v.accept(this);
			}
			{
				ETag_noscript v=n.getETag_noscript();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_noscript) {
			EmptyTag_noscript n = (EmptyTag_noscript)node;
			printToken("<");
			printToken("noscript");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_noscript) {
			STag_noscript n = (STag_noscript)node;
			printToken("<");
			printToken("noscript");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_noscript) {
			ETag_noscript n = (ETag_noscript)node;
			printToken("</");
			printToken("noscript");
			printToken(">");
			return false;
		}
		if (node instanceof Content_noscript_Choice11) {
			Content_noscript_Choice11 n = (Content_noscript_Choice11)node;
			{
				Element_p v=n.getElement_p();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_noscript_Choice12) {
			Content_noscript_Choice12 n = (Content_noscript_Choice12)node;
			{
				Element_h1 v=n.getElement_h1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_noscript_Choice13) {
			Content_noscript_Choice13 n = (Content_noscript_Choice13)node;
			{
				Element_h2 v=n.getElement_h2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_noscript_Choice14) {
			Content_noscript_Choice14 n = (Content_noscript_Choice14)node;
			{
				Element_h3 v=n.getElement_h3();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_noscript_Choice15) {
			Content_noscript_Choice15 n = (Content_noscript_Choice15)node;
			{
				Element_h4 v=n.getElement_h4();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_noscript_Choice16) {
			Content_noscript_Choice16 n = (Content_noscript_Choice16)node;
			{
				Element_h5 v=n.getElement_h5();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_noscript_Choice17) {
			Content_noscript_Choice17 n = (Content_noscript_Choice17)node;
			{
				Element_h6 v=n.getElement_h6();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_noscript_Choice18) {
			Content_noscript_Choice18 n = (Content_noscript_Choice18)node;
			{
				Element_div v=n.getElement_div();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_noscript_Choice19) {
			Content_noscript_Choice19 n = (Content_noscript_Choice19)node;
			{
				Element_ul v=n.getElement_ul();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_noscript_Choice110) {
			Content_noscript_Choice110 n = (Content_noscript_Choice110)node;
			{
				Element_ol v=n.getElement_ol();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_noscript_Choice111) {
			Content_noscript_Choice111 n = (Content_noscript_Choice111)node;
			{
				Element_dl v=n.getElement_dl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_noscript_Choice112) {
			Content_noscript_Choice112 n = (Content_noscript_Choice112)node;
			{
				Element_pre v=n.getElement_pre();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_noscript_Choice113) {
			Content_noscript_Choice113 n = (Content_noscript_Choice113)node;
			{
				Element_hr v=n.getElement_hr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_noscript_Choice114) {
			Content_noscript_Choice114 n = (Content_noscript_Choice114)node;
			{
				Element_blockquote v=n.getElement_blockquote();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_noscript_Choice115) {
			Content_noscript_Choice115 n = (Content_noscript_Choice115)node;
			{
				Element_address v=n.getElement_address();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_noscript_Choice116) {
			Content_noscript_Choice116 n = (Content_noscript_Choice116)node;
			{
				Element_fieldset v=n.getElement_fieldset();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_noscript_Choice117) {
			Content_noscript_Choice117 n = (Content_noscript_Choice117)node;
			{
				Element_table v=n.getElement_table();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_noscript_Choice118) {
			Content_noscript_Choice118 n = (Content_noscript_Choice118)node;
			{
				Element_form v=n.getElement_form();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_noscript_Choice119) {
			Content_noscript_Choice119 n = (Content_noscript_Choice119)node;
			{
				Element_noscript v=n.getElement_noscript();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_noscript_Choice120) {
			Content_noscript_Choice120 n = (Content_noscript_Choice120)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_noscript_Choice121) {
			Content_noscript_Choice121 n = (Content_noscript_Choice121)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_noscript_Choice122) {
			Content_noscript_Choice122 n = (Content_noscript_Choice122)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_colgroup1) {
			Element_colgroup1 n = (Element_colgroup1)node;
			{
				EmptyTag_colgroup v=n.getEmptyTag_colgroup();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_colgroup2) {
			Element_colgroup2 n = (Element_colgroup2)node;
			{
				STag_colgroup v=n.getSTag_colgroup();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_colgroup_Seq1 v : n.getContent_colgroup_Seq1()) {
				v.accept(this);
			}
			{
				ETag_colgroup v=n.getETag_colgroup();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_colgroup) {
			EmptyTag_colgroup n = (EmptyTag_colgroup)node;
			printToken("<");
			printToken("colgroup");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_colgroup) {
			STag_colgroup n = (STag_colgroup)node;
			printToken("<");
			printToken("colgroup");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_colgroup) {
			ETag_colgroup n = (ETag_colgroup)node;
			printToken("</");
			printToken("colgroup");
			printToken(">");
			return false;
		}
		if (node instanceof Content_colgroup_Seq1) {
			Content_colgroup_Seq1 n = (Content_colgroup_Seq1)node;
			{
				Element_col v=n.getElement_col();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_object1) {
			Element_object1 n = (Element_object1)node;
			{
				EmptyTag_object v=n.getEmptyTag_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_object2) {
			Element_object2 n = (Element_object2)node;
			{
				STag_object v=n.getSTag_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_object_Choice1 v : n.getContent_object_Choice1()) {
				v.accept(this);
			}
			{
				ETag_object v=n.getETag_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_object) {
			EmptyTag_object n = (EmptyTag_object)node;
			printToken("<");
			printToken("object");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_object) {
			STag_object n = (STag_object)node;
			printToken("<");
			printToken("object");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_object) {
			ETag_object n = (ETag_object)node;
			printToken("</");
			printToken("object");
			printToken(">");
			return false;
		}
		if (node instanceof Content_object_Choice11) {
			Content_object_Choice11 n = (Content_object_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice12) {
			Content_object_Choice12 n = (Content_object_Choice12)node;
			{
				Element_param v=n.getElement_param();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice13) {
			Content_object_Choice13 n = (Content_object_Choice13)node;
			{
				Element_p v=n.getElement_p();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice14) {
			Content_object_Choice14 n = (Content_object_Choice14)node;
			{
				Element_h1 v=n.getElement_h1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice15) {
			Content_object_Choice15 n = (Content_object_Choice15)node;
			{
				Element_h2 v=n.getElement_h2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice16) {
			Content_object_Choice16 n = (Content_object_Choice16)node;
			{
				Element_h3 v=n.getElement_h3();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice17) {
			Content_object_Choice17 n = (Content_object_Choice17)node;
			{
				Element_h4 v=n.getElement_h4();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice18) {
			Content_object_Choice18 n = (Content_object_Choice18)node;
			{
				Element_h5 v=n.getElement_h5();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice19) {
			Content_object_Choice19 n = (Content_object_Choice19)node;
			{
				Element_h6 v=n.getElement_h6();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice110) {
			Content_object_Choice110 n = (Content_object_Choice110)node;
			{
				Element_div v=n.getElement_div();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice111) {
			Content_object_Choice111 n = (Content_object_Choice111)node;
			{
				Element_ul v=n.getElement_ul();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice112) {
			Content_object_Choice112 n = (Content_object_Choice112)node;
			{
				Element_ol v=n.getElement_ol();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice113) {
			Content_object_Choice113 n = (Content_object_Choice113)node;
			{
				Element_dl v=n.getElement_dl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice114) {
			Content_object_Choice114 n = (Content_object_Choice114)node;
			{
				Element_pre v=n.getElement_pre();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice115) {
			Content_object_Choice115 n = (Content_object_Choice115)node;
			{
				Element_hr v=n.getElement_hr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice116) {
			Content_object_Choice116 n = (Content_object_Choice116)node;
			{
				Element_blockquote v=n.getElement_blockquote();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice117) {
			Content_object_Choice117 n = (Content_object_Choice117)node;
			{
				Element_address v=n.getElement_address();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice118) {
			Content_object_Choice118 n = (Content_object_Choice118)node;
			{
				Element_fieldset v=n.getElement_fieldset();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice119) {
			Content_object_Choice119 n = (Content_object_Choice119)node;
			{
				Element_table v=n.getElement_table();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice120) {
			Content_object_Choice120 n = (Content_object_Choice120)node;
			{
				Element_form v=n.getElement_form();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice121) {
			Content_object_Choice121 n = (Content_object_Choice121)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice122) {
			Content_object_Choice122 n = (Content_object_Choice122)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice123) {
			Content_object_Choice123 n = (Content_object_Choice123)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice124) {
			Content_object_Choice124 n = (Content_object_Choice124)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice125) {
			Content_object_Choice125 n = (Content_object_Choice125)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice126) {
			Content_object_Choice126 n = (Content_object_Choice126)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice127) {
			Content_object_Choice127 n = (Content_object_Choice127)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice128) {
			Content_object_Choice128 n = (Content_object_Choice128)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice129) {
			Content_object_Choice129 n = (Content_object_Choice129)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice130) {
			Content_object_Choice130 n = (Content_object_Choice130)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice131) {
			Content_object_Choice131 n = (Content_object_Choice131)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice132) {
			Content_object_Choice132 n = (Content_object_Choice132)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice133) {
			Content_object_Choice133 n = (Content_object_Choice133)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice134) {
			Content_object_Choice134 n = (Content_object_Choice134)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice135) {
			Content_object_Choice135 n = (Content_object_Choice135)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice136) {
			Content_object_Choice136 n = (Content_object_Choice136)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice137) {
			Content_object_Choice137 n = (Content_object_Choice137)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice138) {
			Content_object_Choice138 n = (Content_object_Choice138)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice139) {
			Content_object_Choice139 n = (Content_object_Choice139)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice140) {
			Content_object_Choice140 n = (Content_object_Choice140)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice141) {
			Content_object_Choice141 n = (Content_object_Choice141)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice142) {
			Content_object_Choice142 n = (Content_object_Choice142)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice143) {
			Content_object_Choice143 n = (Content_object_Choice143)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice144) {
			Content_object_Choice144 n = (Content_object_Choice144)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice145) {
			Content_object_Choice145 n = (Content_object_Choice145)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice146) {
			Content_object_Choice146 n = (Content_object_Choice146)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice147) {
			Content_object_Choice147 n = (Content_object_Choice147)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice148) {
			Content_object_Choice148 n = (Content_object_Choice148)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice149) {
			Content_object_Choice149 n = (Content_object_Choice149)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice150) {
			Content_object_Choice150 n = (Content_object_Choice150)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice151) {
			Content_object_Choice151 n = (Content_object_Choice151)node;
			{
				Element_noscript v=n.getElement_noscript();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice152) {
			Content_object_Choice152 n = (Content_object_Choice152)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice153) {
			Content_object_Choice153 n = (Content_object_Choice153)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_object_Choice154) {
			Content_object_Choice154 n = (Content_object_Choice154)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_sup1) {
			Element_sup1 n = (Element_sup1)node;
			{
				EmptyTag_sup v=n.getEmptyTag_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_sup2) {
			Element_sup2 n = (Element_sup2)node;
			{
				STag_sup v=n.getSTag_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_sup_Choice1 v : n.getContent_sup_Choice1()) {
				v.accept(this);
			}
			{
				ETag_sup v=n.getETag_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_sup) {
			EmptyTag_sup n = (EmptyTag_sup)node;
			printToken("<");
			printToken("sup");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_sup) {
			STag_sup n = (STag_sup)node;
			printToken("<");
			printToken("sup");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_sup) {
			ETag_sup n = (ETag_sup)node;
			printToken("</");
			printToken("sup");
			printToken(">");
			return false;
		}
		if (node instanceof Content_sup_Choice11) {
			Content_sup_Choice11 n = (Content_sup_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice12) {
			Content_sup_Choice12 n = (Content_sup_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice13) {
			Content_sup_Choice13 n = (Content_sup_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice14) {
			Content_sup_Choice14 n = (Content_sup_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice15) {
			Content_sup_Choice15 n = (Content_sup_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice16) {
			Content_sup_Choice16 n = (Content_sup_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice17) {
			Content_sup_Choice17 n = (Content_sup_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice18) {
			Content_sup_Choice18 n = (Content_sup_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice19) {
			Content_sup_Choice19 n = (Content_sup_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice110) {
			Content_sup_Choice110 n = (Content_sup_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice111) {
			Content_sup_Choice111 n = (Content_sup_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice112) {
			Content_sup_Choice112 n = (Content_sup_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice113) {
			Content_sup_Choice113 n = (Content_sup_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice114) {
			Content_sup_Choice114 n = (Content_sup_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice115) {
			Content_sup_Choice115 n = (Content_sup_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice116) {
			Content_sup_Choice116 n = (Content_sup_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice117) {
			Content_sup_Choice117 n = (Content_sup_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice118) {
			Content_sup_Choice118 n = (Content_sup_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice119) {
			Content_sup_Choice119 n = (Content_sup_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice120) {
			Content_sup_Choice120 n = (Content_sup_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice121) {
			Content_sup_Choice121 n = (Content_sup_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice122) {
			Content_sup_Choice122 n = (Content_sup_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice123) {
			Content_sup_Choice123 n = (Content_sup_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice124) {
			Content_sup_Choice124 n = (Content_sup_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice125) {
			Content_sup_Choice125 n = (Content_sup_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice126) {
			Content_sup_Choice126 n = (Content_sup_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice127) {
			Content_sup_Choice127 n = (Content_sup_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice128) {
			Content_sup_Choice128 n = (Content_sup_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice129) {
			Content_sup_Choice129 n = (Content_sup_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice130) {
			Content_sup_Choice130 n = (Content_sup_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice131) {
			Content_sup_Choice131 n = (Content_sup_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice132) {
			Content_sup_Choice132 n = (Content_sup_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice133) {
			Content_sup_Choice133 n = (Content_sup_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sup_Choice134) {
			Content_sup_Choice134 n = (Content_sup_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_h61) {
			Element_h61 n = (Element_h61)node;
			{
				EmptyTag_h6 v=n.getEmptyTag_h6();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_h62) {
			Element_h62 n = (Element_h62)node;
			{
				STag_h6 v=n.getSTag_h6();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_h6_Choice1 v : n.getContent_h6_Choice1()) {
				v.accept(this);
			}
			{
				ETag_h6 v=n.getETag_h6();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_h6) {
			EmptyTag_h6 n = (EmptyTag_h6)node;
			printToken("<");
			printToken("h6");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_h6) {
			STag_h6 n = (STag_h6)node;
			printToken("<");
			printToken("h6");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_h6) {
			ETag_h6 n = (ETag_h6)node;
			printToken("</");
			printToken("h6");
			printToken(">");
			return false;
		}
		if (node instanceof Content_h6_Choice11) {
			Content_h6_Choice11 n = (Content_h6_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice12) {
			Content_h6_Choice12 n = (Content_h6_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice13) {
			Content_h6_Choice13 n = (Content_h6_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice14) {
			Content_h6_Choice14 n = (Content_h6_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice15) {
			Content_h6_Choice15 n = (Content_h6_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice16) {
			Content_h6_Choice16 n = (Content_h6_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice17) {
			Content_h6_Choice17 n = (Content_h6_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice18) {
			Content_h6_Choice18 n = (Content_h6_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice19) {
			Content_h6_Choice19 n = (Content_h6_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice110) {
			Content_h6_Choice110 n = (Content_h6_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice111) {
			Content_h6_Choice111 n = (Content_h6_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice112) {
			Content_h6_Choice112 n = (Content_h6_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice113) {
			Content_h6_Choice113 n = (Content_h6_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice114) {
			Content_h6_Choice114 n = (Content_h6_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice115) {
			Content_h6_Choice115 n = (Content_h6_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice116) {
			Content_h6_Choice116 n = (Content_h6_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice117) {
			Content_h6_Choice117 n = (Content_h6_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice118) {
			Content_h6_Choice118 n = (Content_h6_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice119) {
			Content_h6_Choice119 n = (Content_h6_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice120) {
			Content_h6_Choice120 n = (Content_h6_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice121) {
			Content_h6_Choice121 n = (Content_h6_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice122) {
			Content_h6_Choice122 n = (Content_h6_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice123) {
			Content_h6_Choice123 n = (Content_h6_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice124) {
			Content_h6_Choice124 n = (Content_h6_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice125) {
			Content_h6_Choice125 n = (Content_h6_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice126) {
			Content_h6_Choice126 n = (Content_h6_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice127) {
			Content_h6_Choice127 n = (Content_h6_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice128) {
			Content_h6_Choice128 n = (Content_h6_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice129) {
			Content_h6_Choice129 n = (Content_h6_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice130) {
			Content_h6_Choice130 n = (Content_h6_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice131) {
			Content_h6_Choice131 n = (Content_h6_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice132) {
			Content_h6_Choice132 n = (Content_h6_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice133) {
			Content_h6_Choice133 n = (Content_h6_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h6_Choice134) {
			Content_h6_Choice134 n = (Content_h6_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_h51) {
			Element_h51 n = (Element_h51)node;
			{
				EmptyTag_h5 v=n.getEmptyTag_h5();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_h52) {
			Element_h52 n = (Element_h52)node;
			{
				STag_h5 v=n.getSTag_h5();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_h5_Choice1 v : n.getContent_h5_Choice1()) {
				v.accept(this);
			}
			{
				ETag_h5 v=n.getETag_h5();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_h5) {
			EmptyTag_h5 n = (EmptyTag_h5)node;
			printToken("<");
			printToken("h5");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_h5) {
			STag_h5 n = (STag_h5)node;
			printToken("<");
			printToken("h5");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_h5) {
			ETag_h5 n = (ETag_h5)node;
			printToken("</");
			printToken("h5");
			printToken(">");
			return false;
		}
		if (node instanceof Content_h5_Choice11) {
			Content_h5_Choice11 n = (Content_h5_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice12) {
			Content_h5_Choice12 n = (Content_h5_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice13) {
			Content_h5_Choice13 n = (Content_h5_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice14) {
			Content_h5_Choice14 n = (Content_h5_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice15) {
			Content_h5_Choice15 n = (Content_h5_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice16) {
			Content_h5_Choice16 n = (Content_h5_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice17) {
			Content_h5_Choice17 n = (Content_h5_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice18) {
			Content_h5_Choice18 n = (Content_h5_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice19) {
			Content_h5_Choice19 n = (Content_h5_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice110) {
			Content_h5_Choice110 n = (Content_h5_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice111) {
			Content_h5_Choice111 n = (Content_h5_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice112) {
			Content_h5_Choice112 n = (Content_h5_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice113) {
			Content_h5_Choice113 n = (Content_h5_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice114) {
			Content_h5_Choice114 n = (Content_h5_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice115) {
			Content_h5_Choice115 n = (Content_h5_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice116) {
			Content_h5_Choice116 n = (Content_h5_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice117) {
			Content_h5_Choice117 n = (Content_h5_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice118) {
			Content_h5_Choice118 n = (Content_h5_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice119) {
			Content_h5_Choice119 n = (Content_h5_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice120) {
			Content_h5_Choice120 n = (Content_h5_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice121) {
			Content_h5_Choice121 n = (Content_h5_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice122) {
			Content_h5_Choice122 n = (Content_h5_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice123) {
			Content_h5_Choice123 n = (Content_h5_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice124) {
			Content_h5_Choice124 n = (Content_h5_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice125) {
			Content_h5_Choice125 n = (Content_h5_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice126) {
			Content_h5_Choice126 n = (Content_h5_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice127) {
			Content_h5_Choice127 n = (Content_h5_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice128) {
			Content_h5_Choice128 n = (Content_h5_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice129) {
			Content_h5_Choice129 n = (Content_h5_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice130) {
			Content_h5_Choice130 n = (Content_h5_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice131) {
			Content_h5_Choice131 n = (Content_h5_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice132) {
			Content_h5_Choice132 n = (Content_h5_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice133) {
			Content_h5_Choice133 n = (Content_h5_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h5_Choice134) {
			Content_h5_Choice134 n = (Content_h5_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_h41) {
			Element_h41 n = (Element_h41)node;
			{
				EmptyTag_h4 v=n.getEmptyTag_h4();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_h42) {
			Element_h42 n = (Element_h42)node;
			{
				STag_h4 v=n.getSTag_h4();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_h4_Choice1 v : n.getContent_h4_Choice1()) {
				v.accept(this);
			}
			{
				ETag_h4 v=n.getETag_h4();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_h4) {
			EmptyTag_h4 n = (EmptyTag_h4)node;
			printToken("<");
			printToken("h4");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_h4) {
			STag_h4 n = (STag_h4)node;
			printToken("<");
			printToken("h4");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_h4) {
			ETag_h4 n = (ETag_h4)node;
			printToken("</");
			printToken("h4");
			printToken(">");
			return false;
		}
		if (node instanceof Content_h4_Choice11) {
			Content_h4_Choice11 n = (Content_h4_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice12) {
			Content_h4_Choice12 n = (Content_h4_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice13) {
			Content_h4_Choice13 n = (Content_h4_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice14) {
			Content_h4_Choice14 n = (Content_h4_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice15) {
			Content_h4_Choice15 n = (Content_h4_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice16) {
			Content_h4_Choice16 n = (Content_h4_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice17) {
			Content_h4_Choice17 n = (Content_h4_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice18) {
			Content_h4_Choice18 n = (Content_h4_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice19) {
			Content_h4_Choice19 n = (Content_h4_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice110) {
			Content_h4_Choice110 n = (Content_h4_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice111) {
			Content_h4_Choice111 n = (Content_h4_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice112) {
			Content_h4_Choice112 n = (Content_h4_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice113) {
			Content_h4_Choice113 n = (Content_h4_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice114) {
			Content_h4_Choice114 n = (Content_h4_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice115) {
			Content_h4_Choice115 n = (Content_h4_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice116) {
			Content_h4_Choice116 n = (Content_h4_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice117) {
			Content_h4_Choice117 n = (Content_h4_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice118) {
			Content_h4_Choice118 n = (Content_h4_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice119) {
			Content_h4_Choice119 n = (Content_h4_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice120) {
			Content_h4_Choice120 n = (Content_h4_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice121) {
			Content_h4_Choice121 n = (Content_h4_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice122) {
			Content_h4_Choice122 n = (Content_h4_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice123) {
			Content_h4_Choice123 n = (Content_h4_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice124) {
			Content_h4_Choice124 n = (Content_h4_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice125) {
			Content_h4_Choice125 n = (Content_h4_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice126) {
			Content_h4_Choice126 n = (Content_h4_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice127) {
			Content_h4_Choice127 n = (Content_h4_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice128) {
			Content_h4_Choice128 n = (Content_h4_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice129) {
			Content_h4_Choice129 n = (Content_h4_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice130) {
			Content_h4_Choice130 n = (Content_h4_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice131) {
			Content_h4_Choice131 n = (Content_h4_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice132) {
			Content_h4_Choice132 n = (Content_h4_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice133) {
			Content_h4_Choice133 n = (Content_h4_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h4_Choice134) {
			Content_h4_Choice134 n = (Content_h4_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_h31) {
			Element_h31 n = (Element_h31)node;
			{
				EmptyTag_h3 v=n.getEmptyTag_h3();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_h32) {
			Element_h32 n = (Element_h32)node;
			{
				STag_h3 v=n.getSTag_h3();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_h3_Choice1 v : n.getContent_h3_Choice1()) {
				v.accept(this);
			}
			{
				ETag_h3 v=n.getETag_h3();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_h3) {
			EmptyTag_h3 n = (EmptyTag_h3)node;
			printToken("<");
			printToken("h3");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_h3) {
			STag_h3 n = (STag_h3)node;
			printToken("<");
			printToken("h3");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_h3) {
			ETag_h3 n = (ETag_h3)node;
			printToken("</");
			printToken("h3");
			printToken(">");
			return false;
		}
		if (node instanceof Content_h3_Choice11) {
			Content_h3_Choice11 n = (Content_h3_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice12) {
			Content_h3_Choice12 n = (Content_h3_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice13) {
			Content_h3_Choice13 n = (Content_h3_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice14) {
			Content_h3_Choice14 n = (Content_h3_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice15) {
			Content_h3_Choice15 n = (Content_h3_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice16) {
			Content_h3_Choice16 n = (Content_h3_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice17) {
			Content_h3_Choice17 n = (Content_h3_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice18) {
			Content_h3_Choice18 n = (Content_h3_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice19) {
			Content_h3_Choice19 n = (Content_h3_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice110) {
			Content_h3_Choice110 n = (Content_h3_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice111) {
			Content_h3_Choice111 n = (Content_h3_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice112) {
			Content_h3_Choice112 n = (Content_h3_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice113) {
			Content_h3_Choice113 n = (Content_h3_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice114) {
			Content_h3_Choice114 n = (Content_h3_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice115) {
			Content_h3_Choice115 n = (Content_h3_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice116) {
			Content_h3_Choice116 n = (Content_h3_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice117) {
			Content_h3_Choice117 n = (Content_h3_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice118) {
			Content_h3_Choice118 n = (Content_h3_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice119) {
			Content_h3_Choice119 n = (Content_h3_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice120) {
			Content_h3_Choice120 n = (Content_h3_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice121) {
			Content_h3_Choice121 n = (Content_h3_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice122) {
			Content_h3_Choice122 n = (Content_h3_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice123) {
			Content_h3_Choice123 n = (Content_h3_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice124) {
			Content_h3_Choice124 n = (Content_h3_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice125) {
			Content_h3_Choice125 n = (Content_h3_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice126) {
			Content_h3_Choice126 n = (Content_h3_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice127) {
			Content_h3_Choice127 n = (Content_h3_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice128) {
			Content_h3_Choice128 n = (Content_h3_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice129) {
			Content_h3_Choice129 n = (Content_h3_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice130) {
			Content_h3_Choice130 n = (Content_h3_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice131) {
			Content_h3_Choice131 n = (Content_h3_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice132) {
			Content_h3_Choice132 n = (Content_h3_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice133) {
			Content_h3_Choice133 n = (Content_h3_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h3_Choice134) {
			Content_h3_Choice134 n = (Content_h3_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_h21) {
			Element_h21 n = (Element_h21)node;
			{
				EmptyTag_h2 v=n.getEmptyTag_h2();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_h22) {
			Element_h22 n = (Element_h22)node;
			{
				STag_h2 v=n.getSTag_h2();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_h2_Choice1 v : n.getContent_h2_Choice1()) {
				v.accept(this);
			}
			{
				ETag_h2 v=n.getETag_h2();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_h2) {
			EmptyTag_h2 n = (EmptyTag_h2)node;
			printToken("<");
			printToken("h2");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_h2) {
			STag_h2 n = (STag_h2)node;
			printToken("<");
			printToken("h2");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_h2) {
			ETag_h2 n = (ETag_h2)node;
			printToken("</");
			printToken("h2");
			printToken(">");
			return false;
		}
		if (node instanceof Content_h2_Choice11) {
			Content_h2_Choice11 n = (Content_h2_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice12) {
			Content_h2_Choice12 n = (Content_h2_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice13) {
			Content_h2_Choice13 n = (Content_h2_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice14) {
			Content_h2_Choice14 n = (Content_h2_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice15) {
			Content_h2_Choice15 n = (Content_h2_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice16) {
			Content_h2_Choice16 n = (Content_h2_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice17) {
			Content_h2_Choice17 n = (Content_h2_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice18) {
			Content_h2_Choice18 n = (Content_h2_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice19) {
			Content_h2_Choice19 n = (Content_h2_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice110) {
			Content_h2_Choice110 n = (Content_h2_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice111) {
			Content_h2_Choice111 n = (Content_h2_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice112) {
			Content_h2_Choice112 n = (Content_h2_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice113) {
			Content_h2_Choice113 n = (Content_h2_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice114) {
			Content_h2_Choice114 n = (Content_h2_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice115) {
			Content_h2_Choice115 n = (Content_h2_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice116) {
			Content_h2_Choice116 n = (Content_h2_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice117) {
			Content_h2_Choice117 n = (Content_h2_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice118) {
			Content_h2_Choice118 n = (Content_h2_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice119) {
			Content_h2_Choice119 n = (Content_h2_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice120) {
			Content_h2_Choice120 n = (Content_h2_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice121) {
			Content_h2_Choice121 n = (Content_h2_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice122) {
			Content_h2_Choice122 n = (Content_h2_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice123) {
			Content_h2_Choice123 n = (Content_h2_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice124) {
			Content_h2_Choice124 n = (Content_h2_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice125) {
			Content_h2_Choice125 n = (Content_h2_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice126) {
			Content_h2_Choice126 n = (Content_h2_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice127) {
			Content_h2_Choice127 n = (Content_h2_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice128) {
			Content_h2_Choice128 n = (Content_h2_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice129) {
			Content_h2_Choice129 n = (Content_h2_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice130) {
			Content_h2_Choice130 n = (Content_h2_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice131) {
			Content_h2_Choice131 n = (Content_h2_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice132) {
			Content_h2_Choice132 n = (Content_h2_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice133) {
			Content_h2_Choice133 n = (Content_h2_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h2_Choice134) {
			Content_h2_Choice134 n = (Content_h2_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_h11) {
			Element_h11 n = (Element_h11)node;
			{
				EmptyTag_h1 v=n.getEmptyTag_h1();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_h12) {
			Element_h12 n = (Element_h12)node;
			{
				STag_h1 v=n.getSTag_h1();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_h1_Choice1 v : n.getContent_h1_Choice1()) {
				v.accept(this);
			}
			{
				ETag_h1 v=n.getETag_h1();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_h1) {
			EmptyTag_h1 n = (EmptyTag_h1)node;
			printToken("<");
			printToken("h1");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_h1) {
			STag_h1 n = (STag_h1)node;
			printToken("<");
			printToken("h1");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_h1) {
			ETag_h1 n = (ETag_h1)node;
			printToken("</");
			printToken("h1");
			printToken(">");
			return false;
		}
		if (node instanceof Content_h1_Choice11) {
			Content_h1_Choice11 n = (Content_h1_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice12) {
			Content_h1_Choice12 n = (Content_h1_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice13) {
			Content_h1_Choice13 n = (Content_h1_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice14) {
			Content_h1_Choice14 n = (Content_h1_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice15) {
			Content_h1_Choice15 n = (Content_h1_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice16) {
			Content_h1_Choice16 n = (Content_h1_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice17) {
			Content_h1_Choice17 n = (Content_h1_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice18) {
			Content_h1_Choice18 n = (Content_h1_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice19) {
			Content_h1_Choice19 n = (Content_h1_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice110) {
			Content_h1_Choice110 n = (Content_h1_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice111) {
			Content_h1_Choice111 n = (Content_h1_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice112) {
			Content_h1_Choice112 n = (Content_h1_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice113) {
			Content_h1_Choice113 n = (Content_h1_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice114) {
			Content_h1_Choice114 n = (Content_h1_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice115) {
			Content_h1_Choice115 n = (Content_h1_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice116) {
			Content_h1_Choice116 n = (Content_h1_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice117) {
			Content_h1_Choice117 n = (Content_h1_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice118) {
			Content_h1_Choice118 n = (Content_h1_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice119) {
			Content_h1_Choice119 n = (Content_h1_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice120) {
			Content_h1_Choice120 n = (Content_h1_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice121) {
			Content_h1_Choice121 n = (Content_h1_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice122) {
			Content_h1_Choice122 n = (Content_h1_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice123) {
			Content_h1_Choice123 n = (Content_h1_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice124) {
			Content_h1_Choice124 n = (Content_h1_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice125) {
			Content_h1_Choice125 n = (Content_h1_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice126) {
			Content_h1_Choice126 n = (Content_h1_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice127) {
			Content_h1_Choice127 n = (Content_h1_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice128) {
			Content_h1_Choice128 n = (Content_h1_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice129) {
			Content_h1_Choice129 n = (Content_h1_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice130) {
			Content_h1_Choice130 n = (Content_h1_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice131) {
			Content_h1_Choice131 n = (Content_h1_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice132) {
			Content_h1_Choice132 n = (Content_h1_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice133) {
			Content_h1_Choice133 n = (Content_h1_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_h1_Choice134) {
			Content_h1_Choice134 n = (Content_h1_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_sub1) {
			Element_sub1 n = (Element_sub1)node;
			{
				EmptyTag_sub v=n.getEmptyTag_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_sub2) {
			Element_sub2 n = (Element_sub2)node;
			{
				STag_sub v=n.getSTag_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_sub_Choice1 v : n.getContent_sub_Choice1()) {
				v.accept(this);
			}
			{
				ETag_sub v=n.getETag_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_sub) {
			EmptyTag_sub n = (EmptyTag_sub)node;
			printToken("<");
			printToken("sub");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_sub) {
			STag_sub n = (STag_sub)node;
			printToken("<");
			printToken("sub");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_sub) {
			ETag_sub n = (ETag_sub)node;
			printToken("</");
			printToken("sub");
			printToken(">");
			return false;
		}
		if (node instanceof Content_sub_Choice11) {
			Content_sub_Choice11 n = (Content_sub_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice12) {
			Content_sub_Choice12 n = (Content_sub_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice13) {
			Content_sub_Choice13 n = (Content_sub_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice14) {
			Content_sub_Choice14 n = (Content_sub_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice15) {
			Content_sub_Choice15 n = (Content_sub_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice16) {
			Content_sub_Choice16 n = (Content_sub_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice17) {
			Content_sub_Choice17 n = (Content_sub_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice18) {
			Content_sub_Choice18 n = (Content_sub_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice19) {
			Content_sub_Choice19 n = (Content_sub_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice110) {
			Content_sub_Choice110 n = (Content_sub_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice111) {
			Content_sub_Choice111 n = (Content_sub_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice112) {
			Content_sub_Choice112 n = (Content_sub_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice113) {
			Content_sub_Choice113 n = (Content_sub_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice114) {
			Content_sub_Choice114 n = (Content_sub_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice115) {
			Content_sub_Choice115 n = (Content_sub_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice116) {
			Content_sub_Choice116 n = (Content_sub_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice117) {
			Content_sub_Choice117 n = (Content_sub_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice118) {
			Content_sub_Choice118 n = (Content_sub_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice119) {
			Content_sub_Choice119 n = (Content_sub_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice120) {
			Content_sub_Choice120 n = (Content_sub_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice121) {
			Content_sub_Choice121 n = (Content_sub_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice122) {
			Content_sub_Choice122 n = (Content_sub_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice123) {
			Content_sub_Choice123 n = (Content_sub_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice124) {
			Content_sub_Choice124 n = (Content_sub_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice125) {
			Content_sub_Choice125 n = (Content_sub_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice126) {
			Content_sub_Choice126 n = (Content_sub_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice127) {
			Content_sub_Choice127 n = (Content_sub_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice128) {
			Content_sub_Choice128 n = (Content_sub_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice129) {
			Content_sub_Choice129 n = (Content_sub_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice130) {
			Content_sub_Choice130 n = (Content_sub_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice131) {
			Content_sub_Choice131 n = (Content_sub_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice132) {
			Content_sub_Choice132 n = (Content_sub_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice133) {
			Content_sub_Choice133 n = (Content_sub_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_sub_Choice134) {
			Content_sub_Choice134 n = (Content_sub_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_acronym1) {
			Element_acronym1 n = (Element_acronym1)node;
			{
				EmptyTag_acronym v=n.getEmptyTag_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_acronym2) {
			Element_acronym2 n = (Element_acronym2)node;
			{
				STag_acronym v=n.getSTag_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_acronym_Choice1 v : n.getContent_acronym_Choice1()) {
				v.accept(this);
			}
			{
				ETag_acronym v=n.getETag_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_acronym) {
			EmptyTag_acronym n = (EmptyTag_acronym)node;
			printToken("<");
			printToken("acronym");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_acronym) {
			STag_acronym n = (STag_acronym)node;
			printToken("<");
			printToken("acronym");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_acronym) {
			ETag_acronym n = (ETag_acronym)node;
			printToken("</");
			printToken("acronym");
			printToken(">");
			return false;
		}
		if (node instanceof Content_acronym_Choice11) {
			Content_acronym_Choice11 n = (Content_acronym_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice12) {
			Content_acronym_Choice12 n = (Content_acronym_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice13) {
			Content_acronym_Choice13 n = (Content_acronym_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice14) {
			Content_acronym_Choice14 n = (Content_acronym_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice15) {
			Content_acronym_Choice15 n = (Content_acronym_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice16) {
			Content_acronym_Choice16 n = (Content_acronym_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice17) {
			Content_acronym_Choice17 n = (Content_acronym_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice18) {
			Content_acronym_Choice18 n = (Content_acronym_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice19) {
			Content_acronym_Choice19 n = (Content_acronym_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice110) {
			Content_acronym_Choice110 n = (Content_acronym_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice111) {
			Content_acronym_Choice111 n = (Content_acronym_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice112) {
			Content_acronym_Choice112 n = (Content_acronym_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice113) {
			Content_acronym_Choice113 n = (Content_acronym_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice114) {
			Content_acronym_Choice114 n = (Content_acronym_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice115) {
			Content_acronym_Choice115 n = (Content_acronym_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice116) {
			Content_acronym_Choice116 n = (Content_acronym_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice117) {
			Content_acronym_Choice117 n = (Content_acronym_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice118) {
			Content_acronym_Choice118 n = (Content_acronym_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice119) {
			Content_acronym_Choice119 n = (Content_acronym_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice120) {
			Content_acronym_Choice120 n = (Content_acronym_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice121) {
			Content_acronym_Choice121 n = (Content_acronym_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice122) {
			Content_acronym_Choice122 n = (Content_acronym_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice123) {
			Content_acronym_Choice123 n = (Content_acronym_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice124) {
			Content_acronym_Choice124 n = (Content_acronym_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice125) {
			Content_acronym_Choice125 n = (Content_acronym_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice126) {
			Content_acronym_Choice126 n = (Content_acronym_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice127) {
			Content_acronym_Choice127 n = (Content_acronym_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice128) {
			Content_acronym_Choice128 n = (Content_acronym_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice129) {
			Content_acronym_Choice129 n = (Content_acronym_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice130) {
			Content_acronym_Choice130 n = (Content_acronym_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice131) {
			Content_acronym_Choice131 n = (Content_acronym_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice132) {
			Content_acronym_Choice132 n = (Content_acronym_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice133) {
			Content_acronym_Choice133 n = (Content_acronym_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_acronym_Choice134) {
			Content_acronym_Choice134 n = (Content_acronym_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_select1) {
			Element_select1 n = (Element_select1)node;
			{
				EmptyTag_select v=n.getEmptyTag_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_select2) {
			Element_select2 n = (Element_select2)node;
			{
				STag_select v=n.getSTag_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_select_Choice1 v : n.getContent_select_Choice1()) {
				v.accept(this);
			}
			{
				ETag_select v=n.getETag_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_select) {
			EmptyTag_select n = (EmptyTag_select)node;
			printToken("<");
			printToken("select");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_select) {
			STag_select n = (STag_select)node;
			printToken("<");
			printToken("select");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_select) {
			ETag_select n = (ETag_select)node;
			printToken("</");
			printToken("select");
			printToken(">");
			return false;
		}
		if (node instanceof Content_select_Choice11) {
			Content_select_Choice11 n = (Content_select_Choice11)node;
			{
				Element_optgroup v=n.getElement_optgroup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_select_Choice12) {
			Content_select_Choice12 n = (Content_select_Choice12)node;
			{
				Element_option v=n.getElement_option();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_del1) {
			Element_del1 n = (Element_del1)node;
			{
				EmptyTag_del v=n.getEmptyTag_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_del2) {
			Element_del2 n = (Element_del2)node;
			{
				STag_del v=n.getSTag_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_del_Choice1 v : n.getContent_del_Choice1()) {
				v.accept(this);
			}
			{
				ETag_del v=n.getETag_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_del) {
			EmptyTag_del n = (EmptyTag_del)node;
			printToken("<");
			printToken("del");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_del) {
			STag_del n = (STag_del)node;
			printToken("<");
			printToken("del");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_del) {
			ETag_del n = (ETag_del)node;
			printToken("</");
			printToken("del");
			printToken(">");
			return false;
		}
		if (node instanceof Content_del_Choice11) {
			Content_del_Choice11 n = (Content_del_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice12) {
			Content_del_Choice12 n = (Content_del_Choice12)node;
			{
				Element_p v=n.getElement_p();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice13) {
			Content_del_Choice13 n = (Content_del_Choice13)node;
			{
				Element_h1 v=n.getElement_h1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice14) {
			Content_del_Choice14 n = (Content_del_Choice14)node;
			{
				Element_h2 v=n.getElement_h2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice15) {
			Content_del_Choice15 n = (Content_del_Choice15)node;
			{
				Element_h3 v=n.getElement_h3();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice16) {
			Content_del_Choice16 n = (Content_del_Choice16)node;
			{
				Element_h4 v=n.getElement_h4();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice17) {
			Content_del_Choice17 n = (Content_del_Choice17)node;
			{
				Element_h5 v=n.getElement_h5();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice18) {
			Content_del_Choice18 n = (Content_del_Choice18)node;
			{
				Element_h6 v=n.getElement_h6();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice19) {
			Content_del_Choice19 n = (Content_del_Choice19)node;
			{
				Element_div v=n.getElement_div();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice110) {
			Content_del_Choice110 n = (Content_del_Choice110)node;
			{
				Element_ul v=n.getElement_ul();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice111) {
			Content_del_Choice111 n = (Content_del_Choice111)node;
			{
				Element_ol v=n.getElement_ol();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice112) {
			Content_del_Choice112 n = (Content_del_Choice112)node;
			{
				Element_dl v=n.getElement_dl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice113) {
			Content_del_Choice113 n = (Content_del_Choice113)node;
			{
				Element_pre v=n.getElement_pre();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice114) {
			Content_del_Choice114 n = (Content_del_Choice114)node;
			{
				Element_hr v=n.getElement_hr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice115) {
			Content_del_Choice115 n = (Content_del_Choice115)node;
			{
				Element_blockquote v=n.getElement_blockquote();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice116) {
			Content_del_Choice116 n = (Content_del_Choice116)node;
			{
				Element_address v=n.getElement_address();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice117) {
			Content_del_Choice117 n = (Content_del_Choice117)node;
			{
				Element_fieldset v=n.getElement_fieldset();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice118) {
			Content_del_Choice118 n = (Content_del_Choice118)node;
			{
				Element_table v=n.getElement_table();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice119) {
			Content_del_Choice119 n = (Content_del_Choice119)node;
			{
				Element_form v=n.getElement_form();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice120) {
			Content_del_Choice120 n = (Content_del_Choice120)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice121) {
			Content_del_Choice121 n = (Content_del_Choice121)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice122) {
			Content_del_Choice122 n = (Content_del_Choice122)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice123) {
			Content_del_Choice123 n = (Content_del_Choice123)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice124) {
			Content_del_Choice124 n = (Content_del_Choice124)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice125) {
			Content_del_Choice125 n = (Content_del_Choice125)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice126) {
			Content_del_Choice126 n = (Content_del_Choice126)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice127) {
			Content_del_Choice127 n = (Content_del_Choice127)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice128) {
			Content_del_Choice128 n = (Content_del_Choice128)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice129) {
			Content_del_Choice129 n = (Content_del_Choice129)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice130) {
			Content_del_Choice130 n = (Content_del_Choice130)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice131) {
			Content_del_Choice131 n = (Content_del_Choice131)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice132) {
			Content_del_Choice132 n = (Content_del_Choice132)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice133) {
			Content_del_Choice133 n = (Content_del_Choice133)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice134) {
			Content_del_Choice134 n = (Content_del_Choice134)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice135) {
			Content_del_Choice135 n = (Content_del_Choice135)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice136) {
			Content_del_Choice136 n = (Content_del_Choice136)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice137) {
			Content_del_Choice137 n = (Content_del_Choice137)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice138) {
			Content_del_Choice138 n = (Content_del_Choice138)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice139) {
			Content_del_Choice139 n = (Content_del_Choice139)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice140) {
			Content_del_Choice140 n = (Content_del_Choice140)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice141) {
			Content_del_Choice141 n = (Content_del_Choice141)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice142) {
			Content_del_Choice142 n = (Content_del_Choice142)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice143) {
			Content_del_Choice143 n = (Content_del_Choice143)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice144) {
			Content_del_Choice144 n = (Content_del_Choice144)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice145) {
			Content_del_Choice145 n = (Content_del_Choice145)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice146) {
			Content_del_Choice146 n = (Content_del_Choice146)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice147) {
			Content_del_Choice147 n = (Content_del_Choice147)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice148) {
			Content_del_Choice148 n = (Content_del_Choice148)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice149) {
			Content_del_Choice149 n = (Content_del_Choice149)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice150) {
			Content_del_Choice150 n = (Content_del_Choice150)node;
			{
				Element_noscript v=n.getElement_noscript();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice151) {
			Content_del_Choice151 n = (Content_del_Choice151)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice152) {
			Content_del_Choice152 n = (Content_del_Choice152)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_del_Choice153) {
			Content_del_Choice153 n = (Content_del_Choice153)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_li1) {
			Element_li1 n = (Element_li1)node;
			{
				EmptyTag_li v=n.getEmptyTag_li();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_li2) {
			Element_li2 n = (Element_li2)node;
			{
				STag_li v=n.getSTag_li();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_li_Choice1 v : n.getContent_li_Choice1()) {
				v.accept(this);
			}
			{
				ETag_li v=n.getETag_li();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_li) {
			EmptyTag_li n = (EmptyTag_li)node;
			printToken("<");
			printToken("li");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_li) {
			STag_li n = (STag_li)node;
			printToken("<");
			printToken("li");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_li) {
			ETag_li n = (ETag_li)node;
			printToken("</");
			printToken("li");
			printToken(">");
			return false;
		}
		if (node instanceof Content_li_Choice11) {
			Content_li_Choice11 n = (Content_li_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice12) {
			Content_li_Choice12 n = (Content_li_Choice12)node;
			{
				Element_p v=n.getElement_p();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice13) {
			Content_li_Choice13 n = (Content_li_Choice13)node;
			{
				Element_h1 v=n.getElement_h1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice14) {
			Content_li_Choice14 n = (Content_li_Choice14)node;
			{
				Element_h2 v=n.getElement_h2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice15) {
			Content_li_Choice15 n = (Content_li_Choice15)node;
			{
				Element_h3 v=n.getElement_h3();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice16) {
			Content_li_Choice16 n = (Content_li_Choice16)node;
			{
				Element_h4 v=n.getElement_h4();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice17) {
			Content_li_Choice17 n = (Content_li_Choice17)node;
			{
				Element_h5 v=n.getElement_h5();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice18) {
			Content_li_Choice18 n = (Content_li_Choice18)node;
			{
				Element_h6 v=n.getElement_h6();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice19) {
			Content_li_Choice19 n = (Content_li_Choice19)node;
			{
				Element_div v=n.getElement_div();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice110) {
			Content_li_Choice110 n = (Content_li_Choice110)node;
			{
				Element_ul v=n.getElement_ul();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice111) {
			Content_li_Choice111 n = (Content_li_Choice111)node;
			{
				Element_ol v=n.getElement_ol();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice112) {
			Content_li_Choice112 n = (Content_li_Choice112)node;
			{
				Element_dl v=n.getElement_dl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice113) {
			Content_li_Choice113 n = (Content_li_Choice113)node;
			{
				Element_pre v=n.getElement_pre();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice114) {
			Content_li_Choice114 n = (Content_li_Choice114)node;
			{
				Element_hr v=n.getElement_hr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice115) {
			Content_li_Choice115 n = (Content_li_Choice115)node;
			{
				Element_blockquote v=n.getElement_blockquote();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice116) {
			Content_li_Choice116 n = (Content_li_Choice116)node;
			{
				Element_address v=n.getElement_address();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice117) {
			Content_li_Choice117 n = (Content_li_Choice117)node;
			{
				Element_fieldset v=n.getElement_fieldset();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice118) {
			Content_li_Choice118 n = (Content_li_Choice118)node;
			{
				Element_table v=n.getElement_table();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice119) {
			Content_li_Choice119 n = (Content_li_Choice119)node;
			{
				Element_form v=n.getElement_form();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice120) {
			Content_li_Choice120 n = (Content_li_Choice120)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice121) {
			Content_li_Choice121 n = (Content_li_Choice121)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice122) {
			Content_li_Choice122 n = (Content_li_Choice122)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice123) {
			Content_li_Choice123 n = (Content_li_Choice123)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice124) {
			Content_li_Choice124 n = (Content_li_Choice124)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice125) {
			Content_li_Choice125 n = (Content_li_Choice125)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice126) {
			Content_li_Choice126 n = (Content_li_Choice126)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice127) {
			Content_li_Choice127 n = (Content_li_Choice127)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice128) {
			Content_li_Choice128 n = (Content_li_Choice128)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice129) {
			Content_li_Choice129 n = (Content_li_Choice129)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice130) {
			Content_li_Choice130 n = (Content_li_Choice130)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice131) {
			Content_li_Choice131 n = (Content_li_Choice131)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice132) {
			Content_li_Choice132 n = (Content_li_Choice132)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice133) {
			Content_li_Choice133 n = (Content_li_Choice133)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice134) {
			Content_li_Choice134 n = (Content_li_Choice134)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice135) {
			Content_li_Choice135 n = (Content_li_Choice135)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice136) {
			Content_li_Choice136 n = (Content_li_Choice136)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice137) {
			Content_li_Choice137 n = (Content_li_Choice137)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice138) {
			Content_li_Choice138 n = (Content_li_Choice138)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice139) {
			Content_li_Choice139 n = (Content_li_Choice139)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice140) {
			Content_li_Choice140 n = (Content_li_Choice140)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice141) {
			Content_li_Choice141 n = (Content_li_Choice141)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice142) {
			Content_li_Choice142 n = (Content_li_Choice142)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice143) {
			Content_li_Choice143 n = (Content_li_Choice143)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice144) {
			Content_li_Choice144 n = (Content_li_Choice144)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice145) {
			Content_li_Choice145 n = (Content_li_Choice145)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice146) {
			Content_li_Choice146 n = (Content_li_Choice146)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice147) {
			Content_li_Choice147 n = (Content_li_Choice147)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice148) {
			Content_li_Choice148 n = (Content_li_Choice148)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice149) {
			Content_li_Choice149 n = (Content_li_Choice149)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice150) {
			Content_li_Choice150 n = (Content_li_Choice150)node;
			{
				Element_noscript v=n.getElement_noscript();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice151) {
			Content_li_Choice151 n = (Content_li_Choice151)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice152) {
			Content_li_Choice152 n = (Content_li_Choice152)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_li_Choice153) {
			Content_li_Choice153 n = (Content_li_Choice153)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_cite1) {
			Element_cite1 n = (Element_cite1)node;
			{
				EmptyTag_cite v=n.getEmptyTag_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_cite2) {
			Element_cite2 n = (Element_cite2)node;
			{
				STag_cite v=n.getSTag_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_cite_Choice1 v : n.getContent_cite_Choice1()) {
				v.accept(this);
			}
			{
				ETag_cite v=n.getETag_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_cite) {
			EmptyTag_cite n = (EmptyTag_cite)node;
			printToken("<");
			printToken("cite");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_cite) {
			STag_cite n = (STag_cite)node;
			printToken("<");
			printToken("cite");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_cite) {
			ETag_cite n = (ETag_cite)node;
			printToken("</");
			printToken("cite");
			printToken(">");
			return false;
		}
		if (node instanceof Content_cite_Choice11) {
			Content_cite_Choice11 n = (Content_cite_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice12) {
			Content_cite_Choice12 n = (Content_cite_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice13) {
			Content_cite_Choice13 n = (Content_cite_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice14) {
			Content_cite_Choice14 n = (Content_cite_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice15) {
			Content_cite_Choice15 n = (Content_cite_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice16) {
			Content_cite_Choice16 n = (Content_cite_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice17) {
			Content_cite_Choice17 n = (Content_cite_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice18) {
			Content_cite_Choice18 n = (Content_cite_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice19) {
			Content_cite_Choice19 n = (Content_cite_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice110) {
			Content_cite_Choice110 n = (Content_cite_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice111) {
			Content_cite_Choice111 n = (Content_cite_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice112) {
			Content_cite_Choice112 n = (Content_cite_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice113) {
			Content_cite_Choice113 n = (Content_cite_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice114) {
			Content_cite_Choice114 n = (Content_cite_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice115) {
			Content_cite_Choice115 n = (Content_cite_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice116) {
			Content_cite_Choice116 n = (Content_cite_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice117) {
			Content_cite_Choice117 n = (Content_cite_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice118) {
			Content_cite_Choice118 n = (Content_cite_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice119) {
			Content_cite_Choice119 n = (Content_cite_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice120) {
			Content_cite_Choice120 n = (Content_cite_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice121) {
			Content_cite_Choice121 n = (Content_cite_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice122) {
			Content_cite_Choice122 n = (Content_cite_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice123) {
			Content_cite_Choice123 n = (Content_cite_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice124) {
			Content_cite_Choice124 n = (Content_cite_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice125) {
			Content_cite_Choice125 n = (Content_cite_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice126) {
			Content_cite_Choice126 n = (Content_cite_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice127) {
			Content_cite_Choice127 n = (Content_cite_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice128) {
			Content_cite_Choice128 n = (Content_cite_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice129) {
			Content_cite_Choice129 n = (Content_cite_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice130) {
			Content_cite_Choice130 n = (Content_cite_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice131) {
			Content_cite_Choice131 n = (Content_cite_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice132) {
			Content_cite_Choice132 n = (Content_cite_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice133) {
			Content_cite_Choice133 n = (Content_cite_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_cite_Choice134) {
			Content_cite_Choice134 n = (Content_cite_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_var1) {
			Element_var1 n = (Element_var1)node;
			{
				EmptyTag_var v=n.getEmptyTag_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_var2) {
			Element_var2 n = (Element_var2)node;
			{
				STag_var v=n.getSTag_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_var_Choice1 v : n.getContent_var_Choice1()) {
				v.accept(this);
			}
			{
				ETag_var v=n.getETag_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_var) {
			EmptyTag_var n = (EmptyTag_var)node;
			printToken("<");
			printToken("var");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_var) {
			STag_var n = (STag_var)node;
			printToken("<");
			printToken("var");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_var) {
			ETag_var n = (ETag_var)node;
			printToken("</");
			printToken("var");
			printToken(">");
			return false;
		}
		if (node instanceof Content_var_Choice11) {
			Content_var_Choice11 n = (Content_var_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice12) {
			Content_var_Choice12 n = (Content_var_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice13) {
			Content_var_Choice13 n = (Content_var_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice14) {
			Content_var_Choice14 n = (Content_var_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice15) {
			Content_var_Choice15 n = (Content_var_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice16) {
			Content_var_Choice16 n = (Content_var_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice17) {
			Content_var_Choice17 n = (Content_var_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice18) {
			Content_var_Choice18 n = (Content_var_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice19) {
			Content_var_Choice19 n = (Content_var_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice110) {
			Content_var_Choice110 n = (Content_var_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice111) {
			Content_var_Choice111 n = (Content_var_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice112) {
			Content_var_Choice112 n = (Content_var_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice113) {
			Content_var_Choice113 n = (Content_var_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice114) {
			Content_var_Choice114 n = (Content_var_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice115) {
			Content_var_Choice115 n = (Content_var_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice116) {
			Content_var_Choice116 n = (Content_var_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice117) {
			Content_var_Choice117 n = (Content_var_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice118) {
			Content_var_Choice118 n = (Content_var_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice119) {
			Content_var_Choice119 n = (Content_var_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice120) {
			Content_var_Choice120 n = (Content_var_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice121) {
			Content_var_Choice121 n = (Content_var_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice122) {
			Content_var_Choice122 n = (Content_var_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice123) {
			Content_var_Choice123 n = (Content_var_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice124) {
			Content_var_Choice124 n = (Content_var_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice125) {
			Content_var_Choice125 n = (Content_var_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice126) {
			Content_var_Choice126 n = (Content_var_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice127) {
			Content_var_Choice127 n = (Content_var_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice128) {
			Content_var_Choice128 n = (Content_var_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice129) {
			Content_var_Choice129 n = (Content_var_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice130) {
			Content_var_Choice130 n = (Content_var_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice131) {
			Content_var_Choice131 n = (Content_var_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice132) {
			Content_var_Choice132 n = (Content_var_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice133) {
			Content_var_Choice133 n = (Content_var_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_var_Choice134) {
			Content_var_Choice134 n = (Content_var_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_legend1) {
			Element_legend1 n = (Element_legend1)node;
			{
				EmptyTag_legend v=n.getEmptyTag_legend();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_legend2) {
			Element_legend2 n = (Element_legend2)node;
			{
				STag_legend v=n.getSTag_legend();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_legend_Choice1 v : n.getContent_legend_Choice1()) {
				v.accept(this);
			}
			{
				ETag_legend v=n.getETag_legend();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_legend) {
			EmptyTag_legend n = (EmptyTag_legend)node;
			printToken("<");
			printToken("legend");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_legend) {
			STag_legend n = (STag_legend)node;
			printToken("<");
			printToken("legend");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_legend) {
			ETag_legend n = (ETag_legend)node;
			printToken("</");
			printToken("legend");
			printToken(">");
			return false;
		}
		if (node instanceof Content_legend_Choice11) {
			Content_legend_Choice11 n = (Content_legend_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice12) {
			Content_legend_Choice12 n = (Content_legend_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice13) {
			Content_legend_Choice13 n = (Content_legend_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice14) {
			Content_legend_Choice14 n = (Content_legend_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice15) {
			Content_legend_Choice15 n = (Content_legend_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice16) {
			Content_legend_Choice16 n = (Content_legend_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice17) {
			Content_legend_Choice17 n = (Content_legend_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice18) {
			Content_legend_Choice18 n = (Content_legend_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice19) {
			Content_legend_Choice19 n = (Content_legend_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice110) {
			Content_legend_Choice110 n = (Content_legend_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice111) {
			Content_legend_Choice111 n = (Content_legend_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice112) {
			Content_legend_Choice112 n = (Content_legend_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice113) {
			Content_legend_Choice113 n = (Content_legend_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice114) {
			Content_legend_Choice114 n = (Content_legend_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice115) {
			Content_legend_Choice115 n = (Content_legend_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice116) {
			Content_legend_Choice116 n = (Content_legend_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice117) {
			Content_legend_Choice117 n = (Content_legend_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice118) {
			Content_legend_Choice118 n = (Content_legend_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice119) {
			Content_legend_Choice119 n = (Content_legend_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice120) {
			Content_legend_Choice120 n = (Content_legend_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice121) {
			Content_legend_Choice121 n = (Content_legend_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice122) {
			Content_legend_Choice122 n = (Content_legend_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice123) {
			Content_legend_Choice123 n = (Content_legend_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice124) {
			Content_legend_Choice124 n = (Content_legend_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice125) {
			Content_legend_Choice125 n = (Content_legend_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice126) {
			Content_legend_Choice126 n = (Content_legend_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice127) {
			Content_legend_Choice127 n = (Content_legend_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice128) {
			Content_legend_Choice128 n = (Content_legend_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice129) {
			Content_legend_Choice129 n = (Content_legend_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice130) {
			Content_legend_Choice130 n = (Content_legend_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice131) {
			Content_legend_Choice131 n = (Content_legend_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice132) {
			Content_legend_Choice132 n = (Content_legend_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice133) {
			Content_legend_Choice133 n = (Content_legend_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_legend_Choice134) {
			Content_legend_Choice134 n = (Content_legend_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_abbr1) {
			Element_abbr1 n = (Element_abbr1)node;
			{
				EmptyTag_abbr v=n.getEmptyTag_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_abbr2) {
			Element_abbr2 n = (Element_abbr2)node;
			{
				STag_abbr v=n.getSTag_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_abbr_Choice1 v : n.getContent_abbr_Choice1()) {
				v.accept(this);
			}
			{
				ETag_abbr v=n.getETag_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_abbr) {
			EmptyTag_abbr n = (EmptyTag_abbr)node;
			printToken("<");
			printToken("abbr");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_abbr) {
			STag_abbr n = (STag_abbr)node;
			printToken("<");
			printToken("abbr");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_abbr) {
			ETag_abbr n = (ETag_abbr)node;
			printToken("</");
			printToken("abbr");
			printToken(">");
			return false;
		}
		if (node instanceof Content_abbr_Choice11) {
			Content_abbr_Choice11 n = (Content_abbr_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice12) {
			Content_abbr_Choice12 n = (Content_abbr_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice13) {
			Content_abbr_Choice13 n = (Content_abbr_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice14) {
			Content_abbr_Choice14 n = (Content_abbr_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice15) {
			Content_abbr_Choice15 n = (Content_abbr_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice16) {
			Content_abbr_Choice16 n = (Content_abbr_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice17) {
			Content_abbr_Choice17 n = (Content_abbr_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice18) {
			Content_abbr_Choice18 n = (Content_abbr_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice19) {
			Content_abbr_Choice19 n = (Content_abbr_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice110) {
			Content_abbr_Choice110 n = (Content_abbr_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice111) {
			Content_abbr_Choice111 n = (Content_abbr_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice112) {
			Content_abbr_Choice112 n = (Content_abbr_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice113) {
			Content_abbr_Choice113 n = (Content_abbr_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice114) {
			Content_abbr_Choice114 n = (Content_abbr_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice115) {
			Content_abbr_Choice115 n = (Content_abbr_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice116) {
			Content_abbr_Choice116 n = (Content_abbr_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice117) {
			Content_abbr_Choice117 n = (Content_abbr_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice118) {
			Content_abbr_Choice118 n = (Content_abbr_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice119) {
			Content_abbr_Choice119 n = (Content_abbr_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice120) {
			Content_abbr_Choice120 n = (Content_abbr_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice121) {
			Content_abbr_Choice121 n = (Content_abbr_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice122) {
			Content_abbr_Choice122 n = (Content_abbr_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice123) {
			Content_abbr_Choice123 n = (Content_abbr_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice124) {
			Content_abbr_Choice124 n = (Content_abbr_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice125) {
			Content_abbr_Choice125 n = (Content_abbr_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice126) {
			Content_abbr_Choice126 n = (Content_abbr_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice127) {
			Content_abbr_Choice127 n = (Content_abbr_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice128) {
			Content_abbr_Choice128 n = (Content_abbr_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice129) {
			Content_abbr_Choice129 n = (Content_abbr_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice130) {
			Content_abbr_Choice130 n = (Content_abbr_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice131) {
			Content_abbr_Choice131 n = (Content_abbr_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice132) {
			Content_abbr_Choice132 n = (Content_abbr_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice133) {
			Content_abbr_Choice133 n = (Content_abbr_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_abbr_Choice134) {
			Content_abbr_Choice134 n = (Content_abbr_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Element_input1) {
			Element_input1 n = (Element_input1)node;
			{
				EmptyTag_input v=n.getEmptyTag_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_input2) {
			Element_input2 n = (Element_input2)node;
			{
				STag_input v=n.getSTag_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			{
				ETag_input v=n.getETag_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_input) {
			EmptyTag_input n = (EmptyTag_input)node;
			printToken("<");
			printToken("input");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_input) {
			STag_input n = (STag_input)node;
			printToken("<");
			printToken("input");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_input) {
			ETag_input n = (ETag_input)node;
			printToken("</");
			printToken("input");
			printToken(">");
			return false;
		}
		if (node instanceof Element_address1) {
			Element_address1 n = (Element_address1)node;
			{
				EmptyTag_address v=n.getEmptyTag_address();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Element_address2) {
			Element_address2 n = (Element_address2)node;
			{
				STag_address v=n.getSTag_address();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc1()) {
				v.accept(this);
			}
			for (Content_address_Choice1 v : n.getContent_address_Choice1()) {
				v.accept(this);
			}
			{
				ETag_address v=n.getETag_address();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CMisc v : n.getCMisc2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyTag_address) {
			EmptyTag_address n = (EmptyTag_address)node;
			printToken("<");
			printToken("address");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken("/>");
			return false;
		}
		if (node instanceof STag_address) {
			STag_address n = (STag_address)node;
			printToken("<");
			printToken("address");
			for (Attribute v : n.getAttribute()) {
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof ETag_address) {
			ETag_address n = (ETag_address)node;
			printToken("</");
			printToken("address");
			printToken(">");
			return false;
		}
		if (node instanceof Content_address_Choice11) {
			Content_address_Choice11 n = (Content_address_Choice11)node;
			{
				ASTStringNode v=n.getPcdata();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice12) {
			Content_address_Choice12 n = (Content_address_Choice12)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice13) {
			Content_address_Choice13 n = (Content_address_Choice13)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice14) {
			Content_address_Choice14 n = (Content_address_Choice14)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice15) {
			Content_address_Choice15 n = (Content_address_Choice15)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice16) {
			Content_address_Choice16 n = (Content_address_Choice16)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice17) {
			Content_address_Choice17 n = (Content_address_Choice17)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice18) {
			Content_address_Choice18 n = (Content_address_Choice18)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice19) {
			Content_address_Choice19 n = (Content_address_Choice19)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice110) {
			Content_address_Choice110 n = (Content_address_Choice110)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice111) {
			Content_address_Choice111 n = (Content_address_Choice111)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice112) {
			Content_address_Choice112 n = (Content_address_Choice112)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice113) {
			Content_address_Choice113 n = (Content_address_Choice113)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice114) {
			Content_address_Choice114 n = (Content_address_Choice114)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice115) {
			Content_address_Choice115 n = (Content_address_Choice115)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice116) {
			Content_address_Choice116 n = (Content_address_Choice116)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice117) {
			Content_address_Choice117 n = (Content_address_Choice117)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice118) {
			Content_address_Choice118 n = (Content_address_Choice118)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice119) {
			Content_address_Choice119 n = (Content_address_Choice119)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice120) {
			Content_address_Choice120 n = (Content_address_Choice120)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice121) {
			Content_address_Choice121 n = (Content_address_Choice121)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice122) {
			Content_address_Choice122 n = (Content_address_Choice122)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice123) {
			Content_address_Choice123 n = (Content_address_Choice123)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice124) {
			Content_address_Choice124 n = (Content_address_Choice124)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice125) {
			Content_address_Choice125 n = (Content_address_Choice125)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice126) {
			Content_address_Choice126 n = (Content_address_Choice126)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice127) {
			Content_address_Choice127 n = (Content_address_Choice127)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice128) {
			Content_address_Choice128 n = (Content_address_Choice128)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice129) {
			Content_address_Choice129 n = (Content_address_Choice129)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice130) {
			Content_address_Choice130 n = (Content_address_Choice130)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice131) {
			Content_address_Choice131 n = (Content_address_Choice131)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice132) {
			Content_address_Choice132 n = (Content_address_Choice132)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice133) {
			Content_address_Choice133 n = (Content_address_Choice133)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_address_Choice134) {
			Content_address_Choice134 n = (Content_address_Choice134)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any1) {
			Content_Any1 n = (Content_Any1)node;
			{
				Element_html v=n.getElement_html();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any2) {
			Content_Any2 n = (Content_Any2)node;
			{
				Element_button v=n.getElement_button();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any3) {
			Content_Any3 n = (Content_Any3)node;
			{
				Element_textarea v=n.getElement_textarea();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any4) {
			Content_Any4 n = (Content_Any4)node;
			{
				Element_em v=n.getElement_em();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any5) {
			Content_Any5 n = (Content_Any5)node;
			{
				Element_small v=n.getElement_small();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any6) {
			Content_Any6 n = (Content_Any6)node;
			{
				Element_area v=n.getElement_area();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any7) {
			Content_Any7 n = (Content_Any7)node;
			{
				Element_bdo v=n.getElement_bdo();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any8) {
			Content_Any8 n = (Content_Any8)node;
			{
				Element_form v=n.getElement_form();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any9) {
			Content_Any9 n = (Content_Any9)node;
			{
				Element_link v=n.getElement_link();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any10) {
			Content_Any10 n = (Content_Any10)node;
			{
				Element_label v=n.getElement_label();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any11) {
			Content_Any11 n = (Content_Any11)node;
			{
				Element_dt v=n.getElement_dt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any12) {
			Content_Any12 n = (Content_Any12)node;
			{
				Element_span v=n.getElement_span();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any13) {
			Content_Any13 n = (Content_Any13)node;
			{
				Element_title v=n.getElement_title();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any14) {
			Content_Any14 n = (Content_Any14)node;
			{
				Element_strong v=n.getElement_strong();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any15) {
			Content_Any15 n = (Content_Any15)node;
			{
				Element_script v=n.getElement_script();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any16) {
			Content_Any16 n = (Content_Any16)node;
			{
				Element_div v=n.getElement_div();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any17) {
			Content_Any17 n = (Content_Any17)node;
			{
				Element_dl v=n.getElement_dl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any18) {
			Content_Any18 n = (Content_Any18)node;
			{
				Element_blockquote v=n.getElement_blockquote();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any19) {
			Content_Any19 n = (Content_Any19)node;
			{
				Element_kbd v=n.getElement_kbd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any20) {
			Content_Any20 n = (Content_Any20)node;
			{
				Element_body v=n.getElement_body();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any21) {
			Content_Any21 n = (Content_Any21)node;
			{
				Element_ins v=n.getElement_ins();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any22) {
			Content_Any22 n = (Content_Any22)node;
			{
				Element_map v=n.getElement_map();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any23) {
			Content_Any23 n = (Content_Any23)node;
			{
				Element_dd v=n.getElement_dd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any24) {
			Content_Any24 n = (Content_Any24)node;
			{
				Element_fieldset v=n.getElement_fieldset();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any25) {
			Content_Any25 n = (Content_Any25)node;
			{
				Element_head v=n.getElement_head();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any26) {
			Content_Any26 n = (Content_Any26)node;
			{
				Element_col v=n.getElement_col();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any27) {
			Content_Any27 n = (Content_Any27)node;
			{
				Element_base v=n.getElement_base();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any28) {
			Content_Any28 n = (Content_Any28)node;
			{
				Element_big v=n.getElement_big();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any29) {
			Content_Any29 n = (Content_Any29)node;
			{
				Element_meta v=n.getElement_meta();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any30) {
			Content_Any30 n = (Content_Any30)node;
			{
				Element_code v=n.getElement_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any31) {
			Content_Any31 n = (Content_Any31)node;
			{
				Element_tbody v=n.getElement_tbody();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any32) {
			Content_Any32 n = (Content_Any32)node;
			{
				Element_option v=n.getElement_option();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any33) {
			Content_Any33 n = (Content_Any33)node;
			{
				Element_q v=n.getElement_q();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any34) {
			Content_Any34 n = (Content_Any34)node;
			{
				Element_p v=n.getElement_p();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any35) {
			Content_Any35 n = (Content_Any35)node;
			{
				Element_ol v=n.getElement_ol();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any36) {
			Content_Any36 n = (Content_Any36)node;
			{
				Element_thead v=n.getElement_thead();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any37) {
			Content_Any37 n = (Content_Any37)node;
			{
				Element_ul v=n.getElement_ul();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any38) {
			Content_Any38 n = (Content_Any38)node;
			{
				Element_i v=n.getElement_i();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any39) {
			Content_Any39 n = (Content_Any39)node;
			{
				Element_pre v=n.getElement_pre();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any40) {
			Content_Any40 n = (Content_Any40)node;
			{
				Element_optgroup v=n.getElement_optgroup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any41) {
			Content_Any41 n = (Content_Any41)node;
			{
				Element_img v=n.getElement_img();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any42) {
			Content_Any42 n = (Content_Any42)node;
			{
				Element_caption v=n.getElement_caption();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any43) {
			Content_Any43 n = (Content_Any43)node;
			{
				Element_b v=n.getElement_b();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any44) {
			Content_Any44 n = (Content_Any44)node;
			{
				Element_a v=n.getElement_a();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any45) {
			Content_Any45 n = (Content_Any45)node;
			{
				Element_br v=n.getElement_br();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any46) {
			Content_Any46 n = (Content_Any46)node;
			{
				Element_style v=n.getElement_style();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any47) {
			Content_Any47 n = (Content_Any47)node;
			{
				Element_hr v=n.getElement_hr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any48) {
			Content_Any48 n = (Content_Any48)node;
			{
				Element_param v=n.getElement_param();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any49) {
			Content_Any49 n = (Content_Any49)node;
			{
				Element_table v=n.getElement_table();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any50) {
			Content_Any50 n = (Content_Any50)node;
			{
				Element_tt v=n.getElement_tt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any51) {
			Content_Any51 n = (Content_Any51)node;
			{
				Element_tr v=n.getElement_tr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any52) {
			Content_Any52 n = (Content_Any52)node;
			{
				Element_th v=n.getElement_th();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any53) {
			Content_Any53 n = (Content_Any53)node;
			{
				Element_td v=n.getElement_td();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any54) {
			Content_Any54 n = (Content_Any54)node;
			{
				Element_samp v=n.getElement_samp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any55) {
			Content_Any55 n = (Content_Any55)node;
			{
				Element_tfoot v=n.getElement_tfoot();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any56) {
			Content_Any56 n = (Content_Any56)node;
			{
				Element_dfn v=n.getElement_dfn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any57) {
			Content_Any57 n = (Content_Any57)node;
			{
				Element_noscript v=n.getElement_noscript();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any58) {
			Content_Any58 n = (Content_Any58)node;
			{
				Element_colgroup v=n.getElement_colgroup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any59) {
			Content_Any59 n = (Content_Any59)node;
			{
				Element_object v=n.getElement_object();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any60) {
			Content_Any60 n = (Content_Any60)node;
			{
				Element_sup v=n.getElement_sup();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any61) {
			Content_Any61 n = (Content_Any61)node;
			{
				Element_h6 v=n.getElement_h6();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any62) {
			Content_Any62 n = (Content_Any62)node;
			{
				Element_h5 v=n.getElement_h5();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any63) {
			Content_Any63 n = (Content_Any63)node;
			{
				Element_h4 v=n.getElement_h4();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any64) {
			Content_Any64 n = (Content_Any64)node;
			{
				Element_h3 v=n.getElement_h3();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any65) {
			Content_Any65 n = (Content_Any65)node;
			{
				Element_h2 v=n.getElement_h2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any66) {
			Content_Any66 n = (Content_Any66)node;
			{
				Element_h1 v=n.getElement_h1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any67) {
			Content_Any67 n = (Content_Any67)node;
			{
				Element_sub v=n.getElement_sub();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any68) {
			Content_Any68 n = (Content_Any68)node;
			{
				Element_acronym v=n.getElement_acronym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any69) {
			Content_Any69 n = (Content_Any69)node;
			{
				Element_select v=n.getElement_select();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any70) {
			Content_Any70 n = (Content_Any70)node;
			{
				Element_del v=n.getElement_del();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any71) {
			Content_Any71 n = (Content_Any71)node;
			{
				Element_li v=n.getElement_li();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any72) {
			Content_Any72 n = (Content_Any72)node;
			{
				Element_cite v=n.getElement_cite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any73) {
			Content_Any73 n = (Content_Any73)node;
			{
				Element_var v=n.getElement_var();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any74) {
			Content_Any74 n = (Content_Any74)node;
			{
				Element_legend v=n.getElement_legend();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any75) {
			Content_Any75 n = (Content_Any75)node;
			{
				Element_abbr v=n.getElement_abbr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any76) {
			Content_Any76 n = (Content_Any76)node;
			{
				Element_input v=n.getElement_input();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Content_Any77) {
			Content_Any77 n = (Content_Any77)node;
			{
				Element_address v=n.getElement_address();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		return true;
	}
}
