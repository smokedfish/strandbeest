package org.rob.walking;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Point {
	private final double x;
	private final double y;

	public Point add(Point p) {
		return new Point(x + p.getX(), y + p.getY());
	}

	public Point minus(Point p) {
		return new Point(x - p.getX(), y - p.getY());
	}

	public double length(Point p2) {
		return minus(p2).length();
	}

	public double length() {
		return Math.sqrt((getX() * getX()) + (getY() * getY()));
	}

	public static Point polar(double angle, double length) {
		return new Point(Math.cos(angle) * length, Math.sin(angle) * length);
	}

	public static Point lawOfCosines(Point p1, Point p2, double l1, double l2) {
		Point delta = p1.minus(p2);
		double sAB = delta.length();
		double ang12 = Math.atan2(delta.getY(), delta.getX());
		double ang0 = Math.acos((l2 * l2 - l1 * l1 - sAB * sAB) / (-Math.abs(2 * sAB * l1)));
		return p1.add(Point.polar(ang0 + ang12, -l1));
	}

	public static Point lawOfCosines2(Point p1, Point p2, double l1, double l2) {
		return lawOfCosines(p2, p1, l2, l1);
	}
}