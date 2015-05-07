package Game;



import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;











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
	
	
	static long lastTime;
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
			appgc.setTargetFrameRate(60);
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
 
        public static ArrayList<PowerUp> powerup = new ArrayList<PowerUp>();


       Player[] players = new Player[2];

        
       
        
		@Override
		public void init(GameContainer gc) throws SlickException {

			
			players[0] = new Player(25,25, new Image[] {new Image("wizHor.png"), new Image("wizHor2.png"),new Image("wiz.png"),new Image("wiz2.png"),new Image("b.png"),new Image("b1.png")});
			players[1] = new Player(1100, 730, new Image[]{new Image("woBin.png"), new Image("woBin2.png"),new Image("woBin.png"), new Image("woBin2.png"),new Image("b.png"),new Image("b1.png")});

			
		}

		
		@Override
		public void update(GameContainer gc, int i) throws SlickException {
			
			lastTime = System.currentTimeMillis();
			if(obstacles != null && obstacles.size() < 10){
				
				spawn("Fire.png","Fire1.png",false);
				
			}
			
			if(lastTime%10 == 0 && powerup.size() < 2){
				
				spawn("something.png", "somethingelse.png",true);
			}
				
			Input input = gc.getInput();
			if(input.isKeyPressed(Input.KEY_NUMPAD1))
				players[0].activewep[0] = 0;
			
			if(input.isKeyPressed(Input.KEY_NUMPAD2))
				players[0].activewep[0] = 1;
			
			if(input.isKeyPressed(Input.KEY_1))
				players[1].activewep[0] = 0;
			
			if(input.isKeyPressed(Input.KEY_2))
				players[1].activewep[0] = 1;
			
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
		
			
			if (input.isKeyPressed(Input.KEY_RCONTROL)){
				if(players[0].activewep[0] == 1){
				//Fireball down
				if(players[0].direction[0] == 0 && players[0].direction[1] == 1)
				players[0].fire("FBd.png","FBd1.png", players[0].direction,1);
												
				//Fireball right down
				if(players[0].direction[0] == 1 && players[0].direction[1] == 1)
					players[0].fire("FBrd.png","FBrd1.png", players[0].direction,1);
			
				//Fireball right
				if(players[0].direction[0] == 1 && players[0].direction[1] == 0)
					players[0].fire("FBr.png","FBr1.png", players[0].direction,1);
				
				//Fireball left
				if(players[0].direction[0] == -1 && players[0].direction[1] == 0)
					players[0].fire("FBl.png","FBl1.png", players[0].direction,1);
				
				//Fireball left down
				if(players[0].direction[0] == -1 && players[0].direction[1] == 1)
					players[0].fire("FBld.png","FBld1.png", players[0].direction,1);
				
				//Fireball up
				if(players[0].direction[0] == 0 && players[0].direction[1] == -1)
					players[0].fire("FBu.png","FBu1.png", players[0].direction,1);
				
				//Fireball right up
				if(players[0].direction[0] == 1 && players[0].direction[1] == -1)
					players[0].fire("FBru.png","FBru1.png", players[0].direction,1);
				
				//Fireball left up
				if(players[0].direction[0] == -1 && players[0].direction[1] == -1)
					players[0].fire("FBlu.png","FBlu1.png", players[0].direction,1);
				}
				else if (players[0].activewep[0] == 0){
					players[0].fire("sb.png", "sb1.png", players[0].direction,2);
				}
				
					}
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
		
			
			if (input.isKeyPressed(Input.KEY_SPACE)){
				if(players[1].activewep[0] == 1){
				//Fireball down
				if(players[1].direction[0] == 0 && players[1].direction[1] == 1)
				players[1].fire2("FBd.png","FBd1.png", players[1].direction,1);
												
				//Fireball right down
				if(players[1].direction[0] == 1 && players[1].direction[1] == 1)
					players[1].fire2("FBrd.png","FBrd1.png", players[1].direction,1);
			
				//Fireball right
				if(players[1].direction[0] == 1 && players[1].direction[1] == 0)
					players[1].fire2("FBr.png","FBr1.png", players[1].direction,1);
				
				//Fireball left
				if(players[1].direction[0] == -1 && players[1].direction[1] == 0)
					players[1].fire2("FBl.png","FBl1.png", players[1].direction,1);
				
				//Fireball left down
				if(players[1].direction[0] == -1 && players[1].direction[1] == 1)
					players[1].fire2("FBld.png","FBld1.png", players[1].direction,1);
				
				//Fireball up
				if(players[1].direction[0] == 0 && players[1].direction[1] == -1)
					players[1].fire2("FBu.png","FBu1.png", players[1].direction,1);
				
				//Fireball right up
				if(players[1].direction[0] == 1 && players[1].direction[1] == -1)
					players[1].fire2("FBru.png","FBru1.png", players[1].direction,1);
				
				//Fireball left up
				if(players[1].direction[0] == -1 && players[1].direction[1] == -1)
					players[1].fire2("FBlu.png","FBlu1.png", players[1].direction,1);
				}									
				else if(players[1].activewep[0] == 0){
					players[1].fire2("sb.png", "sb1.png", players[1].direction,2);
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
			for (int i1 = 0; i1 < players.length; i1++){
				if (players[i1].health < 1){
					players[i1].active = players[i1].death;
					players[i1].active.update(i);
				}
				players[i1].xPos += players[i1].direction[0] * players[i1].movementSpeed;
				players[i1].yPos += players[i1].direction[1] * players[i1].movementSpeed;
			}
						
					
			for (int j = 0; j < projectiles.size(); j++){
				projectiles.get(j).xPos += projectiles.get(j).dir[0] * projectiles.get(j).movementSpeed;
				projectiles.get(j).yPos += projectiles.get(j).dir[1] * projectiles.get(j).movementSpeed;
				}
			for (int j = 0; j < projectiles2.size(); j++){
				projectiles2.get(j).xPos += projectiles2.get(j).dir[0] * projectiles2.get(j).movementSpeed;
				projectiles2.get(j).yPos += projectiles2.get(j).dir[1] * projectiles2.get(j).movementSpeed;
				}
		
			
			
			
			
		}
			
		
		
		
		public void spawn(String image1, String image2, boolean a) throws SlickException{
			if(a = false){
			MainClass.obstacles.add(new Obstacle(randInt(0,1200), randInt(0,800), image1, image2, 300,300));
			}
			else if(a = true){
			MainClass.powerup.add(new PowerUp(randInt(0,1200), randInt(0,800), image1, image2, 300, 300));
			}
		}
	

		@Override
		public void render(GameContainer gc, Graphics g) throws SlickException
		{
			for (int i = 0; i < players.length; i++){
				collision(players[i], obstacles);
			}
			
			for(int k = 0; k < obstacles.size()-1; k++){
				Obstacle obsta = obstacles.get(k);
				if(obsta.xPos != players[0].xPos && obsta.yPos != players[0].yPos && obsta.xPos != players[1].xPos && obsta.yPos != players[1].yPos)
				obsta.obst.draw(obsta.xPos,obsta.yPos);
				
				}
			
			for(int k = 0; k < powerup.size()-1; k++){
				PowerUp pUp = powerup.get(k);
				for(int j = 0; j < k; k++){
				Obstacle obsta = obstacles.get(k);
				if(pUp.xPos != obsta.xPos && pUp.yPos != obsta.yPos && pUp.xPos != players[0].xPos && pUp.yPos != players[0].yPos && pUp.xPos != players[1].xPos && pUp.yPos != players[1].yPos )
				pUp.pUps.draw(pUp.xPos,pUp.yPos);
				}
				}
			
			for (int j = 0; j < players.length; j++){
				if (players[j].health > 0){
					g.drawString("Player" + j+1 + " " + players[j].health + "HP", players[j].xPos+10, players[j].yPos-20);
				}
				players[j].active.draw(players[j].xPos,players[j].yPos);
			}
				
			
			for (int i = 0; i < projectiles.size(); i++){
				Projectile currentProj = projectiles.get(i);
				if(players[0].active == players[0].playerAnim1)
				currentProj.proj.draw(currentProj.xPos+50, currentProj.yPos+50);
				else
					currentProj.proj.draw(currentProj.xPos-50, currentProj.yPos+50);
				if (currentProj.xPos > 1200 || currentProj.xPos < 0 || currentProj.yPos > 800 || currentProj.yPos < 0){
					projectiles.remove(i);
					
				}
				
			}
			
			for (int i = 0; i < projectiles2.size(); i++){
				Projectile currentProj = projectiles2.get(i);
				if(players[1].active == players[1].playerAnim1)
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
			
			// OBSTACLES COLLISION
			for(int o = 0; o < ar.size(); o++){
				if((ar.get(o).xPos + ar.get(o).width/2) > (p.xPos - p.width/2)  && (ar.get(o).xPos - ar.get(o).width/2) < (p.xPos + p.width/2) && (ar.get(o).yPos + ar.get(o).height/2) > (p.yPos - p.height/2) && (ar.get(o).yPos - ar.get(o).height/2) < (p.yPos + p.height/2)){
					ar.get(o).collides = true;
					p.movementSpeed = 2;
					p.lifeloss();
					
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
						if(projectiles.get(q).wepEffect == 2){
						players[1].movementSpeed = 1;
						players[1].health-=5;
						}
						
					else if(projectiles.get(q).wepEffect == 1){
						players[1].health -= 10;
					
					}
						projectiles.remove(q);
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
							players[0].movementSpeed = 1;
						players[0].health -= 5;
						
					
						}else if(projectiles2.get(d).wepEffect == 1){
						players[0].health -= 10;
						
						
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
							
						}
						else {
							ar.get(o).collides = false;
							
							
							
						}
					}
				
				

		}
	
	

			
			
		

	
	}
