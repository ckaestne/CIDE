package de.ovgu.cide.typing.jdt;

import java.io.ByteArrayInputStream;
import java.util.Collections;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.junit.BeforeClass;
import org.junit.Test;

import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.jdt.JDTParserWrapper;
import de.ovgu.cide.typing.list.SubsetStrategy;
import de.ovgu.cide.typing.model.ITypingCheck;
import de.ovgu.cide.typing.model.ITypingProvider;

public class JDTTypingTest {

	private static IFile file;
	private static String javaContent = "class Test { int i; public void foo() { i++; } }";
	private static IProject project;

	@BeforeClass
	public static void setUpClass() throws Exception {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		project = root.getProject("test");
		project.open(null);
		file = project.getFile("Test.java");

		if (file.exists())
			file.delete(true, null);
		file.create(new ByteArrayInputStream(javaContent.getBytes()), true,
				null);

	}

	@Test
	public void testParser() throws Exception {
		JDTParserWrapper parser = new JDTParserWrapper(file);

		parser.getRoot();
	}

	@Test
	public void testCheckGeneration() throws FeatureModelNotFoundException {
		ITypingProvider typingprovider = new JDTTypingExtension()
				.createTypingProvider(project);
		typingprovider.updateFile(Collections.singletonList(ColoredSourceFile
				.getColoredSourceFile(file)),null);
		Assert.assertTrue("no checks found",
				typingprovider.getChecks().size() > 0);
	}

	@Test
	public void testCheckEvaluation() throws FeatureModelNotFoundException {
		ITypingProvider typingprovider = new JDTTypingExtension()
				.createTypingProvider(project);
		typingprovider.updateFile(Collections.singletonList(ColoredSourceFile
				.getColoredSourceFile(file)),null);

		SubsetStrategy strategy = new SubsetStrategy();
		for (ITypingCheck check : typingprovider.getChecks()) {
			Assert.assertTrue(check.evaluate(strategy));
		}
	}

}
