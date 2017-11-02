package org.rob.strandbeest.main;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.rob.strandbeest.graphic.Graphic;
import org.rob.strandbeest.graphic.JavaGraphic;
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
				JavaGraphic javaGraphic = new JavaGraphic(FRAME_WIDTH, FRAME_HEIGHT);

				JScrollPane scrollpane = new JScrollPane(javaGraphic, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scrollpane.getViewport().setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

				JFrame frame = new JFrame("Testing");
				frame.getContentPane().add(scrollpane);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);

				draw(javaGraphic);
			}
		});
	}

	private static void draw(Graphic graphic) {
		new Grid(100, 100).draw(graphic);
		new LegRender(Leg.THEO_JANSEN, 2, 10, 5).render(graphic, 0);
	}
}
