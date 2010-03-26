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

package cide.languages;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

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
			// Habe ich mal rausgenommen, weil es denke ich nicht benötigt wird
			//result.setParent(node.getParent(), node.getLocationInParent());
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
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		if (method == null)
			return null;
		
		Object result = null;
		try {
			result = method.invoke(internalParser, (Object[]) null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		if ((result == null) || !(result instanceof IASTNode))
			return null;
		
		return (IASTNode) result;
	}
	
	/**
	 * Gibt zurück, ob zur gegebenen Liste von AST-Knoten Alternativen angegeben werden können. Standardwert ist true.
	 * Subklassen können diese Methode überschreiben, um z.B. Alternativen nur für eine bestimmte Menge von AST-Knoten
	 * zu ermöglichen.
	 */
	public boolean canCreateAlternatives(List<IASTNode> nodes) { return true; }
	
	protected abstract ParserType getInternalParser(String code);
}
