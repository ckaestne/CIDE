package cide.dtdgen;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import tmp.generated_dtd.DTDParser;
import tmp.generated_dtd.ElementDecl;

import cide.dtdgen.DTDgen;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;

public class TestDTDgen extends WhiteSpaceTest {

	private File outputFile;

	@Before
	public void setUp() throws Exception {
		outputFile = new File("test/people.gcide");
	}

	@Test
	public void runGen() throws ParseException, IOException {
		new DTDgen("people", new File("test/people.dtd"), outputFile).run();
	}

	@Test
	public void lexSingleEntry() throws FileNotFoundException, ParseException {
		DTDgen d = new DTDgen("people", "<!ELEMENT people_list (person*)>",
				outputFile);
		d.parse();
		String lex = d.getLexDefs();
		assertWhiteSpaceEqual(
				"<E_people_list: \"people_list\"> : LexElement_Inside", lex);
	}

	@Test
	public void lexEntrys() throws FileNotFoundException, ParseException {
		DTDgen d = new DTDgen(
				"people",
				"<!ELEMENT people_list (person*)>\n"
						+ "<!ELEMENT person (name, birthdate?, gender?, socialsecuritynumber?)>\n"
						+ "<!ELEMENT name (#PCDATA)>", outputFile);
		d.parse();
		String lex = d.getLexDefs();
		assertWhiteSpaceEqual(
				"  <E_people_list: \"people_list\"> : LexElement_Inside\n"
						+ "| <E_person: \"person\"> : LexElement_Inside\n"
						+ "| <E_name: \"name\"> : LexElement_Inside", lex);
	}

	@Test
	public void prodGenInnerRefEmpty() throws ParseException {
		ElementDecl elementDecl = parseElementDecl("<!ELEMENT people_list EMPTY>");
		assertWhiteSpaceEqual("", DTDgen
				.getInnerContentRef(elementDecl));
		assertWhiteSpaceEqual("", DTDgen.getInnerContentDecl(elementDecl));
	}


	@Test
	public void prodGenInnerRefAny() throws ParseException {
		ElementDecl elementDecl = parseElementDecl("<!ELEMENT people_list ANY>");
		assertWhiteSpaceEqual("Content_Any", DTDgen
				.getInnerContentRef(elementDecl));
		assertWhiteSpaceEqual("", DTDgen.getInnerContentDecl(elementDecl));
	}

	
	@Test
	public void prodGenInnerRefSingle() throws ParseException {
		ElementDecl elementDecl = parseElementDecl("<!ELEMENT people_list (a)>");
		assertWhiteSpaceEqual("Content_people_list_Seq1", DTDgen
				.getInnerContentRef(elementDecl));
		assertWhiteSpaceEqual("Content_people_list_Seq1: Element_a;", DTDgen.getInnerContentDecl(elementDecl));
	}
	@Test
	public void prodGenInnerRefSingleMul() throws ParseException {
		ElementDecl elementDecl = parseElementDecl("<!ELEMENT people_list (a*)>");
		assertWhiteSpaceEqual("Content_people_list_Seq1", DTDgen
				.getInnerContentRef(elementDecl));
		assertWhiteSpaceEqual("Content_people_list_Seq1: (LL(2) Element_a)*;", DTDgen.getInnerContentDecl(elementDecl));
	}
	
	@Test
	public void prodGenInnerRefSeq() throws ParseException {
		ElementDecl elementDecl = parseElementDecl("<!ELEMENT people_list (a,b)>");
		assertWhiteSpaceEqual("Content_people_list_Seq1", DTDgen
				.getInnerContentRef(elementDecl));
		assertWhiteSpaceEqual("Content_people_list_Seq1: Element_a Element_b;", DTDgen.getInnerContentDecl(elementDecl));
	}

