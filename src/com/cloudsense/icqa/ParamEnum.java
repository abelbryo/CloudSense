package com.cloudsense.icqa;

/**
 * The following enumeration is used in the the row entries of the ListView.
 */

public enum ParamEnum {
	CO2("Carbondioxide", 0), TEMPERATURE("Temperature", 1), HUMIDITY("Humidity", 2), LUMINANCE("Luminance", 3), MAP("Map", 4);

	private final String rowName;
	private final int position;

	ParamEnum(String rowName, int position) {
		this.rowName = rowName;
		this.position = position;
	}

	public String getRowName() {
		return rowName;
	}

	public int getPosition() {
		return position;
	}
}
