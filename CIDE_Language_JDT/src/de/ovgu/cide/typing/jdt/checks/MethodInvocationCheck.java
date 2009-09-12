package de.ovgu.cide.typing.jdt.checks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.internal.corext.util.JavaModelUtil;
import org.eclipse.jdt.internal.ui.callhierarchy.MethodWrapperWorkbenchAdapter;

import cide.gast.IASTNode;
import cide.gparser.ParseException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.jdt.ASTBridge;
import de.ovgu.cide.language.jdt.JDTParserWrapper;
import de.ovgu.cide.typing.jdt.JDTTypingProvider;
import de.ovgu.cide.typing.jdt.checks.resolutions.ASTBindingFinder;
import de.ovgu.cide.typing.jdt.checks.resolutions.ASTBindingFinderHelper;
import de.ovgu.cide.typing.jdt.checks.resolutions.AbstractJDTTypingCheckWithResolution;
import de.ovgu.cide.typing.jdt.checks.util.OverridenMethodFinder;
import de.ovgu.cide.typing.model.IEvaluationStrategy;
import de.ovgu.cide.typing.model.ITypingMarkerResolution;

/**
 * checks colors between a field and references to it
 * 
 * @author ckaestne
 * 
 */
public class MethodInvocationCheck extends AbstractJDTTypingCheckWithResolution {

	private final IMethodBinding targetMethod;

	public MethodInvocationCheck(ColoredSourceFile file,
			JDTTypingProvider typingProvider, IASTNode source,
			IMethodBinding target) {
		super(file, typingProvider, source);
		this.targetMethod = target;
	}

	public boolean evaluate(IEvaluationStrategy strategy) {
		
		//check the default case
		if (strategy.implies(file.getFeatureModel(), file.getColorManager()
				.getColors(source), typingProvider.getBindingColors()
				.getColors(targetMethod)))
			return true;
		
		
		//checks if target method overrides other methods for which the "implies condition" is true
		List<String> overMethodKeys = new ArrayList<String>();
		
		//get overriden method keys
		OverridenMethodFinder.findAllOverriddenMethodKeys(targetMethod, overMethodKeys);
		
		//the targetMethod(Declaration) overrides at least one method(Declaration) 			
		for (String tmpKey: overMethodKeys) {
			
			System.out.println("CHECK: " + tmpKey);
			
			//check following condition for each overriden method
			if (strategy.implies(file.getFeatureModel(), file.getColorManager()
					.getColors(source), typingProvider.getBindingColors()
					.getColors(tmpKey))) 
				return true;
		}
			
		return false;
	
	}

	public String getErrorMessage() {
		return "Invoking method which is not present in some variants: "
				+ targetMethod.getName();
	}

	public String getProblemType() {
		return "de.ovgu.cide.typing.jdt.methodinvocation";
	}

	@Override
	protected void addResolutions(
			ArrayList<ITypingMarkerResolution> resolutions,
			HashSet<IFeature> colorDiff) {
		resolutions.addAll(createChangeNodeColorResolution(findCallingStatement(source), 
				colorDiff, true, "statement", 20));
		resolutions
				.addAll(createChangeNodeColorResolution(
						findCallingMethod(source), colorDiff, true,
						"method", 18));
		resolutions.addAll(createChangeNodeColorResolution(
				findCallingType(source), colorDiff, true, "type", 16));
		IASTNode methodDecl = ASTBindingFinderHelper.getMethodDecl(targetMethod);
		if (methodDecl !=null)
			resolutions.addAll(createChangeNodeColorResolution(
					methodDecl, colorDiff, false,
					"method declaration", 14));
	}

	@Override
	protected Set<IFeature> getTargetColors() {
		return typingProvider.getBindingColors().getColors(targetMethod);
	}
	
//BACKUP

//	private List<String> findAllOverriddenMethodKeys(IMethod method) {
//		try {
//			IType type = method.getDeclaringType();
//			ITypeHierarchy hierarchy = type.newSupertypeHierarchy(null);
//
//
//			ArrayList<String> keys = new ArrayList<String>();
//			
//			while ((type = hierarchy.getSuperclass(type)) != null) {
//				
//				//TEST
//				
//				IMethod[] overriddenMethods = type.findMethods(method);
//				if (overriddenMethods!=null && overriddenMethods.length>0) {
//					for (int j = 0; j < overriddenMethods.length; j++) {
//						IMethod tmpMethod = overriddenMethods[j];
//																
//						//check also the return type
//						if (!tmpMethod.getReturnType().equals(method.getReturnType()))
//							continue;
//						
//						//check also the exception handling type
//						String[] tmpExeps = tmpMethod.getExceptionTypes();
//						String[] methExeps = method.getExceptionTypes();
//						
//						if  (tmpExeps.length != methExeps.length)
//							continue;
//						
//						HashSet<String> compareSet = new HashSet<String>();
//						for (int k = 0; k < methExeps.length; k++) 
//							compareSet.add(methExeps[k]);			
//						
//						for (int k = 0; k < tmpExeps.length; k++) {
//							if (!compareSet.remove(tmpExeps[k]))
//								break;
//						}
//						
//						if (!compareSet.isEmpty())
//							continue;
//							
//						
//						//check modifier flag
//						//super method must be at least package default
//						if (Flags.isPrivate(tmpMethod.getFlags()))
//							continue;
//						
//						//check visibility reducing conditions
//						if (Flags.isPublic(tmpMethod.getFlags()) && !Flags.isPublic(method.getFlags()) )
//							continue;
//						if (Flags.isProtected(tmpMethod.getFlags()) && ! (Flags.isPublic(method.getFlags()) || Flags.isProtected(method.getFlags())))
//							continue;
//						if (Flags.isPackageDefault(tmpMethod.getFlags()) && Flags.isPrivate(method.getFlags()))
//							continue;
//						
//						
//						System.out.println("RETURNTYPE: " + tmpMethod.getReturnType());
//						
//						//TEST
//						CompilationUnit ast = getAST(tmpMethod);
//						if (ast == null)
//							return null;
//
//						ASTBindingFinder bindingFinder = new ASTBindingFinder(tmpMethod.getKey());
//						ast.accept(bindingFinder);
//						
//						ASTNode result = bindingFinder.getResult();
//						System.out.println("ASTNODE:" + result);
//						if (result == null)
//							return null;
//
//						
//						
//						IMethod[] meths =  type.getMethods();
//						for(IMethod m: meths) {
//							System.out.println(" ===> " + m.getKey());
//						}
//						
//						//TEST
//						
//						//method overrides tmpMethod, add the tmpMethod - key accordingly	
//						keys.add(tmpMethod.getKey());
//						
//					}
//				}
//			}
//
//			return keys.size() > 0 ? keys : null;
//			
//		} catch (JavaModelException e) {
//			return null;
//		}
//	}
	
//	private IMethod getIMethod(IMethodBinding methodBinding) {
//		
//		IJavaElement javaElement = methodBinding.getJavaElement();
//		
//		if (!(javaElement instanceof IMethod)) 
//			return null;
//		
//		return ((IMethod)javaElement);
//	}

}
