package coloredide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;

import cide.gast.ASTNode;
import coloredide.features.source.ColoredSourceFile;

public class FileColorChangedEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	private final Collection<IContainer> resources;

	public FileColorChangedEvent(Object source, IContainer folder) {
		super(source);
		this.resources = Collections.singleton(folder);
	}

	public FileColorChangedEvent(Object source, Collection<IContainer> folders) {
		super(source);
		assert folders != null && !folders.isEmpty();
		this.resources = folders;
	}

	public Collection<IContainer> getAffectedFolders() {
		return resources;
	}

}
