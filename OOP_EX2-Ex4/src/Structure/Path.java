package Structure;

import java.util.ArrayList;

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
       /**
	 * This function is for chase player,we got ArrayList of Ghost's and a Player
	 * and with some Mathmatic's rules we make the Ghost's chasing the player in 
	 * each movment.
	 * @param list ArrayList of Ghost's.
	 * @param player Player in Our Game.
	 * @return returns ArrayList of Point's to Identify the Player Position.  
	 */

	public ArrayList<Ghost> chasePlayer(ArrayList<Ghost> list, Player player){
		for(int i=0; i<list.size(); i++) {
			String[] ghostData = list.get(i).getPoint().split(",");
			String[] playerData = player.getPoint().split(",");
			Point3D Ghost = new Point3D(Double.parseDouble(ghostData[0]), Double.parseDouble(ghostData[1]));
			Point3D Player = new Point3D(Double.parseDouble(playerData[0]), Double.parseDouble(playerData[1]));
			Point3D vec = new Point3D(Player.x() - Ghost.x(), Player.y() - Ghost.y());
			double distance = _Map.distanceBetween2Points(Ghost, Player)
					- Double.parseDouble(list.get(i).getRadius());
			double time = distance / Double.parseDouble(list.get(i).getSpeed());
			if (time > 1) {
				double x = Ghost.x() + (vec.x() / time);
				double y = Ghost.y() + (vec.y() / time);
				_Point = (x + "," + y + "," + 0);
			}
			else {
				_Point = (Player.x() + "," + Player.y() + "," + 0);
			}
			list.get(i).setPoint(_Point);
		}
		ArrayList<Ghost> newgList = new ArrayList<Ghost>();
		for (int i = 0; i < list.size(); i++) {
			newgList.add(list.get(i));
		}
		return newgList;
	}
/**
 * This Function moves our Player to Fruit , this mean that the Player 
 * eat each fruit (by our Algorithm that we make).
 * @param list ArrayList of Fruit's.
 * @param player Player in Our Game.
 * @return returns a Player.
 */
	public Player movePlayer2Fruit(ArrayList<Fruit> list, Player player) {
		Fruit temp = new Fruit();
		double distance = Integer.MAX_VALUE;
		for(int i=0; i<list.size(); i++) {
			String[] Fruit = (list.get(i).getPoint().split(","));
			String[] Player = (player.getPoint().split(","));
			Point3D Fru = new Point3D(Double.parseDouble(Fruit[0]), Double.parseDouble(Fruit[1]));
			Point3D Pla = new Point3D(Double.parseDouble(Player[0]), Double.parseDouble(Player[1]));
			double tempdistance = _Map.distanceBetween2Points(Fru, Pla) 
					- Double.parseDouble(player.getRadius());
			if(tempdistance < distance) {
				distance = tempdistance;
				temp = list.get(i);
			}
		}
		String[] Fruit = (temp.getPoint().split(","));
		player = movePlayer((int)Double.parseDouble(Fruit[0]), (int)Double.parseDouble(Fruit[1]), player);
		return player;
	}
/**
 * This function is For moving our Player to Pacmans (By the Algorithm that we make).
 * @param list ArrayList of Pacmans
 * @param player Player in Our Game.
 * @return returns A Player.
 */
	public Player movePlayer2Pacman(ArrayList<Pacman> list, Player player) {
		Pacman temp = new Pacman();
		double distance = Integer.MAX_VALUE;
		for(int i=0; i<list.size(); i++) {
			String[] Pacman = (list.get(i).getPoint().split(","));
			String[] Player = (player.getPoint().split(","));
			Point3D Pac = new Point3D(Double.parseDouble(Pacman[0]), Double.parseDouble(Pacman[1]));
			Point3D Pla = new Point3D(Double.parseDouble(Player[0]), Double.parseDouble(Player[1]));
			double tempdistance = _Map.distanceBetween2Points(Pac, Pla) 
					- Double.parseDouble(player.getRadius());
			if(tempdistance < distance) {
				distance = tempdistance;
				temp = list.get(i);
			}
		}
		String[] Pacman = (temp.getPoint().split(","));
		player = movePlayer((int)Double.parseDouble(Pacman[0]), (int)Double.parseDouble(Pacman[1]), player);
		return player;
	}
/**
 * This Function To move our Player in the Map/Game.
 * @param x The x Position For the Player.
 * @param y The Y Position For the Player.
 * @param player Our Player in the Game.
 * @return Player.
 */
	public Player movePlayer(int x, int y, Player player) {
		String[] Data = player.getPoint().split(",");
		Point3D oldPlayer = new Point3D(Double.parseDouble(Data[0]), Double.parseDouble(Data[1]));
		Point3D newPlayer = new Point3D(x, y);
		Point3D vec = new Point3D(newPlayer.x() - oldPlayer.x(), newPlayer.y() - oldPlayer.y());
		double distance = _Map.distanceBetween2Points(oldPlayer, newPlayer) - Double.parseDouble(player.getRadius());
		Point3D newPoint =  new Point3D(oldPlayer.x() + (vec.x()/distance*Double.parseDouble(player.getSpeed())), 
				oldPlayer.y() + (vec.y()/distance*Double.parseDouble(player.getSpeed())));
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
				String[] pacData = pList.get(i).getList().get(0).getPoint().split(",");
				String[] fruData = pList.get(i).getList().get(1).getPoint().split(",");
				Point3D Pac = new Point3D(Double.parseDouble(pacData[0]), Double.parseDouble(pacData[1]));
				Point3D Fru = new Point3D(Double.parseDouble(fruData[0]), Double.parseDouble(fruData[1]));
				Point3D vec = new Point3D(Fru.x() - Pac.x(), Fru.y() - Pac.y());
				double distance = _Map.distanceBetween2Points(Pac, Fru)
						- Double.parseDouble(pList.get(i).getList().get(0).getRadius());
				double time = distance / Double.parseDouble(pList.get(i).getList().get(0).getSpeed());
				if (time > 1) {
					double x = Pac.x() + (vec.x() / time);
					double y = Pac.y() + (vec.y() / time);
					_Point = (x + "," + y + "," + 0);
					//	_Angel = pList.get(i).getList().get(1).getAngel();
				} else {
					_Point = (Fru.x() + "," + Fru.y() + "," + 0);
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
