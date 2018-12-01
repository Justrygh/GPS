package GIS;

import Geom.Geom_element;

import Coords.MyCoords;
import GIS.Meta_data;
import Geom.Point3D;

public class Element implements GIS_element{

	@Override
	public Geom_element getGeom() {
		return _Geom;
	}

	/**
	 * computes a new point which is the gps point transformed by a 3D vector(in meters)
	 * @param vec is Point3D that I need to add to my Geom
	 */
	
	@Override
	public void translate(Point3D vec) {
		MyCoords coords = new MyCoords();
		_Geom = coords.add(_Geom, vec);
	}
	
	//**********Constructor**********//

	public Element() {}
	
	public void ElementSet(String name, String descript, String point, String time, String color) {
		this._Name = name;
		this._Descript = descript;
		this._Point = point;
		this._Time = time;
		this._M = new Metadata();
		this._M.setColor(color);
	}

	//**********Private Methods**********//
	private String _Descript;
	private String _Name;
	private String _Time;
	private String _Point;
	private Metadata _M;
	private Point3D _Geom;

	//**********Getters**********//

	@Override
	public Meta_data getData() {
		return _M;
	}

	public String getPoint() {
		return _Point;
	}

	public String getDescript() {
		return _Descript;
	}

	public String getName() {
		return _Name;
	}

	public String getTime() {
		return _Time;
	}

}
