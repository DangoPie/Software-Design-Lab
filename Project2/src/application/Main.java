package application;
import javafx.application.Application;
import javafx.stage.Stage;	
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws Exception{
		//Canvas size
		double width = 800;
		double height = 400;

        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        GraphicsContext GC = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.show();
	
        double screenCenterW = width/2;
        double screenCenterH = height/2;
        
        MyPoint center = new MyPoint(screenCenterW,screenCenterH);
        
        MyCircle cir1 = new MyCircle(center, height / 3, MyColor.LAVENDER);
        cir1.draw(GC);
        MyPolygon pen1 = new MyPolygon(center, height /3 , 5, MyColor.KHAKI);
        pen1.draw(GC);
        
        MyCircle cir2 = new MyCircle(center, height / 3.7, MyColor.HONEYDEW);
        cir2.draw(GC);
        MyPolygon pen2 = new MyPolygon(center, height /3.7, 5, MyColor.BISQUE);
        pen2.draw(GC);
        
        MyCircle cir3 = new MyCircle(center, height / 4.6, MyColor.THISTLE);
        cir3.draw(GC);
        MyPolygon pen3 = new MyPolygon(center, height / 4.6, 5, MyColor.PALETURQUOISE);
        pen3.draw(GC);
        
        MyCircle cir4 = new MyCircle(center, height / 5.7, MyColor.AZURE);
        cir4.draw(GC);
        
        
        MyLine line1 = new MyLine(0, 0, width, height,  MyColor.BLACK);
        line1.draw(GC);
        MyLine line2 = new MyLine(0,height,width,0,  MyColor.BLACK);
        line2.draw(GC);
        
        MyShapeInterface.intersectMyShapes(cir1, cir2);
        
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
