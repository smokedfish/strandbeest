package org.rob.strandbeest.graphic;

import java.awt.Color;
import java.io.PrintWriter;

public class SvgGraphic implements Graphic {

	private final PrintWriter pw;
	private int id = 1;

	public SvgGraphic(PrintWriter pw) {
		this.pw = pw;
	}

	public void begin() {
		xmlBegin();
		svgBegin();
		namedView();
		gBegin();
	}

	@Override
	public void line(Color color, Point p2, Point p1) {
		pw.println("<path");
		pw.println("  style=\"fill:none;stroke:#000000;stroke-width:0.26458332px;stroke-linecap:butt;stroke-    linejoin:miter;stroke-opacity:1\"");
		pw.println("  d=\"M " + x(p1) + "," + y(p1) + " L " + x(p2) + "," + y(p2) + "\"");
		pw.println("  id=\"path" + id++ +"\"");
		pw.println("  inkscape:connector-curvature=\"0\" />");
	}

	@Override
	public void arc(Color colour, Point center, double radius, double arcStart, double arcLength) {
		//https://stackoverflow.com/questions/5736398/how-to-calculate-the-svg-path-for-an-arc-of-a-circle
		Point start = center.add(Point.polar(arcLength + arcStart, radius));
		Point end = center.add(Point.polar(arcStart, radius));
		int largeArc = arcLength <= Math.PI ? 0 : 1;
		int sweep = arcStart > arcStart + arcLength ? 0 : 1;
		pw.println("<path");
		pw.println("  style=\"fill:none;stroke:#000000;stroke-width:0.26458332px;stroke-linecap:butt;stroke-    linejoin:miter;stroke-opacity:1\"");
		pw.println("  d=\"M " + x(start) + "," + y(start) + " A " + radius + " " + radius + " 0 " + largeArc + " " + sweep + " " + x(end) + " " + y(end) + "\" />");
	}

	@Override
	public void circle(Color colour, Point p1, double r) {
		pw.println("<circle");
		pw.println("  style=\"fill:none;stroke:#000000;stroke-width:0.26458332px;stroke-linecap:butt;stroke-    linejoin:miter;stroke-opacity:1\"");
		pw.println("  cx=\"" + x(p1) + "\"");
		pw.println("  cy=\""+ y(p1) + "\"");
		pw.println("  r=\"" + r + "\" />");
	}

	@Override
	public void text(Color colour, Point p1, String text) {
		pw.println("<text");
		pw.println("  x=\"" + x(p1) + "\"");
		pw.println("  y=\""+ y(p1) + "\">" + text + "</text>");
	}

	public void end() {
		gEnd();
		svgEnd();
	}

	private double y(Point p1) {
		return -p1.getY();
	}

	private double x(Point p1) {
		return p1.getX();
	}

	private void xmlBegin() {
		pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
	}

	private void svgBegin() {
		pw.println("<svg");
		pw.println("  xmlns:dc=\"http://purl.org/dc/elements/1.1/\"");
		pw.println("  xmlns:cc=\"http://creativecommons.org/ns#\"");
		pw.println("  xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"");
		pw.println("  xmlns:svg=\"http://www.w3.org/2000/svg\"");
		pw.println("  xmlns=\"http://www.w3.org/2000/svg\"");
		pw.println("  xmlns:sodipodi=\"http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd\"");
		pw.println("  xmlns:inkscape=\"http://www.inkscape.org/namespaces/inkscape\"");
		pw.println("  width=\"210mm\"");
		pw.println("  height=\"297mm\"");
		pw.println("  viewBox=\"0 0 210 297\"");
		pw.println("  version=\"1.1\"");
		pw.println("  id=\"svg8\"");
		pw.println("  inkscape:version=\"0.92.1 r15371\"");
		pw.println("  sodipodi:docname=\"drawing.svg\">");
	}

	private void namedView() {
		pw.println("<sodipodi:namedview");
		pw.println("   id=\"base\"");
		pw.println("   pagecolor=\"#ffffff\"");
		pw.println("   bordercolor=\"#666666\"");
		pw.println("   borderopacity=\"1.0\"");
		pw.println("   inkscape:pageopacity=\"0.0\"");
		pw.println("   inkscape:pageshadow=\"2\"");
		pw.println("   inkscape:zoom=\"0.7\"");
		pw.println("   inkscape:cx=\"120\"");
		pw.println("   inkscape:cy=\"548.57143\"");
		pw.println("   inkscape:document-units=\"mm\"");
		pw.println("   inkscape:current-layer=\"layer1\"");
		pw.println("   showgrid=\"false\"");
		pw.println("   inkscape:window-width=\"1920\"");
		pw.println("   inkscape:window-height=\"1016\"");
		pw.println("   inkscape:window-x=\"0\"");
		pw.println("   inkscape:window-y=\"27\"");
		pw.println("   inkscape:window-maximized=\"1\" />");
	}

	private void gBegin() {
		pw.println("<g");
		pw.println(" inkscape:label=\"Layer 1\"");
		pw.println(" inkscape:groupmode=\"layer\"");
		pw.println(" id=\"layer1\">");
	}

	private void gEnd() {
		pw.println("</g>");
	}

	private void svgEnd() {
		pw.println("</svg>");
	}

}
