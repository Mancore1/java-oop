package telran.numbers;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class RangePredicate implements Iterable<Integer>{
	private int minInclusive;
	private int maxExclusive;
	Predicate<Integer> predicate;
	
	public RangePredicate(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("min must be less than max");
		}
		minInclusive = min;
		maxExclusive = max;
	}

	public Predicate<Integer> getPredicate() {
		return predicate;
	}

	public void setPredicate(Predicate<Integer> predicate) {
		this.predicate = predicate;
	}
	
	public int[] toArray() {
		int length = 0;
		for (int i = minInclusive; i < maxExclusive; i++) {
			if (predicate == null || predicate.test(i)) {
				length++;
			}
		}
		int[] res = new int[length];
		int index = 0;
		for (int i = minInclusive; i < maxExclusive; i++) { 
			if (predicate == null || predicate.test(i)) {
				res[index] = i;
				index++;
			}
		}
		return res;
	}

	private class RangePredicateIterator implements Iterator<Integer> {
		int current;
		Predicate<Integer> innerPredicate;
		
		RangePredicateIterator(Predicate<Integer> predicate) {
			innerPredicate = predicate;
		}
		@Override
		public boolean hasNext() {
			while (current < maxExclusive && !innerPredicate.test(current)) {
				current++;
			}
			return current < maxExclusive;
		}

		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			int res = current;
			current++;
			return res;
		}
	}

	@Override
	public Iterator<Integer> iterator() {
		return new RangePredicateIterator(predicate);
	}
}
