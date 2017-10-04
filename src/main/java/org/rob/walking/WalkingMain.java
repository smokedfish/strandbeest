package org.rob.walking;

import javax.swing.JFrame;

public class WalkingMain {

	static int frameWidth = 700;
	static int frameHeight = 700;

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame();
		frame.setSize(frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Graphic graphic = new Graphic(frameWidth, frameHeight);
		frame.getContentPane().add(graphic);
		frame.setVisible(true);

		TheoJansenLeg2 leg2 = new TheoJansenLeg2(graphic);
//		while(true)
		for (double ang = 0 ; ang <= (2 * Math.PI); ang -= Math.PI/20) {
			graphic.clear();
			leg2.render(ang);
			frame.repaint();
			Thread.sleep(50L);
		}
	}
}
