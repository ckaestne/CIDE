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

package de.ovgu.cide.importjak.featurehouseextension;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.prop4j.Equals;
import org.prop4j.Implies;
import org.prop4j.Literal;
import org.prop4j.Node;
import org.prop4j.Or;

import printer.FeaturePrintVisitor;
import printer.PrintVisitorException;
import printer.PrintVisitorInterface;
import builder.ArtifactBuilderInterface;

import composer.CmdLineInterpreter;
import composer.rules.CompositionError;
import composer.rules.FieldOverriding;
import composer.rules.ImplementsListMerging;
import composer.rules.ModifierListSpecialization;
import composer.rules.Replacement;
import composer.rules.StringConcatenation;

import de.ovgu.cide.fstgen.ast.FSTNode;
import de.ovgu.cide.fstgen.ast.FSTNonTerminal;
import de.ovgu.cide.fstgen.ast.FSTTerminal;
import de.ovgu.cide.fstgen.ast.FSTVisitor;
import featureide.fm.core.Feature;
import featureide.fm.core.FeatureModel;
import featureide.fm.core.editing.NodeCreator;

public class FeatureFSTGenComposer {

	private CmdLineInterpreter cmd = new CmdLineInterpreter();

	private GuidslFileLoader fileLoader = new GuidslFileLoader(this);

	private FeaturePrintVisitor featureVisitor = new FeaturePrintVisitor();

	private ArrayList<FSTNode> fstnodes;

	public void setFstnodes(ArrayList<FSTNode> fstnodes) {
		this.fstnodes = fstnodes;
	}

	public ArrayList<FSTNode> getFstnodes() {
		return fstnodes;
	}

	public FeatureFSTGenComposer() {
		registerArtifactBuilder(new JakBuilder());
		registerPrintVisitor(new FeatureJavaPrintVisitor());
	}

	public void registerArtifactBuilder(ArtifactBuilderInterface builder) {
		fileLoader.registerArtifactBuilder(builder);
	}

	public void unregisterArtifactBuilder(ArtifactBuilderInterface builder) {
		fileLoader.unregisterArtifactBuilder(builder);
	}

	public LinkedList<ArtifactBuilderInterface> getArtifactBuilders() {
		return fileLoader.getArtifactBuilders();
	}

	public void registerPrintVisitor(PrintVisitorInterface visitor) {
		this.featureVisitor.registerPrintVisitor(visitor);
	}

	public void unregisterPrintVisitor(PrintVisitorInterface visitor) {
		this.featureVisitor.unregisterPrintVisitor(visitor);
	}

	public LinkedList<PrintVisitorInterface> getPrintVisitors() {
		return featureVisitor.getPrintVisitors();
	}

	public void run(String[] args, IProgressMonitor monitor) {
		cmd.parseCmdLineArguments(args);
		try {
			if (monitor != null)
				monitor.subTask("Loading feature model");
			try {
				fileLoader.loadFiles(cmd.equationFileName,
						cmd.equationBaseDirectoryName, cmd.isAheadEquationFile,
						monitor);
			} catch (cide.gparser.ParseException e1) {
				System.out.println("error");
				// fireParseErrorOccured(e1);
				e1.printStackTrace();
			}
			String outputDir = cmd.equationBaseDirectoryName;
			if (cmd.outputDirectoryName != null)
				outputDir = cmd.outputDirectoryName;

			featureVisitor.setWorkingDir(outputDir);
			featureVisitor.setExpressionName(cmd.equationFileName);

			for (ArtifactBuilderInterface builder : getArtifactBuilders()) {
				LinkedList<FSTNonTerminal> features = builder.getFeatures();
				initializeOriginalFeatures(features);
				prepareAHEADCode(features);
				new StatisticsCollector().collectStatistics(features);
				if (monitor != null)
					monitor.subTask("Composing " + features.size()
							+ " features");

				FSTNode composition = compose(features, monitor);
				// modify(composition);

				try {
					featureVisitor.visit((FSTNonTerminal) composition);
				} catch (PrintVisitorException e) {
					e.printStackTrace();
				}
				// for (FSTNonTerminal feature : features) {
				// System.out.println(feature.toString());
				// }
				// System.out
				// .println("--------------------------------------------------");
				// if (composition != null)
				// System.out.println(composition.toString());
			}
		} catch (FileNotFoundException e1) {
			// e1.printStackTrace();
		}
	}

