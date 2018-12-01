package GIS;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Project implements GIS_project{

	@Override
	public int size() {
		return _lList.size();
	}

	@Override
	public boolean isEmpty() {
		return _lList.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return _lList.contains(o);
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		return _lList.iterator();
	}

	@Override
	public Object[] toArray() {
		return _lList.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return _lList.toArray(a);
	}

	@Override
	public boolean add(GIS_layer e) {
		return _lList.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return _lList.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return _lList.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> c) {
		return _lList.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return _lList.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return _lList.removeAll(c);
	}

	@Override
	public void clear() {
		_lList.clear();
		System.out.println("the arraylist is empty now!");
		
	}
	
	//**********Private Methods**********//
		private Metadata _M;
		private Set<GIS_layer> _lList;
		
	//**********Constructor**********//
		
	public Project() {
		this._lList = new HashSet<GIS_layer>();
		this._M = new Metadata();
	}
		
	//**********Getters**********//
	@Override
	public Meta_data get_Meta_data() {
		return _M;
	}

}
