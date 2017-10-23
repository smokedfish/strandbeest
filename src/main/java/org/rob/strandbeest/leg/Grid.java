package org.rob.strandbeest.leg;

import java.awt.Color;

import org.rob.strandbeest.graphic.Graphic;
import org.rob.strandbeest.graphic.Point;

public class Grid {
	private static final Color color = Color.red;
	private final int delta = 5;
	private final int height;
	private final int width;

	public Grid(int height, int width) {
		this.height = height;
		this.width = width;
	}

	public void draw(Graphic graphic) {
		graphic.line(color, new Point(0,0), new Point(width, 0));
		graphic.line(color, new Point(0,0), new Point(0, height));
		graphic.text(color, new Point(-delta, -delta), "0,0");
		graphic.text(color, new Point(width - delta, -delta), "0," + height);
		graphic.text(color, new Point(-delta, height - delta), width + ",0");
	}
}
