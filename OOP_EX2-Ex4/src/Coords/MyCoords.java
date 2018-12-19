package Coords;

import Geom.Point3D;

/**
 * 
 * @author Eli
 * @author Qusai
 *
 */

public class MyCoords {
	/**
	 * This interface represents a basic coordinate system converter, including: 1.
	 * The 3D vector between two lat,lon, alt points 2. Adding a 3D vector in meters
	 * to a global point. 3. convert a 3D vector from meters to polar coordinates
	 * 
	 * @authors Eli & Qusai & Shai
	 *
	 */
	final double The_earth_rad = 6378 * 1000;

	/**
	 * computes a new point which is the GPS point transformed by a 3D vector (in
	 * meters) i got the information about earth radios from this site:
	 * https://en.wikipedia.org/wiki/Earth_radius.
	 * @param 3D point, Vector in meters.
	 * @return 3D point.
	 */

	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		double latitude_r = local_vector_in_meter.x() / The_earth_rad;
		double longitudes_phi = local_vector_in_meter.y() / (The_earth_rad * Math.cos(radian(gps.x())));
		double latitude_1 = gps.x() + degree(latitude_r);
		double longitudes_1 = gps.y() + degree(longitudes_phi);
		double tetha = gps.z() + local_vector_in_meter.z();
		Point3D gpsn = new Point3D(latitude_1, longitudes_1, tetha);
		return gpsn;
	}

	/*
	 * The "Haversine formula" to calculate distance between two points on a sphere.
	 * https://en.wikipedia.org/wiki/Haversine_formula
	 *
	 */
	public double distance3d(Point3D gps0, Point3D gps1) {
		double latitude_1 = gps0.x();// the latitude for x in point 0
		double longitudes_1 = gps0.y();// the longitudes for y in point 0

		double latitude_2 = gps1.x();// the lattitude for x in point 1
		double longitudes_2 = gps1.y();// the longitudes for y in point 1

		double a = 0, c = 0, distance = 0;
		double R_lat1 = radian(latitude_1);
		double R_lat2 = radian(latitude_2);
		double diff1 = latitude_1 - latitude_2;
		double diff2 = longitudes_2 - longitudes_1;

		a = Math.pow(Math.sin(radian(diff1) / 2), 2)
				+ Math.cos(R_lat1) * Math.cos(R_lat2) * Math.pow(Math.sin(radian(diff2) / 2), 2);
		c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		distance = The_earth_rad * c;// the distance we got from Haversine_formula

		return distance;
	}

	/**
	 * computes the 3D vector (in meters) between two gps like points.
	 * @param 3D points.
	 * @return Vector in meters.
	 */
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		double longnorm = Math.cos(radian(gps0.x()));
		double pointox = Math.sin((radian(gps1.x()) - radian(gps0.x()))) * The_earth_rad;
		double pointoy = Math.sin((radian(gps1.y()) - radian(gps0.y()))) * The_earth_rad * longnorm;
		double pointoz = gps1.z() - gps0.z();

		Point3D vectormeter = new Point3D(pointox, pointoy, pointoz);
		return vectormeter;
	}

	/**
	 * computes the polar representation of the 3D vector be gps0 to gps1 Note: this
	 * method should return an azimuth (aka yaw), elevation (pitch), and distance
	 * some info about this:
	 * http://tchester.org/sgm/analysis/peaks/how_to_get_view_params.html?fbclid=IwAR3y8HcMQrCSTy5AWa3--_PzH-Fw6wYkLQoYdz5ERDhLaKa63yCMRNMgVt4.
	 * @param 3D points.
	 * @return Azimuth, Elevation, Distance.
	 */
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		// at first we change them to radian.
		double longitudes_gps1 = radian(gps0.y());
		double longitudes_gps2 = radian(gps1.y());
		double latgps1 = radian(gps0.x());
		double latgps2 = radian(gps1.x());
		double alpha = longitudes_gps2 - longitudes_gps1;
		double left = Math.sin(alpha) * Math.cos(latgps2);
		double right = Math.cos(latgps1) * Math.sin(latgps2) - Math.sin(latgps1) * Math.cos(latgps2) * Math.cos(alpha);
		double azimut = Math.atan2(left, right);
		// distance//
		double distance = distance3d(gps0, gps1);
		// elevation//
		azimut = degree(azimut);
		if (azimut < 0)
			azimut += 360;
		if (azimut > 360)
			azimut -= 360;
		double high = gps1.z() - gps0.z();
		double lambda = (double) Math.asin(high / distance);// lambda = (180/pi) * [ (elev2 - elev1) / d - d / (2*R) ];
		double eleveation = degree(lambda);
		double arr[] = { azimut, eleveation, distance };
		return arr;

	}

	/**
	 * 
	 * @param x our value that we want to convert.
	 * @return the converted value.
	 */
	private double radian(double x) {
		return x * (Math.PI) / 180;
	}

	/**
	 * @param x our value that we want to convert.
	 * @return the converted value.
	 */
	private double degree(double x) {
		return x / (Math.PI) * 180;
	}
/**
 * this method is for converting from degree to radians 
 * @param p  our Point that we want to convert.
 * @return our new point after convertion.
 */
	
	public Point3D to_radian(Point3D p) {
		double x = degree(Math.asin(p.z() / The_earth_rad));
		if (x > 180)
			x -= 360;
		else
			x += 360;
		double y = degree(Math.atan2(p.y(), p.x()));
		if (y > 90)
			y -= 180;
		else
			y += 180;
		double z = Math.sqrt(Math.pow(p.x(), 2) + Math.pow(p.y(), 2) + Math.pow(p.z(), 2))- The_earth_rad;
		Point3D new_Point = new Point3D(x, y, z);
		return new_Point;
	}
	/**
	 * This method convert from radian to degree.
	 * @param p our Point that we want to convert.
	 * @return our new point after convertion.
	 */
	public Point3D to_degree(Point3D p) {
		double x = (The_earth_rad + p.z()) * Math.cos(radian(p.x())) * Math.cos(radian(p.y()));
		double y = (The_earth_rad + p.z()) * Math.cos(radian(p.x())) * Math.sin(radian(p.y()));
		double z = (The_earth_rad + p.z()) * Math.sin(radian(p.x()));
		Point3D new_Point = new Point3D(x, y, z);
		return new_Point;
	}

	/**
	 * return true iff this point is a valid lat, lon , lat coordinate:
	 * [-180,+180],[-90,+90],[-450, +inf]
	 * 
	 * @param 3D point.
	 * @return Is this point valid
	 */
	public boolean isValid_GPS_Point(Point3D p) {
		if (p.x() > -180 || p.x() < 180 || p.y() > -90 || p.y() < 90 || p.z() > -450)
			return true;
		else
			return false;

	}

}