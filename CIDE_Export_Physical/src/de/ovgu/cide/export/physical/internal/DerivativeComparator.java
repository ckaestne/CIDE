package de.ovgu.cide.export.physical.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import de.ovgu.cide.features.IFeature;

public class DerivativeComparator implements Comparator<Set<IFeature>> {

	public int compare(Set<IFeature> o1, Set<IFeature> o2) {
		if (o1.size() > o2.size())
			return -1;
		if (o1.size() < o2.size())
			return 1;

		List<IFeature> list1 = new ArrayList<IFeature>(o1);
		List<IFeature> list2 = new ArrayList<IFeature>(o2);
		Collections.sort(list1);
		Collections.sort(list2);

		for (int idx = list1.size() - 1; idx >= 0; idx--) {
			int compareResult = list1.get(idx).compareTo(list2.get(idx));
			if (compareResult != 0)
				return compareResult;
		}

		return 0;
	}

}
