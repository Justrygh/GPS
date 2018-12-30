package Players;

import java.awt.Image;
import java.awt.Toolkit;

public class Player {
	
	private String _Point;
	private String _Speed;
	private String _Radius;
	private double _Angel;
	private double _Time;
	private double _Dis;
	private int _FruitsEaten;
	private int _PacmansEaten;
	private int _Score;
	private Image _Img;
	
	public Player(String point) {
		this._Point = point;
		this._Speed = "1";
		this._Radius = "1";
		this._Angel = 0;
		this._Img = Toolkit.getDefaultToolkit().getImage("ourdata/Player.png");
	}
	
	public Player() {
		this._Point = 0+","+0+","+0;
		this._Speed = "10.0";
		this._Radius = "1";
		this._Angel = 0;
		this._Img = Toolkit.getDefaultToolkit().getImage("ourdata/Player.png");
	}
	
	//**********Setters**********//
	
	public void setAngel(double angel) {
		this._Angel = angel;
	}
	
	public void setPoint(String point) {
		this._Point = point;
	}
	
	public void setSpeed(String speed) {
		this._Speed = speed;
	}
	
	public void setRadius(String radius) {
		this._Radius = radius;
	}
	
	public void setTime(double time) {
		this._Time += time;
	}
	
	public void FruitsEaten() {
		this._FruitsEaten ++;
	}
	
	public void PacmansEaten() {
		this._PacmansEaten ++;
	}
	
	public void setDistance(double distance) {
		this._Dis = distance;
	}
	
	public void setScore(int score) {
		this._Score += score;
	}
	
	//**********Getters**********//
	
	public double getAngel() {
		return _Angel;
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
	
	public Image getImage() {
		return this._Img;
	}
	
	public double getTime() {
		return this._Time;
	}
	
	public int getFruitsEaten() {
		return this._FruitsEaten;
	}
	
	public int getPacmansEaten() {
		return this._PacmansEaten;
	}
	
	public double getDistance() {
		return this._Dis;
	}

	public int getScore() {
		return _Score;
	}

}
