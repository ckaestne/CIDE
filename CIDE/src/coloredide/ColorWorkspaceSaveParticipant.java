package coloredide;

import java.io.File;

import org.eclipse.core.resources.ISaveContext;
import org.eclipse.core.resources.ISaveParticipant;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;

public class ColorWorkspaceSaveParticipant implements ISaveParticipant {

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
			ColoredIDEPlugin myPluginInstance = ColoredIDEPlugin.getDefault();
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
