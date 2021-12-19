package application;

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
	
	public double getX() {return x;}
	public double getY() {return y;}
	public MyColor getColor() {return color;}
	
}
