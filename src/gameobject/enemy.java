package gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class enemy {
	public abstract void update();
	public abstract void draw(Graphics g);
	public abstract Rectangle getBound();
	public abstract boolean isOutOfScreen();
}
