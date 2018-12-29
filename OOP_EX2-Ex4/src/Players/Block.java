package Players;

public class Block {

	private String _Point;
	private String _Type;
	private String _iD;
	private int _Width;
	private int _Height;
	
	public Block() {}
	
	public Block(String type, String point, int width, int height, String id) {
		this._iD = id;
		this._Type = type;
		this._Point = point;
		this._Width = width;
		this._Height = height;
	}
	
	public int getWidth() {
		return _Width;
	}
	public void setWidth(int width) {
		this._Width = width;
	}
	public String getPoint() {
		return _Point;
	}
	public void setPoint(String point) {
		this._Point = point;
	}
	public String getType() {
		return _Type;
	}
	public void setiD(String id) {
		this._iD = id;
	}
	public String getiD() {
		return _iD;
	}
	public void setType(String type) {
		this._Type = type;
	}
	public int getHeight() {
		return _Height;
	}
	public void setHeight(int height) {
		this._Height = height;
	}
}
