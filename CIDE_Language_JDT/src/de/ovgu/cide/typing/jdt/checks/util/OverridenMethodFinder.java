package de.ovgu.cide.typing.jdt.checks.util;


import java.util.HashSet;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import cide.gparser.ParseException;
import de.ovgu.cide.language.jdt.JDTParserWrapper;


public class OverridenMethodFinder extends ASTVisitor {

	public static void findAllOverriddenMethodKeys(IMethodBinding methodBinding, List<String> keys) {
		try {
			
			if (methodBinding == null)
				return;
			
			IMethod method = getIMethod(methodBinding);
			
			//checks if method exists and is not part of a binary file
			if (method == null || method.isBinary())
				return; 
			
			IType type = method.getDeclaringType();
			ITypeHierarchy hierarchy = type.newSupertypeHierarchy(null);
			
			//get the super class
			
			while ((type = hierarchy.getSuperclass(type)) != null) {
				
				//get the ast for this super class
				CompilationUnit ast = getAST(type);
					
				if (ast == null)
					return;
	
				//try to find a method in this super class which is overriden by current "method"
				OverridenMethodFinder bindingFinder = new OverridenMethodFinder(methodBinding);
				ast.accept(bindingFinder);
					
				IMethodBinding result = bindingFinder.getResult();
				
				//check if a overriden method has been found
				if (result != null)  			
					//method overrides tmpMethod, add the tmpMethod - key accordingly	
					keys.add(result.getKey());
			}		

			
		} catch (JavaModelException e) {
			return;
		}
	}
	
	private static IMethod getIMethod(IMethodBinding methodBinding) {
		
		IJavaElement javaElement = methodBinding.getJavaElement();
		
		if (!(javaElement instanceof IMethod)) 
			return null;
		
		return ((IMethod)javaElement);
	}
	
	private static CompilationUnit getAST(IType type) {
		
		IResource res = type.getResource();
		if (!(res instanceof IFile))
			return null;

		try {		
			return JDTParserWrapper.parseJavaFile((IFile) res);
		} catch (ParseException e) {
			e.printStackTrace();
			// if in doubt no error
			return null;
		}

	}
	
	private static boolean overrideCheck(IMethodBinding methodBinding, IMethodBinding superMethodBinding) {
		
		IMethod method = getIMethod(methodBinding);
		IMethod superMethod = getIMethod(superMethodBinding);
		
		if (method == null || superMethod == null)
			return false;
		
		//checks name, number of parameters; simple names of their parameter types
		if (!method.isSimilar(superMethod))
			return false;
										
	/*ADDITION (OPTIONAL) CHECK TO VERIFY OVERRIDING RELATIONSHIP  	
			try {
			//check also the return type
			if (!superMethod.getReturnType().equals(method.getReturnType()))
				return false;
					
			//check also the exception handling type
			String[] superExeps = superMethod.getExceptionTypes();
			String[] methExeps = method.getExceptionTypes();
					
			if  (superExeps.length != methExeps.length)
				return false;
					
			HashSet<String> compareSet = new HashSet<String>();
			for (int k = 0; k < methExeps.length; k++) 
				compareSet.add(methExeps[k]);			
					
			for (int k = 0; k < superExeps.length; k++) {
				if (!compareSet.remove(superExeps[k]))
						break;
			}
					
			if (!compareSet.isEmpty())
				return false;
							
			//check modifier flag
			//super method must be at least package default
			if (Flags.isPrivate(superMethod.getFlags()))
				return false;
			
			//check visibility reducing conditions
			if (Flags.isPublic(superMethod.getFlags()) && !Flags.isPublic(method.getFlags()) )
				return false;
			if (Flags.isProtected(superMethod.getFlags()) && ! (Flags.isPublic(method.getFlags()) || Flags.isProtected(method.getFlags())))
				return false;
			if (Flags.isPackageDefault(superMethod.getFlags()) && Flags.isPrivate(method.getFlags()))
				return false;
			
			} catch (JavaModelException e) {
				return false;
			}
		*/
			
		return true;

	}

	
	public OverridenMethodFinder(IMethodBinding mBinding) {
		this.methodBinding = mBinding;
	}

	private IMethodBinding result = null;

	private final IMethodBinding methodBinding;

	public boolean visit(MethodDeclaration node) {
		if (result != null)
			return false;
		IMethodBinding curBinding = node.resolveBinding();
			
		if (curBinding != null && overrideCheck(methodBinding,curBinding)) {
			result = curBinding;
			return false;
		}
		
		
		return super.visit(node);
	}

	public IMethodBinding getResult() {
		return result;
	}
	
	

}
