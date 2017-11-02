package org.rob.strandbeest.main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.rob.strandbeest.graphic.Graphic;
import org.rob.strandbeest.graphic.SvgGraphic;
import org.rob.strandbeest.leg.Grid;
import org.rob.strandbeest.leg.Leg;
import org.rob.strandbeest.leg.LegRender;

public class SvgMain {

	public static void main(String[] args) throws FileNotFoundException {
		try(PrintWriter pw = new PrintWriter("out.svg")) {
			SvgGraphic svgGraphic = new SvgGraphic(pw);
			svgGraphic.begin();
			draw(svgGraphic);
			svgGraphic.end();
		}
 	}

	private static void draw(Graphic graphic) {
		new Grid(100, 300).draw(graphic);
		new LegRender(Leg.THEO_JANSEN, 2.0, 10, 5).render(graphic, 0);
	}
}
