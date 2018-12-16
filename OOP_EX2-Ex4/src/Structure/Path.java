package Structure;

import java.util.ArrayList;
import java.util.Date;

import Coords.MyCoords;
import Geom.Point3D;
import Players.Fruit;
import Players.Game;
import Players.Pacman;
import Threads.MyThread;

public class Path extends Thread implements Runnable{
	private ArrayList<Game> Paths;

	private long _TimeStamp = new Date().getTime() + 720000;
	private String _Point;

	private boolean isGPS;

	public Path() {
		this.Paths = new ArrayList<Game>();
	}

	public ArrayList<Game> getList(){
		return this.Paths;
	}


	public boolean getGPS() {
		return this.isGPS;
	}

	public ArrayList<Path> Create(ArrayList<Game> List, ArrayList<Pacman> PList){
		ArrayList<Path>	_PList = new ArrayList<Path>();
		String pac = "Pacman";
		for(int i=0; i<PList.size(); i++) {
			Game temp = new Game(PList.get(i));
			Path it = new Path();
			it.getList().add(temp);
			for(int j=0; j<List.size(); j++) {
				if(List.get(j).getType().equals(pac)){
					if(List.get(j).getiD().equals(PList.get(i).getiD())) {
						it.getList().add(List.get(j+1));
					}
				}
			}
			_PList.add(it);
		}
		return _PList;
	}
	public ArrayList<Path> Print(ArrayList<Path> pList){

		for(int i=0; i<pList.size(); i++) {
			if(pList.get(i).getList().get(0).getPoint().equals(pList.get(i).getList().get(1).getPoint())){
				pList.get(i).getList().remove(1);
			}
			if(pList.get(i).getList().size() > 1) {
				String[] pacData = pList.get(i).getList().get(0).getPoint().split(",");
				String[] fruData = pList.get(i).getList().get(1).getPoint().split(",");
				Point3D pac = new Point3D(Double.parseDouble(pacData[0]), Double.parseDouble(pacData[1]));
				Point3D fru = new Point3D(Double.parseDouble(fruData[0]), Double.parseDouble(fruData[1]));
				Point3D vec = new Point3D(fru.x() - pac.x(), fru.y() - pac.y());
				double distance = Math.sqrt(Math.pow(vec.x(), 2) + Math.pow(vec.y(), 2)) - Double.parseDouble(pList.get(i).getList().get(0).getRadius());
				double _Time = distance/Double.parseDouble(pList.get(i).getList().get(0).getSpeed());
				if(_Time > 1) {
					double x = pac.x() + (vec.x()/_Time);
					double y = pac.y() + (vec.y()/_Time);
					_Point = (x+","+y+","+0);
				}
				else {
					_Point = (fru.x()+","+fru.y()+","+0);
					pList.get(i).getList().remove(1);
				}
				pList.get(i).getList().get(0).setPoint(_Point);
				if(pList.get(i).getList().size() == 1) {
					pList.remove(i);
					i--;
				}
			}
			else{
				pList.remove(i);
				i--;
			}
		}
		ArrayList<Path> newpList = new ArrayList<Path>();
		for(int i=0; i<pList.size(); i++) {
			newpList.add(pList.get(i));
		}
		return newpList;
	}

}