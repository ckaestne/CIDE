/**
    Copyright 2010 Christian Kästner

    This file is part of CIDE.

    CIDE is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.

    CIDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CIDE.  If not, see <http://www.gnu.org/licenses/>.

    See http://www.fosd.de/cide/ for further information.
*/

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