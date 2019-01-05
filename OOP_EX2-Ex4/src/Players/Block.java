package Players;

public class Block {
//*********Private variables *********//

	private String _Point;
	private String _Type;
	private String _iD;
	private int _Width;
	private int _Height;
	
/**
 * Default Constructor
 */
	public Block() {}
	
       /**
	 * This class is For the Black- Boxes in the game.
	 * @param type the type 
	 * @param point the Point of the Box.
	 * @param width the width of the Box.
	 * @param height height the width of the Box.
	 * @param id the ID for each Box.
	 */
	public Block(String type, String point, int width, int height, String id) {
		this._iD = id;
		this._Type = type;
		this._Point = point;
		this._Width = width;
		this._Height = height;
	}
	//***********SETTERS & GETTERS**********//

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
