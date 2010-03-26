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
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.IMethodBinding;

import cide.gast.IASTNode;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.jdt.BindingProjectColorCache;
import de.ovgu.cide.typing.jdt.JDTTypingProvider;
import de.ovgu.cide.typing.jdt.checks.resolutions.ASTBindingFinderHelper;
import de.ovgu.cide.typing.jdt.checks.resolutions.AbstractJDTTypingCheckWithResolution;
import de.ovgu.cide.typing.jdt.checks.util.CheckUtils;
import de.ovgu.cide.typing.jdt.checks.util.MethodPathItem;
import de.ovgu.cide.typing.model.IEvaluationStrategy;
import de.ovgu.cide.typing.model.ITypingMarkerResolution;

/**
 * checks colors between a field and references to it
 * 
 * @author ckaestne & adreilin
 * 
 */
public class MethodInvocationCheck extends AbstractJDTTypingCheckWithResolution {

	// TODO CHECK RESOLUTION

	private final IMethodBinding targetMethod;

	private final List<IASTNode> arguments;

	public MethodInvocationCheck(ColoredSourceFile file,
			JDTTypingProvider typingProvider, IASTNode source,
			List<IASTNode> args, IMethodBinding target) {
		super(file, typingProvider, source);
		this.arguments = args;
		this.targetMethod = target;
	}

	private boolean checkSourceAndTargetCondition(IEvaluationStrategy strategy,
			IMethodBinding targetBinding) {

		Set<IFeature> sourceMethodColors = file.getColorManager().getColors(
				source);
		Set<IFeature> targetMethodColors = typingProvider.getBindingColors()
				.getColors(targetBinding);
		if (!strategy.implies(file.getFeatureModel(), sourceMethodColors,
				targetMethodColors))
			return false;

		// check each parameter same condition
		Set<IFeature> context = new HashSet<IFeature>();
		context.addAll(sourceMethodColors);
		context.addAll(targetMethodColors);
		for (int j = 0; j < arguments.size(); j++) {

			if (strategy.equal(file.getFeatureModel(), context, file
					.getColorManager().getColors(arguments.get(j)), file
					.getColorManager().getColors(source)))
				continue;

			// check the default case
			if (strategy.equal(file.getFeatureModel(), context, file
					.getColorManager().getColors(arguments.get(j)),
					typingProvider.getBindingColors().getColors(
							BindingProjectColorCache.getParamKey(targetBinding
									.getKey(), j))))
				continue;

			return false;

		}

		return true;
	}

	public boolean evaluate(IEvaluationStrategy strategy) {

		// check the whole method default case
		if (checkSourceAndTargetCondition(strategy, targetMethod))
			return true;

		// checks if target method overrides other methods for which condition
		// is true
		List<MethodPathItem> inherMethods = new ArrayList<MethodPathItem>();

		// get overridden method keys
		CheckUtils.collectOverriddenMethodKeysInSuperClasses(targetMethod,
				inherMethods);

		// checks "OR" condition for all found keys
		for (MethodPathItem tmpItem : inherMethods) {

			// checks for each overridden method the implies condition
			if (checkSourceAndTargetCondition(strategy, tmpItem.getBinding()))
				return true;
		}
		System.out.println("");

		return false;

	}

	public String getErrorMessage() {
		return "Invoking method which is not present in some variants: "
				+ targetMethod.getName();
	}

	public String getProblemType() {
		return "de.ovgu.cide.typing.jdt.methodinvocationname";
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
		IASTNode methodDecl = ASTBindingFinderHelper
				.getMethodDecl(targetMethod);
		if (methodDecl != null)
			resolutions.addAll(createChangeNodeColorResolution(methodDecl,
					colorDiff, false, "method declaration", 14));
	}

	@Override
	protected Set<IFeature> getTargetColors() {
		return typingProvider.getBindingColors().getColors(targetMethod);
	}

}
