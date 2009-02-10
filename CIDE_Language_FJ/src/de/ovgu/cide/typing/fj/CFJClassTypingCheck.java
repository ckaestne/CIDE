package de.ovgu.cide.typing.fj;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import tmp.generated_fj.ClassConstructor;
import tmp.generated_fj.Expression;
import tmp.generated_fj.FieldAssign;
import tmp.generated_fj.FormalParameter;
import tmp.generated_fj.TypeDeclaration;
import tmp.generated_fj.VarDeclaration;
import cide.gast.IASTNode;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.SourceFileColorManager;
import de.ovgu.cide.typing.model.IEvaluationStrategy;

/**
 * C OK
 * 
 * @author Malte Rosenthal
 */
public class CFJClassTypingCheck extends CFJTypingCheck {
	
	private TypeDeclaration source;
	
	public CFJClassTypingCheck(ColoredSourceFile file, TypeDeclaration source, CFJTypingManager typingManager) {
		super(file, typingManager);
		this.source = source;
	}

	@Override
	public boolean evaluate(IEvaluationStrategy strategy) {
		ClassConstructor constructor = source.getClassConstructor();
		ArrayList<FormalParameter> constructorParameters = 
			(constructor.getFormalParameterList() == null) ? null : constructor.getFormalParameterList().getFormalParameter();
		
		CFJTypeDeclarationWrapper supertypeDeclaration = typingManager.findTypeDeclaration(source.getExtendedType());
		if (supertypeDeclaration == null)
			// Teil von (L.1)
			return createError("Supertype does not exist.");
		
		IFeatureModel fm = file.getFeatureModel();
		SourceFileColorManager colorManager = file.getColorManager();
		
		ArrayList<IASTNode> fieldsOfSupertype = 
			new ArrayList<IASTNode>(typingManager.filter(typingManager.fields(supertypeDeclaration), colorManager.getColors(source), strategy));
		
		List<FormalParameter> ownConstructorParameters = constructorParameters;
		List<FormalParameter> superConstructorParameters = null;
		
		if (constructorParameters == null) {
			if (!((fieldsOfSupertype == null) || (fieldsOfSupertype.isEmpty())))
				return createError("Wrong number of constructor-parameters.");
		} else if ((fieldsOfSupertype != null) && !fieldsOfSupertype.isEmpty()) {
			if (constructorParameters.size() < fieldsOfSupertype.size())
				return createError("Wrong number of constructor-parameters.");
			
			int i = 0;
			for (; i < constructorParameters.size(); ++i) {
				if (i >= fieldsOfSupertype.size())
					break;
				
				if (!CFJTypingManager.typesAreEqual(constructorParameters.get(i).getType(), ((VarDeclaration) fieldsOfSupertype.get(i)).getType()))
					return createError("First parameters of constructor are not fields of supertypes.");
			}
			
			ownConstructorParameters = (i >= constructorParameters.size()) ? null : constructorParameters.subList(i, constructorParameters.size());
			superConstructorParameters = (i < 1) ? null : constructorParameters.subList(0, i);
		}
		
		if (!supertypeDeclaration.isObjectType() 
				&& !strategy.implies(fm, colorManager.getColors(source), colorManager.getColors(supertypeDeclaration.getTypeDeclaration())))
			// (L.1)
			return createError("Supertype does not exist in some variants.");
		
		ArrayList<VarDeclaration> ownFields = source.getVarDeclaration();
		ArrayList<FieldAssign> fieldAssigns = constructor.getFieldAssign();
		
		// Teil von (K.2)
		if (!areEqual(ownFields, fieldAssigns, ownConstructorParameters))
			return createError("Number of fields, field assigns and constructor parameters don't fit.");
		
		if ((ownFields != null) && !ownFields.isEmpty()) {
			for (int i = 0; i < ownFields.size(); ++i) {
				// Teil von (K.2)
				if (!strategy.equal(fm, colorManager.getColors(ownFields.get(i)), colorManager.getColors(fieldAssigns.get(i)))
				 || !strategy.equal(fm, colorManager.getColors(fieldAssigns.get(i)), colorManager.getColors(ownConstructorParameters.get(i))))
					
					return createError("Annotations of fields, field assigns and constructor parameters don't fit.");
				
				// Teil von (K.3)
				if (!strategy.implies(fm, colorManager.getColors(ownFields.get(i)), 
						colorManager.getColors(typingManager.findTypeDeclaration(CFJTypingManager.getIdentifier(ownFields.get(i).getType())))))
					return createError("Type of field >" + ownFields.get(i).getIdentifier().getValue() + "< does not exist in some variants");
			}
		}
		
		ArrayList<Expression> superParameters = (constructor.getExpressionList() == null) ? null : constructor.getExpressionList().getExpression();
		
		// Teil von (K.1)
		if (!areEqual(superConstructorParameters, superParameters, fieldsOfSupertype))
			return createError("Number of constructor parameters, super-parameters and fields of supertype don't fit.");
		
		if ((superConstructorParameters != null) && !superConstructorParameters.isEmpty()) {
			for (int i = 0; i < superConstructorParameters.size(); ++i) {
				Set<IFeature> f1 = colorManager.getColors(superConstructorParameters.get(i));
				CFJTypingManager.addAll(f1, colorManager.getColors(source));
				Set<IFeature> f2 = colorManager.getColors(superParameters.get(i));
				CFJTypingManager.addAll(f2, colorManager.getColors(source));
				Set<IFeature> f3 = colorManager.getColors(fieldsOfSupertype.get(i));
				CFJTypingManager.addAll(f3, colorManager.getColors(source));
				
				// Teil von (K.1)
				if (!strategy.equal(fm, f1, f2) || !strategy.equal(fm, f2, f3))
					return createError("Annotations of constructor parameters, super-parameters and fields of supertype don't fit.");
				
				// Teil von (K.3)
				if (!strategy.implies(fm, f1, 
						colorManager.getColors(typingManager.findTypeDeclaration(CFJTypingManager.getIdentifier(superConstructorParameters.get(i).getType())))))
					return createError("Type of constructor parameter does not exist in some variants.");
			}
		}
		
		return true;
	}
	
	@SuppressWarnings("unchecked")
	private boolean areEqual(List a, List b, List c) {
		int nrOwnFields = (a == null) ? 0 : a.size();
		int nrFieldAssigns = (b == null) ? 0 : b.size();
		int nrOwnConstructorParameters = (c == null) ? 0 : c.size();
		
		return ((nrOwnFields == nrFieldAssigns) && (nrFieldAssigns == nrOwnConstructorParameters));
	}

	@Override
	public String getProblemType() {
		return "de.ovgu.cide.typing.fj.classtyping";
	}

	@Override
	public IASTNode getSource() {
		return source;
	}
}
