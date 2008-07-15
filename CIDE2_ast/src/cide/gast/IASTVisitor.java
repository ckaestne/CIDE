package cide.gast;

public interface IASTVisitor {

	public abstract boolean visit(IASTNode node);

	public abstract void postVisit(IASTNode node);

}