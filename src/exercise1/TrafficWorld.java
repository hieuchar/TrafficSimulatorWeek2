package exercise1;

import java.awt.Color;
import java.util.Random;

import greenfoot.GreenfootImage;
import greenfoot.World;

public class TrafficWorld extends World {
	public static final int ROADWIDTH = 50;
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 750;
	private static final int CELL_SIZE = 1;
	private static final int NUMHCARS = 10;
	private static final int NUMVCARS = 14;
	private static final int NUMHROAD = 5;
	private static final int NUMVROAD = 7;
	private static final int CARWIDTH = 25;
	private static final int NUMCARONROAD = 2;
	private static final int VGAP = (HEIGHT - (NUMHROAD * ROADWIDTH)) / (NUMHROAD - 1);
	private static final int HGAP = (WIDTH - (NUMVROAD * ROADWIDTH)) / (NUMVROAD - 1);
	private static Road[] horizRoad = new Road[NUMHROAD];
	private static Road[] vertRoad = new Road[NUMVROAD];
	public TrafficWorld()
	{
		super(WIDTH,HEIGHT,CELL_SIZE,true);
		GreenfootImage background = this.getBackground();
		background.setColor(Color.GREEN);
		background.fill();
		
		drawHRoads();
		drawVRoads();
		drawInter();
		drawHCars();
		drawVCars();
		
		
	}
	public void drawHRoads(){
		for(int i = 0; i < NUMHROAD; i++){
			horizRoad[i] = new Road(true);
			this.addObject(horizRoad[i], WIDTH / 2, (VGAP + ROADWIDTH) * i + (ROADWIDTH / 2));
		}
	}
	public void drawVRoads(){
		for(int i = 0; i < NUMVROAD; i++){
			vertRoad[i] = new Road(false);
			this.addObject(vertRoad[i],(HGAP + ROADWIDTH) * i + (ROADWIDTH / 2),  HEIGHT / 2 );
		}
	}
	public void drawHCars(){
		Random rand = new Random();
		Car[] hCars = new Car[NUMHCARS];
		int counter = 0;
		for(int i = 0; i < NUMHCARS; i++){
			if(i % 2 == 0 || i == 0){
				hCars[i] = new Car(Orientation.WEST);
			}
			else hCars[i] = new Car(Orientation.EAST);
			
		}
		for(int x = 0; x < NUMHROAD; x++){
			for(int i = 0; i < NUMCARONROAD; i ++){
				Car temp = hCars[counter];
				Orientation dir = temp.getOrientation();
				temp.setRotation(dir.getRotation());
				this.addObject(temp, rand.nextInt(WIDTH), (VGAP + ROADWIDTH) * x  +  (CARWIDTH / 2) +  (CARWIDTH * i));
				counter++;
			}
		}	
	}
	public void drawVCars(){
		Random rand = new Random();
		Car[] vCars = new Car[NUMVCARS];
		int counter = 0;
		for(int i = 0; i < NUMVCARS; i++){
			if(i % 2 == 0 || i == 0){
				vCars[i] = new Car(Orientation.NORTH);
			}
			else vCars[i] = new Car(Orientation.SOUTH);
		}
		for(int x = 0; x < NUMVROAD; x++){
			for(int i = 0; i < NUMCARONROAD; i ++){
				Car temp = vCars[counter];
				Orientation dir = temp.getOrientation();
				temp.setRotation(dir.getRotation());
				this.addObject(temp, (HGAP + ROADWIDTH) * x  + (CARWIDTH / 2) +  (CARWIDTH * i), rand.nextInt(HEIGHT) );
				counter++;
			}
		}
	}
	public void drawInter(){
		for (int i = 0; i < horizRoad.length; i ++){
			for(int j = 0; j < vertRoad.length; j++){
				Intersection intersection = new Intersection();
				this.addObject(intersection, vertRoad[j].getX(), horizRoad[i].getY());
				intersection.addLights();
			}
		}
	}
	
}
