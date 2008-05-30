package coloredide.export2jak;

import org.junit.Test;

import coloredide.export2jak.AbstractExportWorkspaceTest.TestKind;

public class ExportReturnTest extends AbstractExportWorkspaceTest {

	@Test
	public void testValidReturn() {
		testFolder("ValidReturn", TestKind.ExtractOneFeature);
	}

	@Test
	public void testColoredReturn() {
		testFolder("ColoredReturn", TestKind.ExtractOneFeature);
	}
	
	@Test
	public void testTryInHookWithReturn() {
		testFolder("TryInHookWithReturn", TestKind.ExtractOneFeature);
	}
	
	
}
