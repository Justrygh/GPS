package Structure;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Players.Fruit;
import Players.Game;
import Players.Pacman;

public class Map {
	
	private BufferedImage _Img;
	private int Width;
	private int Height;
	
	public Map() {
		try {
			this._Img = ImageIO.read(new File("/home/eli/eclipse-workspace/OOP_EX2-EX4-master/newdata/Ariel1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.Width = _Img.getWidth();
		this.Height = _Img.getHeight();
	}
	
	public BufferedImage getImage() {
		return this._Img;
	}
	
	public int getWidth() {
		return this.Width;
	}
	
	public int getHeight() {
		return this.Height;
	}

	public ArrayList<Game> ConvertPoints2GPS(ArrayList<Game> _List){
		for(int i=0; i<_List.size(); i++) {
			String[] arrP = (_List.get(i).getPoint().split(","));
			double newY = (32.106 - (Double.parseDouble(arrP[1])/152500));
			double newX = (35.2018 + (Double.parseDouble(arrP[0])/118000));
			_List.get(i).setPoint(newX +","+newY+","+0);
		}
		return _List;
	}
	
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
