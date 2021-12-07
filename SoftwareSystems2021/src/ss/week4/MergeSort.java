package ss.week4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {

	public static void main(String[] args) {
		List<Integer> ls = new ArrayList<>(Arrays.asList(0, 1, 4, 3, 6, 8));
		List<Integer> newlist = mergeSort(ls);
		System.out.println(newlist);
	}

	public static <E extends Comparable<E>> List<E> mergeSort(List<E> data) {
		if(data.size() <= 1) {
			return data;
		} else {
			int halfSeparator = data.size() % 2 == 0 ? data.size() / 2 : (data.size() / 2) + 1;
			List<E> fst = mergeSort(data.subList(0, halfSeparator));
			List<E> snd = mergeSort(data.subList(halfSeparator, data.size()));
			List<E> result = new ArrayList<>(data.size());
			int fi = 0;
			int si = 0;
			while (fi < fst.size() && si < snd.size()) {
				if(fst.get(fi).compareTo(snd.get(si)) < 0) {
					result.add(fst.get(fi));
					fi += 1;
				} else {
					result.add(snd.get(si));
					si += 1;
				}
			}
			// check if any remain in fst
			while(fi < fst.size()) {
				result.add(fst.get(fi));
				fi += 1;
			}
			// check if any remain in snd
			while(si < snd.size()) {
				result.add(snd.get(si));
				si += 1;
			}
			return result;
		}
	}
}
