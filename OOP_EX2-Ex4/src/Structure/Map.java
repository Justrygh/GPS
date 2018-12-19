package Structure;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Geom.Point3D;
import Players.Game;
import Players.Pacman;

public class Map {
	/**
	 * @author Eli.
	 * @author Qusai.
	 */
	// Our Private Variables
	private BufferedImage _Img;
	private int Width;
	private int Height;
	
	//Default Constructor that Draws the Ariel University MAP at First Pop-up.
	public Map() {
		try {
			this._Img = ImageIO.read(new File("/home/eli/eclipse-workspace/OOP_EX2-EX4-master/newdata/Ariel1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.Width = _Img.getWidth();
		this.Height = _Img.getHeight();
	}
	/**
	 * @return Ariel Image.
	 */
	public BufferedImage getImage() {
		return this._Img;
	}
	/**
	 * @return Ariel image Width.
	 */
	public int getWidth() {
		return this.Width;
	}
	/**
	 * @return Ariel image Height
	 */
	public int getHeight() {
		return this.Height;
	}
	
	/**
	 * This function returns the distance between two points in 2D.
	 * @param a Point3D - First point to compare
	 * @param b Point3D - Second point to compare
	 * @return the distance between the two points
	 */
	public double distanceBetween2Points(Point3D a, Point3D b) {
		double distance = 0;
		distance = Math.sqrt(Math.pow(a.x()-b.x(), 2) + Math.pow(a.y()-b.y(), 2));
		return distance;
	}
	
	/**
	 * This function returns the angel between two points in 2D
	 * @param a Point3D - First point to compare
	 * @param b Point3D - Second point to compare
	 * @return the angel between the two points.
	 */
	
	public double angelBetween2Points(Point3D a, Point3D b) {
		double angel = 0;
		angel = Math.atan2(Math.abs(a.y()-b.y()), Math.abs(a.x()-b.x()));
		return angel;
	}
	/**
	 * This method gets ArrayList of Game points in Pixels and Converts it to GPS Points.
	 * @param _List The List that Converted by the Method.
	 * @return new Converted List.
	 */
	public ArrayList<Game> ConvertPoints2GPS(ArrayList<Game> _List){
		for(int i=0; i<_List.size(); i++) {
			String[] arrP = (_List.get(i).getPoint().split(","));
			double newY = (32.106 - (Double.parseDouble(arrP[1])/152500));
			double newX = (35.2018 + (Double.parseDouble(arrP[0])/118000));
			_List.get(i).setPoint(newX +","+newY+","+0);
		}
		return _List;
	}
	/**
	 * This method gets ArrayList of Pacmans points in pixels and Converts it to GPS Points(we use it in the KML convert File).
	 * @param _List The List that Converted by the Method.
	 * @return new Converted List.
	 */
	public ArrayList<Pacman> ConvertPac(ArrayList<Pacman> _List){
		for(int i=0; i<_List.size(); i++) {
			String[] arrP = (_List.get(i).getPoint().split(","));
			double newY = (32.106 - (Double.parseDouble(arrP[1])/152500));
			double newX = (35.2018 + (Double.parseDouble(arrP[0])/118000));
			_List.get(i).setPoint(newX +","+newY+","+0);
		}
		return _List;
	}
	/**
	 * This method gets ArrayList of Game points in GPS and Converts it to Pixel Points.
	 * @param _List The List that Converted by the Method.
	 * @return new Converted List.
	 */
	public ArrayList<Game> ConvertPoints2Pixel(ArrayList<Game> _List){
		for(int i=0; i<_List.size(); i++) {
			String[] arrP = (_List.get(i).getPoint().split(","));
			int newY = (int)((32.106 - Double.parseDouble(arrP[1]))*152500);
			int newX = (int)((Double.parseDouble(arrP[0]) - 35.2018)*118000);
			_List.get(i).setPoint(newX+","+newY+","+0);
		}
		return _List;
	}
}