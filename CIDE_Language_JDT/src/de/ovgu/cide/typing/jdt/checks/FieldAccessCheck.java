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

package de.ovgu.cide.typing.jdt.checks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.IVariableBinding;

import cide.gast.IASTNode;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.jdt.JDTTypingProvider;
import de.ovgu.cide.typing.jdt.checks.resolutions.ASTBindingFinderHelper;
import de.ovgu.cide.typing.jdt.checks.resolutions.AbstractJDTTypingCheckWithResolution;
import de.ovgu.cide.typing.model.IEvaluationStrategy;
import de.ovgu.cide.typing.model.ITypingMarkerResolution;

/**
 * checks colors between a field and references to it
 * 
 * @author ckaestne
 * 
 */
public class FieldAccessCheck extends AbstractJDTTypingCheckWithResolution {

	private final IVariableBinding targetField;

	public FieldAccessCheck(ColoredSourceFile file,
			JDTTypingProvider typingProvider, IASTNode source,
			IVariableBinding target) {
		super(file, typingProvider, source);
		this.targetField = target;
	}

	public boolean evaluate(IEvaluationStrategy strategy) {
		return strategy.implies(file.getFeatureModel(), file.getColorManager()
				.getColors(source), typingProvider.getBindingColors()
				.getColors(targetField));
	}

	public String getErrorMessage() {
		return "Access to field which is not present in some variants: "
				+ targetField.getName();
	}

	public String getProblemType() {
		return "de.ovgu.cide.typing.jdt.fieldaccess";
	}

	@Override
	protected void addResolutions(
			ArrayList<ITypingMarkerResolution> resolutions,
			HashSet<IFeature> colorDiff) {
		resolutions
				.addAll(createChangeNodeColorResolution(
						findCallingStatement(source), colorDiff, true,
						"statement", 20));
		resolutions.addAll(createChangeNodeColorResolution(
				findCallingMethod(source), colorDiff, true, "method", 18));
		resolutions.addAll(createChangeNodeColorResolution(
				findCallingType(source), colorDiff, true, "type", 16));

		// add resolution for target (field declaration)
		IASTNode fieldDecl = ASTBindingFinderHelper.getFieldDecl(targetField);
		if (fieldDecl != null)
			resolutions.addAll(createChangeNodeColorResolution(fieldDecl,
					colorDiff, false, "field declaration", 14));
	}

	@Override
	protected Set<IFeature> getTargetColors() {
		return typingProvider.getBindingColors().getColors(targetField);
	}

}
