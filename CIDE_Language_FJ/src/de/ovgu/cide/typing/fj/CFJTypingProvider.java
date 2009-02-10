package de.ovgu.cide.typing.fj;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import tmp.generated_fj.MethodDeclaration;
import tmp.generated_fj.TypeDeclaration;
import cide.gast.IASTNode;
import cide.gast.IASTVisitor;
import cide.gparser.ParseException;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.fj.FJLanguageExtension;
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
		
		final Set<ITypingCheck> checks = new HashSet<ITypingCheck>();
		try {
			final CFJTypingManager typingManager = new CFJTypingManager(file);
			file.getAST().accept(new IASTVisitor() {
				@Override
				public void postVisit(IASTNode node) { }

				@Override
				public boolean visit(IASTNode node) {
					if (node != null) {
						if (node instanceof MethodDeclaration) {
							checks.add(new CFJMethodTypingCheck(file, (MethodDeclaration) node, typingManager));
							return false;
						}
						if (node instanceof TypeDeclaration) {
							checks.add(new CFJClassTypingCheck(file, (TypeDeclaration) node, typingManager));
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

	@Override
	public void prepareReevaluation(Collection<ColoredSourceFile> files) { }

	@Override
	public void prepareReevaluationAll() { }
	
	@Override
	protected boolean matchFileForUpdate(ColoredSourceFile coloredSourceFile) {
		return (coloredSourceFile != null) && coloredSourceFile.isColored() 
			 && coloredSourceFile.getLanguageExtension().getId().equals(FJLanguageExtension.LANGUAGE_EXTENSION_ID);
	}
}
