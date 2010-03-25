package coloredide.export;

import java.util.HashMap;
import java.util.WeakHashMap;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

/**
 * this class caches all local variables of a class.
 * 
 * 
 * @author cKaestner
 * 
 */
public class LocalVariableHelper {

	private static final WeakHashMap<Name, VariableDeclaration> localVariableAccess = new WeakHashMap<Name, VariableDeclaration>();
	private static final WeakHashMap<VariableDeclaration, Formal> formals = new WeakHashMap<VariableDeclaration, Formal>();
	private static final WeakHashMap<Formal, VariableDeclaration> formalsReverse = new WeakHashMap<Formal, VariableDeclaration>();
	private static boolean initialized = false;
	private static int formalNr = 0;

	public static void cacheCompilationUnit(CompilationUnit compUnit) {
		clearCache();

		final HashMap<String, VariableDeclaration> knownDeclarations = new HashMap<String, VariableDeclaration>();

		// find declarations
		compUnit.accept(new ASTVisitor() {
			@Override
			public boolean visit(VariableDeclarationFragment node) {
				IVariableBinding binding = node.resolveBinding();
				if (binding != null)
					knownDeclarations.put(binding.getKey(), node);
				return super.visit(node);
			}

			public boolean visit(SingleVariableDeclaration node) {
				IVariableBinding binding = node.resolveBinding();
				if (binding != null)
					knownDeclarations.put(binding.getKey(), node);
				return super.visit(node);
			}
		});

		// find access and create cache
		compUnit.accept(new ASTVisitor() {
			@Override
			public boolean visit(QualifiedName node) {
				return visitName(node);
			}

			@Override
			public boolean visit(SimpleName node) {
				return visitName(node);
			}

			private boolean visitName(Name node) {
				IBinding binding = node.resolveBinding();
				if (binding != null && binding instanceof IVariableBinding) {
					VariableDeclaration declaration = knownDeclarations
							.get(binding.getKey());
					if (declaration != null) {
						localVariableAccess.put(node, declaration);
						if (formals.get(declaration) == null) {
							Formal formal = createFormal(declaration);
							if (formal != null) {
								formals.put(declaration, formal);
								formalsReverse.put(formal, declaration);
							}
						}
					}
				}
				return true;
			}
		});
		initialized = true;
	}

	public static VariableDeclaration findVariableDeclaration(Name node) {
		assert initialized;
		return localVariableAccess.get(node);
	}

	public static Formal getFormal(VariableDeclaration decl) {
		return formals.get(decl);
	}

	private static Formal createFormal(VariableDeclaration decl) {
		if (decl instanceof SingleVariableDeclaration)
			return new Formal(((SingleVariableDeclaration) decl).getType(),
					decl.getName().getIdentifier(), formalNr++);
		else {
			assert decl instanceof VariableDeclarationFragment;

			Type type = null;
			if (decl.getParent() instanceof VariableDeclarationStatement) {
				type = ((VariableDeclarationStatement) decl.getParent())
						.getType();
			} else if (decl.getParent() instanceof VariableDeclarationExpression) {
				type = ((VariableDeclarationExpression) decl.getParent())
						.getType();
			} else if (decl.getParent() instanceof FieldDeclaration)
				;// not a local variable
			else
				assert false;

			if (type != null)
				return new Formal(type, decl.getName().getIdentifier(),
						formalNr++);
		}
		return null;
	}

	private static void clearCache() {
		localVariableAccess.clear();
		formals.clear();
		formalsReverse.clear();
	}

	public static VariableDeclaration getDeclForFormal(Formal formal) {
		return formalsReverse.get(formal);
	}

	public static void removeName(Name name) {
		localVariableAccess.remove(name);
	}

	/**
	 * method is used to register access to local variables that are created by
	 * the own algorithm (e.g., from parameters of hook methods)
	 * 
	 * @param param
	 * @param decl
	 */
	public static void addLocalVariableAccess(Name name,
			VariableDeclaration decl) {
		if (name != null && decl != null)
			localVariableAccess.put(name, decl);
	}

	public static void addLocalVariableAccess(Name name, Formal formal) {
		VariableDeclaration decl = getDeclForFormal(formal);
		if (name != null && decl != null)
			localVariableAccess.put(name, decl);
	}

}
