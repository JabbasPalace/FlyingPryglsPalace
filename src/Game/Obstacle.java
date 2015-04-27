package Game;
import java.util.Random;
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
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Obstacle {
public int xPos, yPos;
int[] dur={300,300};
public int width = 34; 
public int height = 73;
public boolean collides = false;

Animation obst;

Obstacle(int xPos, int yPos, String img1, String img2, int dur1, int dur2)throws SlickException{
	Image[] anim = { new Image (img1), new Image (img2) };
	obst = new Animation(anim,dur,false);
	this.xPos = randInt(0,1200);
	this.yPos = randInt(0,800);
	
}


public int randInt(int min, int max) {
	  
    Random rand = new Random();
    int randomNum = rand.nextInt((max - min) + 1) + min;
    return randomNum;
}

}
