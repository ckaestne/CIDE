import java.io.File;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import cide.gast.ASTNode;
import cide.gast.ISourceFile;
import cide.gparser.ParseException;

import tmp.generated_cppapprox.*;

public class CPPParserTest extends CPPApproxParserTester {

	private ISourceFile r;

	@Before
	public void setUp() throws Exception {
		tester = new CPPApproxParserTester();
	}

	@Test
	public void testSimpleClass() throws Exception {
		r = parseAndCheck("class String{};");
		assertASTContains(r, CPPClass.class);
	}

	@Test
	public void testSimpleClass2() throws Exception {
		r = parseAndCheck("class String{private: public:};");
		assertASTContains(r, CPPClass.class);
		r = parseAndCheck("class String{Int GetX() {body;} public: Int GetZ(Int X){}};");
		assertASTContains(r, CPPClass.class);
		r = parseAndCheck("class String{private: Int x; Int y; Int GetX() {body;}};");
		assertASTContains(r, CPPClass.class);
	}
	
	@Test
	public void testConstructor() throws Exception {
		r = parseAndCheck("class String{String();String(int a, int b);};");
		assertASTContains(r, CPPClass.class);
	}
	
	
	@Test
	public void testVirtual() throws Exception {
		r = parseAndCheck("class String{virtual void Propagate() = 0;};");
		assertASTContains(r, CPPClass.class);
		r = parseAndCheck("class String{virtual void Propagate() {}};");
		assertASTContains(r, CPPClass.class);
	}	
	
	@Test
	public void testInheritance() throws Exception {
		r = parseAndCheck("class Track : public TObject { };");
		assertASTContains(r, CPPClass.class);
	}	
	
	@Test
	public void testClassForwardDecl() throws Exception {
		r = parseAndCheck("class Track;");
		assertASTContains(r, CPPClassForwardDecl.class);
	}	
	@Test 
	public void testFameDBMS() throws Exception {
		File folder = new File("test/famedbms");
		assert folder.isDirectory();
		parseFilesInFolder(folder, true,true);
	}
	
	@Test
	public void testIgnoreInline() throws Exception {
		r= tester.runParser("class Track {Int \n#line 13 \"C:/workspace\"\nGetX() {body;} };");
		assertASTContains(r, CPPClass.class);
	}	

	@Test
	public void testNamespaceNotation() throws Exception {
		r= tester.runParser("class XX::Track;");
		assertASTContains(r, CPPClassForwardDecl.class);
		r= tester.runParser("class ::Track;");
		assertASTContains(r, CPPClassForwardDecl.class);
	}	
	@Test(expected=ParseException.class)
	public void testNamespaceNotationFail() throws Exception {
		r= tester.runParser("class XX::;");
	}	
	
	@Test
	public void testMisc() throws Exception {
		r = parseAndCheck("class String{__forceinline Int GetX() {body;}};");
		assertASTContains(r, CPPClass.class);
		//invariant methods
		r = parseAndCheck("class String{Int GetX() const {body;}};");
		assertASTContains(r, CPPClass.class);
		//constructor initializers
		r = parseAndCheck("class String{String() : pb(pb) {body;}};");
		assertASTContains(r, CPPClass.class);
		
		r = parseAndCheck("class String{bool Main::testGet() {body;}};");
		assertASTContains(r, CPPClass.class);
		
	}
	
	@Test
	public void testTemplates() throws Exception {
		r= tester.runParser("template<A, B>");
		assertASTContains(r, TemplateDecl.class);
//		r= tester.runParser("class ::Track;");
//		assertASTContains(r, CPPClassForwardDecl.class);
	}
	
	
	@Test
	public void testOperatorOverloading() throws ParseException{
		r= tester.runParser("POSITION& operator=(const POSITION& v)    { Value = v.Value; return *this; }");
//		assertASTContains(r, TemplateDecl.class);
	    

	}
//	@Test
//	public void testCase() throws Exception {
//		r = parseAndCheck("void foo(){ switch (a) { case 1:case 2:abc;}; }");
//		assertASTContains(r, SwCase1.class);
//	}
	

	
}
