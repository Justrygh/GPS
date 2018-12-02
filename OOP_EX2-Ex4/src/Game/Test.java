package Game;

import java.io.File;
import java.util.ArrayList;

import Algorithms.MultiCSV;
import GIS.Project;

public class Test {

	public static void main(String[] args) {
		File Test = new File("/home/eli/eclipse-workspace/OOP_EX2-EX4-master/newdata/p1_Ariel.csv");
		Game Try = new Game();
		ArrayList<Game> it = Try.read(Test);
		Try.write(it, Test);
	}

}
