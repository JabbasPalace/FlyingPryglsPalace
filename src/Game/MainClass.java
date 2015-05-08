package Game;



import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;





//Save







import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;




public class MainClass extends BasicGame  {
	
	
	static long lastTime;
	public static int width = 1200;
	public static int height = 860;
	public Sound firesound;
	public Sound frostsound;
	public Sound footstep;
	public Sound snowballHit;
	public Sound fireballHit;
	public Sound powerUp;
	public Sound burn;
	public Sound splat;
public Sound[] deathSounds = new Sound[2];
	public MainClass(String wizardShit) throws SlickException {
		super(wizardShit);
		
	
	}
	public static void main(String[] args)
	{
		
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new MainClass("Wizard Game"));
			appgc.setDisplayMode(width, height, false);
			appgc.setVSync(true);
			appgc.setTargetFrameRate(60);
			appgc.start();
			
			
		}
		catch (SlickException ex)
		{
			Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		
		
	}
	
		
		//Declaration of ArrayLists to be used to store objects
        public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
        public static ArrayList<Projectile> projectiles2 = new ArrayList<Projectile>();
        public static ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
        public static ArrayList<PowerUp> powerup = new ArrayList<PowerUp>();

        
        


    
         
       Player[] players = new Player[2]; // Player Array
       public Image BG; // Background Image

       
        
		@Override
		public void init(GameContainer gc) throws SlickException {

			//Initial x and y positions and Sprites for animations assigned to players
			players[0] = new Player(1100,730, new Image[] {new Image("wizHor.png"), new Image("wizHor2.png"),new Image("wiz.png"),new Image("wiz2.png"),new Image("b.png"),new Image("b1.png")});

			players[1] = new Player(50, 120, new Image[]{new Image("wizaHor.png"), new Image("wizaHor2.png"),new Image("wiza.png"), new Image("wiza2.png"),new Image("b.png"),new Image("b1.png")});

			BG = new Image("desertBG.png");
			
			firesound = new Sound("Fireballsound.wav");
			frostsound = new Sound("Frostballsound.wav");
			footstep = new Sound("footstep.wav");
			snowballHit = new Sound("snowballHit.wav");
			fireballHit = new Sound("fireballHit.wav");
			powerUp = new Sound("powerUp.wav");
			burn = new Sound("burn.wav");
			deathSounds[0] = new Sound("death1.wav");
			deathSounds[1] = new Sound("death2.wav");
			splat = new Sound("splat.wav");
		}

		
		@Override
		public void update(GameContainer gc, int i) throws SlickException {
			// Tracking time
			lastTime = System.currentTimeMillis();
			
			if(obstacles != null && obstacles.size() < 10){
				
				spawn("Fire.png","Fire1.png");
				
			}
			
			//Spawning powerup
			if(powerup.size() < 2){
				
				
				spawn2("PowerUp.png", "PowerUp.png");
			}
			
	for (int k = 0; k < players.length; k++){
				
				collision(players[k], obstacles,projectiles, i); // Calling collision check for players, obstacles and projectiles every frame.
				Pcollision(players[k],powerup);// Calling collision check for players and powerup every frame.

			}
			Input input = gc.getInput();
			
			//Switch projectile
			if(players[0].alive){
			if(input.isKeyPressed(Input.KEY_NUMPAD1))
				players[0].activewep[0] = 0;
			
			if(input.isKeyPressed(Input.KEY_NUMPAD2))
				players[0].activewep[0] = 1;
			
			if(input.isKeyPressed(Input.KEY_1))
				players[1].activewep[0] = 0;
			
			if(input.isKeyPressed(Input.KEY_2))
				players[1].activewep[0] = 1;
			
			//Player 1 move up
			if (input.isKeyDown(Input.KEY_UP)){
				players[0].direction[1] = -1;
				players[0].lastDir[1] = -1;
				players[0].active.update(i);
				if(players[0].yPos < -30 ){
					players[0].direction[1] = 1;
					
				}
				if (!input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT)){
					players[0].lastDir[0] = 0;
					
					}
			}
			//Player 1 move down
			if (input.isKeyDown(Input.KEY_DOWN)){
				players[0].direction[1] = 1;
				players[0].lastDir[1] = 1;
				players[0].active.update(i);
				if(players[0].yPos > 765 ){
					players[0].direction[1] = -1;
				}
				if (!input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT)){
					players[0].lastDir[0] = 0;	
					}
			}
	        if (!input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_UP)) {
			    players[0].direction[1] = 0;	
			}
	      //Player 1 move left
			if (input.isKeyDown(Input.KEY_LEFT)){
				players[0].direction[0] = -1;
				players[0].lastDir[0] = -1;
				players[0].active = players[0].playerAnim2;
				players[0].active.update(i);
				if(players[0].xPos < -25 ){
					players[0].direction[0] = 1;
				}
				 if (!input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_UP)) {
					    players[0].lastDir[1] = 0;	
					}
				
			}
			//Player 1 move right
			if (input.isKeyDown(Input.KEY_RIGHT)){
				players[0].direction[0] = 1;
				players[0].lastDir[0] = 1;
				players[0].active = players[0].playerAnim1;
				players[0].active.update(i);
				if(players[0].xPos > 1110 ){
					players[0].direction[0] = -1;
				}
				  if (!input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_UP)) {
					    players[0].lastDir[1] = 0;	
					}
			}
			if (!input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT)){
				players[0].direction[0] = 0;	
				}
		
			//Player 1 fire projectile
			if (input.isKeyPressed(Input.KEY_RCONTROL)){
				if(players[0].activewep[0] == 1){
				//Fireball down
				if(players[0].direction[0] == 0 && players[0].direction[1] == 1){
				players[0].fire("FBd.png","FBd1.png", players[0].direction,1);
				firesound.play();
				}
												
				//Fireball right down
				if(players[0].direction[0] == 1 && players[0].direction[1] == 1){
					players[0].fire("FBrd.png","FBrd1.png", players[0].direction,1);
					firesound.play();
				}
				//Fireball right
				if(players[0].direction[0] == 1 && players[0].direction[1] == 0){
					players[0].fire("FBr.png","FBr1.png", players[0].direction,1);
					firesound.play();
				}
				//Fireball left
				if(players[0].direction[0] == -1 && players[0].direction[1] == 0){
					players[0].fire("FBl.png","FBl1.png", players[0].direction,1);
					firesound.play();
				}
				//Fireball left down
				if(players[0].direction[0] == -1 && players[0].direction[1] == 1){
					players[0].fire("FBld.png","FBld1.png", players[0].direction,1);
					firesound.play();
				}
				//Fireball up
				if(players[0].direction[0] == 0 && players[0].direction[1] == -1){
					players[0].fire("FBu.png","FBu1.png", players[0].direction,1);
					firesound.play();
				}
				//Fireball right up
				if(players[0].direction[0] == 1 && players[0].direction[1] == -1){
					players[0].fire("FBru.png","FBru1.png", players[0].direction,1);
					firesound.play();
				}
				//Fireball left up
				if(players[0].direction[0] == -1 && players[0].direction[1] == -1){
					players[0].fire("FBlu.png","FBlu1.png", players[0].direction,1);
					firesound.play();
				}
				}
				else if (players[0].activewep[0] == 0){
					frostsound.play();
					players[0].fire("sb.png", "sb1.png", players[0].direction,2);
				}
				
					}
			}
			if(players[1].alive){
				//Player 2 move up
			if (input.isKeyDown(Input.KEY_W)){
				players[1].direction[1] = -1;
				players[1].lastDir[1] = -1;
				players[1].active.update(i);
				if(players[1].yPos < -30 ){
					players[0].direction[1] = 1;
				}
				if (!input.isKeyDown(Input.KEY_A) && !input.isKeyDown(Input.KEY_RIGHT)){
					players[1].lastDir[0] = 0;
					
					}
			}
			//Player 2 move down
			if (input.isKeyDown(Input.KEY_S)){
				players[1].direction[1] = 1;
				players[1].lastDir[1] = 1;
				players[1].active.update(i);
				if(players[1].yPos > 765 ){
					players[1].direction[1] = -1;
				}
				if (!input.isKeyDown(Input.KEY_A) && !input.isKeyDown(Input.KEY_D)){
					players[1].lastDir[0] = 0;	
					}
			}
	        if (!input.isKeyDown(Input.KEY_S) && !input.isKeyDown(Input.KEY_W)) {
			    players[1].direction[1] = 0;	
			}
	      //Player 2 move left
			if (input.isKeyDown(Input.KEY_A)){
				players[1].direction[0] = -1;
				players[1].lastDir[0] = -1;
				players[1].active = players[1].playerAnim2;
				players[1].active.update(i);
				if(players[1].xPos < -25 ){
					players[1].direction[0] = 1;
				}
				 if (!input.isKeyDown(Input.KEY_S) && !input.isKeyDown(Input.KEY_W)) {
					    players[1].lastDir[1] = 0;	
					}
				
			}
			//Player 2 move right
			if (input.isKeyDown(Input.KEY_D)){
				players[1].direction[0] = 1;
				players[1].lastDir[0] = 1;
				players[1].active = players[1].playerAnim1;
				players[1].active.update(i);
				if(players[1].xPos > 1110 ){
					players[1].direction[0] = -1;
				}
				  if (!input.isKeyDown(Input.KEY_S) && !input.isKeyDown(Input.KEY_W)) {
					    players[1].lastDir[1] = 0;	
					}
			}
			if (!input.isKeyDown(Input.KEY_A) && !input.isKeyDown(Input.KEY_D)){
				players[1].direction[0] = 0;	
				}
		
			//Player 2 fire projectile
			if (input.isKeyPressed(Input.KEY_SPACE)){
				if(players[1].activewep[0] == 1){
				//Fireball down
				if(players[1].direction[0] == 0 && players[1].direction[1] == 1){
				players[1].fire2("FBd.png","FBd1.png", players[1].direction,1);
				firesound.play();
				}
												
				//Fireball right down
				if(players[1].direction[0] == 1 && players[1].direction[1] == 1){
					players[1].fire2("FBrd.png","FBrd1.png", players[1].direction,1);
					firesound.play();
				}
			
				//Fireball right
				if(players[1].direction[0] == 1 && players[1].direction[1] == 0){
					players[1].fire2("FBr.png","FBr1.png", players[1].direction,1);
					firesound.play();
				}
				
				//Fireball left
				if(players[1].direction[0] == -1 && players[1].direction[1] == 0){
					players[1].fire2("FBl.png","FBl1.png", players[1].direction,1);
					firesound.play();
				}
				
				//Fireball left down
				if(players[1].direction[0] == -1 && players[1].direction[1] == 1){
					players[1].fire2("FBld.png","FBld1.png", players[1].direction,1);
					firesound.play();
				}
				
				//Fireball up
				if(players[1].direction[0] == 0 && players[1].direction[1] == -1){
					players[1].fire2("FBu.png","FBu1.png", players[1].direction,1);
					firesound.play();
				}
				
				//Fireball right up
				if(players[1].direction[0] == 1 && players[1].direction[1] == -1){
					players[1].fire2("FBru.png","FBru1.png", players[1].direction,1);
					firesound.play();
				}
				
				//Fireball left up
				if(players[1].direction[0] == -1 && players[1].direction[1] == -1){
					players[1].fire2("FBlu.png","FBlu1.png", players[1].direction,1);
					firesound.play();
				}
				}									
				else if(players[1].activewep[0] == 0){
					players[1].fire2("sb.png", "sb1.png", players[1].direction,2);
					frostsound.play();
				}
					
			}
			}
			//Update projectile animation player 1
			if(projectiles != null){
				for(int k = 0; k < projectiles.size();k++){
				projectiles.get(k).proj.update(i);
				}
			}
			//Update projectile animation player 2	
			if(projectiles2 != null){
				for(int k = 0; k < projectiles2.size();k++){
				projectiles2.get(k).proj.update(i);
				}
			}
			
			//Update obstacle animation
			if(obstacles != null){
				for(int j = 0; j < obstacles.size(); j++){
					obstacles.get(j).obst.update(i);
				}
			}
			// AT DEATH
			for (int i1 = 0; i1 < players.length; i1++){
				if (players[i1].health < 1 && players[i1].alive){
					players[i1].active = players[i1].death;
					players[i1].active.update(i);
					players[i1].alive = false;
					splat.play();
				    deathSounds[i1].play();
				} // moving player in keypressed direction, with current movement speed
				players[i1].xPos += players[i1].direction[0] * players[i1].movementSpeed;
				players[i1].yPos += players[i1].direction[1] * players[i1].movementSpeed;
			}
						
					
			for (int j = 0; j < projectiles.size(); j++){ // Moving projectiles through the scene
				projectiles.get(j).xPos += projectiles.get(j).dir[0] * projectiles.get(j).movementSpeed;
				projectiles.get(j).yPos += projectiles.get(j).dir[1] * projectiles.get(j).movementSpeed;
				}
			for (int j = 0; j < projectiles2.size(); j++){
				projectiles2.get(j).xPos += projectiles2.get(j).dir[0] * projectiles2.get(j).movementSpeed;
				projectiles2.get(j).yPos += projectiles2.get(j).dir[1] * projectiles2.get(j).movementSpeed;
				}
		
			
			//If either player is dead, press enter to restart the game. 
			if(!players[0].alive || !players[1].alive){
				
			
				if(input.isKeyPressed(Input.KEY_ENTER)){
					for(int l = 0; l < obstacles.size(); l++)
						obstacles.remove(l);
						powerup.remove(0);
					init(gc);
					
					
				}
			}
			
			
		}
			
		
		
		
		public void spawn(String image1, String image2) throws SlickException{
			
			MainClass.obstacles.add(new Obstacle(randInt(0,1200), randInt(0,860), image1, image2, 300,300));
			// Adding obstacle objects to ArrayList
			
		}
		
		public void spawn2(String image1, String image2) throws SlickException{
			MainClass.powerup.add(new PowerUp(randInt(0,1200), randInt(0,860), image1, image2, 300, 300));

			// Adding powerup objects to ArrayList

		}
	

		@Override
		public void render(GameContainer gc, Graphics g) throws SlickException
		{
			
			BG.draw(0,30);
			
		
			
			for(int k = 0; k < obstacles.size(); k++){
				Obstacle obsta = obstacles.get(k);
				if(obsta.xPos != players[0].xPos && obsta.yPos != players[0].yPos && obsta.xPos != players[1].xPos && obsta.yPos != players[1].yPos)
				obsta.obst.draw(obsta.xPos,obsta.yPos);
				
				}
			
			for(int k = 0; k < powerup.size()-1; k++){
				PowerUp pUp = powerup.get(k);
				for(int j = 0; j < obstacles.size(); j++){
				Obstacle obsta = obstacles.get(j);
				if(pUp.xPos != obsta.xPos && pUp.yPos != obsta.yPos && pUp.xPos != players[0].xPos && pUp.yPos != players[0].yPos && pUp.xPos != players[1].xPos && pUp.yPos != players[1].yPos )
				pUp.pUps.draw(pUp.xPos,pUp.yPos);
				}
				}
			
			for (int j = 0; j < players.length; j++){
				if (players[j].health > 0){
					g.drawString("Player" + (j+1) + " " + players[j].health + "HP", players[j].xPos+10, players[j].yPos-20); // Draw player health and "name" 1 or 2 above player
				}
				players[j].active.draw(players[j].xPos,players[j].yPos);
			}
				
			
			for (int i = 0; i < projectiles.size(); i++){
				Projectile currentProj = projectiles.get(i);
				if(players[0].active == players[0].playerAnim1)
				currentProj.proj.draw(currentProj.xPos, currentProj.yPos); // Renders projectiles at the xPos and yPos of the player shooting
				else
					currentProj.proj.draw(currentProj.xPos, currentProj.yPos);
				if (currentProj.xPos > 1200 || currentProj.xPos < 0 || currentProj.yPos > 860 || currentProj.yPos < 0){
					projectiles.remove(i);// Removes projectiles when they reach the frame width and/or height
					
				}
				
			}
			
			for (int i = 0; i < projectiles2.size(); i++){
				Projectile currentProj = projectiles2.get(i);
				if(players[1].active == players[1].playerAnim1)
				currentProj.proj.draw(currentProj.xPos, currentProj.yPos); // Renders projectiles at the xPos and yPos of the player shooting
				else
					currentProj.proj.draw(currentProj.xPos, currentProj.yPos);
				if (currentProj.xPos > 1200 || currentProj.xPos < 0 || currentProj.yPos > 860 || currentProj.yPos < 0){
					projectiles2.remove(i); // Removes projectiles when they reach the frame width and/or height
					
				}
				
			}
			
		
			if(!players[0].alive && players[1].alive){
				g.drawString("PLAYER TWO WINS!" + "PRESS ENTER TO RESET", 440, 450);
			}else if(!players[1].alive && players[0].alive)
				g.drawString("PLAYER ONE WINS! " + "PRESS ENTER TO RESET", 440, 450);
			

			
			}
			

		public int randInt(int min, int max) { // Producing random coordinates for obstacle spawn

			 boolean collides = false;
		    Random rand = new Random();
		    int randomNum = rand.nextInt((max - min) + 1) + min;
		    for(int i = 0; i < players.length; i++){ // checking for collision with players before spawning

		    if(randomNum  > (players[i].xPos - players[i].width/2)  && randomNum < (players[i].xPos + players[i].width/2) && randomNum > (players[i].yPos - players[i].height/2) && randomNum < (players[i].yPos + players[i].height/2)){
		   collides = true;
		}
		    
		    }
		    if (collides){
		    	 randInt(min,max);
		    }
		    else{
		    	return randomNum;
		    }
		    return 0;
		}
		
		public void collision (Player p, ArrayList<Obstacle> ar, ArrayList<Projectile> arr, int delta){
			
			// OBSTACLES COLLISION
			for(int o = 0; o < ar.size(); o++){
				if((ar.get(o).xPos + ar.get(o).width/2) > (p.xPos - p.width/2)  && (ar.get(o).xPos - ar.get(o).width/2) < (p.xPos + p.width/2) && (ar.get(o).yPos + ar.get(o).height/2) > (p.yPos - p.height/2) && (ar.get(o).yPos - ar.get(o).height/2) < (p.yPos + p.height/2)){
					ar.get(o).collides = true;
					if(ar.get(o).flameTimer == 0){
						burn.play();
					}
					ar.get(o).flameTimer++;
					if(ar.get(o).flameTimer > 78){
						ar.get(o).flameTimer = 0;
					}
					p.movementSpeed = 4; // Movement speed is restored to default (Enable player to remove Weapon effect 2 slow)
					//p.lifeloss(); // Run lifeloss method from player class
				if(delta % 6 == 0)
					p.health -=5;
				}
					
				else {
					ar.get(o).collides = false;
					
					
					
				}
			}
				
				
				
				
				//Collision
				
				
				
				for(int q = 0; q < projectiles.size(); q++){
					if((projectiles.get(q).xPos + projectiles.get(q).width/2) > (players[1].xPos - players[1].width/2)  && (projectiles.get(q).xPos - projectiles.get(q).width/2) < (players[1].xPos + players[1].width/2) && (projectiles.get(q).yPos + projectiles.get(q).height/2) > (players[1].yPos - players[1].height/2) && (projectiles.get(q).yPos - projectiles.get(q).height/2) < (players[1].yPos + players[1].height/2)){
						projectiles.get(q).collides = true;
					}
					else {
						projectiles.get(q).collides = false;
					}
										
					if(projectiles.get(q).collides){
						if(projectiles.get(q).wepEffect == 2){ // Icebolt slows the player hit, and deals 10 damage
							if(players[1].movementSpeed > 2)
							players[1].movementSpeed --;
						players[1].health-=10;
						snowballHit.play();
						
						}
						
					else if(projectiles.get(q).wepEffect == 1){ // Fireball deals 15 damage
						players[1].health -= 15;
						fireballHit.play();
					
					}
						projectiles.remove(q);
				}
				}
				for(int z = 0; z < projectiles.size(); z++){ // Collision check for Player 1 projectiles on Obstacles
					for(int j = 0; j < obstacles.size(); j++){
					if((projectiles.get(z).xPos + projectiles.get(z).width/2) > (obstacles.get(j).xPos - obstacles.get(j).width/2)  && (projectiles.get(z).xPos - projectiles.get(z).width/2) < (obstacles.get(j).xPos + obstacles.get(j).width/2) && (projectiles.get(z).yPos + projectiles.get(z).height/2) > (obstacles.get(j).yPos - obstacles.get(j).height/2) && (projectiles.get(z).yPos - projectiles.get(z).height/2) < (obstacles.get(j).yPos + obstacles.get(j).height/2)){
						projectiles.get(z).collides = true;
						if(projectiles.get(z).wepEffect == 2){ // Weapon effect 2 (Icebolt) extinguish the flame obstacle
						obstacles.remove(j);						
						}else if(projectiles.get(z).wepEffect == 1){ // Weapon effect 1 (Fireball) shoots faster if collision with flame obstacle
							projectiles.get(z).movementSpeed += 5;
						}
					
					}
					
					else {
						projectiles.get(z).collides = false;
					}
					}
				}
				for(int z = 0; z < projectiles2.size(); z++){ // Collision check for Player 2 projectiles on Obstacles
					for(int j = 0; j < obstacles.size(); j++){
					if((projectiles2.get(z).xPos + projectiles2.get(z).width/2) > (obstacles.get(j).xPos - obstacles.get(j).width/2)  && (projectiles2.get(z).xPos - projectiles2.get(z).width/2) < (obstacles.get(j).xPos + obstacles.get(j).width/2) && (projectiles2.get(z).yPos + projectiles2.get(z).height/2) > (obstacles.get(j).yPos - obstacles.get(j).height/2) && (projectiles2.get(z).yPos - projectiles2.get(z).height/2) < (obstacles.get(j).yPos + obstacles.get(j).height/2)){
						projectiles2.get(z).collides = true;
						if(projectiles2.get(z).wepEffect == 2){
						obstacles.remove(j);
						}else if(projectiles2.get(z).wepEffect == 1){
							projectiles2.get(z).movementSpeed += 5;
						}
					
					}
					
					else {
						projectiles2.get(z).collides = false;
					}
					}
				}
					
				
				for(int d = 0; d < projectiles2.size(); d++){
					if((projectiles2.get(d).xPos + projectiles2.get(d).width/2) > (players[0].xPos - players[0].width/2)  && (projectiles2.get(d).xPos - projectiles2.get(d).width/2) < (players[0].xPos + players[0].width/2) && (projectiles2.get(d).yPos + projectiles2.get(d).height/2) > (players[0].yPos - players[0].height/2) && (projectiles2.get(d).yPos - projectiles2.get(d).height/2) < (players[0].yPos + players[0].height/2)){
						projectiles2.get(d).collides = true;
					}
					else {
						projectiles2.get(d).collides = false;
					}
					
					if(projectiles2.get(d).collides){
						if(projectiles2.get(d).wepEffect == 2){
							if(players[0].movementSpeed > 2)
							players[0].movementSpeed --;
						players[0].health -= 5;
						snowballHit.play();
						
					
						}else if(projectiles2.get(d).wepEffect == 1){
						players[0].health -= 10;
						fireballHit.play();
						
						
					}
						projectiles2.remove(d);
				
				}
				}
		}
				
				public void Pcollision (Player p, ArrayList<PowerUp> ar){
					
					// OBSTACLES COLLISION
					for(int o = 0; o < ar.size(); o++){
						if((ar.get(o).xPos + ar.get(o).width/2) > (p.xPos - p.width/2)  && (ar.get(o).xPos - ar.get(o).width/2) < (p.xPos + p.width/2) && (ar.get(o).yPos + ar.get(o).height/2) > (p.yPos - p.height/2) && (ar.get(o).yPos - ar.get(o).height/2) < (p.yPos + p.height/2)){
							ar.get(o).collides = true;
							p.powerUp();
							powerup.remove(o);
							powerUp.play();
							
						}
						else {
							ar.get(o).collides = false;
							
							
							
						}
					}
				
				

		}
	
	

			
			
		

	
	}
