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

package de.ovgu.cide.export.physical.ahead.ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.TypeParameter;

import de.ovgu.cide.export.physical.internal.IPhysicalOptions;

public class JakClassRefinement {

	public String javadoc;

	private final List<Modifier> modifiers = new ArrayList<Modifier>();

	private SimpleName name;

	private final List<Type> superInterfaceTypes = new ArrayList<Type>();
	private Type superType;

	private boolean isInterface;

	private final List<TypeParameter> typeParameters = new ArrayList<TypeParameter>();

	private final List<FieldDeclaration> fields = new ArrayList<FieldDeclaration>();

	private final List<MethodDeclaration> introductions = new ArrayList<MethodDeclaration>();

	private final List<MethodDeclaration> refinements = new ArrayList<MethodDeclaration>();

	private final List<BodyDeclaration> otherMembers = new ArrayList<BodyDeclaration>();

	private final HashMap<String, JakClassRefinement> innerClassRefinements = new HashMap<String, JakClassRefinement>();

	private AST ast;

	private JakCompilationUnit parent;

	private IPhysicalOptions options;

	public void initializeFromType(TypeDeclaration type) {
		modifiers.clear();
		modifiers.addAll(ASTNode.copySubtrees(ast, type.modifiers()));
		isInterface = type.isInterface();
		superInterfaceTypes.clear();
		superType = null;
		// superInterfaceTypes.addAll(ASTNode.copySubtrees(ast, type
		// .superInterfaceTypes()));
		typeParameters.clear();
		typeParameters.addAll(ASTNode.copySubtrees(ast, type.typeParameters()));
		name = (SimpleName) ASTNode.copySubtree(ast, type.getName());
	}

	JakClassRefinement(JakCompilationUnit compUnit, AST ast,
			IPhysicalOptions options) {
		this.ast = ast;
		this.parent = compUnit;
		this.options = options;
	}

	public JakClassRefinement(JakCompilationUnit compUnit, AST ast,
			TypeDeclaration template, IPhysicalOptions options) {
		this(compUnit, ast, options);
		initializeFromType(template);
	}

	/*
	 * (omit javadoc for this method) Method declared on ASTNode.
	 */
	JakClassRefinement clone(AST target) {
		JakClassRefinement result = new JakClassRefinement(parent, target,
				options);
		// result.setSourceRange(this.getStartPosition(), this.getLength());
		result.javadoc = (this.javadoc);
		result.name = ast.newSimpleName(name.getIdentifier());
		result.isInterface = isInterface;
		result.modifiers.addAll(ASTNode.copySubtrees(target, modifiers));
		result.typeParameters().addAll(
				ASTNode.copySubtrees(target, typeParameters()));
		result.superInterfaceTypes().addAll(
				ASTNode.copySubtrees(target, superInterfaceTypes()));
		result.superType = (Type) ASTNode.copySubtree(target, result.superType);
		result.refinements.addAll(ASTNode.copySubtrees(target, refinements));
		result.introductions
				.addAll(ASTNode.copySubtrees(target, introductions));
		result.fields.addAll(ASTNode.copySubtrees(target, fields));
		return result;
	}

	protected void addRefinement(MethodDeclaration member) {
		refinements.add(member);
	}

	public void addIntroduction(MethodDeclaration member) {
		introductions.add(member);
	}

	/*
	 * (omit javadoc for this method) Method declared on ASTNode.
	 */
	public void accept(ASTVisitor visitor) {
		if (visitor instanceof IJakASTVisitor) {
			boolean visitChildren = ((IJakASTVisitor) visitor).visit(this);
			if (visitChildren) {
				// visit children in normal left to right reading order
				// acceptChild(visitor, getJavadoc());
				for (ASTNode n : modifiers)
					n.accept(visitor);
				name.accept(visitor);
				for (ASTNode n : typeParameters)
					n.accept(visitor);
				for (ASTNode n : superInterfaceTypes)
					n.accept(visitor);
				for (ASTNode n : fields)
					n.accept(visitor);
				for (ASTNode n : introductions)
					n.accept(visitor);
				for (ASTNode n : refinements)
					n.accept(visitor);
				if (!options.getMethodObjectsInStaticToplevelClass())
					for (JakClassRefinement n : innerClassRefinements.values())
						n.accept(visitor);
			}
			((IJakASTVisitor) visitor).endVisit(this);
		}
	}

	public List<?> typeParameters() {
		return this.typeParameters;
	}

	public List<?> superInterfaceTypes() {
		return this.superInterfaceTypes;
	}

	public void addField(FieldDeclaration field) {
		fields.add(field);
	}

	public List<IExtendedModifier> modifiers() {
		return (List<IExtendedModifier>) (List<?>) modifiers;//WTF?
	}

	public SimpleName getName() {
		return name;
	}

	public List<?> bodyDeclarations() {
		List<Object> result = new ArrayList<Object>();
		result.addAll(fields);
		result.addAll(introductions);
		result.addAll(refinements);
		result.addAll(otherMembers);
		return result;
	}

