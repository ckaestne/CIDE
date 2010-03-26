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

package de.ovgu.cide.colorfilter;
//package coloredide.colorfilter;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//
//import org.eclipse.core.resources.IContainer;
//import org.eclipse.core.resources.IFile;
//import org.eclipse.core.resources.IProject;
//import org.eclipse.core.resources.IResource;
//import org.eclipse.core.runtime.CoreException;
//
//import cide.gast.ASTNode;
//import cide.gast.ASTVisitor;
//import cide.gast.ISourceFile;
//import cide.gparser.ParseException;
//import coloredide.features.Feature;
//import coloredide.features.source.ColoredSourceFile;
//import coloredide.features.source.DirectoryColorManager;
//import coloredide.features.source.SourceFileColorManager;
//
//public class ColorFilterCache {
//
//	private IProject project;
//	private Collection<Feature> visibleFeatures;
//
//	ColorFilterCache(IProject project, Collection<Feature> visibleFeatures) {
//
//		this.project = project;
//		this.visibleFeatures = visibleFeatures;
//		fillCache();
//	}
//
//	private void fillCache() {
//		visibleResources.clear();
//		visibleResources.add(project);
//
//		cacheContainer(project);
//
//	}
//
//	private boolean cacheContainer(IContainer container) {
//		DirectoryColorManager dirColorManager = DirectoryColorManager
//				.getColoredDirectoryManagerS(container);
//
//		// entire folder colored?
//		Set<Feature> folderColors = dirColorManager.getFolderColors();
//		if (overlap(folderColors, visibleFeatures)) {
//			markAllVisible(container);
//			return true;
//		}
//
//		// recurse for every member
//		try {
//			boolean anyChildVisible = false;
//			for (IResource r : container.members()) {
//				if (r instanceof IContainer)
//					anyChildVisible |= cacheContainer((IContainer) r);
//				else if (r instanceof IFile)
//					anyChildVisible |= cacheFile(dirColorManager, (IFile) r);
//			}
//
//			if (anyChildVisible)
//				visibleResources.add(container);
//
//			return anyChildVisible;
//		} catch (CoreException e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//
//	private boolean cacheFile(DirectoryColorManager dirColorManager, IFile r) {
//		boolean visible = isFileVisible(dirColorManager, r);
//		if (visible)
//			visibleResources.add(r);
//		return visible;
//	}
//
//	private void markAllVisible(IContainer container) {
//		visibleResources.add(container);
//		try {
//			for (IResource r : container.members()) {
//				if (r instanceof IContainer)
//					markAllVisible((IContainer) r);
//				else
//					visibleResources.add(r);
//			}
//		} catch (CoreException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	private boolean isFileVisible(DirectoryColorManager dirColorManager,
//			IFile file) {
//		// file level
//		Set<Feature> fileColor = dirColorManager.getColors(file);
//		if (overlap(fileColor, visibleFeatures))
//			return true;
//
//		// ast level
//		ColoredSourceFile source = ColoredSourceFile.getColoredSourceFile(file);
//		if (!source.isColored())
//			return false;
//
//		try {
//			ISourceFile ast = source.getAST();
//			ASTColorFinder astcf = new ASTColorFinder(source.getColorManager());
//			if (ast != null)
//				ast.accept(astcf);
//			return astcf.foundVisibleFeature;
//		} catch (CoreException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			return false;
//		}
//
//		return false;
//	}
//
//	private class ASTColorFinder extends ASTVisitor {
//		private SourceFileColorManager colorManager;
//
//		ASTColorFinder(SourceFileColorManager astColorManager) {
//			this.colorManager = astColorManager;
//			this.foundVisibleFeature = false;
//		}
//
//		public boolean visit(ASTNode node) {
//			if (foundVisibleFeature)
//				return false;
//			Set<Feature> nodeColors = colorManager.getColors(node);
//			foundVisibleFeature = overlap(nodeColors, visibleFeatures);
//			return !foundVisibleFeature;
//		}
//
//		boolean foundVisibleFeature;
//	}
//
//	final private Set<IResource> visibleResources = new HashSet<IResource>();
//
//	/**
//	 * returns whether both sets overlap in one or more entries
//	 * 
//	 * @param a
//	 * @param b
//	 * @return
//	 */
//	private boolean overlap(Collection<Feature> a, Collection<Feature> b) {
//		for (Feature f : a)
//			if (b.contains(f))
//				return true;
//		return false;
//	}
//
//	boolean isVisible(IResource r) {
//		return visibleResources.contains(r);
//	}
//}
