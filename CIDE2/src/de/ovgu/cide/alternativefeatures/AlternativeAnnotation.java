package de.ovgu.cide.alternativefeatures;

import org.eclipse.jface.text.source.Annotation;
import org.eclipse.swt.graphics.Image;

import de.ovgu.cide.ColoredIDEImages;

/**
 * NOTE: the entire functionality for alternative features was implemented as part of a master's thesis
 * (available in German here: http://wwwiti.cs.uni-magdeburg.de/~ckaestne/thesisrosenthal.pdf)
 * the functionality has been deactivated mostly, but the code is still included.

 * Annotation (not coloring, but a marker on the vertical ruler) that indicates the existance of alternative code-fragments.
 * 
 * @author Malte Rosenthal
 */
public class AlternativeAnnotation extends Annotation {
	public static String ALTERNATIVE_TYPE = "alternative.type";
	
	public AlternativeAnnotation(String text) {
		super(ALTERNATIVE_TYPE, false, text);
	}
	
	public Image getImage() {
        return ColoredIDEImages.getImage("sample.gif");
    }

    public int getLayer() {
        return 3;
    }
    
    public String getType() {
        return ALTERNATIVE_TYPE;
    }
}