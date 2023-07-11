package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.shapes.Canvas;
import telran.shapes.Rectangle;
import telran.shapes.Shape;
import telran.shapes.Square;

class CanvasTest {
	Rectangle rectangle1 = new Rectangle(4, 6);
	Rectangle rectangle2 = new Rectangle(2, 3);
	Square square1 = new Square(5);
	Square square2 = new Square(10);
	Canvas canvas1;
	Canvas canvas2;
	Canvas canvasTest;
	@BeforeEach
	void setUp() throws Exception {
		canvas1 = new Canvas();
		canvas2 = new Canvas();
		canvasTest = new Canvas();
	}

	@Test
	void perimeterTest() {
		canvasTest.addShape(rectangle1);
		assertEquals(20, canvasTest.perimeter());
		canvasTest.addShape(rectangle2);
		assertEquals(30, canvasTest.perimeter());
		canvasTest.addShape(square1);
		assertEquals(50, canvasTest.perimeter());
		canvas1.addShape(square2);
		canvasTest.addShape(canvas1);
		assertEquals(90, canvasTest.perimeter());
	}
	
	@Test
	void squareTest() {
		canvasTest.addShape(rectangle1);
		assertEquals(24, canvasTest.square());
		canvasTest.addShape(rectangle2);
		assertEquals(30, canvasTest.square());
		canvasTest.addShape(square1);
		assertEquals(55, canvasTest.square());
		canvas2.addShape(square2);
		canvasTest.addShape(canvas2);
		assertEquals(155, canvasTest.square());
	}
	
	@Test
	void removedNestedCanvasTest() {
		canvas1.addShape(square1);
		canvas2.addShape(square1);
		canvasTest.addShape(square1);
		canvasTest.addShape(canvas1);
		canvasTest.addShape(canvas2);
		assertEquals(75, canvasTest.square());
		assertTrue(canvasTest.removeNestedCanvases());
		assertFalse(canvasTest.removeNestedCanvases());
		assertEquals(25, canvasTest.square());
	}
	
	@Test
	void removeIteratorTest() {
		canvasTest.addShape(square1);
		Iterator<Shape> it = canvasTest.iterator();
		assertThrowsExactly(IllegalStateException.class, () -> it.remove());
		it.next();
		it.remove();
		assertThrowsExactly(IllegalStateException.class, () -> it.remove());
	}

	@Test
	void removeIfTest() {
		canvasTest.addShape(square1);
		canvasTest.addShape(square2);
		canvasTest.addShape(rectangle1);
		canvasTest.addShape(rectangle2);
		assertEquals(155 ,canvasTest.square());
		assertTrue(canvasTest.removeIf(shape -> shape.square() < 20));
		assertFalse(canvasTest.removeIf(shape -> shape.square() < 20));
		assertEquals(149 ,canvasTest.square());
	}
}
