package com.cloudsense.icqa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends Activity {
	
	private Button buttonLoginFragment;
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		buttonLoginFragment = (Button) findViewById(R.id.buttonLoginFragment);
		buttonLoginFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, LoginUsingFacebook.class);
                startActivity(intent);
            }
        });
	}
}
