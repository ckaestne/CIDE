package coloredide.export;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import coloredide.features.Feature;

public class DerivativeComparator implements Comparator<Set<Feature>> {

	public int compare(Set<Feature> o1, Set<Feature> o2) {
		if (o1.size() > o2.size())
			return -1;
		if (o1.size() < o2.size())
			return 1;

		List<Feature> list1 = new ArrayList<Feature>(o1);
		List<Feature> list2 = new ArrayList<Feature>(o2);
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
