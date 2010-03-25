package coloredide.export2AspectJ;

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

import coloredide.export.BaseJavaFileExporter;
import coloredide.export.DerivativeComparator;
import coloredide.export.FeatureFinderVisitor;
import coloredide.export.LocalVariableHelper;
import coloredide.features.Feature;
import coloredide.features.source.ColoredJavaSourceFile;
import coloredide.features.source.IColoredJavaSourceFile;

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
		IColoredJavaSourceFile sourceFile = ColoredJavaSourceFile
				.getColoredJavaSourceFile(compUnit);
		sourceFile.getColorManager().setTemporaryMode(true);
		try {

			// get file name without extension
			String classname = file.getName();
			int pos;
			if ((pos = classname.lastIndexOf('.')) > 0)
				classname = classname.substring(0, pos);


			sourceFile.refreshAST();
			CompilationUnit ast = sourceFile.getAST();
			FeatureFinderVisitor featureFinder = new FeatureFinderVisitor(sourceFile);
			ast.accept(featureFinder);
			seenDerivatives.addAll(featureFinder.seenColors);
			LocalVariableHelper.cacheCompilationUnit(ast);
			
			List<Set<Feature>> refactoringOrder1 = getRefactoringOrder(featureFinder.seenColors);
			//Create all aspects
			Iterator<Set<Feature>> iter = refactoringOrder1.iterator();
			while (iter.hasNext()) {
				Set<Feature> feature = iter.next();
				if(!feature.isEmpty() && !(exporter.allAspects.containsKey(feature))){
					AspectJCompilationUnit featureAspect = new AspectJCompilationUnit(ast.getAST(),exporter.getDerivativeName(feature));
					//hier declare parents einfügen (refactoringorder.tostring)
					featureAspect.setPrecedenceDeclaration(refactoringOrder1,exporter);
					exporter.allAspects.put(feature, featureAspect);
				}
			}

//			String compilationUnitTargetLayer = "base";
			IContainer compilationUnitTargetDirectory = exporter.getBaseFolder();
			
			List<Set<Feature>> refactoringOrder = getRefactoringOrder(featureFinder.seenColors);
			for (Set<Feature> derivative : refactoringOrder) {
				
				if (derivative.isEmpty()) {continue;}
				
				IFolder featureDir = null;
				if (derivative.size() == 1){
					featureDir = exporter.getFeatureDirectory(derivative.iterator().next());
				}
				else if (derivative.size() > 1)
					featureDir = exporter.getDerivativeFolder(monitor, derivative);

				if (featureDir == null)
					continue;

				
				TypeDeclaration mainType = null;
				if (ast.types().size() > 0
						&& ast.types().get(0) instanceof TypeDeclaration)
					mainType = (TypeDeclaration) ast.types().get(0);

//				String folder = exporter.getDerivativeName(derivative);
				
				if (derivative.equals(sourceFile.getColorManager().getColors(
						ast))
						|| (mainType != null && derivative.equals(sourceFile
								.getColorManager().getColors(mainType)))) {
					compilationUnitTargetDirectory = featureDir;
//					compilationUnitTargetLayer = folder;
					continue;
				}
				
				//AspectJCompilationUnit aspect = new AspectJCompilationUnit(ast.getAST(),derivative.toString());
//				AspectJCompilationUnit aspect = exporter.allAspects.get(derivative);
				AspectJFeatureRefactorer featureRefactorer = new AspectJFeatureRefactorer(
						derivative, exporter.getDerivativeName(derivative),
						exporter.allAspects, sourceFile.getColorManager());
				ast.accept(featureRefactorer);
			}
			IFile baseFile = compilationUnitTargetDirectory.getFile(folder
					.getProjectRelativePath().append(classname+".java"));
			InputStream source = new ByteArrayInputStream(("// This class is manipulated by the aspects"+refactoringOrder +"\n\n"+ ast.toString()).getBytes());
			baseFile.create(source, true, monitor);


			//
		} finally {
			compUnit.discardWorkingCopy();
			sourceFile.getColorManager().setTemporaryMode(false);
		}
	}

	/**
	 * sets a fixed refactoring order. higher-level derivatives are refactored
	 * before lower-level refactorings. first-order refactorings are refactored
	 * before features. elements on the same level are refactored in a fixed
	 * order (using feature.compare)
	 * 
	 * @param seenColors
	 * @return
	 */
	private List<Set<Feature>> getRefactoringOrder(Set<Set<Feature>> derivatives) {
		List<Set<Feature>> list = new ArrayList<Set<Feature>>(derivatives);
		Collections.sort(list, new DerivativeComparator());
		return list;
	}
}
