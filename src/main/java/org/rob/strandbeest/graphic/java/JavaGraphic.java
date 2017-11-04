package org.rob.strandbeest.graphic.java;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.rob.strandbeest.graphic.Graphic;
import org.rob.strandbeest.graphic.Point;

public class JavaGraphic implements Graphic {
	private final List<Consumer<Graphics2D>> shapes = new ArrayList<>();
	private final List<JavaGraphic> children = new ArrayList<>();
	private final String id;

	JavaGraphic(String id) {
		this.id = id;
	}

	@Override
	public void line(Style style, Point p1, Point p2) {
		shapes.add(g -> {
			g.setColor(color(style));
			g.draw(new Line2D.Double(p1.getX(), -p1.getY(), p2.getX(), -p2.getY()));
		});
	}

	@Override
	public void arc(Style style, Point center, double radius, double arcStart, double arcLength) {
		shapes.add(g -> {
			g.setColor(color(style));
			g.draw(new Arc2D.Double(center.getX()-radius, -center.getY()-radius, radius*2, radius*2, Math.toDegrees(arcStart), Math.toDegrees(arcLength), Arc2D.OPEN));
		});
	}

	@Override
	public void circle(Style style, Point p1, double radius) {
		shapes.add(g -> {
			g.setColor(color(style));
			g.draw(new Arc2D.Double(p1.getX()-radius, -p1.getY()-radius, radius*2, radius*2, 0, 360, Arc2D.OPEN));
		});
	}

	@Override
	public void text(Style style, Point p1, String text) {
		shapes.add(g -> {
			g.setColor(color(style));
			g.drawString(text, (float)p1.getX(), -(float)p1.getY());
		});
	}


	@Override
	public Graphic group(String id) {
		JavaGraphic graphic = new JavaGraphic(id);
		children.add(graphic);
		return graphic;
	}

	public void draw(Graphics2D g2) {
		shapes.forEach(shape -> shape.accept(g2));
		children.forEach(graphic -> graphic.draw(g2));
	}

	public void clear() {
		shapes.clear();
		children.forEach(child -> child.clear());
	}

	private static Color color(Style style) {
		switch (style) {
			case CUT:
				return Color.blue;
			case ENGRAVE_HEAVY:
				return Color.magenta;
			case ENGRAVE_MEDIUM:
				return Color.green;
			case ENGRAVE_LIGHT:
				return Color.green;
			case IGNORE:
			default:
				return Color.orange;
		}
	}
}
