package org.rob.strandbeest.main;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JFrame;

import org.rob.strandbeest.graphic.Graphic;
import org.rob.strandbeest.graphic.JavaGraphic;
import org.rob.strandbeest.graphic.Point;
import org.rob.strandbeest.graphic.SvgGraphic;
import org.rob.strandbeest.leg.ScottBurnsLeg;

public class SvgMain {
	private static int FRAME_WIDTH = 700;
	private static int FRAME_HEIGHT = 700;

	public static void main(String[] args) throws FileNotFoundException {
		try(PrintWriter pw = new PrintWriter("out.svg")) {
			SvgGraphic svgGraphic = new SvgGraphic(pw);
			svgGraphic.begin();
			draw(svgGraphic);
			svgGraphic.end();
		}

		JavaGraphic javaGraphic = new JavaGraphic(FRAME_WIDTH, FRAME_HEIGHT);

		JFrame frame = new JFrame();
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(javaGraphic);
		frame.setVisible(true);

		draw(javaGraphic);
	}

	private static void draw(Graphic graphic) {
		graphic.arc(Color.black, new Point(0,0), 100, Math.PI, Math.PI/4);
		graphic.line(Color.black, new Point(0,0), new Point(300, 0));
		graphic.line(Color.black, new Point(0,0), new Point(0, 100));
		graphic.text(Color.black, new Point(10,20), "(0,0)");
		graphic.text(Color.black, new Point(240,20), "(300,0)");
		graphic.text(Color.black, new Point(10,90), "(0,100)");
		ScottBurnsLeg sbl = new ScottBurnsLeg(graphic);
		sbl.render(0);
	}
}
