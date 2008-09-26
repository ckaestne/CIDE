package de.ovgu.cide.language.jdt;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.ChildPropertyDescriptor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimplePropertyDescriptor;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;

import cide.gast.ASTNode;
import cide.gast.ASTTextNode;
import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gast.PropertyZeroOrMore;
import cide.gast.PropertyZeroOrOne;
import cide.gast.SimpleToken;

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

	protected ASTNode bridgeASTNode(org.eclipse.jdt.core.dom.ASTNode e_node) {
		IToken c_firstToken = new PosToken(e_node.getStartPosition());
		IToken c_lastToken = new PosToken(e_node.getStartPosition()
				+ e_node.getLength());

		List<StructuralPropertyDescriptor> e_props = e_node
				.structuralPropertiesForType();
		List<Property> c_props = new ArrayList<Property>();
		for (StructuralPropertyDescriptor e_prop : e_props) {
			if (e_prop.isSimpleProperty())
				c_props.add(bridgeSimpleProperty(e_node,
						(SimplePropertyDescriptor) e_prop));
			else if (e_prop.isChildListProperty())
				c_props.add(bridgeChildListProperty(e_node,
						(ChildListPropertyDescriptor) e_prop));
			else if (e_prop.isChildProperty())
				c_props.add(bridgeChildProperty(e_node,
						(ChildPropertyDescriptor) e_prop));
		}
		return new UnifiedASTNode(getDisplayName(e_node),ASTID.id(e_node), c_props,
				c_firstToken, c_lastToken);
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
