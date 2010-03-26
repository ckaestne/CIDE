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

package de.ovgu.cide.configuration.jdt;

import java.util.Collection;

import de.ovgu.cide.configuration.ConfigurationException;
import de.ovgu.cide.configuration.IConfigurationMechanism;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.jdt.JDTParserWrapper;

public class JDTConfigurationMechanism implements IConfigurationMechanism {

	public JDTConfigurationMechanism() {
		// TODO Auto-generated constructor stub
	}

	public boolean canConfigureFile(ColoredSourceFile file) {
		return JDTParserWrapper.isJavaFile(file.getResource());
	}

	public String configureFile(ColoredSourceFile sourceFile,
			Collection<IFeature> selectedFeatures)
			throws ConfigurationException {

		return DeleteHiddenNodesVisitor.hideCode(sourceFile, selectedFeatures);
	}

	public int getPriority() {
		return 10;
	}

}
