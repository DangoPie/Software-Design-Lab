package application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.TextAlignment;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;


public class MyPieChart {
	
	Map<Character, Slice> slices = new HashMap<Character, Slice>();
	Map<Character, Double> probability = new HashMap <Character, Double>();
	
	int N;
	MyPoint center;
	int radius;
	double rotateAngle;
	
	MyPieChart(int N, MyPoint p, int r, double rotateAngle, HistogramAlphaBet bet){
		this.N = N;
		this.center = p;
		this.radius = r;
		this.rotateAngle = Optional.ofNullable(rotateAngle).orElse(0.0);
		probability = bet.getProbability();
		slices = getMyPieChart();
	}
	
	public Map<Character, Slice> getMyPieChart(){
		MyColor[] colors = MyColor.getMyColors();
		Random rand = new Random();
		int colorsSize = colors.length;
		double startAngle = rotateAngle;
		for(Character Key: probability.keySet()) {
			double angle = 360.0 * probability.get(Key);
			slices.put(Key, new Slice(center, radius, startAngle, angle, colors[rand.nextInt(colorsSize)]));
			startAngle += angle;
		}
		return slices;
	}
    public double[] getAngle(){
        double[] angle; // array of angles 
        angle = new double[probability.size()];
        int count =0;
        for(double vals: probability.values()){
            angle[count] = 360*vals;
            count =count + 1;
        }
            return angle;
    }
	public void draw(GraphicsContext GC, MyPoint center ) {
		double s = 0; //used for helping the pie segments find their right position
		double sum = 0.0;
		double pad = 30; // used for padding between the labels and their colors.
		double va;
		Object Key = probability.keySet().toArray()[0];;
		for(int i = 0; i < N; i ++) {
			Key = probability.keySet().toArray()[i];
			if(i < N) {
				slices.get(Key).draw(GC);
			}

			Double value = (Double) probability.values().toArray()[i]; 
			va = value.doubleValue();
			sum += va;
	
	        s = s +getAngle()[i];
	        pad +=20;
			GC.setTextAlign(TextAlignment.RIGHT);
			GC.fillText(probability.keySet().toArray()[i]+ ": "+ va, GC.getCanvas().getWidth(), pad);
			
		}
		GC.setFill(Color.ANTIQUEWHITE);
		GC.fillText("Other texts"+ ": "+ (1-sum) ,GC.getCanvas().getWidth(), pad+20);
		GC.fillArc(center.getX() - radius, center.getY()- radius, 2* radius, 2* radius, s, 360-s, ArcType.ROUND);
		GC.strokeArc(center.getX() - radius, center.getY()- radius, 2* radius, 2* radius, s, 360-s, ArcType.ROUND);
	}

}