package de.ovgu.cide.typing.bali;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import tmp.generated_bali.BaliGrammarRule;
import tmp.generated_bali.IdentifierNode;
import cide.gast.ASTVisitor;
import cide.gast.IASTNode;
import cide.gast.ISourceFile;
import cide.gparser.ParseException;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.bali.BaliLanguageExtension;
import de.ovgu.cide.typing.bali.checks.ProductionReferenceCheck;
import de.ovgu.cide.typing.model.AbstractFileBasedTypingProvider;
import de.ovgu.cide.typing.model.ITypingCheck;

public class BaliTypingProvider extends AbstractFileBasedTypingProvider {

	protected BaliTypingProvider(IProject project) {
		super(project);

	}

	/**
	 * method object. use only like this: new FileChecker(file).run(), do not
	 * reuse instance
	 * 
	 * @author ckaestne
	 * 
	 */
	class FileChecker {
		private final ColoredSourceFile file;
		private final Set<ITypingCheck> result = new HashSet<ITypingCheck>();
		private final Map<String, IASTNode> productionTable = new HashMap<String, IASTNode>();
		private ISourceFile ast;

		FileChecker(ColoredSourceFile file) {
			this.file = file;
		}

		Set<ITypingCheck> run() {

			try {
				ast = parse();

				buildTypeTable();

				checkReferences();
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}

		private void buildTypeTable() {
			// build type tables
			ast.accept(new ASTVisitor() {

				@Override
				public boolean visit(IASTNode node) {

					if (node instanceof BaliGrammarRule) {
						productionTable.put(((BaliGrammarRule) node)
								.getIdentifier().getValue(), node);
					}

					return super.visit(node);
				}
			});
		}

		private void checkReferences() {
			// check references
			ast.accept(new ASTVisitor() {

				@Override
				public boolean visit(IASTNode node) {

					if (node instanceof IdentifierNode) {
						IASTNode production = productionTable
								.get(((IdentifierNode) node).getIdentifier()
										.getValue());
						if (production != null)
							result.add(new ProductionReferenceCheck(file, node,
									production));
					}

					return super.visit(node);
				}
			});
		}

		private ISourceFile parse() throws CoreException, ParseException {
			assert BaliLanguageExtension.LANGUAGE_EXTENSION_ID.equals(file
					.getLanguageExtension().getId());

			return file.getAST();
		}
	}

	@Override
	protected boolean matchFileForUpdate(ColoredSourceFile coloredSourceFile) {
		return coloredSourceFile != null
				&& coloredSourceFile.isColored()
				&& coloredSourceFile.getLanguageExtension().getId().equals(
						BaliLanguageExtension.LANGUAGE_EXTENSION_ID);
	}

	@Override
	protected Set<ITypingCheck> checkFile(ColoredSourceFile file) {
		return new FileChecker(file).run();
	}

	public void prepareReevaluation(Collection<ColoredSourceFile> files,
			IProgressMonitor monitor) {
		// nothing to do
	}

	public void prepareReevaluationAll(IProgressMonitor monitor) {
		// nothing to do
	}

}
