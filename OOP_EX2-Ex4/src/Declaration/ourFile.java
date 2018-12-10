package Declaration;

import java.util.Collection;

public class ourFile {

	public ourFile(String name, Collection<Filters> filters) {
		this._Filters = filters;
		this._Name = name;
	}
	
	public ourFile(String name) {
		this._Name = setName(name);
	}

	//**********Getters**********//

	public String getName() {
		return _Name;
	}

	public Collection<Filters> getFilters() {
		return _Filters;
	}
	
	public String setName(String name) {
		this._Name = name.substring(0, (name.length()-4));
		this._Name = _Name + ".kml";
		return this._Name;
	}

	//***********Private Methods**********//

	private Collection<Filters> _Filters;
	private String _Name;
}
