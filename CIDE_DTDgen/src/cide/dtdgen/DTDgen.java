package cide.dtdgen;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import tmp.generated_dtd.DTD;
import tmp.generated_dtd.DTDParser;
import tmp.generated_dtd.ElementDecl;
import cide.gast.ASTNode;
import cide.gast.ASTVisitor;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;

public class DTDgen {

	private String dtdInput;
	private File gcideOutputFile;
	private String name;
	private DTD dtd;

	public static void main(String[] args) {
		try {
			new DTDgen(new File(args[0]), new File(args[1])).run();

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public DTDgen(String name, File dtdInputFile, File gcideOutputFile) {
		this(name, getContents(dtdInputFile), gcideOutputFile);
	}

	public DTDgen(String name, String dtdInput, File gcideOutputFile) {
		this.dtdInput = dtdInput;
		this.gcideOutputFile = gcideOutputFile;
		this.name = name;
	}

	public DTDgen(File dtdInputFile, File gcideOutputFile) {
		this(getNameFromFile(gcideOutputFile), dtdInputFile, gcideOutputFile);
	}

	private static String getNameFromFile(File gcideOutputFile) {
		String name = gcideOutputFile.getName();
		name = name.substring(0, name.indexOf('.'));
		return name;
	}

	public void run() throws ParseException, IOException {
		parse();

		String content = generateGcideContent();

		FileWriter writer = new FileWriter(gcideOutputFile);
		writer.write(content);
		writer.close();
	}

	private String generateGcideContent() throws FileNotFoundException,
			ParseException {
		String lexDefs = getLexDefs();
		String productions = getProductions();
		String lname = name.toLowerCase();
		String uname = Character.toUpperCase(name.charAt(0))
				+ name.substring(1);

		InputStream res = DTDgen.class
				.getResourceAsStream("parserTemplate.gcide");
		String template = getContents(res);

		String content = template.replaceAll("\\$\\{Name\\}", uname)
				.replaceAll("\\$\\{name\\}", lname).replaceAll(
						"\\$\\{ProductionDef\\}", productions).replaceAll(
						"\\$\\{LexDefs\\}", lexDefs);
		return content;
	}

	void parse() throws ParseException {
		DTDParser dtdparser = new DTDParser(new OffsetCharStream(
				new ByteArrayInputStream(dtdInput.getBytes())));
		dtd = dtdparser.DTD();
	}

	String getProductions() {
		final StringBuffer buffer = new StringBuffer();
		final List<String> allProductions = new ArrayList<String>();
		dtd.accept(new ASTVisitor() {
			boolean first = true;

			@Override
			public boolean visit(ASTNode node) {
				if (node instanceof ElementDecl) {
					ElementDecl ed = (ElementDecl) node;
					String name = ed.getName().getValue();
					if (first) {
						rootProduction(name);
						first = false;
					}
					allProductions.add(name);

					String innerContentRef = getInnerContentRef(ed);
					String innerContentDecl = getInnerContentDecl(ed);

					buffer.append("Element_" + name + ":\n");
					buffer.append("\t  LL(\"EmptyTag_" + name
							+ "()\") EmptyTag_" + name + " (CMisc)*\n\t| STag_"
							+ name + " (CMisc)* " + innerContentRef + "  ETag_"
							+ name + " (CMisc)*;\n");
					buffer.append("EmptyTag_" + name + ": \"<\" \"!<E_" + name
							+ ">" + name
							+ "\" (Attribute)* \"!<SLASHEND>/>\";\n");
					buffer.append("STag_" + name + " : \"<\" \"!<E_" + name
							+ ">" + name
							+ "\" (Attribute)* \"!<ELEMENT_END>>\";\n");
					buffer.append("ETag_" + name + ": \"</\" \"!<E_" + name
							+ ">" + name + "\"<NONE> \"!<ELEMENT_END>>\";\n");
					buffer.append(innerContentDecl);
					buffer.append("\n\n");
					// Content_"+name+"1: Element_person | Comment | Whitespace;

				}
				return super.visit(node);
			}

			private void rootProduction(String name) {
				buffer.append("RootElement: Element_" + name + ";\n\n");
			}
		});

		buffer.append("Content_Any: ");
		for (int i = 0; i < allProductions.size(); i++) {
			if (i != 0)
				buffer.append(" | ");
			buffer.append("LL(2) Element_" + allProductions.get(i));
		}
		buffer.append(";\n");

		return buffer.toString();
	}

	private static WeakHashMap<ElementDecl, InnerContentDeclGenerator> cache = new WeakHashMap<ElementDecl, InnerContentDeclGenerator>();

	static String getInnerContentDecl(ElementDecl ed) {

		InnerContentDeclGenerator d = cache.get(ed);
		if (d == null) {
			d = new InnerContentDeclGenerator();
			ed.accept(d);
			cache.put(ed, d);
		}
		return d.productionsBuffer.toString();
	}

	static String getInnerContentRef(ElementDecl ed) {
		InnerContentDeclGenerator d = cache.get(ed);
		if (d == null) {
			d = new InnerContentDeclGenerator();
			ed.accept(d);
			cache.put(ed, d);
		}
		return d.refBuffer.toString();
	}

	String getLexDefs() {
		final StringBuffer buffer = new StringBuffer();
		dtd.accept(new ASTVisitor() {
			@Override
			public boolean visit(ASTNode node) {
				if (node instanceof ElementDecl) {
					ElementDecl ed = (ElementDecl) node;
					String name = ed.getName().getValue();
					if (buffer.length() > 0)
						buffer.append("|");
					buffer.append(" <E_" + name + ": \"" + name
							+ "\" > : LexElement_Inside\n");
				}
				return super.visit(node);
			}
		});

		return buffer.toString();
	}

	/**
	 * Fetch the entire contents of a text file, and return it in a String. This
	 * style of implementation does not throw Exceptions to the caller.
	 * 
	 * @param aFile
	 *            is a file which already exists and can be read.
	 */
	static public String getContents(File aFile) {
		// ...checks on aFile are elided
		StringBuffer contents = new StringBuffer();

		// declared here only to make visible to finally clause
		BufferedReader input = null;
		try {
			// use buffering, reading one line at a time
			// FileReader always assumes default encoding is OK!
			input = new BufferedReader(new FileReader(aFile));
			String line = null; // not declared within while loop
			/*
			 * readLine is a bit quirky : it returns the content of a line MINUS
			 * the newline. it returns null only for the END of the stream. it
			 * returns an empty String if two newlines appear in a row.
			 */
			while ((line = input.readLine()) != null) {
				contents.append(line);
				contents.append(System.getProperty("line.separator"));
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (input != null) {
					// flush and close both "input" and its underlying
					// FileReader
					input.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return contents.toString();
	}

	static public String getContents(InputStream stream) {
		// ...checks on aFile are elided
		StringBuffer contents = new StringBuffer();

		// declared here only to make visible to finally clause
		BufferedReader input = null;
		try {
			// use buffering, reading one line at a time
			// FileReader always assumes default encoding is OK!
			input = new BufferedReader(new InputStreamReader(stream));
			String line = null; // not declared within while loop
			/*
			 * readLine is a bit quirky : it returns the content of a line MINUS
			 * the newline. it returns null only for the END of the stream. it
			 * returns an empty String if two newlines appear in a row.
			 */
			while ((line = input.readLine()) != null) {
				contents.append(line);
				contents.append(System.getProperty("line.separator"));
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (input != null) {
					// flush and close both "input" and its underlying
					// FileReader
					input.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return contents.toString();
	}
}
