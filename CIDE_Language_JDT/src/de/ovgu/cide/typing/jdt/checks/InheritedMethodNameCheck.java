package de.ovgu.cide.typing.jdt.checks;

import java.util.List;

import cide.gast.IASTNode;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.jdt.JDTTypingProvider;
import de.ovgu.cide.typing.jdt.checks.util.MethodPathItem;
import de.ovgu.cide.typing.model.IEvaluationStrategy;

/**
 * checks colors between method declaration and inherited methods.
 * as necessary, throws an error according to the strategy that
 * overriding relationship is changed in some variants 
 * 
 * @author adreilin
 * 
 */
public class InheritedMethodNameCheck extends AbstractJDTTypingCheck {

	private final String name;
	private final List<MethodPathItem> inherMethods;

	public InheritedMethodNameCheck(ColoredSourceFile file,
			JDTTypingProvider typingProvider, IASTNode source,
			String name, List<MethodPathItem> inherMethods) {
		
		super(file,typingProvider,source);

		this.inherMethods = inherMethods;
		this.name = name;
		
	}

	public boolean evaluate(IEvaluationStrategy strategy) {
		
		//checks "AND" condition for all found keys
		for (MethodPathItem tmpItem : inherMethods) {	
			
			if (tmpItem.isAbstract())
				continue;
		
			//checks for each overridden method the implies condition
			if (!strategy.implies(file.getFeatureModel(),   typingProvider.getBindingColors()
					.getColors(tmpItem.getKey()), file.getColorManager()
					.getColors(source))) 
				return false;
		}
		
		return true;
	}

	public String getErrorMessage() {
		return "Overriding Relationship of " + name +  " is changed in some variants.";
	}
	

	public String getProblemType() {
		return "de.ovgu.cide.typing.jdt.methodnameimplementation";
	}

	@Override
	public Severity getSeverity() {
		return Severity.WARNING;
	}

}
