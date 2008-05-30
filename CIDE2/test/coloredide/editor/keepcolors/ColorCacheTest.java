package coloredide.editor.keepcolors;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import coloredide.editor.keepcolors.ColorCache.ColoredItem;
import coloredide.features.Feature;
import coloredide.features.FeatureManager;

public class ColorCacheTest {

	private ColorCache cache;

	@Before
	public void setUp() throws Exception {
		cache = new ColorCache();
		Set<Feature> f1 = new HashSet<Feature>();
		f1.add(FeatureManager.getFeatures().get(2));
		f1.add(FeatureManager.getFeatures().get(3));
		Set<Feature> f2 = new HashSet<Feature>();
		f2.add(FeatureManager.getFeatures().get(1));
		cache.addItem("class", new HashSet<Feature>(), 0, 1000);
		cache.addItem("method", f1, 100, 900);
		cache.addItem("statement", new HashSet<Feature>(), 200, 300);
		cache.addItem("statement", f2, 400, 500);
		cache.addItem("statement", new HashSet<Feature>(), 600, 700);
	}

	@Test
	public void testTrivial() {
		Assert.assertEquals(5, cache.itemList.size());
	}

	@Test
	public void testInsertEnd() {
		cache.modifiedText("abc", 1000, 0);
		assertItem("class", 0, 1000);
		assertItem("method", 100, 900);
		assertItem("statement", 200, 300);
		assertItem("statement", 400, 500);
		assertItem("statement", 600, 700);
	}

	@Test
	public void testInsertStart() {
		cache.modifiedText("abc", 0, 0);
		assertItem("class", 3, 1003);
		assertItem("method", 103, 903);
		assertItem("statement", 203, 303);
		assertItem("statement", 403, 503);
		assertItem("statement", 603, 703);
	}

	@Test
	public void testInsertBetween() {
		cache.modifiedText("abc", 200, 0);
		assertItem("class", 0, 1003);
		assertItem("method", 100, 903);
		assertItem("statement", 203, 303);
		assertItem("statement", 403, 503);
		assertItem("statement", 603, 703);
	}

	@Test
	public void testRemoveEnd() {
		cache.modifiedText("", 1100, 3);
		assertItem("class", 0, 1000);
		assertItem("method", 100, 900);
		assertItem("statement", 200, 300);
		assertItem("statement", 400, 500);
		assertItem("statement", 600, 700);
	}

	@Test
	public void testRemoveStart() {
		cache.modifiedText("", 0, 3);
		assertItem("class", 0, 997);
		assertItem("method", 97, 897);
		assertItem("statement", 197, 297);
		assertItem("statement", 397, 497);
		assertItem("statement", 597, 697);
	}

	@Test
	public void testRemoveBound() {
		cache.modifiedText("", 99, 3);
		assertItem("class", 0, 997);
		assertItem("method", 99, 897);
		assertItem("statement", 197, 297);
		assertItem("statement", 397, 497);
		assertItem("statement", 597, 697);
	}

	@Test
	public void testRemoveBound2() {
		cache.modifiedText("", 299, 3);
		assertItem("class", 0, 997);
		assertItem("method", 100, 897);
		assertItem("statement", 200, 299);
		assertItem("statement", 397, 497);
		assertItem("statement", 597, 697);
	}

	@Test
	public void testRemoveCover() {
		cache.modifiedText("", 200, 100);
		assertItem("class", 0, 900);
		assertItem("method", 100, 800);
		assertItem("statement", 300, 400);
		assertItem("statement", 500, 600);
		Assert.assertEquals(printItemList(cache.itemList), 4, cache.itemList
				.size());
	}

	@Test
	public void testRemoveCover2() {
		cache.modifiedText("", 199, 103);
		assertItem("class", 0, 897);
		assertItem("method", 100, 797);
		assertItem("statement", 297, 397);
		assertItem("statement", 497, 597);
		Assert.assertEquals(4, cache.itemList.size());
	}
	

	private void assertItem(String string, int i, int j) {
		Iterator<ColoredItem> iter = cache.itemList.iterator();
		while (iter.hasNext()) {
			ColoredItem item = iter.next();
			if (item.type.equals(string)) {
				if (item.offset == i && item.length + item.offset == j)
					return;
			}
		}
		Assert.fail("Did not find item \"" + string + "\" at " + i + "-" + j
				+ " (" + printItemList(cache.itemList) + ")");
	}

	private String printItemList(List<ColoredItem> itemList) {
		String r = "";
		for (ColoredItem i : itemList) {
			if (r.length() > 0)
				r += ",";
			r += i.toString();
		}
		return r;
	}
}
