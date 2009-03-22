package de.ovgu.cide.configuration;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import cide.gast.IASTNode;
import cide.gast.IASTVisitor;
import cide.gparser.ParseException;
import cide.languages.ExtendedLanguageExtension;
import cide.languages.ILanguageExtension;
import de.ovgu.cide.af.Alternative;
import de.ovgu.cide.configuration.defaultconf.DefaultConfigurationMechanism;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.SourceFileColorManager;
import de.ovgu.cide.languages.LanguageExtensionProxy;
import de.ovgu.cide.typing.internal.manager.EvaluationStrategyManager;
import de.ovgu.cide.typing.model.IEvaluationStrategy;

/**
 * Konfigurations-Mechanismus, der auch alternative Codefragmente unterstützt
 * 
 * @author Malte Rosenthal
 */
public class AFConfigurationMechanism implements IConfigurationMechanism {

	@Override
	public boolean canConfigureFile(ColoredSourceFile file) {
		if (file == null)
			return false;
		ILanguageExtension le = file.getLanguageExtension();
		
		if (le != null) {
			if (le instanceof LanguageExtensionProxy) {
				le = ((LanguageExtensionProxy) le).getTarget();
			}
			return (le instanceof ExtendedLanguageExtension);
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String configureFile(ColoredSourceFile sourceFile, Collection<IFeature> selectedFeatures) throws ConfigurationException {
		if (sourceFile == null)
			return null;
		IProject project = sourceFile.getProject();
		if (project == null)
			return null;
		
		final Map<String, List<Alternative>> id2alternatives = sourceFile.getAltFeatureManager().getAllAlternatives();
		if ((id2alternatives == null) || (id2alternatives.isEmpty()))
			return (new DefaultConfigurationMechanism()).configureFile(sourceFile, selectedFeatures);
		
		final IEvaluationStrategy strategy;
		try {
			// Eigentlich ist der EvaluationStrategyManager eine interne Klasse des TypeChecking-PlugIns
			strategy = EvaluationStrategyManager.getInstance().getEvaluationStrategy(project);
		} catch (FeatureModelNotFoundException e) {
			throw new ConfigurationException(e);
		}
		
		IASTNode ast;	// Tiefe Kopie des AST des aktiven Dokuments
		try {
			ast = sourceFile.getAST().deepCopy();
		} catch (CoreException e) {
			throw new ConfigurationException(e);
		} catch (ParseException e) {
			throw new ConfigurationException(e);
		}
		
		final IFeatureModel fm = sourceFile.getFeatureModel();
		Set<IFeature> visibleFeatures = fm.getVisibleFeatures();
		final Set<IFeature> visibleSelectedFeatures = new HashSet<IFeature>();
		
		if ((selectedFeatures != null) && (visibleFeatures != null) && !visibleFeatures.isEmpty()) {
			for (IFeature f : selectedFeatures) {
				if (visibleFeatures.contains(f))
					visibleSelectedFeatures.add(f);
			}
		}
		
		final SourceFileColorManager colorManager = sourceFile.getColorManager();
		final ExtendedLanguageExtension languageExtension = getExtendedLanguageExtension(sourceFile.getLanguageExtension());
		final List<ConfigurationException> exceptions = new LinkedList<ConfigurationException>();
		
		ast.accept(new IASTVisitor() {
			@Override
			public void postVisit(IASTNode node) { }

			@Override
			public boolean visit(IASTNode node) {
				List<Alternative> alternatives = id2alternatives.get(node.getId());

				if ((alternatives != null) && !alternatives.isEmpty()) {
					for (Alternative alternative : alternatives) {
						// Bei aktiven Alternativen (d.h. Alternative steht im Dokument) gucken wir ins Dokument
						// selber, d.h. auf den Knoten, weil dieser aktueller ist.
						if (alternative.isActive)
							continue;
						
						if (strategy.implies(fm, visibleSelectedFeatures, alternative.getFeatures())) {
							IASTNode newNode = languageExtension.parseCodeFragment(node, alternative.text);
							if (newNode == null) {
								exceptions.add(new ConfigurationException("Alternative >" + alternative.altID + "< of node >" + 
																		  node.getId() + "< cannot be parsed."));
								return false;
							}
							
							node.replaceSubtreeWith(newNode);
							
							// Den weiteren Durchlauf durch den AST müssen wir jetzt steuern, weil der "alte" Durchlauf
							// nun die Kinder des alten AST-Knotens besuchen würde.
							for (IASTNode child : newNode.getChildren())
								visit(child);
							return false;
						}
					}
				}
				
				// Kommt man so weit, so muss man ins aktive Dokument gucken
				if (node.isOptional() && !strategy.implies(fm, visibleSelectedFeatures, colorManager.getColors(node))) {
					node.remove();
					return false;
				}
								
				return true;
			}
		});
		
		if ((exceptions != null) && !exceptions.isEmpty())
			throw exceptions.get(0);
		
		return ast.render();
	}
	
	@Override
	public int getPriority() {
		return 1;
	}
	
	@SuppressWarnings("unchecked")
	private ExtendedLanguageExtension getExtendedLanguageExtension(ILanguageExtension le) {
		if (le instanceof LanguageExtensionProxy)
			le = ((LanguageExtensionProxy) le).getTarget();
		return (ExtendedLanguageExtension) le;
	}
}
