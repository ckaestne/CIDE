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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;

import cide.gast.IASTNode;

/**
 * NOTE: the entire functionality for alternative features was implemented as part of a master's thesis
 * (available in German here: http://wwwiti.cs.uni-magdeburg.de/~ckaestne/thesisrosenthal.pdf)
 * the functionality has been deactivated mostly, but the code is still included.
 * 
 * Manager for annotations (not coloring, but a marker on the vertical ruler) that indicate the existance of alternative code-fragments.
 * 
 * @author Malte Rosenthal
 */
public class AlternativeAnnotationManager {

	private IAnnotationModel annotationModel;
	private List<String> annotatedNodes;
	
	public AlternativeAnnotationManager(IAnnotationModel annotationModel) {
		this.annotationModel = annotationModel;
		annotatedNodes = new LinkedList<String>();
	}
	
	public void setAnnotations(List<IASTNode> nodes) {
		if ((nodes != null) && !nodes.isEmpty()) {
			for (IASTNode node : nodes) {
				if (node != null)
					addAnnotation(node);
			}
		}
	}
	
	private void addAnnotation(IASTNode node) {
		if (!annotatedNodes.contains(node.getId())) {
			AlternativeAnnotation annotation = new AlternativeAnnotation(node.getId());
			annotationModel.addAnnotation(annotation, new Position(node.getStartPosition(), node.getLength()));
			annotatedNodes.add(node.getId());
		}
	}
	
	public void removeAnnotations() {
		List<Annotation> annotationList = getAnnotationList();
		
		for (Annotation annotation : annotationList) {
			annotation.markDeleted(true);
			annotationModel.removeAnnotation(annotation);
		}
		
		annotatedNodes.clear();
	}
	
	@SuppressWarnings("unchecked")
	private List<Annotation> getAnnotationList() {
		List<Annotation> annotationList = new LinkedList<Annotation>();
		if (annotationModel == null)
			return annotationList;
		
		Iterator iterator = annotationModel.getAnnotationIterator();
		while (iterator.hasNext()) {
			annotationList.add((Annotation) iterator.next());
		}
		
		return annotationList;
	}
}
