package Players;

public class Pacman {
	
	public Pacman(String type, String point, String speed, String radius, String picture) {
		this._Type = type;
		this._Point = point;
		this._Speed = speed;
		this._Radius = radius;
		this._Pic = picture;
		this._Time = 0;
	}
	
	public Pacman() {}
	
	//**********Private Methods**********//
	private String _Speed;
	private String _Radius;
	private String _Type;
	private String _Point;
	private String _Pic;
	private double _Time;
	private int _FruitsEaten;
	
	public void setType(String type) {
		this._Type = type;
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
	
	public void setPicture(String picture) {
		this._Pic = picture;
	}
	
	public void setTime(double time) {
		this._Time += time;
	}
	
	public void FruitsEaten() {
		this._FruitsEaten++;
	}
	
	public String getPicture() {
		return this._Pic;
	}
	
	public String getPoint() {
		return this._Point;
	}
	
	public String getType() {
		return this._Type;
	}
	
	public String getSpeed() {
		return this._Speed;
	}
	
	public String getRadius() {
		return this._Radius;
	}
	
	public double getTime() {
		return this._Time;
	}
	
	public int getFruitsEaten() {
		return this._FruitsEaten;
	}

}