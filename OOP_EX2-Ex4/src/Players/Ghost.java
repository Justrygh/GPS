package Players;

public class Ghost {

	private String _Speed;
	private String _Radius;
	private String _Type;
	private String _Point;
	private String _Pic;
	private String _iD;
	
	public Ghost(String type, String point, String speed, String radius, String id) {
		this._Type = type;
		this._Point = point;
		this._Speed = speed;
		this._Radius = radius;
		this._iD = id;
	}
	
	public Ghost() {}
	
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
	
	public void setiD(String id) {
		this._iD = id;
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
	
	public String getiD() {
		return this._iD;
	}
}
