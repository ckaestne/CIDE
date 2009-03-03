package de.ovgu.cide.af;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import cide.gparser.ParseException;

import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.model.AbstractFileBasedTypingProvider;
import de.ovgu.cide.typing.model.ITypingCheck;

/**
 * @author Malte Rosenthal
 */
public class AlternativeSyntaxProvider extends AbstractFileBasedTypingProvider {
	
	AlternativeSyntaxProvider(IProject project) {
		super(project);
	}

	@Override
	protected Set<ITypingCheck> checkFile(ColoredSourceFile file) {
		if (file == null)
			return null;
		
		Set<ITypingCheck> checks = new HashSet<ITypingCheck>();
		try {
			checks.add(new AlternativeSyntaxCheck(file));
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
		return (coloredSourceFile != null) && coloredSourceFile.isColored();
	}
}
