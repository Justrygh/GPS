package Structure;

import java.util.ArrayList;

import Declaration.Data;
import Geom.Point3D;
import Players.Fruit;
import Players.Game;
import Players.Ghost;
import Players.Pacman;
import Players.Player;

public class Path{
	/**
	 * @author Eli.
	 * @author Qusai.
	 */
	/*** PRIVATE VARIABELS ***/
	private ArrayList<Game> Paths;
	private String _Point;
	//private double _Angel;
	private MyMap _Map = new MyMap();

	//Default Constructor.
	public Path() {
		this.Paths = new ArrayList<Game>();
	}

	//Path Full Constructor.
	public Path(ArrayList<Game> gList) {
		this.Paths = gList;
	}
	/**
	 * @return Current GameList
	 */
	public ArrayList<Game> getList() {
		return this.Paths;
	}
	/**
	 * This method gets two ArrayLists of Game and Pacman in order to create for each pacman its own path.
	 * @param List our Game List
	 * @param PList our Pacman List
	 * @return the Path 
	 */
	public ArrayList<Path> Create(ArrayList<Game> List, ArrayList<Pacman> PList) {
		ArrayList<Path> _PList = new ArrayList<Path>();
		String pac = "Pacman";
		for (int i = 0; i < PList.size(); i++) {
			Game temp = new Game(PList.get(i));
			Path it = new Path();
			it.getList().add(temp);
			for (int j = 0; j < List.size(); j++) {
				if (List.get(j).getType().equals(pac)) {
					if (List.get(j).getiD().equals(PList.get(i).getiD())) {
						it.getList().add(List.get(j + 1));
					}
				}
			}
			_PList.add(it);
		}
		return _PList;
	}

	public ArrayList<Ghost> chasePlayer(ArrayList<Ghost> list, Player player){
		for(int i=0; i<list.size(); i++) {
			Data Ghost = new Data(list.get(i));
			Data Player = new Data(player);
			Point3D vec = new Point3D(Player.getX() - Ghost.getX(), Player.getY() - Ghost.getY());
			double distance = _Map.distanceBetween2Points(new Point3D(Ghost.getX(), Ghost.getY()),
					new Point3D(Player.getX(), Player.getY()))
					- Double.parseDouble(list.get(i).getRadius());
			double time = distance / Double.parseDouble(list.get(i).getSpeed());
			if (time > 1) {
				double x = Ghost.getX() + (vec.x() / time);
				double y = Ghost.getY() + (vec.y() / time);
				_Point = (x + "," + y + "," + 0);
			}
			else {
				_Point = (Player.getX() + "," + Player.getY() + "," + 0);
			}
			list.get(i).setPoint(_Point);
		}
		ArrayList<Ghost> newgList = new ArrayList<Ghost>();
		for (int i = 0; i < list.size(); i++) {
			newgList.add(list.get(i));
		}
		return newgList;
	}

	public Player movePlayer2Fruit(ArrayList<Fruit> list, Player player) {
		Fruit temp = new Fruit();
		double distance = Integer.MAX_VALUE;
		for(int i=0; i<list.size(); i++) {
			Data Fruit = new Data(list.get(i));
			Data Player = new Data(player);
			double tempdistance = _Map.distanceBetween2Points(new Point3D(Fruit.getX(), Fruit.getY()),
					new Point3D(Player.getX(), Player.getY())) - Double.parseDouble(player.getRadius());
			if(tempdistance < distance) {
				distance = tempdistance;
				temp = list.get(i);
			}
		}
		Data Fruit = new Data(temp);
		player = movePlayer(Fruit.getiX(), Fruit.getiY(), player);
		return player;
	}

	public Player movePlayer2Pacman(ArrayList<Pacman> list, Player player) {
		Pacman temp = new Pacman();
		double distance = Integer.MAX_VALUE;
		for(int i=0; i<list.size(); i++) {
			Data Pacman = new Data(list.get(i));
			Data Player = new Data(player);
			double tempdistance = _Map.distanceBetween2Points(new Point3D(Pacman.getX(), Pacman.getY()),
					new Point3D(Player.getX(), Player.getY())) - Double.parseDouble(player.getRadius());
			if(tempdistance < distance) {
				distance = tempdistance;
				temp = list.get(i);
			}
		}
		Data Pacman = new Data(temp);
		player = movePlayer(Pacman.getiX(), Pacman.getiY(), player);
		return player;
	}

	public Player movePlayer(int x, int y, Player player) {
		Data Data = new Data(player);
		Point3D vec = new Point3D(x - Data.getX(), y - Data.getY());
		double distance = _Map.distanceBetween2Points(new Point3D(Data.getX(),Data.getY()), new Point3D(x, y)) 
				- Double.parseDouble(player.getRadius());
		Point3D newPoint =  new Point3D(Data.getX() + (vec.x()/distance*Double.parseDouble(player.getSpeed())), 
				Data.getY() + (vec.y()/distance*Double.parseDouble(player.getSpeed())));
		player.setPoint(newPoint.ix()+","+newPoint.iy()+","+0);
		return player;
	}
	
	/**
	 * This method gets ArrayList of Path and returns a new ArrayList of the Path to Print.
	 * @param pList List of paths.
	 * @return new Path to Print.
	 */
	public ArrayList<Path> Print(ArrayList<Path> pList) {

		for (int i = 0; i < pList.size(); i++) {
			if (pList.get(i).getList().size() > 1) {
				if (pList.get(i).getList().get(0).getPoint().equals(pList.get(i).getList().get(1).getPoint())) {
					pList.get(i).getList().remove(1);
				}
			}
			if (pList.get(i).getList().size() > 1) {
				Data Pac = new Data(pList.get(i).getList().get(0));
				Data Fru = new Data(pList.get(i).getList().get(1));
				Point3D vec = new Point3D(Fru.getX() - Pac.getX(), Fru.getY() - Pac.getY());
				double distance = _Map.distanceBetween2Points(new Point3D(Pac.getX(), Pac.getY()), 
						new Point3D(Fru.getX(), Fru.getY())) 
						- Double.parseDouble(pList.get(i).getList().get(0).getRadius());
				double time = distance / Double.parseDouble(pList.get(i).getList().get(0).getSpeed());
				if (time > 1) {
					double x = Pac.getX() + (vec.x() / time);
					double y = Pac.getY() + (vec.y() / time);
					_Point = (x + "," + y + "," + 0);
					//	_Angel = pList.get(i).getList().get(1).getAngel();
				} else {
					_Point = (Fru.getX() + "," + Fru.getY() + "," + 0);
					//	_Angel = pList.get(i).getList().get(1).getAngel();
					pList.get(i).getList().remove(1);
				}
				pList.get(i).getList().get(0).setPoint(_Point);
				//pList.get(i).getList().get(0).setAngel(_Angel);
			} else {
				pList.remove(i);
				i--;
			}
		}
		ArrayList<Path> newpList = new ArrayList<Path>();
		for (int i = 0; i < pList.size(); i++) {
			newpList.add(pList.get(i));
		}
		return newpList;
	}
}