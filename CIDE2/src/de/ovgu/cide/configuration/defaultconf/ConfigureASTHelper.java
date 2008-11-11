package de.ovgu.cide.configuration.defaultconf;

import java.util.Set;

import org.eclipse.core.runtime.CoreException;

import cide.gast.ASTVisitor;
import cide.gast.IASTNode;
import cide.gast.ISourceFile;
import cide.gparser.ParseException;
import de.ovgu.cide.configuration.ConfigurationException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.SourceFileColorManager;

public class ConfigureASTHelper {

	public class RemoveHiddenColorsVisitor extends ASTVisitor {
		private SourceFileColorManager colorManager;
		private Set<IFeature> hiddenColors;

		public RemoveHiddenColorsVisitor(SourceFileColorManager colorManager,
				Set<IFeature> hiddenColors) {
			this.colorManager = colorManager;
			this.hiddenColors = hiddenColors;
		}

		@Override
		public boolean visit(IASTNode node) {
			if (node.isOptional()) {
				Set<IFeature> colors = colorManager.getColors(node);
				if (overlap(colors, hiddenColors)) {
					node.remove();
					return false;
				}
			}
			return super.visit(node);
		}

		private boolean overlap(Set<IFeature> s1, Set<IFeature> s2) {
			for (IFeature f : s1)
				if (s2.contains(f))
					return true;
			return false;
		}
	}

	public String hideCode(ColoredSourceFile sourceFile,
			Set<IFeature> hiddenColors) throws ConfigurationException {
		try {
			ISourceFile origAst;
			origAst = sourceFile.getAST();
			IASTNode ast = origAst.deepCopy();

			ast.accept(new RemoveHiddenColorsVisitor(sourceFile
					.getColorManager(), hiddenColors));

			return ast.render();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		} catch (CoreException e) {
			throw new ConfigurationException(e);
		}
	}

	

}
