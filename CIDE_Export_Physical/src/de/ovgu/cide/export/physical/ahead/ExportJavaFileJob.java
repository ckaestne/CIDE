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

package de.ovgu.cide.export.physical.ahead;

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
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import cide.gparser.ParseException;
import de.ovgu.cide.export.BaseJavaFileExporter;
import de.ovgu.cide.export.physical.ahead.ast.JakClassRefinement;
import de.ovgu.cide.export.physical.ahead.ast.JakCompilationUnit;
import de.ovgu.cide.export.physical.internal.BasePhysicalExportJob;
import de.ovgu.cide.export.physical.internal.DerivativeComparator;
import de.ovgu.cide.export.physical.internal.FeatureFinderVisitor;
import de.ovgu.cide.export.physical.internal.IPhysicalOptions;
import de.ovgu.cide.export.physical.internal.LocalVariableHelper;
import de.ovgu.cide.export.physical.internal.RefactoringColorManager;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.jdt.JDTParserWrapper;

public class ExportJavaFileJob extends BaseJavaFileExporter {

	private BasePhysicalExportJob<IPhysicalOptions> exporter;

	public ExportJavaFileJob(IContainer folder, IFile file,
			ICompilationUnit compUnit, IProgressMonitor monitor,
			BasePhysicalExportJob<IPhysicalOptions> exporter) {
		super(folder, file, compUnit, monitor);
		this.exporter = exporter;
	}

