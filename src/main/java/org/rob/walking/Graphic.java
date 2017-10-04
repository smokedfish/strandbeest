package org.rob.walking;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JPanel;

public class Graphic extends JPanel {

	private static final long serialVersionUID = 1L;

	private final List<Consumer<Graphics2D>> shapes = new ArrayList<>();
	private final int width;
	private final int height;

	public Graphic(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void line(Point p1, Point p2) {
		shapes.add(g -> {
			g.setColor(Color.black);
			g.draw(new Line2D.Double(p1.getX(), -p1.getY(), p2.getX(), -p2.getY()));
		});
	}

	public void arc(final Color colour, Point p1, int r, double arcStart, double arcLength) {
		shapes.add(g -> {
			g.setColor(colour);
			g.draw(new Arc2D.Double(p1.getX() -r/2, -p1.getY() -r/2, r, r, Math.toDegrees(arcStart), Math.toDegrees(arcLength), Arc2D.OPEN));
		});
	}

	public void circle(final Color colour, Point p1, int r) {
		shapes.add(g -> {
			g.setColor(colour);
			g.draw(new Arc2D.Double(p1.getX() -r/2, -p1.getY() -r/2, r, r, 0, 360, Arc2D.OPEN));
		});
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setPaint(Color.black);
		g2.setStroke(new BasicStroke(1));
		g2.translate(width/2, height/2);
		for (Consumer<Graphics2D> shape : shapes) {
			shape.accept(g2);
		}
		g2.dispose();
	}

	public void clear() {
		shapes.clear();
	}
}
