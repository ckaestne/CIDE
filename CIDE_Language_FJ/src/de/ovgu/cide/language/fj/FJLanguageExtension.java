package de.ovgu.cide.language.fj;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import tmp.generated_fj.FJParser;
import cide.gast.IASTNode;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.languages.ExtendedLanguageExtension;
import cide.languages.ILanguageParser;

public class FJLanguageExtension extends ExtendedLanguageExtension<FJParser> {

	private static final String[] EXTENSIONS = new String[] { ".fj" };
	private static final String[] NODES_FOR_ALTERNATIVES = new String[] { 
		/*"TypeDeclaration", "VarDeclaration", "MethodDeclaration", "FormalParameter", "Type", "Type1", "Type2",*/ "MethodInvoke", "FieldInvoke",
		"AllocationExpression", "CastExpression"
	};
	
	public static final String LANGUAGE_EXTENSION_ID = "de.ovgu.cide.language.fj";

	public String[] getFileExtensions() {
		return EXTENSIONS;
	}

	public ILanguageParser getParser(final InputStream inputStream,
			String filename) {
		return new ILanguageParser() {
			public ISourceFile getRoot() throws ParseException {
				return new FJParser(new OffsetCharStream(inputStream)).Goal();
			}
		};
	}

	public String getId() {
		return LANGUAGE_EXTENSION_ID;
	}
	
	@Override
	public boolean canCreateAlternatives(List<IASTNode> nodes) {
		if ((nodes == null) || nodes.isEmpty())
			return false;
		
		for (IASTNode node : nodes) {
			String simpleName = node.getClass().getSimpleName();
			boolean simpleNameFound = false;
			
			for (int i = 0; i < NODES_FOR_ALTERNATIVES.length; ++i) {
				if (NODES_FOR_ALTERNATIVES[i].equals(simpleName)) {
					simpleNameFound = true;
					break;
				}
			}
			
			if (!simpleNameFound)
				return false;
			
//			// Die AST-Knoten vom Typ Type, Type1 oder Type2 sind nur dann erlaubt, falls sie der Rückgabetyp einer Methode sind.
//			if (simpleName.equals("Type1") || simpleName.equals("Type2")) {
//				if (!(node.getParent().getParent() instanceof MethodDeclaration))
//					return false;
//			}
//			if (simpleName.equals("Type")) {
//				if (!(node.getParent() instanceof MethodDeclaration))
//					return false;
//			}
		}
		
		return true;
	}

	@Override
	protected FJParser getInternalParser(String code) {
		if (code == null)
			return null;
		return new FJParser(new ByteArrayInputStream(code.getBytes()));
	}
}
