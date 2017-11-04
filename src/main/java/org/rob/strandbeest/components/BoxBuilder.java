package org.rob.strandbeest.components;

import org.rob.strandbeest.graphic.Graphic;
import org.rob.strandbeest.graphic.Graphic.Style;
import org.rob.strandbeest.graphic.Point;

public class BoxBuilder {

	public void draw(Graphic graphic, Style style, Point corner1, Point corner3) {
		Point corner2 = new Point(corner1.getX(), corner3.getY());
		Point corner4 = new Point(corner3.getX(), corner1.getY());

		graphic.line(style, corner1, corner2);
		graphic.line(style, corner2, corner3);
		graphic.line(style, corner3, corner4);
		graphic.line(style, corner4, corner1);
	}
}
