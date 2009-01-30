/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package de.ovgu.cide.astview;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import cide.gast.ASTVisitor;
import cide.gast.IASTNode;
import cide.gast.Property;

public class ASTViewContentProvider implements ITreeContentProvider {

	public boolean isFilterNonOptional = false;

	public static class RootCapsle {
		private IASTNode node;

		public RootCapsle(IASTNode node) {
			this.node = node;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface
	 * .viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java
	 * .lang.Object)
	 */
	public Object[] getElements(Object parent) {
		if (parent instanceof RootCapsle) {
			return new Object[] { ((RootCapsle) parent).node };
		}
		return getChildren(parent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object
	 * )
	 */
	public Object getParent(Object child) {
		if (isFilterNonOptional && (child instanceof IASTNode)) {
			IASTNode parent = ((IASTNode) child).getParent();
			while (parent != null && !parent.isOptional())
				parent = parent.getParent();
			return parent;
		}

		if (child instanceof IASTNode) {
			IASTNode node = (IASTNode) child;
			IASTNode parent = node.getParent();
			if (parent != null) {
				return node.getLocationInParent();
			}
		} else if (child instanceof Property) {
			return ((Property) child).getNode();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.
	 * Object)
	 */
	public Object[] getChildren(Object parent) {
		if (isFilterNonOptional && parent instanceof IASTNode)
			return getNonOptionalChildren((IASTNode) parent);

		if (parent instanceof Property) {
			return ((Property) parent).getChildren();
		} else if (parent instanceof IASTNode) {
			return ((IASTNode) parent).getProperties().toArray();
		}
		return new Object[0];
	}

	private Object[] getNonOptionalChildren(final IASTNode parent) {
		final List<IASTNode> children = new ArrayList<IASTNode>();
		parent.accept(new ASTVisitor() {
			@Override
			public boolean visit(IASTNode node) {
				if (node!=parent && node.isOptional()) {
					children.add(node);
					return false;
				}
				return true;
			}
		});

		return children.toArray();
	}

	public boolean hasChildren(Object parent) {
		return getChildren(parent).length > 0;
	}
}
