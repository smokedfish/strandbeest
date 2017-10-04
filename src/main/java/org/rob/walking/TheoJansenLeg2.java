package org.rob.walking;

import java.awt.Color;

public class TheoJansenLeg2 {
	private final Graphic graphic;

	public TheoJansenLeg2(Graphic graphic) {
		this.graphic = graphic;
	}

	public void render(double ang) {
		// Theo Jansens Constants
		double a = scale(38.0);
		double b = scale(41.5);
		double c = scale(39.3);
		double d = scale(40.1);
		double e = scale(55.8);
		double f = scale(39.4);
		double g = scale(36.7);
		double h = scale(65.7);
		double i = scale(49.0);
		double j = scale(50.0);
		double k = scale(61.9);
		double l = scale(7.8);
		double m = scale(15.0);

		Point z = new Point(0,0);
		Point x = z.add(Point.polar(ang, m));

		Point y1 = z.add(new Point(a, -l));
		Point w1 = Point.lawOfCosines(x, y1, j, b);
		Point v1 = Point.lawOfCosines(w1, y1, e, d);
		Point u1 = Point.lawOfCosines(y1, x, c, k);
		Point t1 = Point.lawOfCosines(v1, u1, f, g);
		Point s1 = Point.lawOfCosines(t1, u1, h, i);
		draw(z, x, y1, w1, v1, u1, t1, s1);

		Point y2 = z.add(new Point(-a, -l));
		Point w2 = Point.lawOfCosines(y2, x, b, j);
		Point v2 = Point.lawOfCosines(y2, w2, d, e);
		Point u2 = Point.lawOfCosines(x, y2, k, c);
		Point t2 = Point.lawOfCosines(u2, v2, g, f);
		Point s2 = Point.lawOfCosines(u2, t2, i, h);
		draw(z, x, y2, w2, v2, u2, t2, s2);

		triangle(y1, z, y2);
	}

	private void draw(Point Z, Point x, Point y, Point w, Point v, Point u, Point t, Point s) {
		rod(Z, x);
		rod(x, w);
		rod(x, u);
		rod(y, u);
		rod(v, t);
		//		rod(Z, y1);
		triangle(w, y, v);
		triangle(t, s, u);
		graphic.circle(Color.red, s, 6);
	}

	private void triangle(Point p1, Point p2, Point p3) {
		int r = 10;
		int t = 20;

		graphic.circle(Color.black, p1, r);
		graphic.circle(Color.black, p2, r);
		graphic.circle(Color.black, p3, r);

		Point sum = p1.add(p2.add(p3));
		Point centroid = new Point(sum.getX()/3, sum.getY()/3);

		Point a12 = offset(centroid, p1, p2, t);
		Point a23 = offset(centroid, p2, p3, t);
		Point a13 = offset(centroid, p3, p1, t);

		Point x1 = p1.minus(centroid);
		Point px1 = p1.add(Point.polar(x1.angle(), t));

		Point x2 = p2.minus(centroid);
		Point px2 = p2.add(Point.polar(x2.angle(), t));

		Point x3 = p3.minus(centroid);
		Point px3 = p3.add(Point.polar(x3.angle(), t));

		Point p1a12 = p1.add(a12);
		Point p2a12 = p2.add(a12);
		Point p2a23 = p2.add(a23);
		Point p3a23 = p3.add(a23);
		Point p1a13 = p1.add(a13);
		Point p3a13 = p3.add(a13);

		knob(p1, t, p1a13, p1a12);
		graphic.line(p1a12, p2a12);
		knob(p2, t, p2a12, p2a23);
		graphic.line(p2a23, p3a23);
		knob(p3, t, p3a23, p3a13);
		graphic.line(p3a13, p1a13);

		//		graphic.line(p1a12, p2a12);
		//		graphic.line(p2a12, px2);
		//		graphic.line(px2, p2a23);
		//
		//		graphic.line(p2a23, p3a23);
		//		graphic.line(p3a23, px3);
		//		graphic.line(px3, p3a13);
		//
		//		graphic.line(p3a13, p1a13);
		//		graphic.line(p1a13, px1);
		//		graphic.line(px1, p1a12);
	}

