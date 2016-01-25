package exercise1;

import java.util.Random;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Car extends Actor implements IntersectionListener {
	private GreenfootImage image;
	private Orientation dir;
	private int speed = 2;
	private boolean passed = false;
	Intersection current = null;
	private String[] cars = {"images/topCarBlue.png","images/topCarRed.png","images/topCarPurple.png","images/topCarYellow.png"};
	public Car(Orientation dir){
		Random rand = new Random();
		this.dir = dir;
		int x = rand.nextInt(4);
		image = new GreenfootImage(cars[x]);
		this.setImage(image);
	}
	@Override
	public void act(){		
		this.move(speed);
		if(this.isAtEdge()){
			if(dir.equals(Orientation.NORTH) || dir.equals(Orientation.SOUTH)){
				this.setLocation(this.getX(), (this.getY() == 0)?TrafficWorld.HEIGHT:0);
//				if(this.getY() == 0){
//					this.setLocation(this.getX(), TrafficWorld.HEIGHT);
//				}
//				else {
//					this.setLocation(this.getX(), 0);
//				}
			}
			
			else if(dir.equals(Orientation.EAST) || dir.equals(Orientation.WEST)){
				if (this.getX() == 0){
					this.setLocation(1000, this.getY());
				}
				else {
					this.setLocation(0, this.getY());
				}
			}
		}

		
	}
	public Orientation getOrientation(){
		return dir;
	}
	public int getWidth(){
		return image.getWidth();
	}
	public int getHeight(){
		return image.getHeight();
	}
	public String toString(){
		return ("" + dir);
	}
	public void setSpeed(int speed){
		this.speed = speed;
	}
	@Override
	public void approachingIntersection(Intersection in){
		if(passed && in.equals(current) ){
			passedIntersection(in);
		}
		else{
			current = in;
			passed = false;
			if(dir.equals(Orientation.NORTH) || dir.equals(Orientation.SOUTH)){
				if(in.verticalColor.equals(TrafficLight.Color.RED)){
					speed = 0;
				}
				else if(in.verticalColor.equals(TrafficLight.Color.YELLOW)){
					speed = 1;
				}
				else speed = 2;
			}
			if(dir.equals(Orientation.EAST) || dir.equals(Orientation.WEST)){
				if(in.horizColor.equals(TrafficLight.Color.RED)){
					speed = 0;
				}
				else if(in.horizColor.equals(TrafficLight.Color.YELLOW)){
					speed = 1;
				}
				else speed = 2;
			}
		}
	}
	@Override
	public void inIntersection(Intersection in){
		current = in;
		passed = true;
		if(dir.equals(Orientation.NORTH) || dir.equals(Orientation.SOUTH)){
			if(in.verticalColor.equals(TrafficLight.Color.RED)){
				speed = 0;
			}
			else if(in.verticalColor.equals(TrafficLight.Color.YELLOW)){
				speed = 2;
			}
			else speed = 2;
		}
		if(dir.equals(Orientation.EAST) || dir.equals(Orientation.WEST)){
			if(in.horizColor.equals(TrafficLight.Color.RED)){
				speed = 0;
			}
			else if(in.horizColor.equals(TrafficLight.Color.YELLOW)){
				speed = 2;
			}
			else speed = 2;
		}		
	}
	@Override
	public void passedIntersection(Intersection in){
		speed = 2;
	}
}
