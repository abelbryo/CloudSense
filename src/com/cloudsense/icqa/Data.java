package com.cloudsense.icqa;

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
				new CustomData(R.drawable.ic_launcher, "CO2"),
				new CustomData(R.drawable.ic_launcher, "Temprature"),
				new CustomData(R.drawable.ic_launcher, "Humidity"),
				new CustomData(R.drawable.ic_launcher, "Pressure"),
				new CustomData(R.drawable.ic_launcher, "Luminance"),
				new CustomData(R.drawable.ic_launcher, "Map") };

	};
}
