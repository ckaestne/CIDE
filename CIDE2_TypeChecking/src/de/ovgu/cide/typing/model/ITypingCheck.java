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

package de.ovgu.cide.typing.model;

import cide.gast.IASTNode;
import de.ovgu.cide.features.source.ColoredSourceFile;

public interface ITypingCheck {
	enum Severity {
		ERROR, WARNING, INFO
	};

	/**
	 * severity to be shown on the eclipse marker
	 */
	Severity getSeverity();

	/**
	 * message to be shown on the eclipse marker
	 */
	String getErrorMessage();

	/**
	 * internal type of the eclipse marker (attribute of the marker)
	 */
	String getProblemType();

	/**
	 * location where the error is shown (file and AST node)
	 * 
	 * @return
	 */
	IASTNode getSource();

	ColoredSourceFile getFile();

	/**
	 * returns whether this typing check is ok (well-typed).
	 * 
	 * this method uses the evaluation strategy to perform the actual type check
	 * (a implies b etc). The evaluation strategy is responsible for looking up
	 * feature annotations on AST nodes and for caching results for other
	 * checks.
	 * 
	 * 
	 * @param strategy
	 *            evaluation strategy provided by CIDE or its feature model
	 * @return true for well-typed, false for typing error
	 */
	boolean evaluate(IEvaluationStrategy strategy);

	@Override
	boolean equals(Object obj);

}
