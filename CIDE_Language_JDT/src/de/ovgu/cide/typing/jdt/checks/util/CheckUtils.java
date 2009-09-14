package de.ovgu.cide.typing.jdt.checks.util;


import java.util.List;
import java.util.Set;

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
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

import cide.gparser.ParseException;
import de.ovgu.cide.language.jdt.JDTParserWrapper;


public class CheckUtils /*extends ASTVisitor*/ {

	public static void collectOverridenMethodKeysInSuperClasses(IMethodBinding methodBinding, List<String> keys) {
		ITypeBinding declTypeBinding = methodBinding.getDeclaringClass();
		
		//check only for classes
		if (declTypeBinding == null || !declTypeBinding.isClass())
			return;
		
		collectOverridenMethodKeysInSuperClasses(methodBinding, declTypeBinding.getSuperclass(), keys);
		
	}
	
	private static void collectOverridenMethodKeysInSuperClasses(IMethodBinding targetMethodBinding, ITypeBinding superClassBinding,  List<String> keys) {
		
		if (targetMethodBinding == null || keys == null ||  superClassBinding == null || !superClassBinding.isFromSource())
			return;
		
		IMethodBinding[] methodBindings = superClassBinding.getDeclaredMethods(); 
		
		//check all methods of current class
		for (int j = 0; j < methodBindings.length; j++) {
			IMethodBinding tmpMethodBinding = methodBindings[j];
			
		
			//add key if current method is overriden by "targetMethod" of the implemented method
			if (!targetMethodBinding.overrides(tmpMethodBinding)) 
				continue;
			
			//add the method key and information  if this method is abstract				
			keys.add(tmpMethodBinding.getKey());
			
		}
		
		//in addition to this (recursively) check the super class of the current class 
		collectOverridenMethodKeysInSuperClasses(targetMethodBinding, superClassBinding.getSuperclass(), keys);

	}
	
	public static void collectSimilarMethodKeysInSuperClasses(IMethodBinding implMethodBinding,ITypeBinding superClassBinding,   List<InterfacePathItem>  targetMethodKeys, Set<String> checkedInterfaces) {
		
		if (implMethodBinding == null || superClassBinding == null || targetMethodKeys == null)
			return;
		
		
		IMethodBinding[] methodBindings = superClassBinding.getDeclaredMethods(); 
		
		//check all methods of current class
		for (int j = 0; j < methodBindings.length; j++) {
			IMethodBinding tmpMethodBinding = methodBindings[j];
			
		
			//add key if current method is a sub signature of the implemented method
			if (!tmpMethodBinding.isSubsignature(implMethodBinding)) 
				continue;
			
			//add the method key and information  if this method is abstract				
			targetMethodKeys.add( new InterfacePathItem(tmpMethodBinding.getKey(), Modifier.isAbstract(tmpMethodBinding.getModifiers())));
			
		}
		
		//in addition to this (recursively) check the super class of the current class 
		collectSimilarMethodKeysInSuperClasses(implMethodBinding, superClassBinding.getSuperclass(), targetMethodKeys, checkedInterfaces);
	
		//in addition to this (recursively) check all interfaces of the current class 
		collectSimilarMethodKeysInInterfaces(implMethodBinding, superClassBinding.getInterfaces(), targetMethodKeys, checkedInterfaces);
		
	}
	
	
	public static void collectSimilarMethodKeysInInterfaces(IMethodBinding implMethodBinding, ITypeBinding[] interfaceBindings,  List<InterfacePathItem>  targetMethodKeys, Set<String> checkedInterfaces) {
		
		if (implMethodBinding == null || interfaceBindings == null || targetMethodKeys == null || checkedInterfaces == null)
			return;
		
		//iterate through all interfaces of current declaring type
		for (int i = 0; i < interfaceBindings.length; i++) {
			ITypeBinding tmpInterBinding = interfaceBindings[i];
			String tmpInterKey;
			
			//check only new interfaces
			if (checkedInterfaces.contains(tmpInterKey = tmpInterBinding.getKey()))
				continue;
			
			checkedInterfaces.add(tmpInterKey);
			
			IMethodBinding[] methodBindings = tmpInterBinding.getDeclaredMethods(); 
			
			//check all methods of current interface
			for (int j = 0; j < methodBindings.length; j++) {
				IMethodBinding tmpMethodBinding = methodBindings[j];
			
				//add key if current method is a sub signature of the implemented method
				if (tmpMethodBinding.isSubsignature(implMethodBinding)) 
					//we have only abstract methods in interfaces --> second param is true
					targetMethodKeys.add( new InterfacePathItem(tmpMethodBinding.getKey(), true));	
				
			}
			
			//in addition to this check all super interfaces of the current interface 
			collectSimilarMethodKeysInInterfaces(implMethodBinding, tmpInterBinding.getInterfaces(), targetMethodKeys, checkedInterfaces);
		}
		
	}
	
	/* BACKUP OVERRIDING SOLUTION II
	  
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
				CheckUtils bindingFinder = new CheckUtils(methodBinding);
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
		
		try {
			
		//checks if it is a real implementation and not only an abstract method
		//super method must be at least package default
		if (Flags.isAbstract(superMethod.getFlags()))
			return false;
									
//		//ADDITION (OPTIONAL) CHECK TO VERIFY OVERRIDING RELATIONSHIP  	
//			
//		//check also the return type
//		if (!superMethod.getReturnType().equals(method.getReturnType()))
//			return false;
//				
//		//check also the exception handling type
//		String[] superExeps = superMethod.getExceptionTypes();
//		String[] methExeps = method.getExceptionTypes();
//				
//		if  (superExeps.length != methExeps.length)
//			return false;
//				
//		HashSet<String> compareSet = new HashSet<String>();
//		for (int k = 0; k < methExeps.length; k++) 
//			compareSet.add(methExeps[k]);			
//				
//		for (int k = 0; k < superExeps.length; k++) {
//			if (!compareSet.remove(superExeps[k]))
//					break;
//		}
//				
//		if (!compareSet.isEmpty())
//			return false;
//						
//		//check modifier flag
//		//super method must be at least package default
//		if (Flags.isPrivate(superMethod.getFlags()))
//			return false;
//		
//		//check visibility reducing conditions
//		if (Flags.isPublic(superMethod.getFlags()) && !Flags.isPublic(method.getFlags()) )
//			return false;
//		if (Flags.isProtected(superMethod.getFlags()) && ! (Flags.isPublic(method.getFlags()) || Flags.isProtected(method.getFlags())))
//			return false;
//		if (Flags.isPackageDefault(superMethod.getFlags()) && Flags.isPrivate(method.getFlags()))
//			return false;
//		
//		//
		
		} catch (JavaModelException e) {
			return false;
		}
			
		return true;

	}

	
	public CheckUtils(IMethodBinding mBinding) {
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
	
	*/

}
