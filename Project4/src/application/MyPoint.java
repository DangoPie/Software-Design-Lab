package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyPoint {
	double x, y;
	MyColor color;
	
	MyPoint()
	{
		setPoint(0,0); 
		this.color = MyColor.CRIMSON;
	}

	MyPoint(double x, double y)
	{
		setPoint(x,y);
		this.color = MyColor.CRIMSON;
	}
	
	public void setPoint(double x, double y) 
	{
		this.x = x;
		this.y = y;
	}
	
	public void setPoint(MyPoint p)
	{
		this.x = p.getX();
		this.y = p.getY();
	}
	
	public double angleX(MyPoint p) {
		double dx = (double) p.getX() - x;
		double dy = (double) p.getY() - y;
		return Math.toDegrees(Math.atan2(dy, dx));
	}
	
	public double getX() {return x;}
	public double getY() {return y;}
	public MyColor getColor() {return color;}

	public void draw(GraphicsContext GC) {
		GC.setFill(Color.ANTIQUEWHITE);
		GC.fillRect(x, y, 1, 1);
	}

	void setColor(MyColor Color) {
		this.color = Color;
		
	}

	public double distance(MyPoint p) {
		double dx = x - p.getX();
		double dy = y - p.getY();
		return Math.sqrt(dx * dx + dy * dy);
	}
	
}
