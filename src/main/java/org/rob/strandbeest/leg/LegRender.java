package org.rob.strandbeest.leg;

import java.awt.Color;

import org.rob.strandbeest.graphic.Graphic;
import org.rob.strandbeest.graphic.Point;
import org.rob.strandbeest.graphic.RoundedHelper;

public class LegRender {
	private final Leg leg;
	private final double scale;
	private final RoundedHelper roundedHelper;
	private final double radius;

	public LegRender(Leg leg, double scale, double thickness, double radius) {
		this.leg = leg;
		this.scale = scale;
		this.roundedHelper = new RoundedHelper(thickness);
		this.radius = radius;
	}

	public void render(Graphic graphic, double ang) {
		Point z = new Point(0,0);
		Point x = z.add(Point.polar(ang, scale(leg.getM())));

		Point y1 = z.add(new Point(scale(leg.getA()), -scale(leg.getL())));
		Point w1 = Point.lawOfCosines(x, y1, scale(leg.getJ()), scale(leg.getB()));
		Point v1 = Point.lawOfCosines(w1, y1, scale(leg.getE()), scale(leg.getD()));
		Point u1 = Point.lawOfCosines(y1, x, scale(leg.getC()), scale(leg.getK()));
		Point t1 = Point.lawOfCosines(v1, u1, scale(leg.getF()), scale(leg.getG()));
		Point s1 = Point.lawOfCosines(t1, u1, scale(leg.getH()), scale(leg.getI()));

		Point y2 = z.add(new Point(-scale(leg.getA()), -scale(leg.getL())));
		Point w2 = Point.lawOfCosines(y2, x, scale(leg.getB()), scale(leg.getJ()));
		Point v2 = Point.lawOfCosines(y2, w2, scale(leg.getD()), scale(leg.getE()));
		Point u2 = Point.lawOfCosines(x, y2, scale(leg.getK()), scale(leg.getC()));
		Point t2 = Point.lawOfCosines(u2, v2, scale(leg.getG()), scale(leg.getF()));
		Point s2 = Point.lawOfCosines(u2, t2, scale(leg.getI()), scale(leg.getH()));

		draw(graphic, z, x, y1, w1, v1, u1, t1, s1);
		draw(graphic, z, x, y2, w2, v2, u2, t2, s2);
		triangle(graphic, y1, z, y2);
	}

	private void draw(Graphic graphic, Point Z, Point x, Point y, Point w, Point v, Point u, Point t, Point s) {
		rectangle(graphic, Z, x);
		rectangle(graphic, x, w);
		rectangle(graphic, x, u);
		rectangle(graphic, y, u);
		rectangle(graphic, v, t);
		triangle(graphic, w, y, v);
		triangle(graphic, t, s, u);
	}

	private void rectangle(Graphic graphic, Point p1, Point p2) {
		graphic.circle(Color.green, p1, radius);
		graphic.circle(Color.green, p2, radius);
		roundedHelper.roundedRectangle(graphic, Color.black, p1, p2);
	}

	private void triangle(Graphic graphic, Point p1, Point p2, Point p3) {
		graphic.circle(Color.green, p1, radius);
		graphic.circle(Color.green, p2, radius);
		graphic.circle(Color.green, p3, radius);
		roundedHelper.roundedTriangle(graphic, Color.black, p1, p2, p3);
	}

	private double scale(double d) {
		return d * scale;
	}
}
