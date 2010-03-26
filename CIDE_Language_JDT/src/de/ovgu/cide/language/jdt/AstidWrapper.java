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

package de.ovgu.cide.language.jdt;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;

import cide.gast.IASTNode;
import cide.gast.IASTVisitor;
import cide.gast.ISourceFile;
import cide.gast.Property;

/**
 * careful, this is an ugly hack in matching the JDT AST and the gCIDE AST. Both
 * have the same IDs, but usually, you need to bridge the entire thing to get
 * parents etc right. With this wrapper you can circumvent the entire bridge and
 * just pass the ID wrapped in an IASTNode object. Be careful to use this only
 * in locations where it is guaranteed that only the ID and no parent is accessed 
 * (for example in the colormanager.getOwnColor).
 * 
 * You must know what you're doing when you use this class!
 * 
 * @author ckaestne
 * 
 */
public class AstidWrapper implements IASTNode {
	private final String astid;

	public AstidWrapper(String astid) {
		this.astid = astid;
	}	
	public AstidWrapper(ASTNode node) {
		this.astid = ASTID.id(node);
	}

	public void accept(IASTVisitor visitor) {

	}

	public IASTNode deepCopy() {
		return null;
	}

	public List<IASTNode> getChildren() {
		return null;
	}

	public String getDisplayName() {
		return null;
	}

	public String getId() {
		return astid;
	}

	public int getLength() {
		return 0;
	}

	public Property getLocationInParent() {
		return null;
	}

	public IASTNode getParent() {
		return null;
	}

	public List<Property> getProperties() {
		return null;
	}

	public Property getProperty(String name) {
		return null;
	}

	public ISourceFile getRoot() {
		return null;
	}

	public int getStartPosition() {
		return 0;
	}

	public IASTNode getWrappee() {
		return null;
	}

	public boolean isOptional() {
		return false;
	}

	public boolean isWrapper() {
		return false;
	}

	public void notifyPropertyChanged(Property property) {

	}

	public void remove() {

	}

	public String render() {
		return null;
	}

	public void replaceSubtreeWith(IASTNode newNode) {

	}

	public void setId(String id) {

	}

	public void setParent(IASTNode parentNode, Property parentProperty) {

	}

	public void setParentProperty(Property parentProperty) {

	}

}