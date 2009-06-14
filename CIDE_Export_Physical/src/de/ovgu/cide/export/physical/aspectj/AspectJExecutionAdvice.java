package de.ovgu.cide.export.physical.aspectj;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class AspectJExecutionAdvice// extends MethodDeclaration
{
	public AspectJExecutionAdvice(Block adviceBody,
			TypeDeclaration thisPointer,
			List<SingleVariableDeclaration> parameters, Type returnType,
			SimpleName methodName) {
		this.adviceBody = adviceBody;
		pointcut = new LinkedList<Pointcut>();
		Pointcut pc = new Pointcut(thisPointer, parameters, returnType,
				methodName);
		pointcut.add(pc);
	}

	private List<Pointcut> pointcut;

	private Block adviceBody;

	public Block getAdviceBody() {
		return adviceBody;
	}

	public void setAdviceBody(Block adviceBody) {
		this.adviceBody = adviceBody;
	}

	boolean isCompatible(AspectJExecutionAdvice otherAdvice) {
		if (adviceBody.toString()
				.equals(otherAdvice.getAdviceBody().toString())
				&& getPointcut().get(0).getThisPointer().equals(
						otherAdvice.getPointcut().get(0).getThisPointer()))
			return true;
		return false;
	}

	public List<Pointcut> getPointcut() {
		return pointcut;
	}

	public void setPointcut(List<Pointcut> pointcut) {
		this.pointcut = pointcut;
	}
}
