package application;
import java.util.Optional;
import java.util.Random;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import javafx.scene.canvas.GraphicsContext;
class HistogramAlphaBet {
	
    Map<Character, Integer> frequency = new HashMap<Character, Integer>();
    Map<Character, Double> probability = new HashMap <Character, Double>();
    
    HistogramAlphaBet(){};
    HistogramAlphaBet(Map<Character, Double>n){
    	probability.putAll(n);
    }
    
    HistogramAlphaBet(String text){
    	 String w = text.replaceAll("[^a-zA-Z]", "").toLowerCase();
         for(int i = 0; i < w.length(); i++){
             Character key = w.charAt(i);
             incrementFrequency(frequency, key);
             
         }
    }
    
    private static<K> void incrementFrequency(Map<K, Integer>m , K Key ){
        
        m.putIfAbsent(Key, 0);
        m.put(Key, m.get(Key) + 1);
    }
	public Map<Character, Integer> getFrequency(){
    	return frequency;
    }
    
    public Integer getCumulativeFrequency() {
    	return frequency.values().stream().reduce(0,  Integer::sum);
    }
    
    
    public Map<Character,Integer> sortDownFrequency(){
    	return frequency
    			.entrySet()
    			.stream()
    			.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
    			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2 , LinkedHashMap::new));
    }
    
    public Map<Character, Double> getProbability(){
    	double inverseCumulativeFrequency = 1.0 / getCumulativeFrequency();
        for(Character Key : frequency.keySet()){
            probability.put(Key, (double) frequency.get(Key) * inverseCumulativeFrequency);
        }
        return probability.entrySet()
        				  .stream()
        				  .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
        				  .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue, (e1,e2) -> e2, LinkedHashMap::new));
    }
    
    public Double getSumOfProbability() {
    	return probability
    			.values()
    			.stream()
    			.reduce(0.0, Double::sum);
    }
    
    public String toString() {
    	frequency.forEach((K,V) -> System.out.println(K + ": " + V));
    	String output = "Frequency of Characters: \n";
    	for(Character K : frequency.keySet())
    	{
    		output += K + ": " + frequency.get(K) + "\n";
    	}
    	return output;
    }
    
    class MyPieChart{
    	Map<Character, Slice> slices = new HashMap<Character, Slice>();
    	int N;
    	MyPoint center;
    	int radius;
    	double rotateAngle;
    	
    	MyPieChart(int N, MyPoint p, int r, double rotateAngle){
    		this.N = N;
    		this.center = p;
    		this.radius = r;
    		this.rotateAngle = Optional.ofNullable(rotateAngle).orElse(0.0);
    		probability = getProbability();
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
    	
    	public void draw(GraphicsContext GC) {
    		Map<Character, Integer> sortedFrequency = sortDownFrequency();
	    		for(Character Key: sortedFrequency.keySet()) {
	    			slices.get(Key).draw(GC);
	    		}
    	}
    }

    
}
