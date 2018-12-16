package Players;

public class Fruit {
	
	public Fruit(String type, String point, String picture, String id) {
		this._Type = type;
		this._Point = point;
		this._Pic = picture;
		this._iD = id;
		this._Time = 0;
	}
	
	public Fruit() {}
	
	//**********Private Methods**********//
	private String _Type;
	private String _Point;
	private String _Pic;
	private String _iD;
	private double _Time;
	
	public void setType(String type) {
		this._Type = type;
	}
	
	public void setPoint(String point) {
		this._Point = point;
	}
	
	public void setPicture(String picture) {
		this._Pic = picture;
	}
	
	public void setiD(String id) {
		this._iD = id;
	}
	
	public void setTime(double time) {
		this._Time = time;
	}
	
	public double getTime() {
		return this._Time;
	}
	
	public String getiD() {
		return this._iD;
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

}