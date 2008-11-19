package de.ovgu.cide.typing;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import de.ovgu.cide.typing.internal.ValidateProjectActionTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ValidateProjectActionTest.class
        })
public class AllTests {

	public static Test suite() {
		return new JUnit4TestAdapter(AllTests.class);
	}

}
