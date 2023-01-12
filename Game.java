package gameProperties;

import java.awt.Color;
import java.io.File;
import java.util.*;

import edu.princeton.cs.introcs.StdDraw;
import support.cse131.Timing;

public class Game {

	private List<Snake> snake;
	private List<Character> moves;

	public Game() {
		this.snake = new ArrayList<>();
		this.moves = new ArrayList<>();
	}

	public Game(List<Snake> snake, List<Character> moves) {
		this.snake = snake;
		this.moves = moves;
	}

	public void addSnake(Snake obj) {
		snake.add(obj);
	}

	public void addMove(char c) {
		moves.add(c);
	}

	public List<Snake> getSList() {
		return snake;
	}

	public List<Character> getMList() {
		return moves;
	}

	public void draw(Apple ap) {
		StdDraw.clear();

		StdDraw.setPenColor(Color.black);
		StdDraw.filledRectangle(.5, .5, 1, 1);

		ap.ApDraw();
		for (Snake entity : getSList()) {
			entity.draw();
		}

		StdDraw.show(); // commit deferred drawing as a result of enabling double buffering
	}

	public char lastMove() {
		char c = moves.get(moves.size() - 1);
		return c;
	}

	public void touchApple(Apple ap) {
//		int random =(int) Math.round(Math.random() * 10);	

		if (snake.size() == 1) {
			ap.randomLoc(snake);
			double newX = snake.get(snake.size() - 1).getX() - (snake.get(snake.size() - 1).getHalfLength() * 2);
			double newY = snake.get(snake.size() - 1).getY();
			Snake sZ = new Snake(newX, newY);
			snake.add(sZ);
		} else if (snake.get(snake.size() - 2).getX() > snake.get(snake.size() - 1).getX()
				&& snake.get(snake.size() - 2).getY() > snake.get(snake.size() - 1).getY()) {
			ap.randomLoc(snake);
			double newY = snake.get(snake.size() - 1).getY() - (snake.get(snake.size() - 1).getHalfLength() * 2);
			double newX = snake.get(snake.size() - 1).getX();
			Snake sZ = new Snake(newX, newY);
			snake.add(sZ);

		} else if (snake.get(snake.size() - 2).getX() > snake.get(snake.size() - 1).getX()
				&& snake.get(snake.size() - 2).getY() < snake.get(snake.size() - 1).getY()) {
			ap.randomLoc(snake);
			double newY = snake.get(snake.size() - 1).getY() - (snake.get(snake.size() - 1).getHalfLength() * 2);
			double newX = snake.get(snake.size() - 1).getX();
			Snake sZ = new Snake(newX, newY);
			snake.add(sZ);
		} else if (snake.get(snake.size() - 2).getX() < snake.get(snake.size() - 1).getX()
				&& snake.get(snake.size() - 2).getY() < snake.get(snake.size() - 1).getY()) {
			ap.randomLoc(snake);
			double newY = snake.get(snake.size() - 1).getY() + (snake.get(snake.size() - 1).getHalfLength() * 2);
			double newX = snake.get(snake.size() - 1).getX();
			Snake sZ = new Snake(newX, newY);
			snake.add(sZ);
		}
		else if (snake.get(snake.size() - 2).getX() < snake.get(snake.size() - 1).getX()
				&& snake.get(snake.size() - 2).getY() > snake.get(snake.size() - 1).getY()) {
			ap.randomLoc(snake);
			double newY = snake.get(snake.size() - 1).getY() - (snake.get(snake.size() - 1).getHalfLength() * 2);
			double newX = snake.get(snake.size() - 1).getX();
			Snake sZ = new Snake(newX, newY);
			snake.add(sZ);
		} else if (snake.get(snake.size() - 2).getY() > snake.get(snake.size() - 1).getY()
				&& snake.get(snake.size() - 2).getX() > snake.get(snake.size() - 1).getX()) {
			ap.randomLoc(snake);
			double newX = snake.get(snake.size() - 1).getX() - (snake.get(snake.size() - 1).getHalfLength() * 2);
			double newY = snake.get(snake.size() - 1).getY();
			Snake sZ = new Snake(newX, newY);
			snake.add(sZ);
		} else if (snake.get(snake.size() - 2).getY() < snake.get(snake.size() - 1).getY()
				&& snake.get(snake.size() - 2).getX() < snake.get(snake.size() - 1).getX()) {
			ap.randomLoc(snake);
			double newX = snake.get(snake.size() - 1).getX() + (snake.get(snake.size() - 1).getHalfLength() * 2);
			double newY = snake.get(snake.size() - 1).getY();
			Snake sZ = new Snake(newX, newY);
			snake.add(sZ);
		}

	}

