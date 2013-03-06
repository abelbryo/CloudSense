package com.cloudsense.icqa;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.widget.Toast;

public class FeedbackActivity extends Activity {

	public static Context appContext;
	private static final String TAB_1 = "SIMPLE";
	private static final String TAB_2 = "DETAILED";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback_main_layout);
		appContext = getApplicationContext();

		// ActionBar
		ActionBar actionbar = getActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		ActionBar.Tab PlayerTab = actionbar.newTab().setText(TAB_1);
		ActionBar.Tab StationsTab = actionbar.newTab().setText(TAB_2);

		Fragment simpleFeedback = new SimpleFeedbackFragment();
		Fragment detailedFeedback = new DetailedFeedbackFragment();

		PlayerTab.setTabListener(new FeedbackTabListener(simpleFeedback));
		StationsTab.setTabListener(new FeedbackTabListener(detailedFeedback));

		actionbar.addTab(PlayerTab);
		actionbar.addTab(StationsTab);

	}

	class FeedbackTabListener implements ActionBar.TabListener {
		public Fragment fragment;

		public FeedbackTabListener(Fragment fragment) {
			this.fragment = fragment;
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			Toast.makeText(FeedbackActivity.appContext, "Reselected!",
					Toast.LENGTH_LONG).show();

		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			ft.replace(R.id.fragment_container, fragment);

		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			ft.remove(fragment);

		}

	}

}