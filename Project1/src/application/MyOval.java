package application;

import javafx.scene.canvas.GraphicsContext;

public class MyOval extends MyShape{
	double x,y;
	MyPoint pTopLC;
	double radiusX,radiusY;
	MyColor color;
	
	MyOval(MyPoint p, double radiusX, double radiusY, MyColor color){
		super(new MyPoint() , null);
		this.pTopLC = p;
		this.x = pTopLC.getX();
		this.y = pTopLC.getY();
		this.radiusX = radiusX;
		this.radiusY = radiusY;
		this.color = color;
	}
	public double getPerimeter() 
	{
		return Math.PI*(radiusX + radiusY) - Math.sqrt((3*radiusX + radiusY) * (radiusX + 3*radiusY));
	}
	
	public double getArea()
	{
		return Math.PI * radiusX * radiusY;
	}
	public double getCenterX() {return pTopLC.getX();}
	public double getCenterY() {return pTopLC.getY();}
	public double getA() {return radiusX;}
	public double getB() {return radiusY;}
	public MyPoint getTopLC() {return pTopLC;}
	public double getXaxis() {return radiusX * 2;}
	public double getYaxis() {return radiusY * 2;}
	
	public String toString() 
	{
		String xAxis = Double.toString(radiusX);
		String yAxis = Double.toString(radiusY);
		String perimeter = Double.toString(getPerimeter());
		String area = Double.toString(getPerimeter());
		return "The Xaxis is: " + xAxis + 
				   ". The yAxis is " + yAxis +
				   ". The perimeter is " + perimeter +
				   ". The area is " + area; 
	}
	
	public void draw(GraphicsContext GC) {
		GC.setFill(color.setColor());
		GC.fillOval(pTopLC.getX(), pTopLC.getY(), radiusX, radiusY);
	}
	
}

