package Declaration;

public class Filters {

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

//**********Setters**********//

public void setName(String name) {
	this._Name = name;
}


public void setDescript(String description) {
	this._Descript = description;
}


public void setPoint(String point) {
	_Point = point;
}


public void setTime(String time) {
	this._Time=time;
}

//**********Private Methods**********//
private String _Descript;
private String _Name;
private String _Time;
private String _Point;

}
