package coloredide.export2jak;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class JakCodeComparer {

	@Before
	public void setUp() throws Exception {
	}

	static void assertCodeEquivalent(String a, String b) {
		assertCodeEquivalent(a, b, "");
	}

	static void assertCodeEquivalent(String a, String b, String note) {
		String ashort = cleanCode(a);
		String bshort = cleanCode(b);
		Assert.assertEquals(note + b + "\n\nexpected:\n" + a, ashort, bshort);
	}

	static void assertNotCodeEquivalent(String a, String b) {
		String ashort = cleanCode(a);
		String bshort = cleanCode(b);
		Assert
				.assertFalse("unexpected equivalent: " + b, ashort
						.equals(bshort));
	}

	private static String cleanCode(String a) {
		// ignore layer line
		a = a.replaceAll("^layer \\w+;", "");
		// ignore enumerated hook methods
		a = a.replaceAll("hook\\d*\\(", "hook(");
		a = a.replaceAll("this.hook\\(", "hook(");
		// ignore parantensies after Super
		a = a.replaceAll("Super\\(\\)", "Super");
		// first remove whitespaces
		a = a.replaceAll("[\\ ,\\t,\\n,\\r]", "");
		// new and overrides in method refinements
		a = a.replaceAll("newvoid", "void");
		a = a.replaceAll("overridesvoid", "void");
		a = a.replaceAll("newint", "int");
		a = a.replaceAll("newpublicObject", "publicObject");
		a = a.replaceAll("overridespublicObject", "publicObject");
		a = a.replaceAll("overridesint", "int");
		a = a.replaceAll("overridesprotected", "protected");
		a = a.replaceAll("newprotected", "protected");
		return a;
	}

	@Test
	public void testComparison() {
		assertCodeEquivalent("void foo(  );", "void foo();");
		assertCodeEquivalent("void hook(  );", "void hook();");
		assertCodeEquivalent("void hook();", "void hook4();");
		assertNotCodeEquivalent("void hook(  );", "void hooka();");
		assertCodeEquivalent("layer x;a;", "a;");
		assertNotCodeEquivalent("a; layer x;", "a;");
		assertCodeEquivalent("void foo(  );", "new void foo();");
		assertCodeEquivalent("void foo(  );", "overrides void foo();");
		assertCodeEquivalent("int foo(  );", "new int foo();");
		assertCodeEquivalent("int foo(  );", "overrides int foo();");
		assertNotCodeEquivalent("int foo( Object a= new String() );",
				"int foo( Object a= String() );");
		assertCodeEquivalent("Super.foo()", "Super().foo()");
		assertCodeEquivalent("protected void foo(  );",
				"overrides protected void foo();");
		assertCodeEquivalent("protected void foo(  );",
				"new protected void foo();");
		assertCodeEquivalent("this.hook1()", "hook()");
	}

}
