package de.ovgu.cide.typing.jdt.checks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.IMethodBinding;

import cide.gast.IASTNode;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.jdt.JDTTypingProvider;
import de.ovgu.cide.typing.jdt.checks.resolutions.ASTBindingFinderHelper;
import de.ovgu.cide.typing.jdt.checks.resolutions.AbstractJDTTypingCheckWithResolution;
import de.ovgu.cide.typing.jdt.checks.util.InterfacePathItem;
import de.ovgu.cide.typing.model.IEvaluationStrategy;
import de.ovgu.cide.typing.model.ITypingMarkerResolution;

//TODO RESOLUTION CREATION!

/**
 * checks colors between a method declaration and references 
 * to the declaration in interfaces or abstract classes
 * 
 * @author adreilin
 * 
 */
public class MethodDeclarationCheck extends AbstractJDTTypingCheckWithResolution {

	private final IMethodBinding sourceBinding;
	private final    List<InterfacePathItem> targetKeys;

	public MethodDeclarationCheck(ColoredSourceFile file,
			JDTTypingProvider typingProvider, IASTNode source,
			IMethodBinding sourceBinding, List<InterfacePathItem> targetKeys) {
		super(file, typingProvider, source);
		this.sourceBinding = sourceBinding;
		this.targetKeys = targetKeys;
	}

	public boolean evaluate(IEvaluationStrategy strategy) {
		
		//checks  "AND" condition for all found keys
		for (InterfacePathItem tmpKey: targetKeys) {
			
			System.out.println("CHECK: " + tmpKey.getKey());
		
			//check relationship between each declaring and implemented method
			if (strategy.implies(file.getFeatureModel(), typingProvider.getBindingColors()
					.getColors(tmpKey.getKey()), file.getColorManager().getColors(source)))
				continue;
				
			//checks if current item is abstract
			if (tmpKey.isAbstract())
				//check failed
				return false;
			else
				//another method implementation exists
				return true;
				
			
		}

		return true;
		
	}

	public String getErrorMessage() {
		return "Declaring method which is not present in some variants: "
				+ sourceBinding.getName();
	}

	public String getProblemType() {
		return "de.ovgu.cide.typing.jdt.methoddeclaration";
	}

	@Override
	protected void addResolutions(
			ArrayList<ITypingMarkerResolution> resolutions,
			HashSet<IFeature> colorDiff) {
		
	//TODO!
		
//		resolutions.addAll(createChangeNodeColorResolution(findCallingStatement(source), 
//				colorDiff, true, "statement", 20));
//		resolutions
//				.addAll(createChangeNodeColorResolution(
//						findCallingMethod(source), colorDiff, true,
//						"method", 18));
//		resolutions.addAll(createChangeNodeColorResolution(
//				findCallingType(source), colorDiff, true, "type", 16));
//		IASTNode methodDecl = ASTBindingFinderHelper.getMethodDecl(sourceBinding);
//		if (methodDecl !=null)
//			resolutions.addAll(createChangeNodeColorResolution(
//					"method declaration", 14));
	}

	@Override
	protected Set<IFeature> getTargetColors() {
		return typingProvider.getBindingColors().getColors(sourceBinding);
	}

}
