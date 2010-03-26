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

package de.ovgu.cide.editor.inlineprojection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.text.source.Annotation;

import de.ovgu.cide.editor.ColoredEditorExtensions.IProjectionColoredEditor;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.utils.AbstractToggleProjectionAction;

/**
 * class that initiates the update (what to show or hide) after projections have
 * been changed in some form
 * 
 * @author ckaestne
 * 
 */
public class ProjectionColorManager implements IProjectionColorManager {

	private final IProjectionColoredEditor editor;
	private AbstractToggleProjectionAction toogleProjectionAction = new AbstractToggleProjectionAction() {
		public void run() {
			updateProjectionAnnotationVisibility();
		};
	};

	public ProjectionColorManager(IProjectionColoredEditor editor) {
		this.editor = editor;
	}
	
	public boolean isProjectionActive(){
		return toogleProjectionAction.isChecked();
	}
	public AbstractToggleProjectionAction getAction(){
		return toogleProjectionAction;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seede.ovgu.cide.editor.inlineprojection.IProjectionColorManager#
	 * updateProjectionAnnotations()
	 */
	@SuppressWarnings("unchecked")
	public void updateProjectionAnnotationVisibility() {
		InlineProjectionSourceViewer viewer = (InlineProjectionSourceViewer) editor
				.getSourceViewerR();
		InlineProjectionAnnotationModel annotationModel = viewer
				.getInlineProjectionAnnotationModel();

		List<Annotation> changedAnnotations = new ArrayList<Annotation>();
		if (annotationsEnabled()) {
			// show visible
			Set<IFeature> visibleColors = editor.getFeatureModel()
					.getVisibleFeatures();
			for (Iterator iter = annotationModel.getAnnotationIterator(); iter
					.hasNext();) {
				AbstractColoredInlineProjectionAnnotation annotation = (AbstractColoredInlineProjectionAnnotation) iter
						.next();
				boolean changed = annotation.adjustCollapsing(visibleColors);
				if (changed)
					changedAnnotations.add(annotation);
			}
		} else {
			// show all
			for (Iterator iter = annotationModel.getAnnotationIterator(); iter
					.hasNext();) {
				AbstractColoredInlineProjectionAnnotation annotation = (AbstractColoredInlineProjectionAnnotation) iter
						.next();
				if (annotation.isCollapsed()) {
					annotation.markExpanded();
					changedAnnotations.add(annotation);
				}
			}
		}

		annotationModel.modifyAnnotations(null, null, changedAnnotations
				.toArray(new Annotation[changedAnnotations.size()]));
	}

	private boolean annotationsEnabled() {
		return toogleProjectionAction.isChecked();
	}

}
