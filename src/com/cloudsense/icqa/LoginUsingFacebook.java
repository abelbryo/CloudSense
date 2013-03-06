package com.cloudsense.icqa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.facebook.widget.UserSettingsFragment;

public class LoginUsingFacebook extends FragmentActivity {
	private UserSettingsFragment userSettingsFragment;
	
	public static final String FACEBOOK_USER_ID = "FACEBOOK_ID"; 
	
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_fragment_activity);

		FragmentManager fragmentManager = getSupportFragmentManager();
		userSettingsFragment = (UserSettingsFragment) fragmentManager.findFragmentById(R.id.login_fragment);

		userSettingsFragment.setSessionStatusCallback(new Session.StatusCallback() {

					@Override
					public void call(Session session, SessionState state,
							Exception exception) {
						Log.d("LoginUsingLoginFragmentActivity", String.format("New session state: %s", state.toString()));
						makeMeRequest(session);
								
					}
				});
	}
	
	
	private void makeMeRequest(final Session session) {
	    // Make an API call to get user data and define a 
	    // new callback to handle the response.
	    Request request = Request.newMeRequest(session, 
	            new Request.GraphUserCallback() {
	        @Override
	        public void onCompleted(GraphUser user, Response response) {
	            // If the response is successful
	            if (session == Session.getActiveSession()) {
	                if (user != null) {
	                    // Toast.makeText(getApplicationContext(), user.getFirstName()+" - "+user.getId(), Toast.LENGTH_LONG).show();
	                    
	                    Intent intent = new Intent(LoginUsingFacebook.this, FeedbackActivity.class); 
						intent.putExtra(FACEBOOK_USER_ID, user.getId());
	                    startActivity(intent);
	                }
	            }
	            if (response.getError() != null) {
	                // Handle errors, will do so later.
	            }
	        }
	    });
	    request.executeAsync();
	} 
	

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		userSettingsFragment.onActivityResult(requestCode, resultCode, data);
		super.onActivityResult(requestCode, resultCode, data);
	}

}
