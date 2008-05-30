package coloredide.preview;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;

import coloredide.features.Feature;

public class PreviewManager {

	private static Map<IProject, PreviewFeatureSelection> featureSelection = new HashMap<IProject, PreviewFeatureSelection>();

	static PreviewFeatureSelection getFeatureSelection(IProject p) {
		PreviewFeatureSelection f = featureSelection.get(p);
		if (f == null) {
			f = new PreviewFeatureSelection(p);
			featureSelection.put(p, f);
		}
		return f;
	}

	public static void updatedShowFeature(Feature feature) {
		for (PreviewView view : views) {
			view.updateBrowser();
		}
	}

	private static Set<PreviewView> views = new HashSet<PreviewView>();

	public static void addEditor(PreviewView e) {
		views.add(e);
	}

	public static void removeEditor(PreviewView e) {
		views.add(e);
	}
}
