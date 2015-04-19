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


public class Player {
	static int movementSpeed = 2;
	public int xPos;
	public int yPos;
	int[] direction = {0,0};
	int[] lastDir = {1,0};
	Image[] moveLeft;
	Image[] moveRight;
	int [] duration = {300,300};
	Animation playerAnim1,playerAnim2, active;
	
	
Player(int xPos, int yPos, Image[] images){
	
	moveLeft = new Image[] {images[0], images[1]};
	moveRight = new Image[] {images[2], images[3]};
	
		this.xPos = xPos;
		this.yPos = yPos;
		playerAnim1 = new Animation(moveRight, duration, false);
		playerAnim2 = new Animation(moveLeft, duration, false);
		active = playerAnim1;
			
}

public void fire(String image1, String image2, int[] direction) throws SlickException{
	MainClass.projectiles.add(new Projectile(xPos, yPos, image1, image2, 300,300, lastDir));
}



}
