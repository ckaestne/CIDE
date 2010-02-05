import java.io.ByteArrayInputStream;

import junit.framework.TestCase;
import tmp.generated_java15.Java15Parser;
import cide.gparser.ParseException;


public class ParserTests extends TestCase {

	protected void setUp() throws Exception {
	}
	
	public void testExpression() throws ParseException{
		parse("a+b").Expression();
		parse("a==b").Expression();
		parse("a<b").Expression();
		parse("a>b").RelationalExpression();
	}
	
	public void testUnicode() throws ParseException{
		parse("if (x == '\uFFFF') {}").Statement();
	}
	
	private Java15Parser parse(String x) {
		Java15Parser p = new Java15Parser( new ByteArrayInputStream(x.getBytes()));
		return p;
	}

}
