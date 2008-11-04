import org.junit.Assert;
import org.junit.Test;

import tmp.generated_cppapprox.CPPApproxParser;
import cide.gast.ASTNode;
import cide.gparser.ParseException;

public class CPPPrettyPrinterTest extends CPPApproxParserTester {


	@Test
	public void testDoubleSymbols() throws ParseException {
		checkSymbol("i++;", "++");
		// checkSymbol("i++;","i++");
		// checkSymbol("++i;","++i");
		// checkSymbol("i=i+ ++i;","++i");
		checkSymbol("i=3<<5;", "<<");
		checkSymbol("i=3==5;", "==");
	}

	@Test
	public void testSingleLineComments() throws ParseException {
		String stmt = "{\ni++;\n//test\ni++;\nj++;//test2\n}";
		CPPApproxParser p = getParser(stmt);
		ASTNode s = p.Block();
		s.render();
		// System.out.println(pp.getResult());
		// Assert.assertTrue(pp.getResult(),pp.getResult().contains(symbol));
	}

	@Test
	public void testEndIfPosition() throws ParseException {
		String stmt = "{\ni++;\n#ifdef x\ni++;\nj++;\n#endif\nx++;}";
		CPPApproxParser p = getParser(stmt);
		ASTNode s = p.Block();
		s.render();
		// System.out.println(pp.getResult());

		stmt = "        /*\n"
				+ "         * This method get the data from dbms\n"
				+ "         */\n"
				+ "      void GetData (void)\n"
				+ "      {\n"
				+ "#ifdef WIN32\n"
				+ "          RECORD r;\n"
				+ "          this->KeyInput(r);\n"
				+ "          if(dam.GetData(r))\n"
				+ "              PrintValue(r.value, r.size);\n"
				+ "          else\n"
				+ "              printf(\"\\nFailed to get data!\");\n"
				+ "\n"
				+ "#endif\n"
				+ "#ifdef btnode3\n"
				+ "          /* Dummy data to check the functionality of getting */\n"
				+ "          \n"
				+ "          for (size_t looper = 0; looper < 21; looper ++)\n"
				+ "          {\n" + "              r.key = looper;\n"
				+ "              printf(\"\\nEntered Key:%d\", r.key);\n"
				+ "              if(dam.GetData(r))\n"
				+ "                PrintValue(r.value, r.size);\n"
				+ "              else\n"
				+ "                printf(\"\\nFailed to get data!\");\n"
				+ "          }\n" + "\n" + "#endif\n" + "      }\n";

		p = getParser(stmt);
		s = p.Function();
		s.render();
		
//		Assert.assertFalse(pp.getResult().matches("/endif/"));
//		Assert.assertFalse( Pattern.matches("endif", pp.getResult()));
//		System.out.println(pp.getResult());
	}

	@Test
	public void testWhileAndForSpaces() throws ParseException {
		checkSymbol("while (*(value + ++count)!='\\0')i++;",
				"(*(value + ++count)!='\\0')");
		checkSymbol(
				"for(unsigned int looper=0;looper<copysize*BYTEFACTOR;looper++){}",
				"unsigned int looper");
	}

	@Test
	public void testDefineSpaces() throws ParseException {
		CPPApproxParser p = getParser("#define PAGE_BUFFER_MAX_SIZE (4*1024)\n");
		ASTNode s = p.PPDefineStatement();
		String r=s.render();
		Assert.assertTrue(r, r.contains(
				"PAGE_BUFFER_MAX_SIZE (4*1024)"));
	}
	
	

	private void checkSymbol(String stmt, String symbol) throws ParseException {
		CPPApproxParser p = getParser(stmt);
		ASTNode s = p.CodeUnit_InBlock();
		String r=s.render();
		Assert.assertTrue(r, r.contains(symbol));
		// System.out.println(pp.getResult());
	}

}
