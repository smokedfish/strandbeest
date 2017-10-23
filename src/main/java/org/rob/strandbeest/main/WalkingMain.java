package org.rob.strandbeest.main;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import org.rob.strandbeest.graphic.JavaGraphic;
import org.rob.strandbeest.leg.ScottBurnsLeg;

public class WalkingMain extends JavaGraphic implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final int FRAME_WIDTH = 700;
	private static final int FRAME_HEIGHT = 700;

	private final Timer timer;
	private final ScottBurnsLeg leg2 = new ScottBurnsLeg(5, 2);

	private double ang = 0.0;

	public WalkingMain(int width, int height) {
		super(width, height);
	    this.timer = new Timer(50, this);
	    this.timer.setInitialDelay(190);
	    this.timer.start();
	}

	public static void main(String[] args) throws InterruptedException {
		WalkingMain walkingMain = new WalkingMain(FRAME_WIDTH, FRAME_HEIGHT);
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JScrollPane scrollpane = new JScrollPane(walkingMain, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scrollpane.getViewport().setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

				JFrame frame = new JFrame("Walking Animation");
				frame.getContentPane().add(scrollpane);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		clear();
		ang = ang < (2 * Math.PI) ? ang  += Math.PI/20 : 0.0;
		leg2.render(this, ang);
		repaint();
	}
}
