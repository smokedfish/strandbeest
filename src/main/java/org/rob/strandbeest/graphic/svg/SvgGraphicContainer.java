package org.rob.strandbeest.graphic.svg;

import java.io.PrintWriter;

import org.rob.strandbeest.graphic.Graphic;
import org.rob.strandbeest.graphic.GraphicContainer;

public class SvgGraphicContainer implements GraphicContainer {

	private final PrintWriter pw;
	private final SvgGraphic defaultGraphic;

	public SvgGraphicContainer(PrintWriter pw) {
		this.pw = pw;
		this.defaultGraphic = new SvgGraphic("default");
	}

	public void draw() {
		xmlBegin();
		svgBegin();
		namedView();
		gBegin();
		defaultGraphic.draw(pw);
		gEnd();
		svgEnd();
	}

	@Override
	public Graphic defaultGraphic() {
		return defaultGraphic;
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
