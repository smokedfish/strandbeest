package org.rob.strandbeest.components;

import org.rob.strandbeest.graphic.Graphic;
import org.rob.strandbeest.graphic.Graphic.Style;
import org.rob.strandbeest.graphic.Point;

public class MotorPlate {
	// https://cdn.solarbotics.com/products/datasheets/solarbotics_gm2_metric.pdf

	private static final double HEIGHT = 22.7;
	private static final double WIDTH = 41.8;

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

	public void draw(Graphic graphic) {
		Point shaftPassThrough = new Point(0.0, 0.0);

		Point rhsLegPivot = shaftPassThrough.add(new Point(leg.getA(), -leg.getL()));
		graphic.circle(Style.CUT, rhsLegPivot, radius);

		Point lhsLegPivot = shaftPassThrough.add(new Point(-leg.getA(), -leg.getL()));
		graphic.circle(Style.CUT, lhsLegPivot, radius);

		new BoxBuilder().draw(graphic, Style.CUT, new Point(-SHAFT_DIA/2, SHAFT_DIA/2), new Point(SHAFT_DIA/2, -SHAFT_DIA/2));

		graphic.circle(Style.CUT, shaftPassThrough, SHAFT_PASSTHROUGH_DIA/2.0);

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
		Point[] points = new Point[] { shaftPassThrough, rhsLegPivot, lhsLegPivot, mount1, mount2, lug, bodyTopLeft, bodyBottomRight };

		Point bottomLeft = min(points);
		Point topRight = max(points);
		new BoxBuilder().draw(graphic, Style.CUT, bottomLeft.minus(t), topRight.add(t));

		new JoinBuilder()
			.mortises(graphic, bottomLeft)
			.horizReflect()
			.mortises(graphic, new Point(topRight.getX(), bottomLeft.getY()))
			.vertReflect()
			.mortises(graphic, topRight)
			.horizReflect()
			.mortises(graphic, new Point(bottomLeft.getX(), topRight.getY()));
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
}
