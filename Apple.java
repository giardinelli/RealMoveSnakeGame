package gameProperties;

import java.awt.Color;
import java.util.List;

import edu.princeton.cs.introcs.StdDraw;

public class Apple {
	
	private double x;
	private double y;
	private double halfLength = 0.02;
	
	public Apple(double x , double y) {
		this.x = x;
		this.y = y;
	}
	public double getApX() {
		return x;
	}
	public double getApY() {
		return y;
	}
	public double getHalfLength() {
		return halfLength;
	}
	public void ApDraw() {
		StdDraw.setPenColor(Color.RED);
		StdDraw.filledCircle(x, y, halfLength);
	}
	public void randomLoc(List <Snake> sList) {
		if(sList.size() == 1) {
			double newX = Math.random() * (0.95 - 0.05) + 0.05;
			double newY = Math.random() * (0.965 - 0.93) + 0.93;
			this.x = newX;
			this.y = newY;
		}
		else {
			double newX = Math.random() * (0.95 - 0.05) + 0.05;
		double newY = Math.random() * (0.95 - 0.05) + 0.05;
		this.x = newX;
		this.y = newY;
		}
	}
	public double area() {
		double r2 = Math.pow(this.halfLength, 2);
		double area = Math.PI * r2;
		return area;
		
	}
	public boolean ApTouchingShaft(List<Snake> list) {
		boolean change = false;
		for(int i = 1; i < list.size(); i ++) {
			double xDiff = this.getApX() - list.get(i).getX();
			double yDiff = this.getApY() - list.get(i).getY();
			double dist = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff , 2));
			double distIncHalf = dist - this.halfLength - list.get(i).getHalfLength();
			if(distIncHalf <= 0) {
				change = true;
				break;
			}
			else {
				change = false;
			}
		}
		return change;
	}
}
