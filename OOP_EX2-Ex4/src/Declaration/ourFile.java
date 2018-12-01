package Declaration;

import java.util.Collection;

public class ourFile {

	public ourFile(String name, Collection<Filters> filters) {
		this._Filters = filters;
		this._Name = name;
	}

	//**********Getters**********//

	public String getName() {
		return _Name;
	}

	public Collection<Filters> getFilters() {
		return _Filters;
	}

	//***********Private Methods**********//

	private Collection<Filters> _Filters;
	private String _Name;
}
