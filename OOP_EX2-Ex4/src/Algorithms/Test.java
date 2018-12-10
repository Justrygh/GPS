package Algorithms;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import File_format.Csv2kml;
import File_format.Path2kml;
import GIS.GIS_layer;
import GIS.Project;
import Players.Game;

public class Test {

	public static void main(String[] args) {
		/** In order to run the MultiCSV, follow the instructions:
		 * First you need to define where you want to create the File and its name. 
		 * For Example: ("/home/eli/eclipse-workspace/OOP_EX2-EX4-master/data/MultiCSV.kml")
		 */
		/*MultiCSV Test = new MultiCSV();
		File MultiCSV = new File("/home/eli/eclipse-workspace/OOP_EX2-EX4-master/data/MultiCSV.kml");
		Test.ListOfCSV(MultiCSV);*/
		
		Game Try = new Game(new File("/home/eli/eclipse-workspace/OOP_EX2-EX4-master/newdata/game_1543685769754.csv"));
		ShortestPathAlgo Name = new ShortestPathAlgo(Try.read());
		ArrayList<Game> it = Name.Calculate2GPS();
		Path2kml test = new Path2kml(new File("/home/eli/eclipse-workspace/OOP_EX2-EX4-master/newdata/Coords.kml"));
		test.write(it);
		
	}
}



