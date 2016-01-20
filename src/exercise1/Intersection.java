package exercise1;

import java.awt.Color;
import java.util.ArrayList;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Intersection extends Actor{
	private static final int GREEN_COUNT = 70;
	private static final int YELLOW_COUNT = 15;
	private static final int RED_COUNT = GREEN_COUNT + YELLOW_COUNT;
	private ArrayList<Car> carsOuter, carsInner;
	
	private TrafficLight tf1 = null;
	private TrafficLight tf2 = null;
	private TrafficLight tf3 = null;
	private TrafficLight tf4 = null;
	private int vLightCounter = 0;
	private int hLightCounter = 0;
	TrafficLight.Color verticalColor;
	TrafficLight.Color horizColor;
	public Intersection(){
		GreenfootImage intersection = new GreenfootImage(TrafficWorld.ROADWIDTH, TrafficWorld.ROADWIDTH);
		intersection.setColor(Color.BLACK);
		intersection.fill();
		setImage(intersection);
		
	}
	public void addLights(){
		verticalColor = TrafficLight.Color.RED;
		horizColor = TrafficLight.Color.GREEN;
		tf1 = new TrafficLight(verticalColor);
		tf2 = new TrafficLight(verticalColor);
		tf2.setRotation(Orientation.WEST.getRotation());
		tf3 = new TrafficLight(horizColor);
		tf3.setRotation(Orientation.SOUTH.getRotation());
		tf4 = new TrafficLight(horizColor);
		tf4.setRotation(Orientation.NORTH.getRotation());
		getWorld().addObject(tf1, this.getX(), getY() + TrafficWorld.ROADWIDTH/2 + tf1.getImage().getHeight()/2);
		getWorld().addObject(tf2, this.getX(), getY() - TrafficWorld.ROADWIDTH/2 - tf2.getImage().getHeight()/2);
		getWorld().addObject(tf3, this.getX() - TrafficWorld.ROADWIDTH/2 - tf3.getImage().getWidth(), getY());
		getWorld().addObject(tf4, this.getX() + TrafficWorld.ROADWIDTH/2 + tf4.getImage().getWidth(), getY());
	}
	public void act(){
		vLightCounter++;
		hLightCounter++;
		switch(verticalColor){
			case GREEN:
				if(vLightCounter == GREEN_COUNT){
					verticalColor = TrafficLight.Color.YELLOW;
					tf1.setColor(verticalColor);
					tf2.setColor(verticalColor);
					vLightCounter = 0;
				}
				break;
			case YELLOW:
				if(vLightCounter == YELLOW_COUNT){
					verticalColor = TrafficLight.Color.RED;
					tf1.setColor(verticalColor);
					tf2.setColor(verticalColor);
					vLightCounter = 0;
				}
				break;
			case RED:
				if(vLightCounter == RED_COUNT){
					verticalColor = TrafficLight.Color.GREEN;
					tf1.setColor(verticalColor);
					tf2.setColor(verticalColor);
					vLightCounter = 0;
				}
				break;
		}
		switch(horizColor){
			case GREEN:
				if(hLightCounter == GREEN_COUNT){
					horizColor = TrafficLight.Color.YELLOW;
					tf3.setColor(horizColor);
					tf4.setColor(horizColor);
					hLightCounter = 0;
				}
				break;
			case YELLOW:
				if(hLightCounter == YELLOW_COUNT){
					horizColor = TrafficLight.Color.RED;
					tf3.setColor(horizColor);
					tf4.setColor(horizColor);
					hLightCounter = 0;
				}
				break;
			case RED:
				if(hLightCounter == RED_COUNT){
					horizColor = TrafficLight.Color.GREEN;
					tf3.setColor(horizColor);
					tf4.setColor(horizColor);
					hLightCounter = 0;
				}
				break;
		}
		getOuter();
		getInner();
		adjustSpeed();
		
	}
	
	public void getOuter() {
		carsOuter = (ArrayList<Car>) (this.getObjectsInRange(TrafficWorld.ROADWIDTH + 10,Car.class));
		
	}

	public void getInner() {
		carsInner = (ArrayList<Car>) (this.getIntersectingObjects(Car.class));		
		
	}
	
	public void removeOuter() {
		// TODO Auto-generated method stub
		
	}
	
	public void removeInner() {
		// TODO Auto-generated method stub
		
	}
	
	public void removeDupe(){
		ArrayList<Car> removal = new ArrayList<Car>();
		if(!carsInner.isEmpty() && !carsOuter.isEmpty()){
			for(Car o: carsOuter){
				for(Car i: carsInner){
					if(o.equals(i)){
						removal.add(o);
					}
				}
			}
		}
		carsOuter.removeAll(removal); 
	}
	
	public void adjustSpeed() {
		removeDupe();
//		ArrayList<Car> vCO = new ArrayList<Car>();
//		ArrayList<Car> hCO = new ArrayList<Car>();
//		ArrayList<Car> hCI = new ArrayList<Car>();
//		ArrayList<Car> vCI = new ArrayList<Car>();
		if(!carsOuter.isEmpty()){
			for(Car o: carsOuter){
				o.approachingIntersection(this);
//				System.out.println(o);
//				if(o.getOrientation().equals(Orientation.NORTH) || o.getOrientation().equals(Orientation.SOUTH)){
//					vCO.add(o);
//				}
//				else hCO.add(o);
			}
		}
//			for(Car vOut:vCO){
//				if(verticalColor.equals(TrafficLight.Color.YELLOW)){
//					vOut.setSpeed(1);
//				}
//				else if(verticalColor.equals(TrafficLight.Color.RED)){
//					vOut.setSpeed(0);
//				}
//				else vOut.setSpeed(2);
//			}
//			for(Car hOut:hCO){
//				if(horizColor.equals(TrafficLight.Color.YELLOW)){
//					hOut.setSpeed(1);
//				}
//				else if(horizColor.equals(TrafficLight.Color.RED)){
//					hOut.setSpeed(0);
//				}
//				else hOut.setSpeed(2);
//			}
//		}
		if(!carsInner.isEmpty()){
				for(Car i: carsInner){
					i.inIntersection(this);
//					if(i.getOrientation().equals(Orientation.NORTH) || i.getOrientation().equals(Orientation.SOUTH)){
//						vCI.add(i);
//					}
//					else hCI.add(i);
				}
		}
//				for(Car vIn: vCI){
//					if(verticalColor.equals(TrafficLight.Color.YELLOW)){
//						vIn.setSpeed(2);
//					}
//					else if(verticalColor.equals(TrafficLight.Color.RED)){
//						vIn.setSpeed(2);
//					}
//					else vIn.setSpeed(2);				
//				}
//				for(Car hIn: hCI){
//					if(horizColor.equals(TrafficLight.Color.YELLOW)){
//						hIn.setSpeed(2);
//					}
//					else if(horizColor.equals(TrafficLight.Color.RED)){
//						hIn.setSpeed(2);
//					}
//					else hIn.setSpeed(2);
//					
//				}
//		}
	}
	
	public void clearIntersection() {
		// TODO Auto-generated method stub
		
	}
}
