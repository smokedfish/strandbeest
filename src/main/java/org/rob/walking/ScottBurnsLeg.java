package org.rob.walking;

import java.awt.Color;

/*
 * From http://scottburns.us/walking-mechanism/
 */
public class ScottBurnsLeg {
	private final Graphic graphic;

	public ScottBurnsLeg(Graphic graphic) {
		this.graphic = graphic;
	}

	public void render(double ang) {
		// Theo Jansens Constants
		double a = scale(9.61);
		double b = scale(12.9);
		double c = scale(9.4);
		double d = scale(10.0);
		double e = scale(8.3);
		double f = scale(10.1);
		double g = scale(9.4);
		double h = scale(16.8);
		double i = scale(11.6);
		double j = scale(18.5);
		double k = scale(15.7);
		double l = scale(2.77);
		double m = scale(3.1);

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
		graphic.line(Z, x1);
		graphic.line(x1, w1);
		graphic.line(w1, y1);
		graphic.line(w1, v1);
		graphic.line(y1, v1);
		graphic.line(x1, u1);
		graphic.line(y1, u1);
		graphic.line(u1, t1);
		graphic.line(v1, t1);
		graphic.line(u1, s1);
		graphic.line(t1, s1);
		graphic.line(Z, y1);
		graphic.circle(Color.blue, s1, 6);
	}

	private double scale(double d) {
		return d * 11.86264308;
	}
}
