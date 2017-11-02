package org.rob.strandbeest.graphic.svg;

import java.awt.Color;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.rob.strandbeest.graphic.Graphic;
import org.rob.strandbeest.graphic.Point;

public class SvgGraphic implements Graphic {
	private final String id;
	private final List<Consumer<PrintWriter>> shapes = new ArrayList<>();
	private final List<SvgGraphic> children = new ArrayList<>();

	public SvgGraphic(String id) {
		this.id = id;
	}

	@Override
	public void line(Color color, Point p2, Point p1) {
		shapes.add(pw -> {
			pw.println("<path");
			pw.println("  style=\"fill:none;stroke:#000000;stroke-width:0.26458332px;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1\"");
			pw.println("  d=\"M " + x(p1) + "," + y(p1) + " L " + x(p2) + "," + y(p2) + "\"");
			pw.println("  inkscape:connector-curvature=\"0\" />");
		});
	}

	@Override
	public void arc(Color colour, Point center, double radius, double arcStart, double arcLength) {
		shapes.add(pw -> {
			//https://stackoverflow.com/questions/5736398/how-to-calculate-the-svg-path-for-an-arc-of-a-circle
			Point start = center.add(Point.polar(arcLength + arcStart, radius));
			Point end = center.add(Point.polar(arcStart, radius));
			int largeArc = arcLength <= Math.PI ? 0 : 1;
			int sweep = arcStart > arcStart + arcLength ? 0 : 1;
			pw.println("<path");
			pw.println("  style=\"fill:none;stroke:#000000;stroke-width:0.26458332px;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1\"");
			pw.println("  d=\"M " + x(start) + "," + y(start) + " A " + radius + " " + radius + " 0 " + largeArc + " " + sweep + " " + x(end) + " " + y(end) + "\" />");
		});
	}

	@Override
	public void circle(Color colour, Point p1, double r) {
		shapes.add(pw -> {
			pw.println("<circle");
			pw.println("  style=\"fill:none;stroke:#000000;stroke-width:0.26458332px;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1\"");
			pw.println("  cx=\"" + x(p1) + "\"");
			pw.println("  cy=\""+ y(p1) + "\"");
			pw.println("  r=\"" + r + "\" />");
		});
	}

	@Override
	public void text(Color colour, Point p1, String text) {
		shapes.add(pw -> {
			pw.println("<text");
			pw.println("  x=\"" + x(p1) + "\"");
			pw.println("  y=\""+ y(p1) + "\">" + text + "</text>");
		});
	}

	public void draw(PrintWriter pw) {
		pw.println("<g");
		pw.println(" id=\""+ id + "\">");
		shapes.forEach(shape -> shape.accept(pw));
		children.forEach(graphic -> {
			graphic.draw(pw);
		});
		pw.println("</g>");
	}

	@Override
	public Graphic group(String id) {
		SvgGraphic graphic = new SvgGraphic(id);
		children.add(graphic);
		return graphic;
	}

	private double y(Point p1) {
		return -p1.getY();
	}

	private double x(Point p1) {
		return p1.getX();
	}
}
