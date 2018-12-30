package Structure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import Players.Block;
import Players.Fruit;
import Players.Ghost;
import Players.Pacman;

public class Play {

	private File _Play;
	private String _Type;
	private String _Point;
	private String _Speed;
	private String _Radius;
	private String _iD;
	private double _Width;
	private double _Height;
	private ArrayList<Ghost> _Ghosts;
	private ArrayList<Block> _Blocks;
	private ArrayList<Fruit> _Fruits;
	private ArrayList<Pacman> _Pacmans;

	public ArrayList<Play> read(){
		String L = "";
		String S = ",";
		String id = "ID";
		ArrayList<Play> _List = new ArrayList<Play>();
		try(BufferedReader Buffer = new BufferedReader(new FileReader(_Play))){
			L = Buffer.readLine();
			String[] Check = L.split(S);
			if(Check[1].equals(id)) {
				while((L = Buffer.readLine()) != null) {
					String[] Data = L.split(S);
					String P = "P";
					String F = "F";
					String G = "G";
					String B = "B";
					if(!Data[0].equals(B)) {
						if(Data[0].equals(P)) {
							setType(Data[0]+"acman");
							setPoint(Data[3]+","+Data[2]+","+Data[4]);
							setSpeed(Data[5]);
							setRadius(Data[6]);
							setiD(Data[1]);
						}
						if(Data[0].equals(F)) {
							setType(Data[0]+"ruit");
							setPoint(Data[3]+","+Data[2]+","+Data[4]);
							setSpeed(Data[5]);
							setRadius("");
							setiD(Data[1]);
						}
						if(Data[0].equals(G)) {
							setType(Data[0]+"host");
							setPoint(Data[3]+","+Data[2]+","+Data[4]);
							setSpeed(Data[5]);
							setRadius(Data[6]);
							setiD(Data[1]);
						}
						Play temp = new Play(getType(), getPoint(), getSpeed(), getRadius(), getiD());
						_List.add(temp);
					}
					else {
						setType(Data[0]+"lock");
						setPoint(Data[3]+","+Data[5]+","+0);
						setWidth(Double.parseDouble(Data[6]));
						setHeight(Double.parseDouble(Data[2]));
						setiD(Data[1]);
						Play temp = new Play(getType(), getPoint(), getWidth(), getHeight(), getiD());
						_List.add(temp);
					}
				}
				return _List;
			}
		}
		catch(Exception e) {

		}
		return _List;
	}

	public Play(ArrayList<Play> play) {
		_Ghosts = new ArrayList<Ghost>();
		_Blocks = new ArrayList<Block>();
		_Fruits = new ArrayList<Fruit>();
		_Pacmans = new ArrayList<Pacman>();

		String P = "Pacman";
		String F = "Fruit"; 
		String G = "Ghost";
		String B = "Block";
		for(int i=0; i<play.size(); i++) {
			Play g = play.get(i);
			if(g.getType().equals(F)) {
				Fruit Fruit = new Fruit(g.getType(), g.getPoint(), g.getSpeed(),  g.getiD());
				_Fruits.add(Fruit);
			}
			else if(g.getType().equals(P)) {
				Pacman Pacman = new Pacman(g.getType(), g.getPoint(), g.getSpeed(), g.getRadius(), g.getiD());
				_Pacmans.add(Pacman);
			}

			else if(g.getType().equals(G)) {
				Ghost Ghost = new Ghost(g.getType(), g.getPoint(), g.getSpeed(), g.getRadius(),  g.getiD());
				_Ghosts.add(Ghost);
			}

			else if(g.getType().equals(B)) {
				Block Block = new Block(g.getType(), g.getPoint(), (int)g.getWidth(), (int)g.getHeight(),  g.getiD());
				_Blocks.add(Block);
			}
		}
	}

	public Play(File game) {
		this._Play = game;
	}

	public Play(String type, String point, String speed, String radius, String id) {
		this._Type = type;
		this._Point = point;
		this._Speed = speed;
		this._Radius = radius;
		this._iD = id;
	}

	public Play(String type, String point, double width, double height, String id) {
		this._Type = type;
		this._Point = point;
		this._Width = width;
		this._Height = height;
		this._iD = id;
		this._Speed = "0";
		this._Radius = "0";	
	}

	//**********Getters**********//

	public ArrayList<Ghost> getGList() {
		return this._Ghosts;
	}

	public ArrayList<Pacman> getPList() {
		return this._Pacmans;
	}

	public ArrayList<Fruit> getFList() {
		return this._Fruits;
	}

	public ArrayList<Block> getBList(){
		return this._Blocks;
	}

	public File getFile() {
		return this._Play;
	}

	public String getType() {
		return this._Type;
	}

	public String getPoint() {
		return this._Point;
	}

	public String getSpeed() {
		return this._Speed;
	}

	public String getRadius() {
		return this._Radius;
	}

	public String getiD() {
		return _iD;
	}

	public double getWidth() {
		return _Width;
	}

	public double getHeight() {
		return _Height;
	}


	//**********Setters**********//

	private void setType(String type) {
		this._Type = type;
	}

	public void setPoint(String point) {
		this._Point = point;
	}

	private void setSpeed(String speed) {
		this._Speed = speed;
	}

	private void setRadius(String radius) {
		this._Radius = radius;
	}

	private void setiD(String id) {
		this._iD = id;
	}

	public void setWidth(double width) {
		this._Width = width;
	}

	public void setHeight(double height) {
		this._Height = height;
	}


}
