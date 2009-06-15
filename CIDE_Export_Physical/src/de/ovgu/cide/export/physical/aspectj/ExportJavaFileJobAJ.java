package de.ovgu.cide.export.physical.aspectj;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import cide.gparser.ParseException;
import de.ovgu.cide.export.BaseJavaFileExporter;
import de.ovgu.cide.export.physical.ahead.Export2JakJob;
import de.ovgu.cide.export.physical.internal.DerivativeComparator;
import de.ovgu.cide.export.physical.internal.FeatureFinderVisitor;
import de.ovgu.cide.export.physical.internal.LocalVariableHelper;
import de.ovgu.cide.export.physical.internal.RefactoringColorManager;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.jdt.JDTParserWrapper;

public class ExportJavaFileJobAJ extends BaseJavaFileExporter {

	private Export2AspectJJob exporter;

	public ExportJavaFileJobAJ(IContainer folder, IFile file,
			ICompilationUnit compUnit, IProgressMonitor monitor,
			Export2AspectJJob exporter) {
		super(folder, file, compUnit, monitor);
		this.exporter = exporter;
	}

	public void execute() throws CoreException {

		compUnit = (ICompilationUnit) compUnit.getWorkingCopy(monitor);
		ColoredSourceFile sourceFile = null;
		try {
			sourceFile = ColoredSourceFile.getColoredSourceFile(file);
			sourceFile.getColorManager().setTemporaryMode(true);

			// get file name without extension
			String classname = file.getName();
			int pos;
			if ((pos = classname.lastIndexOf('.')) > 0)
				classname = classname.substring(0, pos);

			CompilationUnit ast = JDTParserWrapper.parseJavaFile(file);
			RefactoringColorManager refactoringColorManager = new RefactoringColorManager(
					sourceFile.getColorManager(), ast, sourceFile.getResource());

			FeatureFinderVisitor featureFinder = new FeatureFinderVisitor(
					sourceFile);
			ast.accept(featureFinder);
			seenDerivatives.addAll(featureFinder.seenColors);
			LocalVariableHelper.cacheCompilationUnit(ast);

			List<Set<IFeature>> refactoringOrder1 = getRefactoringOrder(featureFinder.seenColors);
			// Create all aspects
			Iterator<Set<IFeature>> iter = refactoringOrder1.iterator();
			while (iter.hasNext()) {
				Set<IFeature> IFeature = iter.next();
				if (!IFeature.isEmpty()
						&& !(exporter.allAspects.containsKey(IFeature))) {
					AspectJCompilationUnit featureAspect = new AspectJCompilationUnit(
							ast.getAST(), exporter.getDerivativeName(IFeature));
					// hier declare parents einfügen (refactoringorder.tostring)
					featureAspect.setPrecedenceDeclaration(refactoringOrder1,
							exporter);
					exporter.allAspects.put(IFeature, featureAspect);
				}
			}

			// String compilationUnitTargetLayer = "base";
			IContainer compilationUnitTargetDirectory = exporter
					.getBaseFolder();

			List<Set<IFeature>> refactoringOrder = getRefactoringOrder(featureFinder.seenColors);
			for (Set<IFeature> derivative : refactoringOrder) {

				if (derivative.isEmpty()) {
					continue;
				}

				IFolder featureDir = null;
				if (derivative.size() == 1) {
					featureDir = exporter.getFeatureDirectory(derivative
							.iterator().next());
				} else if (derivative.size() > 1)
					featureDir = exporter.getDerivativeFolder(monitor,
							derivative);

				if (featureDir == null)
					continue;

				TypeDeclaration mainType = null;
				if (ast.types().size() > 0
						&& ast.types().get(0) instanceof TypeDeclaration)
					mainType = (TypeDeclaration) ast.types().get(0);

				// String folder = exporter.getDerivativeName(derivative);

				if (derivative.equals(refactoringColorManager.getColors(ast))
						|| (mainType != null && derivative
								.equals(refactoringColorManager
										.getColors(mainType)))) {
					compilationUnitTargetDirectory = featureDir;
					// compilationUnitTargetLayer = folder;
					continue;
				}

				// AspectJCompilationUnit aspect = new
				// AspectJCompilationUnit(ast.getAST(),derivative.toString());
				// AspectJCompilationUnit aspect =
				// exporter.allAspects.get(derivative);
				AspectJFeatureRefactorer featureRefactorer = new AspectJFeatureRefactorer(
						derivative, exporter.getDerivativeName(derivative),
						exporter.allAspects, refactoringColorManager);
				ast.accept(featureRefactorer);
			}
			IFile baseFile = compilationUnitTargetDirectory.getFile(folder
					.getProjectRelativePath().append(classname + ".java"));
			InputStream source = new ByteArrayInputStream(
					("// This class is manipulated by the aspects"
							+ refactoringOrder + "\n\n" + ast.toString())
							.getBytes());
			Export2JakJob.createFile(baseFile, source, monitor);

			//
		} catch (FeatureModelNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			compUnit.discardWorkingCopy();
			if (sourceFile != null)
				sourceFile.getColorManager().setTemporaryMode(false);
		}
	}

	/**
	 * sets a fixed refactoring order. higher-level derivatives are refactored
	 * before lower-level refactorings. first-order refactorings are refactored
	 * before features. elements on the same level are refactored in a fixed
	 * order (using IFeature.compare)
	 * 
	 * @param seenColors
	 * @return
	 */
	private List<Set<IFeature>> getRefactoringOrder(
			Set<Set<IFeature>> derivatives) {
		List<Set<IFeature>> list = new ArrayList<Set<IFeature>>(derivatives);
		Collections.sort(list, new DerivativeComparator());
		return list;
	}
}
