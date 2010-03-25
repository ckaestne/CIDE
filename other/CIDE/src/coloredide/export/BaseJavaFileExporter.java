package coloredide.export;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;

import coloredide.features.Feature;

public abstract class BaseJavaFileExporter {

	public Set<Set<Feature>> seenDerivatives = new HashSet<Set<Feature>>();

	protected ICompilationUnit compUnit;

	protected IProgressMonitor monitor;

	protected IFile file;

	protected IContainer folder;

	public BaseJavaFileExporter(IContainer folder, IFile file,
			ICompilationUnit compUnit, IProgressMonitor monitor) {
		this.compUnit = compUnit;
		this.monitor = monitor;
		this.file = file;
		this.folder = folder;
	}

	public abstract void execute() throws CoreException;

}