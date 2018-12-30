***GPS Project!***

@authors: Eli & Qusai.

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
      
***Pacman Project***

This Project represents a Pacman Game.
This game purpose is for the Pacmans to eat all the Fruits in the shortest time.

**Attention**:
In order to run the game, you will need to read the instructions first! 
When you will run the code for the first time, the instructions will pop-up on the GUI.

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
