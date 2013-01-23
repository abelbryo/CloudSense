package com.cloudsense.icqa;

import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlSerializer;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
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
				String url = "http://130.233.124.173:9000/sayHello";
				new AsyncHttpPost().execute(url);
				Intent intent = getIntent();
				intent.setClass(getApplicationContext(), MainActivity.class);
				startActivity(intent);

				// Toast.makeText(getApplicationContext(),
				// writeXml(chosen),Toast.LENGTH_LONG).show();

				

			}
		});
	} // end onCreate

	// Don't change the screen orientation
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	/**
	 * This method writes the climate report of the user into a well-formed XML.
	 */
	private String writeXml(List<String> report) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);
			serializer.startTag("", "user-report");
			serializer.attribute("", "user-id", ANDROID_ID);
			for (String c : chosen) {
				serializer.startTag("", "value");
				serializer.text(c);
				serializer.endTag("", "value");
			}
			serializer.endTag("", "user-report");
			serializer.endDocument();
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	// / Throws NetworkOnMainThreadException
	// Use AsyncTask

	public class AsyncHttpPost extends AsyncTask<String, String, String> {

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#doInBackground(Params[])
		 */
		@Override
		protected String doInBackground(String... params) {
			byte[] result = null;
			String str = "";

			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(params[0]); // url

			try {
				String name = "Beriyo";
				StringEntity entity = new StringEntity("name="+name, "UTF-8");
				httppost.setEntity(entity);
				httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");   // This MIME type is FUCKING IMPORTANT. Spent few hours not knowing it.
				HttpResponse response = httpclient.execute(httppost);
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() == HttpURLConnection.HTTP_OK) {
					result = EntityUtils.toByteArray(response.getEntity());
					str = new String(result, "UTF-8");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return str;
		}
		
		@Override
		protected void onPostExecute(String result){
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
		}
		
		
		

		/*
		 * 
		 * private void uploadToWebServer() throws ClientProtocolException,
		 * IOException { String link = "http://130.233.124.173:9000/sayHello";
		 * 
		 * HttpClient httpclient = new DefaultHttpClient(); HttpPost httppost =
		 * new HttpPost(link);
		 * 
		 * try {
		 * 
		 * 
		 * String xmlFile ="Beriyo"; //writeXml(chosen); StringEntity entity =
		 * new StringEntity("name="+xmlFile, "UTF-8");
		 * httppost.setEntity(entity); // httppost.addHeader("Accept",
		 * "application/xml"); // httppost.addHeader("Content-Type",
		 * "application/xml");
		 * 
		 * httppost.addHeader("Content-Type", "text/html");
		 * 
		 * HttpResponse response = httpclient.execute(httppost);
		 * 
		 * if (response.getStatusLine() != null) Toast.makeText(getParent(),
		 * (CharSequence) response.getStatusLine(), Toast.LENGTH_SHORT).show();
		 * //You can get response from calling response.getEntity() and
		 * manipulate with it as you need. } catch (ClientProtocolException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } }
		 */

	}

}
