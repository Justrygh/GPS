package Players;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Declaration.Filters;
import Declaration.ourFile;
import GIS.Element;
import Players.Fruit;
import Players.Pacman;

public class Game {

	public void write(ArrayList<Game> _List, File KML) {
		ourFile file = new ourFile(KML.getName());
		PrintWriter Print = null;
		try {
			Print = new PrintWriter(new File(KML.getParent(), file.getName()));
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
		Builder.append("<Style id=\"Lime\"><IconStyle><Icon><href>/home/eli/eclipse-workspace/OOP_EX2-EX4-master/newdata/Lime.png</href></Icon></IconStyle></Style>");
		while(it.hasNext()) {
			Game Replace = it.next();
			Builder.append("<Placemark>");
			Builder.append("<name>");
			Builder.append(Replace.getType());
			Builder.append("</name>");
			Builder.append("<description>");
			Builder.append("Speed: " + Replace.getSpeed() + "<br/>");
			Builder.append("Radius: " + Replace.getRadius() + "<br/>");
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

	public ArrayList<Game> read(File KML){
		String L = "";
		String S = ",";
		ArrayList<Game> _List = new ArrayList<Game>();
		try(BufferedReader Buffer = new BufferedReader(new FileReader(KML))){
			Buffer.readLine();
			while((L = Buffer.readLine()) != null) {
				String[] Data = L.split(S);
				String P = "P";
				String F = "F";
				if(Data[0].equals(P)) {
					setType(Data[0]+"acman");
					setPoint(Data[2]+","+Data[1]+","+Data[3]);
					setSpeed(Data[4]);
					setRadius(Data[5]);
					setPicture("Pacman");
				}
				if(Data[0].equals(F)) {
					setType(Data[0]+"ruit");
					setPoint(Data[2]+","+Data[1]+","+Data[3]);
					setSpeed("0");
					setRadius("0");
					setPicture(Fruits());
				}
				Game temp = new Game(getType(), getPoint(), getSpeed(), getRadius(), getPicture());
				_List.add(temp);
			}
			return _List;
		}
		catch(Exception e) {

		}
		return _List;
	}
	
	public String Fruits() {
		String[] Fruits = {"Fruit", "Apple", "Lime"};
		Random random = new Random();
		int Select = random.nextInt(Fruits.length);
		String newColor = Fruits[Select];
		if(_Fruits.size() == 7) {
			_Fruits.clear();
			_Fruits.add(Select);
			return newColor;
		}
		while(_Fruits.contains(Select)) {
			Select = random.nextInt(Fruits.length);
			newColor = Fruits[Select];
		}
		_Fruits.add(Select);
		return newColor;
	}
	//**********Constructor**********//
	
	private ArrayList<Integer> _Fruits = new ArrayList<Integer>();
	
	public Game() {}

	public Game(Fruit fruit) {
		this._Type = fruit.getType();
		this._Point = fruit.getPoint();
		this._Pic = fruit.getPicture();
		this._Speed = "0";
		this._Radius = "0";

	}
	
	public Game(String type, String point, String speed, String radius, String picture) {
		this._Type = type;
		this._Point = point;
		this._Speed = speed;
		this._Radius = radius;
		this._Pic = picture;
	}

	public Game(Pacman pacman) {
		this._Type = pacman.getType();
		this._Point = pacman.getPoint();
		this._Pic = pacman.getPicture();
		this._Speed = pacman.getSpeed();
		this._Radius = pacman.getRadius();

	}

	//**********Private Methods**********//
	
	private String _Type;
	private String _Point;
	private String _Pic;
	private String _Speed;
	private String _Radius;

	//**********Getters**********//

	public String getType() {
		return this._Type;
	}

	public String getPoint() {
		return this._Point;
	}

	public String getPicture() {
		return this._Pic;
	}

	public String getSpeed() {
		return this._Speed;
	}

	public String getRadius() {
		return this._Radius;
	}
	
	//**********Setters**********//
	
	private void setType(String type) {
		this._Type = type;
	}
	
	private void setPoint(String point) {
		this._Point = point;
	}
	
	private void setPicture(String pic) {
		this._Pic = pic;
	}
	
	private void setSpeed(String speed) {
		this._Speed = speed;
	}
	
	private void setRadius(String radius) {
		this._Radius = radius;
	}
}