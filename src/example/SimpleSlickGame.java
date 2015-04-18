package example;
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

public class SimpleSlickGame extends BasicGame
{
	protected Animation fireball, sprite, left, right, fire;
	float x = 25f, y = 25f;
	boolean lookLeft = false, spawn = false, fw = false, bw = false, up = false, down = false, isActive = false;
	float tempX, tempY;
	
	public SimpleSlickGame(String gamename)
	{
		super(gamename);
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		Image [] movementLeft = {new Image ("wizHor.png"), new Image("wizHor2.png")};
		Image [] movementRight = {new Image ("wiz.png"), new Image("wiz2.png")};
		Image [] FB = {new Image("FB.png"), new Image("FB.png")};
		int [] duration = {300, 300}; 
		fire = new Animation(FB, duration,false);
		left = new Animation(movementLeft,duration,false);
		right = new Animation(movementRight,duration,false);
		fireball = fire;
		sprite = right;
	}

	
	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		
	
		Input input = gc.getInput();
		if (input.isKeyDown(Input.KEY_SPACE)) {
		if(isActive==false){
			tempX = x;
			tempY = y;
		}
			isActive = true;
			spawn = true;
			fireball.update(i);
			fireball = fire;
		
			
			
		}
				
		if (input.isKeyDown(Input.KEY_UP))
		{
			if(isActive==false){
				up = true;
				fw = false;
				bw = false;
				down = false;
				}
			if(lookLeft == true){
				sprite = left;
			}
			else{
		    sprite = right;
			}
		    sprite.update(i);
		    // The lower the delta the slowest the sprite will animate.
		    y -= i * 0.5f;
		}
		else if (input.isKeyDown(Input.KEY_DOWN))
		{
			if(isActive==false){
				up = false;
				fw = false;
				bw = false;
				down = true;
				}
			if(lookLeft == true){
				sprite = left;
			}
			else{
		    sprite = right;
			}
		    
		    sprite.update(i);
		    y += i * 0.5f;
		}
		else if (input.isKeyDown(Input.KEY_LEFT))
		{
			if(isActive==false){
				up = false;
				fw = false;
				bw = true;
				down = false;
				}
			lookLeft = true;
		    sprite = left;
		    sprite.update(i);
		    x -= i * 0.5f;
		}
		else if (input.isKeyDown(Input.KEY_RIGHT))
		{
		
			if(isActive==false){
			up = false;
			fw = true;
			bw = false;
			down = false;
			}
			lookLeft = false;
		    sprite = right;
		    sprite.update(i);
		    x += i * 0.5f;
		  
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		
		sprite.draw((int)x,(int)y);
		if(spawn == true){
			if(fw == true)
			tempX+=2;
			if(bw == true)
			tempX-=2;
			if(up == true)
			tempY-=2;
			if(down == true)
			tempY+=2;
		
			
			
		fireball.draw((int)tempX,(int)tempY);
		isActive = true;
		
		if(tempX > 1200 || tempX < 0){
		spawn = false;
		isActive = false;
		}
		if(tempY > 860 || tempY < 0){
		spawn = false;
		isActive = false;
		
		}
		
		}
		
		
		
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SimpleSlickGame("Simple Slick Game"));
			appgc.setDisplayMode(1200, 860, false);
			appgc.start();
			
			
		}
		catch (SlickException ex)
		{
			Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}