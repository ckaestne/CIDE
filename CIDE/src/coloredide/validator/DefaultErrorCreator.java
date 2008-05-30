package coloredide.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;

import coloredide.features.ASTID;
import coloredide.features.Feature;
import coloredide.features.source.ColoredJavaSourceFile;
import coloredide.validator.ColorProblem.Kind;

public class DefaultErrorCreator implements ValidationErrorCallback {

	private final ColorProblemFactory problemFactory = new ColorProblemFactory();

	protected static class ColorProblemFactory {
		public ColorProblem createError(ASTNode node, String message) {
			return createError(node, message, node.getStartPosition(), node
					.getStartPosition()
					+ node.getLength(), ColorProblem.OTHER);
		}

		public ColorProblem createError(ASTNode node, String message, Kind kind) {
			return createError(node, message, node.getStartPosition(), node
					.getStartPosition()
					+ node.getLength(), kind);
		}

		public ColorProblem createError(ASTNode node, String message,
				int startPosition, int endPosition, Kind kind) {
			return createProblem(ColoredJavaSourceFile.getResource(node),
					message, IProblem.ExternalProblemNotFixable,
					IMarker.SEVERITY_ERROR, startPosition, endPosition, 0, 0,
					kind, ASTID.id(node).id);
		}

		public ColorProblem createError(IResource resource, String message,
				int startPosition, int endPosition, int line, int column,
				Kind kind, String nodeID) {
			return createProblem(resource, message,
					IProblem.ExternalProblemNotFixable, IMarker.SEVERITY_ERROR,
					startPosition, endPosition, line, column, kind, nodeID);
		}

		public ColorProblem createProblem(IResource resource, String message,
				int id, int severity, int startPosition, int endPosition,
				int line, int column, Kind kind, String nodeID) {
			return new ColorProblem(resource, message, id, new String[] {},
					severity, startPosition, endPosition, line, column, kind,
					nodeID);
		}
	}

	protected final List<ColorProblem> problems;
	protected IProject project;

	public DefaultErrorCreator() {
		problems = new ArrayList<ColorProblem>();
	}
	public DefaultErrorCreator(IProject project) {
		this();
		this.project = project;
	}

	public void errorCallMustHaveDeclarationColor(ASTNode call,
			Set<Feature> callColors, IMethodBinding decl,
			Set<Feature> declColors) {

		String message = "Method/constructor "
				+ decl.getName()
				+ " cannot be resolved in some variants (declaration must have less colors than invocation; "
				+ "; " + printColors(declColors) + " subset of "
				+ printColors(callColors) + ")";

		problems.add(problemFactory.createError(call, message,
				ColorProblem.METHODCALL));
	}

	private String printColors(Set<Feature> callColors) {
		String result = "";
		for (Feature c : callColors) {
			if (result.length() > 0)
				result += ", ";
			result += c.getShortName(project);
		}
		return "{" + result + "}";
	}

	public void errorVariableAccessMustHaveDeclarationColor(ASTNode varAccess,
			Set<Feature> callColors, IVariableBinding decl,
			Set<Feature> declColors) {

		String message = "Variable/field "
				+ decl.getName()
				+ " cannot be resolved in some variants (declaration must have less colors than accessing statements"
				+ "; " + printColors(callColors) + " vs "
				+ printColors(declColors) + ")";

		problems.add(problemFactory.createError(varAccess, message,
				ColorProblem.FIELDACCESS));
	}

	public void nodeMustHaveParentsColor(ASTNode node, Set<Feature> declColors,
			ASTNode parent) {
		String message = "Invalid coloring: must have parents color ("
				+ declColors + ")";

		problems.add(problemFactory.createError(node, message));
	}

	public void warningCannotResolveBinding(ASTNode node) {
		String message = "Cannot resolve binding";
		problems.add(problemFactory.createError(node, message));
	}

	public void errorCallParamMustHaveDeclarationColor(Expression param,
			Set<Feature> declColors, String note) {

		String message = "Parameters do not match in all variants (parameters must have the same colors "
				+ printColors(declColors) + ") " + note;

		problems.add(problemFactory.createError(param, message));
	}
	
	public void errorOverridingParamMustHaveDeclarationColor(
			SingleVariableDeclaration param, Set<Feature> ownSuperParamColors,
			String note) {
		String message = "Parameters do not match in all variants (parameters must have the same colors "
			+ printColors(ownSuperParamColors) + ") " + note;

	problems.add(problemFactory.createError(param, message));
	}

	public void errorTypeRefMustHaveTypeColor(Type node, Set<Feature> typeColors) {
		String message = "Type "
				+ node.toString()
				+ " cannot be resolved in all variants (declaration must have less colors than reference;"
				+ printColors(typeColors) + ")";

		problems.add(problemFactory.createError(node, message,
				ColorProblem.TYPE));
	}

	public void errorImportMustHaveTargetColor(ImportDeclaration node,
			Set<Feature> typeColors) {
		String message = "Import "
				+ node.getName().toString()
				+ " cannot be resolved in all variants (type declaration must have less colors than import statement; "
				+ printColors(typeColors) + ")";

		problems.add(problemFactory.createError(node, message,
				ColorProblem.IMPORT));
	}

	public void errorImportedTypeMustHaveImportColor(ASTNode type,
			Set<Feature> typeColors, Set<Feature> importColors) {
		String message = "Imported Type must have Import colors ("
				+ printColors(importColors) + ")";

		problems.add(problemFactory.createError(type, message));
	}


}
