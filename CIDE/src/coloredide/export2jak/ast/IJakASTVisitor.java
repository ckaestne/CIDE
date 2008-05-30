package coloredide.export2jak.ast;

public interface IJakASTVisitor {

	public boolean visit(JakClassRefinement node);

	public void endVisit(JakClassRefinement node);

	public boolean visit(JakCompilationUnit node);

	public void endVisit(JakCompilationUnit node);

}
