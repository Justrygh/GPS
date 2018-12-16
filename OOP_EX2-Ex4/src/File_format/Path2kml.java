package File_format;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import Declaration.ourFile;
import Players.Game;
import Structure.Path;

public class Path2kml {

	private File myFile;

	public Path2kml(File file) {
		this.myFile = file;
	}

	private final static long TimeStamp = new Date().getTime() + 7200000;

	/**
	 * @author Eli 
	 * @author Qusai 
	 * This function creates the KML file and writes the header.
	 * @param File to be written.
	 */

	public void print() {
		try 
		{
			Print = new PrintWriter(myFile);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}
		StringBuilder Builder = new StringBuilder();
		Builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		Builder.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");
		Builder.append("<Document>");
		Builder.append("<Style id=\"Pacman\"><IconStyle><Icon><href>/home/eli/eclipse-workspace/OOP_EX2-EX4-master/newdata/Pacman.png</href></Icon></IconStyle></Style>");
		Builder.append("<Style id=\"Fruit\"><IconStyle><Icon><href>/home/eli/eclipse-workspace/OOP_EX2-EX4-master/newdata/Fruit.png</href></Icon></IconStyle></Style>");
		Builder.append("<Style id=\"Apple\"><IconStyle><Icon><href>/home/eli/eclipse-workspace/OOP_EX2-EX4-master/newdata/Apple.png</href></Icon></IconStyle></Style>");
		Builder.append("<Style id=\"Done\"><IconStyle><Icon><href>/home/eli/eclipse-workspace/OOP_EX2-EX4-master/newdata/Done.png</href></Icon></IconStyle></Style>");
		//In order to make the pacman/fruit Icon to work, you need to change the Directory Path.
		Print.write(Builder.toString());
	}

	/*
	 *
	 */

	public void write(ArrayList<Game> game) {

		StringBuilder Builder = new StringBuilder();
		Iterator<Game> it = game.iterator();
		while(it.hasNext()) {
			Game Replace=it.next();
			long Time = (int)(Double.parseDouble(Replace.getTime())*1000);
			long Temp = (int)((Double.parseDouble(Replace.getTime()) - (Double.parseDouble(Replace.getDistance()) / Double.parseDouble(Replace.getSpeed())))*1000);
			String sTime = Instant.ofEpochMilli(TimeStamp + Temp).atOffset(ZoneOffset.UTC).toString();
			String eTime = Instant.ofEpochMilli(TimeStamp + Time).atOffset(ZoneOffset.UTC).toString();
			Builder.append("<Placemark>");
			Builder.append("<TimeSpan>");
			Builder.append("<begin>" + sTime + "</begin>");
			Builder.append("<end>" + eTime + "</end>");
			Builder.append("</TimeSpan>");
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
		Print.write(Builder.toString());
	}

	/*
	 * This function closes the KML file and sends it to the directory path you declared.
	 */

	public void Close() {
		StringBuilder Builder = new StringBuilder();
		Builder.append("</Document>");
		Builder.append("</kml>");
		Print.write(Builder.toString());
		Print.close();
		System.out.println("Congrats! You did it!");
	}

	//**********Private Methods**********//

	private PrintWriter Print;

	//**********Constructor**********//

	public Path2kml() {}

	//**********Getters**********//

}
