package de.ovgu.cide.language.jdt;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cide.gast.ASTVisitor;
import cide.gast.IASTNode;
import cide.gast.ISourceFile;

public class JdtWrapperTest {
	private static IFile file;
	private static String javaContent = "public class Dev { "
			+ "public static void main(String[] args) { " + "boolean a=true; "
			+ "if (!a) System.out.print(\"not a\"); "
			+ "System.out.print(\"done\");" + "}" + "}";

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

	@Before
	public void setUp() throws Exception {
		JDTParserWrapper parser = new JDTParserWrapper(file);

		ast = parser.getRoot();
	}

	ISourceFile ast;

	@Test
	public void testWrapper() {
		IASTNode thenNode = findThenNode(ast);
		Assert.assertTrue("thenStatement is not a wrapper", thenNode
				.getParent().getWrappee() == thenNode);
		Assert.assertTrue("thenStatement is not optional", thenNode
				.isOptional());
	}

	private IASTNode findThenNode(ISourceFile ast2) {
		final ArrayList<IASTNode> result = new ArrayList<IASTNode>(1);
		ast2.accept(new ASTVisitor() {
			@Override
			public boolean visit(IASTNode node) {
				if (node.getId().toLowerCase().contains("thenstatement"))
					result.add(node);
				return true;
			}
		});
		Assert.assertTrue(result.size()>0);
		return result.get(0);
	}

}
