package de.ovgu.cide.fstgen.ast;

public class FSTTerminal extends FSTNode {

	private String body;
	private String compose = "error";
	private String prefix;

	public FSTTerminal(String type, String name, String body, String prefix) {
		super(type, name);
		this.body = body;
		this.prefix = prefix;
	}

	public FSTTerminal(String type, String name, String body, String prefix,
			String compositionMechanism) {
		this(type, name, body, prefix);
		this.compose = compositionMechanism;
	}

	public String getBody() {
		return body;
	}

	public String getSpecialTokenPrefix() {
		return prefix;
	}

	public String getCompositionMechanism() {
		return compose;
	}

	@Override
	public String toString() {
		return "[T: "
				+ getType()
				+ "/"
				+ getName()
				+ " \""
				+ (prefix.length() != 0 ? prefix.replaceAll("\\s", " ")
						+ "\" \"" : "") + body.replaceAll("\\s", " ")
				+ "\" compose:" + compose + "]";
	}

	public String printFST(int indent) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < indent; i++)
			buffer.append("    ");
		buffer.append(this.toString());
		buffer.append("\n");
		return buffer.toString();
	}

	@Override
	public void accept(FSTVisitor visitor) {
		visitor.visit(this);
		visitor.postVisit(this);
	}

}
