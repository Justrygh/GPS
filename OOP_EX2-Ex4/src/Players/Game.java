package Players;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import Declaration.ourFile;
import Players.Fruit;
import Players.Pacman;
/**
 * @author Eli.
 * @author Qusai.
 *
 */
public class Game {
	/**
	 * @author Eli.
	 * @author Qusai.
	 * In this Write method we Write All the Data that we got from
	 * the Read Method. using the ArrayList that we returned from the read().
	 * @param _List ArrayList that we returned from the read().
	 */

	public void write(ArrayList<Game> _List) {
		ourFile file = new ourFile(_Game.getName());
		PrintWriter Print = null;
		try {
			Print = new PrintWriter(new File(_Game.getParent(), file.getName()));
		}
		catch(Exception e){
			System.err.println("There's no such file or directory");
		}
		StringBuilder Builder = new StringBuilder();
		Iterator<Game> it = _List.iterator();
		Builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		Builder.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");
		Builder.append("<Document>");
		Builder.append("<Style id=\"Pacman\"><IconStyle><Icon><href>newdata/Pacman.png</href></Icon></IconStyle></Style>");
		Builder.append("<Style id=\"Fruit\"><IconStyle><Icon><href>newdata/Fruit.png</href></Icon></IconStyle></Style>");
		Builder.append("<Style id=\"Apple\"><IconStyle><Icon><href>newdata/Apple.png</href></Icon></IconStyle></Style>");
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
/**
 * In this Method we read all the data that we need to our Game 
 * using BufferedReader that Split all the data in Our CSV File 
 * using ",".
 * @return returned the readable ArrayList that we use in the Write() method.
 */
	public ArrayList<Game> read(){
		String L = "";
		String S = ",";
		String id = "id";
		ArrayList<Game> _List = new ArrayList<Game>();
		try(BufferedReader Buffer = new BufferedReader(new FileReader(_Game))){
			L = Buffer.readLine();
			String[] Check = L.split(S);
			if(Check[1].equals(id)) {
				while((L = Buffer.readLine()) != null) {
					String[] Data = L.split(S);
					String P = "P";
					String F = "F";
					if(Data[0].equals(P)) {
						setType(Data[0]+"acman");
						setPoint(Data[3]+","+Data[2]+","+Data[4]);
						setSpeed(Data[5]);
						setRadius(Data[6]);
						setPicture("Pacman");
						setiD(Data[1]);
					}
					if(Data[0].equals(F)) {
						setType(Data[0]+"ruit");
						setPoint(Data[3]+","+Data[2]+","+Data[4]);
						setSpeed("");
						setRadius("");
						setPicture(fruitsIcon());
						setiD(Data[1]);
					}
					Game temp = new Game(getType(), getPoint(), getSpeed(), getRadius(), getPicture(), getiD());
					_List.add(temp);
				}
				return _List;
			}
			else {
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
						setiD(String.valueOf(i));
						i++;
					}
					if(Data[0].equals(F)) {
						setType(Data[0]+"ruit");
						setPoint(Data[2]+","+Data[1]+","+Data[3]);
						setSpeed("");
						setRadius("");
						setPicture(fruitsIcon());
						setiD(String.valueOf(j));
						j++;
					}
					Game temp = new Game(getType(), getPoint(), getSpeed(), getRadius(), getPicture(), getiD());
					_List.add(temp);
				}
				return _List;
			}
		}
		catch(Exception e) {

		}
		return _List;
	}
/**
 * Simplest method that creates in each iteration a Random 
 * icon to the Fruit Object.
 * @return A Random Fruit icon. 
 */
	public String fruitsIcon() {
		String[] Fruits = {"Fruit", "Apple"};
		Random random = new Random();
		int Select = random.nextInt(Fruits.length);
		String newFruit = Fruits[Select];
		return newFruit;
	}
	
	//**********Constructor**********//

    // Default Constructor. 
	public Game() {}
    /**
     * The Game Constructor  for the Fruit Object. 
     * @param fruit Fruit Object
     */
	public Game(Fruit fruit) {
		this._Type = fruit.getType();
		this._Point = fruit.getPoint();
		this._Pic = fruit.getPicture();
		this._iD = fruit.getiD();
		this._Speed = "";
		this._Radius = "";
		this._Dis = "";
		this._Time = String.valueOf(fruit.getTime());
		this._Angel = fruit.getAngel();
	}
/**
 * Game Full Constructor.
 * @param type the Type of each Object (Fruit/Pacman).
 * @param point the Point of each Object in the Game (Fruit/Pacman).
 * @param speed the Speed is specific for Pacmans and to the Fruits is "NULL".
 * @param radius the Radius is specific for Pacmans and to the Fruits is "NULL".
 * @param picture the Pic of each icon.
 * @param id the ID for each Player(Fruit/Pacman).
 */
	public Game(String type, String point, String speed, String radius, String picture, String id) {
		this._Type = type;
		this._Point = point;
		this._Speed = speed;
		this._Radius = radius;
		this._Pic = picture;
		this._iD = id;
		this._Angel = 0;
	}
	
	public Game(String type, String point, String speed, String radius, String id) {
		this._Type = type;
		this._Point = point;
		this._Speed = speed;
		this._Radius = radius;
		this._iD = id;
		this._Angel = 0;
	}
	
/**
 * the Game Constructor for the Pacman Object. 
 * @param pacman our Object/Player.
 */
	public Game(Pacman pacman) {
		this._Type = pacman.getType();
		this._Point = pacman.getPoint();
		this._Pic = pacman.getPicture();
		this._Speed = pacman.getSpeed();
		this._Radius = pacman.getRadius();
		this._Time = String.valueOf(pacman.getTime());
		this._FruitsEaten = String.valueOf(pacman.getFruitsEaten());
		this._Dis = String.valueOf(pacman.getDistance());
		this._iD = pacman.getiD();
		this._Angel = pacman.getAngel();
	}
/**
 * Constructor for the KML File that we create to use it in Google Earth.
 * @param KML the KML File that we create to use it in Google Earth.
 */
	public Game(File KML) {
		this._Game = KML;
	}

	//**********Private Methods**********//

	private File _Game;
	private String _Type;
	private String _Point;
	private String _Pic;
	private String _Speed;
	private String _Radius;
	private String _Time;
	private String _FruitsEaten;
	private String _Dis;
	private String _iD;
	private double _Angel;
	private int i = 0;
	private int j = 0;

	//**********Getters**********//

	public File getFile() {
		return this._Game;
	}

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

	public String getTime() {
		return this._Time;
	}

	public String getFruitsEaten() {
		return this._FruitsEaten;
	}

	public String getDistance() {
		return this._Dis;
	}
	
	public String getiD() {
		return this._iD;
	}

	//**********Setters**********//

	private void setType(String type) {
		this._Type = type;
	}

	public void setPoint(String point) {
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
	
	private void setiD(String id) {
		this._iD = id;
	}
	public double getAngel() {
		return _Angel;
	}
	public void setAngel(double angel) {
		this._Angel = angel;
	}

}