package Game;



import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;











import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Font;
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
        public static ArrayList<Projectile> projectiles2 = new ArrayList<Projectile>();
        public static ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
        Player player1, player2;
        
       
        
		@Override
		public void init(GameContainer gc) throws SlickException {
			player1 = new Player(25,25, new Image[] {new Image("wizHor.png"), new Image("wizHor2.png"),new Image("wiz.png"),new Image("wiz2.png"),new Image("b.png"),new Image("b1.png")});
			player2 = new Player(1100, 730, new Image[]{new Image("woBin.png"), new Image("woBin2.png"),new Image("woBin.png"), new Image("woBin2.png"),new Image("b.png"),new Image("b1.png")});
			
		}

		
		@Override
		public void update(GameContainer gc, int i) throws SlickException {
			
			if(obstacles != null && obstacles.size() < 10){
				
				spawn("Fire.png","Fire1.png");
				
			}
				
			Input input = gc.getInput();
			if(input.isKeyPressed(Input.KEY_NUMPAD1))
				player1.activewep[0] = 0;
			
			if(input.isKeyPressed(Input.KEY_NUMPAD2))
				player1.activewep[0] = 1;
			
			if(input.isKeyPressed(Input.KEY_1))
				player2.activewep[0] = 0;
			
			if(input.isKeyPressed(Input.KEY_2))
				player2.activewep[0] = 1;
			
			if (input.isKeyDown(Input.KEY_UP)){
				player1.direction[1] = -1;
				player1.lastDir[1] = -1;
				player1.active.update(i);
				if(player1.yPos < -30 ){
					player1.direction[1] = 1;
				}
				if (!input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT)){
					player1.lastDir[0] = 0;
					
					}
			}
			if (input.isKeyDown(Input.KEY_DOWN)){
				player1.direction[1] = 1;
				player1.lastDir[1] = 1;
				player1.active.update(i);
				if(player1.yPos > 765 ){
					player1.direction[1] = -1;
				}
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
				if(player1.xPos < -25 ){
					player1.direction[0] = 1;
				}
				 if (!input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_UP)) {
					    player1.lastDir[1] = 0;	
					}
				
			}
			if (input.isKeyDown(Input.KEY_RIGHT)){
				player1.direction[0] = 1;
				player1.lastDir[0] = 1;
				player1.active = player1.playerAnim1;
				player1.active.update(i);
				if(player1.xPos > 1110 ){
					player1.direction[0] = -1;
				}
				  if (!input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_UP)) {
					    player1.lastDir[1] = 0;	
					}
			}
			if (!input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT)){
				player1.direction[0] = 0;	
				}
		
			
			if (input.isKeyPressed(Input.KEY_RCONTROL)){
				if(player1.activewep[0] == 1){
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
				else if (player1.activewep[0] == 0){
					player1.fire("sb.png", "sb1.png", player1.direction);
				}
				
					}
			if (input.isKeyDown(Input.KEY_W)){
				player2.direction[1] = -1;
				player2.lastDir[1] = -1;
				player2.active.update(i);
				if(player2.yPos < -30 ){
					player1.direction[1] = 1;
				}
				if (!input.isKeyDown(Input.KEY_A) && !input.isKeyDown(Input.KEY_RIGHT)){
					player2.lastDir[0] = 0;
					
					}
			}
			if (input.isKeyDown(Input.KEY_S)){
				player2.direction[1] = 1;
				player2.lastDir[1] = 1;
				player2.active.update(i);
				if(player2.yPos > 765 ){
					player2.direction[1] = -1;
				}
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
				if(player2.xPos < -25 ){
					player2.direction[0] = 1;
				}
				 if (!input.isKeyDown(Input.KEY_S) && !input.isKeyDown(Input.KEY_W)) {
					    player2.lastDir[1] = 0;	
					}
				
			}
			if (input.isKeyDown(Input.KEY_D)){
				player2.direction[0] = 1;
				player2.lastDir[0] = 1;
				player2.active = player2.playerAnim1;
				player2.active.update(i);
				if(player2.xPos > 1110 ){
					player2.direction[0] = -1;
				}
				  if (!input.isKeyDown(Input.KEY_S) && !input.isKeyDown(Input.KEY_W)) {
					    player2.lastDir[1] = 0;	
					}
			}
			if (!input.isKeyDown(Input.KEY_A) && !input.isKeyDown(Input.KEY_D)){
				player2.direction[0] = 0;	
				}
		
			
			if (input.isKeyPressed(Input.KEY_SPACE)){
				if(player2.activewep[0] == 1){
				//Fireball down
				if(player2.direction[0] == 0 && player2.direction[1] == 1)
				player2.fire2("FBd.png","FBd1.png", player2.direction);
												
				//Fireball right down
				if(player2.direction[0] == 1 && player2.direction[1] == 1)
					player2.fire2("FBrd.png","FBrd1.png", player2.direction);
			
				//Fireball right
				if(player2.direction[0] == 1 && player2.direction[1] == 0)
					player2.fire2("FBr.png","FBr1.png", player2.direction);
				
				//Fireball left
				if(player2.direction[0] == -1 && player2.direction[1] == 0)
					player2.fire2("FBl.png","FBl1.png", player2.direction);
				
				//Fireball left down
				if(player2.direction[0] == -1 && player2.direction[1] == 1)
					player2.fire2("FBld.png","FBld1.png", player2.direction);
				
				//Fireball up
				if(player2.direction[0] == 0 && player2.direction[1] == -1)
					player2.fire2("FBu.png","FBu1.png", player2.direction);
				
				//Fireball right up
				if(player2.direction[0] == 1 && player2.direction[1] == -1)
					player2.fire2("FBru.png","FBru1.png", player2.direction);
				
				//Fireball left up
				if(player2.direction[0] == -1 && player2.direction[1] == -1)
					player2.fire2("FBlu.png","FBlu1.png", player2.direction);
				}									
				else if(player2.activewep[0] == 0){
					player2.fire2("sb.png", "sb1.png", player2.direction);
				}
					
			}
			if(projectiles != null){
				for(int k = 0; k < projectiles.size();k++){
				projectiles.get(k).proj.update(i);
				}
			}
				
			if(projectiles2 != null){
				for(int k = 0; k < projectiles2.size();k++){
				projectiles2.get(k).proj.update(i);
				}
			}
			
			
			if(obstacles != null){
				for(int j = 0; j < obstacles.size(); j++){
					obstacles.get(j).obst.update(i);
				}
			}
			// AT DEATH
			if(player1.health < 1){
				player1.active = player1.death;
				player1.active.update(i);
			}
			if(player2.health < 1){
				player2.active = player2.death;
				player2.active.update(i);
			}
			
			
			player1.xPos += player1.direction[0] * player1.movementSpeed;
			player1.yPos += player1.direction[1] * player1.movementSpeed;
			player2.xPos += player2.direction[0] * player2.movementSpeed;
			player2.yPos += player2.direction[1] * player2.movementSpeed;
			
			for (int j = 0; j < projectiles.size(); j++){
				projectiles.get(j).xPos += projectiles.get(j).dir[0] * projectiles.get(j).movementSpeed;
				projectiles.get(j).yPos += projectiles.get(j).dir[1] * projectiles.get(j).movementSpeed;
				}
			for (int j = 0; j < projectiles2.size(); j++){
				projectiles2.get(j).xPos += projectiles2.get(j).dir[0] * projectiles2.get(j).movementSpeed;
				projectiles2.get(j).yPos += projectiles2.get(j).dir[1] * projectiles2.get(j).movementSpeed;
				}
		
			//Collision
		
			
			
			for(int p = 0; p < projectiles.size(); p++){
				if((projectiles.get(p).xPos + projectiles.get(p).width/2) > (player2.xPos - player2.width/2)  && (projectiles.get(p).xPos - projectiles.get(p).width/2) < (player2.xPos + player2.width/2) && (projectiles.get(p).yPos + projectiles.get(p).height/2) > (player2.yPos - player2.height/2) && (projectiles.get(p).yPos - projectiles.get(p).height/2) < (player2.yPos + player2.height/2)){
					projectiles.get(p).collides = true;
				}
				else {
					projectiles.get(p).collides = false;
				}
				
				if(projectiles.get(p).collides){
					player2.health--;
					projectiles.remove(p);
				}
			}
			
			for(int p = 0; p < projectiles2.size(); p++){
				if((projectiles2.get(p).xPos + projectiles2.get(p).width/2) > (player1.xPos - player1.width/2)  && (projectiles2.get(p).xPos - projectiles2.get(p).width/2) < (player1.xPos + player1.width/2) && (projectiles2.get(p).yPos + projectiles2.get(p).height/2) > (player1.yPos - player1.height/2) && (projectiles2.get(p).yPos - projectiles2.get(p).height/2) < (player1.yPos + player1.height/2)){
					projectiles2.get(p).collides = true;
				}
				else {
					projectiles2.get(p).collides = false;
				}
				
				if(projectiles2.get(p).collides){
					player1.health--;
					projectiles2.remove(p);
				}
			}
			
			
			
		}
			
		
		
		
		public void spawn(String image1, String image2) throws SlickException{
			MainClass.obstacles.add(new Obstacle(randInt(0,1200), randInt(0,800), image1, image2, 300,300));
		}
	

		@Override
		public void render(GameContainer gc, Graphics g) throws SlickException
		{
			
			collision(player1, obstacles);
			collision(player2, obstacles);
			
			for(int k = 0; k < obstacles.size()-1; k++){
				Obstacle obsta = obstacles.get(k);
				if(obsta.xPos != player1.xPos && obsta.yPos != player1.yPos && obsta.xPos != player2.xPos && obsta.yPos != player2.yPos)
				obsta.obst.draw(obsta.xPos,obsta.yPos);
				
				}
			
			
			if(player1.health > 0)
			g.drawString("Player 1 " + player1.health + "HP", player1.xPos+10, player1.yPos-20);
			
			if(player2.health > 0)
			g.drawString("Player 2 "+ player2.health + "HP", player2.xPos+10, player2.yPos-20);
			player1.active.draw(player1.xPos,player1.yPos);
			player2.active.draw(player2.xPos,player2.yPos);
			
			
			for (int i = 0; i < projectiles.size(); i++){
				Projectile currentProj = projectiles.get(i);
				if(player1.active == player1.playerAnim1)
				currentProj.proj.draw(currentProj.xPos+50, currentProj.yPos+50);
				else
					currentProj.proj.draw(currentProj.xPos-50, currentProj.yPos+50);
				if (currentProj.xPos > 1200 || currentProj.xPos < 0 || currentProj.yPos > 800 || currentProj.yPos < 0){
					projectiles.remove(i);
					
				}
				
			}
			
			for (int i = 0; i < projectiles2.size(); i++){
				Projectile currentProj = projectiles2.get(i);
				if(player2.active == player2.playerAnim1)
				currentProj.proj.draw(currentProj.xPos+50, currentProj.yPos+50);
				else
					currentProj.proj.draw(currentProj.xPos-50, currentProj.yPos+50);
				if (currentProj.xPos > 1200 || currentProj.xPos < 0 || currentProj.yPos > 800 || currentProj.yPos < 0){
					projectiles2.remove(i);
					
				}
				
			}
			
		
			
			
	
			
			}
			
		public int randInt(int min, int max) {
			  
		    Random rand = new Random();
		    int randomNum = rand.nextInt((max - min) + 1) + min;
		    return randomNum;
		}
		
		public void collision (Player p, ArrayList<Obstacle> ar){
			for(int o = 0; o < ar.size(); o++){
				if((ar.get(o).xPos + ar.get(o).width/2) > (p.xPos - p.width/2)  && (ar.get(o).xPos - ar.get(o).width/2) < (p.xPos + p.width/2) && (ar.get(o).yPos + ar.get(o).height/2) > (p.yPos - p.height/2) && (ar.get(o).yPos - ar.get(o).height/2) < (p.yPos + p.height/2)){
					ar.get(o).collides = true;
				}
				else {
					ar.get(o).collides = false;
				}
				
				if(ar.get(o).collides){
					p.health--;
					ar.remove(o);
				}
				
				}
		}

			
			
		

	
	}