package de.ovgu.cide.typing.manifest;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

import tmp.generated_manifest.Export;
import cide.gast.ASTStringNode;
import cide.gast.ASTVisitor;
import cide.gast.IASTNode;
import cide.gast.ISourceFile;
import cide.gparser.ParseException;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.manifest.ManifestExtension;
import de.ovgu.cide.typing.model.AbstractFileBasedTypingProvider;
import de.ovgu.cide.typing.model.ITypingCheck;
import de.ovgu.cide.typing.model.ITypingExtension;
import de.ovgu.cide.typing.model.ITypingProvider;

public class ManifestJDTTypingExtension implements ITypingExtension {

	public ManifestJDTTypingExtension() {
		// TODO Auto-generated constructor stub
	}

	public ITypingProvider createTypingProvider(IProject project) {
		return new ManifestTypingProvider(project);
	}

	private class ManifestTypingProvider extends
			AbstractFileBasedTypingProvider {

		public ManifestTypingProvider(IProject project) {
			super(project);
		}

		@Override
		protected Set<ITypingCheck> checkFile(ColoredSourceFile file) {
			return new FileChecker(file).run();
		}

		@Override
		protected boolean matchFileForUpdate(ColoredSourceFile coloredSourceFile) {
			return coloredSourceFile != null && coloredSourceFile.isColored()
					&& coloredSourceFile.getName().equals("MANIFEST.MF");
		}

		public void prepareReevaluation(Collection<ColoredSourceFile> files,
				IProgressMonitor monitor) {
			// nothing to do
		}

		public void prepareReevaluationAll(IProgressMonitor monitor) {
			// nothing to do
		}

		/**
		 * method object. use only like this: new FileChecker(file).run(), do
		 * not reuse instance
		 * 
		 * @author ckaestne
		 * 
		 */
		class FileChecker {
			private final ColoredSourceFile file;
			private final Set<ITypingCheck> result = new HashSet<ITypingCheck>();
			private final Map<String, IASTNode> productionTable = new HashMap<String, IASTNode>();
			private ISourceFile ast;
			private IJavaProject javaProject;

			FileChecker(ColoredSourceFile file) {
				this.file = file;
			}

			Set<ITypingCheck> run() {
				javaProject = JavaCore.create(getProject());
				if (javaProject != null)
					try {
						ast = parse();

						checkPackages();
					} catch (CoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				return result;
			}

			private void checkPackages() {
				ast.accept(new ASTVisitor() {
					@Override
					public boolean visit(IASTNode node) {

						if (node instanceof Export) {
							for (ASTStringNode packagedcl : ((Export) node)
									.getPackagedcl())
								checkPackage(packagedcl);
						}

						return super.visit(node);
					}

				});
			}

			private void checkPackage(ASTStringNode packagedcl) {
				String packageStr = packagedcl.getValue();
				try {
					for (IPackageFragment packageFragment : javaProject
							.getPackageFragments()) {
						if (packageFragment.getKind() != IPackageFragmentRoot.K_SOURCE)
							continue;
						if (packageFragment.getElementName().equals(packageStr))
							result.add(new ManifestPackageCheck(file,
									packagedcl, (IFolder) packageFragment
											.getResource()));
					}
				} catch (JavaModelException e) {
					e.printStackTrace();
				}
			}

			private ISourceFile parse() throws CoreException, ParseException {
				assert ManifestExtension.LANGUAGE_EXTENSION_ID.equals(file
						.getLanguageExtension().getId());

				return file.getAST();
			}
		}

	}

}