	private void prepareAHEADCode(LinkedList<FSTNonTerminal> features) {
		for (FSTNonTerminal feature : features) {
			final List<FSTTerminal> layerNodes = new ArrayList<FSTTerminal>();
			feature.accept(new FSTVisitor() {
				public boolean visit(FSTTerminal terminal) {
					if (terminal.getType().equals("LayerDeclaration"))
						layerNodes.add(terminal);

					if (terminal.getType().equals("MethodDecl")) {
						terminal.setBody(terminal.getBody().replaceAll(
								"Super\\s*\\(.*?\\)\\s*\\.\\s*\\w+\\s*\\(",
								"original("));
					}

					return true;
				}

				@Override
				public boolean visit(FSTNonTerminal nonTerminal) {
					if (nonTerminal.getType().equals("Java-File"))
						if (nonTerminal.getName().endsWith(".jak"))
							nonTerminal.setName(nonTerminal.getName()
									.substring(0,
											nonTerminal.getName().length() - 3)
									+ "java");
					return super.visit(nonTerminal);
				}
			});
			for (FSTTerminal node : layerNodes) {
				((FSTNonTerminal) node.getParent()).removeChild(node);
			}

		}

	}

	private void initializeOriginalFeatures(LinkedList<FSTNonTerminal> features) {
		for (FSTNonTerminal feature : features) {
			assert feature.getType().equals("Feature");

			final String featureName = feature.getName();
			feature.accept(new FSTVisitor() {
				public boolean visit(FSTTerminal terminal) {
					FeatureExpressions.set(terminal,
							singleFeatureExpression(featureName));
					return true;
				}

				public boolean visit(FSTNonTerminal nonTerminal) {
					FeatureExpressions.set(nonTerminal,
							singleFeatureExpression(featureName));
					return true;
				}
			});
		}
	}

	public static void main(String[] args) {
		FeatureFSTGenComposer composer = new FeatureFSTGenComposer();
		composer.run(args);
	}

	private static FSTNode compose(List<FSTNonTerminal> tl,
			IProgressMonitor monitor) {
		FSTNode composed = null;
		int idx = 0;
		for (FSTNode current : tl) {
			idx++;
			if (monitor != null)
				monitor.subTask("Composing " + idx + "/" + tl.size()
						+ " features");

			if (composed != null) {
				composed = compose(current, composed);
			} else
				composed = current;
		}
		return composed;
	}

	public static FSTNode compose(FSTNode nodeA, FSTNode nodeB) {
		return compose(nodeA, nodeB, null);
	}

