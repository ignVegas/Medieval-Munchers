# Medieval-Munchers

Medieval Munchers is a 2D Java game developed for the Advanced Software Design course. This game involves a player navigating through a world filled with enemies and various objects, with the goal of surviving and achieving specific objectives.

## Features
- *Player Control*: The player can move and interact with the game world using keyboard inputs (Changable with FPS/Speed).
- *Enemies*: Various Enemies that work based on players current location within distance from the player.
- *Objects*: Game Objects Like Chests that the player can use with a RNG effect.
- *Collision Detection*: Using player hitboxes and location can have collision based on players location and the interactions with the block/tiles.
- *Win/Lose Conditions*: Ending the game by finishing off the boss, or effectively dying.

## Game Components
- *Game Loop*: Implemented in the run method, responsible for updating and rendering game components at a specified frame rate.
- *Player Management*: Controls the player (handled by the Player class), including movement and interactions.
- *Enemy Management*: Manages enemies (handled by the Skeleton class).
- *Object Management*: Manages various objects (handled by the mainObject class).
- *Collision Detection*: Detects and handles collisions between the player, enemies, and objects.
- *Health and Lives*: Manages the player's health and extra lives.
- *Win/Lose Conditions*: Checks and manages the conditions for winning or losing the game.
- *Initialization*: Initializes the game world with tiles and objects.
- *Core Logic*: The tick method manages the game's core logic, updating player and enemy positions.
- *Rendering*: The paintComponent method is responsible for rendering the game world, objects, and entities on the panel.




![Player](https://github.com/ignVegas/Medieval-Munchers/blob/main/Art/player/sFront.png?raw=true?x250) 
![Player](https://github.com/ignVegas/Medieval-Munchers/blob/main/Art/player/RightFaceStanding.png?raw=true?x250) 
