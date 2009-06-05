package de.ovgu.cide.language.jdt;

import java.io.ByteArrayInputStream;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import cide.gast.ASTVisitor;
import cide.gast.ASTWrappers;
import cide.gast.IASTNode;
import cide.gast.IASTVisitor;
import cide.gast.ISourceFile;
import cide.gparser.ParseException;

public class JdtAstTest {

	private static IFile file;
	private static String javaContent = "class Test { public void foo() { if (false) foo(); } }";

	@BeforeClass
	public static void setUpClass() throws Exception {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = root.getProject("test");
		if (!project.exists())
			project.create(null);
		project.open(null);
		file = project.getFile("Test.java");
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
				if (node.isWrapper())
					System.out.print(" WRAPPER");
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
				if (node.isOptional() && node.getParent() != null) {
					node.remove();
					return false;
				}
				return true;
			}
		});
		ast.accept(new ASTVisitor() {
			public boolean visit(IASTNode node) {
				if (node.getParent() != null)
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
		// Assert.assertEquals(javaContent, s);
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

	@Test
	// test the brigde in two modes
	public void testPartlyBridge() throws Exception {
		CompilationUnit e_ast = JDTParserWrapper.parseJavaFile(file);
		TypeDeclaration e_type = (TypeDeclaration) e_ast.types().get(0);
		MethodDeclaration e_method = e_type.getMethods()[0];
		new ASTBridge().getAST(e_ast);

		IASTNode c_method = new ASTBridge().getASTNode(e_ast, e_method, false);
		Assert.assertNotNull(c_method);
		Assert.assertEquals(c_method.getId(), ASTID.calculateId(e_method));
		Assert.assertNotNull(c_method.getParent());
		Assert.assertTrue(c_method.getParent().isOptional());
		Assert.assertNotNull(c_method.getParent().getParent());
		Assert.assertEquals(c_method.getParent().getId(), ASTID
				.calculateId(e_method.getParent()));

		c_method = new ASTBridge().getASTNode(e_ast, e_method, true);
		Assert.assertNotNull(c_method);
		Assert.assertEquals(c_method.getId(), ASTID.calculateId(e_method));
		Assert.assertNotNull(c_method.getParent());
		Assert.assertTrue(c_method.getParent().isOptional());
		Assert.assertNotNull(c_method.getParent().getParent());
		Assert.assertEquals(c_method.getParent().getId(), ASTID
				.calculateId(e_method.getParent()));
	}

	@Test
	public void testNotInheritedColors() {
		ast.accept(new ASTVisitor() {
			@Override
			public boolean visit(IASTNode node) {
				if (node.getParent() == null)
					return true;

				boolean inherits = ASTWrappers.inheritsColors(node.getParent(),
						node);
				if (node.getDisplayName().equals("ExpressionStatement"))
					Assert.assertFalse(
							"Child to if-statement does not inherit color",
							inherits);
				else
					Assert
							.assertTrue(
									"all elements but the ExpressionStatement inherit colors",
									inherits);

				return super.visit(node);
			}

		});

	}
}
