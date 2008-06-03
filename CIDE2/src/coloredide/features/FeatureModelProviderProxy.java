package coloredide.features;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class FeatureModelProviderProxy implements IFeatureModelProvider {

	private final IConfigurationElement configElement;

	public FeatureModelProviderProxy(IConfigurationElement configurationElement) {
		this.configElement = configurationElement;
		name = configElement.getAttribute("name");
		id = configElement.getAttribute("id");
	}

	private final String name;
	private final String id;
	private IFeatureModelProvider target = null;

	private void loadTarget() {
		try {
			target = (IFeatureModelProvider) configElement
					.createExecutableExtension("provider");
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Feature Model Provider: " + name + " (" + id + ")";
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public IFeatureModel getFeatureModel(IProject project)
			throws FeatureModelNotFoundException {
		if (target == null)
			loadTarget();
		return target.getFeatureModel(project);
	}

}