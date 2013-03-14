package com.cloudsense.icqa;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SimpleFeedbackFragment extends Fragment {

	private SeekBar mSeekBar;
	private Button mSubmitButton;
	private Button mMoreButton;
	
	

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		return inflater.inflate(R.layout.simple_feedback_layout, container,
				false);

	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TO-DO make sure states are not lost once the fragment is recreated
	}

	public void onStart() {
		super.onStart();
		mSeekBar = (SeekBar) getActivity().findViewById(R.id.seekBar1);
		mSeekBar.setProgress(50); // initial position of the SeekBar
		TextView textView = (TextView) getActivity().findViewById(R.id.animation);
		mSubmitButton = (Button) getActivity().findViewById(R.id.simple_submit);
		mMoreButton = (Button) getActivity().findViewById(R.id.more_button);
		
		seekBarEventHandler(textView);
		buttonEventHandler(mSubmitButton, mMoreButton);
	}

	private void seekBarEventHandler(final View v) {

		mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) { }

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {	}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				int val = progress  / 10; 				
				((TextView) v).setText(String.valueOf(val));
							
			}
		});
	} // seekBarEventhandler
	
	private void buttonEventHandler(Button ... args){
		Button submit = args[0]; 
		Button more = args[1];
		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TO DO: First submit the value of the seekBar 
				Intent intent = getActivity().getIntent();
				intent.setClass(getActivity(), MainActivity.class);
				startActivity(intent);
			}
		});
		
		more.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Add Bundles here
				DetailedFeedbackFragment dff = new DetailedFeedbackFragment(); 
				FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction(); 
				ft.replace(R.id.fragment_container, dff);
				ft.addToBackStack(null);
				ft.commit();
				
				
				// select the detailed tab, not best way but works
				getActivity().getActionBar().setSelectedNavigationItem(1); 
			}
		});
	}
	
}