	public static FSTNode compose(FSTNode nodeA, FSTNode nodeB,
			FSTNode compParent) {

		if (nodeA.compatibleWith(nodeB)) {
			FSTNode compNode = nodeB.getShallowClone();
			FeatureExpressions.set(compNode, FeatureExpressions.get(nodeB));
			compNode.setParent(compParent);

			// composed SubTree-stub is integrated in the new Tree, needs
			// children
			if (nodeA instanceof FSTNonTerminal
					&& nodeB instanceof FSTNonTerminal) {
				FSTNonTerminal nonterminalA = (FSTNonTerminal) nodeA;
				FSTNonTerminal nonterminalB = (FSTNonTerminal) nodeB;
				FSTNonTerminal nonterminalComp = (FSTNonTerminal) compNode;

				for (FSTNode childB : nonterminalB.getChildren()) {
					FSTNode childA = nonterminalA.getCompatibleChild(childB);
					// for each child of B get the first compatible child of
					// A
					// (CompatibleChild means a Child which root equals B's
					// root)
					if (childA == null) {
						// no compatible child, FST-node only in B
						FSTNode cl = childB.getDeepClone();
						FeatureExpressions.cloneFE(cl, childB);
						nonterminalComp.addChild(cl);
					} else {
						nonterminalComp.addChild(compose(childA, childB,
								nonterminalComp));
					}
				}
				for (FSTNode childA : nonterminalA.getChildren()) {
					FSTNode childB = nonterminalB.getCompatibleChild(childA);
					if (childB == null) {
						// no compatible child, FST-node only in A
						FSTNode cl = childA.getDeepClone();
						FeatureExpressions.cloneFE(cl, childA);
						nonterminalComp.addChild(cl);
					}
				}
				FeatureExpressions.set(nonterminalComp, mergeAnnotations(
						nonterminalA, nonterminalB));

				return nonterminalComp;
			} else if (nodeA instanceof FSTTerminal
					&& nodeB instanceof FSTTerminal
					&& compParent instanceof FSTNonTerminal) {
				FSTTerminal terminalA = (FSTTerminal) nodeA;
				FSTTerminal terminalB = (FSTTerminal) nodeB;
				FSTTerminal terminalComp = (FSTTerminal) compNode;
				FSTNonTerminal nonterminalParent = (FSTNonTerminal) compParent;

				if (terminalA.getCompositionMechanism().equals(
						Replacement.COMPOSITION_RULE_NAME)) {
					// System.out.println("Terminal replacement: " +
					// terminalA.toString() + " replaces " +
					// terminalB.toString());
					FeatureExpressions.set(terminalComp, mergeAnnotations(
							terminalA, terminalB));
				} else if (terminalA.getCompositionMechanism().equals(
						StringConcatenation.COMPOSITION_RULE_NAME)) {
					// System.out.println("Terminal concatenation: " +
					// terminalA.toString() + " is concatenated to " +
					// terminalB.toString());
					StringConcatenation.compose(terminalA, terminalB,
							terminalComp, nonterminalParent);
				} else if (terminalA.getCompositionMechanism().equals(
						ImplementsListMerging.COMPOSITION_RULE_NAME)) {
					// System.out.println("Implements list merging: " +
					// terminalA.toString() + " extends " +
					// terminalB.toString());
					ImplementsListMerging.compose(terminalA, terminalB,
							terminalComp, nonterminalParent);
					// TODO
				} else if (terminalA.getCompositionMechanism().equals(
						FeatureJavaMethodOverriding.COMPOSITION_RULE_NAME)) {
					// System.out.println("Java method overriding: " +
					// terminalA.toString() + " overrides " +
					// terminalB.toString());
					FeatureJavaMethodOverriding.compose(terminalA, terminalB,
							terminalComp, nonterminalParent);
					FeatureExpressions.set(terminalComp, mergeAnnotations(
							terminalA, terminalB));
				} else if (terminalA.getCompositionMechanism().equals(
						FeatureConstructorConcatenation.COMPOSITION_RULE_NAME)) {
					// System.out.println("Constructor concatenation: " +
					// terminalA.toString() + " extends " +
					// terminalB.toString());
					FeatureConstructorConcatenation.compose(terminalA,
							terminalB, terminalComp, nonterminalParent);
					// TODO
				} else if (terminalA.getCompositionMechanism().equals(
						ModifierListSpecialization.COMPOSITION_RULE_NAME)) {
					// System.out.println("Modifier list specification: " +
					// terminalA.toString() + " specializes " +
					// terminalB.toString());
					ModifierListSpecialization.compose(terminalA, terminalB,
							terminalComp, nonterminalParent);
				} else if (terminalA.getCompositionMechanism().equals(
						FieldOverriding.COMPOSITION_RULE_NAME)) {
					// System.out.println("Field overiding: " +
					// terminalA.toString() + " overrides " +
					// terminalB.toString());
					FieldOverriding.compose(terminalA, terminalB, terminalComp,
							nonterminalParent);
				} else if (terminalA.getCompositionMechanism().equals(
						CompositionError.COMPOSITION_RULE_NAME)) {
					CompositionError.compose(terminalA, terminalB,
							terminalComp, nonterminalParent);
				} else {
					System.err
							.println("Error: don't know how to compose terminals: "
									+ terminalB.toString()
									+ " replaces "
									+ terminalA.toString());
				}
				return terminalComp;
			}
			return null;
		} else
			return null;

	}

	/**
	 * or-connects the annotations of both expressions
	 * 
	 * @param nodeA
	 * @param nodeB
	 */
	private static Node mergeAnnotations(FSTNode nodeA, FSTNode nodeB) {
		if (GuidslFileLoader.featureModel.checkCondition(new Implies(
				FeatureExpressions.get(nodeB), FeatureExpressions.get(nodeA))))
			return FeatureExpressions.get(nodeA);
		else if (GuidslFileLoader.featureModel.checkCondition(new Implies(
				FeatureExpressions.get(nodeA), FeatureExpressions.get(nodeB))))
			return FeatureExpressions.get(nodeB);
		else
			return simplify(new Or(FeatureExpressions.get(nodeA),
					FeatureExpressions.get(nodeB)));
	}

	private static Node simplify(Or or) {
		Literal f = firstFeature(or);
		FeatureModel fm = GuidslFileLoader.featureModel;
		Feature feature = fm.getFeature(f.toString());
		Feature parent = feature.getParent();
		if (parent == null)
			return or;
		Literal parentNode = singleFeatureExpression(parent.getName());
		if (fm.checkCondition(new Equals(parentNode, or)))
			return parentNode;

		return or;
	}

	private static Literal firstFeature(Node node) {
		if (node instanceof Literal)
			return (Literal) node;
		Node[] children = node.getChildren();
		if (children.length > 0)
			return firstFeature(children[0]);
		return null;
	}

	private static Literal singleFeatureExpression(final String featureName) {
		return new Literal(NodeCreator.getVariable(
				GuidslFileLoader.featureModel.getFeature(featureName),
				GuidslFileLoader.featureModel));
	}

	public void run(String[] strings) {
		run(strings, null);
	}

}
