package Players;

import java.util.Date;

public class Pacman {
	/**
	 * @author Eli
	 * @author Qusai
	 * @param type the type is Pacman.
	 * @param point our Pacman Point.
	 * @param speed our Pacman Speed.
	 * @param radius our Pacman Radius.
	 * @param picture our Pacman Picture(.PNG) Format.
	 * @param id our Pacman ID.
	 */
	
	public Pacman(String type, String point, String speed, String radius, String picture, String id) {
		this._Type = type;
		this._Point = point;
		this._Speed = speed;
		this._Radius = radius;
		this._Pic = picture;
		this._Time = 0;
		this._Dis = 0;
		this._iD = id;
		this._Angel = 0;
	}
	
	public Pacman(String type, String point, String speed, String radius, String id) {
		this._Type = type;
		this._Point = point;
		this._Speed = speed;
		this._Radius = radius;
		this._iD = id;
		this._Time = 0;
		this._Dis = 0;
		this._Angel = 0;
		this._Pic = "0";
	}
	
	public Pacman() {}
	
	//**********Private Methods**********//
	private String _Speed;
	private String _Radius;
	private String _Type;
	private String _Point;
	private String _Pic;
	private String _iD;
	private double _Time;
	private double _Dis;
	private int _FruitsEaten;
	private double _Angel;
	
	//**********Setters**********//

	public void setAngel(double angel) {
		this._Angel = angel;
	}
	
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
	
	public void setDistance(double distance) {
		this._Dis = distance;
	}
	
	public void setiD(String id) {
		this._iD = id;
	}
	
	//**********Getters**********//
	
	public double getAngel() {
		return _Angel;
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
	
	public double getDistance() {
		return this._Dis;
	}
	
	public String getiD() {
		return this._iD;
	}

}