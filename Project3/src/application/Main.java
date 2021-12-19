package application;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Scanner;
import java.util.Map;
import java.util.LinkedHashMap;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class Main extends Application {
	 static String fileName =  "C:\\Users\\Dango\\Downloads\\AliceinWonderland.txt";
	 static Scanner input;
	 final int WIDTHS = 800;
	 final int HEIGHTS = 800;
	 static String w = "";
	 Map<Character, Double> temp;
	 static Map<Character, Double> temphash = new LinkedHashMap<>();
	@Override
	public void start(Stage stage) {
		try {
			//button set up
			Button button = new Button();
			button.setText("Submit");
			button.setLayoutY(50);
			
			TextField enterN = new TextField();
	        int n =3;
	        enterN.setText(String.valueOf(n));
	        
			//set up stage
			Group root = new Group();
	        Canvas canvas = new Canvas(WIDTHS, HEIGHTS);

	        GraphicsContext GC = canvas.getGraphicsContext2D();
	       
	        root.getChildren().addAll(canvas,button);
	        stage.setScene(new Scene(root)); 
	        Pane flow = new Pane();
	        flow.setStyle("-fx-background-color: darkgrey");
	        
	        Scene scene = new Scene(flow, WIDTHS, HEIGHTS);
	        MyPoint center = new MyPoint(WIDTHS/2,HEIGHTS/2);
            flow.getChildren().addAll(canvas, button, enterN);
            stage.setTitle("Assignment 3");
            stage.setScene(scene);
            stage.show();
            button.setOnAction(event -> {
            	GC.clearRect(0, 0, WIDTHS, HEIGHTS); 
				HistogramAlphaBet histo = new HistogramAlphaBet(w);
				MyPieChart pie = new MyPieChart(Integer.parseInt(enterN.getText()) ,center, HEIGHTS/ 4, 0.0, histo);
				//testing
				temphash = histo.getProbability();
				System.out.println("OUTSIDE");
				temphash.forEach((K, V) -> System.out.println(K + ": " + V));
				
	            pie.draw(GC, center);
            });
            
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void readFile(){
		while(input.hasNextLine()){
            w += input.nextLine().replaceAll("[^a-zA-Z]", "").toLowerCase();
        }
	}
	public static void openFile(){
        try{
            input = new Scanner(Paths.get(fileName));
        }
        catch(IOException ioException){
            System.err.println("File is not found");
        }
    }
	 public static void closeFile(){
	        if(input != null) input.close();
	    }
    public static void main(String[] args){
    	 openFile();
    	 readFile();
    	 closeFile();
    	 launch(args);
    }
}	

