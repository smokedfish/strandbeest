package org.rob.strandbeest.main;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.rob.strandbeest.components.JoinBuilder;
import org.rob.strandbeest.components.Leg;
import org.rob.strandbeest.components.LegBuilder;
import org.rob.strandbeest.components.MotorPlate;
import org.rob.strandbeest.graphic.Graphic;
import org.rob.strandbeest.graphic.Graphic.Style;
import org.rob.strandbeest.graphic.Point;
import org.rob.strandbeest.graphic.java.JavaGraphicContainer;

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
		new JoinBuilder().tenons(graphic, new Point(100,100));
		new JoinBuilder().mortises(graphic, new Point(100,50));
		new MotorPlate(Leg.THEO_JANSEN, 6, 1.5).draw(graphic.group("motor-plate"));
		new LegBuilder(Leg.THEO_JANSEN, 6, 1.5).draw(graphic.group("legs"), 0);
	}
}
