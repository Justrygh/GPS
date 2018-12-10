package File_format;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import Declaration.ourFile;
import Players.Game;

public class Path2kml {
	
	private File myFile;
	
	public Path2kml(File file) {
		this.myFile = file;
	}
	
	public void write(ArrayList<Game> _List) {
		ourFile file = new ourFile(myFile.getName());
		PrintWriter Print = null;
		try {
			Print = new PrintWriter(new File(myFile.getParent(), file.getName()));
		}
		catch(Exception e){
			System.err.println("There's no such file or directory");
		}
		StringBuilder Builder = new StringBuilder();
		Iterator<Game> it = _List.iterator();
		Builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		Builder.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");
		Builder.append("<Document>");
		Builder.append("<Style id=\"Pacman\"><IconStyle><Icon><href>/home/eli/eclipse-workspace/OOP_EX2-EX4-master/newdata/Pacman.png</href></Icon></IconStyle></Style>");
		Builder.append("<Style id=\"Fruit\"><IconStyle><Icon><href>/home/eli/eclipse-workspace/OOP_EX2-EX4-master/newdata/Fruit.png</href></Icon></IconStyle></Style>");
		Builder.append("<Style id=\"Apple\"><IconStyle><Icon><href>/home/eli/eclipse-workspace/OOP_EX2-EX4-master/newdata/Apple.png</href></Icon></IconStyle></Style>");
		while(it.hasNext()) {
			Game Replace = it.next();
			Builder.append("<Placemark>");
			Builder.append("<name>");
			Builder.append(Replace.getType() + "ID: " + Replace.getiD());
			Builder.append("</name>");
			Builder.append("<description>");
			Builder.append("Speed: " + Replace.getSpeed() + "<br/>");
			Builder.append("Radius: " + Replace.getRadius() + "<br/>");
			Builder.append("Time: " + Replace.getTime() + "<br/>");
			Builder.append("Distance: " + Replace.getDistance() + "<br/>");
			Builder.append("Fruits Eaten: " + Replace.getFruitsEaten() + "<br/>");
			Builder.append("</description>");
			Builder.append("<styleUrl>");
			Builder.append(Replace.getPicture());
			Builder.append("</styleUrl>");
			Builder.append("<Point>");
			Builder.append("<coordinates>");
			Builder.append(Replace.getPoint());
			Builder.append("</coordinates>");
			Builder.append("</Point>");
			Builder.append("</Placemark>");
		}
		Builder.append("</Document>");
		Builder.append("</kml>");
		Print.write(Builder.toString());
		Print.close();
		System.out.println("Congrats! You did it!");
	}
}
