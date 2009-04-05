package de.ovgu.cide.typing.bali;

import java.util.Collections;
import java.util.Set;

import junit.framework.Assert;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;

import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.model.ITypingCheck;
import de.ovgu.cide.typing.model.ITypingProvider;

public class BaliTypingProviderTest {

	private IProject project;
	private ITypingProvider provider;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testGetProvider() throws CoreException {
		ITypingProvider provider = getProvider("NoError");
		Assert.assertNotNull(provider);
	}

	private ITypingProvider getProvider(String projectName)
			throws CoreException {

		project = ResourcesPlugin.getWorkspace().getRoot().getProject(
				projectName);
		Assert.assertNotNull(project);
		Assert.assertTrue("Project " + projectName + " does not exist", project
				.exists());

		if (!project.isOpen())
			project.open(null);

		provider = new BaliTypingExtension().createTypingProvider(project);
		return provider;
	}

	private ColoredSourceFile getTestFile()
			throws FeatureModelNotFoundException {
		return ColoredSourceFile
				.getColoredSourceFile(project.getFile("test.b"));
	}

	@Test
	public void testNoErrors() throws CoreException,
			FeatureModelNotFoundException {
		getProvider("NoError");
		provider.updateFile(Collections.singleton(getTestFile()), null);
		Set<ITypingCheck> checks = provider.getChecks();
		Assert.assertNotNull(checks);
		Assert.assertTrue("No checks generated", checks.size() > 0);

	}

	@Test
	public void testCheckingTwice() throws CoreException,
			FeatureModelNotFoundException {
		// checking the same file twice should return the same checks
		getProvider("NoError");
		provider.updateFile(Collections.singleton(getTestFile()), null);
		Set<ITypingCheck> checks1 = provider.getChecks();
		provider.updateFile(Collections.singleton(getTestFile()), null);
		Set<ITypingCheck> checks2 = provider.getChecks();
		Assert.assertEquals(checks1, checks1);
		Assert.assertEquals(checks1, checks2);
	}

	@Test
	public void testCheckAllAndCheckFileEqual() throws Exception {
		// checking that updateAll works as expected
		getProvider("NoError");
		provider.updateFile(Collections.singleton(getTestFile()), null);
		Set<ITypingCheck> checks1 = provider.getChecks();
		getProvider("NoError");
		provider.updateAll(null);
		Set<ITypingCheck> checks2 = provider.getChecks();
		Assert.assertEquals(checks1, checks2);
	}

}
