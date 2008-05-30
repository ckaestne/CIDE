//package de.ovgu.cide.language.gcide;
//
//import java.util.HashMap;
//
//import tmp.generated_gcide.NonTerminal;
//import tmp.generated_gcide.Production;
//import cide.gast.ASTNode;
//import cide.gast.ASTVisitor;
//import cide.gast.ISourceFile;
//import cide.greferences.IReferenceType;
//import cide.greferences.IResolver;
//import cide.greferences.ReferenceResolvingException;
//
//public class GCIDEResolver implements IResolver {
//
//	private HashMap<String, ASTNode> productionsCache;
//	private ISourceFile cachedFile = null;
//
//	public void cacheSourceFile(ISourceFile ast) {
//		cacheProductions(ast);
//	}
//
//	public void clearCache() {
//		productionsCache = null;
//	}
//
//	public CacheRequirement getCacheRequirement() {
//		return CacheRequirement.NoCache;
//	}
//
//	public ASTNode getReferenceTarget(IReferenceType type, ASTNode source)
//			throws ReferenceResolvingException {
//		if (source instanceof NonTerminal) {
//			cacheProductions(source.getRoot());
//			return productionsCache.get(((NonTerminal) source).getIdentifier()
//					.getValue());
//		}
//		return null;
//	}
//
//	private void cacheProductions(ISourceFile root) {
//		if (productionsCache == null || root != cachedFile) {
//			cachedFile = root;
//			productionsCache = new HashMap<String, ASTNode>();
//			root.accept(new ASTVisitor() {
//				@Override
//				public boolean visit(ASTNode node) {
//					if (node instanceof Production) {
//						productionsCache.put(((Production) node)
//								.getIdentifier().getValue(), node);
//					}
//					return super.visit(node);
//				}
//			});
//		}
//	}
//
//}
