package coloredide.export2jak;

import org.junit.Test;

import coloredide.export2jak.AbstractExportWorkspaceTest.TestKind;
import coloredide.export2jak.JakExportOptions.OutputType;

public class ExportFSTComposerFormat extends AbstractExportWorkspaceTest {

	@Test
	public void testFSTComposerFormat() {
		OutputType oldOutputType = JakExportOptions.OUTPUTTYPE;
		JakExportOptions.OUTPUTTYPE=OutputType.FST_JAVA;
		testFolder("FSTComposer", TestKind.ExtractOneFeature);
		JakExportOptions.OUTPUTTYPE=oldOutputType;
	}
}
