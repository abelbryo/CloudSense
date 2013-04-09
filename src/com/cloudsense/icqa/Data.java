package com.cloudsense.icqa;

/**
 * The @code{Data} class refers the the list row value resources 
 * in the <code>ClimateParameter</code> fragment.
 */

public class Data {

	/* This method returns what is displayed when a list is clicked */
	public static String getValue(int position) {
		return getClimateParams()[position].getTitle();
	}

	public static CustomData[] getClimateParams() {
		return new CustomData[] {

				/*
				 * new CustomData(R.drawable.ic_launcher,
				 * ParamEnum.INDOOR_PRESSURE.getRowName()),
				 */

				new CustomData(R.drawable.temp,	 ParamEnum.TEMPERATURE.getRowName()),
				new CustomData(R.drawable.humid, ParamEnum.HUMIDITY.getRowName()),
				new CustomData(R.drawable.lumin, ParamEnum.LUMINANCE.getRowName()),
				new CustomData(R.drawable.co2,   ParamEnum.CO2.getRowName()),
				new CustomData(R.drawable.map,   ParamEnum.MAP.getRowName()), };

	};
}
