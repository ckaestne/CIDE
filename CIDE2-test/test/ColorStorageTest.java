import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.ovgu.cide.features.Feature;

public class ColorStorageTest {

	private static class MyFeature {

	}

	private HashMap<String, Set<Feature>> id2colors;

	@Before
	public void setUp() throws Exception {
	}

	@Test@Ignore
	public void testLoadOldColorFile() throws Exception {
		InputStream is = new FileInputStream("test/.dircolors");
		ObjectInputStream out = new ObjectInputStream(is);
		try {
			long version = out.readLong();
			if (version == 1l)
				id2colors = loadLegacy(out);
			// id2colors = (HashMap<String, Set<MyFeature>>) out.readObject();
		} finally {
			out.close();
		}
		// DirectoryColorManager.getColoredDirectoryManager(workspace.getProject("test"));
	}

	private HashMap<String, Set<Feature>> loadLegacy(ObjectInputStream out)
			throws Exception {
		HashMap<String, Set<Feature>> id2colors = (HashMap<String, Set<Feature>>) out
				.readObject();
		System.out.println(id2colors);
		return null;
	}

}
