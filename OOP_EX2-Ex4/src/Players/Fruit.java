package Players;

public class Fruit {
	
	public Fruit(String type, String point) {
		this._Type = type;
		this._Point = point;
	}
	
	public Fruit() {}
	
	//**********Private Methods**********//
	private String _Type;
	private String _Point;
	private String _Pic;
	
	public void setType(String type) {
		this._Type = type;
	}
	
	public void setPoint(String point) {
		this._Point = point;
	}
	
	public void setPicture(String picture) {
		this._Pic = picture;
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