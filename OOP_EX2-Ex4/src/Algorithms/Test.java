package Algorithms;

import java.io.File;

public class Test {

	public static void main(String[] args) {
		/** In order to run the MultiCSV, follow the instructions:
		 * First you need to define where you want to create the File and its name. 
		 * For Example: ("/home/eli/eclipse-workspace/OOP_EX2-EX4-master/data/MultiCSV.kml")
		 */
		MultiCSV Test = new MultiCSV();
		File MultiCSV = new File("Your/Directory/Path/OOP_EX2-EX4-master/data/MultiCSV.kml");
		Test.ListOfCSV(MultiCSV);
		
		
	}

}
