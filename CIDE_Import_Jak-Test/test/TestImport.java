import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.ovgu.cide.importjak.JakImporter;

public class TestImport {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	private static void setupTestProject() throws CoreException {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
				"test_input");

		project.create(null);
		project.open(null);

		project.getFile("model.m").create(
				new ByteArrayInputStream(
						"SPL: [d] [c] [b] a::_SPL;\n%%\nc implies not b;b implies not c;\n"
								.getBytes()), true, null);

		project.getFolder("a").create(true, true, null);
		project.getFolder("a").getFile("Bar.java").create(
				new ByteArrayInputStream(
						"class Bar {\n			void m(){\n				a();\n			}\n			void a(){}\n		}"
								.getBytes()), true, null);
		project.getFolder("b").create(true, true, null);
		project.getFolder("b").getFile("Bar.java").create(
				new ByteArrayInputStream(
						"class Bar {\n			void n(){\n				b();\n			}\n			void b(){}\n		}"
								.getBytes()), true, null);
		project.getFolder("c").create(true, true, null);
		project
				.getFolder("c")
				.getFile("Bar.java")
				.create(
						new ByteArrayInputStream(
								"class Bar {\n			void m(){\n				original(); c();			}\n			void n(){\n				c();\n			}\n			void c(){}\n		}"
										.getBytes()), true, null);
		project.getFolder("d").create(true, true, null);
		project.getFolder("d").getFile("Bar.java").create(
				new ByteArrayInputStream(
						"class Bar {\n			void m(){\n				original(); d();\n			}\n			void d(){}\n		}"
								.getBytes()), true, null);

	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testImport() throws CoreException, IOException {
		setupTestProject();

		IProject targetJavaProject = ResourcesPlugin.getWorkspace().getRoot()
				.getProject("target");
		targetJavaProject.create(null);
		targetJavaProject.open(null);
		new JakImporter().importJakProject(ResourcesPlugin.getWorkspace()
				.getRoot().getProject("test_input"), targetJavaProject, null);

	}
}
