package Structure;

import java.util.ArrayList;
import Geom.Point3D;
import Players.Game;
import Players.Pacman;

public class Path{
	/**
	 * @author Eli.
	 * @author Qusai.
	 */
	/*** PRIVATE VARIABELS ***/
	private ArrayList<Game> Paths;
	private String _Point;
	private Map _Map = new Map();

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
	 * This method gets ArrayList of Path and returns a new ArrayList of the Path to Print.
	 * @param pList List of paths.
	 * @return new Path to Print.
	 */
	public ArrayList<Path> Print(ArrayList<Path> pList) {

		for (int i = 0; i < pList.size(); i++) {
			if (pList.get(i).getList().get(0).getPoint().equals(pList.get(i).getList().get(1).getPoint())) {
				pList.get(i).getList().remove(1);
			}
			if (pList.get(i).getList().size() > 1) {
				String[] pacData = pList.get(i).getList().get(0).getPoint().split(",");
				String[] fruData = pList.get(i).getList().get(1).getPoint().split(",");
				Point3D Pac = new Point3D(Double.parseDouble(pacData[0]), Double.parseDouble(pacData[1]));
				Point3D Fru = new Point3D(Double.parseDouble(fruData[0]), Double.parseDouble(fruData[1]));
				Point3D vec = new Point3D(Fru.x() - Pac.x(), Fru.y() - Pac.y());
				double distance = _Map.distanceBetween2Points(Pac, Fru)
						- Double.parseDouble(pList.get(i).getList().get(0).getRadius());
				double _Time = distance / Double.parseDouble(pList.get(i).getList().get(0).getSpeed());
				if (_Time > 1) {
					double x = Pac.x() + (vec.x() / _Time);
					double y = Pac.y() + (vec.y() / _Time);
					_Point = (x + "," + y + "," + 0);
				} else {
					_Point = (Fru.x() + "," + Fru.y() + "," + 0);
					pList.get(i).getList().remove(1);
				}
				pList.get(i).getList().get(0).setPoint(_Point);
				if (pList.get(i).getList().size() == 1) {
					pList.remove(i);
					i--;
				}
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