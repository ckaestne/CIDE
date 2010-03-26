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
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;

import cide.gast.IASTNode;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.jdt.JDTTypingProvider;
import de.ovgu.cide.typing.jdt.checks.resolutions.AbstractJDTTypingCheckWithResolution;
import de.ovgu.cide.typing.jdt.checks.resolutions.OrganizeImportColorsResolution;
import de.ovgu.cide.typing.model.IEvaluationStrategy;
import de.ovgu.cide.typing.model.ITypingMarkerResolution;

/**
 * checks colors between an import statement and the type it imports
 * 
 * @author ckaestne
 * 
 */
public class ImportTargetCheck extends AbstractJDTTypingCheckWithResolution {

	private final IBinding targetBinding;

	public ImportTargetCheck(ColoredSourceFile file,
			JDTTypingProvider typingProvider, IASTNode source, IBinding binding) {
		super(file, typingProvider, source);
		this.targetBinding = binding;
	}

	public boolean evaluate(IEvaluationStrategy strategy) {
		Set<IFeature> importColors = file.getColorManager().getColors(source);

		Set<IFeature> targetColors = getTargetColor();
		return strategy.implies(file.getFeatureModel(), importColors,
				targetColors);
	}

	private Set<IFeature> getTargetColor() {
		Set<IFeature> targetColors = Collections.emptySet();
		if (targetBinding instanceof ITypeBinding) {
			targetColors = typingProvider.getBindingColors().getColors(
					(ITypeBinding) targetBinding);
		}
		if (targetBinding instanceof IMethodBinding) {
			targetColors = typingProvider.getBindingColors().getColors(
					(IMethodBinding) targetBinding);
		}
		if (targetBinding instanceof IVariableBinding) {
			targetColors = typingProvider.getBindingColors().getColors(
					(IVariableBinding) targetBinding);
		}
		return targetColors;
	}

	public String getErrorMessage() {
		return "Import of type which is not present in some variants: "
				+ targetBinding.getName();
	}

	public String getProblemType() {
		return "de.ovgu.cide.typing.jdt.importtarget";
	}

	@Override
	protected void addResolutions(
			ArrayList<ITypingMarkerResolution> resolutions,
			HashSet<IFeature> colorDiff) {
		resolutions.add(new OrganizeImportColorsResolution(file, typingProvider
				.getBindingColors()));
	}

	@Override
	protected Set<IFeature> getTargetColors() {
		return getTargetColor();
	}

}
