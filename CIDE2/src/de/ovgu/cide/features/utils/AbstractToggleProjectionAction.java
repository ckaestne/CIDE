package de.ovgu.cide.features.utils;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;

import de.ovgu.cide.ColoredIDEImages;

public abstract class AbstractToggleProjectionAction extends Action {

	public AbstractToggleProjectionAction() {
		super("Filter", IAction.AS_CHECK_BOX);

		setText("Filter");
		setToolTipText("Hide unchecked features");
		ColoredIDEImages
				.setImageDescriptors(this, ColoredIDEImages.INTERACTION);
	}
}
