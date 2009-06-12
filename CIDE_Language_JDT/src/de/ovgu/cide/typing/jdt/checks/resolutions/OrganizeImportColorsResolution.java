package de.ovgu.cide.typing.jdt.checks.resolutions;


import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;

import cide.gparser.ParseException;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.jdt.BindingProjectColorCache;
import de.ovgu.cide.typing.jdt.organizeimports.OrganizeAllImportsJob;
import de.ovgu.cide.typing.model.AbstractTypingMarkerResolution;

/**
 * sets the colors of all import statements. needs to parse the JDT ast and then
 * assign colors to every bridged import statement
 * 
 * @author ckaestne
 * 
 */
public class OrganizeImportColorsResolution extends
		AbstractTypingMarkerResolution {

	protected final ColoredSourceFile source;

	private final BindingProjectColorCache bindingProjectColorCache;

	public OrganizeImportColorsResolution(ColoredSourceFile source,
			BindingProjectColorCache bindingProjectColorCache) {
		this.source = source;
		this.bindingProjectColorCache = bindingProjectColorCache;
	}

	public void run(IMarker marker) {
		try {
			OrganizeAllImportsJob.organizeImports(source, bindingProjectColorCache);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	public String getLabel() {
		return "Organize Features on Imports";
	}

	public String getDescription() {
		return "Sets the feature annotations on every import statement "
				+ "to the features of the imported type's declaration.";
	}

	public Image getImage() {
		return null;
	}

}
