package de.ovgu.cide.typing.fj;

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
	
	protected CFJTypingCheck(ColoredSourceFile file, CFJTypingManager typingManager) {
		this.file = file;
		this.typingManager = typingManager;
	}
	
	protected boolean createError(String message) {
		StringBuilder sb = new StringBuilder(message.length() + typingManager.getErrorMessages().size() * 50);
		sb.append(message);
		for (String s : typingManager.getErrorMessages()) {
			sb.append(" -> ").append(s);
		}
		errorMessage = sb.toString();
		
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
	public Severity getSeverity() {
		return Severity.ERROR;
	}
}