	private void knob(Point p1, int t, Point p1a12, Point p1a13) {
		/*
		 * Lets say that the center of the circle is (x0, y0) and that the arc contains your two points (x1, y1) and (x2, y2). Then the radius is: r=sqrt((x1-x0)(x1-x0) + (y1-y0)(y1-y0)). So:
		 */
		double startAngle = p1a12.minus(p1).angle();
		double endAngle = p1a13.minus(p1).angle();
		if (Math.abs(endAngle-startAngle) > Math.PI) {
			graphic.arc(Color.black, p1, t*2, startAngle, endAngle-startAngle);
		} else {
			graphic.arc(Color.red, p1, t*2, startAngle, endAngle-startAngle);
		}
	}

	private void drawRoundedCorner(Point angularPoint, Point p1, Point p2, double radius) {
		//Vector 1
		Point d1 = angularPoint.minus(p1);
		//Vector 2
		Point d2 = angularPoint.minus(p2);
		//Angle between vector 1 and vector 2 divided by 2
		double angle = (d1.angle() - d2.angle()) / 2;

		// The length of segment between angular point and the
		// points of intersection with the circle of a given radius
		double tan = Math.abs(Math.tan(angle));
		double segment = radius / tan;

		//Check the segment
		double length = Math.min(d1.length(), d2.length());

		if (segment > length) {
			segment = length;
			radius = (double)(length * tan);
		}

		// Points of intersection are calculated by the proportion between
		// the coordinates of the vector, length of vector and the length of the segment.
		Point p1Cross = GetProportionPoint(angularPoint, segment, d1.length(), d1.getX(), d1.getY());
		Point p2Cross = GetProportionPoint(angularPoint, segment, d2.length(), d2.getX(), d2.getY());

		// Calculation of the coordinates of the circle
		// center by the addition of angular vectors.
		double dx = angularPoint.getX() * 2 - p1Cross.getX() - p2Cross.getX();
		double dy = angularPoint.getY() * 2 - p1Cross.getY() - p2Cross.getY();

		double L = new Point(dx, dy).length();
		double d = new Point(segment, radius).length();

		Point circlePoint = GetProportionPoint(angularPoint, d, L, dx, dy);

		//StartAngle and EndAngle of arc
		double startAngle = Math.atan2(p1Cross.getY() - circlePoint.getY(), p1Cross.getX() - circlePoint.getX());
		double endAngle = Math.atan2(p2Cross.getY() - circlePoint.getY(), p2Cross.getX() - circlePoint.getX());

		//Sweep angle
		double sweepAngle = endAngle - startAngle;

		//Some additional checks
		Color color = Color.blue;
		if (sweepAngle < 0) {
			startAngle = endAngle;
			sweepAngle = -sweepAngle;
		}

		if (sweepAngle > Math.PI) {
			sweepAngle = Math.PI - sweepAngle;
			color = Color.red;
		}

		Point sum1 = p1.add(angularPoint);
		Point p13 = new Point(sum1.getX()/2, sum1.getY()/2);

		Point sum2 = p2.add(angularPoint);
		Point p23 = new Point(sum2.getX()/2, sum2.getY()/2);

		graphic.line(p13, p1Cross);
		graphic.line(p23, p2Cross);

		graphic.arc(color, circlePoint, radius * 2, startAngle, sweepAngle);
	}

	private Point GetProportionPoint(Point point, double segment, double length, double dx, double dy) {
		double factor = segment / length;
		return new Point(point.getX() - dx * factor, point.getY() - dy * factor);
	}

	private Point offset(Point centroid, Point p1, Point p2, int t) {
		Point intersect = intersect(p1, p2, centroid);
		Point d12 = centroid.minus(intersect);
		return Point.polar(Math.PI + d12.angle(), t);
	}

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

	private void rod(Point p1, Point p2) {
		int r = 10;
		int t = 20;
		Point delta = p1.minus(p2);
		double ang12 = Math.atan2(delta.getY(), delta.getX());
		Point a = Point.polar(ang12 + Math.PI/2, t);
		Point b = Point.polar(ang12 - Math.PI/2, t);

		graphic.line(p1.add(b), p2.add(b));
		graphic.line(p2.add(a), p1.add(a));
		graphic.arc(Color.black, p1, 2 * t, ang12 - Math.PI/2, Math.PI);
		graphic.arc(Color.black, p2, 2 * t, ang12 + Math.PI/2, Math.PI);
		graphic.circle(Color.green, p1, r);
		graphic.circle(Color.green, p2, r);
	}

	private double scale(double d) {
		return d * 3;
	}
}
