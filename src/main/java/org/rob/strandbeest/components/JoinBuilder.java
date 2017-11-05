package org.rob.strandbeest.components;

import org.rob.strandbeest.graphic.Graphic;
import org.rob.strandbeest.graphic.Graphic.Style;
import org.rob.strandbeest.graphic.Point;

public class JoinBuilder {
	private final double boltWidth = 3.0;
	private final double boltHeight = 17.0;
	private final double nutWidth = 5.5;
	private final double nutHeight = 2.5;
	private final double materialThickness = 3.0;
	private final double lugWidth = 8.0;
	private double horiz = 1.0;
	private double vert = 1.0;

	public JoinBuilder() {
	}

	public Point tenons(Graphic graphic, Point p) {
		graphic = graphic.group("tenons");

		Point mt = new Point(horiz((double) 0), vert(materialThickness));
		Point ls = new Point(horiz((lugWidth - boltWidth)/2.0), vert((double) 0));
		Point cl = new Point(horiz((double) 0), vert((boltHeight - materialThickness - nutHeight)/2.0));
		Point ns = new Point(horiz((nutWidth - boltWidth)/2.0), vert((double) 0));
		Point lw = new Point(horiz(lugWidth), vert((double) 0));
		Point nh = new Point(horiz((double) 0), vert(nutHeight));
		Point bw = new Point(horiz(boltWidth), vert((double) 0));

		p = line(graphic, p, p.minus(mt));
		p = line(graphic, p, p.add(lw));
		p = line(graphic, p, p.add(mt));
		p = line(graphic, p, p.add(ls));
		p = line(graphic, p, p.add(cl));
		p = line(graphic, p, p.minus(ns));
		p = line(graphic, p, p.add(nh));
		p = line(graphic, p, p.add(ns));
		p = line(graphic, p, p.add(cl));
		p = line(graphic, p, p.add(bw));
		p = line(graphic, p, p.minus(cl));
		p = line(graphic, p, p.add(ns));
		p = line(graphic, p, p.minus(nh));
		p = line(graphic, p, p.minus(ns));
		p = line(graphic, p, p.minus(cl));
		p = line(graphic, p, p.add(ls));
		p = line(graphic, p, p.minus(mt));
		p = line(graphic, p, p.add(lw));
		p = line(graphic, p, p.add(mt));

		return p;
	}

	public void mortises(Graphic graphic, Point p) {
		graphic = graphic.group("mortises");
		Point boxSize = new Point(horiz(lugWidth), vert(-materialThickness));
		Point boxSpacing = new Point(horiz(lugWidth * 2.0), 0);

		new BoxBuilder().draw(graphic, Style.CUT, p, p.add(boxSize));
		new BoxBuilder().draw(graphic, Style.CUT, p.add(boxSpacing), p.add(boxSize).add(boxSpacing));
		graphic.circle(Style.CUT, p.add(new Point(horiz(lugWidth * 1.5), vert(-materialThickness/2.0))), boltWidth/2.0);
	}

	public JoinBuilder horizReflect() {
		this.horiz *= -1.0;
		return this;
	}

	public JoinBuilder vertReflect() {
		this.vert *= -1.0;
		return this;
	}

	private double horiz(double x) {
		return x * horiz;
	}

	private double vert(double y) {
		return y * vert;
	}

	private Point line(Graphic graphic, Point p1, Point p2) {
		graphic.line(Style.CUT, p1, p2);
		return p2;
	}
}
