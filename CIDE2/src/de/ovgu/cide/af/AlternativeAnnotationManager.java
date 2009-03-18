package de.ovgu.cide.af;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;

import cide.gast.IASTNode;

/**
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
				addAnnotation(node);
			}
		}
	}
	
	public void addAnnotation(IASTNode node) {
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
