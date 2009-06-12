package de.ovgu.cide.typing.model;

import org.eclipse.core.resources.IMarker;

public interface ITypingCheckWithResolution extends ITypingCheck {
	public final static ITypingMarkerResolution[] NO_RESOLUTIONS = new ITypingMarkerResolution[0];

	ITypingMarkerResolution[] getResolutions(IMarker marker);

}
