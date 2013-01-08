package com.cloudsense.icqa;

/**
 * The @code{Data} class refers the the list row value resources
 * and contains the text values to be displayed when the
 * items are clicked in the list.
 */


public class Data {
	
	static String[] values = { 
		"CO2 level", 
		"Temprature", 
		"Humidity",
		"Indoor Pressure", 
		"Luminance", 
		"Map" 
		};

	public static CustomData[] getClimateParams() {
		return new CustomData[] {
				new CustomData(R.drawable.ic_launcher_1, "CO2"),
				new CustomData(R.drawable.ic_launcher_1, "Temprature"),
				new CustomData(R.drawable.ic_launcher_1, "Humidity"),
				new CustomData(R.drawable.ic_launcher_1, "Pressure"),
				new CustomData(R.drawable.ic_launcher_1, "Luminance"),
				new CustomData(R.drawable.ic_launcher_1, "Map") };

	};
}
