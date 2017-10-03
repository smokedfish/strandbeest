package org.rob.walking;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JPanel;

public class Graphic extends JPanel {

	private static final long serialVersionUID = 1L;

	private final List<Consumer<Graphics>> shapes = new ArrayList<>();
	private final int width;
	private final int height;

	public Graphic(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void line(Point p1, Point p2) {
		shapes.add(g -> {
			g.setColor(Color.black);
			g.drawLine(
					(int)p1.getX(), -(int)p1.getY(),
					(int)p2.getX(), -(int)p2.getY());
		});
	}

	public void point(Point p1) {
		shapes.add(g -> {
			int r = 6;
			g.setColor(Color.red);
			g.drawArc((int)p1.getX() -r/2, -(int)p1.getY() -r/2, r, r, 0, 360);
		});
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setPaint(Color.black);
		g2.setStroke(new BasicStroke(1));
		g2.translate(width/2, height/2);
		for (Consumer<Graphics> shape : shapes) {
			shape.accept(g2);
		}
		g2.dispose();
	}

	public void clear() {
		shapes.clear();
	}
}
