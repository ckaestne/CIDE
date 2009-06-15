package de.ovgu.cide.export.physical.aspectj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import de.ovgu.cide.features.IFeature;

public class AspectJCompilationUnit {

	private HashMap<TypeDeclaration, ArrayList<ASTNode>> introductions = new HashMap<TypeDeclaration, ArrayList<ASTNode>>();

	private List<AspectJExecutionAdvice> adviceList = new ArrayList<AspectJExecutionAdvice>();

	private String precedenceDeclaration;

	private String aspectName;

	public List imports;

	AspectJCompilationUnit(AST ast, String aspectName) {
		this.aspectName = aspectName;
		imports = new LinkedList();
	}

	public void addExecutionAdvice(AspectJExecutionAdvice member) {
		this.adviceList.add(member);
	}

	public void addIntroduction(TypeDeclaration introTarget, ASTNode member) {
		ArrayList<ASTNode> introsForClass = this.introductions.get(introTarget);
		if (introsForClass == null) {
			introsForClass = new ArrayList<ASTNode>();
			introductions.put(introTarget, introsForClass);
		}
		introsForClass.add(member);
	}

	public String getSource() {
		if (introductions.size() == 0 && adviceList.size() == 0)
			return "";

		StringBuffer result = new StringBuffer();
		if (aspectName != null)
			result.append("// aspect " + aspectName.toString() + ";\n\n");

		AspectJPrettyPrinter printer = new AspectJPrettyPrinter();
		this.accept(printer);

		return result.append(printer.getResult()).toString();
	}

	void accept(AspectJPrettyPrinter visitor) {
		boolean visitChildren = visitor.visit(this);
		if (visitChildren) {

			System.out
					.println("Failure: Pretty printer not supported for Aspects");
			// for (ASTNode node : introductions)
			// node.accept(visitor);
			// for (AspectJExecutionAdvice node : adviceList)
			// node.accept(visitor);
		}
		visitor.endVisit(this);
	}

	public void setAspectName(String name) {
		this.aspectName = name;
	}

	public String getAspectName() {
		return aspectName;
	}

	public List<AspectJExecutionAdvice> getAdviceList() {
		return this.adviceList;
	}

	public HashMap<TypeDeclaration, ArrayList<ASTNode>> introductions() {
		return this.introductions;
	}

	public String getPrecedenceDeclaration() {
		return precedenceDeclaration;
	}

	public void setPrecedenceDeclaration(
			List<Set<IFeature>> newPrecedenceDeclaration,
			Export2AspectJJob exporter) {
		this.precedenceDeclaration = "";
		for (Iterator iter = newPrecedenceDeclaration.iterator(); iter
				.hasNext();) {
			Set<IFeature> derivative = (Set<IFeature>) iter.next();
			precedenceDeclaration = exporter.getDerivativeName(derivative)
					+ ", " + precedenceDeclaration;
		}
		if (precedenceDeclaration.startsWith(", "))
			precedenceDeclaration = precedenceDeclaration.substring(2,
					precedenceDeclaration.length());
		if (precedenceDeclaration.endsWith(", "))
			precedenceDeclaration = precedenceDeclaration.substring(0,
					precedenceDeclaration.length() - 2);
		precedenceDeclaration += ";\n";
		precedenceDeclaration = "declare precedence: " + precedenceDeclaration;

	}

	boolean optimizeAdvice() {
		boolean optimized = false;
		for (Iterator iter1 = adviceList.iterator(); iter1.hasNext();) {
			AspectJExecutionAdvice origin = (AspectJExecutionAdvice) iter1
					.next();
			for (Iterator iter2 = adviceList.iterator(); iter2.hasNext();) {
				AspectJExecutionAdvice candidate = (AspectJExecutionAdvice) iter2
						.next();
				if (origin != candidate) {
					if (origin.isCompatible(candidate)) {
						origin.getPointcut().addAll(candidate.getPointcut());
						adviceList.remove(candidate);
						optimized = true;
						iter1 = adviceList.iterator();
						iter2 = adviceList.iterator();
					}
				}
			}
		}
		return optimized;
	}
}
