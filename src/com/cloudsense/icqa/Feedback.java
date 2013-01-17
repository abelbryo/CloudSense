package com.cloudsense.icqa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Feedback extends Activity {

	private Button feedbackButton;

	private static final int CHOICE_BUTTON_NO = 8;

	private Button[] buttonArray; // 8 buttons so far
	private EditText editText;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback);
		feedbackButton = (Button) findViewById(R.id.feedback_button);
		editText = (EditText)findViewById(R.id.feedback_edit_text);

		buttonArray = new Button[CHOICE_BUTTON_NO];

		buttonArray[0] = (Button) findViewById(R.id.button1);
		buttonArray[1] = (Button) findViewById(R.id.button2);
		buttonArray[2] = (Button) findViewById(R.id.button3);
		buttonArray[3] = (Button) findViewById(R.id.button4);
		buttonArray[4] = (Button) findViewById(R.id.button5);
		buttonArray[5] = (Button) findViewById(R.id.button6);
		buttonArray[6] = (Button) findViewById(R.id.button7);
		buttonArray[7] = (Button) findViewById(R.id.button8);
		
		
		for(final Button btn : buttonArray){
			btn.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v){
					editText.append(((String) btn.getText())+"-");
					btn.setEnabled(false);
				}
			});
		}
		
		

		feedbackButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = getIntent();
				intent.setClass(getApplicationContext(), MainActivity.class);
				startActivity(intent);
			}
		});
	} // end onCreate
	
	/*****************************************************
	 * TO DO
	 * Implement the methods that will send the data over
	 * via TCP/IP.
	 * 
	 *****************************************************/
	
	

}
