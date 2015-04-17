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
	private Animation sprite, left, right;
	float x = 25f, y = 25f;
	public SimpleSlickGame(String gamename)
	{
		super(gamename);
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		Image [] movementLeft = {new Image ("wizHor.png"), new Image("wizHor2.png")};
		Image [] movementRight = {new Image ("wiz.png"), new Image("wiz2.png")};
		int [] duration = {300, 300};
		left = new Animation(movementLeft,duration,false);
		right = new Animation(movementRight,duration,false);
		sprite = right;
	}

	
	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		Input input = gc.getInput();
		if (input.isKeyDown(Input.KEY_UP))
		{
		    sprite = right;
		    sprite.update(i);
		    // The lower the delta the slowest the sprite will animate.
		    y -= i * 0.1f;
		}
		else if (input.isKeyDown(Input.KEY_DOWN))
		{
		    sprite = right;
		    sprite.update(i);
		    y += i * 0.1f;
		}
		else if (input.isKeyDown(Input.KEY_LEFT))
		{
		    sprite = left;
		    sprite.update(i);
		    x -= i * 0.1f;
		}
		else if (input.isKeyDown(Input.KEY_RIGHT))
		{
		    sprite = right;
		    sprite.update(i);
		    x += i * 0.1f;
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		sprite.draw((int)x,(int)y);
		
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SimpleSlickGame("Simple Slick Game"));
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
			
			
		}
		catch (SlickException ex)
		{
			Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}