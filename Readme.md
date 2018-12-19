**********GPS Project!**********

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
      we gave each layer a *UNIQE* color that all the elements inside the layer will have the 
      same color in order to notice the difference between each layer.
      // Each Layer is a CSV file.

 * 3. Create a Project that will contain all the Layers. Convert a folder with CSV files into
      One KML file containing all the details of the CSV files.
      
**********Pacman Project**********

This project represents a Pacman Game where you can import a CSV file with Pacmans and Fruits details, you can create a new Game in the GUI by placing a Pacmans and Fruits where ever you like in the map and you can save the Game to a new CSV file.

In the project you can see the:
 * 1. pacmans Path represented in Lines.
 
 * 2. Simulation in real-time where each Pacman is located.
 
 * 3. Import to a KML file and see as well in Google-Earth each Pacman location in real-time. (Represents in TimeSpan)

1. ShortestPathAlgo:
Calculates the Shortest Time when all the Pacmans will eat all the fruits.

2. Map:
Picture of Ariel University map, Convert points to Pixel from GPS and vice-versa.

3. Path:
Saves each Pacman path to eat the nearest Fruit in the shortest time.

4. Game:
Collection of Pacmans and Fruits

5. MyFrame:
The main GUI that represents the GAME, includes all the classes Functions and methods inside.
In this class, you can import a game, export a new game, and create a new game.

6. Path2kml:
Converts the Game GUI from CSV file to KML file in order to view it in Google-Earth.

**In order to know what each class/function purpose is, I suggest you to look over at our JAVA DOC which explains everything into specific details.**


