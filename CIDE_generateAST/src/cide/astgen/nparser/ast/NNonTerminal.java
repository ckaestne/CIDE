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

package cide.astgen.nparser.ast;

import cide.astgen.nparser.visitor.NVisitor;

public class NNonTerminal extends NAbstractValue {

	private String origNTerminal;
	/**
	 * used only if the target production should be used as a wrapper. in this
	 * case it stores the type of the wrap target (wrappee is specified inside
	 * the target production).
	 */
	private String wrapsAroundType = null;

	public NNonTerminal(NChoice parent, Type type, String terminal) {
		super(parent, terminal.toString(), type);
		this.origNTerminal = terminal;
	}

	public void accept(NVisitor visitor) {
		visitor.visit(this);
		visitor.postVisit(this);
	}

	@Override
	public String genVariablePlainType() {
		if (wrapsAroundType != null)
			return "IASTNode";

		return name;
	}

	public boolean isWrapper() {
		return wrapsAroundType != null && type == Type.ONE;
	}

	public String getWrapsAroundType() {
		return wrapsAroundType;
	}

	public void setWrapsAroundType(String type) {
		wrapsAroundType = type;
	}

	/**
	 * searches the grammar for the target production. searches inside this
	 * target production for a property that has been marked as wrappee.
	 * 
	 * @return name of the wrappee property in the target production or null if
	 *         not found.
	 */
	public String getWrappeePropertyName() {
		NGrammar grammar = parent.production.getGrammar();
		NProduction production = grammar.findProduction(origNTerminal);
		if (production == null)
			return null;
		if (production.choices.size() != 1)
			throw new UnsupportedOperationException(
					"Cannot wrap around production with multiple choices.");
		for (NAbstractValue unit : production.choices.get(0).getUnits())
			if (unit.isWrappee())
				return unit.genPropertyName();
		return null;
	}

	@Override
	public NAbstractValue cloneValue() {
		return new NNonTerminal(parent, type, origNTerminal);
	}

	@Override
	protected void adjustFrom(NAbstractValue template) {
		super.adjustFrom(template);
		this.wrapsAroundType = ((NNonTerminal) template).wrapsAroundType;
	}
}
