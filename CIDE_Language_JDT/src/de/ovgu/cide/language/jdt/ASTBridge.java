package de.ovgu.cide.language.jdt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.ChildPropertyDescriptor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimplePropertyDescriptor;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;

import cide.gast.ASTNode;
import cide.gast.ASTTextNode;
import cide.gast.ASTVisitor;
import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gast.PropertyZeroOrMore;
import cide.gast.PropertyZeroOrOne;
import cide.gast.SimpleToken;
import de.ovgu.cide.configuration.jdt.ASTColorInheritance;

/**
 * input: Eclipse AST; output: CIDE AST
 * 
 * this is not an on the fly adapter, but it copies the entire data structure.
 * (as no way back to the eclipse ast is needed this is ok) only the pretty
 * printer must be written from scratch.
 * 
 * e_ stands of eclipse object; c_ stands for CIDE object
 * 
 * 
 * @author ckaestne
 * 
 */
public class ASTBridge {

	public IASTNode getAST(CompilationUnit e_root) {
		return bridgeASTNode(e_root);
	}

	/**
	 * to bridge a single node from the outside the root is still required to
	 * get the parents right. note, unless resolveAllSiblings is true, the
	 * returned structure is not complete. parent nodes have only a single
	 * property required to get here. only their ids are preserved to enable
	 * color lookups, but not navigation
	 * 
	 * @param e_root
	 * @param e_node
	 * @return
	 */
	public IASTNode getASTNode(CompilationUnit e_root,
			org.eclipse.jdt.core.dom.ASTNode e_node, boolean resolveAllSiblings) {
		// fake inheritance by adding fake parent nodes with correct ID but
		// without other children
		if (!resolveAllSiblings) {
			ASTNode result = bridgeASTNode(e_node);
			bridgeFakeParentNodes(result, e_node);
			return result;
		}
		// bridge the entire AST but retrieve the correct node
		ASTNode c_root = bridgeASTNode(e_root);
		BridgedASTFinder astFinder = new BridgedASTFinder(ASTID.id(e_node));
		c_root.accept(astFinder);
		return astFinder.node;
	}

	private static class BridgedASTFinder extends ASTVisitor {
		BridgedASTFinder(String id) {
			this.id = id;
		}

		final String id;
		IASTNode node = null;

		@Override
		public boolean visit(IASTNode node) {
			if (node.getId().equals(id)) {
				this.node = node;
				return false;
			}
			return super.visit(node);
		}
	}

	private void bridgeFakeParentNodes(ASTNode c_node,
			org.eclipse.jdt.core.dom.ASTNode e_node) {
		while (e_node.getParent() != null) {
			org.eclipse.jdt.core.dom.ASTNode e_parent = e_node.getParent();

			IToken c_firstToken = new PosToken(e_parent.getStartPosition());
			IToken c_lastToken = new PosToken(e_parent.getStartPosition()
					+ e_node.getLength());

			StructuralPropertyDescriptor e_prop = e_node.getLocationInParent();
			List<Property> c_props = new ArrayList<Property>();

			// fake a property with a single child
			c_props.add(new PropertyZeroOrOne<ASTNode>(e_prop.getId(), c_node));

			c_node = new UnifiedASTNode(getDisplayName(e_parent), ASTID
					.id(e_parent), c_props, c_firstToken, c_lastToken, null);

			e_node = e_parent;
		}

	}

	private ASTNode bridgeASTNode(org.eclipse.jdt.core.dom.ASTNode e_node) {
		IToken c_firstToken = new PosToken(e_node.getStartPosition());
		IToken c_lastToken = new PosToken(e_node.getStartPosition()
				+ e_node.getLength());

		IASTNode[] wrappee = null;
		List<StructuralPropertyDescriptor> e_props = e_node
				.structuralPropertiesForType();
		List<Property> c_props = new ArrayList<Property>();
		for (StructuralPropertyDescriptor e_prop : e_props) {
			if (e_prop.isSimpleProperty()) {
				Property prop = bridgeSimpleProperty(e_node,
						(SimplePropertyDescriptor) e_prop);
				if (ASTColorInheritance.notInheritedProperties.contains(e_prop))
					wrappee = join(wrappee, prop.getChildren());
				c_props.add(prop);
			} else if (e_prop.isChildListProperty()) {
				Property prop = bridgeChildListProperty(e_node,
						(ChildListPropertyDescriptor) e_prop);
				if (ASTColorInheritance.notInheritedProperties.contains(e_prop))
					wrappee = join(wrappee, prop.getChildren());
				c_props.add(prop);
			} else if (e_prop.isChildProperty()) {
				Property prop = bridgeChildProperty(e_node,
						(ChildPropertyDescriptor) e_prop);
				if (ASTColorInheritance.notInheritedProperties.contains(e_prop))
					wrappee = join(wrappee, prop.getChildren());
				c_props.add(prop);
			}

		}
		return new UnifiedASTNode(getDisplayName(e_node), ASTID.id(e_node),
				c_props, c_firstToken, c_lastToken, wrappee);
	}

