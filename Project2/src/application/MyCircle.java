package application;

import javafx.scene.canvas.GraphicsContext;

public class MyCircle extends MyOval{
	MyPoint center;
	double centerX;
	double centerY;
	double radius;
	MyColor color;
	
	MyCircle(MyPoint p, double r, MyColor Color) {
		super(p, r, r, Color);
		this.center = p;
		this.radius = r;
		this.color = Color;
		this.centerX = p.getX();
		this.centerY =  p.getY();
	}
	
	public final void setCenterX(double x) {
		this.centerX = x;
	}
	
	public final void setCenterY(double y) {
		this.centerY = y;
	}
	
	public double getCenterY() {
		return centerY;
	}
	
	public double getCenterX() {
		return centerX;
	}
	
	
	public MyPoint getCenter(){return center;}
	public double getRadius() {return radius;}
	public MyColor getColor() {return color;}
	
	public String toString() {
		return "Circle center: (" + center.getX() + ", " + center.getY() + ") \n" + 
				"Radius ";
	}
	public void draw(GraphicsContext GC) {
		GC.setFill(color.setColor());
		GC.strokeOval((centerX - radius), (centerY - radius) , radius*2, radius*2);
		GC.fillOval((centerX - radius), (centerY - radius) , radius*2, radius*2);
	}
}
