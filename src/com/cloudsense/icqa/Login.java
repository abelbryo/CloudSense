package com.cloudsense.icqa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Login extends Activity {
	
	private Button buttonLoginFragment;
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		buttonLoginFragment = (Button) findViewById(R.id.buttonLoginFragment);
		
		
		Display display = getWindowManager().getDefaultDisplay();
	    DisplayMetrics outMetrics = new DisplayMetrics ();
	    display.getMetrics(outMetrics);

	    float density  = getResources().getDisplayMetrics().density;
	    float dpHeight = outMetrics.heightPixels / density;
	    float dpWidth  = outMetrics.widthPixels / density;
	    String val = "dp: " + dpWidth+", "+dpHeight;
	    Toast.makeText(this, val, Toast.LENGTH_LONG).show();
	    Log.v("Screen resolution:", val);
		
		
		
		buttonLoginFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, LoginUsingFacebook.class);
                startActivity(intent);
            }
        });
	}
}
