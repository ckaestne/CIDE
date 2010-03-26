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
