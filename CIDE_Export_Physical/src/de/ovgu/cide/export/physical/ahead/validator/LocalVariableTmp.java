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

package de.ovgu.cide.export.physical.ahead.validator;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class LocalVariableTmp extends ASTVisitor {

	private static int counter = 0;

	private void count(MethodDeclaration method, String varName) {
		counter++;
		System.out.println("Local variable conflict: " + counter + " (in "
				+ ((TypeDeclaration) method.getParent()).getName() + "."
				+ method.getName().toString() + ":" + varName + ")");
	}

	@Override
	public boolean visit(final MethodDeclaration method) {
		
		method.accept(new ASTVisitor(){
			private Set<String> names=new HashSet<String>();
			
			@Override
			public boolean visit(VariableDeclarationFragment var) {
				String varName = var.getName().toString();
				if (!names.add(varName))
					count(method, varName);
				return true;
			}
			
		});
		
		
		return false;
	}
}
