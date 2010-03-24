package de.ovgu.cide.typing.fj;

import cide.gast.IASTNode;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.model.ITypingCheck;

/**
 * Abstrakte Basisklasse für alle Typing-Checks in CFJ
 * 
 * @author Malte Rosenthal
 */
public abstract class CFJTypingCheck implements ITypingCheck {
	
	protected ColoredSourceFile file;
	protected CFJTypingManager typingManager;
	protected String errorMessage;
	protected IASTNode source;
	
	protected CFJTypingCheck(ColoredSourceFile file, CFJTypingManager typingManager, IASTNode source) {
		this.file = file;
		this.typingManager = typingManager;
		this.source = source;
	}
	
	protected boolean createError(String message, IASTNode source) {
		if (source != null)
			this.source = source;
		
		StringBuilder sb = new StringBuilder(message.length() + typingManager.getErrorMessages().size() * 50);
		sb.append(message);
		for (String s : typingManager.getErrorMessages()) {
			sb.append(" -> ").append(s);
		}
		errorMessage = sb.toString();
		
		typingManager.clearErrorMessages();
		return false;
	}
	
	protected boolean createError(String message) {
		return createError(message, null);
	}
	
	protected boolean prependError(String message) {
		errorMessage = (errorMessage == null) ? message : message + " -> " + errorMessage;
		return false;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public ColoredSourceFile getFile() {
		return file;
	}

	public Severity getSeverity() {
		return Severity.ERROR;
	}
	
	
	public IASTNode getSource() {
		return source;
	}
}
