package com.cloudsense.icqa.chart;

import android.content.Context;
import android.view.ViewGroup;

/**
 * All chart types inherit from the {@code MotherChart} class.
 * It contains a draw method, which every child class has to
 * implement.
 * @author terefea1
 *
 */

public abstract class MotherChart {
	/**
	 * An abstract method that knows how to draw
	 * a given chart type. 
	 * @param context The context of the chart when it is created.
	 * @param layout The layout to draw the chart on. Typically, a LinearLayout.
	 * 
	 */
	public abstract void draw(Context context, ViewGroup layout);
}
