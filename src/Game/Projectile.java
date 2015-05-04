package Game;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;






import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;



public class Projectile {
	int[] dir;
	public int xPos;
	public int yPos;
	public int wepEffect;
	public int movementSpeed = 10;
	Animation proj;
	public int width = 73;
	public int height = 34;
	public boolean collides = false;
	Projectile(int xPos, int yPos, String imgOne, String imgTwo, int dur1, int dur2, int[] direction, int wepEffect) throws SlickException{
		
	this.xPos = new Integer(xPos) ;
	this.yPos = new Integer(yPos) ;
	this.wepEffect = new Integer(wepEffect);
	this.dir = new int[] {new Integer(direction[0]), new Integer(direction[1])};
	Image[] projImg = { new Image(imgOne), new Image(imgTwo) };
	int [] duration = {dur1, dur2}; 
	proj = new Animation(projImg, duration, false);
	
	}
	
		
		
	
	
}
	
	