	protected void setName(SimpleName name2) {
		name = name2;
	}

	public void moveAllMembers(TypeDeclaration type) {

		for (Iterator<BodyDeclaration> iter = type.bodyDeclarations()
				.iterator(); iter.hasNext();) {
			BodyDeclaration bd = iter.next();
			iter.remove();
			// type.bodyDeclarations().remove(bd);
			if (bd instanceof MethodDeclaration)
				addIntroduction((MethodDeclaration) bd);
			else if (bd instanceof FieldDeclaration)
				addField((FieldDeclaration) bd);
			else
				addOtherMember(bd);
		}
	}

	public void addOtherMember(BodyDeclaration bd) {
		otherMembers.add(bd);
	}

	public List<BodyDeclaration> getOtherMembers() {
		return otherMembers;
	}

	public List<MethodDeclaration> refinements() {
		return refinements;
	}

	public List<MethodDeclaration> introductions() {
		return introductions;
	}

	public void setSuperclassType(Type superType) {
		this.superType = superType;
	}

	public void addSuperInterfaceType(Type implementsType) {
		superInterfaceTypes.add(implementsType);
	}

	public Type getSuperclassType() {
		return superType;
	}

	public boolean isEmpty() {
		if (bodyDeclarations().size() > 0)
			return false;
		if (superType != null)
			return false;
		if (superInterfaceTypes.size() > 0)
			return false;
		if (options.isDebugPrintInnerClassRefinements()
				|| !options.getMethodObjectsInStaticToplevelClass()
				&& innerClassRefinements.size() > 0)
			return false;
		if (parent.imports().size() > 0)
			return false;
		return true;
	}

	/**
	 * inner-class refinements are created implicitly by using the get function.
	 * There is no need to create them explicitly.
	 * 
	 * Inner class refinements are supported by neither AHEAD nor FSTComposer,
	 * therefore they are not actually printed, but just used as temporary
	 * constructs
	 * 
	 * @return
	 */
	public JakClassRefinement getInnerClassRefinement(TypeDeclaration original) {
		JakClassRefinement ref = innerClassRefinements.get(original.getName()
				.getIdentifier());
		if (ref == null) {
			ref = new JakClassRefinement(parent, ast, original, options);
			innerClassRefinements.put(original.getName().getIdentifier(), ref);
		}
		return ref;
	}

	public List<JakClassRefinement> getInnerClassRefinements() {
		ArrayList<JakClassRefinement> result = new ArrayList<JakClassRefinement>();
		for (JakClassRefinement ref : innerClassRefinements.values()) {
			if (!ref.isEmpty())
				result.add(ref);
		}
		return result;
	}

	/**
	 * adds a refinement for a given original method. if the method belongs to
	 * an inner class the method is added to the according inner class
	 * refinement
	 * 
	 * @param method
	 * @param copiedMethod
	 */
	public void addRefinementForMethod(MethodDeclaration originalMethod,
			MethodDeclaration refinement) {

		assert originalMethod.getParent() instanceof TypeDeclaration;
		// toplevel class
		if (originalMethod.getParent().getParent() instanceof CompilationUnit)
			this.addRefinement(refinement);
		else {// inner class
			assert originalMethod.getParent().getParent() instanceof TypeDeclaration;
			assert originalMethod.getParent().getParent().getParent() instanceof CompilationUnit;
			TypeDeclaration innerClass = (TypeDeclaration) originalMethod
					.getParent();
			JakClassRefinement innerClassRefinement = getInnerClassRefinement(innerClass);
			innerClassRefinement.addRefinement(refinement);
		}

	}

	/**
	 * adds a refinement for a given original method. if the method belongs to
	 * an inner class the method is added to the according inner class
	 * refinement
	 * 
	 * @param method
	 * @param copiedMethod
	 */
	public void addMethodIntroductionForType(TypeDeclaration originalType,
			MethodDeclaration refinement) {

		// toplevel class
		if (originalType.getParent() instanceof CompilationUnit)
			this.addRefinement(refinement);
		else {// inner class
			assert originalType.getParent() instanceof TypeDeclaration;
			assert originalType.getParent().getParent() instanceof CompilationUnit;
			JakClassRefinement innerClassRefinement = getInnerClassRefinement(originalType);
			innerClassRefinement.addRefinement(refinement);
		}

	}

	/**
	 * adds a refinement for a given original method. if the method belongs to
	 * an inner class the method is added to the according inner class
	 * refinement
	 * 
	 * @param method
	 * @param copiedMethod
	 */
	public void addFieldIntroductionForType(TypeDeclaration originalType,
			FieldDeclaration field) {

		// toplevel class
		if (originalType.getParent() instanceof CompilationUnit)
			this.addField(field);
		else {// inner class
			assert originalType.getParent() instanceof TypeDeclaration;
			assert originalType.getParent().getParent() instanceof CompilationUnit;
			JakClassRefinement innerClassRefinement = getInnerClassRefinement(originalType);
			innerClassRefinement.addField(field);
		}

	}

	public boolean isInterface() {
		return isInterface;
	}
}
