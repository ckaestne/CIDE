/**
    Copyright 2010 Christian Kästner

    This file is part of CIDE.

    CIDE is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.

    CIDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CIDE.  If not, see <http://www.gnu.org/licenses/>.

    See http://www.fosd.de/cide/ for further information.
*/

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;
import org.sat4j.specs.TimeoutException;

import featureide.fm.core.Feature;
import featureide.fm.core.FeatureModel;
import featureide.fm.core.io.UnsupportedModelException;
import featureide.fm.core.io.guidsl.FeatureModelReader;

public class FeatureModelTest {

	@Before
	public void setUp() throws Exception {

	}

	private IFile createModelFile(String content) throws CoreException {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
				"test_generated");
		if (!project.exists())
			project.create(null);
		if (!project.isOpen())
			project.open(null);
		IFile file = project.getFile("_testfile");
		if (file.exists())
			file.delete(true, true, null);
		file.create(new ByteArrayInputStream(content.getBytes()), true, null);
		return file;
	}

	private FeatureModel createFeatureModel(String content)
			throws CoreException, UnsupportedModelException, IOException {
		IFile file = createModelFile(content);
		FeatureModel model = new FeatureModel();
		FeatureModelReader modelReader = new FeatureModelReader(model);
		modelReader.readFromFile(file);
		return model;
	}

	@Test
	public void testFeatureModelCreation() throws CoreException,
			UnsupportedModelException, IOException {
		FeatureModel model = createFeatureModel("A: B C::_A;");
		System.out.println(model.getFeatureNames());
		Assert.assertNotNull(model);
	}

	@Test
	public void testIsValid() throws CoreException, UnsupportedModelException,
			IOException, TimeoutException {

		Assert.assertTrue(createFeatureModel(
				"A: [B] [C] ::_A;\n%%\nB implies C;").isValid());
		Assert.assertFalse(createFeatureModel(
				"A: [B] [C] ::_A;\n%%\nB and C;B implies not C;").isValid());

	}

	@Test
	public void testImplicationSimple() throws CoreException,
			UnsupportedModelException, IOException, TimeoutException {

		FeatureModel model = createFeatureModel("A: [B] [C] [D] ::_A;\n%%\nB implies C;");
		Feature A = model.getFeature("A");
		Feature B = model.getFeature("B");
		Feature C = model.getFeature("C");
		Feature D = model.getFeature("D");

		assertImpl(model, Collections.singleton(A), Collections.singleton(A));
		assertNotImpl(model, Collections.singleton(A), Collections.singleton(B));
		assertImpl(model, Collections.singleton(B), Collections.singleton(A));
		assertImpl(model, L(A, B), Collections.singleton(B));
		assertImpl(model, Collections.singleton(B), Collections.singleton(C));
		assertImpl(model, Collections.singleton(B), Collections.EMPTY_SET);
		assertNotImpl(model, Collections.EMPTY_SET, Collections.singleton(C));
		assertImpl(model, L(B, D), L(C, D));
		assertImpl(model, L(A, B), Collections.singleton(C));

	}

	@Test
	public void testInvalidImplication() throws CoreException,
			UnsupportedModelException, IOException, TimeoutException {

		FeatureModel model = createFeatureModel("A: [B] [C] ::_A;\n%%\nB and C;B implies not C;");
		Feature A = model.getFeature("A");
		Feature B = model.getFeature("B");

		assertImpl(model, Collections.singleton(B), Collections.singleton(A));
	}

	@Test
	public void testImplicationDeadFeatures() throws CoreException,
			UnsupportedModelException, IOException, TimeoutException {

		FeatureModel model = createFeatureModel("A: [B] [C] [D] ::_A;\n%%\nA implies not C;D implies C;");
		Feature A = model.getFeature("A");
		Feature B = model.getFeature("B");
		Feature C = model.getFeature("C");
		Feature D = model.getFeature("D");

		assertImpl(model, Collections.singleton(C), Collections.singleton(D));
		assertImpl(model, Collections.singleton(D), Collections.singleton(C));
		assertNotImpl(model, Collections.singleton(A), Collections.singleton(C));
		assertNotImpl(model, Collections.singleton(B), Collections.singleton(C));
		assertImpl(model, Collections.singleton(C), Collections.singleton(B));
		assertImpl(model, L(B, C), Collections.singleton(A));

	}

	@Test
	public void testImplicationSubset() throws CoreException,
			UnsupportedModelException, IOException, TimeoutException {

		FeatureModel model = createFeatureModel("A: [B] [C] [D] ::_A;");
		Feature A = model.getFeature("A");
		Feature B = model.getFeature("B");
		Feature C = model.getFeature("C");
		Feature D = model.getFeature("D");

		assertImpl(model, L(L(B, C), D), L(B, C));
		assertImpl(model, L(L(B, C), D), L(C, D));
		assertNotImpl(model, L(C, D), L(L(B, C), D));
		assertImpl(model, L(L(B, C), A), L(B, C));
		assertImpl(model, L(L(B, C), D), Collections.singleton(C));

	}

	private Set<Feature> L(Feature a, Feature b) {
		HashSet<Feature> result = new HashSet<Feature>();
		result.add(a);
		result.add(b);
		return result;
	}

	private Set<Feature> L(Set<Feature> set, Feature b) {
		set.add(b);
		return set;
	}

	private void assertImpl(FeatureModel model, Set<Feature> a, Set<Feature> b)
			throws TimeoutException {
		Assert.assertTrue("assume: " + a + "=>" + b, model.checkImplies(a, b));
	}

	private void assertNotImpl(FeatureModel model, Set<Feature> a,
			Set<Feature> b) throws TimeoutException {
		Assert.assertFalse("assume not: " + a + "=>" + b, model.checkImplies(a,
				b));
	}

}
