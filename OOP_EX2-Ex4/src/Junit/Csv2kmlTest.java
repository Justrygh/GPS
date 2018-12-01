package File_format;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import org.junit.jupiter.api.Test;
import Declaration.ourFile;

class Csv2kmlTest {

	@Test
	void testWrite() {
		File M1 = new File("/Users/qusaitrabeh/Downloads/GPS-master/data/MultiCSV.kml");
		File M2 = new File("/Users/qusaitrabeh/Downloads/GPS-master/data/MultiCSV.kml");
		Csv2kml c1 = new Csv2kml("Your/Directory/Path/OOP_EX2-EX4-master/data/MultiCSV.kml");
		Csv2kml c2 = new Csv2kml("Your/Directory/Path/OOP_EX2-EX4-master/data/MultiCSV.kml");
		c1.write();
		c2.write();
		assertEquals(M1.canWrite(),M2.canWrite());
	}

	@Test
	void testRead() {
		Csv2kml c1 = new Csv2kml("Your/Directory/Path/OOP_EX2-EX4-master/data/MultiCSV.kml");
		Csv2kml c2 = new Csv2kml("Your/Directory/Path/OOP_EX2-EX4-master/data/MultiCSV.kml");
		ourFile file1 = c1.read();
		ourFile file2 = c2.read();
	assertTrue(file1.getName().equals(file2.getName()));

	}

}
