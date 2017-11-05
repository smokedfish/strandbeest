package org.rob.strandbeest.main;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import org.rob.strandbeest.components.Leg;
import org.rob.strandbeest.components.LegBuilder;
import org.rob.strandbeest.components.MotorPlate;
import org.rob.strandbeest.graphic.java.JavaGraphicContainer;

public class WalkingMain extends JavaGraphicContainer implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final int FRAME_WIDTH = 700;
	private static final int FRAME_HEIGHT = 700;
	private static final int RPM = 24;
	private static final int STEPS = 20;

	private final Timer timer;
	private final LegBuilder leg = new LegBuilder(Leg.THEO_JANSEN, 5, 2);

	private final MotorPlate motor = new MotorPlate(Leg.THEO_JANSEN, 5, 2);

	private double ang = 0.0;

	public WalkingMain(int width, int height, int rpm) {
		super(width, height);
		int delay = (60 * 1000)/(RPM * STEPS);
	    this.timer = new Timer(delay, this);
	    this.timer.setInitialDelay(200);
	    this.timer.start();
	}

	public static void main(String[] args) throws InterruptedException {
		WalkingMain walkingMain = new WalkingMain(FRAME_WIDTH, FRAME_HEIGHT, RPM);
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
		ang = ang < (2 * Math.PI) ? ang  += (2 *Math.PI)/STEPS : 0.0;
		leg
			.draw(defaultGraphic(), ang)
			.horizReflect()
			.draw(defaultGraphic(), ang);
		motor.side(defaultGraphic());

		repaint();
	}
}
