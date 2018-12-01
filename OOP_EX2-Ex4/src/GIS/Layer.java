package GIS;

import java.util.ArrayList;


import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import GIS.GIS_element;

public class Layer implements GIS_layer{

	@Override
	public int size() {
		return _eList.size();
	}
	@Override
	public boolean isEmpty() {
		return _eList.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return _eList.contains(o);
	}

	@Override
	public Iterator<GIS_element> iterator() {
		return _eList.iterator();
	}

	@Override
	public Object[] toArray() {
		return _eList.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return _eList.toArray(a);
	}

	@Override
	public boolean add(GIS_element e) {
		return _eList.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return _eList.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return _eList.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_element> c) {
		return _eList.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return _eList.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return _eList.removeAll(c);
	}

	@Override
	public void clear() {
		_eList.clear();
		System.out.println("the arraylist is empty now!");
	}

	//**********Private Methods**********//
	private Metadata _M;
	private Set<GIS_element> _eList;
	private static ArrayList<Integer> _Colors = new ArrayList<Integer>();
	
	//**********Constructor**********//
	
	public Layer() {
		this._eList = new HashSet<GIS_element>();
		this._M = new Metadata();
		this._M.setColor(Colors());
	}
	
	//**********Getters**********//
	
	@Override
	public Meta_data get_Meta_data() {
		return _M;
	}
	
	//**********Layer Color**********//
	/*
	 * Declares a unique color for each Layer.
	 * All the elements inside the Layer will get the same color.
	 */
	public String Colors() {
		String[] Colors = {"#red", "#yellow", "#green"};
		Random random = new Random();
		int Select = random.nextInt(Colors.length);
		String newColor = Colors[Select];
		if(_Colors.size() == 3) {
			_Colors.remove(Select);
			return newColor;
		}
		while(_Colors.contains(Select)) {
			Select = random.nextInt(Colors.length);
			newColor = Colors[Select];
		}
		_Colors.add(Select);
		return newColor;
	}
}
