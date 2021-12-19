package application;
import javafx.application.Application;
import javafx.stage.Stage;	
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.lang.Math;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws Exception{
		//Canvas size
		int width = 1600;
		int height = 800;
		
		//shape size
		double shapeW = width/2;
		double shapeH = height/2;
	
        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        GraphicsContext GC = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.show();
		
        double m = (double)height/(double)width;

        MyPoint FTLC1 = new MyPoint(100,m * 100 );
        MyPoint FTLC2 = new MyPoint(215,m * 215 );
        MyPoint FTLC3 = new MyPoint(300,m * 300 );
        
        MyRectangle rect1 = new MyRectangle(FTLC1,shapeW,shapeH, MyColor.PALETURQUOISE);
        shapeW = shapeW/Math.sqrt(2);
        shapeH = shapeH/Math.sqrt(2);
        MyRectangle rect2 = new MyRectangle(FTLC2,shapeW,shapeH, MyColor.ALICEBLUE);
        MyRectangle rect3 = new MyRectangle(FTLC3,shapeW/Math.sqrt(2),shapeH/Math.sqrt(2), MyColor.LAVENDER);
		shapeW = width/2;
		shapeH = height/2;
        MyOval oval1 = new MyOval(FTLC1, shapeW , shapeH , MyColor.LIGHTPINK);
        shapeW = shapeW/Math.sqrt(2);
        shapeH = shapeH/Math.sqrt(2);
        MyOval oval2 = new MyOval(FTLC2, shapeW , shapeH , MyColor.PEACHPUFF);
        MyOval oval3 = new MyOval(FTLC3, shapeW/Math.sqrt(2),shapeH/Math.sqrt(2), MyColor.MISTYROSE);
		MyLine line1 = new MyLine(0,0,width,height,  MyColor.BLACK);

		rect1.draw(GC);
		oval1.draw(GC);
		rect2.draw(GC);
		oval2.draw(GC);
		rect3.draw(GC);
		oval3.draw(GC);
		line1.draw(GC);
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
