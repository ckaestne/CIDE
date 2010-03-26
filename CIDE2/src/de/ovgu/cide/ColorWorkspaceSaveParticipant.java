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

import org.eclipse.core.resources.ISaveContext;
import org.eclipse.core.resources.ISaveParticipant;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;

public class ColorWorkspaceSaveParticipant implements ISaveParticipant {

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

		// since the save operation has failed, delete the saved state we have
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
			File f = myPluginInstance.getStateLocation().append(saveFileName)
					.toFile();
			// if we fail to write, an exception is thrown and we do not update
			// the path
			myPluginInstance.writeImportantState(f);
			context.map(new Path("save"), new Path(saveFileName));
			context.needSaveNumber();
			break;
		case ISaveContext.PROJECT_SAVE:
			// get the project related to this save operation
//			IProject project = context.getProject();
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
