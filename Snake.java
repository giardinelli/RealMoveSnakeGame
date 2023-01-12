package gameProperties;

import java.awt.Color;
import java.util.*;

import edu.princeton.cs.introcs.StdDraw;

public class Snake {
	
	private double x;
	private double y;
	private double halfLength = 0.0225;
	
	public Snake(double x ,  double y) {
		this.x = x;
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getHalfLength() {
		return halfLength;
	}
	public void setX(double xVal){
		x = xVal;
	}
	public void setY(double yVal){
		y = yVal;
	}
	public void draw() {
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.filledSquare(x, y, halfLength);
	}
	public boolean isTouchingShaft(Snake obj) {
		// Math.sqrt(Math.pow((x2 - x1) , 2) + Math.pow((y2 - y1) , 2))
		double xDiff = this.getX() - obj.getX();
		double yDiff = this.getY() - obj.getY();
		double dist = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff , 2));
		double distIncHalf = dist - this.halfLength - obj.halfLength;
		if(distIncHalf <= 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean closeEnough(Snake obj) {
		// Math.sqrt(Math.pow((x2 - x1) , 2) + Math.pow((y2 - y1) , 2))
		double xDiff = this.getX() - obj.getX();
		double yDiff = this.getY() - obj.getY();
		double dist = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff , 2));
		double distIncHalf = dist - this.halfLength - obj.halfLength;
		if(distIncHalf >= halfLength / 100) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isTouchingApple(Apple obj) {
		// Math.sqrt(Math.pow((x2 - x1) , 2) + Math.pow((y2 - y1) , 2))
				double xDiff = this.getX() - obj.getApX();
				double yDiff = this.getY() - obj.getApY();
				double dist = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff , 2));
				double distIncHalf = dist - this.halfLength - obj.getHalfLength();
				if(distIncHalf <= 0) {
					return true;
				}
				else {
					return false;
				}
	}
	
	public boolean wallHit() {
		if(this.getX() >=1-halfLength || this.getX() <= 0+halfLength || this.getY() <= 0+halfLength || this.getY() >= 1-halfLength) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void moveToward(double xOther, double yOther, double amount) {
		double xVector = xOther - getX();
		double yVector = yOther - getY();
		double angle = Math.atan2(yVector, xVector);
		double xAmount = amount * Math.cos(angle);
		double yAmount = amount * Math.sin(angle);

		this.x += xAmount;
		this.y += yAmount;

	}

	/**
	 * @param other  the other Entity
	 * @param amount the amount to move toward the other Entity.
	 */
	public void moveToward(Snake other, double amount) {
		moveToward(other.getX(), other.getY(), amount);
	}
	
	public void moveTowardForHead(double xOther, double yOther, double amount) {
		double xVector = xOther - getX();
		double yVector = yOther - getY();
		double angle = Math.atan2(yVector, xVector);
		double xAmount = amount * Math.cos(angle);
		double yAmount = amount * Math.sin(angle);

		this.x += xAmount;
		this.y += yAmount;

	}
	
	
	public List<Character> update(List<Snake> snakeList , double deltaTime, char got, List<Character> moveList) {
		double move = .3 * deltaTime;
		if(this.equals(snakeList.get(0))) {
			if(got == 'w') {
				moveTowardForHead(this.x, 1 , move);
				moveList.add('w');
				return moveList;
			}
			else if(got == 'a') {
				moveTowardForHead(0, this.y , move);
				moveList.add('a');
				return moveList;
			}
			else if(got == 's') {
				moveTowardForHead(this.x, 0 , move);
				moveList.add('s');
				return moveList;
			}
			else if(got == 'd') {
				moveTowardForHead(1, this.y , move);
				moveList.add('d');
				return moveList;
			}
			else {
				char c = moveList.get(moveList.size() - 1);
				if(c == 'w') {
					moveTowardForHead(this.x, 1 , move);
					return moveList;
				}
				else if(c == 'a') {
					moveTowardForHead(0, this.y , move);
					return moveList;
				}
				else if(c == 's') {
					moveTowardForHead(this.x, 0 , move);
					return moveList;
				}
				else if(c == 'd') {
					moveTowardForHead(1, this.y , move);
					return moveList;
				}
				else {
					return moveList;
				}
			}
		}
		else {
			//follow direction of object in front of it
			for(int i = 0; i < snakeList.size(); i++) {
				if(this.equals(snakeList.get(i))) {
					int indexBefore = i - 1;
					if(snakeList.get(indexBefore).closeEnough(this)) {
						moveToward(snakeList.get(indexBefore) , move);
					break;
					}
					break;
					
				}
					
			}
			return moveList;
			
			}
		
		
	}
	
}