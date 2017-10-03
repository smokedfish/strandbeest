package org.rob.walking;

import java.awt.Color;

public class TheoJansenLeg {
	private final Graphic graphic;

	public TheoJansenLeg(Graphic graphic) {
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
		graphic.circle(Color.red, s1, 6);
	}

	private double scale(double d) {
		return d * 3;
	}
}
