package de.ovgu.cide.fm.purevariants;

import java.util.Collections;
import java.util.Set;

import org.eclipse.swt.graphics.RGB;

import com.ps.consul.eclipse.ui.mapping.Rule;

import coloredide.features.IFeature;

public class RuleAdapter implements IFeature {

	private Rule rule;
	private RGB rgb;

	public RuleAdapter(Rule rule) {
		this.rule = rule;
		rgb = new RGB(250, 0, 0);
	}

	public boolean canSetName() {
		return false;
	}

	public boolean canSetRGB() {
		return true;
	}

	public boolean canSetVisible() {
		return false;
	}

	public String getName() {
		return rule.getReadableCode();
	}

	public RGB getRGB() {
		return rgb;
	}

	public Set<IFeature> getRequiredFeatures() {
		return Collections.EMPTY_SET;
	}

	public boolean isVisible() {
		return true;
	}

	public void setName(String name) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	public void setRGB(RGB color) throws UnsupportedOperationException {

		rgb = color;
	}

	public void setVisible(boolean isVisible)
			throws UnsupportedOperationException {
		throw new UnsupportedOperationException();

	}

	@Override
	public int compareTo(IFeature o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Rule getRule() {
		return rule;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RuleAdapter)
			return rule.equals(((RuleAdapter)obj).getRule());
		return false;
	}
	@Override
	public int hashCode() {
		return rule.hashCode();
	}

}
