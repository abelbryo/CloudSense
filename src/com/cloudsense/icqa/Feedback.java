package com.cloudsense.icqa;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Feedback extends Activity {

	private Button feedbackSubmit;

	private static final int CHOICE_BUTTON_NO = 8;

	private Button[] buttonArray; // 8 buttons so far
	private EditText editText;
	private ArrayList<String> chosen;
	private String ANDROID_ID = Settings.Secure.ANDROID_ID;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback);
		feedbackSubmit = (Button) findViewById(R.id.feedback_button);
		editText = (EditText) findViewById(R.id.feedback_edit_text);

		buttonArray = new Button[CHOICE_BUTTON_NO];

		buttonArray[0] = (Button) findViewById(R.id.button1);
		buttonArray[1] = (Button) findViewById(R.id.button2);
		buttonArray[2] = (Button) findViewById(R.id.button3);
		buttonArray[3] = (Button) findViewById(R.id.button4);
		buttonArray[4] = (Button) findViewById(R.id.button5);
		buttonArray[5] = (Button) findViewById(R.id.button6);
		buttonArray[6] = (Button) findViewById(R.id.button7);
		buttonArray[7] = (Button) findViewById(R.id.button8);

		chosen = new ArrayList<String>();

		for (final Button btn : buttonArray) {
			btn.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					chosen.add((String) btn.getText());
					editText.setText(TextUtils.join("-", chosen));
					Selection.setSelection(editText.getText(), editText
							.getText().length()); // set cursor at the end
					btn.setEnabled(false);
				}
			});
		}

		editText.addTextChangedListener(new TextWatcher() {

			// If all the text is deleted
			// enable the buttons again
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (s.toString().length() == 0) {
					for (Button btn : buttonArray)
						btn.setEnabled(true);
					chosen.clear();
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) { /* empty */
			}

			@Override
			public void afterTextChanged(Editable s) { /* empty */
			}
		});

		feedbackSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), writeXml(chosen), Toast.LENGTH_LONG).show();
				Intent intent = getIntent();
				intent.setClass(getApplicationContext(), MainActivity.class);
				startActivity(intent);
			}
		});
	} // end onCreate
	
	// Don't change the screen orientation 
	public void onConfigurationChanged(Configuration newConfig){
		super.onConfigurationChanged(newConfig);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	
	
	/**
	 * This method write the climate report of 
	 * the user into a well-formed XML.
	 */
	private String writeXml(List<String> report){
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try{
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);
			serializer.startTag("", "user-report");
			serializer.attribute("", "user-id", ANDROID_ID);
			for(String c : chosen){
				serializer.startTag("", "value");
				serializer.text(c);
				serializer.endTag("", "value");
			}
			serializer.endTag("", "user-report");
			serializer.endDocument();
			return writer.toString();
		}catch(Exception e){
			throw new RuntimeException();
		}
	}
	
	
	

	/*************************************************************************
	 * == TO DO Implement the methods that will send the data over via TCP/IP.
	 *************************************************************************/

}
