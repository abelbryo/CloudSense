package com.cloudsense.icqa;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ClimateParamDescription extends Fragment {
	public final static String ARG_POSITION = "loc";
	private int mCurrent = -1;

	/* This is the point where the fragment will be shown on screen. */
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			mCurrent = savedInstanceState.getInt(ARG_POSITION);
		}
		return inflater.inflate(R.layout.climate_param_description, parent,
				false);
	}

	public void onStart() {
		super.onStart();
		Bundle args = getArguments();
		if (args != null) {
			updateDescriptionView(args.getInt(ARG_POSITION));
		} else if (mCurrent != -1) {
			updateDescriptionView(mCurrent);
		}
	}

	public void onResume() {
		super.onResume();
		Bundle args = getArguments();
		if (args != null) {
			updateDescriptionView(args.getInt(ARG_POSITION));
		} else if (mCurrent != -1) {
			updateDescriptionView(mCurrent);
		}
	}

	public void updateDescriptionView(int position) {
		TextView desc = (TextView) getActivity().findViewById(R.id.climate_param_desc);
		if (Data.getValue(position).equals(ParamEnum.TEMPERATURE.getRowName())) {
			draw();
		} 

			desc.setText(Data.getValue(position));
		mCurrent = position;
	}

	

	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(ARG_POSITION, mCurrent);
	}

	/***********************************************
	 *  
	 *  !! === TESTING IF THE GRAPH WORKS === !! 
	 *  
	 ***********************************************/ 
	
	

	private GraphicalView mChartView;

	XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();

	XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();

	XYSeries mCurrentSeries;

	XYSeriesRenderer mCurrentRenderer;

	public void initChart() {
		mCurrentSeries = new XYSeries("TUAS Temprature Data");
		mDataset.addSeries(mCurrentSeries);
		mCurrentRenderer = new XYSeriesRenderer();
		mRenderer.addSeriesRenderer(mCurrentRenderer);
	}

	public void addSampleData() {
		mCurrentSeries.add(1, 2);
		mCurrentSeries.add(2, 3);
		mCurrentSeries.add(3, 2);
		mCurrentSeries.add(4, 5);
		mCurrentSeries.add(5, 4);
	}
	
	public void draw() {
		LinearLayout layout = (LinearLayout) getActivity().findViewById(
				R.id.chart);

		if (mChartView == null) {
			initChart();
			addSampleData();
			mRenderer.setXLabelsPadding(20);
			mRenderer.setYLabelsPadding(20);
			mRenderer.setLabelsTextSize(16);
			mRenderer.setApplyBackgroundColor(true);
			
			mRenderer.setMarginsColor(Color.parseColor("#f8f8f8"));
			mRenderer.setLabelsColor(Color.BLUE);
			

			mChartView = ChartFactory.getCubeLineChartView(getActivity(),
					mDataset, mRenderer, 0.3f);
			layout.addView(mChartView);
		} else {
			mChartView.repaint();
		}

	}

}
