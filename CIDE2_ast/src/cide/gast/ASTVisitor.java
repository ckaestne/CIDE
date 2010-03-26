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

public class ASTVisitor implements IASTVisitor {

	/* (non-Javadoc)
	 * @see cide.gast.IASTVisitor#visit(cide.gast.ASTNode)
	 */
	public boolean visit(IASTNode node){
		return true;
	}
	
	/* (non-Javadoc)
	 * @see cide.gast.IASTVisitor#postVisit(cide.gast.ASTNode)
	 */
	public void postVisit(IASTNode node){
		
	}
	
}
