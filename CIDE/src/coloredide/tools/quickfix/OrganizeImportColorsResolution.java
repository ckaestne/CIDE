package coloredide.tools.quickfix;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolution2;

import coloredide.ColorChangedEvent;
import coloredide.ColoredIDEPlugin;
import coloredide.features.Feature;
import coloredide.features.bindings.BindingColorCache;
import coloredide.features.source.IColorManager;
import coloredide.features.source.IColoredJavaSourceFile;

@SuppressWarnings("restriction")
public class OrganizeImportColorsResolution implements IMarkerResolution,
		IMarkerResolution2 {

	protected IColoredJavaSourceFile source;

	private int rel = 10;

	public OrganizeImportColorsResolution(IColoredJavaSourceFile source) {
		this.source = source;
	}

	public void run(IMarker marker) {
		nodeColors().beginBatch();
		try {

			try {
				for (Object i : source.getAST().imports()) {
					if (i instanceof ImportDeclaration) {
						ImportDeclaration imp = (ImportDeclaration) i;
						IBinding binding = imp.resolveBinding();

						Set<Feature> impColors = nodeColors().getOwnColors(imp);
						Set<Feature> targetColors = new HashSet<Feature>();
						if (binding instanceof ITypeBinding) {
							targetColors = javaElementColors().getColors(
									source.getProject(), (ITypeBinding) binding);
						}

						if (targetColors.size() != impColors.size()
								|| !targetColors.containsAll(impColors)) {
							for (Feature color : targetColors) {
								if (!impColors.contains(color))
									nodeColors().addColor(imp, color);
							}
							for (Feature color : impColors) {
								if (!targetColors.contains(color))
									nodeColors().removeColor(imp, color);
							}
							ColoredIDEPlugin.getDefault().notifyListeners(
									new ColorChangedEvent(this, imp, source));
						}
					}

				}
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} finally {
			nodeColors().endBatch();
		}
	}

	protected IColorManager nodeColors() {
		return source.getColorManager();
	}

	protected BindingColorCache javaElementColors() {
		return ColoredIDEPlugin.getDefault().colorCache;
	}

	public String getLabel() {
		return "Organize Import Colors";
	}


	public String getDescription() {
		return null;
	}

	public Image getImage() {
		return null;
	}

	public void setRelevance(int rel) {
		this.rel = rel;
	}

	public int getRelevance() {
		return rel;
	}
}
