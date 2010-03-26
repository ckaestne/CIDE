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

package de.ovgu.cide.fm.purevariants;

import java.util.Collections;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.RGB;

import com.ps.consul.eclipse.ui.mapping.Mapping;
import com.ps.consul.eclipse.ui.mapping.Rule;

import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.utils.ColorHelper;

public class RuleAdapter implements IFeature {

	private Rule rule;
	private boolean visible;
	private RGB rgb;
	private Mapping mapping;

	private static int colorCounter = 0;

	public RuleAdapter(Rule rule, Mapping mapping) {
		this.rule = rule;
		this.mapping = mapping;
		visible = rule.getAttributeValue("visible", "true").equals("true");
		rgb = decodeColor(rule.getAttributeValue("color", ""));
		if (rgb == null)
			setRGB(ColorHelper.DEFAULT_COLORS[++colorCounter
					% ColorHelper.DEFAULT_COLORS.length]);

	}

	public boolean canSetName() {
		return false;
	}

	public boolean canSetRGB() {
		return mapping != null;
	}

	public boolean canSetVisible() {
		return mapping != null;
	}

	public String getName() {
		return rule.getReadableCode();
	}

	public RGB getRGB() {
		return rgb;
	}

	public Set<IFeature> getRequiredFeatures() {
		return Collections.emptySet();
	}

	public boolean isVisible() {
		return true;
	}

	public void setName(String name) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	public void setRGB(RGB color) throws UnsupportedOperationException {
		rgb = color;
		try {
			rule.setAttributeValue("color", encodeColor(rgb));
			if (mapping != null)
				mapping.save();
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	private static String encodeColor(RGB rgb) {
		return rgb.red + "," + rgb.green + "," + rgb.blue;
	}

	private static RGB decodeColor(String colorStr) {
		int firstComma = colorStr.indexOf(',');
		int secondComma = colorStr.lastIndexOf(',');

		RGB rgb = null;

		if (firstComma > 0 && secondComma > 0 && secondComma > firstComma)
			try {
				int r = Integer.parseInt(colorStr.substring(0, firstComma));
				int g = Integer.parseInt(colorStr.substring(firstComma + 1,
						secondComma));
				int b = Integer.parseInt(colorStr.substring(secondComma + 1));
				rgb = new RGB(r, g, b);
			} catch (Exception e) {
				rgb = null;
			}

		return rgb;

	}

	public void setVisible(boolean isVisible)
			throws UnsupportedOperationException {
		this.visible = isVisible;
		try {
			rule.setAttributeValue("visible", visible ? "true" : "false");
			if (mapping != null)
				mapping.save();
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	public int compareTo(IFeature o) {
		return this.getName().compareTo(o.getName());
	}

	public Rule getRule() {
		return rule;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RuleAdapter)
			return rule.equals(((RuleAdapter) obj).getRule());
		return false;
	}

	@Override
	public int hashCode() {
		return rule.hashCode();
	}

	public Mapping getMapping() {
		return mapping;
	}

}
