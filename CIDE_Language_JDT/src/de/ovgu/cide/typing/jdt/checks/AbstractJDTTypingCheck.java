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

	

	public final ColoredSourceFile getFile() {
		return file;
	}

	public final Severity getSeverity() {
		return Severity.ERROR;
	}

	public final IASTNode getSource() {
		return source;
	}

}