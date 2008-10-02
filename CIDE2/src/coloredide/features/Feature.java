package coloredide.features;

import java.io.IOException;
import java.io.Serializable;

import org.eclipse.swt.graphics.RGB;

/**
 * deprecated class, just needed to load legacy color files. do not use or
 * reference
 * 
 * @author ckaestne
 * @deprecated
 */
public class Feature implements Serializable {

	private static final long serialVersionUID = 1L;

	long id;
	@SuppressWarnings("unused")
	private RGB rgb;

	public long getId() {
		return id;
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.writeLong(id);
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		id = in.readLong();
	}

}
