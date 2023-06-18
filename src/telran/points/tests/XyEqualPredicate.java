package telran.points.tests;

import java.util.function.Predicate;

import telran.points.Point;

public class XyEqualPredicate implements Predicate<Point> {

	@Override
	public boolean test(Point t) {
		return t.getX() == t.getY();
	}

}
