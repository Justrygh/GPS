package Structure;

import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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
	private boolean Flag;
	private int blockWidth;
	private int blockHeight;
	
	//Default Constructor that Draws the Ariel University MAP at First Pop-up.
	public Map() {
		try {
			this._Img = ImageIO.read(new File("newdata/Ariel1.png"));
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
	
	public int getBlockWidth() {
		return this.blockWidth;
	}
	/**
	 * @return Ariel image Height
	 */
	public int getBlockHeight() {
		return this.blockHeight;
	}
	
	public boolean isFlag() {
		return Flag;
	}
	
	public void setFlag(boolean flag) {
		Flag = flag;
	}
	
	public boolean[][] image2Matrix(BufferedImage img){
		boolean[][] mat = new boolean[img.getHeight()][img.getWidth()];
		for(int i=0; i<img.getHeight(); i++) {
			for(int j=0; j<img.getWidth(); j++) {
				if(i == 0 || j == 0 || img.getRGB(j, i) == Color.BLACK.getRGB() || j == img.getWidth()-1 || i == img.getHeight()-1) {
					mat[i][j] = false;
				}
				else {
					mat[i][j] = true;
				}
			}
		}
		return mat;
	}
	
	public BufferedImage matImg(int height, int width) {
		Image scaledImg = _Img.getScaledInstance(width, height, Image.SCALE_FAST);
		BufferedImage done = toBufferedImage(scaledImg);
		return done;
	}
	
	/**
	 * Converts a given Image into a BufferedImage
	 *
	 * @param img The Image to be converted
	 * @return The converted BufferedImage
	 */
	public BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
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
		angel = Math.atan2((b.y()-a.y()) , (b.x()-a.x()));
		return Math.toDegrees(angel);
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
	
	/**
	 * This method gets ArrayList of Play points in GPS and Converts it to Pixel Points.
	 * @param _List The List that Converted by the Method.
	 * @return new Converted List.
	 */
	public ArrayList<Play> ConvertList2Pixel(ArrayList<Play> _List){
		String B = "Block";
		for(int i=0; i<_List.size(); i++) {
			String[] arrP = (_List.get(i).getPoint().split(","));
			int newY = (int)((32.106 - Double.parseDouble(arrP[1]))*152500);
			int newX = (int)((Double.parseDouble(arrP[0]) - 35.2018)*118000);
			_List.get(i).setPoint(newX+","+newY+","+0);
			if(_List.get(i).getType().equals(B)) {
				int newHeight = Math.abs((int)((32.106 - _List.get(i).getHeight())*152500) - newY);
				int newWidth = Math.abs((int)((_List.get(i).getWidth() - 35.2018)*118000) - newX);
				_List.get(i).setWidth(newWidth);
				_List.get(i).setHeight(newHeight);
			}
		}
		return _List;
	}
}