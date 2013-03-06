package com.cloudsense.icqa;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SimpleFeedbackFragment extends Fragment {
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		return inflater.inflate(R.layout.simple_feedback_layout, container, false);

	}
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		// TO-DO make sure states are not lost once the fragment is recreated
	}
	
	public void onStart(){
		super.onStart();
	}
	
	
	
}
