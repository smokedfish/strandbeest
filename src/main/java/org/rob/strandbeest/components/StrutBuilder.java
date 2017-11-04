package org.rob.strandbeest.components;

import org.rob.strandbeest.graphic.Graphic;
import org.rob.strandbeest.graphic.Point;
import org.rob.strandbeest.graphic.Graphic.Style;

public class StrutBuilder {
	private final double strutThickness;
	private final double radius;

	public StrutBuilder(double thickness, double holeRadius) {
		this.strutThickness = thickness;
		this.radius = holeRadius;
	}

	public void triangleStrut(Graphic graphic, Style style, Point p1, Point p2, Point p3) {
		Point sum = p1.add(p2.add(p3));
		Point centroid = new Point(sum.getX()/3, sum.getY()/3);

		graphic.circle(style, p1, radius);
		graphic.circle(style, p2, radius);
		graphic.circle(style, p3, radius);

		Point a12 = Point.offset(centroid, p1, p2, strutThickness);
		Point a23 = Point.offset(centroid, p2, p3, strutThickness);
		Point a13 = Point.offset(centroid, p3, p1, strutThickness);

		Point p1a12 = p1.add(a12);
		Point p2a12 = p2.add(a12);
		Point p2a23 = p2.add(a23);
		Point p3a23 = p3.add(a23);
		Point p1a13 = p1.add(a13);
		Point p3a13 = p3.add(a13);

		roundedCorner(graphic, style, p1, p1a13, p1a12);
		graphic.line(style, p1a12, p2a12);
		roundedCorner(graphic, style, p2, p2a12, p2a23);
		graphic.line(style, p2a23, p3a23);
		roundedCorner(graphic, style, p3, p3a23, p3a13);
		graphic.line(style, p3a13, p1a13);
	}

	public void rectangleStrut(Graphic graphic, Style style, Point p1, Point p2) {
		Point delta = p1.minus(p2);
		double ang12 = Math.atan2(delta.getY(), delta.getX());
		Point a = Point.polar(ang12 + Math.PI/2, strutThickness);
		Point b = Point.polar(ang12 - Math.PI/2, strutThickness);

		graphic.circle(Style.CUT, p1, radius);
		graphic.circle(Style.CUT, p2, radius);

		graphic.line(style, p1.add(b), p2.add(b));
		graphic.line(style, p2.add(a), p1.add(a));
		graphic.arc(style, p1, strutThickness, ang12 - Math.PI/2, Math.PI);
		graphic.arc(style, p2, strutThickness, ang12 + Math.PI/2, Math.PI);
	}

	private void roundedCorner(Graphic graphic, Style style, Point center, Point start, Point end) {
		double startAngle = start.minus(center).angle();
		double endAngle = end.minus(center).angle();
		if ((endAngle-startAngle) > Math.PI) {
			graphic.arc(style, center, strutThickness, startAngle, endAngle-startAngle - Math.PI*2);
		} else if ((endAngle-startAngle) < -Math.PI) {
			graphic.arc(style, center, strutThickness, startAngle, endAngle-startAngle + Math.PI*2);
		} else {
			graphic.arc(style, center, strutThickness, startAngle, endAngle-startAngle);
		}
	}
}
