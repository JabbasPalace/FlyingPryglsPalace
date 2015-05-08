# FlyingPryglsPalace
Starting seed for a Slick2D project using Eclipse
System Description

MainClass is where everything else is put to use. 
Files a stored in the Game Container using void init(gamecontainer), Inputs, such as keypresses, are listened to through the void update(gamecontainer, delta), where animations are also controlled. Everything that needs to be drawn is called in the void render(gamecontainer, graphics) function, and collision between all the objects in the game is run through the void collision(player, obstacle), and pcollision(player, powerup) functions.

Player Class is where all the data needed to create a controllable player is allocated. I.e. its X and Y positions, its sprites, animations an so forth.

Projectile Class is using Player coordinates to know in which direction it should shoot. It takes images, duration (integers used to determain how often, and for how long each sprite should be drawn in the renderer)It also takes input through keypresses, enabling a player to shoot and switch between two different kind of weapons.

Obstacle Class is alot like the Projectile Class. It gets an random position from a random Integer function, and check for player position ( to avoid collision upon spawn ) before an instance of it is added to the ArrayList located in main, and afterwards drawn in the renderer. It damages the player upon impact and restores the players movement speed to default.

Powerup Class also checks for collision with player. Unlike the Obstacle Class, the Powerup adds a more movement speed to a given player. There can only spawn 1 obstacle at a time.
