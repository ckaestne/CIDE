package de.ovgu.cide.typing.internal;

import java.util.List;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Before;
import org.junit.Test;

import de.ovgu.cide.features.FeatureModelManager;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.typing.internal.manager.EvaluationStrategyManager;
import de.ovgu.cide.typing.internal.manager.TypingExtensionManager;
import de.ovgu.cide.typing.model.IEvaluationStrategy;
import de.ovgu.cide.typing.model.ITypingProvider;

public class ValidateProjectActionTest {

	private IProject project;

	@Before
	public void setUp() throws Exception {

		project = ResourcesPlugin.getWorkspace().getRoot()
				.getProject("NoError");
		if (!project.isOpen())
			project.open(null);
	}

	@Test
	public void testGetTypingProvider() {
		List<ITypingProvider> providers = TypingExtensionManager.getInstance()
				.getTypingProviders(project);
		Assert.assertTrue("No providers found", providers.size() > 0);

	}

	@Test
	public void testGetEvaluationStrategy()
			throws FeatureModelNotFoundException {
		FeatureModelManager.getInstance().getFeatureModel(project);
		Assert.assertEquals(FeatureModelManager.getInstance()
				.getActiveFeatureModelProvider().getId(),
				"de.ovgu.cide.fm.list");

		IEvaluationStrategy strategy = EvaluationStrategyManager.getInstance()
				.getEvaluationStrategy(project);
		Assert.assertNotNull(strategy);
	}

	@Test
	public void testRecheckProjectJob() throws CoreException {
		TypingManager tm = new TypingManager();
		tm.register();
		WorkspaceJob op = new TypecheckProjectJob(new IProject[] { project },
				tm);
		op.runInWorkspace(new NullProgressMonitor());
		tm.unregister();
		IFile file = project.getFile("test.b");
		assert file.exists();
		IMarker[] markers = file.findMarkers(
				TypingMarkerFactory.MARKER_TYPE_ID, true,
				IResource.DEPTH_INFINITE);
		Assert.assertTrue("keine Marker erstellt", markers.length > 0);
	}

	@Test
	public void testValidateProjectAction() {
		ForceValidationAction action = new ForceValidationAction();
		action.getProjects().clear();
		action.getProjects().add(project);
		action.run(null);
	}
}
