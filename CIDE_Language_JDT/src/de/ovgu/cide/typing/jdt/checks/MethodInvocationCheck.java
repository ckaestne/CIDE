package de.ovgu.cide.typing.jdt.checks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;

import cide.gast.IASTNode;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.jdt.ASTBridge;
import de.ovgu.cide.typing.jdt.BindingProjectColorCache;
import de.ovgu.cide.typing.jdt.JDTTypingProvider;
import de.ovgu.cide.typing.jdt.checks.resolutions.ASTBindingFinderHelper;
import de.ovgu.cide.typing.jdt.checks.resolutions.AbstractJDTTypingCheckWithResolution;
import de.ovgu.cide.typing.jdt.checks.util.CheckUtils;
import de.ovgu.cide.typing.jdt.checks.util.MethodPathItem;
import de.ovgu.cide.typing.model.IEvaluationStrategy;
import de.ovgu.cide.typing.model.ITypingMarkerResolution;

/**
 * checks colors between a field and references to it
 * 
 * @author ckaestne & adreilin
 * 
 */
public class MethodInvocationCheck extends AbstractJDTTypingCheckWithResolution {

	//TODO CHECK RESOLUTION
	
	private final IMethodBinding targetMethod;

	private final List<IASTNode> arguments;
	

	public MethodInvocationCheck(ColoredSourceFile file,
			JDTTypingProvider typingProvider, IASTNode source,
			List<IASTNode> args, IMethodBinding target) {
		super(file, typingProvider, source);
		this.arguments = args;
		this.targetMethod = target;
	}


	
	private boolean checkSourceAndTargetCondition(IEvaluationStrategy strategy, IMethodBinding targetBinding) {
		
		if (!strategy.implies(file.getFeatureModel(), file.getColorManager()
				.getColors(source), typingProvider.getBindingColors()
				.getColors(targetBinding)))
			return false;
			
		//check each parameter same condition
		for (int j = 0; j < arguments.size(); j++) {
			
			if (strategy.equal(file.getFeatureModel(), file.getColorManager()
					.getColors(arguments.get(j)), file.getColorManager()
					.getColors(source)))
				continue;
			
			//check the default case
			if (strategy.equal(file.getFeatureModel(), file.getColorManager()
					.getColors(arguments.get(j)), typingProvider.getBindingColors()
					.getColors( BindingProjectColorCache.getParamKey(targetBinding.getKey(), j))))
				continue;
			
			return false;

		}
		
		return true;
	}
	
	public boolean evaluate(IEvaluationStrategy strategy) {
		
		//check the whole method default case
		if (checkSourceAndTargetCondition(strategy, targetMethod))
			return true;
		
		//checks if target method overrides other methods for which  condition is true
		List<MethodPathItem> inherMethods = new ArrayList<MethodPathItem>();

		//get overridden method keys
		CheckUtils.collectOverriddenMethodKeysInSuperClasses(targetMethod, inherMethods);
		
		//checks "OR" condition for all found keys		
		for (MethodPathItem tmpItem: inherMethods) {

			//checks for each overridden method the implies condition
			if (checkSourceAndTargetCondition(strategy, tmpItem.getBinding())) 
				return true;
		}
			
		return false;
	
	}
	

	public String getErrorMessage() {
		return "Invoking method which is not present in some variants: "
				+ targetMethod.getName();
	}

	public String getProblemType() {
		return "de.ovgu.cide.typing.jdt.methodinvocationname";
	}

	@Override
	protected void addResolutions(
			ArrayList<ITypingMarkerResolution> resolutions,
			HashSet<IFeature> colorDiff) {
		resolutions.addAll(createChangeNodeColorResolution(findCallingStatement(source), 
				colorDiff, true, "statement", 20));
		resolutions
				.addAll(createChangeNodeColorResolution(
						findCallingMethod(source), colorDiff, true,
						"method", 18));
		resolutions.addAll(createChangeNodeColorResolution(
				findCallingType(source), colorDiff, true, "type", 16));
		IASTNode methodDecl = ASTBindingFinderHelper.getMethodDecl(targetMethod);
		if (methodDecl !=null)
			resolutions.addAll(createChangeNodeColorResolution(
					methodDecl, colorDiff, false,
					"method declaration", 14));
	}

	@Override
	protected Set<IFeature> getTargetColors() {
		return typingProvider.getBindingColors().getColors(targetMethod);
	}

}
