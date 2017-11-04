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

	public JoinBuilder tenons(Graphic graphic, Point p) {
		graphic = graphic.group("tenons");

		Point mt = xxx(0, materialThickness);
		Point ls = xxx((lugWidth - boltWidth)/2.0, 0);
		Point cl = xxx(0, (boltHeight - materialThickness - nutHeight)/2.0);
		Point ns = xxx((nutWidth - boltWidth)/2.0, 0);
		Point lw = xxx(lugWidth, 0);
		Point nh = xxx(0, nutHeight);
		Point bw = xxx(boltWidth, 0);

		p = cut(graphic, p, p.minus(mt));
		p = cut(graphic, p, p.add(lw));
		p = cut(graphic, p, p.add(mt));
		p = cut(graphic, p, p.add(ls));
		p = cut(graphic, p, p.add(cl));
		p = cut(graphic, p, p.minus(ns));
		p = cut(graphic, p, p.add(nh));
		p = cut(graphic, p, p.add(ns));
		p = cut(graphic, p, p.add(cl));
		p = cut(graphic, p, p.add(bw));
		p = cut(graphic, p, p.minus(cl));
		p = cut(graphic, p, p.add(ns));
		p = cut(graphic, p, p.minus(nh));
		p = cut(graphic, p, p.minus(ns));
		p = cut(graphic, p, p.minus(cl));
		p = cut(graphic, p, p.add(ls));
		p = cut(graphic, p, p.minus(mt));
		p = cut(graphic, p, p.add(lw));
		p = cut(graphic, p, p.add(mt));
		return this;
	}

	private Point xxx(double x, double y) {
		return new Point(horiz(x), vert(y));
	}

	public JoinBuilder mortises(Graphic graphic, Point p) {
		graphic = graphic.group("mortises");
		Point boxSize = new Point(horiz(lugWidth), vert(-materialThickness));
		Point boxSpacing = new Point(horiz(lugWidth * 2.0), 0);

		new BoxBuilder().draw(graphic, Style.CUT, p, p.add(boxSize));
		new BoxBuilder().draw(graphic, Style.CUT, p.add(boxSpacing), p.add(boxSize).add(boxSpacing));
		graphic.circle(Style.CUT, p.add(new Point(horiz(lugWidth * 1.5), vert(-materialThickness/2.0))), boltWidth/2.0);
		return this;
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

	private Point cut(Graphic graphic, Point p1, Point p2) {
		graphic.line(Style.CUT, p1, p2);
		return p2;
	}
}
