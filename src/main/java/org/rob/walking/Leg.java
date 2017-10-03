package org.rob.walking;

public class Leg {
	private final Graphic graphic;

	public Leg(Graphic graphic) {
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

		Point Z = new Point(0,0);
		Point x1 = Z.add(Point.polar(ang, m));
		Point y1 = Z.add(new Point(a, -l));
		Point w1 = Point.lawOfCosines(x1, y1, j, b);
		Point v1 = Point.lawOfCosines(w1, y1, e, d);
		Point u1 = Point.lawOfCosines(y1, x1, c, k);
		Point t1 = Point.lawOfCosines(v1, u1, f, g);
		Point s1 = Point.lawOfCosines(t1, u1, h, i);

		draw(Z, x1, y1, w1, v1, u1, t1, s1);

		Point x2 = Z.add(Point.polar(ang, m));
		Point y2 = Z.add(new Point(-a, -l));
		Point w2 = Point.lawOfCosines2(x2, y2, j, b);
		Point v2 = Point.lawOfCosines2(w2, y2, e, d);
		Point u2 = Point.lawOfCosines2(y2, x2, c, k);
		Point t2 = Point.lawOfCosines2(v2, u2, f, g);
		Point s2 = Point.lawOfCosines2(t2, u2, h, i);

		draw(Z, x2, y2, w2, v2, u2, t2, s2);
	}

	private void draw(Point Z, Point x1, Point y1, Point w1, Point v1, Point u1, Point t1, Point s1) {
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
		graphic.point(s1);
	}

	public void renderx(double ang) {
		Point p1 = new Point(10, 10);
		Point p2 = p1.add(Point.polar(ang, 100));
		Point p3 = Point.lawOfCosines(p1, p2, (double) 100, (double) 100);

//		System.out.println("p1=" + p1 + " p2=" + p2 + " p3=" + p3);

		rod(p1, p2);
		rod(p2, p3);
		rod(p3, p1);
	}

	private double scale(double d) {
		return d * 3;
	}

	private void rod(Point p1, Point p2) {
		graphic.line(p1, p2);
	}
}
