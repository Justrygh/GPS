package Algorithms;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import Players.Fruit;
import Players.Pacman;
import Players.Game;

public class ShortestPathAlgo {
	
	public ShortestPathAlgo(ArrayList<Game> game) {
		String P = "Pacman";
		String F = "Fruit"; 
		for(int i=0; i<game.size(); i++) {
			Game g = game.get(i);
			if(g.getType().equals(F)) {
				Fruit Fruit = new Fruit(g.getType(), g.getPoint(), g.getPicture());
				_Fruits.add(Fruit);
			}
			else if(g.getType().equals(P)) {
				Pacman Pacman = new Pacman(g.getType(), g.getPoint(), g.getSpeed(), g.getRadius(), g.getPicture());
				_Pacmans.add(Pacman);
			}
		}
	}
	
	public static void main(String[] args) {
		File Test = new File("/home/eli/eclipse-workspace/OOP_EX2-EX4-master/newdata/p1_Ariel.csv");
		Game Try = new Game(Test);
		ShortestPathAlgo Name = new ShortestPathAlgo(Try.read());
	}
	private ArrayList<Fruit> _Fruits = new ArrayList<Fruit>();
	private ArrayList<Pacman> _Pacmans = new ArrayList<Pacman>();
	
}
