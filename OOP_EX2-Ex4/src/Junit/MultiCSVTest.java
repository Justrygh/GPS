package Junit;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import org.junit.jupiter.api.Test;

import Algorithms.MultiCSV;
import GIS.Element;
import GIS.GIS_element;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import GIS.Layer;
import GIS.Project;

class MultiCSVTest {
	private PrintWriter Print;

	@Test
	void testPrint() {
		File M1 = new File("Your/Directory/Path/OOP_EX2-EX4-master/data/MultiCSV.kml");

		try {
			Print = new PrintWriter(M1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		StringBuilder Builder = new StringBuilder();
		Builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		Builder.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");
		Builder.append("<Document>");
		Builder.append(
				"<Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style>");
		Builder.append(
				"<Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style>");
		Builder.append(
				"<Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style>");
		Print.write(Builder.toString());
		File M2 = new File("Your/Directory/Path/OOP_EX2-EX4-master/data/MultiCSV.kml");
		try {
			Print = new PrintWriter(M2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		StringBuilder Builder1 = new StringBuilder();
		Builder1.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		Builder1.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");
		Builder1.append("<Document>");
		Builder1.append(
				"<Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style>");
		Builder1.append(
				"<Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style>");
		Builder1.append(
				"<Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style>");
		Print.write(Builder1.toString());
		assertEquals(M1, M2);

	}

	@Test
	void testWrite() {
		Layer layer1 = new Layer();
		StringBuilder Builder1 = new StringBuilder();
		Iterator<GIS_element> it1 = layer1.iterator();
		while (it1.hasNext()) {
			Element Replace1 = (Element) it1.next();
			Builder1.append("<Placemark>");
			Builder1.append("<name>");
			Builder1.append(Replace1.getName());
			Builder1.append("</name>");
			Builder1.append("<description>");
			Builder1.append("Ethernet: " + Replace1.getDescript() + "<br/>");
			Builder1.append("Date: " + Replace1.getTime() + "<br/>");
			Builder1.append("Time Stamp: " + Replace1.getData().toString());
			Builder1.append("</description>");
			Builder1.append("<styleUrl>");
			Builder1.append(Replace1.getData().getColor());
			Builder1.append("</styleUrl>");
			Builder1.append("<Point>");
			Builder1.append("<coordinates>");
			Builder1.append(Replace1.getPoint());
			Builder1.append("</coordinates>");
			Builder1.append("</Point>");
			Builder1.append("</Placemark>");
		}
		Layer layer2 = new Layer();

		StringBuilder Builder2 = new StringBuilder();
		Iterator<GIS_element> it2 = layer2.iterator();
		while (it1.hasNext()) {
			Element Replace2 = (Element) it2.next();
			Builder2.append("<Placemark>");
			Builder2.append("<name>");
			Builder2.append(Replace2.getName());
			Builder2.append("</name>");
			Builder2.append("<description>");
			Builder2.append("Ethernet: " + Replace2.getDescript() + "<br/>");
			Builder2.append("Date: " + Replace2.getTime() + "<br/>");
			Builder2.append("Time Stamp: " + Replace2.getData().toString());
			Builder2.append("</description>");
			Builder2.append("<styleUrl>");
			Builder2.append(Replace2.getData().getColor());
			Builder2.append("</styleUrl>");
			Builder2.append("<Point>");
			Builder2.append("<coordinates>");
			Builder2.append(Replace2.getPoint());
			Builder2.append("</coordinates>");
			Builder2.append("</Point>");
			Builder2.append("</Placemark>");
		}
		String S1, S2 = null;
		S1 = Builder1.toString();
		S2 = Builder2.toString();
		assertTrue(S1.equals(S2));
	}

	@Test
	void testClose() {
		String S1, S2 = null;
		StringBuilder Builder1 = new StringBuilder();
		Builder1.append("</Document>");
		Builder1.append("</kml>");
		S1 = Builder1.toString();
		StringBuilder Builder2 = new StringBuilder();
		Builder2.append("</Document>");
		Builder2.append("</kml>");
		S2 = Builder2.toString();
		if (!S1.contentEquals(S2)) {
			fail("ERR");
		}
	}

	@Test
	void testRead() {
		File _Csv1 = new File("Your/Directory/Path/OOP_EX2-EX4-master/data/MultiCSV.kml");
		String L = "";
		String S = ",";
		Layer _List = new Layer();
		try (BufferedReader Buffer = new BufferedReader(new FileReader(_Csv1))) {
			Buffer.readLine();
			Buffer.readLine();
			while ((L = Buffer.readLine()) != null) {
				String[] Data = L.split(S);
				Element place = new Element();
				place.ElementSet(Data[1], Data[10], Data[7] + "," + Data[6] + "," + Data[8], Data[3],
						_List.get_Meta_data().getColor());
				_List.add(place);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		File _Csv2 = new File("Your/Directory/Path/OOP_EX2-EX4-master/data/MultiCSV.kml");

		String L1 = "";
		String S1 = ",";
		Layer _List1 = new Layer();
		try (BufferedReader Buffer1 = new BufferedReader(new FileReader(_Csv1))) {
			Buffer1.readLine();
			Buffer1.readLine();
			while ((L1 = Buffer1.readLine()) != null) {
				String[] Data = L1.split(S1);
				Element place = new Element();
				place.ElementSet(Data[1], Data[10], Data[7] + "," + Data[6] + "," + Data[8], Data[3],
						_List1.get_Meta_data().getColor());
				_List1.add(place);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(_Csv2, _Csv1);
	}

	@Test
	void testListOfCSV() {
		MultiCSV c1 = new MultiCSV();
		File _Csv1 = new File("/Users/qusaitrabeh/Downloads/GPS-master/data/MultiCSV.kml");
		Project _pList1 = new Project();
		File[] files1 = new File("Your/Directory/Path/OOP_EX2-EX4-master/data").listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
		});
		c1.print(_Csv1);
		for (int i = 0; i < files1.length; i++) {
			File name = files1[i];
			Layer temp = c1.read(name);
			c1.write(temp, _Csv1);
			_pList1.add(temp);
		}
		c1.Close(_Csv1);

		MultiCSV c2 = new MultiCSV();
		File _Csv2 = new File("Your/Directory/Path/OOP_EX2-EX4-master/data/MultiCSV.kml");
		Project _pList2 = new Project();
		File[] files2 = new File("Your/Directory/Path/OOP_EX2-EX4-master/data").listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
		});
		c2.print(_Csv1);
		for (int i = 0; i < files2.length; i++) {
			File name = files2[i];
			Layer temp = c2.read(name);
			c2.write(temp, _Csv1);
			_pList2.add(temp);
		}
		c1.Close(_Csv1);

		assertEquals(_Csv2, _Csv1);

	}

}
