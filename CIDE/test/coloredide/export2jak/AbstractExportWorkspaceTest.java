package coloredide.export2jak;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.ui.PlatformUI;
import org.junit.AfterClass;
import org.junit.Before;

import coloredide.export.LocalVariableHelper;
import coloredide.export2jak.JakExportOptions.OutputType;
import coloredide.export2jak.ast.JakClassRefinement;
import coloredide.export2jak.ast.JakCompilationUnit;
import coloredide.features.Feature;
import coloredide.features.FeatureManager;
import coloredide.features.source.ColoredJavaSourceFile;
import coloredide.features.source.IColorManager;
import coloredide.features.source.IColoredJavaSourceFile;

public abstract class AbstractExportWorkspaceTest {

	enum TestKind {
		ExtractOneFeature, ExtractTwoFeatures, ExpectColorCheckFailOnly, ExpectColorCheckPassOnly
	};

	protected HashSet<Feature> color1;
	private HashSet<Feature> color2;
	private HashSet<Feature> colorDerivative;
	protected boolean ENABLE_METHODOBJECTS = false;

	public AbstractExportWorkspaceTest() {
		Feature c1 = FeatureManager.getFeatures().get(0);
		Feature c2 = FeatureManager.getFeatures().get(1);
		color1 = new HashSet<Feature>();
		color1.add(c1);
		color2 = new HashSet<Feature>();
		color2.add(c2);
		colorDerivative = new HashSet<Feature>();
		colorDerivative.add(c1);
		colorDerivative.add(c2);
	}

	protected void testFolder(String name, TestKind testKind) {

		try {
			IProject project = getOpenProject(name);
			IColoredJavaSourceFile source = getSourceFile(name, "Main.java");

			LocalVariableHelper.cacheCompilationUnit(source.getAST());

			checkColors(source, testKind == TestKind.ExpectColorCheckFailOnly);

			if (testKind == TestKind.ExtractOneFeature
					|| TestKind.ExtractTwoFeatures == testKind) {
				CompilationUnit ast = getAST(source);
				RefactoringColorManager refactoringColorManager = new RefactoringColorManager(
						source.getColorManager(), ast);

				ast.accept(new ReturnExtractionHelper(refactoringColorManager));

				if (ENABLE_METHODOBJECTS)
					ast.accept(new MethodObjectHelper.MethodObjectCreator(
							refactoringColorManager, true));

				if (TestKind.ExtractTwoFeatures == testKind) {
					String generatedDerivative = extractFeature(ast,
							refactoringColorManager, colorDerivative);
					JakCodeComparer.assertCodeEquivalent(getFileContent(name,
							"Derivative.jak"), generatedDerivative,
							"Derivative-Comparison: ");
				}

				String generatedRefinement = extractFeature(ast,
						refactoringColorManager, color1);
//				JakCodeComparer.assertCodeEquivalent(getFileContent(name,
//				"Main.jak"), ast.toString(), "Main-Comparison: ");//TMP
				System.out.println(generatedRefinement);
				JakCodeComparer.assertCodeEquivalent(getFileContent(name,
						"Refinement.jak"), generatedRefinement,
						"Feature 1-Comparison: ");

				if (TestKind.ExtractTwoFeatures == testKind) {
					String generatedRefinement2 = extractFeature(ast,
							refactoringColorManager, color2);
					JakCodeComparer.assertCodeEquivalent(getFileContent(name,
							"Refinement2.jak"), generatedRefinement2,
							"Feature 2-Comparison: ");
				}

				JakCodeComparer.assertCodeEquivalent(getFileContent(name,
						"Main.jak"), ast.toString(), "Main-Comparison: ");
			}
		} catch (CoreException e) {
			Assert.fail(e.toString());
		}
	}

	protected String getFileContent(String project, String filen) {
		IFile file = getFile(project, filen);

		StringBuffer out = new StringBuffer();
		try {
			InputStream stream = file.getContents();
			byte[] b = new byte[4096];
			for (int n; (n = stream.read(b)) != -1;) {
				out.append(new String(b, 0, n));
			}
		} catch (Exception e) {
			Assert.fail(e.toString());
		}
		return out.toString();
	}

	protected String extractFeature(CompilationUnit compUnit,
			IColorManager colorManager, Set<Feature> derivative) {
		JakCompilationUnit output = new JakCompilationUnit(compUnit.getAST(),
				compUnit, "l");
		output.adjustImports(compUnit, derivative, colorManager);
		List<TypeDeclaration> types = compUnit.types();
		if (types.isEmpty())
			return "";
		TypeDeclaration mainType = types.get(0);
		if (mainType != null) {
			output.setRefinement(new JakClassRefinement(output, compUnit
					.getAST(), mainType));
			BaseFeatureRefactorer featureRefactorer = new JakFeatureRefactorer(
					derivative, output, colorManager);
			mainType.accept(featureRefactorer);
		}

		return output.getSource();
	}

	protected CompilationUnit getAST(IColoredJavaSourceFile source) {
		CompilationUnit ast = null;
		try {
			ast = source.getAST();
		} catch (JavaModelException e) {
			Assert.fail(e.toString());
		} catch (CoreException e) {
			Assert.fail(e.toString());
		}
		return ast;
	}

	protected void checkColors(IColoredJavaSourceFile source) {
		checkColors(source, false);
	}

	private void checkColors(IColoredJavaSourceFile source, boolean expectErrors) {
		JakColorChecker colorChecker = new JakColorChecker(source
				.getColorManager());
		getAST(source).accept(colorChecker);

		if (expectErrors)
			Assert
					.assertTrue(
							"Colorchecker marked AST as valid, although colors are not supported by JakRefactoring",
							colorChecker.foundUnsupportedColoring());
		else
			Assert.assertFalse("Coloring unsupported: "
					+ colorChecker.getUnsupportedColorings().toString(),
					colorChecker.foundUnsupportedColoring());
	}

	protected IColoredJavaSourceFile getSourceFile(String projectName,
			String fileName) {
		IFile file = getFile(projectName, fileName);
		ICompilationUnit compUnit = (ICompilationUnit) JavaCore.create(file);
		Assert
				.assertNotNull("file " + fileName + " not a java file?",
						compUnit);
		return ColoredJavaSourceFile.getColoredJavaSourceFile(compUnit);
	}

	private IFile getFile(String projectName, String fileName) {
		IProject project = getProject(projectName);

		IFile file = project.getFile(fileName);
		Assert.assertTrue("file " + fileName + " not found", file != null
				&& project.exists());
		return file;
	}

	protected IProject getProject(String projectName) {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
				projectName);
		Assert.assertTrue("project " + projectName + " not found",
				project != null && project.exists());
		return project;
	}

	protected IProject getOpenProject(String name) throws CoreException {
		IProject project = getProject(name);
		if (!project.isOpen())
			project.open(null);
		project.refreshLocal(IResource.DEPTH_INFINITE, null);
		return project;
	}

	@Before
	public void setup() {
		JakExportOptions.resetDefaultAHEAD();
		JakExportOptions.OUTPUTTYPE = OutputType.JAK;
		ENABLE_METHODOBJECTS = false;
	}

	@AfterClass
	public static void closeAllProjects() throws CoreException {
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot()
				.getProjects();
		for (IProject project : projects) {
			if (project.isOpen())
				project.close(null);
		}
	}

	@AfterClass
	public static void closeAllEditors() throws CoreException {
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.closeAllEditors(false);

	}
}
