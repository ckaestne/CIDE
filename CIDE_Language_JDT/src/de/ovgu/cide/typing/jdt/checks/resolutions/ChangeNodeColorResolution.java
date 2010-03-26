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

package de.ovgu.cide.typing.jdt.checks.resolutions;

import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.swt.graphics.Image;

import cide.gast.IASTNode;
import de.ovgu.cide.ASTColorChangedEvent;
import de.ovgu.cide.CIDECorePlugin;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.SourceFileColorManager;
import de.ovgu.cide.typing.model.AbstractTypingMarkerResolution;

public class ChangeNodeColorResolution extends AbstractTypingMarkerResolution {

	private final Set<IFeature> colorDiff;

	private boolean add;

	private IASTNode targetNode;

	private String title = null;

	private String description = null;

	protected ColoredSourceFile source;

	private String nodeType = null;


	public ChangeNodeColorResolution(ColoredSourceFile source,
			IASTNode targetNode, Set<IFeature> colorDiff, boolean add) {
		this.targetNode = targetNode;
		this.colorDiff = colorDiff;
		this.add = add;
		this.source = source;
	}

	public ChangeNodeColorResolution(ColoredSourceFile source,
			IASTNode targetNode, Set<IFeature> colorDiff, boolean add,
			String title, String desc) {
		this(source, targetNode, colorDiff, add);
		this.title = title;
		this.description = desc;
	}

	public ChangeNodeColorResolution(ColoredSourceFile source,
			IASTNode targetNode, Set<IFeature> colorDiff, boolean add,
			String nodeType, int relevance) {
		this(source, targetNode, colorDiff, add);
		this.nodeType = nodeType;
		setRelevance(relevance);
	}

	public void run(IMarker marker) {
		nodeColors().beginBatch();
		for (IFeature color : colorDiff) {
			if (add)
				nodeColors().addColor(targetNode, color);
			else
				nodeColors().removeColor(targetNode, color);
		}
		nodeColors().endBatch();
		CIDECorePlugin.getDefault().notifyListeners(
				new ASTColorChangedEvent(this, targetNode, source));
	}

	protected SourceFileColorManager nodeColors() {
		return source.getColorManager();
	}

	public String getLabel() {
		if (title != null)
			return title;
		return generateTitle();
	}

	private String generateTitle() {
		String t = add ? "Add" : "Remove";
		t += " feature";
		t += colorDiff.size() > 1 ? "s " : " ";
		t += add ? "to " : "from ";
		t += nodeType == null ? "node" : nodeType;
		t += ": ";
		for (Iterator<IFeature> iterator = colorDiff.iterator(); iterator
				.hasNext();) {
			t += iterator.next().getName() + (iterator.hasNext() ? ", " : "");
		}
		return t + ".";
	}

	public String getDescription() {
		return description;
	}

	public Image getImage() {
		return null;
	}



}
