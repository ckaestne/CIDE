package cide.languages;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cide.gast.IASTNode;

/**
 * Erweiterung einer LanguageExtension um die Funktionalität, auch einzelne Codefragmente parsen zu können
 * 
 * @author Malte Rosenthal
 *
 * @param <ParserType> Typ des interner Parsers (z.B. FJParser)
 */
public abstract class ExtendedLanguageExtension<ParserType> implements ILanguageExtension {
	
	/**
	 * Parsed das gegebene Codefragment, das an Stelle des gegebenen AST-Knotens stehen kann.
	 * D.h. z.B. auch, dass die ID des gegebenen Knotens übernommen wird.
	 * @param node
	 * @param code
	 * @return
	 */
	public IASTNode parseCodeFragment(IASTNode node, String code) {
		IASTNode result = this.parseCodeFragment(node.getClass().getSimpleName(), code);
		if (result != null) {
			result.setId(node.getId());
		}
		
		return result;
	}

	private IASTNode parseCodeFragment(String nonTerminalName, String code) {
		ParserType internalParser = getInternalParser(code);
		if (internalParser == null)
			return null;
		
		Method method = null;
		try {
			method = internalParser.getClass().getMethod(nonTerminalName, (Class<?>[]) null);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (method == null)
			return null;
		
		Object result = null;
		try {
			result = method.invoke(internalParser, (Object[]) null);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if ((result == null) || !(result instanceof IASTNode))
			return null;
		
		return (IASTNode) result;
	}
	
	protected abstract ParserType getInternalParser(String code);
}
