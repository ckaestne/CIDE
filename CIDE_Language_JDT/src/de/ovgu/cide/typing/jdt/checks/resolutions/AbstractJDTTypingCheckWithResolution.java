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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IMarker;

import cide.gast.IASTNode;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.jdt.UnifiedASTNode;
import de.ovgu.cide.language.jdt.UnifiedASTNode.Kind;
import de.ovgu.cide.typing.jdt.JDTTypingProvider;
import de.ovgu.cide.typing.jdt.checks.AbstractJDTTypingCheck;
import de.ovgu.cide.typing.model.ITypingCheckWithResolution;
import de.ovgu.cide.typing.model.ITypingMarkerResolution;

public abstract class AbstractJDTTypingCheckWithResolution extends
		AbstractJDTTypingCheck implements ITypingCheckWithResolution {

	public AbstractJDTTypingCheckWithResolution(ColoredSourceFile file,
			JDTTypingProvider typingProvider, IASTNode source) {
		super(file, typingProvider, source);
	}

	public ITypingMarkerResolution[] getResolutions(IMarker marker) {
		Set<IFeature> declColors = getTargetColors();
		if (declColors == null) {
			return NO_RESOLUTIONS;
		}

		Set<IFeature> callColors = file.getColorManager().getColors(source);

		HashSet<IFeature> colorDiff = new HashSet<IFeature>();
		colorDiff.addAll(declColors);
		colorDiff.removeAll(callColors);
		if (colorDiff.isEmpty())
			return NO_RESOLUTIONS;

		ArrayList<ITypingMarkerResolution> resolutions = new ArrayList<ITypingMarkerResolution>();
		addResolutions(resolutions, colorDiff);
		Collections.sort(resolutions);
		return resolutions.toArray(new ITypingMarkerResolution[resolutions
				.size()]);
	}

	protected abstract void addResolutions(
			ArrayList<ITypingMarkerResolution> resolutions,
			HashSet<IFeature> colorDiff);

	protected abstract Set<IFeature> getTargetColors();

	protected static IASTNode findCallingType(IASTNode node) {
		while (node != null
				&& !(node instanceof UnifiedASTNode && ((UnifiedASTNode) node)
						.getKind() == Kind.TYPE))
			node = node.getParent();
		return node;
	}

	protected static IASTNode findCallingMethod(IASTNode node) {
		while (node != null
				&& !(node instanceof UnifiedASTNode && ((UnifiedASTNode) node)
						.getKind() == Kind.METHOD))
			node = node.getParent();
		return node;
	}

	protected static IASTNode findCallingStatement(IASTNode node) {
		while (node != null
				&& !(node instanceof UnifiedASTNode && ((UnifiedASTNode) node)
						.getKind() == Kind.STATEMENT))
			node = node.getParent();
		return node;
	}

	protected Collection<ITypingMarkerResolution> createChangeNodeColorResolution(
			IASTNode node, HashSet<IFeature> colorDiff, boolean add,
			String nodeType, int relevance) {

		if (node == null)
			return Collections.EMPTY_SET;
		ArrayList<ITypingMarkerResolution> resolutions = new ArrayList<ITypingMarkerResolution>();

		if (add) {
			if (colorDiff.size() > 1)
				for (IFeature color : colorDiff) {

					resolutions.add(new ChangeNodeColorResolution(file, node,
							Collections.singleton(color), add, nodeType,
							relevance));
				}
			resolutions.add(new ChangeNodeColorResolution(file, node,
					colorDiff, add, nodeType, relevance + 1));
		}

		if (!add) {
			// find declaring node
			for (IFeature color : colorDiff) {
				IASTNode currentNode = node;
				while (currentNode != null
						&& !file.getColorManager().hasColor(currentNode, color)) {
					currentNode = currentNode.getParent();
				}

				if (currentNode != null)
					if (file.getColorManager().hasColor(currentNode, color))
						resolutions.add(new ChangeNodeColorResolution(file,
								currentNode, Collections.singleton(color), add,
								nodeType, relevance));
			}
		}

		return resolutions;
	}
}
