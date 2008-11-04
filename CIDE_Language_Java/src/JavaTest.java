import tmp.generated_java15.CompilationUnit;
import tmp.generated_java15.Java15Parser;
import cide.gparser.ParseException;

public class JavaTest {
	public static void main(String[] args) throws ParseException {
		Java15Parser p = new Java15Parser("TestFile.java");
		CompilationUnit u = p.CompilationUnit();
		System.out.println(u.render());
	}
}
