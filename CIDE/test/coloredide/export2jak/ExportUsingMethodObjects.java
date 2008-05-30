package coloredide.export2jak;

import junit.framework.Assert;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.junit.Before;
import org.junit.Test;

import coloredide.export.LocalVariableHelper;
import coloredide.export2jak.JakExportOptions.OutputType;
import coloredide.features.source.IColoredJavaSourceFile;

public class ExportUsingMethodObjects extends AbstractExportWorkspaceTest {

	public class MethodFinder extends ASTVisitor {

		private String methodName;
		private MethodDeclaration method;

		public MethodFinder(String methodName) {
			this.methodName = methodName;
		}

		@Override
		public boolean visit(MethodDeclaration node) {
			if (node.getName().toString().equals(methodName))
				this.method = node;
			return super.visit(node);
		}

	}

	private String projectName;
	private IColoredJavaSourceFile source;
	private RefactoringColorManager colorManager;
	private MethodDeclaration targetMethod;
	private CompilationUnit ast;

	public void setup(String projectName) throws CoreException {
		JakExportOptions.resetDefaultAHEAD();
		JakExportOptions.OUTPUTTYPE=OutputType.JAK;
		this.projectName = projectName;
		getOpenProject(projectName);
		this.source = getSourceFile(projectName, "Main.java");
		source.refreshAST();

		LocalVariableHelper.cacheCompilationUnit(source.getAST());
		checkColors(source);

		this.ast = getAST(source);
		for (IProblem p : ast.getProblems())
			System.out.println(p.getMessage());
		this.colorManager = new RefactoringColorManager(source
				.getColorManager(), ast);

		MethodFinder methodFinder = new MethodFinder("foo");
		ast.accept(methodFinder);
		Assert.assertNotNull(methodFinder.method);
		this.targetMethod = methodFinder.method;
	}
	
	@Before
	public void reset(){
		MethodObjectHelper.knownMethodObjectNames.clear();
	}

	@Test
	public void testSimpleMethodObjectTransformation() throws Exception {
		setup("SimpleMethodObject");
		MethodObjectHelper.moveMethodToMethodObject(targetMethod, colorManager);

		JakCodeComparer.assertCodeEquivalent(getFileContent(projectName,
				"MethodObject.javax"), ast.toString(), "Method Object: ");
	}

	@Test
	public void testMethodObjectMarker() throws Exception {
		setup("SimpleMethodObject");
		TypeDeclaration methodClass = MethodObjectHelper
				.moveMethodToMethodObject(targetMethod, colorManager);
		Assert.assertTrue(MethodObjectHelper.isMethodObjectClass(methodClass));
	}

	@Test
	public void testRefactoringOfMethodObject() throws Exception {
		setup("SimpleMethodObject");
		JakExportOptions.DEBUG_PRINTINNERCLASSREFINEMENTS = true;

		MethodObjectHelper.moveMethodToMethodObject(targetMethod, colorManager);

		String generatedRefinement = extractFeature(ast, colorManager, color1);
		JakCodeComparer.assertCodeEquivalent(getFileContent(projectName,
				"Refinement_S1.jak"), generatedRefinement,
				"Feature 1-Comparison: ");

		JakCodeComparer.assertCodeEquivalent(getFileContent(projectName,
				"Main_S1.jak"), ast.toString(), "Main-Comparison: ");
	}

	@Test
	public void testMethodObjectTransformationWithParamter() throws Exception {
		setup("MethodObjectWithParam2");
		MethodObjectHelper.moveMethodToMethodObject(targetMethod, colorManager);

		JakCodeComparer.assertCodeEquivalent(getFileContent(projectName,
				"MethodObject.javax"), ast.toString(),
				"Method Object: ");
	}
	
	@Test
	public void testRefactoringOfMethodObjectWithParameter() throws Exception {
		setup("MethodObjectWithParam2");
		JakExportOptions.DEBUG_PRINTINNERCLASSREFINEMENTS = true;

		MethodObjectHelper.moveMethodToMethodObject(targetMethod, colorManager);

		String generatedRefinement = extractFeature(ast, colorManager, color1);
		JakCodeComparer.assertCodeEquivalent(getFileContent(projectName,
				"Refinement_S1.jak"), generatedRefinement,
				"Feature 1-Comparison: ");

		JakCodeComparer.assertCodeEquivalent(getFileContent(projectName,
				"Main_S1.jak"), ast.toString(), "Main-Comparison: ");
	}

	

	@Test
	public void testMakeThisAccessExplicit() throws Exception {
		setup("MethodObjectWithParam2");
		MethodObjectHelper.makeThisAccessExplicit(targetMethod);

		JakCodeComparer.assertCodeEquivalent(getFileContent(projectName,
				"StaticMain.javax"), ast.toString(),
				"Method Object: ");
	}
	
	@Test
	public void testStaticMethodObjectTransformation() throws Exception {
		setup("MethodObjectWithParam2");
		MethodObjectHelper.moveMethodToMethodObject(targetMethod, colorManager, true);

		JakCodeComparer.assertCodeEquivalent(getFileContent(projectName,
				"StaticMethodObject.javax"), ast.toString(),
				"Method Object: ");
	}
	
	@Test
	public void testSimpleStaticMethodObjectTransformation() throws Exception {
		setup("SimpleMethodObject");
		MethodObjectHelper.moveMethodToMethodObject(targetMethod, colorManager, true);

		JakCodeComparer.assertCodeEquivalent(getFileContent(projectName,
				"StaticMethodObject.javax"), ast.toString(), "Static Method Object: ");
	}
	
	@Test
	public void testStaticMethodObject() throws Exception {
		setup("StaticMethodObject");
		JakExportOptions.DEBUG_PRINTINNERCLASSREFINEMENTS = true;

		MethodObjectHelper.moveMethodToMethodObject(targetMethod, colorManager,true);

		String generatedRefinement = extractFeature(ast, colorManager, color1);
		JakCodeComparer.assertCodeEquivalent(getFileContent(projectName,
				"Refinement_S1.jak"), generatedRefinement,
				"Feature 1-Comparison: ");

		JakCodeComparer.assertCodeEquivalent(getFileContent(projectName,
				"Main_S1.jak"), ast.toString(), "Main-Comparison: ");
	}

	// @Test
	// public void testComplexTry() {
	// testFolder("ComplexTry", TestKind.ExpectColorCheckFailOnly);
	// }

	// TODO: check unique variable names
	// TODO method parameters must be passed to MO constructor
}
