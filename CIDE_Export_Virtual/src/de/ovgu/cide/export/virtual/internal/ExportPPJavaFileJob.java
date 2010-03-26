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
import de.ovgu.cide.export.CopiedNaiveASTFlattener;
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
		ast=options.refactorAST(ast,sourceFile);
		CopiedNaiveASTFlattener pp = options.getPrettyPrinter(sourceFile);
		ast.accept(pp);
		return pp.getResult();
	}
}
