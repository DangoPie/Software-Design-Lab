package application;

import javafx.scene.canvas.GraphicsContext;

class MyOval extends MyShape{
	MyPoint center;
	double width, height;
	double semiMajor, semiMinor;
	MyColor color;
	
	int focus; 
	double eccentricity;
	
	MyOval(MyPoint p, double a, double b, MyColor Color){
		super(new MyPoint(), null);
		
		this.center = p;
		this.width = a;
		this.height = b;
		
		this.semiMajor = (a > b) ? a : b;
		this.semiMinor = (a < b) ? a : b;
		this.color = Color;
		
		this.focus = (int) Math.sqrt(Math.pow(semiMajor, 2) - Math.pow(semiMinor, 2));
		this.eccentricity = (double) focus/semiMajor;
	}
	
	public MyPoint getCenter() {return center;}
	public double getWidth() {return width;}
	public double getHeight() {return height;}
	public double getMajorAxis() {return semiMajor;}
	public double getMinorAxis() {return semiMinor;}
	public MyColor getColor() {return color;}
	
	public int getFocus() {return focus;}
	public double getEccentricity() {return eccentricity;}
	
	@Override
	public int perimeter() {
		return (int)(Math.sqrt(2) * Math.PI * Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2)));
	}
	
	@Override
	public int area() {return (int)(Math.PI * semiMajor * semiMinor);}
	
	public MyRectangle getMyBoundingRectangle() {
		double x = center.getX() - width;
		double y = center.getY() - height;
		MyPoint pLTC = new MyPoint( x, y);
		
		return new MyRectangle(pLTC, 2*width, 2*height, null);
	}
	
	public boolean pointInMyShape(MyPoint p) {
		if (width == height) {
			return center.distance(p) <= width;
		}else {	
			double dx = center.getX() - p.getX();
			double dy = center.getY() - p.getY();
			return Math.pow(height * dx, 2) + Math.pow(width*dy, 2) <= Math.pow(width*height, 2);
		}
		
	}
	
	public void draw(GraphicsContext GC) {
		GC.setFill(color.setColor());
		GC.fillOval(center.getX() - width, center.getY() - height, 2 * width, 2 * height);
		
	}
	
	public String toString() {
		return "Oval center (" + center.getX() + ", " + center.getY() + 
				")\n Major Axis" + semiMajor + " Minor Axis " + semiMinor + "\n"
				+ "Perimeter " + perimeter() + " Area " + area();}
	
}

