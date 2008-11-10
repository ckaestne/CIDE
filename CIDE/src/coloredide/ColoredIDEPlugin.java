/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package coloredide;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.ISaveContext;
import org.eclipse.core.resources.ISaveParticipant;
import org.eclipse.core.resources.ISavedState;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import coloredide.features.bindings.BindingColorCache;
import coloredide.features.source.ASTRefreshListener;
import coloredide.validator.ValidationManager;

public class ColoredIDEPlugin extends AbstractUIPlugin {

	public static final String ID_ASTVIEW = "coloredide.ASTView";
	public static final String ID_PREVIEW = "coloredide.previewview";
	public static final String ID_INTERACTION = "coloredide.InteractionsView";
	public static final String ID_COLOREDEDITOR = "coloredIDE.ColorEditor";

	public static final int AST_VERSION = AST.JLS3;

	private static final long serialVersionUID = 8L;

	private static ColoredIDEPlugin fgDefault;

	public BindingColorCache colorCache;

	public ColoredIDEPlugin() {
		fgDefault = this;

	}

	public static String getPluginId() {
		return "org.eclipse.jdt.astview"; //$NON-NLS-1$
	}

	/**
	 * @return the shared instance
	 */
	public static ColoredIDEPlugin getDefault() {
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

	public static void log(String message, Throwable e) {
		log(new Status(IStatus.ERROR, getPluginId(), IStatus.ERROR, message, e));
	}

	private ValidationManager validator;

	private ASTRefreshListener astRefreshListener;

	protected boolean readStateFrom(File target) {
		try {
			FileInputStream fw = new FileInputStream(target);
			ObjectInputStream out = new ObjectInputStream(fw);
			try {
				long version = out.readLong();
				if (version != serialVersionUID)
					return false;
				colorCache = (BindingColorCache) out.readObject();
				return true;
			} finally {
				out.close();
			}
		} catch (Exception e) {
			// ignore
		}
		return false;
	}

	static class IncompatibleCideVersionsException extends Exception {
		private static final long serialVersionUID = 1L;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		// disable this plugin if CIDE2 is loaded
		if (Platform.getBundle("de.ovgu.cide.core") != null)
			throw new IncompatibleCideVersionsException();

		super.start(context);

		validator = new ValidationManager();
		astRefreshListener = new ASTRefreshListener();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(
				astRefreshListener);

		if (!load())
			initDefault();

	}

	public void stop(BundleContext context) throws Exception {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(
				astRefreshListener);
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
		colorCache = new BindingColorCache();
	}

	protected void writeImportantState(File target) {
		try {
			FileOutputStream fw = new FileOutputStream(target);
			ObjectOutputStream out = new ObjectOutputStream(fw);
			out.writeLong(serialVersionUID);
			out.writeObject(colorCache);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public class MyWorkspaceSaveParticipant implements ISaveParticipant {

		public void doneSaving(ISaveContext context) {
			ColoredIDEPlugin myPluginInstance = ColoredIDEPlugin.getDefault();

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
			ColoredIDEPlugin myPluginInstance = ColoredIDEPlugin.getDefault();

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
				ColoredIDEPlugin myPluginInstance = ColoredIDEPlugin
						.getDefault();
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

	public ValidationManager getValidator() {
		return validator;
	}

	private List<IColorChangeListener> listeners = null;

	public void addColorChangeListener(IColorChangeListener listener) {
		if (listeners == null)
			listeners = new ArrayList<IColorChangeListener>();
		listeners.add(listener);
	}

	public void removeColorChangeListener(IColorChangeListener listener) {
		listeners.remove(listener);
	}

	public void notifyListeners(ColorChangedEvent event) {
		colorCache.colorChanged(event);
		validator.colorChanged(event);
		if (listeners != null)
			for (IColorChangeListener listener : listeners) {
				listener.colorChanged(event);
			}
	}
}
