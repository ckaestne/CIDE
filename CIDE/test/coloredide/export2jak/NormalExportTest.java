package coloredide.export2jak;

import org.junit.Test;

import coloredide.export2jak.AbstractExportWorkspaceTest.TestKind;

public class NormalExportTest extends AbstractExportWorkspaceTest {
	@Test
	public void testEmpty() {
		testFolder("Empty", TestKind.ExtractOneFeature);
	}

	@Test
	public void testNoExtraction() {
		testFolder("NoExtraction", TestKind.ExtractOneFeature);
	}

	@Test
	public void testMethodExtraction() {
		testFolder("MethodExtraction", TestKind.ExtractOneFeature);
	}

	@Test
	public void testSingleStatement() {
		testFolder("SingleStatement", TestKind.ExtractOneFeature);
	}

	@Test
	public void testAroundAdvice() {
		testFolder("AroundAdvice", TestKind.ExtractOneFeature);
	}

	@Test
	public void testSimpleHook() {
		testFolder("SimpleHook", TestKind.ExtractOneFeature);
	}

	@Test
	public void testHookWithParameterAndReturn() {
		testFolder("HookWithParameterAndReturn", TestKind.ExtractOneFeature);
	}

	@Test
	public void testInvalidHookWithTwoChanges() {
		testFolder("InvalidHookWithTwoChanges",
				TestKind.ExpectColorCheckFailOnly);
	}

	@Test
	public void testTryWithInner() {
		testFolder("TryWithInner", TestKind.ExtractOneFeature);
	}

	@Test
	public void testTryWithoutInner() {
		testFolder("TryWithoutInner", TestKind.ExtractOneFeature);
	}

	@Test
	public void testTryInBefore() {
		testFolder("TryInBefore", TestKind.ExtractOneFeature);
	}

	@Test
	public void testTryInHook() {
		testFolder("TryInHook", TestKind.ExtractOneFeature);
	}

	@Test
	public void testInvalidConditional() {
		testFolder("InvalidConditional", TestKind.ExpectColorCheckFailOnly);
	}

	@Test
	public void testInvalidIfTwoExceptions() {
		testFolder("InvalidIfTwoExceptions", TestKind.ExpectColorCheckFailOnly);
	}

	@Test
	public void testDoException() {
		testFolder("DoException", TestKind.ExtractOneFeature);
	}

	@Test
	public void testTryTwo() {
		testFolder("TryTwo", TestKind.ExtractOneFeature);
	}

	@Test
	public void testIfForWhileEtc() {
		testFolder("IfForWhileEtc", TestKind.ExtractOneFeature);
	}

	@Test
	public void testNestedTryBlocks() {
		testFolder("NestedTryBlocks", TestKind.ExtractOneFeature);
	}

	@Test
	public void testColorOrderInExceptions() {
		testFolder("ColorOrderInExceptions", TestKind.ExtractTwoFeatures);
	}

	@Test
	public void testNestedIfs() {
		testFolder("NestedIfs", TestKind.ExpectColorCheckFailOnly);
	}

	@Test
	public void testNestedDifferentIfs() {
		testFolder("NestedDifferentIfs", TestKind.ExpectColorCheckFailOnly);
	}

	@Test
	public void testAccessToColloredVariableDeclaration() {
		testFolder("AccessToColloredVariableDeclaration",
				TestKind.ExtractTwoFeatures);
	}

	@Test
	public void testMultipleWriteAccessProblem() {
		testFolder("MultipleWriteAccessProblem", TestKind.ExtractOneFeature);
	}

	@Test
	public void testExtendsImplements() {
		testFolder("ExtendsImplements", TestKind.ExtractOneFeature);
	}

	@Test
	public void testImportRefinement() {
		testFolder("ImportRefinement", TestKind.ExtractOneFeature);
	}

	@Test
	public void testInterfaceRefinement() {
		testFolder("InterfaceRefinement", TestKind.ExtractOneFeature);
	}

	@Test
	public void testSuperRefinement() {
		testFolder("SuperRefinement", TestKind.ExtractOneFeature);
	}

	@Test
	public void testCatchedException() {
		testFolder("CatchedException", TestKind.ExtractOneFeature);
	}

	//
	// @Test
	// public void testConstructorRefinement() {
	// testFolder("ConstructorRefinement", TestKind.ExpectColorCheckFailOnly);
	// }

	@Test
	public void testStaticHook() {
		testFolder("StaticHook", TestKind.ExtractOneFeature);
	}

	@Test
	public void testTryInHookWithParam() {
		testFolder("TryInHookWithParam", TestKind.ExtractOneFeature);
	}

	@Test
	public void testNestedHooks() {
		testFolder("NestedHooks", TestKind.ExtractTwoFeatures);
	}

	@Test
	public void testNestedHooks2() {
		testFolder("NestedHooks2", TestKind.ExtractOneFeature);
	}

	@Test
	public void testTryInDerivative() {
		testFolder("TryInDerivative", TestKind.ExtractTwoFeatures);
	}

	@Test
	public void testTryInDerivativeMethodObject() {
		JakExportOptions.DEBUG_PRINTINNERCLASSREFINEMENTS = true;
		ENABLE_METHODOBJECTS = true;
		testFolder("TryInDerivativeMethodObject", TestKind.ExtractTwoFeatures);
	}

	@Test
	public void testQualifiedName() {
		testFolder("QualifiedName", TestKind.ExtractOneFeature);
	}

	@Test
	public void testTryWithWriteAccessInHook() {
		testFolder("TryWithWriteAccessInHook", TestKind.ExtractOneFeature);
	}
	
	@Test
	public void testParameterForHookMethod() {
		testFolder("ParameterForHookMethod", TestKind.ExtractTwoFeatures);
	}
	
	@Test
	public void testFirstColoredInTry() {
		testFolder("FirstColoredInTry", TestKind.ExtractOneFeature);
	}
	
	@Test
	public void testNestedHooksWithWriteAccess() {
		testFolder("NestedHooksWithWriteAccess", TestKind.ExtractOneFeature);
	}

	

}
