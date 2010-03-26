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

package de.ovgu.cide.importjak.featurehouseextension;

import java.util.HashMap;

import org.prop4j.Node;

import de.ovgu.cide.fstgen.ast.FSTNode;
import de.ovgu.cide.fstgen.ast.FSTNonTerminal;

/**
 * hack to associate existing fstnodes with feature expressions.
 * 
 * 
 * @author ckaestne
 * 
 *         TODO move to import plugin
 */
public class FeatureExpressions {

	private static FeatureExpressions instance = new FeatureExpressions();

	static FeatureExpressions getInstance() {
		return instance;
	}

	private final HashMap<FSTNode, Node> map = new HashMap<FSTNode, Node>();

	public static void set(FSTNode node, Node expr) {
		getInstance().setExpression(node, expr);
	}

	public void setExpression(FSTNode node, Node expr) {
		map.put(node, expr);
	}

	public static Node get(FSTNode node) {
		return getInstance().getExpression(node);
	}

	public Node getExpression(FSTNode node) {
		return map.get(node);
	}

	public static void cloneFE(FSTNode target, FSTNode source) {
		set(target, get(source));
		if (source instanceof FSTNonTerminal) {
			for (int i = 0; i < ((FSTNonTerminal) source).getChildren().size(); i++) {
				cloneFE(((FSTNonTerminal) target).getChildren().get(i),
						((FSTNonTerminal) source).getChildren().get(i));
			}
		}
	}

}
