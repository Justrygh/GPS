package Junit;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import Coords.MyCoords;
import Geom.Point3D;

class MyCoordsTest {

	@Test
	void addTest() {
		MyCoords new_coord = new MyCoords();
		Point3D a = new Point3D(32.10332, 35.20904, 670);
		Point3D b = new Point3D(337.699, -359.249, -20);
		Point3D new_Point = new_coord.add(a, b);
		Point3D expected_Point = new Point3D(32.10635366689335, 35.205230187940515, 650.0);
		//here for checking the two gps POINTS we should use the distance between them that should be 0
		double result=new_coord.distance3d(new_Point, expected_Point);
		if(result!=0) {
			fail(" ERR>> the distance between them that should be 0");
		}

	}

	@Test
	void testDistance3d() {
		MyCoords new_coord = new MyCoords();
		Point3D the_first_gps_point = new Point3D(32.103315, 35.209039, 670);
		Point3D the_second_gps_point = new Point3D(32.106352, 35.205225, 650);
		double result_1 = new_coord.distance3d(the_first_gps_point, the_second_gps_point);
		double result_2=493.5897055794331;
		assertEquals(result_1, result_2);
	}

	@Test
	void testVector3D() {
		MyCoords new_coord = new MyCoords();
		Point3D the_first_gps_point = new Point3D(32.103315, 35.209039, 670);
		Point3D the_second_gps_point = new Point3D(32.106352, 35.205225, 650);
		Point3D a = new_coord.vector3D(the_first_gps_point, the_second_gps_point);
		//System.err.print(a.toString());
		Point3D b = new Point3D(338.0700316067363,-359.6439243220462, -20.0);
		assertEquals(a.toString(), b.toString());
	}

	@Test
	void testAzimuth_elevation_dist() {
		boolean is_valid = true;
		MyCoords new_coord = new MyCoords();
		Point3D the_first_gps_point = new Point3D(32.103315, 35.209039, 670);
		Point3D the_second_gps_point = new Point3D(32.106352, 35.205225, 650);
		double[] actual = new_coord.azimuth_elevation_dist(the_first_gps_point, the_second_gps_point);
		double[] expected_one = { 313.2304203264993, -2.3222311473630652, 493.5897055794331 };
		// here we run on the a array to check if the values equals to expected_one
		// values.
		for (int i = 0; i < 3; i++) {
			if (!Arrays.equals(actual, expected_one)) {
				is_valid = false;
			}
		}
		assertTrue(is_valid);
	}

	@Test
	void testIsValid_GPS_Point() {
		MyCoords new_coord = new MyCoords();
		Point3D the_first_gps_point = new Point3D(32.103315, 35.209039, 670);
		if (!new_coord.isValid_GPS_Point(the_first_gps_point)) {
			fail("ERR try another GPS point in range >>[-180,+180],[-90,+90],[-450, +inf]<<");
		}
	}

	@Test
	void testIsValid_GPS_Point_Another_test() {
		MyCoords new_coord = new MyCoords();
		Point3D the_second_gps_point = new Point3D(32.106352, 35.205225, 650);
		if (!new_coord.isValid_GPS_Point(the_second_gps_point)) {
			fail("ERR try another GPS point in range >>[-180,+180],[-90,+90],[-450, +inf]<<");
		}
	}
	@Test
	void testIsValid_GPS_Point_Another_test_1() {
		MyCoords new_coord = new MyCoords();
		Point3D the_second_gps_point = new Point3D(31.950214, 35.060431, 356);
		if (!new_coord.isValid_GPS_Point(the_second_gps_point)) {
			fail("ERR try another GPS point in range >>[-180,+180],[-90,+90],[-450, +inf]<<");
		}
	}
	@Test
	void test_to_radian() {
		MyCoords new_coord = new MyCoords();
		Point3D a=new Point3D(32.103315,35.209039,670);
		Point3D a_to_radian=new_coord.to_radian(a);
		Point3D expected=new Point3D(360.00601884169714,227.6416958840577,-6377328.307883579);
		if(!a_to_radian.equals(expected)) {
			fail("ERR : the radian function isn't converting currect try to fix it!!");
		}
		
	}
}
