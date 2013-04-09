package com.cloudsense.icqa;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

/**
 * This FragmentActivity contains to Fragments. These are
 * the <code>ClimateParameter</code> and <code>ClimateParamDescription</code> fragments.
 *  
 */

public class MainActivity extends FragmentActivity implements
		ClimateParameter.OnClimateParamSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.climate_param);

		if (findViewById(R.id.fragment_container) != null) {
			if (savedInstanceState != null) {
				return;
			}

			ClimateParameter homeFragment = new ClimateParameter();
			homeFragment.setArguments(getIntent().getExtras());
			getSupportFragmentManager().beginTransaction()
					.add(R.id.fragment_container, homeFragment).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.headlines, menu);
		return true;
	}

	
	/**
	 *  This method gets called when the user clicks on one of 
	 *  the list item in the climate parameter fragment i.e. when 
	 *  the user clicks on the temperature or humidity.
	 */
	
	@Override
	public void onClimateParamSelected(int position) {
		ClimateParamDescription desc = (ClimateParamDescription) getSupportFragmentManager()
				.findFragmentById(R.id.climateparamdescription_fragment);
		if (desc != null) {
			// if in two-pane layout
			desc.updateDescriptionView(position);
		} else {
			ClimateParamDescription newDesc = new ClimateParamDescription();
			Bundle args = new Bundle();
			args.putInt(ClimateParamDescription.ARG_POSITION, position);
			newDesc.setArguments(args);
			FragmentTransaction trans = getSupportFragmentManager()
					.beginTransaction();
			trans.replace(R.id.fragment_container, newDesc);
			trans.addToBackStack(null);
			trans.commit();
		}
	}
}
