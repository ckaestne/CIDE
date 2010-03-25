package coloredide.export2jak;

import java.util.HashSet;
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
