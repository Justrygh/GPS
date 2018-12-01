package Geom;

/**
 * @author Eli 
 * @author Qusai 
 * @author Shai
 */

public class Geom implements Geom_element {
	
	//**********Private Methods**********//
	private Point3D _Geom;// we intalize our Point here
	
	
	//**********Constructor**********//
	public Geom() {
		this._Geom=null;
	}
	
	public Geom(Point3D geom) {
		this._Geom=geom;
	}
	/**
	 * 
	 * @param x direction for our vector.
	 * @param y direction for our vector.
	 * @param z direction for our vector.
	 */
	public Geom(double x,double y,double z) {
		this._Geom=new Point3D(x,y,z);
	}
	
	public Geom(double x,double y) {
		this._Geom=new Point3D(x,y);
	}
/**
 * @param p this is our 3D point;
 * @return result is the distance between our 3D Point to the other Point that we got from Geom.
 * so we calculate the distance between Geom Point to another Point.
 */
	@Override
	public double distance3D(Point3D p) {
		return _Geom.distance3D(p);
	}
	/**
	 * @param p our Point the we got.
	 * @return result 
	 * this method is for calculating our 2D distance (in the plane) between
	 *  the two Points that we got in the distance3D.
	 */

	@Override
	public double distance2D(Point3D p) {
		return _Geom.distance2D(p);
	}

}
