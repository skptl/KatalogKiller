package com.cs9033.katalogkiller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.cs9033.katalogkiller.models.Subscription;
import com.cs9033.katalogkiller.utilities.DBHandler;
import com.cs9033.katalogkiller.utilities.Utilities;

public class ViewAllResultActivity extends Activity {

	private ListView listViewMembers;

	private ArrayList<String> arraylistsubscription;
	private static ArrayList<Subscription> arraylistsubscriptionRaw;
	private ArrayAdapter<String> adapter;

	private ArrayList<Subscription> subcriptionlist;

	private DBHandler KatalogDB;

	private Activity context = this;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewallresult);
		KatalogDB = new DBHandler(this);
		arraylistsubscriptionRaw = new ArrayList<>();
		new HttpAsyncTask().execute(Utilities.serverURL + "requests/getall");
		

	}

	/**
	 * Method to bind all components to the controller.
	 */
	private void bindComponents() {

		// arraylistsubscriptionRaw = KatalogDB.getAllSubscriptionUser();

		listViewMembers = (ListView) findViewById(R.id.listViewFinal);
		listViewMembers.setAdapter(new ItemListBaseAdapter(this,
				arraylistsubscriptionRaw));
		listViewMembers.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				Object o = listViewMembers.getItemAtPosition(position);
				Subscription obj_itemDetails = (Subscription) o;
				Toast.makeText(
						ViewAllResultActivity.this,
						"You have chosen : " + " "
								+ obj_itemDetails.getSubscription_name(),
						Toast.LENGTH_LONG).show();
			}
		});

		// adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, arraylistsubscription);
		// listViewMembers.setAdapter(adapter);

	}

	private class HttpAsyncTask extends AsyncTask<String, Void, String> {
		
		private ProgressDialog progressDialog;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = ProgressDialog.show(context, "Wait",
					"We are working on this...");
		}
		
		@Override
		protected String doInBackground(String... urls) {
			return POST(urls[0], subcriptionlist);
		}

		protected void onPostExecute(String result) {
			bindComponents();
			progressDialog.dismiss();
		}

	}

	public String POST(String url, ArrayList<Subscription> subscriptionlist) {
		InputStream inputStream = null;
		String result = "";
		try {

			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			String json = "";
			long unixTime = System.currentTimeMillis() / 1000L;

			StringEntity se = new StringEntity(json);
			httpPost.setEntity(se);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			HttpResponse httpResponse = httpclient.execute(httpPost);
			inputStream = httpResponse.getEntity().getContent();
			if (inputStream != null) {
				result = convertInputStreamToString(inputStream,
						subscriptionlist);

			} else
				result = "Did not work!";
		} catch (Exception e) {
			Log.d("InputStream", e.getLocalizedMessage());
		}

		return result;
	}

	private static String convertInputStreamToString(InputStream inputStream,
			ArrayList<Subscription> subscriptionlist) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null) {
			result += line;
		}
		Log.i("Result", result);
		// Parsing json response to get trip_id
		try {
			JSONArray obj = new JSONArray(result);
			for (int i = 0; i < obj.length(); i++) {
				JSONObject jsonob = new JSONObject(obj.get(i).toString());
				Subscription sub1 = new Subscription(i + "",
						jsonob.getString("companyName"),
						jsonob.getString("status"));
				arraylistsubscriptionRaw.add(sub1);
			

			}
			Subscription sub2= new Subscription("0","Samsung","True");
			Subscription sub3= new Subscription("1","Apple","True");
			Subscription sub4= new Subscription("2","Yahoo","True");
			Subscription sub5= new Subscription("3","Bloomberg","True");
			Subscription sub6= new Subscription("4","Quora","True");
			arraylistsubscriptionRaw.add(sub2);
			arraylistsubscriptionRaw.add(sub3);
			arraylistsubscriptionRaw.add(sub4);
			arraylistsubscriptionRaw.add(sub5);
			arraylistsubscriptionRaw.add(sub6);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		inputStream.close();
		return "true";
	}

}