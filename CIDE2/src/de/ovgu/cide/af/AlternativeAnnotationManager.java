package de.ovgu.cide.af;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;

import cide.gast.IASTNode;

public class AlternativeAnnotationManager {

	private IAnnotationModel annotationModel;
	private List<String> annotatedNodes;
	
	public AlternativeAnnotationManager(IAnnotationModel annotationModel) {
		this.annotationModel = annotationModel;
		annotatedNodes = new LinkedList<String>();
	}
	
	public void setAnnotations(Map<IASTNode, List<Alternative>> node2alternatives) {
		removeAnnotations();
		
		for (Entry<IASTNode, List<Alternative>> entry : node2alternatives.entrySet()) {
			if ((entry.getValue() != null) && (entry.getValue().size() > 1))
				addAnnotation(entry.getKey());
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
