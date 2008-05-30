import java.io.File;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import tmp.generated_capprox.Function;
import tmp.generated_capprox.PPDefineStatement1;
import tmp.generated_capprox.PPIfDef_TopLevel;
import tmp.generated_capprox.PPIncludeStatement;
import tmp.generated_capprox.Statement;
import cide.gast.ASTNode;
import cide.gast.ASTVisitor;
import cide.gast.ISourceFile;
import cide.gparser.ParseException;

public class ParserTest extends CApproxParserTester {

	private boolean foundDefine;
	private ISourceFile r;

	@Before
	public void setUp() throws Exception {
		tester = new CApproxParserTester();
	}

	@Test
	public void testEmpty() throws Exception {
		parseAndCheck("");
	}

	@Test
	public void testLiterals() throws Exception {
		getParser("1").Literal();
		getParser("\"xxx\"").Literal();
		getParser("'b'").Literal();
	}

	@Test
	public void testNL() throws Exception {
		parseAndCheck("\n\n\n\n\n\n\n");
	}

	@Test
	public void testEmptyStatements() throws Exception {
		parseAndCheck("\n;;;\n\n;\n\n;");
	}

	@Test
	public void testComments() throws Exception {
		File cfile = new File("test/comment.c");
		ISourceFile parseResult = tester.runParser(cfile);
		assertWhiteSpaceEqual("", printAST(parseResult));
	}

	@Test
	public void testInclude() throws Exception {
		r = parseAndCheck("#include <stdio.h>\n");
		assertASTContains(r, PPIncludeStatement.class);

		r = parseAndCheck("#include \"stdio.h\"\n");
		assertASTContains(r, PPIncludeStatement.class);

		r = parseAndCheck("# include \"stdio.h\"\n");
		assertASTContains(r, PPIncludeStatement.class);
	}

	@Test
	public void testDefine() throws Exception {
		ISourceFile x = parseAndCheck("#define x y.z\n a;b;");
		foundDefine = false;
		x.accept(new ASTVisitor() {
			public boolean visit(ASTNode node) {
				if (node instanceof PPDefineStatement1) {
					foundDefine = true;
					String v = ((PPDefineStatement1) node).getFindlineend()
							.getValue();
					assertWhiteSpaceEqual("x y.z", v);
				}
				return super.visit(node);
			}
		});
		Assert.assertTrue(foundDefine);
		r = parseAndCheck("#   define x y.z\n a;b;");
		assertASTContains(r, PPDefineStatement1.class);
		parseAndCheck("#define FOREVER              while(1)" + "\n");

	}

	@Test
	public void testMultilineDefine() throws Exception {
		r = parseAndCheck("#define x (y. \\\n z\n");
		assertASTContains(r, PPDefineStatement1.class);
	}

	@Test
	public void testSimpleFunction() throws Exception {
		parseAndCheck("int main(){}");
		parseAndCheck("int main()" + "{" + "   printf(\"Hello World/n\");"
				+ "    return (0);}");
		parseAndCheck("int main();");
	}

	@Test
	public void testIf() throws Exception {
		parseAndCheckInFunction("if (x) {x;}");
		parseAndCheckInFunction("if (x) {x;} else {y;}");
		parseAndCheckInFunction("if (x) x; y;");
		parseAndCheckInFunction("if (x) {x;} else if (b) {x;} else {y;}");
	}

	@Test
	public void testIfDef() throws Exception {
		ISourceFile r = parseAndCheck("#ifdef x a; b; #endif y;");
		assertASTNesting(r, PPIfDef_TopLevel.class, Statement.class);

		parseAndCheck("#ifdef x a; #else b; #endif y;");

		parseAndCheck("void main1() {}  #ifdef x void main() {} #endif void main2() {} ");

		parseAndCheck("#ifndef x a; b; #endif y;");

		r = parseAndCheck("#ifdef x a; #ifdef xx a; b; #endif #endif y;");
		assertASTNesting(r, PPIfDef_TopLevel.class, PPIfDef_TopLevel.class);

		parseAndCheckInFunction("#ifdef x a; b; #endif y;");
		parseAndCheck("#if 0\n a; b;\n #endif \ny;");
		parseAndCheck("#if x && y\n a; b;\n #endif \ny;");
		parseAndCheck("#if 0\n a;\n#elif a\n b;\n#elsif x\na;\n #endif \ny;");
	}

	@Test
	public void testPreprocessorMisc() throws Exception {
		parseAndCheck("#line x \"abc\"\na;");
	}

	@Test
	public void testTypeDef() throws Exception {
		parseAndCheck("typedef int aaa, bbb, ccc;");
		parseAndCheck("typedef int ( * _onexit_t)(void);");
		parseAndCheck("typedef int func(int, int);");
		parseAndCheck("typedef int (*a10ptoa5i[10])[5];");
	}

	@Test
	public void testFor() throws Exception {
		parseAndCheckInFunction("for (x;y;z) {x;}");
		parseAndCheckInFunction("for (;;) {x;}");
		parseAndCheckInFunction("for (x;a;b) x; y;");
	}

	@Test
	public void testFunctionPointer() throws Exception {
		r = parseAndCheck("wchar_t *  _wgetdcwd(int, wchar_t *, int);");
		assertASTContains(r, Function.class);
		r = parseAndCheck("FILE             *fp;");
		assertASTNotContains(r, Function.class);
	}

