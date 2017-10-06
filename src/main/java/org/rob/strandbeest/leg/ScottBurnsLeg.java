package org.rob.strandbeest.leg;

import java.awt.Color;

import org.rob.strandbeest.graphic.Graphic;
import org.rob.strandbeest.graphic.Point;
import org.rob.strandbeest.graphic.RoundedHelper;

/**
 * From: http://scottburns.us/walking-mechanism/
 */
public class ScottBurnsLeg {
	private final Graphic graphic;
	private final RoundedHelper roundedHelper;
	private double thickness = 10;
	private double radius = 5;

	public ScottBurnsLeg(Graphic graphic) {
		this.graphic = graphic;
		this.roundedHelper = new RoundedHelper(graphic, thickness);
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

		Point y2 = z.add(new Point(-a, -l));
		Point w2 = Point.lawOfCosines(y2, x, b, j);
		Point v2 = Point.lawOfCosines(y2, w2, d, e);
		Point u2 = Point.lawOfCosines(x, y2, k, c);
		Point t2 = Point.lawOfCosines(u2, v2, g, f);
		Point s2 = Point.lawOfCosines(u2, t2, i, h);

		draw(z, x, y1, w1, v1, u1, t1, s1);
		draw(z, x, y2, w2, v2, u2, t2, s2);
		triangle(y1, z, y2);
	}

	private void draw(Point Z, Point x, Point y, Point w, Point v, Point u, Point t, Point s) {
		rectangle(Z, x);
		rectangle(x, w);
		rectangle(x, u);
		rectangle(y, u);
		rectangle(v, t);
		triangle(w, y, v);
		triangle(t, s, u);
	}

	private void rectangle(Point p1, Point p2) {
		graphic.circle(Color.green, p1, radius);
		graphic.circle(Color.green, p2, radius);
		roundedHelper.roundedRectangle(Color.black, p1, p2);
	}

	private void triangle(Point p1, Point p2, Point p3) {
		graphic.circle(Color.green, p1, radius);
		graphic.circle(Color.green, p2, radius);
		graphic.circle(Color.green, p3, radius);
		roundedHelper.roundedTriangle(Color.black, p1, p2, p3);
	}

	private double scale(double d) {
		return d * 11.86264308;
	}
}
