/**
 * 
 */
package coloredide.validator;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IJavaModelMarker;
import org.eclipse.jdt.internal.compiler.problem.DefaultProblem;

@SuppressWarnings("restriction")
public class ColorProblem extends DefaultProblem {
	
	public static class Kind {
		private String name;
		private int id;
		Kind(String name, int id){this.name=name;this.id=id;}
		public String toString() {return "Kind: "+name;}
		public int getID(){return id;}
	}
	public final static Kind METHODCALL=new Kind("Method Call",1);
	public final static Kind FIELDACCESS=new Kind("Field Access",2);
	public final static Kind IMPORT=new Kind("Field Access",3);
	public final static Kind TYPE=new Kind("Type Missmatch",4);
	public final static Kind OTHER=new Kind("other",0);
	public static final String PARAM_PROBLEMTYPE="coloride.problemtype";
	public static final String PARAM_PROBLEMDATA="coloride.problemdata";
	public static Kind[] getKinds() {
		return new Kind[]{METHODCALL,FIELDACCESS,IMPORT,OTHER};
	}
	
	private Kind kind;
	private String data;
	
	
	public ColorProblem(IResource resource, String message, int id,
			String[] stringArguments, int severity, int startPosition,
			int endPosition, int line,int column, Kind kind, String data) {
		super(resource.getName().toCharArray(), message, id, stringArguments,
				severity, startPosition, endPosition, line, column);
		this.resource = resource;
		this.kind=kind;
		this.data=data;
	}

	public String getMarkerType() {
		return TYPEID;
	}

	public final static String TYPEID = "coloride.problem";

	public IResource resource;

	public final static String[] ATTRIBUTE_NAMES = { IMarker.MESSAGE,
			IMarker.SEVERITY, IJavaModelMarker.ID, IMarker.CHAR_START,
			IMarker.CHAR_END, IMarker.LINE_NUMBER, IJavaModelMarker.ARGUMENTS,
			IJavaModelMarker.CATEGORY_ID, PARAM_PROBLEMTYPE,PARAM_PROBLEMDATA};


	public String getData() {
		return data;
	}
	public Kind getKind() {
		return kind;
	}


}