	@Test
	public void testMisc() throws Exception {
		parseAndCheck("int a[]={1,3,3};");
		parseAndCheck("int a[]=(abc){1,3,3};");
		parseAndCheckInFunction("a:");
		parseAndCheckInFunction("b;a:x;y:a;goto x;");
		r = parseAndCheck("static int a(){}");
		assertASTContains(r, Function.class);
		// r = parseAndCheck("__attribute__((format(printf, 1, 2)))\nstatic int
		// a(){}");
		// assertASTContains(r, Function.class);
		r = parseAndCheck("static long long a(){}");
		assertASTContains(r, Function.class);
		r = parseAndCheck("static inline const int a(){}");
		assertASTContains(r, Function.class);
		parseAndCheck("int a(){{}}");

		r = parseAndCheck("struct ntfs_device *ntfs_device_alloc(const char *name, const long state,\n		struct ntfs_device_operations *dops, void *priv_data)\n{}");
		assertASTContains(r, Function.class);
		r = parseAndCheck("static jobject __dbj_wrap_DB_LSN(JNIEnv *jenv, DB_LSN *lsn)\n	{\n			return (*jenv)->NewObject(jenv, dblsn_class, dblsn_construct,\n			    lsn->file, lsn->offset);\n		}");
		assertASTContains(r, Function.class);
		

		parseAndCheck("static struct ntfs_logging ntfs_log = (struct ntfs_logging) {\n"
				+ ".levels = NTFS_LOG_LEVEL_INFO | NTFS_LOG_LEVEL_QUIET |\n"
				+ "		NTFS_LOG_LEVEL_WARNING | NTFS_LOG_LEVEL_ERROR |\n"
				+ "		NTFS_LOG_LEVEL_PERROR | NTFS_LOG_LEVEL_CRITICAL |\n"
				+ "		NTFS_LOG_LEVEL_PROGRESS |\n"
				+ "		0,\n"
				+ ".flags = NTFS_LOG_FLAG_ONLYNAME,\n"
				+ ".handler = ntfs_log_handler_null,\n" + "};");
		parseAndCheck("enum {	NF_KEY_HELP,	NF_KEY_UMASK,};");
		parseAndCheck("enum action {	NF_KEY_HELP,	NF_KEY_UMASK,};");

		parseAndCheckInFunction("#if 0\nif(a) {a;b;}\n#endif\n");
		
		parseAndCheck("extern \"C\"\n		{\n#include <stdlib.h>\n }");
		
	}

	@Test
	public void testSwitch() throws ParseException {
		parseAndCheckInFunction("switch (a) { case b: d; case d: case e: x; default:}");
		parseAndCheckInFunction("switch (a) { case (B)b: d;}");
		parseAndCheckInFunction("switch (a) { case 1: d; case 2:}");
		parseAndCheckInFunction("switch (a) { case 1: d; case 2: x; default: a;}");
		parseAndCheckInFunction("switch (a) { case 1: d; default: a; case 2: y;}");
		parseAndCheckInFunction("switch (a) { case 1: d; default: a; case 2: y;}");
		parseAndCheckInFunction("switch (a) { case b | x: d; case d: case e: x; default:}");
		parseAndCheckInFunction("switch (a) { case -1: d; case 2:}");
	}

	@Test
	public void savla_extern() throws Exception {
		r = parseAndCheck("extern unsigned char	__tiny TESTEO_LLAMA;");
		assertASTNotContains(r, Function.class);
		r = parseAndCheck("extern void LlamaInicializa(void) {}");
		assertASTContains(r, Function.class);
	}

	@Test
	public void salva_regbank() throws ParseException {
		r = parseAndCheck("extern void __regbank(1) inttc1(void){}");
		assertASTContains(r, Function.class);
	}

	@Test
	public void testComplexButPlainC() throws Exception {
		tester.runParser(new File("test/berkeley_bench.c"));
	}

	@Test
	public void testOldStyleMethodDecl() throws Exception {
		r = parseAndCheck("int foo(a,b,c)int a;int b,c;{}");
		assertASTContains(r, Function.class);
	}

	@Test
	public void testHacks() throws Exception {
		// should not work but we add it anyway as it is often used
		parseAndCheckInFunction("#if 0\nif(a) {a;b;}else\n#endif\n{}");
	}

	

		
	 @Test
	public void testFullNTFSLib() throws Exception {
		File folder = new File("test/libntfs");
		assert folder.isDirectory();
		parseFilesInFolder(folder, false);
	}

	@Test
	public void testFullNTFSProgs() throws Exception {
		File folder = new File("test/ntfsprogs");
		assert folder.isDirectory();
		parseFilesInFolder(folder, false);
	}

	@Test
	public void testFullBerkeleyDB4420() throws Exception {
		File folder = new File("test/berkeleydb4420");
		assert folder.isDirectory();
		parseFilesInFolder(folder, true);
	}

	private void parseFilesInFolder(File folder, boolean recursive)
			throws Exception {
		for (File file : folder.listFiles()) {
			if (file.isFile()) {
				if (file.isHidden()) {
					System.out.println("skipping " + file);
				} else {
					String name = file.getName();
					int idx = name.lastIndexOf('.');
					if (idx > 0 && (name.substring(idx).equals(".c")/*
																	 * ||
																	 * name.substring(
																	 * idx).equals(".h")
																	 */)) {
						try {
							tester.runParser(file);
						} catch (ParseException e) {
							System.out.println("Attempted to parse " + file);
							System.out.flush();
							e.printStackTrace();
							throw e;
						}
					}
				}
			}
			if (recursive && file.isDirectory())
				parseFilesInFolder(file, recursive);
		}
	}
	
	
}
