package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

interface MyShapeInterface {
	MyRectangle getMyBoundingRectangle();
	
	boolean pointInMyShape(MyPoint p);

	public static MyRectangle overlapMyRectangles(MyRectangle R1, MyRectangle R2) {
		double x1 = R1.getX();
		double y1 = R1.getY();
		double w1 = R1.getWidth();
		double h1 = R1.getHeight();
		
		double x2 = R2.getX();
		double y2 = R2.getY();
		double w2 = R2.getWidth();
		double h2 = R2.getHeight();
		
		if( y1 + h1 < y2 || y1 > y2 + h2) 
			return null;
		
		if(x1 + w1 < x2 || x1 > x2 + w2)
			return null;
		
		int xmax = (int) Math.max(x1, x2);
		int ymax = (int) Math.max(y1, y2);
		int xmin = (int) Math.min(x1 + w1, x2 + w2);
		int ymin = (int) Math.min(y1 + h1, y2 + h2);
		
		MyPoint p = new MyPoint(xmax, ymax);
		
		return new MyRectangle(p, Math.abs(xmin - xmax), Math.abs(ymin - ymax), null);
		
	}
	
	public static MyRectangle overlapMyShapes(MyShape S1, MyShape S2) {
		MyRectangle R1 = S1.getMyBoundingRectangle();
		MyRectangle R2 = S2.getMyBoundingRectangle();
		return overlapMyRectangles(R1, R2);
	}
	
	public static List<MyPoint> intersectMyShapes(MyShape S1, MyShape S2){
		MyRectangle R1 = S1.getMyBoundingRectangle();
		MyRectangle R2 = S2.getMyBoundingRectangle();
		MyRectangle R = overlapMyShapes(R1,R2);
		
		if( R != null) {
			double x = R.getX();
			double y = R.getY();
			double w = R.getWidth();
			double h = R.getHeight();
			
			List<MyPoint> intersect = new ArrayList<MyPoint>();
			
			for(int i = 0; i < w; ++i) {
				double xi = x + i;
				for(int j = 0; j < h; ++j) {
					MyPoint p = new MyPoint(xi, y + j);
					if(S1.pointInMyShape(p) && S2.pointInMyShape(p)) {
						p.setColor(MyColor.MISTYROSE);
						intersect.add(p);
					}
				}
			}
			return intersect;
			
		}
	
		else {
			return null;
		}
	}
	
	public default Canvas drawIntersectMyShapes(MyShape S1, MyShape S2) {
		List<MyPoint> intersect = intersectMyShapes(S1, S2);
			Canvas overlayCV = new Canvas();
			GraphicsContext overlayGC = overlayCV.getGraphicsContext2D();
			S1.draw(overlayGC);
			S2.draw(overlayGC);
		for(MyPoint p : intersect) {
			p.draw(overlayGC);
		}
		return overlayCV;
	}
	
}
