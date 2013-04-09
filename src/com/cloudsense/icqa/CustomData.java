package com.cloudsense.icqa;

/**
 * Represents a row data in the <code>ClimateParameter</code> Fragment
 *
 */


public class CustomData {
	private int icon;
	private String title;

	public CustomData(int icon, String title) {
		this.icon = icon;
		this.title = title;
	}

	/* Getters and Setters */

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
