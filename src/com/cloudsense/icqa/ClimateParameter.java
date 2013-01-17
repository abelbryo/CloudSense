package com.cloudsense.icqa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ClimateParameter extends ListFragment {
	OnClimateParamSelectedListener mCallback;
	private CustomData[] mData = Data.getClimateParams();

	public ClimateParameter() {
		super();
	}

	public interface OnClimateParamSelectedListener {
		public void onClimateParamSelected(int position);
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int layout = R.layout.climate_param_row_view;
		CustomArrayAdapter adapter = new CustomArrayAdapter(getActivity(),
				layout, mData);
		setListAdapter(adapter);
	}

	public void onStart() {
		super.onStart();
	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mCallback = (OnClimateParamSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(
					"The parent activity should implement the interface");
		}
	}

	public void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent();
		if (getRowName(position).equals(ParamEnum.MAP.getRowName())) { 
			// If the user clicks on the Map row
			// Start a map activity
			Toast.makeText(getActivity(), "Loading map ...", Toast.LENGTH_SHORT).show();
			intent.setClass(getActivity(), IndoorMap.class);
			getActivity().startActivity(intent); 
		} else
			mCallback.onClimateParamSelected(position);

		getListView().setItemChecked(position, true);
	}
	
	public String getRowName(int position){
		return mData[position].getTitle();
	}

}
