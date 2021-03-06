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
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;


public class Player {
	public int movementSpeed = 4;
	public int xPos;
	public int yPos;
	int[] direction = {0,0};
	int[] lastDir = {1,0};
	Image[] moveLeft;
	Image[] moveRight;
	Image[] d;
	int [] duration = {300,300};
	Animation playerAnim1,playerAnim2, death, none, active;
	public int health = 100;
	public int[] activewep = {0,0 };
	public boolean collision = false, alive = true;
	int width = 50;
	int height = 55;
	

	
//Player class constructor	
Player(int xPos, int yPos, Image[] images){
	
	moveLeft = new Image[] {images[0], images[1]};
	moveRight = new Image[] {images[2], images[3]};
	d = new Image[] {images[4],images[5]};
	
		this.xPos = xPos;
		this.yPos = yPos;
		playerAnim1 = new Animation(moveRight, duration, false);
		playerAnim2 = new Animation(moveLeft, duration, false);
		death  = new Animation(d, duration, false);
		active = playerAnim1;
	
	
			
}



//Method for gaining movementspeed when taking a powerup.
public void powerUp(){
long Ntime = System.currentTimeMillis();
if(((MainClass.lastTime/100) + (Ntime/100))%4 == 0 ){
movementSpeed += 5;

}


}


//Method for firing projectiles for player 1
public void fire(String image1, String image2, int[] direction, int wepEffect) throws SlickException{
	MainClass.projectiles.add(new Projectile(this.xPos, this.yPos, image1, image2, 300,300, lastDir, wepEffect));
}
//Method for firing projectiles for player 2
public void fire2(String image1, String image2, int[] direction, int wepEffect) throws SlickException{
	MainClass.projectiles2.add(new Projectile(this.xPos, this.yPos, image1, image2, 300,300, lastDir, wepEffect));
}




}
