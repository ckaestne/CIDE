package coloredide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;

import org.eclipse.core.resources.IProject;

import cide.gast.ASTNode;
import coloredide.features.Feature;
import coloredide.features.source.ColoredSourceFile;

public class ColorListChangedEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	private final Collection<Change> changes;

	private final IProject project;

	public enum ChangeType {
		NAME, COLOR, VISIBILITY, ADD, REMOVE
	};

	public static class Change {
		final public Feature feature;
		final public ChangeType type;

		public Change(Feature feature, ChangeType type) {
			this.feature = feature;
			this.type = type;
		}
	}

	public ColorListChangedEvent(Object source, IProject project,
			Collection<Change> changes) {
		super(source);
		this.changes = changes;
		this.project = project;
	}

	public ColorListChangedEvent(Object source, IProject project, Change change) {
		super(source);
		this.changes = Collections.singleton(change);
		this.project = project;
	}

	public ColorListChangedEvent(Object source, IProject project,
			Feature feature, ChangeType type) {
		super(source);
		this.changes = Collections.singleton(new Change(feature, type));
		this.project = project;
	}

	public Collection<Change> getChanges() {
		return changes;
	}

	public IProject getProject() {
		return project;
	}

	public boolean anyChangeOf(ChangeType type) {
		for (Change change : changes)
			if (change.type == type)
				return true;
		return false;
	}

}
