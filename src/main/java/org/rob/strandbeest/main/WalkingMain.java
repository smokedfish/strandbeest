package org.rob.strandbeest.main;

import javax.swing.JFrame;

import org.rob.strandbeest.graphic.JavaGraphic;
import org.rob.strandbeest.leg.ScottBurnsLeg;

public class WalkingMain {
	private static int FRAME_WIDTH = 700;
	private static int FRAME_HEIGHT = 700;

	public static void main(String[] args) throws InterruptedException {
		JavaGraphic graphic = new JavaGraphic(FRAME_WIDTH, FRAME_HEIGHT);

		JFrame frame = new JFrame();
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(graphic);
		frame.setVisible(true);

		ScottBurnsLeg leg2 = new ScottBurnsLeg(graphic);
		while (true) {
			for (double ang = 0 ; ang <= (2 * Math.PI); ang += Math.PI/20) {
				graphic.clear();
				leg2.render(ang);
				frame.repaint();
				Thread.sleep(50L);
			}
		}
	}
}
