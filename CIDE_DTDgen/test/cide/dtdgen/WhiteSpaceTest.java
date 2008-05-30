package cide.dtdgen;

import junit.framework.Assert;

public class WhiteSpaceTest {

	protected void assertWhiteSpaceEqual(String expected, String actual) {
		String ashort = removeWhiteSpace(expected);
		String bshort = removeWhiteSpace(actual);
		Assert.assertEquals(actual + "\n\nexpected:\n" + expected, ashort, bshort);
	}

	private String removeWhiteSpace(String a) {
		return a.replaceAll("[\\ ,\\t,\\n,\\r]", "");
	}

}