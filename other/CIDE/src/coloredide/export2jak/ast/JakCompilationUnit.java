package coloredide.export2jak.ast;

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

import coloredide.export2jak.FSTComposerJavaPrettyPrinter;
import coloredide.export2jak.JakExportOptions;
import coloredide.export2jak.JakPrettyPrinter;
import coloredide.export2jak.JakExportOptions.OutputType;
import coloredide.features.Feature;
import coloredide.features.source.IColorManager;
import coloredide.utils.CopiedNaiveASTFlattener;

public class JakCompilationUnit {

	private String layer;

	private AST ast;

	private JakClassRefinement refinement;

	public PackageDeclaration package_;

	public final List<ImportDeclaration> imports = new ArrayList<ImportDeclaration>();

	public void initializeFromCompUnit(CompilationUnit node) {
		imports.clear();
		// imports.addAll(ASTNode.copySubtrees(ast, node.imports()));
		package_ = (PackageDeclaration) ASTNode.copySubtree(ast, node
				.getPackage());
	}

	public void adjustImports(CompilationUnit orig, Set<Feature> colors,
			IColorManager colorManager) {

		Iterator<ImportDeclaration> importIterator = orig.imports().iterator();

		while (importIterator.hasNext()) {
			ImportDeclaration imp = importIterator.next();
			if (colorManager.getColors(imp).equals(colors)) {
				importIterator.remove();
				imports.add(imp);
			}
		}

	}

	public JakCompilationUnit(AST ast, CompilationUnit template, String layer) {
		this.ast = ast;
		this.layer = layer;
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
		refinement = new JakClassRefinement(this, ast);

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
		CopiedNaiveASTFlattener pp;
		if (JakExportOptions.OUTPUTTYPE == OutputType.FST_JAVA)
			pp = new FSTComposerJavaPrettyPrinter();
		else
			pp = new JakPrettyPrinter();
		this.acceptInner(innerClass, pp);
		return pp.getResult();
	}

	public String getFilename() {
		return getFilename(getRefinement());
	}

	public String getFilename(JakClassRefinement innerClass) {
		return innerClass.getName().getIdentifier() + ".jak";
	}

	public PackageDeclaration getPackage() {
		return package_;
	}

	public List<ImportDeclaration> imports() {
		return imports;
	}

}
