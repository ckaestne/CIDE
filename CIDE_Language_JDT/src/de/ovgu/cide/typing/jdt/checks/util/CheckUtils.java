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

package de.ovgu.cide.typing.jdt.checks.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.Modifier;

import cide.gast.IASTNode;
import de.ovgu.cide.language.jdt.ASTBridge;

/**
 * provides some methods for check preparation
 * 
 * @author aDreiling
 * 
 */
public class CheckUtils /* extends ASTVisitor */{
	
	public static List<IASTNode> getIASTNodeList(List list) {

		ArrayList<IASTNode> l = new ArrayList<IASTNode>();
		for (int j = 0; j < list.size(); j++) {
			l.add(ASTBridge.bridge((ASTNode)list.get(j)));
		}
		
		
		return l;
		
	}

	public static MethodPathItem getFirstNonAbstractItem(
			List<MethodPathItem> inherMethods) {

		if (inherMethods == null)
			return null;

		for (MethodPathItem tmpItem : inherMethods) {
			if (tmpItem.isAbstract())
				continue;

			return tmpItem;
		}

		return null;

	}

	public static void collectOverriddenMethodKeysInSuperClasses(
			IMethodBinding methodBinding, List<MethodPathItem> inherMethods) {
		ITypeBinding declTypeBinding = methodBinding.getDeclaringClass();

		// check only for classes
		if (declTypeBinding == null || !declTypeBinding.isClass())
			return;

//		List<MethodPathItem> methodBindings = new ArrayList<MethodPathItem>();

		collectOverriddenMethodsInSuperClasses(methodBinding, declTypeBinding
				.getSuperclass(), inherMethods);

//		for (MethodPathItem tmpItem : methodBindings) {
//			keys.add(tmpItem.getKey());
//		}

	}

	public static void collectOverriddenMethodsInSuperClasses(
			IMethodBinding sourceMethodBinding, ITypeBinding superClassBinding,
			List<MethodPathItem> inherMethods) {

		if (sourceMethodBinding == null || inherMethods == null
				|| superClassBinding == null)
//				|| !superClassBinding.isFromSource())
			return;

		IMethodBinding[] methodBindings = superClassBinding
				.getDeclaredMethods();

		// check all methods of current class
		for (int j = 0; j < methodBindings.length; j++) {
			IMethodBinding tmpMethodBinding = methodBindings[j];

			if (!sourceMethodBinding.overrides(tmpMethodBinding))
				continue;

			// add the method key and information if this method is abstract
			inherMethods.add(new MethodPathItem(tmpMethodBinding, Modifier
					.isAbstract(superClassBinding.getModifiers())));

		}

		// in addition to this (recursively) check the super class of the
		// current class
		collectOverriddenMethodsInSuperClasses(sourceMethodBinding,
				superClassBinding.getSuperclass(), inherMethods);

	}

	public static void collectSimilarMethodKeysInSuperClasses(
			IMethodBinding implMethodBinding, ITypeBinding superClassBinding,
			List<MethodPathItem> methods, Set<String> checkedInterfaces) {

		if (implMethodBinding == null || superClassBinding == null
				|| methods == null)
			return;

		IMethodBinding[] methodBindings = superClassBinding
				.getDeclaredMethods();

		// check all methods of current class
		for (int j = 0; j < methodBindings.length; j++) {
			IMethodBinding tmpMethodBinding = methodBindings[j];

			// add method if current method is a sub signature of the implemented
			// method
			if (!tmpMethodBinding.isSubsignature(implMethodBinding))
				continue;

			// add the method binding
			methods.add(new MethodPathItem(tmpMethodBinding, Modifier
					.isAbstract(superClassBinding.getModifiers())));

		}

		// in addition to this (recursively) check the super class of the
		// current class
		collectSimilarMethodKeysInSuperClasses(implMethodBinding,
				superClassBinding.getSuperclass(), methods, checkedInterfaces);

		// in addition to this (recursively) check all interfaces of the current
		// class
		collectSimilarMethodKeysInInterfaces(implMethodBinding,
				superClassBinding.getInterfaces(), methods, checkedInterfaces);

	}

