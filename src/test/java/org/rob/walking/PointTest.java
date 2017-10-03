package org.rob.walking;

import static org.junit.Assert.*;
import org.junit.Test;

public class PointTest {

	@Test
	public void shouldAddPoints() {
		Point point = new Point(10, 10);
		Point actual = point.add(new Point(20, 30));
		assertEquals(actual, new Point(30, 40));
	}

	@Test
	public void shouldSubtractPoints() {
		Point point = new Point(100, 200);
		Point actual = point.minus(new Point(20, 30));
		assertEquals(actual, new Point(80, 170));
	}

	@Test
	public void shouldCalculateLengthBetweenPoints() {
		Point p1 = new Point(10, 10);
		Point p2 = new Point(13, 14);
		double length = p1.length(p2);
		assertEquals(5.0, length, 0.0001);
		assertEquals(p1.length(p2), p2.length(p1), 0.0001);
	}

	@Test
	public void shouldCreatePolarPoint() {
		double length = Math.sqrt(4*4 + 4*4);
		Point actual = Point.polar(Math.PI/4, length);
		Point expected = new Point(4, 4);
		assertEquals(expected.getX(), actual.getX(), 0.0001);
		assertEquals(expected.getY(), actual.getY(), 0.0001);
	}

	@Test
	public void shouldCalculateLawOfCosines() {
		//Equilateral triangle
		Point p1 = new Point(10, 10);
		Point p2 = new Point(110, 10);
		Point expected = Point.lawOfCosines(p1, p2, (double) 100, (double) 100);
		Point actual = new Point(60, 96.60254);
		assertEquals(expected.getX(), actual.getX(), 0.0001);
		assertEquals(expected.getY(), actual.getY(), 0.0001);
	}
}
