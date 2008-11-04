import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;

import tmp.generated_ecmascript.EcmaScriptParser;
import cide.gast.ASTNode;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;

public class ECMAScriptMain {
	public static void main(String args[]) {
		EcmaScriptParser parser = null;
		if (args.length == 0) {
			System.out
					.println("EcmaScript Parser:  Reading from standard input . . .");
			parser = new EcmaScriptParser(new OffsetCharStream(System.in));
		} else if (args.length == 1) {
			System.out.println("EcmaScript Parser:  Reading from file "
					+ args[0] + " . . .");
			try {
				parser = new EcmaScriptParser(new OffsetCharStream(
						new FileInputStream(args[0]), "UTF-8"));
			} catch (java.io.FileNotFoundException e) {
				System.out.println("EcmaScript Parser:  File " + args[0]
						+ " not found.");
				return;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("EcmaScript Parser:  Usage is one of:");
			System.out.println("         EcmaScript < inputfile");
			System.out.println("OR");
			System.out.println("         EcmaScript inputfile");
			return;
		}
		try {
			if (parser != null) {
				ASTNode n = parser.Program();
				
				System.out.println(n.render());
				System.out
						.println("EcmaScript parser:  EcmaScript program parsed successfully.");
			}
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			System.out
					.println("EcmaScript parser:  Encountered errors during parse.");
		}
	}
}
