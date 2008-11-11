package de.ovgu.cide.fm.list.editor;

import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;

import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.fm.list.GuidslExport;
import de.ovgu.cide.fm.list.ListFMProvider;
import de.ovgu.cide.fm.list.ListFeatureModel;

public class ExportFeatureModelAction extends EditFeatureModelAction{


	public void run(IAction action) {
		IProject project = getSelectedProject();
		try {
			new GuidslExport().exportToGuidsl((ListFeatureModel) new ListFMProvider().getFeatureModel(project), project);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FeatureModelNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
