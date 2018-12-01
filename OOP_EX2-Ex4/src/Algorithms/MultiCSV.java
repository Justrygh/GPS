package Algorithms;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import GIS.Element;
import GIS.GIS_element;
import GIS.Layer;
import GIS.Project;

public class MultiCSV {

	/**
	 * @author Eli 
	 * @author Qusai 
	 * @author Shai
	 * This function creates the KML file and writes the header.
	 * @param File to be written.
	 */
	
	public void print(File MultiCSV) {
		try 
		{
			Print = new PrintWriter(MultiCSV);
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
		Builder.append("<Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style>");
		Builder.append("<Style id=\"orange\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/orange-dot.png</href></Icon></IconStyle></Style>");
		Builder.append("<Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style>");
		Builder.append("<Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style>");
		Builder.append("<Style id=\"blue\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/blue-dot.png</href></Icon></IconStyle></Style>");
		Builder.append("<Style id=\"purple\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/purple-dot.png</href></Icon></IconStyle></Style>");
		Builder.append("<Style id=\"pink\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/pink-dot.png</href></Icon></IconStyle></Style>");
		Builder.append("<Style id=\"pacman\"><IconStyle><Icon><href>Your/Directory/Path/OOP_EX2-EX4-master/data/Icon.png</href></Icon></IconStyle></Style>");
	    //In order to make the pacman Icon to work, you need to change the Directory PAth.
		Print.write(Builder.toString());
	}

	/*
	 * This function Writes down the elements from the Layer into the KML file.
	 */
	
	public void write(Layer layer, File MultiCSV) {
		StringBuilder Builder = new StringBuilder();
		Iterator<GIS_element> it = layer.iterator();
		while(it.hasNext()) {
			Element Replace=(Element) it.next();
			Builder.append("<Placemark>");
			Builder.append("<name>");
			Builder.append(Replace.getName());
			Builder.append("</name>");
			Builder.append("<description>");
			Builder.append("Ethernet: " + Replace.getDescript() + "<br/>");
			Builder.append("Date: " + Replace.getTime() + "<br/>");
			Builder.append("Time Stamp: " + Replace.getData().toString());
			Builder.append("</description>");
			Builder.append("<styleUrl>");
			Builder.append(Replace.getData().getColor());
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
	
	public void Close(File MultiCSV) {
		StringBuilder Builder = new StringBuilder();
		Builder.append("</Document>");
		Builder.append("</kml>");
		Print.write(Builder.toString());
		Print.close();
		System.out.println("Congrats! You did it!");
	}

	/*
	 * This function reads the CSV file.
	 * For each row in the file it creates a new element and inserts it into the Layer Collection.
	 * At the end of the function, it takes the Layer and inserts it into the Project Collection.
	 * @return this function returns the Layer and sends it to the Write function.
	 */
	
	public Layer read(File Csv) {
		String L = "";
		String S = ",";
		Layer _List = new Layer();
		try (BufferedReader Buffer = new BufferedReader(new FileReader(Csv))) {
			Buffer.readLine();
			Buffer.readLine();
			while ((L = Buffer.readLine()) != null) {
				String[] Data = L.split(S);
				Element place = new Element();
				place.ElementSet(Data[1], Data[10], Data[7]+","+Data[6]+","+Data[8], Data[3], _List.get_Meta_data().getColor());
				_List.add(place);
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return _List;
	}

	/* 
	 * This function gets the location of the CSV files.
	 * In order to do that you need to define your CSV folder path.
	 * For example: /home/eli/eclipse-workspace/OOP_EX2-EX4-master/data
	 * This function accepts .csv files ONLY!
	 */
	public Project ListOfCSV(File MultiCSV) {
		Project _pList = new Project();
		File[] files = new File("/home/eli/eclipse-workspace/OOP_EX2-EX4-master/data").listFiles(
				new FilenameFilter() { @Override public boolean accept(File dir, String name) { return name.endsWith(".csv"); } });
		print(MultiCSV);
		for(int i=0; i<files.length; i++) {
			File name = files[i];
			Layer temp = read(name);
			write(temp, MultiCSV);
			_pList.add(temp);
		}
		Close(MultiCSV);
		return _pList;
	}

	//**********Private Methods**********//

	private PrintWriter Print;

	//**********Constructor**********//

	public MultiCSV() {}

	//**********Getters**********//

}
