package de.ovgu.cide.tools.featureview;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;

import de.ovgu.cide.CIDECorePlugin;
import de.ovgu.cide.ColorListChangedEvent;

public class ProjectionKindManager {
	private ProjectionKindManager() {
	}

	private static final ProjectionKindManager instance = new ProjectionKindManager();

	public static ProjectionKindManager getInstance() {
		return instance;
	}

	public static enum ProjectionKind {
		FEATURE, VARIANT
	};

	private ProjectionKind currentProjectionKind = ProjectionKind.FEATURE;

	private void setProjectionKind(ProjectionKind kind) {
		currentProjectionKind = kind;
		CIDECorePlugin.getDefault().notifyListeners(
				new ColorListChangedEvent(this, null, kind));
	}

	public ProjectionKind getProjectionKind() {
		return currentProjectionKind;
	}

	// Style = radio button
	public class ChangeProjectionKind extends Action {
		private ProjectionKind kind;

		public ChangeProjectionKind(ProjectionKind kind) {
			super(kind == ProjectionKind.FEATURE ? "View on a feature"
					: "View on a variant", AS_RADIO_BUTTON);
			this.kind = kind;
			setChecked(currentProjectionKind == kind);
		}

		@Override
		public void run() {
			setProjectionKind(kind);
		}
	}

	public List<Action> getActions() {
		ArrayList<Action> list = new ArrayList<Action>();
//		list.add(new ChangeProjectionKind(ProjectionKind.NONE));
		list.add(new ChangeProjectionKind(ProjectionKind.FEATURE));
		list.add(new ChangeProjectionKind(ProjectionKind.VARIANT));
		return list;
	}

	public static boolean isVariantView() {
		return getInstance().getProjectionKind()==ProjectionKind.VARIANT;
	}

}
