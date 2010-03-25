/**
 * 
 */
package coloredide.export;

import org.eclipse.jdt.core.dom.Type;

public class Formal implements Comparable<Formal> {
	private int orderNr;

	public Formal(Type type, String identifier, int orderNr) {
		this.name = identifier;
		this.type = type;
		this.orderNr = orderNr;
	}

	public Type type;

	public String name;

	public int compareTo(Formal o) {
		if (orderNr < o.orderNr)
			return -1;
		if (orderNr > o.orderNr)
			return 1;
		return 0;
	}

	@Override
	public String toString() {
		return (type != null ? type.toString() : "void") + " " + name;
	}
}