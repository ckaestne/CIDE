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

package de.ovgu.cide.utils;

import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.IAction;

import cide.gast.IASTNode;
import cide.gast.IASTVisitor;
import cide.gast.ISourceFile;
import cide.gast.Property;
import de.ovgu.cide.features.FeatureModelManager;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.ColoredSourceFileIteratorJob;
import de.ovgu.cide.features.source.DirectoryColorManager;

public class ColorFileUpdater extends AbstractCIDEProjectAction {

	public class DummyAstNode implements IASTNode {

		public void accept(IASTVisitor visitor) {
			// TODO Auto-generated method stub

		}

		public IASTNode deepCopy() {
			// TODO Auto-generated method stub
			return null;
		}

		public List<IASTNode> getChildren() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getDisplayName() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getId() {
			return "dummy$id";
		}

		public int getLength() {
			// TODO Auto-generated method stub
			return 0;
		}

		public Property getLocationInParent() {
			// TODO Auto-generated method stub
			return null;
		}

		public IASTNode getParent() {
			// TODO Auto-generated method stub
			return null;
		}

		public List<Property> getProperties() {
			// TODO Auto-generated method stub
			return null;
		}

		public Property getProperty(String name) {
			// TODO Auto-generated method stub
			return null;
		}

		public ISourceFile getRoot() {
			// TODO Auto-generated method stub
			return null;
		}

		public int getStartPosition() {
			// TODO Auto-generated method stub
			return 0;
		}

		public IASTNode getWrappee() {
			// TODO Auto-generated method stub
			return null;
		}

		public boolean isOptional() {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean isWrapper() {
			// TODO Auto-generated method stub
			return false;
		}

		public void notifyPropertyChanged(Property property) {
			// TODO Auto-generated method stub

		}

		public void remove() {
			// TODO Auto-generated method stub

		}

		public String render() {
			// TODO Auto-generated method stub
			return null;
		}

		public void replaceSubtreeWith(IASTNode newNode) {
			// TODO Auto-generated method stub

		}

		public void setId(String id) {
			// TODO Auto-generated method stub

		}

		public void setParent(IASTNode parentNode, Property parentProperty) {
			// TODO Auto-generated method stub

		}

		public void setParentProperty(Property parentProperty) {
			// TODO Auto-generated method stub

		}

	}

	public class UpdateColorFileJob extends ColoredSourceFileIteratorJob {

		private IFeature dummyColor;

		public UpdateColorFileJob(IProject project, String jobDescription,
				String jobPrefix) {
			super(project, jobDescription, jobPrefix);

			try {
				IFeatureModel fm = FeatureModelManager.getInstance().getFeatureModel(
						project);
				dummyColor = fm.getFeatures().iterator().next();
				boolean wasVisible = dummyColor.isVisible();
				dummyColor.setVisible(!wasVisible);
				dummyColor.setVisible(wasVisible);
			} catch (FeatureModelNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		protected void processSource(ColoredSourceFile source)
				throws CoreException {
			source.getColorManager().addColor(new DummyAstNode(), dummyColor);
			source.getColorManager()
					.removeColor(new DummyAstNode(), dummyColor);
		}

		@Override
		protected void processContainer(IContainer container,
				IFeatureModel featureModel, IProgressMonitor monitor)
				throws CoreException {
			super.processContainer(container, featureModel, monitor);
			DirectoryColorManager dcm = DirectoryColorManager
					.getColoredDirectoryManagerS(container, featureModel);
			IFile file = container.getFile(new Path("dummy$file.p"));
			dcm.addColor(file, dummyColor);
			dcm.removeColor(file, dummyColor);
		}
	}

	public void run(IAction action) {
		for (IProject p : resources) {
			WorkspaceJob op = new UpdateColorFileJob(p,
					"autoupdate color files", "");
			op.setUser(true);
			op.schedule();
		}
	}

}