	@Test
	public void prodGenInnerRefSeq2() throws ParseException {
		ElementDecl elementDecl = parseElementDecl("<!ELEMENT people_list (a,b?)>");
		assertWhiteSpaceEqual("Content_people_list_Seq1", DTDgen
				.getInnerContentRef(elementDecl));
		assertWhiteSpaceEqual("Content_people_list_Seq1: Element_a [LL(2)  Element_b];", DTDgen.getInnerContentDecl(elementDecl));
	}
	
	@Test
	public void prodGenInnerRefChoice() throws ParseException {
		ElementDecl elementDecl = parseElementDecl("<!ELEMENT people_list (a|b?)>");
		assertWhiteSpaceEqual("Content_people_list_Choice1", DTDgen
				.getInnerContentRef(elementDecl));
		assertWhiteSpaceEqual("Content_people_list_Choice1: LL(2) Element_a | LL(2) [ LL(2) Element_b];", DTDgen.getInnerContentDecl(elementDecl));
	}
	
	@Test
	public void prodGenInnerRefChoice2() throws ParseException {
		ElementDecl elementDecl = parseElementDecl("<!ELEMENT people_list (a|b)>");
		assertWhiteSpaceEqual("Content_people_list_Choice1", DTDgen
				.getInnerContentRef(elementDecl));
		assertWhiteSpaceEqual("Content_people_list_Choice1: LL(2) Element_a | LL(2) Element_b;", DTDgen.getInnerContentDecl(elementDecl));
	}
	
	@Test
	public void prodGenInnerRefMulChoice() throws ParseException {
		ElementDecl elementDecl = parseElementDecl("<!ELEMENT people_list (a|b)*>");
		assertWhiteSpaceEqual("(LL(2) Content_people_list_Choice1)*", DTDgen
				.getInnerContentRef(elementDecl));
		assertWhiteSpaceEqual("Content_people_list_Choice1: LL(2) Element_a | LL(2) Element_b;", DTDgen.getInnerContentDecl(elementDecl));
	}
	
	@Test
	public void prodGenInnerRefComplex() throws ParseException {
		ElementDecl elementDecl = parseElementDecl("<!ELEMENT people_list (a,(b|c)*,d?)*>");
		assertWhiteSpaceEqual("(LL(2) Content_people_list_Seq1)*", DTDgen
				.getInnerContentRef(elementDecl));
		assertWhiteSpaceEqual("Content_people_list_Choice1: LL(2) Element_b | LL(2) Element_c;" +
				"Content_people_list_Seq1: Element_a (LL(2) Content_people_list_Choice1)* [LL(2) Element_d];", DTDgen.getInnerContentDecl(elementDecl));
	}
	


	private ElementDecl parseElementDecl(String string) throws ParseException {
		DTDParser d = new DTDParser(new OffsetCharStream(
				new ByteArrayInputStream(string.getBytes())));
		return d.ElementDecl();
	}

	// @Test
	// public void prodSingleEntry() throws FileNotFoundException,
	// ParseException {
	// DTDgen d = new DTDgen("people", "<!ELEMENT people_list (person*)>",
	// outputFile);
	// d.parse();
	// String prod = d.getProductions();
	// assertWhiteSpaceEqual(
	// " RootElement: Element_people_list;\n"
	// + " Element_people_list: \n"
	// + " LL(\"EmptyTag_people_list()\") EmptyTag_people_list |
	// STag_people_list (Content_people_list1)* ETag_people_list;\n"
	// + " EmptyTag_people_list : \"<\" \"!<E_people_list>people_list\"
	// (Attribute)* \"!<SLASHEND>/>\";\n"
	// + " STag_people_list : \"<\" \"!<E_people_list>people_list\" (Attribute)*
	// \"!<ELEMENT_END>>\";\n"
	// + " ETag_people_list: \"</\" \"!<E_people_list>people_list\"<NONE>
	// \"!<ELEMENT_END>>\";\n"
	// + " Content_people_list1: Element_person | Comment | Whitespace;\n"
	//
	// , prod);
	// }
}