	/** helper method to concatenate two arrays */
	private IASTNode[] join(IASTNode[] existing, IASTNode[] newarray) {
		if (existing == null || existing.length == 0)
			return newarray;
		if (newarray == null || newarray.length == 0)
			return existing;

		ArrayList<IASTNode> result = new ArrayList<IASTNode>(existing.length
				+ newarray.length);
		result.addAll(Arrays.asList(existing));
		result.addAll(Arrays.asList(newarray));
		return result.toArray(new IASTNode[existing.length + newarray.length]);
	}

	private Property bridgeChildProperty(
			org.eclipse.jdt.core.dom.ASTNode e_node,
			ChildPropertyDescriptor prop) {
		if (prop.isMandatory())
			return bridgeOneChildProperty(e_node, prop);
		else
			return bridgeOptionalChildProperty(e_node, prop);
	}

	private Property bridgeOneChildProperty(
			org.eclipse.jdt.core.dom.ASTNode e_node,
			StructuralPropertyDescriptor prop) {
		Object o = e_node.getStructuralProperty(prop);
		ASTNode child;
		assert o != null;
		if (o instanceof org.eclipse.jdt.core.dom.ASTNode)
			child = bridgeASTNode((org.eclipse.jdt.core.dom.ASTNode) o);
		else
			child = new ASTTextNode(o.toString(), new SimpleToken(e_node
					.getStartPosition(), e_node.getLength()));

		return new PropertyOne<ASTNode>(prop.getId(), child);
	}

	private Property bridgeOptionalChildProperty(
			org.eclipse.jdt.core.dom.ASTNode e_node,
			StructuralPropertyDescriptor prop) {
		Object o = e_node.getStructuralProperty(prop);
		ASTNode child;
		if (o == null)
			child = null;
		else if (o instanceof org.eclipse.jdt.core.dom.ASTNode)
			child = bridgeASTNode((org.eclipse.jdt.core.dom.ASTNode) o);
		else
			child = new ASTTextNode(o.toString(), new SimpleToken(e_node
					.getStartPosition(), e_node.getLength()));
		return new PropertyZeroOrOne<ASTNode>(prop.getId(), child);

	}

	private Property bridgeChildListProperty(
			org.eclipse.jdt.core.dom.ASTNode e_node,
			ChildListPropertyDescriptor prop) {
		Object e_object = e_node.getStructuralProperty(prop);
		assert e_object instanceof List;
		List<?> e_childList = (List<?>) e_object;
		ArrayList<ASTNode> c_children = new ArrayList<ASTNode>();
		for (Object e_child : e_childList) {
			assert e_child instanceof org.eclipse.jdt.core.dom.ASTNode;
			c_children
					.add(bridgeASTNode((org.eclipse.jdt.core.dom.ASTNode) e_child));
		}

		return new PropertyZeroOrMore<ASTNode>(prop.getId(), c_children);
	}

	private String getDisplayName(org.eclipse.jdt.core.dom.ASTNode node) {
		return org.eclipse.jdt.core.dom.ASTNode.nodeClassForType(
				node.getNodeType()).getSimpleName();
	}

	private Property bridgeSimpleProperty(
			org.eclipse.jdt.core.dom.ASTNode e_node,
			SimplePropertyDescriptor prop) {
		if (prop.isMandatory())
			return bridgeOneChildProperty(e_node, prop);
		else
			return bridgeOptionalChildProperty(e_node, prop);
	}
}
