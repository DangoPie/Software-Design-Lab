package application;

import javafx.scene.canvas.GraphicsContext;

class MyLine extends MyShape{
	private double x1,x2;
	private double y1,y2;
	private MyColor color;
	MyPoint p1;
	MyPoint p2;
	
	MyPoint[] pLine = new MyPoint[2];
	public MyLine(double x1, double y1, double x2, double y2, MyColor color) 
	{
		super(new MyPoint() , null);
		this.color = color;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		//p1.setPoint(x1, y1);
		//p2.setPoint(x2, y2);
	}
	
	public double getLength() 
	{
		return Math.sqrt(Math.pow((this.x2 - this.x1), 2) + Math.pow((this.y2 - this.y1),2));
	}
	
	public double get_xAngle()
	{
		double slope = (this.y2 -this.y1) / (this.x2 - this.x1);
		double angle = Math.atan(slope);
		return Math.toDegrees(angle);
	}
	public MyPoint[] getLine() {return pLine;}
	public MyColor getColor() {return color;}
	
	
	public double getX1() { return x1;}
	public double getX2() { return x2;}
	public double getY1() { return y1;}
	public double getY2() { return y2;}
	@Override
	public int perimeter() {return (int) getLength();}
	@Override 
	public int area() {return 0;}
	
	
	public MyRectangle getMyBoundingRectangle() {
		MyPoint pTLC = new MyPoint (Math.min(x1, x2), Math.min(y1,y2));
		return new MyRectangle(pTLC, Math.abs(x1-x2), Math.abs(y1 -y2), null);
	}
	
	public boolean pointInMyShape(MyPoint p) {
		return(p1.distance(p) + p2.distance(p) == perimeter());
	}
	
	public String toString() 
	{
		String length = Double.toString(getLength());
		String angle = Double.toString(get_xAngle());
		String x1 = Double.toString(getX1());
		String x2 = Double.toString(getX2());
		String y1 = Double.toString(getY1());
		String y2 = Double.toString(getY2());
		return "The length is: " + length 
				+ ". The angle is " + angle 
				+ ". Point 1 is: (" + x1 + ", " + y1 + "). "
				+ "Point 2 is: (" + x2 + ", " + y2 + "). ";
	}
	
	public void draw (GraphicsContext GC) 
	{
		GC.setStroke(color.setColor());
		GC.setLineWidth(1);
		GC.strokeLine(this.x1, this.y1, this.x2, this.y2);
	}

	
	
}
