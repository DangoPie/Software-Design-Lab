package application;

import javafx.scene.paint.Color;

enum MyColor {
	BLACK(0,0,0),
	ALICEBLUE(240,248,255),
	LAVENDER(230,230,250),
	MISTYROSE(255,228,225),
	AZURE(240,255,255),
	PEACHPUFF(255,218,185),
	CRIMSON(220,20,60),
	PALETURQUOISE(175,238,238),
	LIGHTPINK(255,182,193),
	TOMATO(255,99,71),
	KHAKI(240,230,140),
	CORNFLOWERBLUE(100,149,237),
	THISTLE(216,191,216),
	ROSYBROWN(188,143,143),
	HONEYDEW(240,255,240),
	MINTCREAM(245,255,250),
	BISQUE(255,228,196);
	
	//Variables
	private int r,g,b;
	
	MyColor(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
		
	}
	
	public Color setColor() {
		return Color.rgb(r, g, b);
	}

}
