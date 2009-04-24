package de.ovgu.cide.importjak;

import java.util.Collection;

import junit.framework.TestCase;

import org.junit.Test;
import org.prop4j.Literal;
import org.prop4j.Or;

import de.ovgu.cide.importjak.featurehouseextension.FeatureJavaPrintVisitor;

public class CIDEAnnotationParserTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testParseFeatures() {
		String ifdef = FeatureJavaPrintVisitor.startIfdef(new Or(new Literal(
				"A"), new Literal("B"), new Literal("XX")));
		Collection<String> r = new CIDEAnnotationParser(null).parseFeatures(
				ifdef, 0);
		assertEquals(3, r.size());
		assertTrue(r.contains("A"));
		assertTrue(r.contains("B"));
		assertTrue(r.contains("XX"));

	}

}
