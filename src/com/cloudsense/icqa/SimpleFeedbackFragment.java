package com.cloudsense.icqa;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SimpleFeedbackFragment extends Fragment {

	private SeekBar seekBar;
	//private Stack<String> stack = new Stack<String>(); // for the simple
														// animation

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
		seekBar = (SeekBar) getActivity().findViewById(R.id.seekBar1);
		seekBar.setProgress(50); // initial position of the SeekBar
		TextView textView = (TextView) getActivity().findViewById(R.id.animation);
		eventHandler(textView);
	}

	public void eventHandler(final View v) {

		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

				/*String[] str = { "/", "--", "\\", "|" };
				if (stack.isEmpty()) {
					for (String s : str) {
						stack.push(s);
					}
				}
				((TextView) v).setText(stack.pop());*/
				int val = progress  / 10; 
				
				((TextView) v).setText(String.valueOf(val));
				
				
				
			}
		});
	}
}
