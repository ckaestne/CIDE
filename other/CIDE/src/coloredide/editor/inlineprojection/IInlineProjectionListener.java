package coloredide.editor.inlineprojection;

import org.eclipse.jface.text.source.projection.IProjectionListener;

public interface IInlineProjectionListener extends IProjectionListener {

	/**
	 * Tells this listener that projection has been enabled.
	 */
	void inlineProjectionEnabled();

	/**
	 * Tells this listener that projection has been disabled.
	 */
	void inlineProjectionDisabled();

}
