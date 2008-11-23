package de.ovgu.cide.typing.jdt;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;

import cide.gparser.ParseException;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.jdt.JDTLanguageExtension;
import de.ovgu.cide.language.jdt.JDTParserWrapper;
import de.ovgu.cide.typing.model.AbstractFileBasedTypingProvider;
import de.ovgu.cide.typing.model.ITypingCheck;
import de.ovgu.cide.typing.model.ITypingProvider;

public class JDTTypingProvider extends AbstractFileBasedTypingProvider
		implements ITypingProvider {

	protected JDTTypingProvider(IProject project) {
		super(project);
		bindingColorCache = new BindingProjectColorCache(JavaCore
				.create(project));
	}

	public void updateAll() {
		getBindingColors().clear();
		super.updateAll();
	}

	private final BindingProjectColorCache bindingColorCache;

	public BindingProjectColorCache getBindingColors() {
		return bindingColorCache;
	}

	@Override
	protected boolean matchFileForUpdate(ColoredSourceFile coloredSourceFile) {
		return coloredSourceFile != null
				&& coloredSourceFile.isColored()
				&& coloredSourceFile.getLanguageExtension().getId().equals(
						JDTLanguageExtension.LANGUAGE_EXTENSION_ID);
	}

	@Override
	protected Set<ITypingCheck> checkFile(ColoredSourceFile file) {
		IFile resource = file.getResource();
		CompilationUnit e_ast;
		try {
			e_ast = JDTParserWrapper.parseJavaFile(resource);
		} catch (ParseException e) {
			e.printStackTrace();
			// if in doubt no error
			return Collections.EMPTY_SET;
		}

		// when (re)parsing a file, update the color cache
		getBindingColors().updateASTColors(e_ast, file);

		// generate all necessary checks
		Set<ITypingCheck> result = new HashSet<ITypingCheck>();
		e_ast.accept(new JDTCheckGenerator(file, this, result));

		return result;
	}

	private void updateFileColorCache(Collection<ColoredSourceFile> files) {
		for (ColoredSourceFile file : files) {
			if (file.getProject() != getProject())
				continue;
			if (!file.isColored())
				continue;
			if (!matchFileForUpdate(file))
				continue;

			IJavaElement javaElement = JavaCore.create(file.getResource());
			if (javaElement instanceof ICompilationUnit)
				getBindingColors().rebuildFile((ICompilationUnit) javaElement,
						file);
		}
	}

	public void prepareReevaluation(Collection<ColoredSourceFile> files) {
		updateFileColorCache(files);
	}

	public void prepareReevaluationAll() {
		try {
			getBindingColors().rebuildEntireProject();
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FeatureModelNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
