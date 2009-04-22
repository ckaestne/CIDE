package de.ovgu.cide.importjak.featurehouseextension;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.StringTokenizer;

import tmp.generated_jak.JakParser;
import builder.ArtifactBuilder;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import de.ovgu.cide.fstgen.ast.FSTNonTerminal;

public class JakBuilder extends ArtifactBuilder {
	public JakBuilder() {
		super(new String[] { ".java", ".jak" });
	}

	public void processNode(FSTNonTerminal parent, StringTokenizer st,
			File inputFile) throws FileNotFoundException {
		FSTNonTerminal rootDocument = new FSTNonTerminal("Java-File", st
				.nextToken());
		parent.addChild(rootDocument);
		JakParser p = new JakParser(new OffsetCharStream(
				new FileInputStream(inputFile)));
		try {
			p.CompilationUnit(false);
			rootDocument.addChild(p.getRoot());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
