package File_format;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import Declaration.Filters;
import Declaration.ourFile;

class Csv2kmlTest {

	@Test
	void testWrite() {
		String S1, S2 = null;
		Csv2kml c1 = new Csv2kml("/Users/qusaitrabeh/Downloads/GPS-master/data/MultiCSV.kml");
		Csv2kml c2 = new Csv2kml("/Users/qusaitrabeh/Downloads/GPS-master/data/MultiCSV.kml");
		ourFile file1 = c1.read();
		ourFile file2 = c2.read();
		PrintWriter Print = null;

		try {
			Print = new PrintWriter(new File(file1.getName()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringBuilder Builder1 = new StringBuilder();
		Iterator<Filters> it1 = file1.getFilters().iterator();
		Builder1.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		Builder1.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");
		Builder1.append("<Document>");
		while (it1.hasNext()) {
			Filters Replace = it1.next();
			Builder1.append("<Placemark>");
			Builder1.append("<name>");
			Builder1.append(Replace.getName());
			Builder1.append("</name>");
			Builder1.append("<description>");
			Builder1.append("Ethernet: " + Replace.getDescript() + "<br/>");
			Builder1.append("Date: " + Replace.getTime() + "<br/>");
			Builder1.append("</description>");
			Builder1.append("<Point>");
			Builder1.append("<coordinates>");
			Builder1.append(Replace.getPoint());
			Builder1.append("</coordinates>");
			Builder1.append("</Point>");
			Builder1.append("</Placemark>");
		}
		Builder1.append("</Document>");
		Builder1.append("</kml>");
		S1 = Builder1.toString();

		file2 = c2.read();
		PrintWriter Print2 = null;

		try {
			Print2 = new PrintWriter(new File(file2.getName()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringBuilder Builder2 = new StringBuilder();
		Iterator<Filters> it2 = file2.getFilters().iterator();
		Builder2.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		Builder2.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");
		Builder2.append("<Document>");
		while (it2.hasNext()) {
			Filters Replace2 = it2.next();
			Builder2.append("<Placemark>");
			Builder2.append("<name>");
			Builder2.append(Replace2.getName());
			Builder2.append("</name>");
			Builder2.append("<description>");
			Builder2.append("Ethernet: " + Replace2.getDescript() + "<br/>");
			Builder2.append("Date: " + Replace2.getTime() + "<br/>");
			Builder2.append("</description>");
			Builder2.append("<Point>");
			Builder2.append("<coordinates>");
			Builder2.append(Replace2.getPoint());
			Builder2.append("</coordinates>");
			Builder2.append("</Point>");
			Builder2.append("</Placemark>");
		}
		Builder2.append("</Document>");
		Builder2.append("</kml>");
		S2 = Builder2.toString();
		assertEquals(file1.getName(), file2.getName());
	}

	@Test
	void testRead() {
		Csv2kml c1 = new Csv2kml("/Users/qusaitrabeh/Downloads/GPS-master/data/MultiCSV.kml");
		Csv2kml c2 = new Csv2kml("/Users/qusaitrabeh/Downloads/GPS-master/data/MultiCSV.kml");
		ourFile file1 = c1.read();
		ourFile file2 = c2.read();
	assertTrue(file1.getName().equals(file2.getName()));

	}

}
