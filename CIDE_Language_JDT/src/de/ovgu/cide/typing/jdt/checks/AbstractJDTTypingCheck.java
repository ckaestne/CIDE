package de.ovgu.cide.typing.jdt.checks;

import cide.gast.IASTNode;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.jdt.JDTTypingProvider;
import de.ovgu.cide.typing.model.ITypingCheck;

public abstract class AbstractJDTTypingCheck implements ITypingCheck {
	protected final IASTNode source;
	protected final JDTTypingProvider typingProvider;
	protected final ColoredSourceFile file;

	public AbstractJDTTypingCheck(ColoredSourceFile file,
			JDTTypingProvider typingProvider, IASTNode source) {
		this.file = file;
		this.source = source;
		this.typingProvider = typingProvider;
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