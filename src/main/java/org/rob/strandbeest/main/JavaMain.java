package org.rob.strandbeest.main;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.rob.strandbeest.graphic.Graphic;
import org.rob.strandbeest.graphic.java.JavaGraphicContainer;
import org.rob.strandbeest.leg.Grid;
import org.rob.strandbeest.leg.Leg;
import org.rob.strandbeest.leg.LegRender;

public class JavaMain {
	private static int FRAME_WIDTH = 700;
	private static int FRAME_HEIGHT = 700;

	public static void main(String[] args) throws FileNotFoundException {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JavaGraphicContainer javaGraphic = new JavaGraphicContainer(FRAME_WIDTH, FRAME_HEIGHT);

				JScrollPane scrollpane = new JScrollPane(javaGraphic, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scrollpane.getViewport().setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

				JFrame frame = new JFrame("Testing");
				frame.getContentPane().add(scrollpane);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);

				draw(javaGraphic.defaultGraphic());
			}
		});
	}

	private static void draw(Graphic graphic) {
		new Grid(100, 100).draw(graphic.group("grid"));
		new LegRender(Leg.THEO_JANSEN, 2, 10, 5).render(graphic.group("legs"), 0);
	}
}
