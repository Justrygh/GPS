package Declaration;

public class Filters {

	public Filters(String name, String time, String descript, String point) {
		this._Name = name;
		this._Time = time;
		this._Descript = descript;
		this._Point = point;
	}

	//**********Getters**********//

	public String getPoint() {
		return _Point;
	}

	public String getName() {
		return _Name;
	}

	public String getTime() {
		return _Time;
	}

	public String getDescript() {
		return _Descript;
	}

	//**********Private Methods**********//
	private String _Descript;
	private String _Name;
	private String _Time;
	private String _Point;

}
