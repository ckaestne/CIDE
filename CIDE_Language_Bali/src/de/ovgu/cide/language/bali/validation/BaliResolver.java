//package de.ovgu.cide.language.bali.validation;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.WeakHashMap;
//
//import tmp.generated_bali.BaliGrammarRule;
//import tmp.generated_bali.IdentifierNode;
//import tmp.generated_bali.ReferenceManager;
//import cide.gast.ASTNode;
//import cide.gast.ASTVisitor;
//import cide.gast.ISourceFile;
//import cide.greferences.IReferenceType;
//import cide.greferences.IResolver;
//import cide.greferences.ReferenceResolvingException;
//import cide.greferences.UnsupportedReferenceTypeException;
//
//public class BaliResolver implements IResolver {
//
//	public void cacheSourceFile(ISourceFile ast) {
//	}
//
//	public void clearCache() {
//	}
//
//	public CacheRequirement getCacheRequirement() {
//		return CacheRequirement.NoCache;
//	}
//
//	WeakHashMap<ISourceFile, Map<String/* ProductionName */, ASTNode/* ProductionNode */>> productionCache = new WeakHashMap<ISourceFile, Map<String, ASTNode>>();
//
//	public ASTNode getReferenceTarget(IReferenceType type, ASTNode source)
//			throws ReferenceResolvingException {
//
//		Map<String, ASTNode> productionNames = getProductionNames(source
//				.getRoot());
//
//		if (type == ReferenceManager.grammar) {
//			assert source instanceof IdentifierNode;
//			String referredProductionName = ((IdentifierNode) source)
//					.getIdentifier().getValue();
//			return productionNames.get(referredProductionName);
//		}
//
//		throw new UnsupportedReferenceTypeException(type);
//	}
//
//	private Map<String/* ProductionName */, ASTNode/* ProductionNode */> getProductionNames(
//			ISourceFile root) {
//		Map<String, ASTNode> result;
//		if ((result = productionCache.get(productionCache)) != null)
//			return result;
//		result = new HashMap<String, ASTNode>();
//		fillProductionNames(root, result);
//		productionCache.put(root, result);
//		return result;
//	}
//
//	private void fillProductionNames(ISourceFile root,
//			final Map<String, ASTNode> result) {
//		root.accept(new ASTVisitor() {
//			@Override
//			public boolean visit(ASTNode node) {
//				if (node instanceof BaliGrammarRule) {
//					result.put(((BaliGrammarRule) node).getIdentifier()
//							.getValue(), node);
//					return false;
//				}
//
//				return true;
//			}
//		});
//	}
//
//}
