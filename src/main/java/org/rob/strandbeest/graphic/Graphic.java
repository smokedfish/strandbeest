package org.rob.strandbeest.graphic;

public interface Graphic {
	public enum Style { IGNORE, CUT, ENGRAVE_HEAVY, ENGRAVE_MEDIUM, ENGRAVE_LIGHT };
	void line(Style style, Point p1, Point p2);
	void arc(Style style, Point p1, double r, double arcStart, double arcLength);
	void circle(Style style, Point p1, double r);
	void text(Style style, Point p1, String text);
	Graphic group(String id);
}