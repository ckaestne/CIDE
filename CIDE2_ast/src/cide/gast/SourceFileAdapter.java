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

package cide.gast;

import java.util.List;

import cide.gast.IASTNode;
import cide.gast.IASTVisitor;
import cide.gast.ISourceFile;
import cide.gast.Property;

/**
 * adapter that adapts a IASTNode to an ISourceFile; just for typing reasons.
 * 
 * @author ckaestne
 * 
 */
public class SourceFileAdapter implements ISourceFile {

	public IASTNode ast;

	public SourceFileAdapter(IASTNode ast) {
		this.ast = ast;
	}

	public void accept(IASTVisitor visitor) {
		ast.accept(visitor);
	}

	public IASTNode deepCopy() {
		return ast.deepCopy();
	}

	public String getId() {
		return ast.getId();
	}

	public int getLength() {
		return ast.getLength();
	}

	public IASTNode getParent() {
		return ast.getParent();
	}

	public Property getProperty(String name) {
		return ast.getProperty(name);
	}

	public ISourceFile getRoot() {
		return ast.getRoot();
	}

	public int getStartPosition() {
		return ast.getStartPosition();
	}

	public String getDisplayName() {

		return ast.getDisplayName();
	}

	public Property getLocationInParent() {
		return ast.getLocationInParent();
	}

	public List<Property> getProperties() {
		return ast.getProperties();
	}

	public boolean isOptional() {
		return ast.isOptional();
	}

	public void notifyPropertyChanged(Property property) {
		ast.notifyPropertyChanged(property);
	}

	public String render() {
		return ast.render();
	}

	public void setParent(IASTNode parentNode, Property parentProperty) {
		ast.setParent(parentNode, parentProperty);
	}

	public void setParentProperty(Property parentProperty) {
		ast.setParentProperty(parentProperty);
	}

	public void remove() {
		ast.remove();
	}

	public List<IASTNode> getChildren() {
		return ast.getChildren();
	}

	public void replaceSubtreeWith(IASTNode newNode) {
		ast.replaceSubtreeWith(newNode);
	}

	public void setId(String id) {
		ast.setId(id);
	}

	public IASTNode getWrappee() {
		return null;
	}

	public boolean isWrapper() {
		return false;
	}
}
