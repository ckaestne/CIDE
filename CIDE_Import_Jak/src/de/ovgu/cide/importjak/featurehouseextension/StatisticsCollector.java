package de.ovgu.cide.importjak.featurehouseextension;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import de.ovgu.cide.fstgen.ast.FSTNonTerminal;
import de.ovgu.cide.fstgen.ast.FSTTerminal;
import de.ovgu.cide.fstgen.ast.FSTVisitor;

public class StatisticsCollector {

	private Set<String> classNames = new HashSet<String>();
	private int classCount = 0;
	private int methodCount = 0;
	private int methodRefinements = 0;

	public void collectStatistics(LinkedList<FSTNonTerminal> features) {
		for (FSTNonTerminal feature : features) {
			feature.accept(new FSTVisitor() {
				@Override
				public boolean visit(FSTNonTerminal nonTerminal) {
					if (nonTerminal.getType().equals("ClassDeclaration")) {
						classCount++;
						classNames.add(nonTerminal.getName());
					}

					return super.visit(nonTerminal);
				}

				@Override
				public boolean visit(FSTTerminal terminal) {
					if (terminal.getType().equals("MethodDecl")) {
						methodCount++;
						if (terminal.getBody().matches(".*\\s*original\\s*.*"))
							methodRefinements++;
					}
					return super.visit(terminal);
				}
			});
		}

		System.out.println("Statistics: \n\tfeatures=" + features.size()
				+ "\n\tclass introductions=" + classNames.size()
				+ "\n\tclass refinements=" + (classCount - classNames.size())
				+ "\n\tmethod introductions="
				+ (methodCount - methodRefinements) + "\n\tmethod refinements="
				+ methodRefinements);

	}

}
