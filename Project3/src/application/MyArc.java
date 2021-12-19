package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class MyArc extends MyShape{
	MyPoint center;
	MyPoint p1, p2;
	double startAngle;
	double angle;
	double rStartAngle, rAngle, rEndAngle;
	MyColor color;
	int a , b;
	MyOval O;
	MyArc(MyPoint p, int a, int b, double startAngle, double angle, MyColor color){
		super(new MyPoint(), null);
		
		this.center = p;
		this.a = a;
		this.b = b;
		this.startAngle = startAngle;
		this.angle = angle;
		this.color = color;
		this.rStartAngle = Math.toRadians(startAngle);
		this.rAngle = Math.toRadians(angle);
		this.rEndAngle = Math.toRadians(startAngle + angle);
		
		int x = (int) center.getX();
		int y = (int) center.getY();
		int x1 = (int) (x + (double)(a * b)/ Math.sqrt(Math.pow(b,2) + Math.pow(b, 2)+ Math.pow(a*Math.tan(rStartAngle), 2)));
		int y1 = (int) (y + (double) (a * b)*Math.tan(rStartAngle) / Math.sqrt(Math.pow(b,2) *  Math.pow(a*Math.tan(rStartAngle), 2)));
		int x2 = (int) (x + (double)(a * b)/ Math.sqrt(Math.pow(b,2) + Math.pow(b, 2)+ Math.pow(a*Math.tan(rEndAngle), 2)));
		int y2 = (int) (y + (double) (a * b)*Math.tan(rEndAngle) / Math.sqrt(Math.pow(b,2) *  Math.pow(a*Math.tan(rEndAngle), 2)));
		this.p1 = new MyPoint(x1,y1);
		this.p2 = new MyPoint(x2,y2);
		
		this.O = new MyOval(center, a, b, color);
	}
	
	public MyPoint getCenter() {
		return center;
	}
	
	public double getStartAngle() {
		return startAngle;
	}
	
	public double gettAngle() {
		return angle;
	}
	
	public MyOval getOval() {
		return O;
	}
	
	public MyColor getColor() {
		return color;
	}
	
	public void setCenter(MyPoint center) {
		this.center = center;
	}
	
	public void setStartAngle(double startAngle) {
		this.startAngle = startAngle;
	}
	
	public void setAngle(double angle) {
		this.angle = angle ;
	}
	
	public void setOval(MyOval O) {
		this.O = O;
	}
	
	public void setColor(MyColor color) {
		this.color = color;
	}
	
	@Override
	public MyRectangle getMyBoundingRectangle() {
		return O.getMyBoundingRectangle();
	}
	@Override
	public boolean pointInMyShape(MyPoint p) {
		double pAngle = center.angleX(p);
		double dx = center.getX() - p.getX();
		double dy = center.getY() - p.getY();
		return Math.pow(b*dx, 2) + Math.pow(a*dy, 2) <= Math.pow( a*b, 2)
					&&  pAngle >= startAngle && pAngle <= startAngle + angle;
	}
	@Override
	public int area() {
		double HpW = (double) ( b + a );
		double HwW = (double) ( b - a );
		return (int) (0.5 * a * b *
				rAngle - (Math.atan(HwW * Math.sin(2.0 * rEndAngle)) / ( HpW + HwW * Math.cos(2.0 *rEndAngle)))
						- (Math.atan(HwW * Math.sin(2.0 * rStartAngle)) / ( HpW + HwW * Math.cos(2.0 *rStartAngle))));
	}
	@Override
	public int perimeter() {
		return (int) (0.5 * Math.PI / Math.sqrt(2) * p1.distance(p2));
	}
	
	public String toString() {
		return "Arc: Center" + center + "Oval Width" + 2*a + "Oval Height" + 2*b + "(STart Angle, Angle)" + startAngle + "," + angle
				+ "Perimeter" + perimeter() + "area" + area() + "color" + color;
	}
	
	public void draw(GraphicsContext GC) {
		GC.setFill(color.setColor());
		GC.fillArc(p.getX()- a, p.getY()- b , 2*a, 2*b, startAngle, angle, ArcType.ROUND);
	}
	
}
