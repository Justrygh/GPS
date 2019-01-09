@authors: Eli & Qusai.

__
**GPS Project!**.

This project represent a GPS system in Object Oriented course in Ariel University.
In this project we work with 3D/2D Points in the Sphere (In our case the planet Earth).

We have been asked to do the following assigments:

1. represent a basic coordinate system including:
 * 1. The 3D vector between two (lat,lon, alt) points.
 * 2. Adding a 3D vector in meters to a global point.
 * 3. convert a 3D vector from meters to polar coordinates

2. Convert a given CSV file into KML file with the following details:
 * 1. Name 
 * 2. Ethernet (In our case it's WIFI)
 * 3. Date (Whenever the WIFI was first seen).
 * 4. Coordinates (lat, lon, alt)

3. Create a Data structure that will contain the following things:
 * 1. Create an Element that will contain the details above we mentioned and in addition will
      contain the Time Stamp (The time and date the element was created) and his color.
      // Each element is a row in the CSV file.

 * 2. Create a Layer that will contain all the Elements we created. While creating the layer
      we gave each layer a UNIQE color that all the elements inside the layer will have the 
      same color in order to notice the difference between each layer.
      // Each Layer is a CSV file.

 * 3. Create a Project that will contain all the Layers. Convert a folder with CSV files into
      One KML file containing all the details of the CSV files.
      
___    
**Pacman Project!**

- **OFFLINE MODE**

This Project represents a Pacman Game.
This game purpose is for the Pacmans to eat all the Fruits in the shortest time.

**Attention**:
In order to run the game, you will need to read the instructions first! 
When you will run the code for the first time, the instructions will pop-up on the GUI.
Make sure to read the instructions carefully, when you finish click on the screen in order to pass to the next page.

In this project, you can do the following things:

 1. You can import a CSV file, which contains list of Pacmans and Fruits including:
      - Type(P/F), 
      - Cordinates represented in: (Lat, Long, Alt)
      - Speed/Weight (Pacman ONLY) -The speed of how many meters the Pacman will make in a second
      - Radius (Pacman ONLY) - The distance that the Pacman is able to eat the Fruit infront of him.
      - ID - Unique Identifier for each Pacman/Fruit.
      
 2. You can create a new GAME in GUI:
      Whenever you run the code for the first time, an Ariel University map will be showen in the GUI with instructions
      explaining what features do we have and how to use them.
      You can add Pacmans/Fruits as many as you want, where ever you want in our GUI, and run the game.
      *Make sure to read the instructions before in order to be able to play the game*
      
 3. You can export a game you created in our GUI to a new CSV file and save all the details in order to run it again next time:
      - Type(P/F), 
      - Cordinates represented in: (Lat, Long, Alt)
      - Speed/Weight (Pacman ONLY) -The speed of how many meters the Pacman will make in a second
      - Radius (Pacman ONLY) - The distance that the Pacman is able to eat the Fruit infront of him.
      - ID - Unique Identifier for each Pacman/Fruit.
      
In the project we have special features in order to accomplish our mission and view the game running:

 1. Run - Calculates the Shortest Path for each Pacman and represents it by drawing lines from our Pacman to the next fruit
            he is about to eat, until he reaches the last fruit in his path.
 
 2. Demo - Simulate each Pacman's path in real-time by showing his new location every 1/2 second (The pacmans caculates their
             new location for every second, but in order to make the game run faster since it will take few minutes for the
             Pacmans to complete their path, we fast forwarded it,
             You can feel free and change the delay time to your own will.
 
 3. Convert2kml - Another way of viewing each Pacman location, we added a feature that we convert our game to a new KML file
                  which allows the player to view the game in Google-Earth.


**UPDATED:** 
We added some new cool features in order to improve your gaming experience:

1. When you create a new game, your default Radius and Speed for all the pacmans are 1. Now you have the ability to change it! Just press "Define" Tab and choose whatever you like.
You can change the Default Speed/Radius for all the pacmans or you can choose one single pacman and change his Speed/Radius using his ID. (You can see the Pacman's ID in the GUI above his head)

2. Open / Open & Edit: 
With the Open tab you can *ONLY* open a saved game but you can't change anything (Radius/Speed/Adding new Fruits/Pacmans) You can only just run the game. In order to open a saved game and to have the ability to change it, we added you a new feature called Open & Edit. When you use that features you have the access to change everything you want in the game. *BUT*, in order to run the game, first you will have to save the changes you made and then you can play!

**Notice**

We made some Limits for the changes you can make in the game:

- **Speed limit: No LIMIT, MUST be greater than 0**
- **Radius limit: 1-9**

- **ONLINE MODE**

So far we introduced you our game in offline mode.
In our offline mode you could place your pacmans and fruits on the map and watch how the pacmans eat the fruits on the gui in the fastest way, without having the ability to actually play in our game. In our online mode, you can play the game and decide wherever your player will go.

So how this works:
Same as our offline mode you can open a CSV file containing the pacmans and fruits, BUT here is the catch, now you can add ghosts and blocks to your own game.

So whats the purpose of the ghosts & blocks and how do you actually play?
Before we will begin with the ghosts & blocks lets explain to you how the game works.

You have 2 types of games you can play:

- Click & Play - You can choose where to move your player on the map (using your mouse) and plan your own best strategy how to eat the pacmans/fruits while trying to avoid the ghosts/blocks.
- Autoplay - Let the computer play for you the choose the best strategy based on our algorithm.


You have 2 levels for the game Easy/Hard.


Whats the difference? In the hard level, the ghosts will follow your player to his next location which makes it harder for you to escape them while in the easy level they will follow your player to his previous location (before you made your move), You got the idea.

Now whats the purpose of the ghosts and blocks?

- **Ghosts:** The ghosts purpose is to "hunt" your player, if they manage doing so, your player will lose 20 points.
Don't worry, if you are being killed, the game is not over until all the fruits are being eaten or the time's up and you have 3 seconds of "immunity" where the ghosts won't hunt your player.
- **Blocks:** In order to make the game a little bit harder, we added some blocks which you can't pass through them, and if you will try doing so, your player wont move, and you will lose 1 point. So don't try to fool us!


Now all is left is to explain you how to play (make sure to follow the instructions in the exact order)
1. Open a game (If you don't have a game, you can download one from our library in "ourdata" folder)
2. Insert your ID/s (You can insert up to 3 different IDs, if you are playing solo, DON'T fill the rest it will automatically make it default 0)
- **Attention**
Make sure to write a VALID ID (9 digits)!
3. Place your player on the map (Make sure not to place it on a block - marked as black square).
4. Choose your game and start playing!


Have fun!




