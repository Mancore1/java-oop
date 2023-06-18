package telran.util.tests;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		boolean isEven = o1 % 2 == 0;
		boolean isOdd = o2 % 2 != 0;
		
		if (isEven && isOdd) {
			return -1;
		} else if(!isEven && !isOdd) {
			return 1;
		} else if (isEven && !isOdd) {
			return o1.compareTo(o2);
		} else {
			return o2.compareTo(o1);
		}
	}
}
