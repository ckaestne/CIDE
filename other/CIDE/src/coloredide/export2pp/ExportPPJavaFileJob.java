package coloredide.export2pp;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;

import coloredide.export.BaseExportJob;
import coloredide.export.BaseJavaFileExporter;
import coloredide.features.source.ColoredJavaSourceFile;
import coloredide.features.source.IColoredJavaSourceFile;

public class ExportPPJavaFileJob extends BaseJavaFileExporter {

	private BaseExportJob exporter;

	public ExportPPJavaFileJob(IContainer folder, IFile file,
			ICompilationUnit compUnit, IProgressMonitor monitor,
			BaseExportJob exporter) {
		super(folder, file, compUnit, monitor);
		this.exporter = exporter;
	}

	public void execute() throws CoreException {

		compUnit = (ICompilationUnit) compUnit.getWorkingCopy(monitor);
		IColoredJavaSourceFile sourceFile = ColoredJavaSourceFile
				.getColoredJavaSourceFile(compUnit);
		sourceFile.getColorManager().setTemporaryMode(true);
		try {

			String ppJavaStr = renderASTPP(sourceFile);

			IContainer compilationUnitTargetDirectory = exporter
					.getBaseFolder();
			IFile baseFile = compilationUnitTargetDirectory.getFile(folder
					.getProjectRelativePath().append(
							compUnit.getResource().getName()));
			InputStream source = new ByteArrayInputStream(ppJavaStr.getBytes());
			Export2PPJob.createFile(baseFile, source, monitor);

		} finally {
			compUnit.discardWorkingCopy();
			sourceFile.getColorManager().setTemporaryMode(false);
		}
	}

	private String renderASTPP(IColoredJavaSourceFile sourceFile) throws JavaModelException, CoreException {
		sourceFile.refreshAST();
		CompilationUnit ast = sourceFile.getAST();
		PPPrettyPrinter pp = new PPPrettyPrinter(sourceFile.getColorManager(),
				sourceFile.getProject());
		ast.accept(pp);
		return pp.getResult();
	}
}
