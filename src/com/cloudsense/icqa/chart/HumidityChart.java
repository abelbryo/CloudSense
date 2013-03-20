package com.cloudsense.icqa.chart;

import java.text.DateFormat;
import java.util.Date;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;

public class HumidityChart extends MotherChart {

	private GraphicalView mChartView;

	XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();

	XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();

	TimeSeries mCurrentSeries;

	XYSeriesRenderer mCurrentRenderer;

	private ViewGroup layout;

	public HumidityChart(ViewGroup layout) {
		super();
		this.layout = layout;
	}

	public void initChart() {
		mCurrentSeries = new TimeSeries("Office 2531 - Humidity data");
		mDataset.addSeries(mCurrentSeries);
		mCurrentRenderer = new XYSeriesRenderer();
		mRenderer.addSeriesRenderer(mCurrentRenderer);
	}

	public void addSampleData() {

		String dateString1 = "Feb 4, 2013 8:14 PM";
		String dateString2 = "Feb 5, 2013 8:14 PM";
		String dateString3 = "Feb 6, 2013 8:14 PM";
		String dateString4 = "Feb 7, 2013 8:14 PM";
		String dateString5 = "Feb 8, 2013 8:14 PM";
		String dateString6 = "Feb 9, 2013 8:14 PM";
		String dateString7 = "Feb 10, 2013 8:14 PM";
		String dateString8 = "Feb 11, 2013 8:14 PM";
		String dateString9 = "Feb 12, 2013 8:14 PM";
		String dateString10 = "Feb 13, 2013 8:14 PM";
		
		
		
		DateFormat format = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
				DateFormat.SHORT);
		try {
			Date date1 = format.parse(dateString1);
			Date date2 = format.parse(dateString2);
			Date date3 = format.parse(dateString3);
			Date date4 = format.parse(dateString4);
			Date date5 = format.parse(dateString5);
			Date date6 = format.parse(dateString6);
			Date date7 = format.parse(dateString7);
			Date date8 = format.parse(dateString8);
			Date date9 = format.parse(dateString9);
			Date date10 = format.parse(dateString10);
			

			mCurrentRenderer.setLineWidth(3.0f);
			mCurrentRenderer.setHighlighted(true);

			mCurrentSeries.add(date1, 50);
			mCurrentSeries.add(date2, 44);
			mCurrentSeries.add(date3, 61.5);
			mCurrentSeries.add(date4, 39);
			mCurrentSeries.add(date5, 40);
			mCurrentSeries.add(date6, 50);
			mCurrentSeries.add(date7, 44);
			mCurrentSeries.add(date8, 61.5);
			mCurrentSeries.add(date9, 39);
			mCurrentSeries.add(date10, 40);

		} catch (Exception e) {
		}
	}

	@Override
	public void draw(Context context) {
		if (mChartView == null) {
			initChart();
			addSampleData();
			mRenderer.setXLabelsPadding(20);
			mRenderer.setYLabelsPadding(20);
			mRenderer.setLabelsTextSize(16);
			mRenderer.setApplyBackgroundColor(true);
			mRenderer.setMarginsColor(Color.parseColor("#f8f8f8"));
			mRenderer.setLabelsColor(Color.GREEN);
			mChartView = ChartFactory.getTimeChartView(context, mDataset, mRenderer, "EEE MMM dd HH:mm yyyy");
			layout.addView(mChartView);
		} else {
			mChartView.repaint();
		}
	}
}
