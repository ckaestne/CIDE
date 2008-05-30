/**
 * 
 */
package coloredide.validator;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

import cide.greferences.IValidationRule;

@SuppressWarnings("restriction")
public class ColorProblem {

	public static final String PARAM_PROBLEMTYPE = "coloride.problemtype";
	public static final String PARAM_PROBLEMDATA = "coloride.problemdata";

	private String nodeId;
	private String message;
	private int severity;
	private int startPosition;
	private int endPosition;
	private int line;
	private int colum;
	private IValidationRule brokenRule;

	public ColorProblem(IResource resource, String message, int severity,
			int startPosition, int endPosition, int line, int column,
			IValidationRule brokenRule, String nodeId) {
		this.resource = resource;
		this.nodeId = nodeId;

		this.message = message;
		this.severity = severity;
		this.startPosition = startPosition;
		this.endPosition = endPosition;
		this.line = line;
		this.colum = column;
		this.brokenRule = brokenRule;
	}

	public String getMarkerType() {
		return TYPEID;
	}

	public final static String TYPEID = "de.ovgu.cide.core.problem";

	public IResource resource;

	public String getNodeId() {
		return nodeId;
	}

	private final static String[] ATTRIBUTE_NAMES = { IMarker.MESSAGE,
			IMarker.SEVERITY, IMarker.CHAR_START, IMarker.CHAR_END,
			IMarker.LINE_NUMBER, PARAM_PROBLEMTYPE, PARAM_PROBLEMDATA };

	public void setMarkerAttributes(IMarker marker) throws CoreException {
		marker.setAttributes(ColorProblem.ATTRIBUTE_NAMES, new Object[] {
				message, severity, startPosition, endPosition + 1, line,
				brokenRule.getName(), nodeId });
	}

}