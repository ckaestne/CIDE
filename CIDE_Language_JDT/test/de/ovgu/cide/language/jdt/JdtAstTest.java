package de.ovgu.cide.language.jdt;

import java.io.ByteArrayInputStream;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import cide.gast.ASTVisitor;
import cide.gast.IASTNode;
import cide.gast.IASTVisitor;
import cide.gast.ISourceFile;
import cide.gparser.ParseException;

public class JdtAstTest {

	private static IFile file;
	private static String javaContent = "class Test { public void foo() {} }";

	@BeforeClass
	public static void setUpClass() throws Exception {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = root.getProject("test");
		project.create(null);
		project.open(null);
		file = project.getFile("Test.java");

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
	public void testStructure() throws ParseException {
		// parent,id !=null
		ast.accept(new IASTVisitor() {
			int indent = -1;

			public void postVisit(IASTNode node) {
				indent--;
			}

			public boolean visit(IASTNode node) {
				indent++;
				for (int i = 0; i < indent; i++)
					System.out.print("\t");
				if (node.isOptional())
					System.out.print("[");
				System.out.print(node.getDisplayName());
				if (node.isOptional())
					System.out.print("]");
				System.out.println("  - " + node.getStartPosition() + " - "
						+ node.getLength());

				if (indent > 0) {
					Assert.assertNotNull(node.getParent());
					Assert.assertNotNull(node.getLocationInParent());
				}
				Assert.assertNotNull(node.getId());
				Assert.assertNotNull(node.getDisplayName());
				Assert.assertNotSame(0, node.getLength());
				return true;
			}
		});

	}

	@Test
	public void testRemoval() throws ParseException {
		// parent,id !=null
		ast.accept(new ASTVisitor() {
			public boolean visit(IASTNode node) {
				if (node.isOptional()) {
					node.remove();
					return false;
				}
				return true;
			}
		});
		ast.accept(new ASTVisitor() {
			public boolean visit(IASTNode node) {
				Assert.assertFalse("optional element after removal", node
						.isOptional());
				return true;
			}
		});

	}

	@Test
	public void testCloning() {
		IASTNode copy = ast.deepCopy();

		Assert.assertNotNull(copy);

		StructureVisitor sv1 = new StructureVisitor();
		StructureVisitor sv2 = new StructureVisitor();
		ast.accept(sv1);
		copy.accept(sv2);

		Assert.assertEquals(sv1.structureKey, sv2.structureKey);
	}

	@Test
	public void testRendering() {
		String s = ast.render();
		System.out.println(s);
		Assert.assertEquals(javaContent, s);
	}

	private static class StructureVisitor extends ASTVisitor {
		String structureKey = "";

		public void postVisit(IASTNode node) {
			structureKey += "]";
		}

		public boolean visit(IASTNode node) {
			structureKey += "[" + node.getDisplayName();
			return true;
		}
	}

}
