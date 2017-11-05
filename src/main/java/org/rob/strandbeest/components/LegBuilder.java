package org.rob.strandbeest.components;

import org.rob.strandbeest.graphic.Graphic;
import org.rob.strandbeest.graphic.Graphic.Style;
import org.rob.strandbeest.graphic.Point;

public class LegBuilder {
	private final Leg leg;
	private final StrutBuilder strutBuilder;
	private double horiz = 1.0;

	public LegBuilder(Leg leg, double thickness, double radius) {
		this.leg = leg;
		this.strutBuilder = new StrutBuilder(thickness, radius);
	}

	public LegBuilder draw(Graphic graphic, double ang) {
		Point z = new Point(0,0);
		Point x = z.add(Point.polar(ang, leg.getM()));

		Point y1 = z.add(new Point(horiz(leg.getA()), -leg.getL()));
		Point w1 = lawOfCosines(x, y1, leg.getJ(), leg.getB());
		Point v1 = lawOfCosines(w1, y1, leg.getE(), leg.getD());
		Point u1 = lawOfCosines(y1, x, leg.getC(), leg.getK());
		Point t1 = lawOfCosines(v1, u1, leg.getF(), leg.getG());
		Point s1 = lawOfCosines(t1, u1, leg.getH(), leg.getI());

		strutBuilder.rectangleStrut(graphic.group("strut-z-x"), Style.CUT, z, x);
		strutBuilder.rectangleStrut(graphic.group("strut-x-w"), Style.CUT, x, w1);
		strutBuilder.rectangleStrut(graphic.group("strut-x-u"), Style.CUT, x, u1);
		strutBuilder.rectangleStrut(graphic.group("strut-y-u"), Style.CUT, y1, u1);
		strutBuilder.rectangleStrut(graphic.group("strut-v-t"), Style.CUT, v1, t1);
		strutBuilder.triangleStrut(graphic.group("triang-w-y-v"), Style.CUT, w1, y1, v1);
		strutBuilder.triangleStrut(graphic.group("triang-t-s-u"), Style.CUT, t1, s1, u1);

		return this;
	}

	private Point lawOfCosines(Point p1, Point p2, double l1, double l2) {
		return horiz > 0 ? Point.lawOfCosines(p1, p2, l1, l2) : Point.lawOfCosines(p2, p1, l2, l1);
	}

	public LegBuilder horizReflect() {
		this.horiz *= -1.0;
		return this;
	}

	private double horiz(double x) {
		return x * horiz;
	}

}
