package org.rob.strandbeest.graphic;

import lombok.Builder;
import lombok.Data;

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

	public double angle() {
		return Math.atan2(getY(), getX());
	}

	public static Point polar(double angle, double length) {
		return new Point(Math.cos(angle) * length, Math.sin(angle) * length);
	}

	/**
	 * Calculate Point that is located l1 from p1 and l2 from p2
	 */
	public static Point lawOfCosines(Point p1, Point p2, double l1, double l2) {
		Point delta = p1.minus(p2);
		double sAB = delta.length();
		double ang0 = Math.acos((l2 * l2 - l1 * l1 - sAB * sAB) / (-Math.abs(2 * sAB * l1)));
		return p1.add(Point.polar(ang0 + delta.angle(), -l1));
	}

	/**
	 * Calculate Point forming a line with centroid that is perpendicular to line p1 p2. Point is offset
	 * from p1 p2 and on the opposite side to centroid.
	 */
	public static Point offset(Point centroid, Point p1, Point p2, double offset) {
		Point intersect = intersect(p1, p2, centroid);
		Point d12 = centroid.minus(intersect);
		return Point.polar(Math.PI + d12.angle(), offset);
	}

	/**
	 * Calculate the closest Point between x and line p1 p2
	 */
	public static Point intersect(Point p1, Point p2, Point x) {
		Point d12 = p2.minus(p1);
		if ((d12.getX() == 0) && (d12.getY() == 0)) {
			throw new IllegalArgumentException("p1 and p2 cannot be the same point");
		}

		Point d31 = x.minus(p1);

		final double u = (d31.getX() * d12.getX() + d31.getY() * d12.getY()) / (d12.getX() * d12.getX() + d12.getY() * d12.getY());

		final Point closestPoint;
		if (u < 0) {
			closestPoint = p1;
		} else if (u > 1) {
			closestPoint = p2;
		} else {
			closestPoint = new Point(p1.getX() + u * d12.getX(), p1.getY() + u * d12.getY());
		}
		return closestPoint;
	}
}