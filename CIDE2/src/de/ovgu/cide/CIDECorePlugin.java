/**
    Copyright 2010 Christian Kästner

    This file is part of CIDE.

    CIDE is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.

    CIDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CIDE.  If not, see <http://www.gnu.org/licenses/>.

    See http://www.fosd.de/cide/ for further information.
 */

package de.ovgu.cide;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ISaveContext;
import org.eclipse.core.resources.ISaveParticipant;
import org.eclipse.core.resources.ISavedState;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import de.ovgu.cide.features.source.FileColorChangeListener;
import de.ovgu.cide.nature.CIDEProjectNature;
import de.ovgu.cide.navigator.FileColorUpdater;

public class CIDECorePlugin extends AbstractUIPlugin {

	public static final String ID_ASTVIEW = "coloredide.ASTView";
	public static final String ID_PREVIEW = "coloredide.previewview";
	public static final String ID_INTERACTION = "coloredide.InteractionsView";
	public static final String ID_COLOREDEDITOR = "coloredIDE.ColorEditor";

	private static final long serialVersionUID = 9L;
	public static final String ID = "de.ovgu.cide.core";

	private static CIDECorePlugin fgDefault;

	public CIDECorePlugin() {
		fgDefault = this;
		fileColorChangeListener = new FileColorChangeListener();
		fileColorUpdater = new FileColorUpdater();

	}

	public static String getPluginId() {
		return "org.eclipse.jdt.astview"; //$NON-NLS-1$
	}

	/**
	 * @return the shared instance
	 */
	public static CIDECorePlugin getDefault() {
		return fgDefault;
	}

	public static void log(IStatus status) {
		getDefault().getLog().log(status);
	}

	public static void logErrorMessage(String message) {
		log(new Status(IStatus.ERROR, getPluginId(), IStatus.ERROR, message,
				null));
	}

	public static void logErrorStatus(String message, IStatus status) {
		if (status == null) {
			logErrorMessage(message);
			return;
		}
		MultiStatus multi = new MultiStatus(getPluginId(), IStatus.ERROR,
				message, null);
		multi.add(status);
		log(multi);
	}

