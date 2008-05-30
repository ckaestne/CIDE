package coloredide.export2jak;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;

import coloredide.features.Feature;
import coloredide.features.source.AbstractColorManager;
import coloredide.features.source.IColorManager;
import coloredide.features.source.IColoredJavaSourceFile;

/**
 * this colormanager replaces the traditional colormanager during refactoring.
 * 
 * this colormanager works on the identity of AST objects instead on IDs, to the
 * colors remain when moving elements!
 * 
 * all changes to this colormanager are not permanently.
 * 
 * the colormanager is initialized with another colormanager and the root of the
 * AST at hand to copy all values
 * 
 * @author cKaestner
 * 
 */
public class RefactoringColorManager extends AbstractColorManager {

	private final HashMap<ASTNode, Set<Feature>> storage = new HashMap<ASTNode, Set<Feature>>();

	RefactoringColorManager(IColorManager colorManager, ASTNode root) {
		initialize(colorManager, root);
	}

	private void initialize(final IColorManager oldColorManager, ASTNode root) {
		root.accept(new ASTVisitor() {
			@Override
			public void preVisit(ASTNode node) {
				Set<Feature> colors = oldColorManager.getOwnColors(node);
				if (!colors.isEmpty())
					storage.put(node, colors);
				super.preVisit(node);
			}
		});
	}

	public void beginBatch() {
		throw new UnsupportedOperationException();
	}

	public void endBatch() {
		throw new UnsupportedOperationException();
	}

	public Set<Feature> getOwnColors(ASTNode node) {
		Set<Feature> c = storage.get(node);
		if (c != null)
			return c;
		return new HashSet<Feature>();
	}

	public IColoredJavaSourceFile getSource() {
		throw new UnsupportedOperationException();
	}

	public boolean hasColors() {
		return storage.size() > 0;
	}

	public void setColors(ASTNode node, Set<Feature> newColors) {
		if (newColors != null && !newColors.isEmpty())
			storage.put(node, newColors);
		else
			storage.remove(node);

	}

	public void setTemporaryMode(boolean enable) {
		throw new UnsupportedOperationException();
	}

}
