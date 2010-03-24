package de.ovgu.cide.typing.fj;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import tmp.generated_fj.MethodDeclaration;
import tmp.generated_fj.TypeDeclaration;
import cide.gast.IASTNode;
import cide.gast.IASTVisitor;
import cide.gparser.ParseException;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.fj.FJLanguageExtension;
import de.ovgu.cide.typing.fj.af.CFJClassTypingCheckAF;
import de.ovgu.cide.typing.fj.af.CFJMethodTypingCheckAF;
import de.ovgu.cide.typing.fj.af.CFJTypingManagerAF;
import de.ovgu.cide.typing.model.AbstractFileBasedTypingProvider;
import de.ovgu.cide.typing.model.ITypingCheck;

/**
 * @author Malte Rosenthal
 */
public class CFJTypingProvider extends AbstractFileBasedTypingProvider {

	protected CFJTypingProvider(IProject project) {
		super(project);
	}

	@Override
	protected Set<ITypingCheck> checkFile(final ColoredSourceFile file) {
		if (file == null)
			return null;
		
		final boolean handleAlternatives = file.alternativesArePossible();
		final Set<ITypingCheck> checks = new HashSet<ITypingCheck>();
		
		try {
			final CFJTypingManagerAF typingManagerAF = handleAlternatives ? new CFJTypingManagerAF(file) : null; 
			final CFJTypingManager typingManager = !handleAlternatives ? new CFJTypingManager(file) : null;
			
			file.getAST().accept(new IASTVisitor() {
				public void postVisit(IASTNode node) { }

				public boolean visit(IASTNode node) {
					if (node != null) {
						if (node instanceof MethodDeclaration) {
							if (handleAlternatives)
								checks.add(new CFJMethodTypingCheckAF(file, typingManagerAF, (MethodDeclaration) node));
							else
								checks.add(new CFJMethodTypingCheck(file, typingManager, (MethodDeclaration) node));
							
							return false;
						}
						if (node instanceof TypeDeclaration) {
							if (handleAlternatives)
								checks.add(new CFJClassTypingCheckAF(file, typingManagerAF, (TypeDeclaration) node));
							else
								checks.add(new CFJClassTypingCheck(file, typingManager, (TypeDeclaration) node));
							
							return true;
						}
					}
					return true;
				}
			});
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return checks;
	}

	public void prepareReevaluation(Collection<ColoredSourceFile> files, IProgressMonitor monitor) { }

	public void prepareReevaluationAll(IProgressMonitor monitor) { }
	
	@Override
	protected boolean matchFileForUpdate(ColoredSourceFile coloredSourceFile) {
		return (coloredSourceFile != null) && coloredSourceFile.isColored() 
			 && coloredSourceFile.getLanguageExtension().getId().equals(FJLanguageExtension.LANGUAGE_EXTENSION_ID);
	}
}
