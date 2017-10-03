package org.rob.walking;

import java.awt.Dimension;
import java.awt.Toolkit;

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

		Leg leg = new Leg(graphic);
		while(true)
			for (double ang = 0 ; ang <= (2 * Math.PI); ang += Math.PI/20) {
				graphic.clear();
				leg.render(ang);
				frame.repaint();
				Thread.sleep(50L);
			}
	}

	public void centerTheGUIFrame(JFrame frame) {
		int widthWindow = frame.getWidth();
		int heightWindow = frame.getHeight();

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int X = (screen.width / 2) - (widthWindow / 2); // Center horizontally.
		int Y = (screen.height / 2) - (heightWindow / 2); // Center vertically.

		frame.setBounds(X, Y, widthWindow, heightWindow);
	}
}
