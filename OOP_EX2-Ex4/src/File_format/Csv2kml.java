package File_format;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import Declaration.Filters;
import Declaration.ourFile;

/**
 * 
 * @author Eli 
 * @author Qusai 
 *
 */
public class Csv2kml {
	
	public  void write() {
		ourFile file=this.read();
		PrintWriter Print = null;
		try 
		{
			Print = new PrintWriter(new File(file.getName()));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}
		StringBuilder Builder = new StringBuilder();
		Iterator<Filters> it=file.getFilters().iterator();
		Builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		Builder.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");
		Builder.append("<Document>");
		while(it.hasNext()) {
			Filters Replace=it.next();
			Builder.append("<Placemark>");
			Builder.append("<name>");
			Builder.append(Replace.getName());
			Builder.append("</name>");
			Builder.append("<description>");
			Builder.append("Ethernet: " + Replace.getDescript() + "<br/>");
			Builder.append("Date: " + Replace.getTime() + "<br/>");
			Builder.append("</description>");
			Builder.append("<Point>");
			Builder.append("<coordinates>");
			Builder.append(Replace.getPoint());
			Builder.append("</coordinates>");
			Builder.append("</Point>");
			Builder.append("</Placemark>");
		}
		Builder.append("</Document>");
		Builder.append("</kml>");
		Print.write(Builder.toString());
		Print.close();
		System.out.println("Congrats! You did it!");
	}
	
	public ourFile read() {
		String L = "";
		String S = ",";
		try (BufferedReader Buffer = new BufferedReader(new FileReader(_Csv2kml))) {
			ArrayList<Filters> List=new ArrayList<Filters>();
			Buffer.readLine();
			Buffer.readLine();
			while ((L = Buffer.readLine()) != null) {
				String[] Data = L.split(S);
				Filters place = new Filters(Data[1], Data[3], Data[10], (Data[7]+","+Data[6]+","+Data[8]));
				List.add(place);
			}
			String temp=_Csv2kml.substring(0, _Csv2kml.length()-4);
			ourFile file=new ourFile(temp, List);

			return file;
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	//**********Private Methods**********//
	
	private String _Csv2kml;
	
	//**********Constructor**********//
	
	public Csv2kml(String csv2kml) {
		this._Csv2kml=csv2kml;
	}
	
	//**********Getters**********//
	
	public String getCsvFile() {
		return _Csv2kml;
	}
	
	//**********Setters**********//
	
	public void setCsvFile(String csv2kml) {
		this._Csv2kml=csv2kml;
	}
	
}
