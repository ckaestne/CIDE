package de.ovgu.cide.typing.bali;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

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

	public void updateFile(Collection<ColoredSourceFile> files) {
		Set<ITypingCheck> addedChecks = new HashSet<ITypingCheck>();
		Set<ITypingCheck> obsoleteChecks = new HashSet<ITypingCheck>();

		for (ColoredSourceFile file : files) {
			Set<ITypingCheck> oldChecks = checks.get(file.getResource());
			if (oldChecks == null)
				oldChecks = new HashSet<ITypingCheck>();

			if (file != null
					&& file.isColored()
					&& file.getLanguageExtension().getId()==BaliLanguageExtension.LANGUAGE_EXTENSION_ID) {
				Set<ITypingCheck> newChecks = new FileChecker(file).run();

				for (ITypingCheck old : oldChecks)
					if (!newChecks.contains(old))
						obsoleteChecks.add(old);
				for (ITypingCheck newc : newChecks)
					if (!oldChecks.contains(newc))
						addedChecks.add(newc);
				checks.put(file.getResource(), newChecks);
			} else {
				obsoleteChecks.addAll(oldChecks);
				checks.remove(file.getResource());
			}
		}

		fireTypingCheckChanged(addedChecks, obsoleteChecks);
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
			assert file.getLanguageExtension().getId()==BaliLanguageExtension.LANGUAGE_EXTENSION_ID;

			return file.getAST();
		}
	}

	@Override
	protected boolean matchFileForUpdate(ColoredSourceFile coloredSourceFile) {
		return coloredSourceFile != null
				&& coloredSourceFile.isColored()
				&& coloredSourceFile.getLanguageExtension().getId()==BaliLanguageExtension.LANGUAGE_EXTENSION_ID;
	}

}
