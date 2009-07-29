package de.ovgu.cide.language.jdt;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Set;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.RGB;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cide.gast.ASTVisitor;
import cide.gast.IASTNode;
import cide.gast.ISourceFile;
import cide.gparser.ParseException;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureWithID;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.SourceFileColorManager;

public class JdtWrapperTest {
	private static IFile file;
	private static String javaContent = "public class Dev { "
			+ "public static void main(String[] args) { "
			+ "boolean a=true; "
			+ "if (a) System.out.print(\"a\"); else System.out.print(\"not a2\");"
			+ "System.out.print(\"done\");" + "}" + "}";

	private class MyFeature implements IFeatureWithID {

		public boolean canSetName() {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean canSetRGB() {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean canSetVisible() {
			// TODO Auto-generated method stub
			return false;
		}

		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}

		public RGB getRGB() {
			// TODO Auto-generated method stub
			return null;
		}

		public Set<IFeature> getRequiredFeatures() {
			// TODO Auto-generated method stub
			return null;
		}

		public boolean isVisible() {
			// TODO Auto-generated method stub
			return false;
		}

		public void setName(String name) throws UnsupportedOperationException {
			// TODO Auto-generated method stub

		}

		public void setRGB(RGB color) throws UnsupportedOperationException {
			// TODO Auto-generated method stub

		}

		public void setVisible(boolean isVisible)
				throws UnsupportedOperationException {
			// TODO Auto-generated method stub

		}

		public int compareTo(IFeature arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		public long getId() {
			return 0;
		}

	}

	@BeforeClass
	public static void setUpClass() throws Exception {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = root.getProject("test");
		if (!project.exists())
			project.create(null);
		project.open(null);
		file = project.getFile("Dev.java");
		if (file.exists())
			file.delete(true, null);
		file.create(new ByteArrayInputStream(javaContent.getBytes()), true,
				null);

	}

	@Test
	public void testWrapperThen() throws CoreException, ParseException,
			FeatureModelNotFoundException {
		ColoredSourceFile source = ColoredSourceFile.getColoredSourceFile(file);
		ISourceFile ast = source.getAST();
		IASTNode thenNode = findNode(ast, "thenstatement");
		Assert.assertTrue("thenStatement is not a wrapper", thenNode
				.getParent().getWrappee() == thenNode);
		Assert.assertTrue("thenStatement is not optional", thenNode
				.isOptional());
	}

	@Test
	public void testWrapperElse() throws FeatureModelNotFoundException,
			CoreException, ParseException {
		ColoredSourceFile source = ColoredSourceFile.getColoredSourceFile(file);
		ISourceFile ast = source.getAST();
		SourceFileColorManager colormanager = source.getColorManager();
		MyFeature feature = new MyFeature();

		IASTNode ifNode = findNode(ast, "ifstatement");
		colormanager.addColor(ifNode, feature);
		IASTNode elseNode = findNode(ast, "elsestatement/expression");
		Assert.assertTrue("elseStatement is not a wrapper", elseNode
				.getParent().getWrappee() != elseNode);
		Assert.assertTrue("elseStatement is not optional", elseNode
				.isOptional());
		Assert.assertTrue("elseStatement is not colored", colormanager
				.hasColor(elseNode, feature));

		// else branch inherits colors
	}

	private IASTNode findNode(ISourceFile ast2, final String pattern) {
		final ArrayList<IASTNode> result = new ArrayList<IASTNode>(1);
		ast2.accept(new ASTVisitor() {
			@Override
			public boolean visit(IASTNode node) {
				if (node.getId().toLowerCase().contains(pattern))
					result.add(node);
				return true;
			}
		});
		Assert.assertTrue(result.size() > 0);
		return result.get(0);
	}

}
