package Algorithms;
import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import Coords.MyCoords;
import Geom.Point3D;
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

	public ArrayList<Game> Calculate() {
		double time = Integer.MAX_VALUE; 
		MyCoords Test = new MyCoords();
		for(int i=0; i<_Pacmans.size(); i++) {
			for(int j=0; j<_Fruits.size(); j++) {
				double speed = Integer.parseInt(_Pacmans.get(i).getSpeed());
				String[] arrP = (_Pacmans.get(i).getPoint().split(","));
				String[] arrF = (_Fruits.get(j).getPoint().split(","));
				Point3D Pac = new Point3D(Double.parseDouble(arrP[0]), Double.parseDouble(arrP[1]), Double.parseDouble(arrP[2]));
				Point3D Fru = new Point3D(Double.parseDouble(arrF[0]), Double.parseDouble(arrF[1]), Double.parseDouble(arrF[2]));
				double distance = Test.distance3d(Pac, Fru) - Double.parseDouble(_Pacmans.get(i).getRadius());
				double temp = distance/speed;
				if(this._Pacman != null) {
					if(temp < time && (_Pacmans.get(i).getTime() + temp) < (this._Pacman.getTime() + this._Time)) {
						time = temp;
						setTime(time);
						setPacman(_Pacmans.get(i));
						setFruit(_Fruits.get(j));
						setDistance(distance);
					}
				}
				else {
					if(temp < time) {
						time = temp;
						setTime(time);
						setPacman(_Pacmans.get(i));
						setFruit(_Fruits.get(j));
						setDistance(distance);
					}
				}
			}
		}
		while(_Fruits.size()>0) {
			createPath(getPacman(), getFruit(), getTime());
			Calculate();
		}
		
		ArrayList<Game> _List = new ArrayList<Game>();
		for(int i=0; i<_Pacmans.size(); i++) {
			Game temp = new Game(_Pacmans.get(i));
			_List.add(temp);
		}
		
		return _List;
	}

	public void Zero() {
		this._Pacman = null;
		this._Fruit = null;
		this._Distance = 0;
		this._Time = 0;
	}

	public void createPath(Pacman pacman, Fruit fruit, double time) {
		pacman.setTime(time);
		pacman.setPoint(fruit.getPoint());
		pacman.FruitsEaten();
		_Fruits.remove(fruit);
		Zero();
	}
	private Fruit _Fruit;
	private Pacman _Pacman;
	private double _Distance;
	private double _Time;
	private ArrayList<Fruit> _Fruits = new ArrayList<Fruit>();
	private ArrayList<Pacman> _Pacmans = new ArrayList<Pacman>();

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

}
