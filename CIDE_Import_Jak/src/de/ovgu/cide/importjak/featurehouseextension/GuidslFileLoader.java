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

package de.ovgu.cide.importjak.featurehouseextension;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

import builder.ArtifactBuilderInterface;
import cide.gparser.ParseException;
import featureide.fm.core.Feature;
import featureide.fm.core.FeatureModel;
import featureide.fm.core.io.UnsupportedModelException;
import featureide.fm.core.io.guidsl.FeatureModelReader;

public class GuidslFileLoader {

	private LinkedList<ArtifactBuilderInterface> builderList;

	private MyFileFilter fileFilter = new MyFileFilter();

	private DirectoryFileFilter directoryFileFilter = new DirectoryFileFilter();

	public GuidslFileLoader(FeatureFSTGenComposer genComposer) {
		builderList = new LinkedList<ArtifactBuilderInterface>();
	}

	public void registerArtifactBuilder(ArtifactBuilderInterface builder) {
		builderList.add(builder);
	}

	public void unregisterArtifactBuilder(ArtifactBuilderInterface builder) {
		builderList.remove(builder);
	}

	public LinkedList<ArtifactBuilderInterface> getArtifactBuilders() {
		return builderList;
	}

	public void loadFiles(String equationFileName,
			String equationBaseDirectoryName, boolean aheadEquation,
			IProgressMonitor monitor) throws FileNotFoundException,
			ParseException {
		parseEquationFile(equationFileName, equationBaseDirectoryName,
				aheadEquation, monitor);
	}

	private void parseEquationFile(String equationFileName,
			String equationBaseDirectoryName, boolean aheadEquation,
			IProgressMonitor monitor) throws FileNotFoundException,
			ParseException {
		if (equationFileName == null || equationFileName.length() == 0)
			throw new FileNotFoundException();
		File equationFile = new File(equationFileName);

		if (equationFileName.endsWith(".m")) {
			// if input is a guidsl feature model
			loadGuidslModel(equationFile, equationBaseDirectoryName, monitor);
			return;
		}
		throw new ParseException("Unexpected file format. Use model.m file");
	}

	public static FeatureModel featureModel;

	private void loadGuidslModel(File grammarFile,
			String equationBaseDirectoryName, IProgressMonitor monitor)
			throws FileNotFoundException, ParseException {
		FeatureModel fm = new FeatureModel();
		try {
			new FeatureModelReader(fm).readFromFile(grammarFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedModelException e) {
			e.printStackTrace();
		}
		featureModel = fm;

		if (!fm.getFeatures().isEmpty()) {

			System.out.println("Found the following features:");
			List<String> features = getFeatureList(fm);
			for (String s : features)
				System.out.println(s);

			// if (features.length == 0) {
			// features = equationFileContent.split(" ");
			// }
			// System.out.println("BaseDirectory: " + baseDirectoryName);
			Iterator<ArtifactBuilderInterface> iterator = builderList
					.iterator();
			if (!equationBaseDirectoryName.trim().endsWith(
					"" + File.separatorChar)) {
				equationBaseDirectoryName = equationBaseDirectoryName.trim()
						+ File.separatorChar;
			}
			while (iterator.hasNext()) {
				iterator.next().setBaseDirectoryName(
						getDirectoryName(new File(equationBaseDirectoryName)));
			}
			for (String feature : features) {
				if (monitor != null)
					monitor.subTask("Parsing " + feature + "...");
				if (!feature.trim().equals("")) {
					File featureFile = new File(equationBaseDirectoryName
							+ feature);
					parseDirectory(featureFile, true, monitor);
				}
			}
		}

	}

	public static List<String> getFeatureList(FeatureModel fm) {
		List<String> result = new ArrayList<String>();
		getFeatureList(fm.getRoot(), result);
		return result;
	}

	private static void getFeatureList(Feature root, List<String> result) {
		result.add(root.getName());
		for (int i = root.getChildrenCount() - 1; i >= 0; i--)
			getFeatureList(root.getChildren().get(i), result);
	}

	private void parseDirectory(File directory, boolean recursive,
			IProgressMonitor monitor) throws FileNotFoundException,
			ParseException {
		if (recursive) {
			File[] files = directory.listFiles(fileFilter);
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					Iterator<ArtifactBuilderInterface> iterator = builderList
							.iterator();
					while (iterator.hasNext()) {
						ArtifactBuilderInterface builder = iterator.next();
						if (builder.acceptFile(files[i])) {
							if (monitor != null)
								monitor.subTask("Parsing " + files[i] + "...");
							builder.processFile(files[i]);
						}
					}
				}
			}
			File[] directories = directory.listFiles(directoryFileFilter);
			if (directories != null) {
				for (int i = 0; i < directories.length; i++) {
					parseDirectory(directories[i], recursive, monitor);
				}
			}
		} else {
			File[] files = directory.listFiles(fileFilter);
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					Iterator<ArtifactBuilderInterface> iterator = builderList
							.iterator();
					while (iterator.hasNext()) {
						ArtifactBuilderInterface builder = iterator.next();
						if (builder.acceptFile(files[i])) {
							if (monitor != null)
								monitor.subTask("Parsing " + files[i] + "...");
							builder.processFile(files[i]);
						}
					}
				}
			} else {
				System.out
						.println("Input directory does not contain any parsable files: "
								+ directory.getPath());
			}

		}

	}

	private String getDirectoryName(File file) {
		String result = "";
		if (file.isDirectory()) {
			result = file.getPath();
		} else {
			result = file.getParentFile().getPath();
		}
		return result;
	}

	private class DirectoryFileFilter implements FileFilter {
		public DirectoryFileFilter() {

		}

		public boolean accept(File pathname) {
			if (pathname.isFile()) {
				return false;
			} else if (pathname.isDirectory()) {
				return true;
			}
			return false;
		}
	}

	private class MyFileFilter implements FileFilter {

		public MyFileFilter() {

		}

		public boolean accept(File pathname) {
			if (pathname.isFile()) {
				return true;
			}
			return false;
		}

	}

}
