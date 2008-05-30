package coloredide.export2AspectJ;

import java.util.List;

import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class Pointcut {
	TypeDeclaration thisPointer;

	List<SingleVariableDeclaration> parameters;

	Type returnType;

	SimpleName methodName;

	public Pointcut(TypeDeclaration thisPointer2,
			List<SingleVariableDeclaration> parameters2, Type returnType2,
			SimpleName methodName2) {
		thisPointer = thisPointer2;
		parameters = parameters2;
		returnType = returnType2;
		methodName = methodName2;
	}

	public SimpleName getMethodName() {
		return methodName;
	}

	public void setMethodName(SimpleName methodName) {
		this.methodName = methodName;
	}

	public List<SingleVariableDeclaration> getParameters() {
		return parameters;
	}

	public void setParameters(List<SingleVariableDeclaration> parameters) {
		this.parameters = parameters;
	}

	public Type getReturnType() {
		return returnType;
	}

	public void setReturnType(Type returnType) {
		this.returnType = returnType;
	}

	public TypeDeclaration getThisPointer() {
		return thisPointer;
	}

	public void setThisPointer(TypeDeclaration thisPointer) {
		this.thisPointer = thisPointer;
	}
}
