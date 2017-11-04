package org.rob.strandbeest.graphic.svg;

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
	public void line(Style style, Point p2, Point p1) {
		shapes.add(pw -> {
			pw.println("<path");
			pw.println("  style=\"" + style(style) + "\"");
			pw.println("  d=\"M " + p1.getX() + "," + -p1.getY() + " L " + p2.getX() + "," + -p2.getY() + "\"");
			pw.println("  inkscape:connector-curvature=\"0\" />");
		});
	}

	@Override
	public void arc(Style style, Point center, double radius, double arcStart, double arcLength) {
		shapes.add(pw -> {
			//https://stackoverflow.com/questions/5736398/how-to-calculate-the-svg-path-for-an-arc-of-a-circle
			Point start = center.add(Point.polar(arcLength + arcStart, radius));
			Point end = center.add(Point.polar(arcStart, radius));
			int largeArc = arcLength <= Math.PI ? 0 : 1;
			int sweep = arcStart > arcStart + arcLength ? 0 : 1;
			pw.println("<path");
			pw.println("  style=\"" + style(style) + "\"");
			pw.println("  d=\"M " + start.getX() + "," + -start.getY() + " A " + radius + " " + radius + " 0 " + largeArc + " " + sweep + " " + end.getX() + " " + -end.getY() + "\" />");
		});
	}

	@Override
	public void circle(Style style, Point p1, double r) {
		shapes.add(pw -> {
			pw.println("<circle");
			pw.println("  style=\"" + style(style) + "\"");
			pw.println("  cx=\"" + p1.getX() + "\"");
			pw.println("  cy=\""+ -p1.getY() + "\"");
			pw.println("  r=\"" + r + "\" />");
		});
	}

	@Override
	public void text(Style style, Point p1, String text) {
		shapes.add(pw -> {
			pw.println("<text");
			pw.println("  x=\"" + p1.getX() + "\"");
			pw.println("  y=\""+ -p1.getY() + "\">" + text + "</text>");
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

	private static String style(Style style) {
		switch (style) {
			case CUT:
				return "fill:none;stroke:#0000ff;stroke-width:0.1;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1";
			case ENGRAVE_HEAVY:
				return "fill:none;stroke:#ff0000;stroke-width:0.1;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1";
			case ENGRAVE_MEDIUM:
				return "fill:none;stroke:#00ff00;stroke-width:0.1;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1";
			case ENGRAVE_LIGHT:
				return "fill:none;stroke:#ff00ff;stroke-width:0.1;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1";
			case IGNORE:
			default:
				return "fill:none;stroke:#ffaf00;stroke-width:0.1;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1";
		}
	}
}
