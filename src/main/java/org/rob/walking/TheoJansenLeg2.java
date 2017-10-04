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
	}

	private void draw(Point Z, Point x1, Point y1, Point w1, Point v1, Point u1, Point t1, Point s1) {
		rod(new Point(100,100), new Point(300, 300));
		rod(Z, x1);
		rod(x1, w1);
		rod(w1, y1);
		rod(w1, v1);
		rod(y1, v1);
		rod(x1, u1);
		rod(y1, u1);
		rod(u1, t1);
		rod(v1, t1);
		rod(u1, s1);
		rod(t1, s1);
		rod(Z, y1);
		graphic.circle(Color.red, s1, 6);
	}

	private void rod(Point p1, Point p2) {
		int r = 10;
		int t = 20;
		Point delta = p1.minus(p2);
		double ang12 = Math.atan2(delta.getY(), delta.getX());
		Point a = Point.polar(ang12 + Math.PI/2, t);
		Point b = Point.polar(ang12 - Math.PI/2, t);

		Point p1a = p1.add(a);
		Point p1b = p1.add(b);
		Point p2a = p2.add(a);
		Point p2b = p2.add(b);
		graphic.line(p1b, p2b);
		graphic.line(p2a, p1a);
		graphic.arc(Color.black, p1, 2 * t, ang12 - Math.PI/2, Math.PI);
		graphic.arc(Color.black, p2, 2 * t, ang12 + Math.PI/2, Math.PI);
		graphic.circle(Color.green, p1, r);
		graphic.circle(Color.green, p2, r);
	}

	private double scale(double d) {
		return d * 3;
	}
}
