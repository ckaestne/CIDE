package coloredide.export2jak.validator;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import coloredide.export2jak.UnsupportedColoring;

public class JakColorCheckMarker {

	public static final String TYPEID = "coloride.jakColorWarning";

	public static void createMarker(IResource resource, UnsupportedColoring u)
			throws CoreException {
		IMarker marker = resource.createMarker(JakColorCheckMarker.TYPEID);
		marker.setAttribute(IMarker.MESSAGE,
				"Invalid Color for Jak Refactoring: " + u.toString());
		marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO);
		marker.setAttribute(IMarker.CHAR_START, u.getStartPosition());
		marker.setAttribute(IMarker.CHAR_END, u.getStartPosition()
				+ u.getLength());
	}
}
