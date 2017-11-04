package org.rob.strandbeest.components;

import org.rob.strandbeest.graphic.Graphic;
import org.rob.strandbeest.graphic.Graphic.Style;
import org.rob.strandbeest.graphic.Point;

public class Motor {
	// https://cdn.solarbotics.com/products/datasheets/solarbotics_gm2_metric.pdf

	private static final double HEIGHT = 22.7;
	private static final double WIDTH = 41.8;

	private static final double SHAFT_PASSTHROUGH_DIA = 8.0;
	private static final double SHAFT_DIA = 4.73;
	private static final double SHAFT_H_OFFSET = 30.76;

	private static final double LUG_DIA = 3.9;
	private static final double LUG_H_OFFSET = 19.51 - SHAFT_H_OFFSET;

	private static final double MOUNT_DIA = 2.5;
	private static final double MOUNT_H_OFFSET = 39.05 - SHAFT_H_OFFSET;
	private static final double MOUNT_V_OFFSET = 17.2/2.0;


	public void draw(Graphic graphic) {
		new BoxBuilder().draw(graphic, Style.CUT, new Point(-SHAFT_DIA/2, SHAFT_DIA/2), new Point(SHAFT_DIA/2, -SHAFT_DIA/2));

		Point shaftPassThrough = new Point(0.0, 0.0);
		graphic.circle(Style.CUT, shaftPassThrough, SHAFT_PASSTHROUGH_DIA/2.0);

		Point mount1 = new Point(MOUNT_H_OFFSET, MOUNT_V_OFFSET);
		graphic.circle(Style.CUT, mount1, MOUNT_DIA/2.0);

		Point mount2 = new Point(MOUNT_H_OFFSET, -MOUNT_V_OFFSET);
		graphic.circle(Style.CUT, mount2, MOUNT_DIA/2.0);

		Point lug = new Point(LUG_H_OFFSET, 0);
		graphic.circle(Style.CUT, lug, LUG_DIA/2.0);

		Point tl = new Point(-SHAFT_H_OFFSET, HEIGHT/2.0);
		Point br = new Point(WIDTH - SHAFT_H_OFFSET, -HEIGHT/2.0);
		new BoxBuilder().draw(graphic, Style.IGNORE, tl, br);
	}
}
