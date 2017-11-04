package org.rob.strandbeest.main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.rob.strandbeest.components.JoinBuilder;
import org.rob.strandbeest.components.Leg;
import org.rob.strandbeest.components.LegBuilder;
import org.rob.strandbeest.components.Motor;
import org.rob.strandbeest.components.MotorPlate;
import org.rob.strandbeest.graphic.Graphic;
import org.rob.strandbeest.graphic.Point;
import org.rob.strandbeest.graphic.svg.SvgGraphicContainer;

public class SvgMain {

	public static void main(String[] args) throws FileNotFoundException {
		try(PrintWriter pw = new PrintWriter("out.svg")) {
			SvgGraphicContainer svgGraphic = new SvgGraphicContainer(pw);
			draw(svgGraphic.defaultGraphic());
			svgGraphic.draw();
		}
 	}

	private static void draw(Graphic graphic) {
		new JoinBuilder().tenons(graphic, new Point(100,100));
		new MotorPlate(Leg.THEO_JANSEN, 5, 1.5).draw(graphic.group("motor-plate"));
		new LegBuilder(Leg.THEO_JANSEN, 5, 1.5).draw(graphic, 0);
	}
}
