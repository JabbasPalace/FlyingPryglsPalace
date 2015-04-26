package Game;

import java.util.ArrayList;
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




public class MainClass extends BasicGame  {

	public MainClass(String wizardShit) throws SlickException {
		super(wizardShit);
	}
	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new MainClass("Wizard Game"));
			appgc.setDisplayMode(1200, 860, false);
			appgc.setVSync(true);
			appgc.start();
			
			
		}
		catch (SlickException ex)
		{
			Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
		
		
        public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>(); 
        Player player1, player2;
       
        
		@Override
		public void init(GameContainer gc) throws SlickException {
			player1 = new Player(25,25, new Image[] {new Image("wizHor.png"), new Image("wizHor2.png"),new Image("wiz.png"),new Image("wiz2.png")});
			player2 = new Player(1200, 800, new Image[]{new Image("woBin.png"), new Image("woBin2.png"),new Image("woBin.png"), new Image("woBin2.png")});
		}

		
		@Override
		public void update(GameContainer gc, int i) throws SlickException {
			Input input = gc.getInput();
			if (input.isKeyDown(Input.KEY_UP)){
				player1.direction[1] = -1;
				player1.lastDir[1] = -1;
				player1.active.update(i);
				if (!input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT)){
					player1.lastDir[0] = 0;
					
					}
			}
			if (input.isKeyDown(Input.KEY_DOWN)){
				player1.direction[1] = 1;
				player1.lastDir[1] = 1;
				player1.active.update(i);
				if (!input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT)){
					player1.lastDir[0] = 0;	
					}
			}
	        if (!input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_UP)) {
			    player1.direction[1] = 0;	
			}
			if (input.isKeyDown(Input.KEY_LEFT)){
				player1.direction[0] = -1;
				player1.lastDir[0] = -1;
				player1.active = player1.playerAnim2;
				player1.active.update(i);
				 if (!input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_UP)) {
					    player1.lastDir[1] = 0;	
					}
				
			}
			if (input.isKeyDown(Input.KEY_RIGHT)){
				player1.direction[0] = 1;
				player1.lastDir[0] = 1;
				player1.active = player1.playerAnim1;
				player1.active.update(i);
				  if (!input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_UP)) {
					    player1.lastDir[1] = 0;	
					}
			}
			if (!input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT)){
				player1.direction[0] = 0;	
				}
		
			
			if (input.isKeyPressed(Input.KEY_RCONTROL)){
				
				//Fireball down
				if(player1.direction[0] == 0 && player1.direction[1] == 1)
				player1.fire("FBd.png","FBd1.png", player1.direction);
												
				//Fireball right down
				if(player1.direction[0] == 1 && player1.direction[1] == 1)
					player1.fire("FBrd.png","FBrd1.png", player1.direction);
			
				//Fireball right
				if(player1.direction[0] == 1 && player1.direction[1] == 0)
					player1.fire("FBr.png","FBr1.png", player1.direction);
				
				//Fireball left
				if(player1.direction[0] == -1 && player1.direction[1] == 0)
					player1.fire("FBl.png","FBl1.png", player1.direction);
				
				//Fireball left down
				if(player1.direction[0] == -1 && player1.direction[1] == 1)
					player1.fire("FBld.png","FBld1.png", player1.direction);
				
				//Fireball up
				if(player1.direction[0] == 0 && player1.direction[1] == -1)
					player1.fire("FBu.png","FBu1.png", player1.direction);
				
				//Fireball right up
				if(player1.direction[0] == 1 && player1.direction[1] == -1)
					player1.fire("FBru.png","FBru1.png", player1.direction);
				
				//Fireball left up
				if(player1.direction[0] == -1 && player1.direction[1] == -1)
					player1.fire("FBlu.png","FBlu1.png", player1.direction);
														
				
					}
			if (input.isKeyDown(Input.KEY_W)){
				player2.direction[1] = -1;
				player2.lastDir[1] = -1;
				player2.active.update(i);
				if (!input.isKeyDown(Input.KEY_A) && !input.isKeyDown(Input.KEY_RIGHT)){
					player2.lastDir[0] = 0;
					
					}
			}
			if (input.isKeyDown(Input.KEY_S)){
				player2.direction[1] = 1;
				player2.lastDir[1] = 1;
				player2.active.update(i);
				if (!input.isKeyDown(Input.KEY_A) && !input.isKeyDown(Input.KEY_D)){
					player2.lastDir[0] = 0;	
					}
			}
	        if (!input.isKeyDown(Input.KEY_S) && !input.isKeyDown(Input.KEY_W)) {
			    player2.direction[1] = 0;	
			}
			if (input.isKeyDown(Input.KEY_A)){
				player2.direction[0] = -1;
				player2.lastDir[0] = -1;
				player2.active = player2.playerAnim2;
				player2.active.update(i);
				 if (!input.isKeyDown(Input.KEY_S) && !input.isKeyDown(Input.KEY_W)) {
					    player2.lastDir[1] = 0;	
					}
				
			}
			if (input.isKeyDown(Input.KEY_D)){
				player2.direction[0] = 1;
				player2.lastDir[0] = 1;
				player2.active = player2.playerAnim1;
				player2.active.update(i);
				  if (!input.isKeyDown(Input.KEY_S) && !input.isKeyDown(Input.KEY_W)) {
					    player2.lastDir[1] = 0;	
					}
			}
			if (!input.isKeyDown(Input.KEY_A) && !input.isKeyDown(Input.KEY_D)){
				player2.direction[0] = 0;	
				}
		
			
			if (input.isKeyPressed(Input.KEY_SPACE)){
				
				//Fireball down
				if(player2.direction[0] == 0 && player2.direction[1] == 1)
				player2.fire("FBd.png","FBd1.png", player2.direction);
												
				//Fireball right down
				if(player2.direction[0] == 1 && player2.direction[1] == 1)
					player2.fire("FBrd.png","FBrd1.png", player2.direction);
			
				//Fireball right
				if(player2.direction[0] == 1 && player2.direction[1] == 0)
					player2.fire("FBr.png","FBr1.png", player2.direction);
				
				//Fireball left
				if(player2.direction[0] == -1 && player2.direction[1] == 0)
					player2.fire("FBl.png","FBl1.png", player2.direction);
				
				//Fireball left down
				if(player2.direction[0] == -1 && player2.direction[1] == 1)
					player2.fire("FBld.png","FBld1.png", player2.direction);
				
				//Fireball up
				if(player2.direction[0] == 0 && player2.direction[1] == -1)
					player2.fire("FBu.png","FBu1.png", player2.direction);
				
				//Fireball right up
				if(player2.direction[0] == 1 && player2.direction[1] == -1)
					player2.fire("FBru.png","FBru1.png", player2.direction);
				
				//Fireball left up
				if(player2.direction[0] == -1 && player2.direction[1] == -1)
					player2.fire("FBlu.png","FBlu1.png", player2.direction);
														
				
					}
			if(projectiles != null){
				for(int k = 0; k < projectiles.size();k++){
				projectiles.get(k).proj.update(i);
				}
			}
			
			player1.xPos += player1.direction[0] * player1.movementSpeed;
			player1.yPos += player1.direction[1] * player1.movementSpeed;
			player2.xPos += player2.direction[0] * player2.movementSpeed;
			player2.yPos += player2.direction[1] * player2.movementSpeed;
			
			for (int j = 0; j < projectiles.size(); j++){
				projectiles.get(j).xPos += projectiles.get(j).dir[0] * projectiles.get(j).movementSpeed;
				projectiles.get(j).yPos += projectiles.get(j).dir[1] * projectiles.get(j).movementSpeed;
				}
		}
	

		@Override
		public void render(GameContainer gc, Graphics g) throws SlickException
		{
			
			player1.active.draw(player1.xPos,player1.yPos);
			player2.active.draw(player2.xPos,player2.yPos);
			
			for (int i = 0; i < projectiles.size(); i++){
				Projectile currentProj = projectiles.get(i);
				if(player1.active == player1.playerAnim1 || player2.active == player2.playerAnim1)
				currentProj.proj.draw(currentProj.xPos+50, currentProj.yPos+50);
				else
					currentProj.proj.draw(currentProj.xPos-50, currentProj.yPos+50);
				if (currentProj.xPos > 1200 || currentProj.xPos < 0 || currentProj.yPos > 800 || currentProj.yPos < 0){
					projectiles.remove(i);
					
				}
			}
	
			
			}
			
			
			
			
		

	
	}