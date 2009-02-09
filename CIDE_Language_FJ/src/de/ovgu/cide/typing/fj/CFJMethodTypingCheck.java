package de.ovgu.cide.typing.fj;

import java.util.LinkedList;
import java.util.List;

import tmp.generated_fj.FormalParameter;
import tmp.generated_fj.MethodDeclaration;
import cide.gast.IASTNode;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.SourceFileColorManager;
import de.ovgu.cide.typing.fj.CFJTypingManager.CFJType;
import de.ovgu.cide.typing.model.IEvaluationStrategy;
import de.ovgu.cide.typing.model.ITypingCheck;

/**
 * M OK in C
 * 
 * @author Malte Rosenthal
 */
public class CFJMethodTypingCheck implements ITypingCheck {
	
	private ColoredSourceFile file;
	private MethodDeclaration source;
	private CFJTypingManager typingManager;
	
	private String errorMessage;
	
	public CFJMethodTypingCheck(ColoredSourceFile file, MethodDeclaration source, CFJTypingManager typingManager) {
		this.file = file;
		this.source = source;
		this.typingManager = typingManager;
	}

	@Override
	public boolean evaluate(IEvaluationStrategy strategy) {
		IFeatureModel fm = file.getFeatureModel();
		SourceFileColorManager colorManager = file.getColorManager();
		
		List<IASTNode> presentParameters = 
			typingManager.filter(typingManager.cast(source.getFormalParameterList().getFormalParameter()), colorManager.getColors(source), strategy);
		typingManager.presentVariables = new LinkedList<String>();
		for (IASTNode node : presentParameters) {
			typingManager.presentVariables.add(((FormalParameter) node).getIdentifier().getValue());
		}
		
		CFJTypeDeclarationWrapper returnTypeDeclaration = typingManager.findTypeDeclaration(CFJTypingManager.getIdentifier(source.getType()));
		if (returnTypeDeclaration == null) {
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
			return createError("Return-type does not exist in all possible variants of this method.");
		}
		
		if (!typingManager.override(source.getIdentifier().getValue(), returnType.getSuperType().getASTNode(), typingManager.new MethodSignature(source), 
									colorManager.getColors(source), strategy)) {
			return createError("No valid overriding.");
		}
		
		for (FormalParameter param : source.getFormalParameterList().getFormalParameter()) {
			if (!strategy.implies(fm, colorManager.getColors(param), 
									  colorManager.getColors(typingManager.findTypeDeclaration(CFJTypingManager.getIdentifier(param.getType()))))) {
				return createError("Type of parameter >" + param.getIdentifier().getValue() + "< does not exist in some variants.");
			}
		}
		
		return true;
	}
	
	private boolean createError(String message) {
		StringBuilder sb = new StringBuilder(message.length() + typingManager.getErrorMessages().size() * 50);
		sb.append(message);
		for (String s : typingManager.getErrorMessages()) {
			sb.append(" -> ").append(s);
		}
		errorMessage = sb.toString();
		
		typingManager.presentVariables = null;
		typingManager.clearErrorMessages();
		return false;
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
		return "de.ovgu.cide.typing.fj.methodtyping";
	}

	@Override
	public Severity getSeverity() {
		return Severity.ERROR;
	}

	@Override
	public IASTNode getSource() {
		return source;
	}
}