	public static void collectSimilarMethodKeysInInterfaces(
			IMethodBinding implMethodBinding, ITypeBinding[] interfaceBindings,
			List<MethodPathItem> targetMethodKeys, Set<String> checkedInterfaces) {

		if (implMethodBinding == null || interfaceBindings == null
				|| targetMethodKeys == null || checkedInterfaces == null)
			return;

		// iterate through all interfaces of current declaring type
		for (int i = 0; i < interfaceBindings.length; i++) {
			ITypeBinding tmpInterBinding = interfaceBindings[i];
			String tmpInterKey;

			// check only new interfaces
			if (checkedInterfaces.contains(tmpInterKey = tmpInterBinding
					.getKey()))
				continue;

			checkedInterfaces.add(tmpInterKey);

			IMethodBinding[] methodBindings = tmpInterBinding
					.getDeclaredMethods();

			// check all methods of current interface
			for (int j = 0; j < methodBindings.length; j++) {
				IMethodBinding tmpMethodBinding = methodBindings[j];

				// add key if current method is a sub signature of the
				// implemented method
				if (tmpMethodBinding.isSubsignature(implMethodBinding))
					// interfaces are abstract --> second param is true
					targetMethodKeys.add(new MethodPathItem(tmpMethodBinding,
							true));

			}

			// in addition to this check all super interfaces of the current
			// interface
			collectSimilarMethodKeysInInterfaces(implMethodBinding,
					tmpInterBinding.getInterfaces(), targetMethodKeys,
					checkedInterfaces);
		}

	}
	
	/*BACKUP SOME METHODD*/
	
	// System.out.println("CHECK: " + source.getId() + " COLOR: " +
	// file.getColorManager()
	// .getColors(source)) ;
	// System.out.println("      <--> "+ tmpKey);
	// System.out.println("      COLOR: " + typingProvider.getBindingColors()
	// .getColors(tmpKey));
	
	// public static void getOverriddenParamKeys(List<MethodPathItem> methods,
	// List<List<String>> paramKeys) {
	//		
	// if ( paramKeys == null || methods == null || methods.size() == 0)
	// return;
	//		
	//
	// // add param keys of all super methods
	// ArrayList<String> tmpKeys;
	// for (int i = 0; i < implMethodBinding.getParameterTypes().length; i++) {
	// tmpKeys = new ArrayList<String>();
	//
	// for (MethodPathItem tmpItem : methods) {
	//				
	// // if (!tmpItem.isOverridden())
	// // continue;
	//				
	// tmpKeys.add(BindingProjectColorCache.getParamKey(
	// tmpItem.getKey(), i));
	// }
	//
	// paramKeys.add(tmpKeys);
	// }
	//		
	// }
	
	// public static void getOverriddenExceptionKeys( MethodDeclaration node,
	// List<MethodPathItem> methods, List<List<String>> execKeys) {
	//		
	// if ( node == null || execKeys == null || methods == null)
	// return;
	//		
	// // checks if method overrides another one
	// if (methods.size() == 0)
	// return;
	//		
	// // add cast compatible exception types of first overridden method (in
	// // super class)
	// for (Object curExcNode : node.thrownExceptions()) {
	//
	// ITypeBinding tmpExBinding = ((Name) curExcNode)
	// .resolveTypeBinding();
	//
	// if (tmpExBinding == null)
	// continue;
	//
	//			
	// IMethodBinding superMethBinding = null;
	//			
	// //get first overridden item
	// for (MethodPathItem tmpItem : methods) {
	// if (!tmpItem.isOverridden())
	// continue;
	//				
	// superMethBinding = tmpItem.getBinding();
	// break;
	// }
	//			
	// if (superMethBinding == null)
	// continue;
	//			
	//			
	// ArrayList<String> tmpExceKeys = new ArrayList<String>();
	//
	// for (ITypeBinding tmpMethExBinding : superMethBinding
	// .getExceptionTypes()) {
	//
	// if (tmpExBinding.isCastCompatible(tmpMethExBinding))
	// tmpExceKeys.add(BindingProjectColorCache.getExceptionKey(
	// superMethBinding.getKey(), tmpMethExBinding
	// .getKey()));
	//
	// }
	//
	// execKeys.add(tmpExceKeys);
	//
	// }
	//		
	//		
	// }
	
