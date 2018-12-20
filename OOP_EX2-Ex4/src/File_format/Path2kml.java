package File_format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import Players.Game;
import Players.Pacman;

public class Path2kml {
	
	/**
	 * @author Eli 
	 * @author Qusai 
	 * This function creates the KML file in which we can see each Pacman in real-time location.
	 * @param File to be written.
	 */

	private File myFile;
	private final static long TimeStamp = new Date().getTime() + 7200000;
	public Path2kml(File file) {
		this.myFile = file;
	}

	/**
	 * Prints the Header of the KML, 
	 * 
	 * ATTENTION!!!
	 * In order to do that you need to change the Directory Path of your project.
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
		Builder.append("<Style id=\"Pacman\"><IconStyle><Icon><href>newdata/Pacman.png</href></Icon></IconStyle></Style>");
		Builder.append("<Style id=\"Fruit\"><IconStyle><Icon><href>newdata/Fruit.png</href></Icon></IconStyle></Style>");
		Builder.append("<Style id=\"Apple\"><IconStyle><Icon><href>newdata/Apple.png</href></Icon></IconStyle></Style>");
		Builder.append("<Style id=\"Done\"><IconStyle><Icon><href>newdata/Done.png</href></Icon></IconStyle></Style>");
		Print.write(Builder.toString());
	}

	/**
	 * Writes down the fruits and the pacmans to new KML file with TimeStamp.
	 * Replace.getTime() = The current time the pacman passed before reaching the fruit. (The last fruit he ate)
	 * We calculated the time until the pacman reached the last point + the time until he reached the new fruit.
	 * @param game Sorted ArrayList of game from the ShortestPathAlgo class (Calculate method). 
	 * @param pac Sorted ArrayList of Pacmans in their last positions.
	 */

	public void write(ArrayList<Game> game, ArrayList<Pacman> pac) {
		StringBuilder Builder = new StringBuilder();
		for(int i=0; i<game.size(); i+=2) {
			Game Replace=game.get(i);
			Game ReplaceF=game.get(i+1);
			long Time = (int)(Double.parseDouble(Replace.getTime())*1000);
			long Temp = (int)((Double.parseDouble(Replace.getTime()) + (Double.parseDouble(Replace.getDistance()) / Double.parseDouble(Replace.getSpeed())))*1000);
			String sTime = Instant.ofEpochMilli(TimeStamp + Time).atOffset(ZoneOffset.UTC).toString();
			String eTime = Instant.ofEpochMilli(TimeStamp + Temp).atOffset(ZoneOffset.UTC).toString();
			String fTime = Instant.ofEpochMilli(TimeStamp).atOffset(ZoneOffset.UTC).toString();
			//**********Pacman**********//
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
			//**********Fruit**********//
			Builder.append("<Placemark>");
			Builder.append("<TimeSpan>");
			Builder.append("<begin>" + fTime + "</begin>");
			Builder.append("<end>" + eTime + "</end>");
			Builder.append("</TimeSpan>");
			Builder.append("<name>");
			Builder.append(ReplaceF.getType() + "ID: " + ReplaceF.getiD());
			Builder.append("</name>");
			Builder.append("<styleUrl>");
			Builder.append(ReplaceF.getPicture());
			Builder.append("</styleUrl>");
			Builder.append("<Point>");
			Builder.append("<coordinates>");
			Builder.append(ReplaceF.getPoint());
			Builder.append("</coordinates>");
			Builder.append("</Point>");
			Builder.append("</Placemark>");
		}
		for(int i=0; i<pac.size(); i++) {
			Pacman Replace=pac.get(i);
			long Time = (int)(Replace.getTime()*1000);
			long Temp = (int)(Replace.getTime() + (Replace.getDistance() / Double.parseDouble(Replace.getSpeed()))*1000);
			String sTime = Instant.ofEpochMilli(TimeStamp + Time).atOffset(ZoneOffset.UTC).toString();
			String eTime = Instant.ofEpochMilli(TimeStamp + Temp).atOffset(ZoneOffset.UTC).toString();
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

	/**
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
