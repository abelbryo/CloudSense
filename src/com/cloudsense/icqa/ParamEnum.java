package com.cloudsense.icqa;

/**
 * The following enumeration is used in the
 * the row entries of the ListView.
 */

public enum ParamEnum {
	CO2				("CO2", 0), 
	TEMPERATURE		("Temperature", 1), 
	HUMIDITY		("Humidity", 2), 
	INDOOR_PRESSURE	("Indoor Pressure", 3), 
	LUMINANCE		("Luminance", 4), 
	MAP				("Map", 5);

	private final String rowName;
	private final int position;

	ParamEnum(String rowName, int position) {
		this.rowName = rowName;
		this.position = position;
	}
	
	public String getRowName() { return rowName; }
	public int getPosition(){ return position; }
}