	// public static void collectOverriddenSignaturePartKeysInSuperClasses(
	// IMethodBinding implMethodBinding, MethodDeclaration node,
	// List<List<String>> paramKeys, List<List<String>> execKeys) {
	//
	// if (implMethodBinding == null || node == null
	// || paramKeys == null || execKeys == null)
	// return;
	//
	// List<IMethodBinding> methodBindings = new ArrayList<IMethodBinding>();
	//
	// collectOverriddenMethodsInSuperClasses(implMethodBinding,
	// declTypeBinding.getSuperclass(), methodBindings);
	//
	// // checks if method overrides another one
	// if (methodBindings.size() == 0)
	// return;
	//
	// // add param keys of all super methods
	// ArrayList<String> tmpKeys;
	// for (int i = 0; i < implMethodBinding.getParameterTypes().length; i++) {
	// tmpKeys = new ArrayList<String>();
	//
	// for (IMethodBinding tmpMethodBinding : methodBindings) {
	// tmpKeys.add(BindingProjectColorCache.getParamKey(
	// tmpMethodBinding.getKey(), i));
	// }
	//
	// paramKeys.add(tmpKeys);
	// }
	//
	// // add cast compatible exception types of first overridden method (in
	// // super class)
	// for (Object curExcNode : node.thrownExceptions()) {
	//
	// ITypeBinding tmpExBinding = ((Name) curExcNode)
	// .resolveTypeBinding();
	//
	// if (tmpExBinding == null)
	// continue;
	//
	// IMethodBinding superMethBinding = methodBindings.get(0);
	// ArrayList<String> tmpExceKeys = new ArrayList<String>();
	//
	// for (ITypeBinding tmpMethExBinding : superMethBinding
	// .getExceptionTypes()) {
	//
	// if (tmpExBinding.isCastCompatible(tmpMethExBinding))
	// tmpExceKeys.add(BindingProjectColorCache.getExceptionKey(
	// superMethBinding.getKey(), tmpMethExBinding
	// .getKey()));
	//
	// }
	//
	// execKeys.add(tmpExceKeys);
	//
	// }
	//
	// }	
	
	
	
//BACKUP

//		private List<String> findAllOverriddenMethodKeys(IMethod method) {
//			try {
//				IType type = method.getDeclaringType();
//				ITypeHierarchy hierarchy = type.newSupertypeHierarchy(null);
//
//
//				ArrayList<String> keys = new ArrayList<String>();
//				
//				while ((type = hierarchy.getSuperclass(type)) != null) {
//					
//					//TEST
//					
//					IMethod[] overriddenMethods = type.findMethods(method);
//					if (overriddenMethods!=null && overriddenMethods.length>0) {
//						for (int j = 0; j < overriddenMethods.length; j++) {
//							IMethod tmpMethod = overriddenMethods[j];
//																	
//							//check also the return type
//							if (!tmpMethod.getReturnType().equals(method.getReturnType()))
//								continue;
//							
//							//check also the exception handling type
//							String[] tmpExeps = tmpMethod.getExceptionTypes();
//							String[] methExeps = method.getExceptionTypes();
//							
//							if  (tmpExeps.length != methExeps.length)
//								continue;
//							
//							HashSet<String> compareSet = new HashSet<String>();
//							for (int k = 0; k < methExeps.length; k++) 
//								compareSet.add(methExeps[k]);			
//							
//							for (int k = 0; k < tmpExeps.length; k++) {
//								if (!compareSet.remove(tmpExeps[k]))
//									break;
//							}
//							
//							if (!compareSet.isEmpty())
//								continue;
//								
//							
//							//check modifier flag
//							//super method must be at least package default
//							if (Flags.isPrivate(tmpMethod.getFlags()))
//								continue;
//							
//							//check visibility reducing conditions
//							if (Flags.isPublic(tmpMethod.getFlags()) && !Flags.isPublic(method.getFlags()) )
//								continue;
//							if (Flags.isProtected(tmpMethod.getFlags()) && ! (Flags.isPublic(method.getFlags()) || Flags.isProtected(method.getFlags())))
//								continue;
//							if (Flags.isPackageDefault(tmpMethod.getFlags()) && Flags.isPrivate(method.getFlags()))
//								continue;
//							
//							
//							System.out.println("RETURNTYPE: " + tmpMethod.getReturnType());
//							
//							//TEST
//							CompilationUnit ast = getAST(tmpMethod);
//							if (ast == null)
//								return null;
	//
//							ASTBindingFinder bindingFinder = new ASTBindingFinder(tmpMethod.getKey());
//							ast.accept(bindingFinder);
//							
//							ASTNode result = bindingFinder.getResult();
//							System.out.println("ASTNODE:" + result);
//							if (result == null)
//								return null;
	//
//							
//							
//							IMethod[] meths =  type.getMethods();
//							for(IMethod m: meths) {
//								System.out.println(" ===> " + m.getKey());
//							}
//							
//							//TEST
//							
//							//method overrides tmpMethod, add the tmpMethod - key accordingly	
//							keys.add(tmpMethod.getKey());
//							
//						}
//					}
//				}
	//
//				return keys.size() > 0 ? keys : null;
//				
//			} catch (JavaModelException e) {
//				return null;
//			}
//		}
		
//		private IMethod getIMethod(IMethodBinding methodBinding) {
//			
//			IJavaElement javaElement = methodBinding.getJavaElement();
//			
//			if (!(javaElement instanceof IMethod)) 
//				return null;
//			
//			return ((IMethod)javaElement);
//		}

	
	/*
	 * BACKUP OVERRIDING SOLUTION II
	 * 
	 * public static void findAllOverriddenMethodKeys(IMethodBinding
	 * methodBinding, List<String> keys) { try {
	 * 
	 * if (methodBinding == null) return;
	 * 
	 * IMethod method = getIMethod(methodBinding);
	 * 
	 * //checks if method exists and is not part of a binary file if (method ==
	 * null || method.isBinary()) return;
	 * 
	 * IType type = method.getDeclaringType(); ITypeHierarchy hierarchy =
	 * type.newSupertypeHierarchy(null);
	 * 
	 * //get the super class
	 * 
	 * while ((type = hierarchy.getSuperclass(type)) != null) {
	 * 
	 * //get the ast for this super class CompilationUnit ast = getAST(type);
	 * 
	 * if (ast == null) return;
	 * 
	 * //try to find a method in this super class which is overriden by current
	 * "method" CheckUtils bindingFinder = new CheckUtils(methodBinding);
	 * ast.accept(bindingFinder);
	 * 
	 * IMethodBinding result = bindingFinder.getResult();
	 * 
	 * //check if a overriden method has been found if (result != null) //method
	 * overrides tmpMethod, add the tmpMethod - key accordingly
	 * keys.add(result.getKey()); }
	 * 
	 * 
	 * } catch (JavaModelException e) { return; } }
	 * 
	 * private static IMethod getIMethod(IMethodBinding methodBinding) {
	 * 
	 * IJavaElement javaElement = methodBinding.getJavaElement();
	 * 
	 * if (!(javaElement instanceof IMethod)) return null;
	 * 
	 * return ((IMethod)javaElement); }
	 * 
	 * private static CompilationUnit getAST(IType type) {
	 * 
	 * IResource res = type.getResource(); if (!(res instanceof IFile)) return
	 * null;
	 * 
	 * try { return JDTParserWrapper.parseJavaFile((IFile) res); } catch
	 * (ParseException e) { e.printStackTrace(); // if in doubt no error return
	 * null; }
	 * 
	 * }
	 * 
	 * private static boolean overrideCheck(IMethodBinding methodBinding,
	 * IMethodBinding superMethodBinding) {
	 * 
	 * IMethod method = getIMethod(methodBinding); IMethod superMethod =
	 * getIMethod(superMethodBinding);
	 * 
	 * if (method == null || superMethod == null) return false;
	 * 
	 * //checks name, number of parameters; simple names of their parameter
	 * types if (!method.isSimilar(superMethod)) return false;
	 * 
	 * try {
	 * 
	 * //checks if it is a real implementation and not only an abstract method
	 * //super method must be at least package default if
	 * (Flags.isAbstract(superMethod.getFlags())) return false;
	 * 
	 * // //ADDITION (OPTIONAL) CHECK TO VERIFY OVERRIDING RELATIONSHIP // //
	 * //check also the return type // if
	 * (!superMethod.getReturnType().equals(method.getReturnType())) // return
	 * false; // // //check also the exception handling type // String[]
	 * superExeps = superMethod.getExceptionTypes(); // String[] methExeps =
	 * method.getExceptionTypes(); // // if (superExeps.length !=
	 * methExeps.length) // return false; // // HashSet<String> compareSet = new
	 * HashSet<String>(); // for (int k = 0; k < methExeps.length; k++) //
	 * compareSet.add(methExeps[k]); // // for (int k = 0; k <
	 * superExeps.length; k++) { // if (!compareSet.remove(superExeps[k])) //
	 * break; // } // // if (!compareSet.isEmpty()) // return false; // //
	 * //check modifier flag // //super method must be at least package default
	 * // if (Flags.isPrivate(superMethod.getFlags())) // return false; // //
	 * //check visibility reducing conditions // if
	 * (Flags.isPublic(superMethod.getFlags()) &&
	 * !Flags.isPublic(method.getFlags()) ) // return false; // if
	 * (Flags.isProtected(superMethod.getFlags()) && !
	 * (Flags.isPublic(method.getFlags()) ||
	 * Flags.isProtected(method.getFlags()))) // return false; // if
	 * (Flags.isPackageDefault(superMethod.getFlags()) &&
	 * Flags.isPrivate(method.getFlags())) // return false; // // //
	 * 
	 * } catch (JavaModelException e) { return false; }
	 * 
	 * return true;
	 * 
	 * }
	 * 
	 * 
	 * public CheckUtils(IMethodBinding mBinding) { this.methodBinding =
	 * mBinding; }
	 * 
	 * private IMethodBinding result = null;
	 * 
	 * private final IMethodBinding methodBinding;
	 * 
	 * public boolean visit(MethodDeclaration node) { if (result != null) return
	 * false; IMethodBinding curBinding = node.resolveBinding();
	 * 
	 * if (curBinding != null && overrideCheck(methodBinding,curBinding)) {
	 * result = curBinding; return false; }
	 * 
	 * 
	 * return super.visit(node); }
	 * 
	 * public IMethodBinding getResult() { return result; }
	 */

}