	public void update(double deltaTime, Apple ap, char got) {
		for (int i = 0; i < snake.size(); i++) {
			snake.get(i).update(snake, deltaTime, got, moves);
		}
	}

	public boolean didSnakeHitWall() {
		if (snake.get(0).wallHit()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean didSnakeHitSnake() {
		for (int i = 2; i < snake.size(); i++) {
			if (snake.get(0).getX() == snake.get(i).getX() && snake.get(0).getY() == snake.get(i).getY()) {
				return true;
			}
		}
		return false;
	}

	public boolean isAppleUnderSnake(Apple ap) {
		for (int i = 1; i < snake.size(); i++) {
			if (snake.get(i).getX() == ap.getApX() && snake.get(i).getY() == ap.getApY()) {
				return true;
			}
		}
		return false;
	}

	public void addToMove(char a) {
		moves.add(a);
	}

	public char repeatLastMove() {
		char s = moves.get(moves.size() - 1);
		return s;
	}

	public static void main(String[] args) {
		StdDraw.enableDoubleBuffering();
		
		
		
		while (!StdDraw.hasNextKeyTyped()) {
		
		StdDraw.clear();
		StdDraw.setPenColor(Color.green);
		StdDraw.filledSquare(.5, .5, 1);
		
		
		
		StdDraw.setPenColor(Color.black);
		StdDraw.text(.5, .85, "Realistic Movement Snake Game");
		StdDraw.text(.5, .8, "By Matt Giardinelli");
		
		
		StdDraw.text(.5, .65, "Use the 'wasd' keys to controll the snakes movement");
		StdDraw.text(.5, .58, "w - up");
		StdDraw.text(.5, .48, "a - left");
		StdDraw.text(.5, .38, "s - down");
		StdDraw.text(.5, .28, "d - right");
		
		StdDraw.text(.5, .15, "Press d to start");
		
		StdDraw.show();
		
		StdDraw.pause(10);
		}
		
		StdDraw.enableDoubleBuffering(); // reduce unpleasant drawing artifacts, speed things up
		double prevTime = Timing.getCurrentTimeInSeconds();

		Snake s1 = new Snake(.2, .5);
		Game game = new Game();
		game.addSnake(s1);
		game.addMove('d');

		Apple ap = new Apple(.85, .5);
		int appleCount = 0;
		
		while (true) {
			double currTime = Timing.getCurrentTimeInSeconds();

			char key = '\0';
			// Check if the user has typed a key
			if (StdDraw.hasNextKeyTyped()) {
				// If the user has typed a key, store it in a variable and add it to the list
				key = StdDraw.nextKeyTyped();
				game.addMove(key);
			}
			double deltaTime = currTime - prevTime;
			if (deltaTime > 0.0) {
				game.update(deltaTime, ap, key);
				game.draw(ap);

				// checks if snake hits a wall
				if (game.snake.get(0).wallHit()) {
					break;
				}
				// checks if snake head hits another part of snake
				boolean check = false;
				for (int i = 2; i < game.snake.size(); i++) {
					if (game.snake.get(i).isTouchingShaft(game.snake.get(0))) {
						check = true;
						break;
					}
				}
				if (check == true) {
					break;
				}
				// checks if the snake head hits an apple
				if (game.snake.get(0).isTouchingApple(ap)) {
					game.touchApple(ap);
					appleCount += 1;
				}
			}
			StdDraw.pause(1);
			prevTime = currTime;
		}
		
		StdDraw.pause(1000);
		StdDraw.clear();
		StdDraw.setPenColor(Color.green);
		StdDraw.filledSquare(.5, .5, 1);
		
		Apple fiAp = new Apple(.41 , .5);
		fiAp.ApDraw();
		StdDraw.text(.52, .498, "'s collected");
		
		StdDraw.setPenColor(Color.black);
		StdDraw.text(.5, .65, "GOOD GAME!");
		StdDraw.text(.5, .6, "Thanks for playing");
		String sAppleCount = "" + appleCount;
		StdDraw.text(.5, .43, sAppleCount);
		
		StdDraw.text(.255, .05, "Realistic Movement Snake Game");
		StdDraw.text(.18, .02, "By Matthew Giardinelli");
		
		StdDraw.show();
		
	}
}