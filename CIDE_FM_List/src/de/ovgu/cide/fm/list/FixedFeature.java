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

package de.ovgu.cide.fm.list;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;

import org.eclipse.swt.graphics.RGB;

public class FixedFeature implements Comparable<FixedFeature>, Serializable {

	private static final long serialVersionUID = 1L;

	private RGB rgb;

	private long id;

	public FixedFeature(long id, RGB rgb) {
		this.rgb = rgb;
		this.id = id;
	}

	RGB getRGB() {
		return rgb;
	}

	public long getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coloredide.features.IFeature#toString()
	 */
	public String toString() {
		return "Feature_" + id;
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.writeLong(id);
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		id = in.readLong();
	}

	private Object readResolve() throws ObjectStreamException {
		for (FixedFeature feature : FeatureManager.getFeatures()) {
			if (feature.getId() == id)
				return feature;
		}
		throw new InvalidObjectException("Feature with ID " + id
				+ " is not known.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coloredide.features.IFeature#compareTo(coloredide.features.Feature)
	 */
	public int compareTo(FixedFeature o) {
		if (this.id < o.getId())
			return -1;
		if (this.id > o.getId())
			return 1;
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coloredide.features.IFeature#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FixedFeature)
			return id == ((FixedFeature) obj).id;
		return super.equals(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coloredide.features.IFeature#hashCode()
	 */
	@Override
	public int hashCode() {
		return (int) id;
	}
}
