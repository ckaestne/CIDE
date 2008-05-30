import cide.gparser.ParseException;
import tmp.generated_java15.CompilationUnit;
import tmp.generated_java15.Java15Parser;
import tmp.generated_java15.SimplePrintVisitor;

public class JavaTest {
	public static void main(String[] args) throws ParseException {
		Java15Parser p = new Java15Parser("TestFile.java");
		CompilationUnit u = p.CompilationUnit();
		u.accept(new SimplePrintVisitor(System.out));
	}

	void foo(int i, int j, int k) throws X, Y, Z {
	}
}
