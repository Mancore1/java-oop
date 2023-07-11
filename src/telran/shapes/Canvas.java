package telran.shapes;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class Canvas implements Shape, Iterable<Shape> {
	public Shape[] shapes = new Shape[0];

	private class CanvasIterator implements Iterator<Shape> {
		boolean flNext = false;		
		int current = 0;
		@Override
		public boolean hasNext() {
			
			return current < shapes.length;
		}

		@Override
		public Shape next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			flNext = true;
			return shapes[current++];
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			flNext = false;
			Shape[] res = new Shape[shapes.length - 1];
			
			if (res.length != 0) {
				System.arraycopy(shapes, 0, res, 0, current - 1);
				System.arraycopy(shapes, current, res, current - 1, res.length - current + 1);
			}
			current--;
			shapes = res;
			}
		}

	@Override
	public int perimeter() {
		int sum = 0;
		for (Shape shape: this) {
				sum += shape.perimeter();
			}
		return sum;
	}

	@Override
	public int square() {
		int sum = 0;
		for (Shape shape: this) {
				sum += shape.square();
		}
		return sum;
	}

	@Override
	public Iterator<Shape> iterator() {
		return new CanvasIterator();
	}

	public void addShape(Shape shape) {
		shapes = Arrays.copyOf(shapes, shapes.length + 1);
		shapes[shapes.length - 1] = shape;
	}
	
	public boolean removeIf(Predicate<Shape> predicate) {
		int oldLength = shapes.length;
		Iterator<Shape> it = iterator();
		while (it.hasNext()) {
			Shape shape = it.next();
			if (predicate.test(shape)) {
				it.remove();
			}
		}
			return oldLength > shapes.length;
	}
	
	public boolean removeNestedCanvases() {
		return removeIf(shape -> shape instanceof Canvas);
	}
}
