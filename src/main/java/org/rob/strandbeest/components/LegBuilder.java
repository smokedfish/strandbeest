package org.rob.strandbeest.components;

import org.rob.strandbeest.graphic.Graphic;
import org.rob.strandbeest.graphic.Graphic.Style;
import org.rob.strandbeest.graphic.Point;

public class LegBuilder {
	private final Leg leg;
	private final StrutBuilder strutBuilder;

	public LegBuilder(Leg leg, double thickness, double radius) {
		this.leg = leg;
		this.strutBuilder = new StrutBuilder(thickness, radius);
	}

	public void draw(Graphic graphic, double ang) {
		Point z = new Point(0,0);
		Point x = z.add(Point.polar(ang, leg.getM()));

		Point y1 = z.add(new Point(leg.getA(), -leg.getL()));
		Point w1 = Point.lawOfCosines(x, y1, leg.getJ(), leg.getB());
		Point v1 = Point.lawOfCosines(w1, y1, leg.getE(), leg.getD());
		Point u1 = Point.lawOfCosines(y1, x, leg.getC(), leg.getK());
		Point t1 = Point.lawOfCosines(v1, u1, leg.getF(), leg.getG());
		Point s1 = Point.lawOfCosines(t1, u1, leg.getH(), leg.getI());
		draw(graphic.group("leg-lhs"), z, x, y1, w1, v1, u1, t1, s1);

		Point y2 = z.add(new Point(-leg.getA(), -leg.getL()));
		Point w2 = Point.lawOfCosines(y2, x, leg.getB(), leg.getJ());
		Point v2 = Point.lawOfCosines(y2, w2, leg.getD(), leg.getE());
		Point u2 = Point.lawOfCosines(x, y2, leg.getK(), leg.getC());
		Point t2 = Point.lawOfCosines(u2, v2, leg.getG(), leg.getF());
		Point s2 = Point.lawOfCosines(u2, t2, leg.getI(), leg.getH());
		draw(graphic.group("leg-rhs"), z, x, y2, w2, v2, u2, t2, s2);

//		triangle(graphic.group("body"), y1, z, y2);
	}

	private void draw(Graphic graphic, Point z, Point x, Point y, Point w, Point v, Point u, Point t, Point s) {
		strutBuilder.rectangleStrut(graphic.group("strut-z-x"), Style.CUT, z, x);
		strutBuilder.rectangleStrut(graphic.group("strut-x-w"), Style.CUT, x, w);
		strutBuilder.rectangleStrut(graphic.group("strut-x-u"), Style.CUT, x, u);
		strutBuilder.rectangleStrut(graphic.group("strut-y-u"), Style.CUT, y, u);
		strutBuilder.rectangleStrut(graphic.group("strut-v-t"), Style.CUT, v, t);
		strutBuilder.triangleStrut(graphic.group("triang-w-y-v"), Style.CUT, w, y, v);
		strutBuilder.triangleStrut(graphic.group("triang-t-s-u"), Style.CUT, t, s, u);
	}
}
