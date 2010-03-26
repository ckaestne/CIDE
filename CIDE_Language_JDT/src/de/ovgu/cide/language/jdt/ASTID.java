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

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class ASTID implements Serializable {

	private static final long serialVersionUID = 1L;

	public final String id;

	private ASTID(ASTNode node) {
		this.id = calculateId(node);
	}

	private ASTID(ASTNode node, boolean old) {
		if (old)
			this.id = calculateId_old(node);
		else
			this.id = calculateId(node);
	}

	// private ASTID(String id) {
	// this.id = id;
	// }

	private static final Map<ASTNode, ASTID> cache = Collections
			.synchronizedMap(new WeakHashMap<ASTNode, ASTID>());


	public static String id(ASTNode node) {
		ASTID id = cache.get(node);
		if (id == null) {
			id = new ASTID(node);
			cache.put(node, id);
		}
		return id.id;
	}

	public static ASTID id_old(ASTNode node) {
		return new ASTID(node, true);
	}

	private static String clean(String str) {
		str = str.replace('/', '_');
		return str.replace(':', '_');
	}

	public static String calculateId(ASTNode node) {
		if (node == null)
			return "/";
		String id = calculateId(node.getParent());

		if (node.getParent() instanceof Block) {
			Block block = (Block) node.getParent();
			int idx = block.statements().indexOf(node);
			id += "[" + idx + "]";
		}
		if (node.getParent() instanceof ArrayInitializer) {
			ArrayInitializer block = (ArrayInitializer) node.getParent();
			int idx = block.expressions().indexOf(node);
			id += "[" + idx + "]";
		}
		if (node.getLocationInParent() != null && node.getParent() != null) {
			id += ":" + node.getLocationInParent().getId();
		}
		if (node.getParent() instanceof TryStatement) {
			TryStatement trystmt = (TryStatement) node.getParent();
			if (trystmt.getBody() == node)
				id += "[body]";
		}
		// if (node.getLocationInParent() instanceof
		// ChildListPropertyDescriptor) {
		// List children = (List) node.getParent().getStructuralProperty(
		// node.getLocationInParent());
		// id += "[" + children.indexOf(node) + "]";
		// }

		id += "/" + clean(node.getClass().getSimpleName());

		if (node instanceof TypeDeclaration) {
			id += ":" + clean(((TypeDeclaration) node).getName().toString());
		}
		if (node instanceof FieldDeclaration) {
			for (Object fragment : ((FieldDeclaration) node).fragments()) {
				id += ":"
						+ clean(((VariableDeclarationFragment) fragment)
								.getName().toString());
			}
		}
		if (node instanceof Type) {
			ITypeBinding b = ((Type) node).resolveBinding();
			id += "::" + (b == null ? "null" : b.getQualifiedName());
		}
		if (node instanceof VariableDeclaration)
			id += ":"
					+ clean(((VariableDeclaration) node).getName().toString());
		if (node instanceof Name)
			id += ":" + clean(((Name) node).getFullyQualifiedName());
		if (node instanceof ImportDeclaration)
			id += ":" + clean(((ImportDeclaration) node).getName().toString());
		if (node instanceof MethodDeclaration) {
			Type rt = ((MethodDeclaration) node).getReturnType2();
			id += ":" + clean(rt == null ? "void" : rt.toString());
			id += ":" + clean(((MethodDeclaration) node).getName().toString());
			id += ":"
					+ clean(getParameterTypes(((MethodDeclaration) node)
							.parameters()));
		}

		ChildListPropertyDescriptor[] enumLists = new ChildListPropertyDescriptor[] {
				SwitchStatement.STATEMENTS_PROPERTY,
				InfixExpression.EXTENDED_OPERANDS_PROPERTY,
				TryStatement.CATCH_CLAUSES_PROPERTY,
				MethodInvocation.ARGUMENTS_PROPERTY,
				ConstructorInvocation.ARGUMENTS_PROPERTY,
				ClassInstanceCreation.ARGUMENTS_PROPERTY,
				SuperConstructorInvocation.ARGUMENTS_PROPERTY,
				SuperMethodInvocation.ARGUMENTS_PROPERTY };
		for (ChildListPropertyDescriptor prop : enumLists) {
			if (node.getLocationInParent() == prop) {
				List<?> childList = (List<?>) node.getParent()
						.getStructuralProperty(prop);
				int idx = childList.indexOf(node);
				id += "[" + idx + "]";
			}
		}

		if (node instanceof CompilationUnit) {
			// IJavaElement je = ((CompilationUnit) node).getJavaElement();
			CompilationUnit cu = ((CompilationUnit) node);
			id += "[";
			if (cu.getPackage() != null && cu.getPackage().getName() != null)
				id += clean(cu.getPackage().getName().getFullyQualifiedName()
						+ ".");
			if (cu.types() != null
					&& cu.types().size() >= 1
					&& ((AbstractTypeDeclaration) cu.types().get(0)).getName() != null)
				id += ((AbstractTypeDeclaration) cu.types().get(0)).getName()
						.getFullyQualifiedName();
			id += "]";
			// id += "[" + clean(je.getPath().toPortableString()) + "]";
		}
		return id;
	}

	private static String getParameterTypes(List<?> list) {
		String result = "";
		for (Object p : list) {
			if (p instanceof SingleVariableDeclaration) {
				Type t = ((SingleVariableDeclaration) p).getType();
				if (t != null)
					result += t.toString() + ";";
			}
		}
		if (result.length() > 0)
			result = result.substring(0, result.length() - 1);

		return "[" + result + "]";
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof ASTID))
			return false;
		ASTID otherIdentifier = ((ASTID) obj);
		return id.equals(otherIdentifier.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	public static String calculateId_old(ASTNode node) {
		if (node == null)
			return "/";
		String id = calculateId(node.getParent());

		if (node.getParent() instanceof Block) {
			Block block = (Block) node.getParent();
			int idx = block.statements().indexOf(node);
			id += "[" + idx + "]";
		}
		if (node.getLocationInParent() != null && node.getParent() != null) {
			id += ":" + node.getLocationInParent().getId();
		}
		if (node.getParent() instanceof TryStatement) {
			TryStatement trystmt = (TryStatement) node.getParent();
			if (trystmt.getBody() == node)
				id += "[body]";
		}
		// if (node.getLocationInParent() instanceof
		// ChildListPropertyDescriptor) {
		// List children = (List) node.getParent().getStructuralProperty(
		// node.getLocationInParent());
		// id += "[" + children.indexOf(node) + "]";
		// }

		id += "/" + clean(node.getClass().getSimpleName());

		if (node instanceof TypeDeclaration) {
			id += ":" + clean(((TypeDeclaration) node).getName().toString());
		}
		if (node instanceof FieldDeclaration) {
			for (Object fragment : ((FieldDeclaration) node).fragments()) {
				id += ":"
						+ clean(((VariableDeclarationFragment) fragment)
								.getName().toString());
			}
		}
		if (node instanceof Type) {
			ITypeBinding b = ((Type) node).resolveBinding();
			id += "::" + (b == null ? "null" : b.getQualifiedName());
		}
		if (node instanceof VariableDeclaration)
			id += ":"
					+ clean(((VariableDeclaration) node).getName().toString());
		if (node instanceof Name)
			id += ":" + clean(((Name) node).getFullyQualifiedName());
		if (node instanceof ImportDeclaration)
			id += ":" + clean(((ImportDeclaration) node).getName().toString());
		if (node instanceof MethodDeclaration) {
			Type rt = ((MethodDeclaration) node).getReturnType2();
			id += ":" + clean(rt == null ? "void" : rt.toString());
			id += ":" + clean(((MethodDeclaration) node).getName().toString());
			id += ":"
					+ clean(getParameterTypes(((MethodDeclaration) node)
							.parameters()));
		}

		ChildListPropertyDescriptor[] enumLists = new ChildListPropertyDescriptor[] {
				SwitchStatement.STATEMENTS_PROPERTY,
				InfixExpression.EXTENDED_OPERANDS_PROPERTY,
				TryStatement.CATCH_CLAUSES_PROPERTY };
		for (ChildListPropertyDescriptor prop : enumLists) {
			if (node.getLocationInParent() == prop) {
				List<?> childList = (List<?>) node.getParent()
						.getStructuralProperty(prop);
				int idx = childList.indexOf(node);
				id += "[" + idx + "]";
			}
		}

		if (node instanceof CompilationUnit) {
			// IJavaElement je = ((CompilationUnit) node).getJavaElement();
			CompilationUnit cu = ((CompilationUnit) node);
			id += "[";
			if (cu.getPackage() != null && cu.getPackage().getName() != null)
				id += clean(cu.getPackage().getName().getFullyQualifiedName()
						+ ".");
			if (cu.types() != null
					&& cu.types().size() >= 1
					&& ((AbstractTypeDeclaration) cu.types().get(0)).getName() != null)
				id += ((AbstractTypeDeclaration) cu.types().get(0)).getName()
						.getFullyQualifiedName();
			id += "]";
			// id += "[" + clean(je.getPath().toPortableString()) + "]";
		}
		return id;
	}

}
