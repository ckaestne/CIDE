package de.ovgu.cide.typing.internal;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;

import de.ovgu.cide.typing.model.ITypingCheck;

public class TypingMarkerFactory {
	public final static String MARKER_TYPE_ID = "de.ovgu.cide.core.typing.problem";

	public static final String PARAM_PROBLEMTYPE = "de.ovgu.cide.core.typing.problem.problemtype";
	public static final String PARAM_PROBLEMDATA = "de.ovgu.cide.core.typing.problem.problemdata";

	private final static String[] ATTRIBUTE_NAMES = { IMarker.MESSAGE,
			IMarker.SEVERITY, IMarker.CHAR_START, IMarker.CHAR_END, PARAM_PROBLEMTYPE, PARAM_PROBLEMDATA };

	public IMarker createErrorMarker(ITypingCheck check) throws CoreException {
		assert null != check.getFile();
		IMarker marker = check.getFile().getResource().createMarker(
				MARKER_TYPE_ID);
		assert marker != null;

		updateErrorMarker(marker, check);
		return marker;
	}

	public void updateErrorMarker(IMarker marker, ITypingCheck check)
			throws CoreException {
		marker.setAttributes(ATTRIBUTE_NAMES, new Object[] {
				check.getErrorMessage(), getSeverity(check),
				getStartPosition(check), getEndPosition(check),
				check.getProblemType(), getNodeId(check) });
	}

	private Integer getStartPosition(ITypingCheck check) {
		return check.getSource().getStartPosition();
	}

	private Integer getEndPosition(ITypingCheck check) {
		return check.getSource().getStartPosition() + check.getSource().getLength();
	}
	
	private Integer getSeverity(ITypingCheck check) {
		switch (check.getSeverity()) {
		case ERROR:
			return IMarker.SEVERITY_ERROR;
		case WARNING:
			return IMarker.SEVERITY_WARNING;
		default:
			return IMarker.SEVERITY_INFO;
		}
	}

	private String getNodeId(ITypingCheck check) {
		return check.getSource().getId();
	}
}
