package application;

import javafx.scene.canvas.GraphicsContext;

class MyRectangle extends MyShape {
	MyPoint pTopLC;
	double width, height;
	MyColor color;
	
	MyRectangle(MyPoint p, double w, double h, MyColor color)
	{
		super(new MyPoint() , null);
		
		this.pTopLC = p;
		this.width = w;
		this.height = h;
		this.color = color;
	}

	public MyPoint getTopLC() {return pTopLC;}
	public double getWidth() {return width;}
	public double getHeight() {return height;}
	public MyColor getColor() {return color;}
	public double getX() {return pTopLC.getX();}
	public double getY() {return pTopLC.getY();}
	
	public double getArea() {return width*height;}
	
	public double getPerimeter() {return 2* (width + height);}
	
	@Override
	public int perimeter() {return (int)getPerimeter();}
	@Override 
	public int area() {return (int)getArea();}
	
	
	
	public String toString() 
	{
		String pTopLC = this.pTopLC.toString();
		String width = Double.toString(getWidth());
		String height = Double.toString(getHeight());
		String perimeter = Double.toString(getPerimeter());
		String area = Double.toString(getArea());
		return "The width is: " + width + 
			   ". The height is " + height +
			   ". The perimeter is " + perimeter +
			   ". The area is " + area +
			   ". The top left corner is" + pTopLC;
	}
	
	public void draw(GraphicsContext GC) {
		GC.setStroke(color.setColor());
		GC.setFill(color.setColor());
		GC.fillRect(pTopLC.getX(), pTopLC.getY(), width, height);
	}

	@Override
	public MyRectangle getMyBoundingRectangle() {
		return this;
	}

	@Override
	public boolean pointInMyShape(MyPoint p) {
		double x = p.getX();
		double y = p.getY();
		double xR = pTopLC.getX();
		double yR = pTopLC.getY();
		return (xR <= x && x<= xR + width) && (yR <= y && y <= yR + height);
	}
}
