package coloredide.features.source;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.dom.ASTNode;

import coloredide.features.ASTID;
import coloredide.features.Feature;

/**
 * serializable color manager that represents the colors of one individual .java
 * file.
 * 
 * @author cKaestner
 * 
 */
public class CompilationUnitColorManager extends AbstractColorManager implements IColorManager {

	private final IFile colorFile;

	private HashMap<ASTID, Set<Feature>> node2colors = null;

	private IColoredJavaSourceFile source;

	public CompilationUnitColorManager(IFile colorFile,
			IColoredJavaSourceFile source) {
		this.colorFile = colorFile;
		this.source = source;
		if (!colorFile.exists() || !loadColorFile())
			node2colors = new HashMap<ASTID, Set<Feature>>();
	}

	private final static long serialVersionUID = 1l;

	private boolean loadColorFile() {
		try {
			if (!colorFile.exists())
				return false;

			InputStream is = colorFile.getContents(true);
			ObjectInputStream out = new ObjectInputStream(is);
			try {
				long version = out.readLong();
				if (version != serialVersionUID)
					return false;
				node2colors = (HashMap<ASTID, Set<Feature>>) out.readObject();
				return true;
			} finally {
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private void save() {
		if (tempMode)
			return;
		if (!changed)
			return;
		if (batch != 0)
			return;

		try {
			saveColorFile(null);
			changed = false;
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see coloredide.features.source.IColorManager#saveColorFile(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public boolean saveColorFile(IProgressMonitor monitor) throws CoreException {
		try {
			if (!node2colors.isEmpty()) {
				ByteArrayOutputStream b = new ByteArrayOutputStream();
				ObjectOutputStream out = new ObjectOutputStream(b);
				out.writeLong(serialVersionUID);
				out.writeObject(node2colors);
				out.close();
				ByteArrayInputStream source = new ByteArrayInputStream(b
						.toByteArray());
				if (!colorFile.exists())
					colorFile.create(source, true, monitor);
				else
					colorFile.setContents(source, true, true, monitor);
				// System.out.println("saving color file " + colorFile);
			} else {
				if (colorFile.exists())
					colorFile.delete(true, monitor);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see coloredide.features.source.IColorManager#getOwnColors(org.eclipse.jdt.core.dom.ASTNode)
	 */
	public Set<Feature> getOwnColors(ASTNode node) {
		@SuppressWarnings("unused")
		ASTID id;
		Set<Feature> colors = node2colors.get(id = ASTID.id(node));
		// // slow, but let's try for an easier evolution between two ASTIDs
		// if (colors == null) {
		// ASTID oldid = ASTID.id_old(node);
		// colors = node2colors.get(oldid);
		// if (colors != null) {
		// node2colors.put(id, colors);
		// node2colors.remove(oldid);
		// }
		// }
		HashSet<Feature> result = new HashSet<Feature>();
		if (colors != null) {
			result.addAll(colors);
			for (Feature f : colors) {
				addRequiredFeatures(result, f);
			}
		}
		return Collections.unmodifiableSet(result);
	}

	private void addRequiredFeatures(Set<Feature> featureList, Feature f) {
		Set<Feature> requiredFeatures = f.getRequiredFeatures(source
				.getProject());
		for (Feature requiredFeature : requiredFeatures) {
			if (!featureList.contains(requiredFeature)) {
				featureList.add(requiredFeature);
				addRequiredFeatures(featureList, requiredFeature);
			}
		}
	}

	/* (non-Javadoc)
	 * @see coloredide.features.source.IColorManager#addColor(org.eclipse.jdt.core.dom.ASTNode, coloredide.features.Feature)
	 */
	public boolean addColor(ASTNode node, Feature color) {
		Set<Feature> colors = node2colors.get(ASTID.id(node));
		if (colors == null) {
			colors = new HashSet<Feature>();
			node2colors.put(ASTID.id(node), colors);
		}
		boolean success = colors.add(color);
		changed |= success;
		save();
		return success;
	}

	/* (non-Javadoc)
	 * @see coloredide.features.source.IColorManager#removeColor(org.eclipse.jdt.core.dom.ASTNode, coloredide.features.Feature)
	 */
	public boolean removeColor(ASTNode node, Feature color) {
		ASTID id = ASTID.id(node);
		Set<Feature> colors = node2colors.get(id);
		boolean success = false;
		if (colors != null) {
			success |= colors.remove(color);
			if (colors.isEmpty())
				node2colors.remove(id);
		}
		changed |= success;
		save();
		return success;
	}

	private int batch = 0;

	private boolean changed = false;

	private Stack<Boolean> changeStack = null;

	private boolean tempMode;

	/* (non-Javadoc)
	 * @see coloredide.features.source.IColorManager#beginBatch()
	 */
	public void beginBatch() {
		if (batch > 0) {
			if (changeStack == null)
				changeStack = new Stack<Boolean>();
			changeStack.push(new Boolean(changed));
		}
		batch++;
		changed = false;
	}

	/* (non-Javadoc)
	 * @see coloredide.features.source.IColorManager#endBatch()
	 */
	public void endBatch() {
		batch--;

		if (batch > 0) {
			changed = changeStack.pop().booleanValue() | changed;
		} else {
			save();
		}
	}

	/* (non-Javadoc)
	 * @see coloredide.features.source.IColorManager#clearColor(org.eclipse.jdt.core.dom.ASTNode)
	 */
	public boolean clearColor(ASTNode node) {
		ASTID id = ASTID.id(node);
		Set<Feature> colors = node2colors.get(id);
		boolean success = colors != null && colors.size() > 0;
		if (colors != null)
			node2colors.remove(id);
		changed |= success;
		save();
		return success;
	}

	/* (non-Javadoc)
	 * @see coloredide.features.source.IColorManager#hasColors()
	 */
	public boolean hasColors() {
		for (ASTID id : node2colors.keySet()) {
			Set<Feature> colors = node2colors.get(id);
			if (colors != null && !colors.isEmpty())
				return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see coloredide.features.source.IColorManager#setTemporaryMode(boolean)
	 */
	public void setTemporaryMode(boolean enable) {
		if (tempMode == true && enable == false)
			if (!loadColorFile())
				node2colors = new HashMap<ASTID, Set<Feature>>();
		this.tempMode = enable;
	}

	/* (non-Javadoc)
	 * @see coloredide.features.source.IColorManager#setColors(org.eclipse.jdt.core.dom.ASTNode, java.util.Set)
	 */
	public void setColors(ASTNode node, Set<Feature> newColors) {
		Set<Feature> previousColors = node2colors.get(ASTID.id(node));
		node2colors.put(ASTID.id(node), new HashSet<Feature>(newColors));
		boolean success = previousColors == null
				|| !previousColors.equals(newColors);
		changed |= success;
		save();
	}
	
	//<TESTING
	/* (non-Javadoc)
	 * @see coloredide.features.source.IColorManager#getSource()
	 */
	public IColoredJavaSourceFile getSource()
	{
		return source;
	}

	//>
	
	/**
	 * visibility breach for temporary file format conversion. do not use otherwise
	 * @return 
	 */
	public HashMap<ASTID, Set<Feature>> tmpnode2colors(){
		return node2colors;
	}
}
