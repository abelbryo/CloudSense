package com.cloudsense.icqa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Feedback extends Activity{
	
	private Button feedbackButton;

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback);
		feedbackButton = (Button) findViewById(R.id.feedback_button);
		feedbackButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = getIntent(); 
				intent.setClass(getApplicationContext(), MainActivity.class);
				startActivity(intent);
			}

		});	
		
	}


	
	
}
