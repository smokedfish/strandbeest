package org.rob.strandbeest.graphic.java;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import org.rob.strandbeest.graphic.Graphic;
import org.rob.strandbeest.graphic.GraphicContainer;

public class JavaGraphicContainer extends JPanel implements GraphicContainer {
	private static final long serialVersionUID = 1L;
	private static final double MM_PER_INCH = 25.4;

	private final double scale;
	private final Dimension dimension;
	private final JavaGraphic defaultGraphic;

	public JavaGraphicContainer(int width, int height) {
		this.scale = java.awt.Toolkit.getDefaultToolkit().getScreenResolution() / MM_PER_INCH;
		this.dimension = new Dimension(1000,1000);
		this.defaultGraphic = new JavaGraphic("default");
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

		defaultGraphic.draw(g2);
		g2.dispose();
	}

	@Override
	public Dimension getPreferredSize() {
		return dimension;
	}

	public void clear() {
		defaultGraphic.clear();
	}

	@Override
	public Graphic defaultGraphic() {
		return defaultGraphic;
	}
}
