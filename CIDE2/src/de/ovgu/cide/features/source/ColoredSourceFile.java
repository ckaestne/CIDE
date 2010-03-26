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

package de.ovgu.cide.features.source;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import cide.gast.IASTNode;
import cide.gast.ISourceFile;
import cide.gparser.ParseException;
import cide.gparser.TokenMgrError;
import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;
import de.ovgu.cide.alternativefeatures.AlternativeFeatureManager;
import de.ovgu.cide.features.FeatureModelManager;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.languages.LanguageExtensionManager;
import de.ovgu.cide.languages.LanguageExtensionProxy;

public class ColoredSourceFile {

	protected final IFile coloredSourceFile;

	protected final ILanguageExtension language;

	protected WeakReference<ISourceFile> astRef = null;

	protected SourceFileColorManager colorManager = null;
	
	protected AlternativeFeatureManager altFeatureManager = null;

	private final IFeatureModel featureModel;

	private final IStorageProvider storageProvider;

	private static final WeakHashMap<ISourceFile, ColoredSourceFile> ast2FileMap = new WeakHashMap<ISourceFile, ColoredSourceFile>();

	private static final WeakHashMap<IFile, WeakReference<ColoredSourceFile>> fileCache = new WeakHashMap<IFile, WeakReference<ColoredSourceFile>>();

	protected ColoredSourceFile(IFile coloredSourceFile,
			IFeatureModel featureModel) {
		this.coloredSourceFile = coloredSourceFile;
		this.featureModel = featureModel;
		this.language = findLanguageExtension(coloredSourceFile);

		this.storageProvider = StorageProviderManager.getInstance()
				.getStorageProvider(coloredSourceFile.getProject(),
						featureModel);
		
		// Ich denke, das brauchen wir nicht
//		if (language == null)
//			errorLanguageExtensionNotFound(coloredSourceFile);
	}
	
	public boolean alternativesArePossible() {
		if (storageProvider == null)
			return false;
		return storageProvider.canHandleAlternatives();
	}

	/**
	 * returns whether this file can be colored. to be colorable the file must
	 * belong to a language extension so that CIDE can build an AST and has a
	 * pretty printer as minimum requirements.
	 * 
	 * files that cannot be colored do not have a color file (or if they have it
	 * will be ignored)
	 * 
	 * @return
	 */
	public boolean isColored() {
		return language != null;
	}

	/**
	 * determines whether a file is colored without instanciating a
	 * ColoredSourceFile, thus without the need of a feature model
	 * 
	 * @param coloredSourceFile
	 * @return
	 */
	public static boolean isFileColored(IFile coloredSourceFile) {
		ILanguageExtension language = findLanguageExtension(coloredSourceFile);
		return language != null;
	}

	public ISourceFile getAST() throws CoreException, ParseException {
		assert isColored() : "cannot create AST because file is not colored";
		if (!isColored()) return null;

		if (astRef != null) {
			ISourceFile r = astRef.get();
			if (r != null)
				return r;
		}

		ISourceFile r = createAST();
		astRef = new WeakReference<ISourceFile>(r);
		return r;
	}

	public ISourceFile createAST() throws CoreException, ParseException {
		ISourceFile astRoot = null;
		long start = System.currentTimeMillis();
		ILanguageParser parser = language.getParser(coloredSourceFile
				.getContents(true), coloredSourceFile.getFullPath().toOSString());
		try {
			astRoot = parser.getRoot();
		} catch (TokenMgrError error) {
			throw new ParseException(error.getMessage());
		}
		System.out.println("Parsed " + coloredSourceFile.getName() + " in "
				+ (System.currentTimeMillis() - start) + " ms");

		if (astRoot != null)
			ast2FileMap.put(astRoot, this);

		return astRoot;
	}

	public void refreshAST() {
		astRef = null;
		fireASTRefreshed();
	}

	public static ISourceFile getASTRoot(IASTNode node) {
		return node.getRoot();
	}

