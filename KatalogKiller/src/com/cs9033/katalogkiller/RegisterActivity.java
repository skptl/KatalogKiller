package com.cs9033.katalogkiller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cs9033.katalogkiller.models.User;
import com.cs9033.katalogkiller.utilities.DBHandler;
import com.cs9033.katalogkiller.utilities.GPSTracker;
import com.cs9033.katalogkiller.utilities.Utilities;

public class RegisterActivity extends Activity{
	
	private EditText edtPersonName;
	private EditText editEmailId;
	private EditText edtPassword;
	private EditText edtPhoneNumber;
	private EditText edtAddress;
	private TextView txtMember;
	private Button btnRegister;
	String personnametext=null;
	String emailidtext=null;
	String passwordtext=null;
	String phonenumbertext=null;
	String addresstext=null;
	
	private static double latitude = 0.0;
	private static double longitude=0.0;
	private LocationManager locationManager;
	private String provider;
	private String serverid;
	
	private DBHandler KatalogDB;
	private Activity activity = this;
	private User user = new User(null, null, null, null, null, null, null);
	
	

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.register);
		// check if GPS enabled
	     GPSTracker gpsTracker = new GPSTracker(this);
		 
		 edtPersonName = (EditText)findViewById(R.id.editName);
		 editEmailId = (EditText)findViewById(R.id.editemailid);
		 edtPassword = (EditText)findViewById(R.id.editPassword);
		 edtPhoneNumber = (EditText)findViewById(R.id.editPhonenumber);
		 edtAddress=(EditText)findViewById(R.id.editAddress);
		 txtMember = (TextView)findViewById(R.id.textalreadyamember);
		 btnRegister = (Button)findViewById(R.id.btnregister);
		 KatalogDB = new DBHandler(this);
		 edtAddress.setEnabled(true);
		 edtAddress.setText(gpsTracker.getAddressLine(this));
		 
		 txtMember.setClickable(true);
		 
		 txtMember.setOnClickListener(new View.OnClickListener(){
	            public void onClick(View v){
	            	Intent forgetpasswordIn = new Intent(RegisterActivity.this, LoginActivity.class);
	      	      startActivity(forgetpasswordIn);
	      	    
	            }
	        });
		 
		 btnRegister.setOnClickListener(new View.OnClickListener(){
	            public void onClick(View v){
	            	Utilities.log("TEST", "above", -1);
	            	personnametext=edtPersonName.getText().toString();
	            	emailidtext=editEmailId.getText().toString();
	            	passwordtext=edtPassword.getText().toString();
	            	phonenumbertext=edtPhoneNumber.getText().toString();
	            	addresstext=edtAddress.getText().toString();
	            	System.out.println("Personm name"+personnametext);
	            	if(personnametext.matches("")|| emailidtext.matches("")
	            			|| passwordtext.matches("") || phonenumbertext.matches("")
	            			|| addresstext.matches(""))
	            	{
	            		Toast.makeText(getApplicationContext(), "Please enter all fields to complete registration",
	        					Toast.LENGTH_SHORT).show();
	        		
	        		 
	            	}
	            	else
	            	{
	            		 user.setUser_name(edtPersonName.getText().toString());
		        		  user.setEmail_id(editEmailId.getText().toString());
		        		  user.setPassword(edtPassword.getText().toString());
		        		  user.setPhone_number(edtPhoneNumber.getText().toString());
		        		  user.setAddress(edtAddress.getText().toString());
		  	      	    long id =  KatalogDB.addUser(user);
		  	      	    
		  	      	new HttpAsyncTask()
					.execute(Utilities.serverURL +"users/add/");
		  	      	    
		  	      	    
		  	      	    if(id!=-1){
		  	      	Intent registerIn = new Intent(RegisterActivity.this, LoginActivity.class);
		      	      startActivity(registerIn);
		  	      	    }
		  	      	    else{
		  	      	    	Toast.makeText(RegisterActivity.this,"User Already Exists",Toast.LENGTH_SHORT).show();
		  	      	    }
	            	}
	            }
	        });
	}
	
	
	public String POST(String url, User user) {
		InputStream inputStream = null;
		String result = "";
		try {

			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			String json = "";
			long unixTime = System.currentTimeMillis() / 1000L;
			
			
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("fName", user.getUser_name());
			jsonObject.accumulate("lName", user.getUser_name());
			jsonObject.accumulate("email", user.getEmail_id());
			jsonObject.accumulate("password", user.getPassword());
			jsonObject.accumulate("address", user.getPassword());
			jsonObject.accumulate("zipCode", "00000");
			jsonObject.accumulate("phoneNumber", user.getPhone_number());
		
		
			json = jsonObject.toString();
			StringEntity se = new StringEntity(json);
			httpPost.setEntity(se);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			HttpResponse httpResponse = httpclient.execute(httpPost);
			inputStream = httpResponse.getEntity().getContent();
			if (inputStream != null) {
				result = convertInputStreamToString(inputStream);
				Log.i("user_id", result);

			} else
				result = "Did not work!";

		} catch (Exception e) {
			Log.d("InputStream", e.getLocalizedMessage());
		}

		return result;
	}
	
	
	private class HttpAsyncTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {
			return POST(urls[0], user);
		}

		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {

		}
	}
	
	private static String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null) {
			result += line;
		}
		return result;


	}
	
	
}
