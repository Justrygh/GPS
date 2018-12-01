package Coords;

import java.util.Arrays;
import Geom.Point3D;

public class test {

	public static void main(String[] args) {
		Point3D a=new Point3D(32.103315,35.209039,670);
		Point3D b=new Point3D(32.106352,35.205225,650);	
		MyCoords new_coord = new MyCoords();
		Point3D new_point = new_coord.vector3D(a, b);
		double new_coord_distance= new_coord.distance3d(a, b);
		double [] array_of_coords = new_coord.azimuth_elevation_dist(a, b);
		System.out.println(new_coord_distance);
		System.out.println(new_point);
		System.out.println(new_point);
		System.out.println(Arrays.toString(array_of_coords));
		

		
		

	}

}
