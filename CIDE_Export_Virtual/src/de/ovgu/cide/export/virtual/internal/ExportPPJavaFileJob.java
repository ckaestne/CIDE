package de.ovgu.cide.export.virtual.internal;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;

import cide.gparser.ParseException;
import de.ovgu.cide.export.BaseExportJob;
import de.ovgu.cide.export.BaseJavaFileExporter;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.jdt.JDTParserWrapper;

public class ExportPPJavaFileJob extends BaseJavaFileExporter {

	private BaseExportJob exporter;
	private IPPExportOptions options;

	public ExportPPJavaFileJob(IContainer folder, IFile file,
			ICompilationUnit compUnit, IProgressMonitor monitor,
			BaseExportJob exporter, IPPExportOptions options) {
		super(folder, file, compUnit, monitor);
		this.exporter = exporter;
		this.options = options;
	}

	public void execute() throws CoreException, FeatureModelNotFoundException {

		ColoredSourceFile sourceFile = ColoredSourceFile
				.getColoredSourceFile(file);
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

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sourceFile.getColorManager().setTemporaryMode(false);
		}
	}

	private String renderASTPP(ColoredSourceFile sourceFile)
			throws JavaModelException, CoreException, ParseException {
		CompilationUnit ast = JDTParserWrapper.parseCompilationUnit(compUnit);
		PPPrettyPrinter pp = new PPPrettyPrinter(sourceFile.getColorManager(),
				sourceFile.getProject(), options);
		ast.accept(pp);
		return pp.getResult();
	}
}
