package de.ovgu.cide.typing.fj;

import java.util.ArrayList;
import java.util.HashSet;

import tmp.generated_fj.FormalParameter;
import tmp.generated_fj.FormalParameterList;
import tmp.generated_fj.MethodDeclaration;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.SourceFileColorManager;
import de.ovgu.cide.typing.fj.CFJTypingManager.CFJType;
import de.ovgu.cide.typing.model.IEvaluationStrategy;

/**
 * M OK in C
 * 
 * Grundlegende Voraussetzung: jedes CFJ-Programm ist auch ein gültiges FJ-Programm, wenn man die Annotationen weglässt
 * 
 * @author Malte Rosenthal
 */
public class CFJMethodTypingCheck extends CFJTypingCheck {
	
	protected MethodDeclaration methodDeclaration;
	
	public CFJMethodTypingCheck(ColoredSourceFile file, CFJTypingManager typingManager, MethodDeclaration methodDeclaration) {
		super(file, typingManager, methodDeclaration);
		this.methodDeclaration = methodDeclaration;
	}

	public boolean evaluate(IEvaluationStrategy strategy) {
		IFeatureModel fm = file.getFeatureModel();
		SourceFileColorManager colorManager = file.getColorManager();

		CFJTypeDeclarationWrapper returnTypeDeclaration = typingManager.findTypeDeclaration(CFJTypingManager.getIdentifier(methodDeclaration.getType()));
		
		FormalParameterList formalParameterList = methodDeclaration.getFormalParameterList();
		ArrayList<FormalParameter> formalParameters = (formalParameterList == null) ? null : formalParameterList.getFormalParameter();
		
		if (formalParameters != null) {
			HashSet<String> set = new HashSet<String>(formalParameters.size());
			
			for (FormalParameter param : formalParameters) {
				// (M.6): keine 2 formalen Parameter mit gleichem Namen
				if (set.contains(param.getIdentifier().getValue()))
					return createError("Duplicate method parameter >" + param.getIdentifier().getValue() + "<.", param);
				set.add(param.getIdentifier().getValue());
				
				// (M.3): Typ eines jeden formalen Parameters muss existieren
				CFJTypeDeclarationWrapper typeDecl = typingManager.findTypeDeclaration(CFJTypingManager.getIdentifier(param.getType()));
				if ((typeDecl == null) || !strategy.implies(fm, colorManager.getColors(param), colorManager.getColors(typeDecl))) {
					return createError("Type of parameter >" + param.getIdentifier().getValue() + "< does not exist in some variants.", param.getType());
				}
			}
		}
		
		// (M.1): Rückgabetyp muss existieren
		if (returnTypeDeclaration == null) {
			return createError("Return-type does not exist.", methodDeclaration.getType());
		}
		if (!strategy.implies(fm, colorManager.getColors(methodDeclaration), colorManager.getColors(returnTypeDeclaration))) {
			return createError("Return-type does not exist in all possible variants of this method.", methodDeclaration.getType());
		}
		
		CFJType returnType = typingManager.getType(returnTypeDeclaration);
		
		// (M.4): Expression muss well-typed sein
		CFJType typeOfExpression = typingManager.typeOf(methodDeclaration.getExpression(), strategy);
		if (typeOfExpression == null) {
			return createError("Expression is not well-typed.", methodDeclaration.getExpression());
		}
		
		// (M.5): Typ der Expression muss Subtyp des Rückgabetyps sein
		if (!typeOfExpression.isSubtypeOf(returnType)) {
			return createError("Type of expression is no subtype of return-type.", methodDeclaration.getExpression());
		}
		
		// (M.2): Gültiges Overriding
		if (!typingManager.override(methodDeclaration, strategy)) {
			return createError("No valid overriding.", methodDeclaration);
		}
				
		return true;
	}

	public String getProblemType() {
		return "de.ovgu.cide.typing.fj.methodtyping";
	}
}
