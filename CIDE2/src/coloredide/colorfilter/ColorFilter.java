//package coloredide.colorfilter;
//
//import java.util.HashMap;
//
//import org.eclipse.core.resources.IProject;
//import org.eclipse.core.resources.IResource;
//import org.eclipse.core.resources.IWorkspaceRoot;
//import org.eclipse.jface.viewers.TreePath;
//import org.eclipse.jface.viewers.Viewer;
//import org.eclipse.jface.viewers.ViewerFilter;
//
//import coloredide.features.FeatureManager;
//
//public class ColorFilter extends ViewerFilter {
//
//	@Override
//	public Object[] filter(Viewer viewer, Object parent, Object[] elements) {
//		if (parent instanceof TreePath)
//			parent = ((TreePath) parent).getLastSegment();
//
//		if (!(parent instanceof IResource) || parent instanceof IWorkspaceRoot)
//			return elements;
//		else
//			return super.filter(viewer, parent, elements);
//	}
//
//	private final HashMap<IProject, ColorFilterCache> caches = new HashMap<IProject, ColorFilterCache>();
//
//	@Override
//	public boolean select(Viewer viewer, Object parentElement, Object element) {
//		if (!(element instanceof IResource))
//			return true;
//
//		IResource r = (IResource) element;
//		IProject p = r.getProject();
//
//		ColorFilterCache cache = caches.get(p);
//		if (cache == null) {
//			cache = new ColorFilterCache(p, FeatureManager
//					.getVisibleFeatures(p));
//			caches.put(p, cache);
//		}
//
//		return cache.isVisible(r);
//	}
//
//}
