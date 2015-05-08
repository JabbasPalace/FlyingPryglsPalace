# Slick2D Eclipse Seed Project
This an eclipse project containing all the files necessary to begin developing games with [Slick 2D](http://slick.ninjacave.com/). You can fork this repository, clone it on your disk and start to work in Eclipse.

After having cloned the repository to your disk you have to follow these few steps:

1. Open up Eclipse.
2. Create a new java project and select the folder containing this seed.
2. Go to Project --> Properties in the menu bar.
3. Click on Java Build Path.
4. click the Add Jar button.
5. Select the *lib* folder in your project.
6. Select all the *.jar* files and click OK.
7. Expand *lwjgl.jar*.
8. Select *Natives Library Location* and click the Edit button.
9. Click the Workspace button.
10. Select the *native* folder in your project
11. Select your operating system and click OK until you get back to the default eclipse window.
12. Press play to check it it works.
13. Now, you can start making your own game.

System Description

MainClass is where everything else is put to use. Files a stored in the Game Container using void init(gamecontainer), Inputs, such as keypresses, are listened to through the void update(gamecontainer, delta), where animations are also controlled. Everything that needs to be drawn is called in the void render(gamecontainer, graphics) function, and collision between all the objects in the game is run through the void collision(player, obstacle), and pcollision(player, powerup) functions.

Player Class is where all the data needed to create a controllable player is allocated. I.e. its X and Y positions, its sprites, animations an so forth.

Projectile Class is using Player coordinates to know in which direction it should shoot. It takes images, duration (integers used to determain how often, and for how long each sprite should be drawn in the renderer)It also takes input through keypresses, enabling a player to shoot and switch between two different kind of weapons.

Obstacle Class is alot like the Projectile Class. It gets an random position from a random Integer function, and check for player position ( to avoid collision upon spawn ) before an instance of it is added to the ArrayList located in main, and afterwards drawn in the renderer. It damages the player upon impact and restores the players movement speed to default.

Powerup Class also checks for collision with player. Unlike the Obstacle Class, the Powerup adds a more movement speed to a given player. There can only spawn 1 obstacle at a time.