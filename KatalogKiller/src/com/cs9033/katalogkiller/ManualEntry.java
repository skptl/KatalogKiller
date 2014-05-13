package com.cs9033.katalogkiller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.cs9033.katalogkiller.utilities.Utilities;

public class ManualEntry extends Activity {

	private EditText edtInputText;
	private Button btnSubmitInput;
	
	private static String TAG = "ManualEntry";
	private Activity context = this;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.entermanualinput);

		bindComponents();
	}

	private void bindComponents() {

		edtInputText = (EditText) findViewById(R.id.editText1);
		btnSubmitInput = (Button) findViewById(R.id.button1);

		btnSubmitInput.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String URL = Utilities.serverURL + "requests/add/"+Utilities.userName+"/"+edtInputText.getText();
				new SendRequest().execute(URL);
			}
		});

	}

	private class SendRequest extends AsyncTask<String, Integer, String> {

		private ProgressDialog progressDialog;
		String result;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = ProgressDialog.show(context, "Wait",
					"Your request is being sent.\nCheck your email.");
		}

		protected String doInBackground(String... URL) {
			try {
				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(URL[0]);
				HttpResponse response = client.execute(post);
				result = getContent(response);
				Utilities.log(TAG, result, -1);

			} catch (Exception e) {
				e.printStackTrace();
			}

			return result;
		}

		protected void onPostExecute(String result) {
			progressDialog.dismiss();
			finish();

		}

		public String getContent(HttpResponse response) throws IOException {
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String body = "";
			String content = "";

			while ((body = rd.readLine()) != null) {
				content += body + "\n";
			}
			return content.trim();
		}

	}
}
