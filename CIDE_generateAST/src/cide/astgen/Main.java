/**
    Copyright 2010 Christian Kästner

    This file is part of CIDE.

    CIDE is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.

    CIDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CIDE.  If not, see <http://www.gnu.org/licenses/>.

    See http://www.fosd.de/cide/ for further information.
*/

package cide.astgen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;

import cide.astgen.nparser.ast.NGrammar;
import cide.astgen.nparser.parser.ParseException;
import cide.astgen.nparser.parser.SlimJJParser;
import cide.astgen.nparser.visitor.ASTCreationVisitor;
import cide.astgen.nparser.visitor.CreateSimplePrintVisitorVisitor;
import cide.astgen.nparser.visitor.JavaCCPrintVisitor;
import cide.astgen.nparser.visitor.SlimPrintVisitor;

public class Main {

	private String grammarName;

	public Main(String name) {
		grammarName = name;
	}

	public static void main(String[] args) throws Exception {
		new Main(args[0]).run();

	}

	private void run() throws Exception {
		try {
			runGenerator(grammarName + ".gcide", new File("generated_"
					+ grammarName + "/"), "tmp.generated_" + grammarName);
		} catch (cide.astgen.nparser.parser.ParseException e) {
			e.printStackTrace();
			System.err.flush();
			System.out.println("Line " + e.currentToken.beginLine);
		}
	}

	public void runGenerator(String grammarFileName, File targetDirectory,
			String targetPackage) throws FileNotFoundException, ParseException {
		FileReader reader = new FileReader(grammarFileName);
		System.out.println("Reading " + grammarFileName);
		SlimJJParser parser = new SlimJJParser(reader);
		NGrammar grammar = parser.Grammar();
		System.out.println("Number of Productions for \""+grammarFileName+"\": "+grammar.productions.size());
		runProductions(grammar, targetDirectory, targetPackage);
	}

	private void runProductions(NGrammar grammar, File targetDirectory,
			String targetPackage) throws FileNotFoundException {
		// for (NormalProduction production : bnfproductions) {
		// if (containsNestedChoice(production)) {
		// printProduction(production);
		// throw new NestedChoiceException();
		// }
		//
		// production.productionAnnotation.collectProduction();
		//
		// // printClasses(production);
		// }
		// NGrammar grammar = new NGrammarBuilder().build(bnfproductions);
		PrintStream jjout = new PrintStream(new File(targetDirectory,new File(grammarName + ".jj").getName()));
		grammar.accept(new SlimPrintVisitor(System.out));
		grammar.accept(new JavaCCPrintVisitor(jjout));

		targetDirectory.mkdir();
		grammar.accept(new ASTCreationVisitor(targetDirectory, targetPackage));
		grammar.accept(new CreateSimplePrintVisitorVisitor(targetDirectory,
				targetPackage));
//		grammar.accept(new CreateReferenceManagerVisitor(targetDirectory,
//				targetPackage));
		System.out.println("done.");
	}

	private void printIndented(String string, int indent) {
		for (int i = 0; i < indent; i++)
			System.out.print("  ");
		System.out.println(string);

	}

}
