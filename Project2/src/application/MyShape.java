package application;

import javafx.scene.canvas.GraphicsContext;

abstract class MyShape implements MyShapeInterface {
	MyPoint p;
	MyColor color;

	MyShape(MyPoint p, MyColor color)
	{
		setPoint(p);
		this.color = MyColor.BISQUE;
	}
	
	public void setPoint(MyPoint p) {this.p = p;}
	public void setPoint(int x, int y) { p.setPoint(x,y);}
	public void setColor(MyColor color) {this.color = color;}
	
	public MyPoint getPoint() {return p;}
	public double getX() {return p.getX();}
	public double getY() {return p.getY();}
	public MyColor getColor() {return color;}
	
	public abstract int area();
	public abstract int perimeter();

	public void draw(GraphicsContext GC) {
		GC.setFill(color.setColor());
		GC.fillRect(0, 0, p.getX(), p.getY());
	}
	
	public String toString() {return "This is MyShape Object";}
	
}
