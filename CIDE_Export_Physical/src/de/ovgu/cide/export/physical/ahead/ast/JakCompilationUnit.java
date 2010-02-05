package de.ovgu.cide.export.physical.ahead.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;

import de.ovgu.cide.export.CopiedNaiveASTFlattener;
import de.ovgu.cide.export.physical.internal.IPhysicalOptions;
import de.ovgu.cide.export.physical.internal.RefactoringColorManager;
import de.ovgu.cide.features.IFeature;

public class JakCompilationUnit {

	private String layer;

	private AST ast;

	private JakClassRefinement refinement;

	public PackageDeclaration package_;

	public final List<ImportDeclaration> imports = new ArrayList<ImportDeclaration>();

	private IPhysicalOptions options;

	public void initializeFromCompUnit(CompilationUnit node) {
		imports.clear();
		// imports.addAll(ASTNode.copySubtrees(ast, node.imports()));
		package_ = (PackageDeclaration) ASTNode.copySubtree(ast, node
				.getPackage());
	}

	public void adjustImports(CompilationUnit orig, Set<IFeature> colors,
			RefactoringColorManager colorManager) {

		Iterator<ImportDeclaration> importIterator = orig.imports().iterator();

		while (importIterator.hasNext()) {
			ImportDeclaration imp = importIterator.next();
			if (colorManager.getColors(imp).equals(colors)) {
				importIterator.remove();
				imports.add(imp);
			}
		}

	}

	public JakCompilationUnit(AST ast, CompilationUnit template, String layer,
			IPhysicalOptions options) {
		this.ast = ast;
		this.layer = layer;
		this.options = options;
		initializeFromCompUnit(template);
	}

	public String getLayer() {
		if (layer == null)
			return "";
		return layer;
	}

	public void setRefinement(JakClassRefinement node) {
		refinement = node;
	}

	public JakClassRefinement getRefinement() {

		if (refinement != null)
			return refinement;
		refinement = new JakClassRefinement(this, ast, options);

		return refinement;
	}

	public void accept(ASTVisitor visitor) {
		acceptInner(refinement, visitor);
	}

	private void acceptInner(JakClassRefinement innerClass, ASTVisitor visitor) {
		assert visitor instanceof IJakASTVisitor;
		if (visitor instanceof IJakASTVisitor) {
			boolean visitChildren = ((IJakASTVisitor) visitor).visit(this);
			if (visitChildren) {
				// visit children in normal left to right reading order
				if (package_ != null)
					getPackage().accept(visitor);
				for (ImportDeclaration import_ : (List<ImportDeclaration>) this
						.imports())
					import_.accept(visitor);
				if (innerClass != null)
					innerClass.accept(visitor);
			}
			((IJakASTVisitor) visitor).endVisit(this);
		}
	}

	public String getSource() {
		return getSource(getRefinement());
	}

	public String getSource(JakClassRefinement innerClass) {
		if (innerClass.isEmpty())
			return "";
		CopiedNaiveASTFlattener pp = options.getPrettyPrinter();
		this.acceptInner(innerClass, pp);
		return pp.getResult();
	}

//	public String getFilename() {
//		return getFilename(getRefinement());
//	}

	public String getInnerClassName(JakClassRefinement innerClass) {
		return innerClass.getName().getIdentifier();
	}

	public PackageDeclaration getPackage() {
		return package_;
	}

	public List<ImportDeclaration> imports() {
		return imports;
	}

}
