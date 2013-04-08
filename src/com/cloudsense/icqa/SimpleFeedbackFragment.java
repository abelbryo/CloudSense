package com.cloudsense.icqa;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * The <code>SimpleFeedbackFragment</code> consists of a Slider/SeekBar and two
 * buttons. Upon clicking Submit button, it will redirect to
 * <code>MainActivity</code>, which is were we have the list of climate
 * parameters. These are temperature, humidity etc.
 * 
 * At the moment the slider values are not sent anywhere. That should be FIXED,
 * when the server side is ready.
 */

public class SimpleFeedbackFragment extends Fragment {

	private SeekBar mSeekBar;
	private Button mSubmitButton;
	private Button mMoreButton;

	protected static int mCurrentProgress = 5;
	protected static final String SEEKBAR_PROGRESS = "progress";

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState != null)
			mCurrentProgress = savedInstanceState.getInt(SEEKBAR_PROGRESS);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		return inflater.inflate(R.layout.simple_feedback_layout, container,
				false);
	}

	public void onStart() {
		super.onStart();
		mSeekBar = (SeekBar) getActivity().findViewById(R.id.seekBar1);
		TextView textView = (TextView) getActivity().findViewById(
				R.id.animation);
		mSubmitButton = (Button) getActivity().findViewById(R.id.simple_submit);
		mMoreButton = (Button) getActivity().findViewById(R.id.more_button);

		textView.setText(String.valueOf(mCurrentProgress));
		mSeekBar.setProgress(mCurrentProgress * 10);

		seekBarEventHandler(textView);
		buttonEventHandler(mSubmitButton, mMoreButton);
	}

	private void seekBarEventHandler(final View v) {

		mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				mCurrentProgress = progress / 10;
				((TextView) v).setText(String.valueOf(mCurrentProgress));

			}
		});
	} // seekBarEventhandler

	private void buttonEventHandler(Button... btn) {
		Button submit = btn[0];
		Button more = btn[1];
		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TO DO: First submit the value of the seekBar
				// Then redirect
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
				FragmentTransaction ft = getActivity().getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.fragment_container, dff);
				ft.addToBackStack(null);
				ft.commit();

				// select the detailed tab
				getActivity().getActionBar().setSelectedNavigationItem(1);
			}
		});
	} // buttonEventHandler

	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mCurrentProgress = mSeekBar.getProgress() / 10;
		outState.putInt(SEEKBAR_PROGRESS, mCurrentProgress);
	}

} // == END
