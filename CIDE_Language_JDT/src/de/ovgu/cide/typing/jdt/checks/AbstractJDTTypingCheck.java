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

import cide.gast.IASTNode;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.jdt.JDTTypingProvider;
import de.ovgu.cide.typing.model.ITypingCheck;

public abstract class AbstractJDTTypingCheck implements ITypingCheck {
	protected final IASTNode source;
	protected final JDTTypingProvider typingProvider;
	protected final ColoredSourceFile file;

	public AbstractJDTTypingCheck(ColoredSourceFile file,
			JDTTypingProvider typingProvider, IASTNode source) {
		this.file = file;
		this.source = source;
		this.typingProvider = typingProvider;
	}

	public ColoredSourceFile getFile() {
		return file;
	}

	public Severity getSeverity() {
		return Severity.ERROR;
	}

	public IASTNode getSource() {
		return source;
	}

}