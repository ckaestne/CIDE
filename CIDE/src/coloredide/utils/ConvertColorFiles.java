package coloredide.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import coloredide.features.ASTID;
import coloredide.features.Feature;
import coloredide.features.source.CompilationUnitColorManager;
import coloredide.features.source.IColorManager;
import coloredide.features.source.IColoredJavaSourceFile;

/**
 * loads .color files in the CIDE format and stores them back in the CIDE2
 * format (.java.color). the old color files are not removed.
 * 
 * @author ckaestne
 * 
 */
public class ConvertColorFiles implements IObjectActionDelegate {
	public class DummySource implements IColoredJavaSourceFile {

		public CompilationUnit getAST() throws JavaModelException,
				CoreException {
			// TODO Auto-generated method stub
			return null;
		}

		public CompilationUnit getAST(ASTCreator creator)
				throws JavaModelException, CoreException {
			// TODO Auto-generated method stub
			return null;
		}

		public IColorManager getColorManager() {
			// TODO Auto-generated method stub
			return null;
		}

		public ICompilationUnit getCompilationUnit() {
			// TODO Auto-generated method stub
			return null;
		}

		public IProject getProject() {
			return project;
		}

		public boolean hasColors() {
			return false;
		}

		public void refreshAST() {
		}

	}

	public class Converter implements IResourceVisitor {

		public boolean visit(IResource resource) throws CoreException {
			if ("color".equals(resource.getFileExtension())
					&& resource instanceof IFile) {
				IFile colorFile = (IFile) resource;
				IPath relpath = colorFile.getProjectRelativePath();
				CompilationUnitColorManager manager = new CompilationUnitColorManager(
						colorFile, new DummySource());

				IFile newFile = project.getFile(relpath.removeFileExtension()
						.addFileExtension("java.color"));
				assert !newFile.exists();
				try {
					writeNewFormat(newFile, manager);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return true;
		}

		private void writeNewFormat(IFile newFile,
				CompilationUnitColorManager manager) throws IOException,
				CoreException {
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(b);
			out.writeLong(2l);
			out.writeObject(getIdMap(manager));
			out.close();
			ByteArrayInputStream source = new ByteArrayInputStream(b
					.toByteArray());
			newFile.create(source, true, null);
		}

		private Map<String, Set<Long>> getIdMap(
				CompilationUnitColorManager manager) {
			HashMap<String, Set<Long>> result = new HashMap<String, Set<Long>>();

			for (Map.Entry<ASTID, Set<Feature>> entry : manager
					.tmpnode2colors().entrySet()) {
				Set<Feature> features = entry.getValue();
				if (!features.isEmpty()) {
					Set<Long> ids = new HashSet<Long>();
					for (Feature feature : features) {
						ids.add(feature.id);
					}
					if (!ids.isEmpty())
						result.put(entry.getKey().id, ids);
				}
			}
			return result;
		}

	}

	private IProject project;

	public ConvertColorFiles() {
		// TODO Auto-generated constructor stub
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub

	}

	public void run(IAction action) {
		if (project != null) {
			try {
				project.accept(new Converter());
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	protected IProject getSelectedProject(ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Object selected = ((IStructuredSelection) selection)
					.getFirstElement();
			if (selected instanceof IProject)
				return (IProject) selected;
			if (selected instanceof IJavaProject)
				return ((IJavaProject) selected).getProject();
		}
		return null;
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.project = getSelectedProject(selection);
	}

}
