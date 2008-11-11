package de.ovgu.cide.validator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;

import cide.gast.IASTNode;
import cide.greferences.IValidationErrorCallback;
import cide.greferences.IValidationRule;
import de.ovgu.cide.features.source.ColoredSourceFile;

public class DefaultErrorCreator implements IValidationErrorCallback {

	private final ColorProblemFactory problemFactory = new ColorProblemFactory();

	protected static class ColorProblemFactory {
		public ColorProblem createError(IASTNode node, String message,
				IValidationRule brokenRule) {
			return createError(node, message, node.getStartPosition(), node
					.getStartPosition()
					+ node.getLength(), brokenRule);
		}

		public ColorProblem createError(IASTNode node, String message,
				int startPosition, int endPosition, IValidationRule brokenRule) {
			return createProblem(ColoredSourceFile.getResource(node), message,/* IProblem.ExternalProblemNotFixable, */
			IMarker.SEVERITY_ERROR, startPosition, endPosition, 0, 0,
					brokenRule, node.getId());
		}

		public ColorProblem createError(IResource resource, String message,
				int startPosition, int endPosition, int line, int column,
				IValidationRule brokenRule, String nodeID) {
			return createProblem(resource, message,
			/* IProblem.ExternalProblemNotFixable, */brokenRule
					.getErrorSeverity(), startPosition, endPosition, line,
					column, brokenRule, nodeID);
		}

		public ColorProblem createProblem(IResource resource, String message,
				int severity, int startPosition, int endPosition, int line,
				int column, IValidationRule brokenRule, String nodeID) {
			return new ColorProblem(resource, message, severity, startPosition,
					endPosition, line, column, brokenRule, nodeID);
		}
	}

	protected final List<ColorProblem> problems = new ArrayList<ColorProblem>();

	// public void errorCallMustHaveDeclarationColor(IASTNode call,
	// Set<Feature> callColors, IMethodBinding decl,
	// Set<Feature> declColors) {
	//
	// String message = "Method/Constructor Call and Declaration colors do not
	// match ("
	// + decl.getName()
	// + "; "
	// + callColors
	// + " vs "
	// + declColors
	// + ")";
	//
	// problems.add(problemFactory.createError(call, message,
	// ColorProblem.METHODCALL));
	// }
	//
	// public void errorVariableAccessMustHaveDeclarationColor(IASTNode
	// varAccess,
	// Set<Feature> callColors, IVariableBinding decl,
	// Set<Feature> declColors) {
	//
	// String message = "Variable/Field Access and Declaration colors do not
	// match ("
	// + decl.getName()
	// + "; "
	// + callColors
	// + " vs "
	// + declColors
	// + ")";
	//
	// problems.add(problemFactory.createError(varAccess, message,
	// ColorProblem.FIELDACCESS));
	// }
	//
	// public void nodeMustHaveParentsColor(IASTNode node, Set<Feature>
	// declColors,
	// IASTNode parent) {
	// String message = "Invalid coloring: must have parents color ("
	// + declColors + ")";
	//
	// problems.add(problemFactory.createError(node, message));
	// }
	//
	// public void warningCannotResolveBinding(IASTNode node) {
	// String message = "Cannot resolve binding";
	// problems.add(problemFactory.createError(node, message));
	// }
	//
	// public void errorCallParamMustHaveDeclarationColor(Expression param,
	// Set<Feature> declColors, String note) {
	//
	// String message = "Parameter must have declared colors (" + declColors
	// + ") " + note;
	//
	// problems.add(problemFactory.createError(param, message));
	// }
	//
	// public void errorTypeRefMustHaveTypeColor(IASTNode node,
	// Set<Feature> typeColors) {
	// String message = "Type reference must have Type colors (" + typeColors
	// + ")";
	//
	// problems.add(problemFactory.createError(node, message,
	// ColorProblem.TYPE));
	// }
	//
	// public void errorImportMustHaveTargetColor(ImportDeclaration node,
	// Set<Feature> typeColors) {
	// String message = "Import must have target colors (" + typeColors + ")";
	//
	// problems.add(problemFactory.createError(node, message,
	// ColorProblem.IMPORT));
	// }
	//
	// public void errorImportedTypeMustHaveImportColor(IASTNode type,
	// Set<Feature> typeColors, Set<Feature> importColors) {
	// String message = "Imported Type must have Import colors ("
	// + importColors + ")";
	//
	// problems.add(problemFactory.createError(type, message));
	// }
	public void validationError(IASTNode node, IValidationRule brokenRule) {
		problems.add(problemFactory.createError(node, brokenRule
				.getErrorMessage(), brokenRule));
	}

}
