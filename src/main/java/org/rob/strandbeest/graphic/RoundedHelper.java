package org.rob.strandbeest.graphic;

import java.awt.Color;

public class RoundedHelper {
	private final Graphic graphic;
	private final double thickness;

	public RoundedHelper(Graphic graphic, double thickness) {
		this.graphic = graphic;
		this.thickness = thickness;
	}

	public void roundedTriangle(Color color, Point p1, Point p2, Point p3) {
		Point sum = p1.add(p2.add(p3));
		Point centroid = new Point(sum.getX()/3, sum.getY()/3);

		Point a12 = Point.offset(centroid, p1, p2, thickness);
		Point a23 = Point.offset(centroid, p2, p3, thickness);
		Point a13 = Point.offset(centroid, p3, p1, thickness);

		Point p1a12 = p1.add(a12);
		Point p2a12 = p2.add(a12);
		Point p2a23 = p2.add(a23);
		Point p3a23 = p3.add(a23);
		Point p1a13 = p1.add(a13);
		Point p3a13 = p3.add(a13);

		roundedCorner(color, p1, p1a13, p1a12);
		graphic.line(color, p1a12, p2a12);
		roundedCorner(color, p2, p2a12, p2a23);
		graphic.line(color, p2a23, p3a23);
		roundedCorner(color, p3, p3a23, p3a13);
		graphic.line(color, p3a13, p1a13);
	}

	public void roundedCorner(Color color, Point center, Point start, Point end) {
		double startAngle = start.minus(center).angle();
		double endAngle = end.minus(center).angle();
		if ((endAngle-startAngle) > Math.PI) {
			graphic.arc(color, center, thickness*2, startAngle, endAngle-startAngle - Math.PI*2);
		} else if ((endAngle-startAngle) < -Math.PI) {
			graphic.arc(color, center, thickness*2, startAngle, endAngle-startAngle + Math.PI*2);
		} else {
			graphic.arc(color, center, thickness*2, startAngle, endAngle-startAngle);
		}
	}

	public void roundedRectangle(Color color, Point p1, Point p2) {
		Point delta = p1.minus(p2);
		double ang12 = Math.atan2(delta.getY(), delta.getX());
		Point a = Point.polar(ang12 + Math.PI/2, thickness);
		Point b = Point.polar(ang12 - Math.PI/2, thickness);

		graphic.line(color, p1.add(b), p2.add(b));
		graphic.line(color, p2.add(a), p1.add(a));
		graphic.arc(color, p1, 2 * thickness, ang12 - Math.PI/2, Math.PI);
		graphic.arc(color, p2, 2 * thickness, ang12 + Math.PI/2, Math.PI);
	}
}
