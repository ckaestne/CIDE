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

package de.ovgu.cide.importjak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import de.ovgu.cide.importjak.CIDEAnnotationParser.AnnotationMarker;
import de.ovgu.cide.importjak.featurehouseextension.FeatureFSTGenComposer;
import de.ovgu.cide.utils.StrUtils;

/**
 * central controller for the import process
 * 
 * @author ckaestne
 * 
 */
public class JakImporter {

	/**
	 * applies all steps from a jak project (with featureide layout) to an
	 * existing but empty java project.
	 * 
	 * 
	 * @param jakProject
	 * @param targetJavaProject
	 * @throws CoreException
	 * @throws IOException
	 */
	public void importJakProject(IProject jakProject,
			IProject targetJavaProject, IProgressMonitor monitor)
			throws CoreException, IOException {
		assert jakProject.getFile("model.m").exists();

		monitor.beginTask("Importing from " + jakProject.getName(),
				IProgressMonitor.UNKNOWN);

		// flatten all files into annotations with FeatureHouse extension, code
		// in "model" folder
		monitor.subTask("Composing Java/Jak files...");
		new FeatureFSTGenComposer().run(new String[] { "--expression",
				jakProject.getFile("model.m").getLocation().toOSString(),
				"--base-directory", jakProject.getLocation().toOSString(),
				"--output-directory",
				targetJavaProject.getLocation().toOSString() + "/" }, monitor);

		// copy model file
		jakProject.getFile("model.m").copy(
				targetJavaProject.getFile("model.m").getFullPath(), true,
				monitor);

		// refresh
		targetJavaProject.refreshLocal(IResource.DEPTH_INFINITE, monitor);

		// prepare feature model with _OR_ features
		String newfm = prepareFeatureModel(targetJavaProject, StrUtils
				.strFromInputStream(targetJavaProject.getFile("model.m")
						.getContents(true)));
		targetJavaProject.getFile("model.m").setContents(
				StrUtils.strToInputStream(newfm), true, true, monitor);

		// parse as CIDE annotations on AST
		monitor.subTask("Importing annotations...");
		targetJavaProject.getFolder("model").accept(
				new CIDEAnnotationParser(monitor));

		monitor.done();
	}

	private String prepareFeatureModel(IProject targetJavaProject,
			String featureModel) throws CoreException {
		// first search all java files for annotations, sets to avoid duplicates
		final Set<Set<String>> orFeatures = new HashSet<Set<String>>();
		targetJavaProject.getFolder("model").accept(new IResourceVisitor() {

			public boolean visit(IResource resource) throws CoreException {
				if (resource instanceof IFile
						&& ((IFile) resource).getFileExtension().equals("java")) {
					try {
						orFeatures.addAll(parseAnnoations(StrUtils
								.strFromInputStream(((IFile) resource)
										.getContents(true))));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return true;
			}

			private Set<Set<String>> parseAnnoations(String content) {

				List<AnnotationMarker> annotations = new CIDEAnnotationParser(
						null).findAnnotations(content);

				Set<Set<String>> result = new HashSet<Set<String>>();
				for (AnnotationMarker annoation : annotations) {
					if (annoation.orFeatures.size() > 1)
						result.add(new HashSet<String>(annoation.orFeatures));
				}
				return result;
			}

		});
		if (orFeatures.isEmpty())
			return featureModel;

		// add to lists and sort
		List<List<String>> sortedOrFeatures = new ArrayList<List<String>>();
		for (Set<String> orFeature : orFeatures) {
			ArrayList<String> list = new ArrayList<String>(orFeature);
			Collections.sort(list);
			sortedOrFeatures.add(list);
		}
		Collections.sort(sortedOrFeatures, new Comparator<List<String>>() {

			public int compare(List<String> o1, List<String> o2) {
				if (o1.size() < o2.size())
					return 1;
				if (o2.size() < o1.size())
					return -1;
				for (int i = 0; i < o1.size(); i++) {
					int r = o1.get(i).compareToIgnoreCase(o2.get(i));
					if (r != 0)
						return r;
				}
				return 0;
			}
		});
		System.out.println(sortedOrFeatures);

		System.out.println(orFeatures);

		if (featureModel.indexOf("%%") == -1)
			featureModel = featureModel + "\n%%\n";

		int colon = featureModel.indexOf(':');
		featureModel = featureModel.substring(0, colon + 1) + " [OR_FEATURES]"
				+ featureModel.substring(colon + 1);

		int sep = featureModel.indexOf("%%");
		featureModel = featureModel.substring(0, sep)
				+ getOrFeatureDecl(sortedOrFeatures) + "\n%%\n"
				+ getOrFeatureConstraints(sortedOrFeatures)
				+ featureModel.substring(sep + 3);

		return featureModel;
	}

	private String getOrFeatureConstraints(List<List<String>> sortedOrFeatures) {
		String result = "";
		for (List<String> orFeature : sortedOrFeatures) {
			result += getOrFeatureStr(orFeature) + " iff (" + orFeature.get(0);
			for (int i = 1; i < orFeature.size(); i++)
				result += " or " + orFeature.get(i);
			result += ");\n";
		}

		return result;
	}

	static String getOrFeatureStr(List<String> orFeature) {
		String result = orFeature.get(0);
		for (int i = 1; i < orFeature.size(); i++)
			result += "_OR_" + orFeature.get(i);
		return result;
	}

	private String getOrFeatureDecl(List<List<String>> sortedOrFeatures) {
		String result = "OR_FEATURES: ";
		for (List<String> orFeature : sortedOrFeatures)
			result += "[" + getOrFeatureStr(orFeature) + "] ";

		return result + "::_OR_FEATURES;";
	}
}
