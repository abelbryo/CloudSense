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
		// in two pane-mode highlight the selected climate parameter
		if (getFragmentManager().findFragmentById(R.id.climateparamdescription_fragment) != null)
			setActivateOnItemClick(true, getListView());
	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if(!(activity instanceof OnClimateParamSelectedListener)){
			throw new IllegalStateException("Host activity should implement the callback interface.");
		}
		mCallback = (OnClimateParamSelectedListener)activity;
	}

	public void onListItemClick(ListView l, View v, int position, long id) {
		
		// If the user clicks on the Map row
		// Start a map activity
		Intent intent = new Intent();
		if (getRowName(position).equals(ParamEnum.MAP.getRowName())) {
			
			Toast.makeText(getActivity(), "Loading map ...", Toast.LENGTH_SHORT)
					.show();
			intent.setClass(getActivity(), IndoorMap.class);
			getActivity().startActivity(intent);
		}else{
		
			// if clicked on other rows
			// show corresponding view of the fragment
			mCallback.onClimateParamSelected(position);
			getListView().setItemChecked(position, true);
		}
	}

	public String getRowName(int position) {
		return mData[position].getTitle();
	}
	
	/**
	 * For some reason this doesn't work.
	 * @param activate
	 * @param v
	 */
	public void setActivateOnItemClick(boolean activate, ListView v){
		v.setChoiceMode(activate ? ListView.CHOICE_MODE_SINGLE : ListView.CHOICE_MODE_NONE);
		v.setSelected(activate);
	}
	
}
