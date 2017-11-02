package org.rob.strandbeest.graphic;

import java.awt.Color;

public interface Graphic {
	void line(Color color, Point p1, Point p2);
	void arc(Color colour, Point p1, double r, double arcStart, double arcLength);
	void circle(Color colour, Point p1, double r);
	void text(Color colour, Point p1, String text);
	Graphic group(String id);
}