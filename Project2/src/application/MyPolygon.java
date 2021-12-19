package application;

import javafx.scene.canvas.GraphicsContext;

public class MyPolygon extends MyShape {
	private int N;
	private double r;
	private double[] xVert;
	private double[] yVert;
	
	MyColor Color;
	MyPolygon(MyPoint p, double r, int N, MyColor color) {
		super(p, color);
		this.N = N;
		this.r = r;
		this.Color = color;
		this.xVert = new double[N];
		this.yVert = new double[N];
		double intAngle = (2 * Math.PI) / N;
		for(int i = 0; i< N; i++) {
			xVert[i] = this.p.getX() + (r * Math.sin(i * intAngle) * -1);
			yVert[i] = this.p.getY() + (r * Math.cos(i * intAngle) * -1);
		}
	}

	@Override
	public MyRectangle getMyBoundingRectangle() {
		double x = r;
		double y = r;
		MyPoint pLTC = new MyPoint( x, y);
		return new MyRectangle(pLTC, 2*r, 2*r, null);
	}

	@Override
	public boolean pointInMyShape(MyPoint p) {
		double x = p.getX();
		double y = p.getY();
		boolean TF = false;
		for(int i = 0; i< N; i++) {
			if (x <= xVert[i] && y <= yVert[i] && r <= xVert[i] && r <= yVert[i])
				TF =  true;
			else {
				TF = false;
			}
		}
		return TF;
	}

	@Override
	public int area() {
		return ((N-2)*180/N);
	}

	@Override
	public int perimeter() {
		return N*getSide();
	}
	public double getApothem() {
		return r*Math.cos(Math.PI/N);
	}
	public double getAngle() {
		return (2 * Math.PI) / N;
	}
	public String toString() {
		String Apothem = Double.toString(getApothem());
		String x = Double.toString(p.getX());
		String y = Double.toString(p.getY());
		String sideLength = Double.toString(getSide());
		String interiorAngle = Double.toString(getAngle());
		String perimeter = Double.toString(perimeter());
		String area = Double.toString(area());
		return "Apothem " + Apothem + "\n"
				+"center (" + x + ", "+ y + ")"+ "\n"
				+ "Side Length" + sideLength + "\n"
				+ "Interior Angle " + interiorAngle+ "\n"
				+"perimeter " + perimeter+ "\n"
				+ "area " + area + "\n";
	}
	
	public int getSide() {return (int) Math.sqrt((Math.pow(xVert[0] - xVert[1], 2) + (Math.pow(yVert[0] - yVert[1], 2)))); }
	
	public void draw(GraphicsContext GC) {
		GC.setFill(Color.setColor());
		GC.strokePolygon(xVert, yVert, N);
		GC.fillPolygon(xVert, yVert, N);
	}

}