	public void execute() throws CoreException {
		System.out.println(file);

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

			// String jakFilename = classname + ".jak";

			sourceFile.refreshAST();
			CompilationUnit ast = JDTParserWrapper.parseJavaFile(file);

			RefactoringColorManager refactoringColorManager = new RefactoringColorManager(
					sourceFile.getColorManager(), ast, sourceFile.getResource());

			FeatureFinderVisitor featureFinder = new FeatureFinderVisitor(
					sourceFile);
			ast.accept(featureFinder);
			seenDerivatives.addAll(featureFinder.seenColors);
			IContainer compilationUnitTargetDirectory = exporter
					.getBaseFolder();
			String compilationUnitTargetLayer = "base";

			if (featureFinder.seenColors.size() > 0) {
				addCideJakUtilImport(ast);
				LocalVariableHelper.cacheCompilationUnit(ast);
			}

			SuperTypeHelper.cacheAll(ast);

			ReturnExtractionHelper returnExtractionHelper = new ReturnExtractionHelper(
					refactoringColorManager);
			ast.accept(returnExtractionHelper);

			// create method objects
			ast.accept(new MethodObjectHelper.MethodObjectCreator(
					refactoringColorManager, true));

			handlePackageAndImportDeclarations(ast);

			List<Set<IFeature>> refactoringOrder = getRefactoringOrder(featureFinder.seenColors);
			for (Set<IFeature> derivative : refactoringOrder) {
				IFolder featureDir = null;
				if (derivative.size() == 1)
					featureDir = exporter.getFeatureDirectory(derivative
							.iterator().next());
				else if (derivative.size() > 1)
					featureDir = exporter.getDerivativeFolder(monitor,
							derivative);

				if (featureDir == null)
					continue;

				String layer = exporter.getDerivativeName(derivative);

				TypeDeclaration mainType = null;
				if (ast.types().size() > 0
						&& ast.types().get(0) instanceof TypeDeclaration)
					mainType = (TypeDeclaration) ast.types().get(0);

				if (derivative.equals(refactoringColorManager.getColors(ast))
						|| (mainType != null && derivative
								.equals(refactoringColorManager
										.getColors(mainType)))) {
					compilationUnitTargetDirectory = featureDir;
					compilationUnitTargetLayer = layer;
					continue;
				}

				JakCompilationUnit output = new JakCompilationUnit(
						ast.getAST(), ast, layer, exporter.getOptions());
				if (mainType != null) {
					output.adjustImports(ast, derivative,
							refactoringColorManager);
					output.setRefinement(new JakClassRefinement(output, ast
							.getAST(), mainType, exporter.getOptions()));
					BaseFeatureRefactorer featureRefactorer = new JakFeatureRefactorer(
							derivative, output, refactoringColorManager);
					mainType.accept(featureRefactorer);
				}

				createFilesForDerivative(output, featureDir);
			}
			createFilesForBase(ast, compilationUnitTargetDirectory,
					compilationUnitTargetLayer);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FeatureModelNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			compUnit.discardWorkingCopy();
			if (sourceFile != null)
				sourceFile.getColorManager().setTemporaryMode(false);
		}
	}

	private void handlePackageAndImportDeclarations(CompilationUnit ast) {
		if (exporter.getOptions().getFlattenPackages()) {
			ast.setPackage(null);
			for (int i = ast.imports().size() - 1; i >= 0; i--) {
				ImportDeclaration imp = (ImportDeclaration) ast.imports()
						.get(i);

				String removePackagePrefix = exporter.getOptions()
						.getRemovePackagePrefix();
				if (removePackagePrefix != null
						&& removePackagePrefix.length() > 0)
					if (imp.getName().toString()
							.startsWith(removePackagePrefix))
						ast.imports().remove(i);
			}
		}
	}

	private void addCideJakUtilImport(CompilationUnit cunit) {
		AST ast = cunit.getAST();
		ImportDeclaration imp = ast.newImportDeclaration();
		SimpleName de = ast.newSimpleName("de");
		QualifiedName deOvgu = ast.newQualifiedName(de, ast
				.newSimpleName("ovgu"));
		QualifiedName deOvguCide = ast.newQualifiedName(deOvgu, ast
				.newSimpleName("cide"));
		QualifiedName deOvguCideJakutil = ast.newQualifiedName(deOvguCide, ast
				.newSimpleName("jakutil"));

		imp.setName(deOvguCideJakutil);
		imp.setOnDemand(true);
		cunit.imports().add(imp);
	}

	private void createFilesForBase(CompilationUnit ast,
			IContainer compilationUnitTargetDirectory,
			String compilationUnitTargetLayer) throws CoreException {

		TypeDeclaration topLevelType = (TypeDeclaration) ast.types().get(0);

		if (exporter.getOptions().getMethodObjectsInStaticToplevelClass())
			createFilesForInnerClasses(topLevelType.bodyDeclarations(), ast
					.getPackage(), ast.imports(),
					compilationUnitTargetDirectory, compilationUnitTargetLayer);

		createFileForBase(topLevelType, ast, compilationUnitTargetDirectory,
				compilationUnitTargetLayer);

	}

	private void createFilesForInnerClasses(
			List<BodyDeclaration> bodyDeclarations,
			PackageDeclaration packageDecl, List<ImportDeclaration> imports,
			IContainer targetDirectory, String targetLayer)
			throws CoreException {
		List<TypeDeclaration> innerClasses = extractInnerClasses(bodyDeclarations);
		for (TypeDeclaration innerClass : innerClasses) {
			CompilationUnit container = innerClass.getAST()
					.newCompilationUnit();
			if (packageDecl != null)
				container.setPackage((PackageDeclaration) ASTNode.copySubtree(
						innerClass.getAST(), packageDecl));
			container.imports().addAll(
					ASTNode.copySubtrees(innerClass.getAST(), imports));
			container.types().add(innerClass);
			MethodObjectHelper.stripMethodObjectAnnotation(innerClass);
			MethodObjectHelper.stripStaticModifier(innerClass.modifiers());

			createFileForBase(innerClass, container, targetDirectory,
					targetLayer);
		}

	}

	private void createFileForBase(TypeDeclaration type, CompilationUnit ast,
			IContainer compilationUnitTargetDirectory,
			String compilationUnitTargetLayer) throws CoreException {
		IFile baseFile;
		if (exporter.getOptions().getFlattenPackages())
			baseFile = compilationUnitTargetDirectory.getFile(new Path(
					getFilename(type)));
		else
			baseFile = compilationUnitTargetDirectory.getFile(folder
					.getProjectRelativePath().append(getFilename(type)));
		String layerStr = exporter.getOptions().getFileHeader(
				compilationUnitTargetLayer);
		InputStream source = new ByteArrayInputStream((layerStr + ast
				.toString()).getBytes());
		Export2JakJob.createFile(baseFile, source, monitor);
	}

	/**
	 * removes (certain) inner classes from the AST and returns them as a list;
	 * 
	 * @param ast
	 * @return
	 */
	private List<TypeDeclaration> extractInnerClasses(
			List<BodyDeclaration> bodyDeclarations) {
		List<TypeDeclaration> innerClasses = new ArrayList<TypeDeclaration>();
		Iterator<BodyDeclaration> bodyDeclIterator = bodyDeclarations
				.iterator();
		while (bodyDeclIterator.hasNext()) {
			BodyDeclaration bodyDecl = bodyDeclIterator.next();
			if (bodyDecl instanceof TypeDeclaration)
				if (MethodObjectHelper
						.isMethodObjectClass((TypeDeclaration) bodyDecl)) {
					innerClasses.add((TypeDeclaration) bodyDecl);
					bodyDeclIterator.remove();
				}
		}
		return innerClasses;
	}

	private String getFilename(TypeDeclaration type) {
		return exporter.getOptions()
				.getFilename(type.getName().getIdentifier());
	}

	private void createFilesForDerivative(JakCompilationUnit output,
			IFolder featureDir) throws CoreException {
		if (exporter.getOptions().getMethodObjectsInStaticToplevelClass())
			createFilesForInnerClasses(
					output.getRefinement().getOtherMembers(), output
							.getPackage(), output.imports(), featureDir, output
							.getLayer());

		List<JakClassRefinement> innerRefs = new ArrayList<JakClassRefinement>();
		innerRefs.add(output.getRefinement());
		if (exporter.getOptions().getMethodObjectsInStaticToplevelClass())
			innerRefs.addAll(output.getRefinement().getInnerClassRefinements());

		for (JakClassRefinement innerRef : innerRefs) {
			if (innerRef.isEmpty())
				continue;
			MethodObjectHelper
					.stripMethodObjectAnnotation(innerRef.modifiers());
			MethodObjectHelper.stripStaticModifier(innerRef.modifiers());
			String sourceStr = output.getSource(innerRef);
			if (sourceStr.length() == 0)
				continue;

			IFile targetFile;
			String filename = exporter.getOptions().getFilename(
					output.getInnerClassName(innerRef));
			if (exporter.getOptions().getFlattenPackages())
				targetFile = featureDir.getFile(filename);
			else
				targetFile = featureDir.getFile(folder.getProjectRelativePath()
						.append(filename));
			InputStream source = new ByteArrayInputStream(sourceStr.getBytes());
			Export2JakJob.createFile(targetFile, source, monitor);
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
