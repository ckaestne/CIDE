package de.ovgu.cide.af;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.core.runtime.CoreException;

import cide.gast.IASTNode;
import cide.gast.IASTVisitor;
import cide.gparser.ParseException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.model.IEvaluationStrategy;
import de.ovgu.cide.typing.model.ITypingCheck;

/**
 * Überprüft die Fälle, die bei alternativen Codefragmenten zu Syntaxfehlern führen können:
 * 
 * - Je zwei Alternativen eines Codefragments müssen sich gegenseitig ausschließen (mutually exclusive).
 * - Ist ein AST-Knoten nicht-optional, so muss bei jeder gültigen Feature-Auswahl genau eine Alternative
 *   präsent sein.
 *   
 * Diese Syntax-Checks sind technisch gesehen als ITypingChecks implementiert, obwohl es sich natürlich
 * nicht um Überprüfungen des Typsystems handelt. Aus diesem Grund konnten sie auch nicht im Projekt
 * CIDE2 implementiert werden, da es sonst einen Abhängigkeitszyklus CIDE2_TypeChecking <-> CIDE2
 * gegeben hätte. Da der Mechanismus "alternative Features" zur Zeit nur mit dem XML-Storage-Provider
 * funktioniert, habe ich die Syntax-Checks auch mal hier rein gepackt.
 * 
 * @author Malte Rosenthal
 */
public class AlternativeSyntaxCheck implements ITypingCheck {
	
	ColoredSourceFile file;
	Severity severity;
	IASTNode ast;
	IASTNode source;
	String errorMessage;

	public AlternativeSyntaxCheck(ColoredSourceFile file) throws CoreException, ParseException {
		this.file = file;
		this.ast = file.getAST();
		this.source = this.ast;
	}
	
	@Override
	public boolean evaluate(IEvaluationStrategy strategy) {
		Map<String, List<Alternative>> id2alternatives = file.getAltFeatureManager().getAllAlternatives();
		if (id2alternatives == null)
			return true;
		
		IFeatureModel fm = file.getFeatureModel();
		
		for (Entry<String, List<Alternative>> entry : id2alternatives.entrySet()) {
			List<Alternative> alternatives = entry.getValue();
			if ((alternatives == null) || alternatives.isEmpty())
				continue;
			
			Set<IFeature> context = new HashSet<IFeature>();
			boolean isOptional = false;
			List<Set<IFeature>> featureSets = new LinkedList<Set<IFeature>>();
			
			Map<Alternative, List<Alternative>> alternativesGroupedByParent = file.getAltFeatureManager().groupByParent(alternatives);
			for (Entry<Alternative, List<Alternative>> group : alternativesGroupedByParent.entrySet()) {
				context = group.getKey().getFeatures();
				featureSets.clear();
				
				for (Alternative alternative : group.getValue()) {
					// Wir könnten noch überprüfen, ob isOptional bei jeder Alternative gleich ist, aber
					// das sollten sie (sonst Programmierfehler beim Anlegen/Speichern von Alternativen). Außerdem
					// würde es ausreichen, isOptional nur aus einer der Alternativen auszulesen.
					isOptional = alternative.isOptional;
					featureSets.add(alternative.ownFeatures);
				}
				
				// Zwei Alternativen müssen sich gegenseitig ausschließen
				if (!strategy.areMutualExclusive(fm, context, featureSets)) {
					return createError("At least two alternatives of node >" + entry.getKey() + "< in parent-alternative >" + 
									   group.getKey().altID + "< are not mutually exclusive.", entry.getKey());
				}
				
				// Bei nicht-optionalen AST-Knoten muss zusätzlich bei jeder gültigen Konfiguration mindestens eine
				// Alternative präsent sein
				if (!isOptional && strategy.mayBeMissing(fm, context, featureSets)) {
					return createError("Parent-alternative >" + group.getKey().altID + "<: Node >" + entry.getKey() + 
									   "< is not present in some variants.", entry.getKey());
				}
			}
		}
		
		return true;
	}
	
	protected boolean createError(String message, IASTNode source) {
		if (source != null)
			this.source = source;
		errorMessage = message;
		severity = Severity.ERROR;
		return false;
	}
	
	protected boolean createError(String message, String astID) {
		ASTNodeFinder finder = new ASTNodeFinder(astID);
		this.ast.accept(finder);
		
		return createError(message, finder.source);
	}
	
	private class ASTNodeFinder implements IASTVisitor {
		public IASTNode source;
		private String astID;
		
		public ASTNodeFinder(String astID) {
			this.astID = astID;
		}
		
		@Override
		public void postVisit(IASTNode node) { }

		@Override
		public boolean visit(IASTNode node) {
			if (node.getId().equals(this.astID)) {
				source = node;
				return false;
			}
			return true;
		}
	}
	
	@Override
	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public ColoredSourceFile getFile() {
		return file;
	}

	@Override
	public String getProblemType() {
		return "de.ovgu.cide.af.syntax";
	}

	@Override
	public Severity getSeverity() {
		return severity;
	}

	@Override
	public IASTNode getSource() {
		return source;
	}
}
