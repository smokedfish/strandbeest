package org.rob.strandbeest.graphic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JPanel;

public class JavaGraphic extends JPanel implements Graphic {
	private static final long serialVersionUID = 1L;
	private static final double MM_PER_INCH = 25.4;

	private final List<Consumer<Graphics2D>> shapes = new ArrayList<>();
	private final double scale;
	private final Dimension dimension;

	public JavaGraphic(int width, int height) {
		this.scale = java.awt.Toolkit.getDefaultToolkit().getScreenResolution() / MM_PER_INCH;
		this.dimension = new Dimension(1000,1000);
	}

	@Override
	public void line(Color color, Point p1, Point p2) {
		shapes.add(g -> {
			g.setColor(color);
			g.draw(new Line2D.Double(p1.getX(), -p1.getY(), p2.getX(), -p2.getY()));
		});
	}

	@Override
	public void arc(Color colour, Point center, double radius, double arcStart, double arcLength) {
		shapes.add(g -> {
			g.setColor(colour);
			g.draw(new Arc2D.Double(center.getX()-radius, -center.getY()-radius, radius*2, radius*2, Math.toDegrees(arcStart), Math.toDegrees(arcLength), Arc2D.OPEN));
		});
	}

	@Override
	public void circle(Color colour, Point p1, double radius) {
		shapes.add(g -> {
			g.setColor(colour);
			g.draw(new Arc2D.Double(p1.getX()-radius, -p1.getY()-radius, radius*2, radius*2, 0, 360, Arc2D.OPEN));
		});
	}

	@Override
	public void text(Color colour, Point p1, String text) {
		shapes.add(g -> {
			g.setColor(colour);
			g.drawString(text, (float)p1.getX(), -(float)p1.getY());
		});
	}

	public void paintComponent(Graphics g) {
	    super.paintComponent(g);

		AffineTransform at = new AffineTransform();
		at.translate(this.getWidth()/2, this.getHeight()/2);
		at.scale(scale, scale);

		Graphics2D g2 = (Graphics2D) g.create();
		g2.setPaint(Color.black);
		g2.setStroke(new BasicStroke((float) 0.1));
		g2.setTransform(at);

		for (Consumer<Graphics2D> shape : shapes) {
			shape.accept(g2);
		}
		g2.dispose();
	}

	@Override
	public Dimension getPreferredSize() {
		return dimension;
	}

	public void clear() {
		shapes.clear();
	}
}
