package de.ovgu.cide.language.haskell;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import tmp.generated_haskell.HaskellParser;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.gparser.Token;

public class ParserPatternTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testPattern() throws ParseException {
		parse("1", "patr");
		parse("_", "patr");
		parse("- 4", "patr");
		parse("~ - 1", "patr");
		parse("ll@ - 1", "patr");
		parse("ll@xx@ - 1", "patr");
		parse("ll@ ~xx@ - 1", "patr");
		parse("varName", "patr");
		parse("var@Mod.varName", "patr");
		parse("ConName", "patr");
		parse("Mod.ConName", "patr");
		parse("var@ConName", "patr");
		// parse("(varName, X, `%%`)", "patr");
		// parse("[varName, X, `%%`, (a,b)]", "patr");
		parse("(varName, X, aa)", "patr");
		parse("(# varName, X, aa #)", "patr");//extension: unboxed tuple
		parse("[varName, X, aa, (a,b)]", "patr");
		parse("Mod.ConName{v=X,v=(a,b)}", "patr");
		parse("ConName :! x := a", "patr");
		parse("X Y Z", "patr");
		parse("X v ~Z (a, x@b)", "patr");
		parse("v+2", "patr");
		parse("[v+2,~(a+1,x+100)]", "patr");
		notParse("x ->", "patr");
		notParse("x -> 3+3", "patr");
	}

	@Test
	public void testLowend() throws ParseException {
		parse(":", "gconsym");
		parse(":%%", "gconsym");
		parse("Mod. :%%", "qconsym");
		parse("a", "qvarid");
		parse("Mod.a", "qvarid");
		parse(":%", "qconop");
		parse("`Xx`", "qconop");
		parse("`Mod.Xx`", "qconop");
		parse("%%", "qvarop");
		parse("`xx`", "qvarop");
		parse("`Mod.xx`", "qvarop");
		parse("%%", "op");
		notParse("Mod.%%", "op");
	}

	@Test
	public void testVar() throws ParseException {
		parse("varName", "var");
		parse("Module.varName", "var");
		parse("(%)", "var");
		parse("(.|.)", "var");
		parse("(.)", "var");
		parse("fork#","var");//extension, see armins email from 2.jul.2007
	}

	@Test
	public void testNaam() throws ParseException {
		parse("ConName", "naam");
		parse("Module.ConName", "naam");
		parse("Mod1.Mod2.Mod3.ConName", "naam");
		parse("`X.Y`", "qconop");
		// parse("(Module.::)", "naam");
		// parse("Module.`Mod.Mod2. (Mod3.Abc)`", "naam");
		// parse("Module.`Mod.Mod2. (Mod3.Abc.`Mod5. :=`)`", "naam");
		// notParse("Module.`Mod. %!`", "naam");
	}

	@Test
	public void testType() throws ParseException {
		parse("ConName", "type");
		parse("vonName", "type");
		parse("()", "type");
		parse("(())", "type");
		parse("(A)", "type");
		parse("(A A)", "type");
		parse("(A, A)", "type");
		parse("(# A, A #)", "type");//extension: unboxed tuple
		parse("(A a, B b)", "type");
		parse("[a]", "type");
		parse("[a a]", "type");
		parse("[a a -> b -> c d]", "type");
		notParse("[vonName, Con var]", "type");
		parse("(vonName, Con, var)", "type");
		parse("(a->b->c)", "type");
		parse("(a->(vonName, Con, var),b)", "type");
		parse("((vonName, Con, var)->(b->[C->X]))", "type");
		parse("((vonName  Con, var) (b->[C->X]))", "type");
		parse("((vonName  Con, var), (b->[C->X]))", "type");
		parse("(a->(b))", "type");
		parse("()", "functiontype");
		parse("X ()", "functiontype");
		parse("a->b->c", "functiontype");
		parse("IO (Handle, SockAddr)","paramtype");
		parse("Socket -> IO (Handle, SockAddr)","functiontype");
		
	}

	@Test
	public void testKlasse() throws ParseException {
		parse("ConName b", "klasse");
		// parse("Mod.VonName Mod2.b", "klasse");
		parse("Mod.VonName b", "klasse");
		parse("X (b (vonName, Con, var))", "klasse");
		parse("X (b (a->b) x C)", "klasse");
	}

	@Test
	public void testContext() throws ParseException {
		parse("ConName b =>", "context");
		// parse("Mod.VonName Mod2.b=>", "context");
		parse("Mod.VonName b=>", "context");
		parse("X (b (vonName, Con, var))=>", "context");
		parse("X (b (a->b) x C)=>", "context");
		parse("(X (b (a->b) x C),X b, A c)=>", "context");
	}

	@Test
	public void testExpr() throws ParseException {
		parse("1", "expr");
		parse("X", "expr");
		parse("x", "expr");
		parse("1 x X", "expr");
		parse("-X 1", "expr");
		parse("\\ X Y -> x", "expr");
		parse("x + y :% z", "expr");
		parse("- x + y :% z", "expr");
		parse("if - x + y then z else 3+3", "expr");
		parse("case 3+3 of { x -> 3+3 }", "expr");
		parse("case 3+3 of { x | y-> 3+3 }", "expr");
		parse("case 3+3 of { x | y-> 3+3 | z->a }", "expr");
		parse("case 3+3 of { x  | y-> 3+3 | z->a where{}; x->x where {} }",
				"expr");
		parse("case 3+3 of { x -> 3+3; y->z; z->2 }", "expr");
		parse("x :: v", "expr");
		parse("x :: (V->x)", "expr");
		parse("x :: Klasse var => typ", "expr");
		parse("x :: (Klasse (var typ typ), Klasse typevar)=> (typ->Typ)",
				"expr");

		parse("let {} in 1+1", "expr");
		parse("let " + decls + " in 1+3", "expr");
		parse("do { let {}; 1<-3;3+3}", "expr");
		parse("[1]", "expr");
		parse("[(0,0,0,0)]", "expr");
		parse("not . null . (gsel (\\ c -> (node' c `elem` suc' c)))", "expr");
		parse("(.) . (.)", "expr");
		
		parse("I.keys A","expr");
		parse("(I.filter fromNode)","expr");
		parse("(-1 ==)","expressie");
		parse("I.keys (I.filter (-1 ==) fromNode)","expr");

	}

	@Test
	public void testExpressie() throws ParseException {
		parse("1", "expressie");
		parse("X", "expressie");
		parse("x", "expressie");
		parse("Naam{var=3+3}", "expressie");
		parse("var{var=3+3,var2=22}", "expressie");
		parse("(3,4,a+2)", "expressie");
		parse("(#3,4,a+2#)", "expressie");//extension: unboxed tuple
		parse("()", "expressie");
		parse("(3+3+)", "expressie");
		parse("(+3+3)", "expressie");
		parse("[]", "expressie");
		parse("[3+3 ..]", "expressie");
		parse("[3+3 .. 5]", "expressie");
		parse("[3+3, 3 ..]", "expressie");
		parse("[3+3, 3 .. 5+x]", "expressie");
		parse("[1,2,3,4,5]", "expressie");
		parse("[1|X v ~Z (a, x@b) <- 3+3]", "expressie");
		parse("[1|3]", "expressie");
		parse("[1|3,4,5,6]", "expressie");
		parse("[1|a<-3, 3, X v ~Z (a, x@b) <- 3+3]", "expressie");
		parse("[1|let " + decls + ", 1+1]", "expressie");
	}

	@Test
	public void testFunction() throws ParseException {
		parse("var patr", "function");
		parse("var p X v ~Z (a, x@b)", "function");
		parse("(var p) p X v ~Z (a, x@b)", "function");
		parse("p+x", "function");
		parse("p+1", "function");
	}

	private static String decls = "{var::X;var patr = 3+3;var patr | 3+3 = 3+3}";

	@Test
	public void testDeclaration() throws ParseException {
		parse("var patr = 3+3", "declaration");
		parse("var patr | 3+3 = 3+3", "declaration");
		parse("var patr | 3+3 = 3+3|a=1+1", "declaration");
		parse("[v+2,~(a+1,x+100)] = x", "declaration");
		parse("[v+2,~(a+1,x+100)] | a= x", "declaration");
		parse("var patr = 3+3 where {}", "declaration");
		parse("[v+2,~(a+1,x+100)] | a= x where {var::X}", "declaration");
		parse("var, var, var::Naam var=>(x->Z)", "declaration");
		parse("var::(x->Z)", "declaration");
		parse("infix 3 $, $", "declaration");
		parse("var :: X ()", "declaration");
	}

	@Test
	public void testDecls() throws ParseException {
		parse(
				"{var patr = 3+3;var patr | 3+3 = 3+3;[v+2,~(a+1,x+100)] = x;var::(x->Z);infix 3 $, $}",
				"decls");
	}

	@Test
	public void testDefinition() throws ParseException {
		parse("type TypeNaam var var var = (x->y)", "definition");
		parse("data TypeNaam var = (x->y) $ (x->y) | X", "definition");
		parse("data X v=> TypeNaam var = (x->y) $ (x->y) | X", "definition");
		parse("data TypeNaam var = Y|x$x|X|Y { v, v:: X, x::x->X}",
				"definition");
		parse("data TypeNaam = X deriving X", "definition");
		parse("data TypeNaam = X deriving (X, Y, Z)", "definition");
		parse("newtype TypeNaam var var = X (x->z)", "definition");
		parse("newtype TypeNaam var var = X x", "definition");
		parse("newtype TypeNaam var var = X {v::x->z}", "definition");
		parse("class X v", "definition");
		parse("class X v where {}", "definition");
		parse("instance X X", "definition");
		parse("instance X v=>X X where {}", "definition");
		parse("instance X [v]", "definition");
		parse("instance X ()", "definition");
		parse("instance X (X)", "definition");
		parse("instance X (X v)", "definition");
		parse("instance X (v -> v)", "definition");
		parse("instance X (v , v, v)", "definition");
		parse("default (x->y)", "definition");
		parse("default (x->y, X)", "definition");
		parse("var patr = 3+3", "definition");
	}

	@Test
	public void testExport() throws ParseException {
		parse("var", "export");
		parse("Naam", "export");
		parse("Naam ( ..)", "export");
		parse("Naam()", "export");
		parse("Naam (X)", "export");
		parse("Naam (X, Y)", "export");
		parse("Naam (X, x, X, x, X)", "export");
		parse("Naam (x, x)", "export");
		parse("module X", "export");
	}

	@Test
	public void testModule() throws ParseException {
		parse("{}", "module");
		parse("module X where {}", "module");
		parse("module X  () where {}", "module");
		parse("module X  (var) where {}", "module");
		parse("module X  (Naam (X, x), v, module X) where {}", "module");
		parse("{import X}", "module");
		parse("{import qualified X as Y}", "module");
		parse("{import X hiding (v, N())}", "module");
		parse("{import X ()}", "module");
		parse("{import X; import Y; import Z}", "module");
		parse("{default (x->y);default (x->y)}", "module");
		parse("{import X ();import Y;default (x->y)}", "module");
		notParse("{import Y;default (x->y);import X ()}", "module");
	}
	
	@Test
	public void testInst() throws ParseException {
		//test to ensure we do not loose expressiveness compared to H98 standard
		parse("X X","inst");
		parse("X (X)","inst");
		parse("X (X y z)","inst");
		parse("X ()","inst");
		parse("X (v->v)","inst");
		parse("X (v,v,v)","inst");
		parse("X [v]","inst");
	}
	
	@Test
	public void testConstr() throws ParseException {
		//test to ensure we do not loose expressiveness compared to H98 standard
		parse("X","constr");
		parse("X (x x)","constr");
		parse("X () () X","constr");
		parse("X { x::T}","constr");
		parse("X {x,y::T}","constr");
		parse("X {x::T,y::T}","constr");
		parse("X {x,y::T,z::T,a,b::T}","constr");
		parse("NodeMap{map :: FiniteMap a Node, key :: Int}","constr");
		
	}
	

	@Test
	public void testRealExamples() throws ParseException {
		// real examples
		parse(
				"mapResult :: (a -> Result b err) -> Result a err -> Result b err",
				"definition");
		parse(
				"	  liftResult (Result x : rest)		    = case liftResult rest of		          { Result xs -> Result (x : xs);		            Fail e -> Fail e}",
				"declaration");
		parse(" (GraphM m gr) =>","context");
		parse(" var:: (GraphM m gr) => Int -> m (gr () ())","declaration");
		
		parse("instance GraphM IO SGr","definition");
		parse("Show (IO (SGr a b)) ","inst");
		parse("instance (Show a, Show b) => Show (IO (SGr a b)) ","definition");
		
		parse("fst $ mkNodes m asx","expr");
		parse(" delNodes (v : vs) g = delNodes vs (snd (match v g))","declaration");
		parse("mkNodes_ m asx = fst $ mkNodes m asx","declaration");
		parse("(.:) = (.) . (.)","declaration");
		parse("accept :: Socket -> IO (Handle, SockAddr)","declaration");
		parse(    "       case (fork# action s) of\n"+
			    "           { (# s1, id #) -> (# s1, ThreadId id #) }","expr");
		parse("forkIOIgnoreExceptions action \n"+
		    " = IO $\n"+
		    "     \\ s ->\n"+
		    "       case (fork# action s) of\n"+
		    "           { (# s1, id #) -> (# s1, ThreadId id #) }","declaration");

	}

	private void notParse(String code, String production) throws ParseException {
		boolean fail = false;
		try {
			parseInternal(code, production);
		} catch (ParseException e) {
			fail = true;
		} catch (AssertionError e) {
			fail = true;
		}
		Assert.assertTrue(fail);
	}

	private void printTokens(String string) {
		printTokens(newParser(string));
	}

	private void parse(String code, String production) throws ParseException {
		try {
			parseInternal(code, production);
		} catch (RuntimeException r) {
			System.out.println("-----------------------");
			System.out.println("Parsing: " + code);
			printTokens(code);
			throw r;

		} catch (Error r) {
			System.out.println("-----------------------");
			System.out.println("Parsing: " + code);
			printTokens(code);
			throw r;

		} catch (ParseException r) {
			System.out.println("-----------------------");
			System.out.println("Parsing: " + code);
			printTokens(code);
			throw r;
		}
	}

	private void parseInternal(String code, String production)
			throws ParseException {
		HaskellParser p = newParser(code);
		try {
			Method m = p.getClass().getMethod(production, null);
			m.invoke(p, null);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw (ParseException) e.getTargetException();
		}
		Token t = p.getToken(1);
		Assert.assertTrue("Did not reach EOF, but " + t.image, t.kind == 0);
	}

	private void printTokens(HaskellParser parser) {
		System.out.println("//////////////////");
		int i = 1;
		Token t = parser.getToken(i);
		while (t.kind != 0) {
			System.out.println(t.image + " " + t.kind + " ("
					+ parser.tokenImage[t.kind] + ")");
			t = parser.getToken(++i);
		}
	}

	protected HaskellParser newParser(String string) {
		ByteArrayInputStream a = new ByteArrayInputStream(string.getBytes());
		InputStreamReader b = new InputStreamReader(a);
		HaskellParser p = new HaskellParser(new OffsetCharStream(b));
		return p;
	}

}
