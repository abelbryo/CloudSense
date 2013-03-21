package com.cloudsense.icqa;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cloudsense.icqa.chart.CarbondioxideChart;
import com.cloudsense.icqa.chart.HumidityChart;
import com.cloudsense.icqa.chart.LuminanceChart;
import com.cloudsense.icqa.chart.MotherChart;
import com.cloudsense.icqa.chart.TemperatureChart;

public class ClimateParamDescription extends Fragment {
	public final static String ARG_POSITION = "loc";
	private int mCurrent = -1;

	// LinearLayout layout;

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
		TextView descTitle = (TextView) getActivity().findViewById(
				R.id.climate_param_desc);

		LinearLayout layout = (LinearLayout) getActivity().findViewById(
				R.id.chart);

		layout.removeAllViews();
		MotherChart mc;
		if (Data.getValue(position).equals(ParamEnum.TEMPERATURE.getRowName())) {
			mc = new TemperatureChart(layout);
			mc.draw(getActivity());
			descTitle.setText(Data.getValue(position) + "         17.7 ºc");
		} else if (Data.getValue(position).equals(
				ParamEnum.HUMIDITY.getRowName())) {
			mc = new HumidityChart(layout);
			mc.draw(getActivity());
			descTitle.setText(Data.getValue(position) + "         54.3 %");
		} else if (Data.getValue(position).equals(
				ParamEnum.LUMINANCE.getRowName())) {
			mc = new LuminanceChart(layout);
			mc.draw(getActivity());
			descTitle.setText(Data.getValue(position)+"            150 lux");
		}else if (Data.getValue(position).equals(ParamEnum.CO2.getRowName())){
			mc = new CarbondioxideChart(layout);
			mc.draw(getActivity());
			descTitle.setText(Data.getValue(position)+"            562ppm");
		}

		mCurrent = position;
	}

	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(ARG_POSITION, mCurrent);
	}

}
