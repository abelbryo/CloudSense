package com.cloudsense.icqa;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FailureReportFragment extends Fragment {
	private EditText mEditText;
	private Button mButton;

	private static final String URL = "http://130.233.124.173:9000/sayHello";

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.failure_report, container,
				false);
		return rootView;
	}

	public void onStart() {
		super.onStart();
		mEditText = (EditText) getActivity().findViewById(
				R.id.failure_report_editText);
		mButton = (Button) getActivity().findViewById(R.id.failure_button);

		mButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mEditText.getText().toString().length() == 0)
					Toast.makeText(getActivity(),
							"Form is empty.Report not delivered.",
							Toast.LENGTH_SHORT).show();
				else
					new SendViaAsyncTask().execute(URL);
			}
		});
	}

	private class SendViaAsyncTask extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... param) {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(param[0]);

			String val = mEditText.getText().toString();
			byte[] result = null;
			String str = null;
			try {
				StringEntity entity = new StringEntity(val, "utf-8");
				httpPost.setEntity(entity);

				httpPost.addHeader("Content-Type",
						"application/x-www-form-urlencoded");
				HttpResponse response = httpClient.execute(httpPost);
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() == HttpURLConnection.HTTP_OK) {
					result = EntityUtils.toByteArray(response.getEntity());
					str = new String(result, "UTF-8");
				}

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}

		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(getActivity(), "RESULT: " + result,
					Toast.LENGTH_SHORT).show();
		}

	} // end AsyncTask

}// == END ==
