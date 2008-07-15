package cide.greferences;

import cide.gast.IASTNode;

public interface IValidationErrorCallback {
	public void validationError(IASTNode node, IValidationRule brokenRule);
}
