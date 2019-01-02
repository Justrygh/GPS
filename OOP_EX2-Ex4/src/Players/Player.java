package Players;

import java.awt.Image;
import java.awt.Toolkit;

public class Player {
	
	private String _Point;
	private String _Speed;
	private String _Radius;
	private double _Angel;
	private double _Time;
	private int _FruitsEaten;
	private int _PacmansEaten;
	private double _Score;
	private int ghostKill;
	private int wrongLocation;
	private Image _Img;
	
	public Player(Player player) {
		this._Point = player.getPoint();
		this._Speed = player.getSpeed();
		this._Radius = player.getRadius();
		this._Img = player.getImage();
		this._Angel = player.getAngel();
		this._Time = player.getTime();
		this._Score = player.getScore();
		this._PacmansEaten = player.getPacmansEaten();
		this._FruitsEaten = player.getFruitsEaten();
		this.ghostKill = player.getGhostKill();
		this.wrongLocation = player.getWrongLocation();
	}
	public Player(String point) {
		this._Point = point;
		this._Speed = "20.0";
		this._Radius = "1.0";
		this._Angel = 0;
		this._Time = 0;
		this._Score = 0;
		this._PacmansEaten = 0;
		this._FruitsEaten = 0;
		this.ghostKill = 0;
		this.wrongLocation = 0;
		this._Img = Toolkit.getDefaultToolkit().getImage("ourdata/Player.png");
	}
	
	public Player() {
		this._Point = 0+","+0+","+0;
		this._Speed = "20.0";
		this._Radius = "1.0";
		this._Angel = 0;
		this._Time = 0;
		this._Score = 0;
		this._PacmansEaten = 0;
		this._FruitsEaten = 0;
		this.ghostKill = 0;
		this.wrongLocation = 0;
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
	
	public void Time() {
		this._Time += 0.1;
	}
	
	public void FruitsEaten() {
		this._FruitsEaten ++;
	}
	
	public void PacmansEaten() {
		this._PacmansEaten ++;
	}
	
	public void setScore(double score) {
		this._Score += score;
	}
	
	public void ghostKill() {
		this.ghostKill ++;
	}
	
	public void wrongLocation() {
		this.wrongLocation ++;
	}
	
	//**********Getters**********//
	
	public int getWrongLocation() {
		return this.wrongLocation;
	}
	
	public int getGhostKill() {
		return this.ghostKill;
	}
	
	public double getAngel() {
		return this._Angel;
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

	public double getScore() {
		return this._Score;
	}

}
