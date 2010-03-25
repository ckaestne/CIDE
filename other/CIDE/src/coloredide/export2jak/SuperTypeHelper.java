package coloredide.export2jak;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;

/**
 * this class is used to associate additional information about parameter types
 * to super calls (method and constructor).
 * 
 * the problem is that after moving statements to class refinements it is no
 * longer possible to resolve types. therefore they are stored here.
 * 
 * (constructors are obsolete, super constructor calls are implicit in Jak)
 * 
 * all methods are static.
 * 
 * @author cKaestner
 * 
 */
public class SuperTypeHelper {


	private static WeakHashMap<SuperMethodInvocation, String> superMethodTypes = new WeakHashMap<SuperMethodInvocation, String>();

	// private static WeakHashMap<SuperConstructorInvocation, String>
	// superConstructorTypes = new WeakHashMap<SuperConstructorInvocation,
	// String>();

	public static void cacheTypes(SuperMethodInvocation superMethodInvocation) {

		String result = getTypesStr(superMethodInvocation.arguments());
		if (result == null)
			return;// no caching

		cacheTypes(superMethodInvocation, result);
	}

	private static String getTypesStr(List<Expression> list) {
		String result = "";
		for (Iterator<Expression> it = list.iterator(); it.hasNext();) {
			Expression e = it.next();
			ITypeBinding tb = e.resolveTypeBinding();
			if (tb == null)
				return null;// no caching
			result += tb.getName();

			if (it.hasNext()) {
				result += ",";//$NON-NLS-1$
			}
		}
		return result;
	}

	public static void cacheTypes(SuperMethodInvocation superMethodInvocation,
			String types) {
		superMethodTypes.put(superMethodInvocation, types);
	}

	public static String getCachedTypes(
			SuperMethodInvocation superMethodInvocation) {
		return superMethodTypes.get(superMethodInvocation);
	}

	// public static void cacheTypes(
	// SuperConstructorInvocation superConstructorInvocation) {
	//
	// String result = getTypesStr(superConstructorInvocation.arguments());
	// if (result == null)
	// return;// no caching
	//
	// cacheTypes(superConstructorInvocation, result);
	// }

	// public static void cacheTypes(
	// SuperConstructorInvocation superConstructorInvocation, String types) {
	// superConstructorTypes.put(superConstructorInvocation, types);
	// }
	//
	// public static String getCachedTypes(
	// SuperConstructorInvocation superConstructorInvocation) {
	// return superConstructorTypes.get(superConstructorInvocation);
	// }

	public static void cacheAll(CompilationUnit compUnit) {
		compUnit.accept(new CacheTypeVisitor());
	}

	public static class CacheTypeVisitor extends ASTVisitor {
		public boolean visit(SuperMethodInvocation node) {
			cacheTypes(node);
			return super.visit(node);
		}
		//
		// public boolean visit(SuperConstructorInvocation node) {
		// cacheTypes(node);
		// return super.visit(node);
		// }
	}

}