	public static boolean isCIDEProject(IProject project) {
		try {
			IProjectDescription description = project.getDescription();
			return Arrays.asList(description.getNatureIds()).contains(
					CIDEProjectNature.NATURE_ID);
		} catch (CoreException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void log(String message, Throwable e) {
		log(new Status(IStatus.ERROR, getPluginId(), IStatus.ERROR, message, e));
	}

	private IResourceChangeListener fileColorChangeListener;
	private FileColorUpdater fileColorUpdater;

	protected boolean readStateFrom(File target) {
		try {
			FileInputStream fw = new FileInputStream(target);
			ObjectInputStream out = new ObjectInputStream(fw);
			try {
				long version = out.readLong();
				if (version != serialVersionUID)
					return false;
				// colorCache = (BindingColorCache) out.readObject();
				return true;
			} finally {
				out.close();
			}
		} catch (Exception e) {
			// ignore
		}
		return false;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);

		ResourcesPlugin.getWorkspace().addResourceChangeListener(
				fileColorChangeListener);
		addColorChangeListener(fileColorUpdater);

		if (!load())
			initDefault();
	}

	public void stop(BundleContext context) throws Exception {

		ResourcesPlugin.getWorkspace().removeResourceChangeListener(
				fileColorChangeListener);
		removeColorChangeListener(fileColorUpdater);

		super.stop(context);
	}

	private boolean load() throws CoreException {
		ISaveParticipant saveParticipant = new MyWorkspaceSaveParticipant();
		ISavedState lastState = ResourcesPlugin.getWorkspace()
				.addSaveParticipant(this, saveParticipant);
		if (lastState == null)
			return false;

		IPath location = lastState.lookup(new Path("save"));
		if (location == null)
			return false;

		// the plugin instance should read any important state from the file.
		File f = getStateLocation().append(location).toFile();
		return readStateFrom(f);
	}

	private void initDefault() {
		// colorCache = new BindingColorCache();
	}

	protected void writeImportantState(File target) {
		try {
			FileOutputStream fw = new FileOutputStream(target);
			ObjectOutputStream out = new ObjectOutputStream(fw);
			out.writeLong(serialVersionUID);
			// out.writeObject(colorCache);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public class MyWorkspaceSaveParticipant implements ISaveParticipant {

		public void doneSaving(ISaveContext context) {
			CIDECorePlugin myPluginInstance = CIDECorePlugin.getDefault();

			// delete the old saved state since it is not necessary anymore
			int previousSaveNumber = context.getPreviousSaveNumber();
			String oldFileName = "save-" + Integer.toString(previousSaveNumber);
			File f = myPluginInstance.getStateLocation().append(oldFileName)
					.toFile();
			f.delete();
		}

		public void prepareToSave(ISaveContext context) throws CoreException {
			// TODO Auto-generated method stub

		}

		public void rollback(ISaveContext context) {
			CIDECorePlugin myPluginInstance = CIDECorePlugin.getDefault();

			// since the save operation has failed, delete the saved state we
			// have
			// just written
			int saveNumber = context.getSaveNumber();
			String saveFileName = "save-" + Integer.toString(saveNumber);
			File f = myPluginInstance.getStateLocation().append(saveFileName)
					.toFile();
			f.delete();
		}

		public void saving(ISaveContext context) throws CoreException {
			switch (context.getKind()) {
			case ISaveContext.FULL_SAVE:
				CIDECorePlugin myPluginInstance = CIDECorePlugin.getDefault();
				// save the plug-in state
				int saveNumber = context.getSaveNumber();
				String saveFileName = "save-" + Integer.toString(saveNumber);
				File f = myPluginInstance.getStateLocation().append(
						saveFileName).toFile();
				// if we fail to write, an exception is thrown and we do not
				// update
				// the path
				myPluginInstance.writeImportantState(f);
				context.map(new Path("save"), new Path(saveFileName));
				context.needSaveNumber();
				break;
			case ISaveContext.PROJECT_SAVE:
				// get the project related to this save operation
				// IProject project = context.getProject();
				// save its information, if necessary
				break;
			case ISaveContext.SNAPSHOT:
				// This operation needs to be really fast because
				// snapshots can be requested frequently by the
				// workspace.
				break;
			}
		}

	}

	private List<WeakReference<IColorChangeListener>> listeners = null;

	public void addColorChangeListener(IColorChangeListener listener) {
		if (listeners == null)
			listeners = new ArrayList<WeakReference<IColorChangeListener>>();
		listeners.add(new WeakReference<IColorChangeListener>(listener));
	}

	public void removeColorChangeListener(IColorChangeListener listener) {
		Iterator<WeakReference<IColorChangeListener>> iter = listeners
				.iterator();
		while (iter.hasNext()) {
			WeakReference<IColorChangeListener> reference = iter.next();
			IColorChangeListener referencedListener = reference.get();
			if (referencedListener == null || referencedListener == listener)
				iter.remove();
		}
	}

	public void notifyListeners(ASTColorChangedEvent event) {
		if (listeners != null)
			for (WeakReference<IColorChangeListener> ref : new ArrayList<WeakReference<IColorChangeListener>>(
					listeners)) {
				IColorChangeListener listener = ref.get();
				if (listener != null)
					listener.astColorChanged(event);
			}
	}

	public void notifyListeners(FileColorChangedEvent event) {
		if (listeners != null)
			for (WeakReference<IColorChangeListener> ref : new ArrayList<WeakReference<IColorChangeListener>>(
					listeners)) {
				IColorChangeListener listener = ref.get();
				if (listener != null)
					listener.fileColorChanged(event);
			}
	}

	public void notifyListeners(ColorListChangedEvent event) {
		if (listeners != null)
			for (WeakReference<IColorChangeListener> ref : new ArrayList<WeakReference<IColorChangeListener>>(
					listeners)) {
				IColorChangeListener listener = ref.get();
				if (listener != null)
					listener.colorListChanged(event);
			}
	}

}
