package gameobject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.Resource;

public class EnemiesManager {
	
	private BufferedImage barrel1;
	private BufferedImage barrel2;
	private Random rand;
	
	private List<enemy> enemies;
	private MainCharacter mainCharacter;
	
	public EnemiesManager(MainCharacter mainCharacter) {
		rand = new Random();
		barrel1 = Resource.getResouceImage("data/barrel1.png");
		barrel2 = Resource.getResouceImage("data/barrel2.png");
		enemies = new ArrayList<enemy>();
		this.mainCharacter = mainCharacter;
		enemies.add(createEnemy());
	}
	
	public void update() {
		for(enemy e : enemies) {
			e.update();
		}
		enemy enemy = enemies.get(0);
		if(enemy.isOutOfScreen()) {
			mainCharacter.scoreUpSound();
			enemies.clear();
			enemies.add(createEnemy());
		}
	}
	
	public void draw(Graphics g) {
		for(enemy e : enemies) {
			e.draw(g);
		}
	}
	
	private enemy createEnemy() {
		
		int type = rand.nextInt(2);
		if(type == 0) {
			return new Barrel(mainCharacter, 800, barrel1.getWidth() - 10, barrel1.getHeight() - 10, barrel1);
		} else {
			return new Barrel(mainCharacter, 800, barrel2.getWidth() - 10, barrel2.getHeight() - 10, barrel2);
		}
	}
	
	public boolean isCollision() {
		for(enemy e : enemies) {
			if (mainCharacter.getBound().intersects(e.getBound())) {
				return true;
			}
		}
		return false;
	}
	
	public void reset() {
		enemies.clear();
		enemies.add(createEnemy());
	}
	
}
