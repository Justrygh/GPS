package Junit;

import Geom.Geom;
import Geom.Point3D;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GeomTest {

	@Test
	void testDistance3D() {
		Geom the_first_one = new Geom(5, 5, 5);
		Point3D the_second = new Point3D(3.6, 2.9, 6.9);
		double dis = the_first_one.distance3D(the_second);
		assertTrue(dis >= 3.15 && dis <= 3.16);
	}

	@Test
	void testDistance2D() {
		Geom the_first_one = new Geom(5, 5);
		Point3D the_second = new Point3D(3.6, 2.9);
		double dis= the_first_one.distance2D(the_second);
		assertTrue(dis >= 2.52 && dis <= 2.53);
	}

}