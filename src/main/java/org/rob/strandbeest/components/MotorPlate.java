package org.rob.strandbeest.components;

import org.rob.strandbeest.graphic.Graphic;
import org.rob.strandbeest.graphic.Graphic.Style;
import org.rob.strandbeest.graphic.Point;

public class MotorPlate {
	// https://cdn.solarbotics.com/products/datasheets/solarbotics_gm2_metric.pdf

	private static final double HEIGHT = 22.7;
	private static final double WIDTH = 41.8;
	private static final double DEPTH = 50.0;

	private static final double SHAFT_PASSTHROUGH_DIA = 8.0;
	private static final double SHAFT_DIA = 4.73;
	private static final double SHAFT_H_OFFSET = 30.76;

	private static final double LUG_DIA = 3.9;
	private static final double LUG_H_OFFSET = 19.51 - SHAFT_H_OFFSET;

	private static final double MOUNT_DIA = 2.5;
	private static final double MOUNT_H_OFFSET = 39.05 - SHAFT_H_OFFSET;
	private static final double MOUNT_V_OFFSET = 17.2/2.0;

	private final Leg leg;
	private final double thickness;
	private final double radius;

	public MotorPlate(Leg leg, double thickness, double radius) {
		this.leg = leg;
		this.thickness = thickness;
		this.radius = radius;
	}

	public MotorPlate arial(Graphic graphic, Point p) {
		Point d = new Point(0, DEPTH);
		Point s = new Point(4.0, 0);

		JoinBuilder joinBuilder = new JoinBuilder();

		p = joinBuilder.vertReflect().tenons(graphic, p);
		p = line(graphic, p, p.add(s));
		p = line(graphic, p, p.minus(d));
		p = line(graphic, p, p.minus(s));
		p = joinBuilder.vertReflect().horizReflect().tenons(graphic, p);
		p = line(graphic, p, p.minus(s));
		p = line(graphic, p, p.add(d));
		p = line(graphic, p, p.add(s));

		return this;
	}

	public MotorPlate side(Graphic graphic) {
		Point shaftCentre = new Point(0.0, 0.0);

		Point rhsLegPivot = shaftCentre.add(new Point(leg.getA(), -leg.getL()));
		graphic.circle(Style.CUT, rhsLegPivot, radius);

		Point lhsLegPivot = shaftCentre.add(new Point(-leg.getA(), -leg.getL()));
		graphic.circle(Style.CUT, lhsLegPivot, radius);

		new BoxBuilder().draw(graphic, Style.CUT, new Point(-SHAFT_DIA/2, SHAFT_DIA/2), new Point(SHAFT_DIA/2, -SHAFT_DIA/2));

		graphic.circle(Style.CUT, shaftCentre, SHAFT_PASSTHROUGH_DIA/2.0);

		Point mount1 = new Point(MOUNT_H_OFFSET, MOUNT_V_OFFSET);
		graphic.circle(Style.CUT, mount1, MOUNT_DIA/2.0);

		Point mount2 = new Point(MOUNT_H_OFFSET, -MOUNT_V_OFFSET);
		graphic.circle(Style.CUT, mount2, MOUNT_DIA/2.0);

		Point lug = new Point(LUG_H_OFFSET, 0);
		graphic.circle(Style.CUT, lug, LUG_DIA/2.0);

		Point bodyTopLeft = new Point(-SHAFT_H_OFFSET, HEIGHT/2.0);
		Point bodyBottomRight = new Point(WIDTH - SHAFT_H_OFFSET, -HEIGHT/2.0);
		new BoxBuilder().draw(graphic, Style.IGNORE, bodyTopLeft, bodyBottomRight);

		Point t = new Point(thickness, thickness);
		Point[] points = new Point[] { shaftCentre, rhsLegPivot, lhsLegPivot, mount1, mount2, lug, bodyTopLeft, bodyBottomRight };

		Point bottomLeft = min(points);
		Point topRight = max(points);
		new BoxBuilder().draw(graphic, Style.CUT, bottomLeft.minus(t), topRight.add(t));

		JoinBuilder joinBuilder = new JoinBuilder();
		joinBuilder.mortises(graphic, bottomLeft);
		joinBuilder.horizReflect().mortises(graphic, new Point(topRight.getX(), bottomLeft.getY()));
		joinBuilder.vertReflect().mortises(graphic, topRight);
		joinBuilder.horizReflect().mortises(graphic, new Point(bottomLeft.getX(), topRight.getY()));

		return this;
	}

	private Point min(Point... points) {
		Point retVal = new Point(Double.MAX_VALUE, Double.MAX_VALUE);
		for (Point point : points) {
			retVal = new Point(Math.min(retVal.getX(), point.getX()), Math.min(retVal.getY(), point.getY()));
		}
		return retVal;
	}

	private Point max(Point... points) {
		Point retVal = new Point(Double.MIN_VALUE, Double.MIN_VALUE);
		for (Point point : points) {
			retVal = new Point(Math.max(retVal.getX(), point.getX()), Math.max(retVal.getY(), point.getY()));
		}
		return retVal;
	}

	private Point line(Graphic graphic, Point p1, Point p2) {
		graphic.line(Style.CUT, p1, p2);
		return p2;
	}
}
