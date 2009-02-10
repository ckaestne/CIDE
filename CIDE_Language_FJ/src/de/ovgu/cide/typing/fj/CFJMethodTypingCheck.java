package de.ovgu.cide.typing.fj;

import java.util.ArrayList;

import tmp.generated_fj.FormalParameter;
import tmp.generated_fj.FormalParameterList;
import tmp.generated_fj.MethodDeclaration;
import cide.gast.IASTNode;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.SourceFileColorManager;
import de.ovgu.cide.typing.fj.CFJTypingManager.CFJType;
import de.ovgu.cide.typing.model.IEvaluationStrategy;

/**
 * M OK in C
 * 
 * @author Malte Rosenthal
 */
public class CFJMethodTypingCheck extends CFJTypingCheck {
	
	private MethodDeclaration source;
	
	public CFJMethodTypingCheck(ColoredSourceFile file, MethodDeclaration source, CFJTypingManager typingManager) {
		super(file, typingManager);
		this.source = source;
	}

	@Override
	public boolean evaluate(IEvaluationStrategy strategy) {
		IFeatureModel fm = file.getFeatureModel();
		SourceFileColorManager colorManager = file.getColorManager();

		CFJTypeDeclarationWrapper returnTypeDeclaration = typingManager.findTypeDeclaration(CFJTypingManager.getIdentifier(source.getType()));
		if (returnTypeDeclaration == null) {
			// Teil von (M.1)
			return createError("Return-type does not exist.");
		}
		
		CFJType returnType = typingManager.getType(returnTypeDeclaration);
		
		CFJType typeOfExpression = typingManager.typeOf(source.getExpression(), strategy);
		if (typeOfExpression == null) {
			return createError("Expression is not well-typed.");
		}
		
		if (!typeOfExpression.isSubtypeOf(returnType)) {
			return createError("Type of expression is no subtype of return-type.");
		}
		
		if (!strategy.implies(fm, colorManager.getColors(source), colorManager.getColors(returnTypeDeclaration))) {
			// (M.1)
			return createError("Return-type does not exist in all possible variants of this method.");
		}
		
		if (!typingManager.override(source, strategy)) {
			// (M.2)
			return createError("No valid overriding.");
		}
		
		FormalParameterList formalParameterList = source.getFormalParameterList();
		ArrayList<FormalParameter> formalParameters = (formalParameterList == null) ? null : formalParameterList.getFormalParameter();
		
		if (formalParameters != null) {
			for (FormalParameter param : formalParameters) {
				if (!strategy.implies(fm, colorManager.getColors(param), 
						colorManager.getColors(typingManager.findTypeDeclaration(CFJTypingManager.getIdentifier(param.getType()))))) {
					// (M.3)
					return createError("Type of parameter >" + param.getIdentifier().getValue() + "< does not exist in some variants.");
				}
			}
		}
		
		return true;
	}

	@Override
	public String getProblemType() {
		return "de.ovgu.cide.typing.fj.methodtyping";
	}

	@Override
	public IASTNode getSource() {
		return source;
	}
}