	public int hashCode() {
		return coloredSourceFile.hashCode();
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof ColoredSourceFile))
			return false;
		return coloredSourceFile
				.equals(((ColoredSourceFile) obj).coloredSourceFile);
	}

	public SourceFileColorManager getColorManager() {
		assert isColored();
		if (colorManager == null) {
			colorManager = new SourceFileColorManager(storageProvider, this,
					DirectoryColorManager.getColoredDirectoryManagerS(
							coloredSourceFile.getParent(), featureModel));
		}
		return colorManager;
	}
	
	public AlternativeFeatureManager getAltFeatureManager() {
		if (altFeatureManager == null) {
			altFeatureManager = new AlternativeFeatureManager(this);
		}
		return altFeatureManager;
	}

	private static void cache(ColoredSourceFile sourceFile) {
		WeakReference<ColoredSourceFile> r = new WeakReference<ColoredSourceFile>(
				sourceFile);
		fileCache.put(sourceFile.coloredSourceFile, r);
	}

	// public static ColoredSourceFile getColoredSourceFile(IFile
	// coloredSourceFile)
	// throws FeatureModelNotFoundException {
	// IFeatureModel fm = FeatureModelManager.getInstance().getFeatureModel(
	// coloredSourceFile.getProject());
	// return getColoredSourceFile(coloredSourceFile, fm);
	// }

	public static ColoredSourceFile getColoredSourceFile(IFile coloredSourceFile)
			throws FeatureModelNotFoundException {
		IFeatureModel featureModel = FeatureModelManager.getInstance()
				.getFeatureModel(coloredSourceFile.getProject());
		return getColoredSourceFile(coloredSourceFile, featureModel);
	}

	public static ColoredSourceFile getColoredSourceFile(
			IFile coloredSourceFile, IFeatureModel featureModel) {
		ColoredSourceFile cachedCJSF = null;
		WeakReference<ColoredSourceFile> r = fileCache.get(coloredSourceFile);
		if (r != null)
			cachedCJSF = r.get();
		if (cachedCJSF == null) {
			cachedCJSF = new ColoredSourceFile(coloredSourceFile, featureModel);
			cache(cachedCJSF);
		}
		return cachedCJSF;
	}

	// public IProject getProject() {
	// return colorFile.getProject();
	// }

	public IFeatureModel getFeatureModel() {
		return featureModel;
	}

	public boolean hasColors() {
		return getColorManager().hasColors();
	}

	@Override
	public String toString() {
		return (isColored() ? "" : "(Un-)") + "Colored File "
				+ coloredSourceFile.toString();
	}

	public String getName() {
		return coloredSourceFile.getName();
	}

	public IFile getResource() {
		return coloredSourceFile;
	}

	public IProject getProject() {
		return getResource().getProject();
	}

	public ILanguageExtension getLanguageExtension() {
		return language;
	}

	// Ich denke, das brauchen wir nicht
//	private void errorLanguageExtensionNotFound(IFile input) {
//		System.err.println("No language extensions registered for file "
//				+ input.getName());
//		System.err.print("Enabled file extensions:");
//		for (LanguageExtensionProxy le : LanguageExtensionManager.getInstance()
//				.getEnabledLanguageExtensions())
//			System.err.print(" " + le.printFileExtensions(" "));
//		System.err.println();
//	}

	private static ILanguageExtension findLanguageExtension(IFile input) {
		List<LanguageExtensionProxy> languageExtensions = LanguageExtensionManager
				.getInstance().getEnabledLanguageExtensions();
		String targetFileExtension = "." + input.getFileExtension();
		for (LanguageExtensionProxy le : languageExtensions) {
			for (String fileExtension : le.getFileExtensions())
				if (fileExtension.equals(targetFileExtension))
					return le;
		}
		return null;
	}

	public static IFile getResource(IASTNode node) {
		ISourceFile root = node.getRoot();
		ColoredSourceFile csf = ast2FileMap.get(root);
		return csf == null ? null : csf.getResource();
		// for (WeakReference<ColoredSourceFile> coloredSourceFileRef :
		// fileCache
		// .values()) {
		// ColoredSourceFile coloredSourceFile = coloredSourceFileRef.get();
		// if (coloredSourceFile != null) {
		// WeakReference<ISourceFile> astRef = coloredSourceFile.astRef;
		// if (astRef != null) {
		// ISourceFile ast = astRef.get();
		// if (ast == root)
		// return coloredSourceFile.getResource();
		// }
		// }
		// }
		// return null;
	}

	private final ArrayList<IASTRefreshListener> listeners = new ArrayList<IASTRefreshListener>();

	public void addASTRefreshListener(IASTRefreshListener listener) {
		if (!listeners.contains(listener))
			listeners.add(listener);
	}

	public void removeASTRefreshListener(IASTRefreshListener listener) {
		listeners.add(listener);
	}

	protected void fireASTRefreshed() {
		for (IASTRefreshListener listener : listeners) {
			listener.astRefreshed(this);
		}
	}

}
