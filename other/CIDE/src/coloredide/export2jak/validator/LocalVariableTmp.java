package coloredide.export2jak.validator;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class LocalVariableTmp extends ASTVisitor {

	private static int counter = 0;

	private void count(MethodDeclaration method, String varName) {
		counter++;
		System.out.println("Local variable conflict: " + counter + " (in "
				+ ((TypeDeclaration) method.getParent()).getName() + "."
				+ method.getName().toString() + ":" + varName + ")");
	}

	@Override
	public boolean visit(final MethodDeclaration method) {
		
		method.accept(new ASTVisitor(){
			private Set<String> names=new HashSet<String>();
			
			@Override
			public boolean visit(VariableDeclarationFragment var) {
				String varName = var.getName().toString();
				if (!names.add(varName))
					count(method, varName);
				return true;
			}
			
		});
		
		
		return false;
	}
}
