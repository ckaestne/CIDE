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

import org.eclipse.jdt.core.dom.IVariableBinding;

import cide.gast.IASTNode;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.jdt.JDTTypingProvider;
import de.ovgu.cide.typing.model.IEvaluationStrategy;

/**
 * checks colors between a field and references to it
 * 
 * @author ckaestne & adreilin
 * 
 */
public class FinalFieldAssignmentCheck extends AbstractJDTTypingCheck {

	private final IVariableBinding targetField;

	public FinalFieldAssignmentCheck(ColoredSourceFile file,
			JDTTypingProvider typingProvider, IASTNode source,
			IVariableBinding target) {
		super(file, typingProvider, source);
		this.targetField = target;
	}

	public boolean evaluate(IEvaluationStrategy strategy) {
		return strategy.implies(file.getFeatureModel(), typingProvider.getBindingColors()
				.getColors(targetField), file.getColorManager()
				.getColors(source));
	}

	public String getErrorMessage() {
		return "Final field " + targetField.getName()+"  is not intialized in some variants.";
	}

	public String getProblemType() {
		return "de.ovgu.cide.typing.jdt.finalfieldassignment";
	}

//	@Override
//	protected void addResolutions(
//
//		ArrayList<ITypingMarkerResolution> resolutions,
//			HashSet<IFeature> colorDiff) {
//		resolutions
//				.addAll(createChangeNodeColorResolution(
//						findCallingStatement(source), colorDiff, true,
//						"statement", 20));
//		resolutions.addAll(createChangeNodeColorResolution(
//				findCallingMethod(source), colorDiff, true, "method", 18));
//		resolutions.addAll(createChangeNodeColorResolution(
//				findCallingType(source), colorDiff, true, "type", 16));
//
//		// add resolution for target (field declaration)
//		IASTNode fieldDecl = ASTBindingFinderHelper.getFieldDecl(targetField);
//		if (fieldDecl != null)
//			resolutions.addAll(createChangeNodeColorResolution(fieldDecl,
//					colorDiff, false, "field declaration", 14));
//					
//	}
//
//	@Override
//	protected Set<IFeature> getTargetColors() {
//		return typingProvider.getBindingColors().getColors(targetField);
//	}

}
