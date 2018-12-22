package Algorithms;
import java.util.ArrayList;
import Geom.Point3D;
import Players.Fruit;
import Players.Pacman;
import Structure.Map;
import Players.Game;
/**
 * 
 * @author Eli.
 * @author Qusai.
 *
 */

public class ShortestPathAlgo {
	
	/**
	 * Constructor that gets an ArrayList of Game.
	 * @param game List.
	 */

	public ShortestPathAlgo(ArrayList<Game> game) {
		String P = "Pacman";
		String F = "Fruit"; 
		for(int i=0; i<game.size(); i++) {
			Game g = game.get(i);
			if(g.getType().equals(F)) {
				Fruit Fruit = new Fruit(g.getType(), g.getPoint(), g.getPicture(), g.getiD());
				_Fruits.add(Fruit);
			}
			else if(g.getType().equals(P)) {
				Pacman Pacman = new Pacman(g.getType(), g.getPoint(), g.getSpeed(), g.getRadius(), g.getPicture(), g.getiD());
				_Pacmans.add(Pacman);
			}
		}
	}
	/**
	 * This method is a simple Algorithm that calculates the Shortest Path from
	 *  Pacman to the nearest Fruit.
	 * @return returned the calculated path.
	 */
	public ArrayList<Game> calculatePath() {
		double time = Integer.MAX_VALUE; 
		for(int i=0; i<_Pacmans.size(); i++) {
			for(int j=0; j<_Fruits.size(); j++) {
				double speed = Double.parseDouble(_Pacmans.get(i).getSpeed());
				String[] arrP = (_Pacmans.get(i).getPoint().split(","));
				String[] arrF = (_Fruits.get(j).getPoint().split(","));
				Point3D Pac = new Point3D(Double.parseDouble(arrP[0]), Double.parseDouble(arrP[1]));
				Point3D Fru = new Point3D(Double.parseDouble(arrF[0]), Double.parseDouble(arrF[1]));
				double distance = Map.distanceBetween2Points(Pac, Fru) 
						- Double.parseDouble(_Pacmans.get(i).getRadius());
				if(distance < 0)
					distance = 0;
				double temp = distance/speed;
				double angel = Map.angelBetween2Points(Pac, Fru);
				if(this._Pacman != null) {
					if(temp < time && (_Pacmans.get(i).getTime() + temp) < (this._Pacman.getTime() + this._Time)) {
						time = temp;
						setTime(time);
						setPacman(_Pacmans.get(i));
						setFruit(_Fruits.get(j));
						setDistance(distance);
						setAngel(angel);
					}
				}
				else {
					if(temp < time) {
						time = temp;
						setTime(time);
						setPacman(_Pacmans.get(i));
						setFruit(_Fruits.get(j));
						setDistance(distance);
						setAngel(angel);
					}
				}
			}
		}
		while(_Fruits.size()>0) {
			createPath();
			calculatePath();
		}
		
		return _List;
	}
   /**
    * This method resets our private methods.
    */
	public void zero() {
		this._Pacman = null;
		this._Fruit = null;
		this._Angel = 0;
		this._Distance = 0;
		this._Time = 0;
	}
	/**
	 * This method adds our Pacman and Fruit that we got from the Algorithm to a new Game List by order.
	 * First the Pacman and then the Fruit, the pacman suppose to eat next. 
	 */
	public void add() {
		this._Fruit.setAngel(_Angel);
		_List.add(new Game(this._Pacman));
		_List.add(new Game(this._Fruit));
	}
	
   /**
    * This method updates the pacman after he ate each fruit.
    * 
    */
	public void createPath() {
		add();
		
		this._Pacman.FruitsEaten();
		this._Pacman.setTime(this._Time);
		this._Pacman.setDistance(this._Distance);
		this._Pacman.setPoint(this._Fruit.getPoint());
		_Fruits.remove(this._Fruit);
		zero();
	}
	
	/*********PRIVATE VARIABELS*********/
	private Fruit _Fruit;
	private Pacman _Pacman;
	private double _Distance;
	private double _Time;
	private double _Angel;
	private Map Map = new Map();
	private ArrayList<Fruit> _Fruits = new ArrayList<Fruit>();
	private ArrayList<Pacman> _Pacmans = new ArrayList<Pacman>();
	private ArrayList<Game> _List = new ArrayList<Game>();
	
    /*********Getters&Setters*********/
	private void setDistance(double distance) {
		this._Distance = distance;
	}

	private void setTime(double time) {
		this._Time = time;
	}
	
	private void setPacman(Pacman pacman) {
		this._Pacman = pacman;
	}
	
	private void setFruit(Fruit fruit) {
		this._Fruit = fruit;
	}
	
	private void setAngel(double angel) {
		this._Angel = angel;
	}
	
	public double getAngel() {
		return this._Angel;
	}
	
	public Pacman getPacman() {
		return this._Pacman;
	}
	
	public Fruit getFruit() {
		return this._Fruit;
	}

	public double getTime() {
		return this._Time;
	}

	public double getDistance() {
		return this._Distance;
	}
	
	public ArrayList<Pacman> getPList(){
		return this._Pacmans;
	}
	
	public ArrayList<Fruit> getFList(){
		return this._Fruits;
	}
}