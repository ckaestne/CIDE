package cide.dtdgen;
import java.util.ArrayList;
import java.util.HashMap;

import tmp.generated_dtd.*;
import cide.gast.ASTNode;
import cide.gast.ASTVisitor;

public class InnerContentDeclGenerator extends ASTVisitor {

	final HashMap<Seq, String> knownSeq = new HashMap<Seq, String>();
	final HashMap<Choice, String> knownChoice = new HashMap<Choice, String>();

	final StringBuffer productionsBuffer = new StringBuffer();
	final StringBuffer refBuffer = new StringBuffer();

	String elementName;
	int seqCount = 0;
	int choiceCount = 0;

	@Override
	public boolean visit(ASTNode node) {
		if (node instanceof ElementDecl) {
			elementName = ((ElementDecl) node).getName().getValue();
			seqCount = 0;
			choiceCount = 0;
		}
		if (node instanceof Seq) {
			getName((Seq) node);
		}
		if (node instanceof Choice) {
			getName((Choice) node);
		}

		return super.visit(node);
	}

	@Override
	public void postVisit(ASTNode node) {
		if (node instanceof Seq) {
			String name = getName((Seq) node);
			productionsBuffer.append(name + ":");

			ArrayList<Cp> cps = ((Seq) node).getCp();
			for (Cp cp : cps) {
				productionsBuffer.append(" ");
				printCp(cp);
			}

			productionsBuffer.append(";\n");

		}
		if (node instanceof Choice) {
			String name = getName((Choice) node);
			productionsBuffer.append(name + ": LL(2) ");
			boolean first = true;

			ArrayList<Cp> cps = ((Choice) node).getCp();
			for (Cp cp : cps) {
				if (!first)
					productionsBuffer.append(" | LL(2) ");
				first = false;
				printCp(cp);
			}

			productionsBuffer.append(";\n");

		}
		if (node instanceof ContentSpec2) {
			refBuffer.append("Content_Any");
		}
		if (node instanceof ContentSpec3) {
			String name = getName(((ContentSpec3) node).getChildren()
					.getSeqOrChoice());
			Modifier m = ((ContentSpec3) node).getChildren().getModifier();

			refBuffer.append(applyModifier(m, name));
		}
	}

	private void printCp(Cp cp) {
		String name;
		Modifier modifier;
		if (cp instanceof Cp1) {
			name="<PCDATA>";
			modifier=null;
		}else
		if (cp instanceof Cp2) {
			name = "Element_" + ((Cp2) cp).getName().getValue();
			modifier = ((Cp2) cp).getModifier();
		} else {
			name = getName(((Cp3) cp).getSeqOrChoice());
			modifier = ((Cp3) cp).getModifier1();
		}

		productionsBuffer.append(applyModifier(modifier, name));
	}

	private String applyModifier(Modifier modifier, String name) {
		if (modifier instanceof Modifier1)
			return ("( LL(2) " + name + " )*");
		else if (modifier instanceof Modifier2)
			return ("( LL(2) " + name + " )+");
		else if (modifier instanceof Modifier3)
			return ("[ LL(2) " + name + " ]");
		else
			return (name);
	}

	private String getName(SeqOrChoice seqOrChoice) {
		if (seqOrChoice instanceof SeqOrChoice1)
			return getName(((SeqOrChoice1) seqOrChoice).getChoice());
		if (seqOrChoice instanceof SeqOrChoice2)
			return getName(((SeqOrChoice2) seqOrChoice).getSeq());
		return null;
	}

	private String getName(Seq node) {
		String name = knownSeq.get(node);
		if (name != null)
			return name;
		seqCount++;
		name = "Content_" + elementName + "_Seq" + seqCount;
		knownSeq.put(node, name);
		return name;
	}

	private String getName(Choice node) {
		String name = knownChoice.get(node);
		if (name != null)
			return name;
		choiceCount++;
		name = "Content_" + elementName + "_Choice" + choiceCount;
		knownChoice.put(node, name);
		return name;
	}
}
