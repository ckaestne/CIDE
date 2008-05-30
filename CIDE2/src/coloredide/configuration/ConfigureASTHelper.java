package coloredide.configuration;

import java.util.Set;

import org.eclipse.core.runtime.CoreException;

import cide.features.IFeature;
import cide.gast.ASTNode;
import cide.gast.ASTVisitor;
import cide.gast.IASTNode;
import cide.gast.ISourceFile;
import cide.gparser.ParseException;
import cide.languages.ILanguagePrintVisitor;
import coloredide.features.Feature;
import coloredide.features.source.ColoredSourceFile;
import coloredide.features.source.SourceFileColorManager;

public class ConfigureASTHelper {

	public class RemoveHiddenColorsVisitor extends ASTVisitor {
		private SourceFileColorManager colorManager;
		private Set<Feature> hiddenColors;

		public RemoveHiddenColorsVisitor(SourceFileColorManager colorManager,
				Set<Feature> hiddenColors) {
			this.colorManager = colorManager;
			this.hiddenColors = hiddenColors;
		}

		@Override
		public boolean visit(ASTNode node) {
			if (node.isOptional()) {
				Set<Feature> colors = colorManager.getColors(node);
				if (overlap(colors, hiddenColors)) {
					node.remove();
					return false;
				}
			}
			return super.visit(node);
		}

		private boolean overlap(Set<Feature> s1, Set<Feature> s2) {
			for (IFeature f : s1)
				if (s2.contains(f))
					return true;
			return false;
		}
	}

	public String hideCode(ColoredSourceFile sourceFile,
			Set<Feature> hiddenColors) throws CoreException {
		try {
			ISourceFile origAst;
			origAst = sourceFile.getAST();
			IASTNode ast = origAst.deepCopy();

			ast.accept(new RemoveHiddenColorsVisitor(sourceFile
					.getColorManager(), hiddenColors));

			ILanguagePrintVisitor printer = sourceFile.getLanguageExtension()
					.getPrettyPrinter();
			ast.accept(printer);
			return printer.getResult();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

}
