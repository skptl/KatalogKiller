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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.cs9033.katalogkiller.models.Subscription;
import com.cs9033.katalogkiller.utilities.DBHandler;
import com.cs9033.katalogkiller.utilities.Utilities;

public class ViewProcessedResultActivity extends Activity{
	 private ListView listViewMembers;

	 	private ArrayList<String> arraylistsubscription;
	 	private static ArrayList<Subscription> arraylistsubscriptionRaw;
	 	private ArrayAdapter<String> adapter;
	 	
	 	private DBHandler KatalogDB;
	 	
	 	private Activity activity = this;
	 	private Activity context = this;
		private ArrayList<Subscription> subcriptionlist;
	 	
	 	
	 	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.viewprocessedrequest);
	        arraylistsubscriptionRaw = new ArrayList<>();
	        listViewMembers = (ListView) findViewById(R.id.viewprocessedrequest);
	     
	        new HttpAsyncTask().execute(Utilities.serverURL + "requests/getall");
	        
	        KatalogDB = new DBHandler(this);
			bindComponents();

}
	 	 
	 	/**
			 * Method to bind all components to the controller.
			 */
			private void bindComponents() {
				
				
			
				
				
				
				
				

				//arraylistsubscriptionRaw = KatalogDB.getAllProcessedSubscriptionUser();
				
				arraylistsubscription = new ArrayList<String>();
				
				for (Subscription subs  : arraylistsubscriptionRaw) {
					arraylistsubscription.add(subs.getSubscription_name());
				}

				

				adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arraylistsubscription);
				listViewMembers.setAdapter(adapter);

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
				
				} catch (Exception e) {
					e.printStackTrace();
				}
				inputStream.close();
				return "true";
			}
}
