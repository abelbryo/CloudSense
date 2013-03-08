package com.cloudsense.icqa;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cloudsense.icqa.DetailedFeedbackFragment.OnUserInputChangedListener;
import com.cloudsense.icqa.FeedbackDialog.FeedbackDialogListener;

public class FeedbackActivity extends FragmentActivity  implements FeedbackDialogListener, OnUserInputChangedListener{

	public static Context appContext;
	private static final String TAB_1 = "SIMPLE";
	private static final String TAB_2 = "DETAILED";
	
	
	private View view; // this refers to the userinputbox in the detailedfeedbackfrag
	
	// For saving state
	private static final String TAB = "tab"; // The current tab

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback_main_layout);
		appContext = getApplicationContext();

		// ActionBar
		ActionBar actionbar = getActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		ActionBar.Tab simpleTab = actionbar.newTab().setText(TAB_1);
		ActionBar.Tab detailedTab = actionbar.newTab().setText(TAB_2);

		Fragment simpleFeedback = new SimpleFeedbackFragment();
		Fragment detailedFeedback = new DetailedFeedbackFragment();

		simpleTab.setTabListener(new FeedbackTabListener(simpleFeedback));
		detailedTab.setTabListener(new FeedbackTabListener(detailedFeedback));

		actionbar.addTab(simpleTab);
		actionbar.addTab(detailedTab);
		
		if(savedInstanceState != null){
			actionbar.setSelectedNavigationItem(savedInstanceState.getInt(TAB));
		}

	} // onCreate

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

	} // FeedBackListener
	
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		outState.putInt(TAB, getActionBar().getSelectedNavigationIndex());
	}

	@Override
	public void onDialogNegativeButtonClick(DialogInterface dialog, String item) {
		String formVal = ((EditText) view).getText().toString();
		int start = formVal.indexOf(item);
		int end = start + item.length();
		((EditText) view).getText().replace(start, end, "");
		
	}

	@Override
	public void onDialogListItemClick(String[] items, String item, int index) {
		String formVal = ((EditText) view).getText().toString();
		int start = formVal.indexOf(item);
		int end = start + item.length();
		((EditText) view).getText().replace(start, end, "");
		//DetailedFeedbackFragment dff = new DetailedFeedbackFragment();
		((EditText) view).getText().insert(start, DetailedFeedbackFragment.createTextTokenizer(items[index]));
	}

	@Override
	public void onUserInputSelected(View view, String item) {
		FeedbackDialog feedbackDialog = new FeedbackDialog();
		Log.d(getClass().toString(), item); 
		
		Bundle args = new Bundle();
		args.putString(FeedbackDialog.CHOSEN_ADJECTIVE, item);
		feedbackDialog.setArguments(args);
		feedbackDialog.show(getSupportFragmentManager(), "SimpleFeedbackDialog");
		this.view = view;
	}
	

} // == END ==