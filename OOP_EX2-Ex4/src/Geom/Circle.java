package Geom;

import Geom.Point3D;
/**
 * This class represents a 2D (open set) circle
 * @author ben-moshe
 *
 */
public class Circle implements Geom_element {
	private Point3D _cen;
	private double _radius;
	public Circle(Point3D cen, double rad) {
		this.set_cen(new Point3D(cen));
		this.set_radius(rad);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double distance3D(Point3D p) {
		Point3D z = new Point3D(_cen.x(), _cen.y());
		set_cen(z);
		double d = p.distance2D(this.get_cen());
		double dr = d - this.get_radius();
		double ans = Math.max(0, dr);
		return ans;
	}

	@Override
	public double distance2D(Point3D p) {
		double d = p.distance3D(this.get_cen());
		double dr = d - this.get_radius();
		double ans = Math.max(0, dr);
		return ans;
	}

	public Point3D get_cen() {
		return _cen;
	}

	private void set_cen(Point3D _cen) {
		this._cen = _cen;
	}

	public double get_radius() {
		return _radius;
	}

	private void set_radius(double _radius) {
		this._radius = _radius;
	}

}
