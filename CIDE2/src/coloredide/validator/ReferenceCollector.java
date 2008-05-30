package coloredide.validator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import cide.gast.ASTNode;
import cide.gast.ASTVisitor;
import cide.greferences.IReference;
import cide.greferences.IReferenceType;
import cide.greferences.IResolver;
import cide.greferences.Reference;
import cide.greferences.ReferenceResolvingException;

public class ReferenceCollector extends ASTVisitor {

	private final HashMap<IReferenceType, Set<IReference>> referencesByType = new HashMap<IReferenceType, Set<IReference>>();
	private Set<IReferenceType> requiredReferences;
	private IResolver resolver;

	public ReferenceCollector(IResolver resolver,
			Set<IReferenceType> requiredReferences) {
		this.requiredReferences = requiredReferences;
		this.resolver = resolver;
	}

	public HashMap<IReferenceType, Set<IReference>> getReferences() {
		return referencesByType;
	}

	@Override
	public boolean visit(ASTNode node) {
		IReferenceType[] providedReferenceTypes = node.getReferenceTypes();
		for (IReferenceType providedReferenceType : providedReferenceTypes) {
			if (requiredReferences.contains(providedReferenceType)) {
				ASTNode target = null;
				try {
					target = resolver.getReferenceTarget(providedReferenceType,
							node);
				} catch (ReferenceResolvingException e) {
					e.printStackTrace();
				}
				if (target != null) {
					foundReference(providedReferenceType, node, target);
				}
			}
		}

		return super.visit(node);
	}

	private void foundReference(IReferenceType referenceType, ASTNode source,
			ASTNode target) {
		Reference reference = new Reference(referenceType, source, target);
		Set<IReference> references = referencesByType.get(referenceType);
		if (references == null) {
			references = new HashSet<IReference>();
			referencesByType.put(referenceType, references);
		}
		references.add(reference);
	}

}
