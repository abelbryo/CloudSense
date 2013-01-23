package com.cloudsense.icqa;

/**
 * The @code{Data} class refers the the list row value resources and contains
 * the text values to be displayed when the items are clicked in the list.
 */

public class Data {

	/* This method returns what is displayed when a list is clicked */
	public static String getValue(int position) {
		return getClimateParams()[position].getTitle();
	}

	public static CustomData[] getClimateParams() {
		return new CustomData[] {
				new CustomData(R.drawable.ic_launcher_1,
						ParamEnum.CO2.getRowName()),
				new CustomData(R.drawable.ic_launcher_1,
						ParamEnum.INDOOR_PRESSURE.getRowName()),
				new CustomData(R.drawable.ic_launcher_1,
						ParamEnum.TEMPERATURE.getRowName()),
				new CustomData(R.drawable.ic_launcher_1,
						ParamEnum.HUMIDITY.getRowName()),
				new CustomData(R.drawable.ic_launcher_1,
						ParamEnum.LUMINANCE.getRowName()),
				new CustomData(R.drawable.ic_launcher_1,
						ParamEnum.MAP.getRowName()), };

	};
}
