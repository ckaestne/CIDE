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

package de.ovgu.cide.export.physical.ahead;

import java.util.WeakHashMap;

import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;

/**
 * this class keeps references to all super calls that are meant as
 * Super/original calls to another layer, instead of super calls to the
 * superclass
 * 
 * @author cKaestner
 * 
 */
public class SuperCallHelper {

	private static Boolean dummy = new Boolean(true);

	/**
	 * second value has no meaning, is just required to use the weakhashmap as a
	 * weakhashset.
	 */
	private static WeakHashMap<SuperMethodInvocation, Boolean> superLayerMethodInvocations = new WeakHashMap<SuperMethodInvocation, Boolean>();

	private static WeakHashMap<SuperConstructorInvocation, Boolean> superLayerConstructorInvocations = new WeakHashMap<SuperConstructorInvocation, Boolean>();

	public static void addSuperLayerCall(SuperMethodInvocation call) {
		superLayerMethodInvocations.put(call, dummy);
	}

	public static void addSuperLayerCall(SuperConstructorInvocation call) {
		superLayerConstructorInvocations.put(call, dummy);
	}

	public static boolean isSuperLayerCall(SuperMethodInvocation call) {
		return superLayerMethodInvocations.get(call) == dummy;
	}

	public static boolean isSuperLayerCall(SuperConstructorInvocation call) {
		return superLayerConstructorInvocations.get(call) == dummy;
	}

}
