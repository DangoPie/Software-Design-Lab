package application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

class Slice {
	MyPoint center;
	int radius;
	double startAngle;
	double angle;
	double rStartAngle, rAngle, arcLength;
	
	MyColor color;
	Slice(MyPoint p, int r, double startAngle, double angle, MyColor color){
		this.center = p;
		this.radius = r;
		this.startAngle = startAngle;
		this.angle = angle;
		this.color = color;
		
		this.rAngle = Math.toRadians(angle);
	}
	
	Slice(Slice s){
		this.center = s.getCenter();
		this.radius = s.getRadius();
		this.startAngle = s.getStartAngle();
		this.angle = s.getAngle();
		
		this.rAngle = Math.toRadians(angle);
	}
	
	public MyPoint getCenter() {
		return center;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public double getStartAngle() {
		return startAngle;
	}
	
	public double getAngle() {
		return angle;
	}
	
	public double getArcLength() {
		return (double) radius * rAngle;
	}
	
	public MyColor getColor() {
		return color;
	}
	
	public double area() {
		return (0.5 * rAngle * Math.pow(radius, 2));
	}
	
	public double perimeter() {
		return getArcLength();
	}
	
	
	public void setCenter(MyPoint center) {
		this.center = center;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public void setStartAngle(double startAngle) {
		this.startAngle = startAngle;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public void setArcLength(double arcLength) {
		this.arcLength = arcLength;
	}
	
	public void setColor(MyColor color) {
		this.color = color;
	}
	
	
	public String toString() {
		return "Slice: Center(" + center.getX()+ ", " + center.getY() + ") Radius: " + radius
				+ "(Starting Angle, Angle): (" + startAngle + ", " + angle + "), ";
	}
	
	public void draw(GraphicsContext GC) {
		GC.setFill(color.setColor());
		GC.strokeArc(center.getX() - radius, center.getY()- radius, 2* radius, 2* radius, startAngle, angle, ArcType.ROUND);
		GC.fillArc(center.getX() - radius, center.getY()- radius, 2* radius, 2* radius, startAngle, angle, ArcType.ROUND);
	}
}
