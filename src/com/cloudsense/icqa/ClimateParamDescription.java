package com.cloudsense.icqa;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ClimateParamDescription extends Fragment{
	public final static String ARG_POSITION = "loc"; 
	private int mCurrent = -1;
	
	/* This is the point where the fragment will be shown on screen. */
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		if(savedInstanceState != null){
			mCurrent = savedInstanceState.getInt(ARG_POSITION);
		}
		return inflater.inflate(R.layout.climate_param_description, parent, false);
	}
	
	public void onStart(){
		super.onStart(); 
		Bundle args = getArguments(); 
		if (args != null){
			updateDescriptionView(args.getInt(ARG_POSITION)); 
		} else if (mCurrent != -1){
			updateDescriptionView(mCurrent);
		}
	}
	
	public void updateDescriptionView(int position){
		TextView desc = (TextView) getActivity().findViewById(R.id.climate_param_desc);
		desc.setText(Data.getValue(position));
		mCurrent = position;
	}
	
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState); 
		outState.putInt(ARG_POSITION, mCurrent);
	}

}
