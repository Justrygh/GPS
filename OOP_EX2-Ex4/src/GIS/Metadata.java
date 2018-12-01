package GIS;

import java.util.Date;
import java.time.Instant;
import java.time.ZoneOffset;
import Geom.Point3D;

public class Metadata implements Meta_data{
	
	@Override
	public String toString() {
		String s = Instant.ofEpochMilli(getUTC()).atOffset(ZoneOffset.UTC).toString();
		String Time = s.substring(11, 19);
		String Date = s.substring(0, 10);
		String Total = "Date: " + Date + ", Time: " + Time + ", Color: " + this._Color;
		return Total;
	}
	
	//**********Constructor**********//
	
	public Metadata() {
		this._Color = null;
		this._Time = getUTC();
	}
	
	//**********Private Methods**********//
	
	private long _Time;
	private String _Color;
	
	//**********Getters**********//
	
	@Override
	public long getUTC() {
		this._Time = new Date().getTime() + 7200000;
		return this._Time;
	}
	
	public String getColor() {
		return this._Color;
	}
	
	@Override
	public Point3D get_Orientation() {
		return null;
	}
	
	//**********Setters**********//
	
	public void setColor(String color) {
		this._Color = color;
	}
	
}
