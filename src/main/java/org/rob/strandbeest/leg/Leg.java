package org.rob.strandbeest.leg;

import lombok.Data;

@Data
public class Leg {
	// From: https://en.wikibooks.org/wiki/OpenSCAD_User_Manual/Example/Strandbeest
	public static final Leg THEO_JANSEN = new Leg(38.0, 41.5, 39.3, 40.1, 55.8, 39.4, 36.7, 65.7, 49.0, 50.0, 61.9, 7.8, 15.0);
	// From: http://scottburns.us/walking-mechanism/
	public static final Leg SCOTT_BURNS = new Leg(9.61, 12.9, 9.4, 10.0, 8.3, 10.1, 9.4, 16.8, 11.6, 18.5, 15.7, 2.77, 3.1);

	private final double a;
	private final double b;
	private final double c;
	private final double d;
	private final double e;
	private final double f;
	private final double g;
	private final double h;
	private final double i;
	private final double j;
	private final double k;
	private final double l;
	private final double m;
}
