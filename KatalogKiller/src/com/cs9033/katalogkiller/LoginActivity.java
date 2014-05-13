package com.cs9033.katalogkiller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cs9033.katalogkiller.models.User;
import com.cs9033.katalogkiller.utilities.DBHandler;
import com.cs9033.katalogkiller.utilities.GPSTracker;
import com.cs9033.katalogkiller.utilities.Utilities;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

public class LoginActivity extends Activity {

	private static String TAG = "LoginActivity";
	private static final String APP_ID = "629800467114260";
	private String trackUsername;
	private User user = new User(null, null, null, null, null, null, null);

	private EditText username = null;
	private EditText password = null;
	private TextView forgetpassword;
	private Button login;
	private Button btnregister;
	private CheckBox rememberme;
	GPSTracker gpsTracker;
	private DBHandler KatalogDB;

	private Button facebooklogin;
	private Facebook facebook;
	private AsyncFacebookRunner mAsyncRunner;
	String FILENAME = "AndroidSSO_data";
	private SharedPreferences mPrefs;
	private DBHandler db; 

	/*
	 * // load the library - name matches jni/Android.mk static {
	 * System.loadLibrary("KatalogKiller"); }
	 * 
	 * // declare the native code function - must match ndkfoo.c private native
	 * String invokeNativeFunction();
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginnew);
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		db = new DBHandler(this);
		
		boolean remembermevalue = sharedPreferences.getBoolean("REMEMBER",false);
		if(remembermevalue){
			Toast.makeText(getApplicationContext(), "Redirecting...",
					Toast.LENGTH_SHORT).show();
			User userinfo = db.userInfo(sharedPreferences.getString("EMAIL","null"));
			Intent homePageIntent = new Intent(this, HomeActivity.class);
			homePageIntent.putExtra("USER", userinfo);
			homePageIntent.putExtra(trackUsername, "Username");
			startActivity(homePageIntent);
			
		}
		


		facebook = new Facebook(APP_ID);
		mAsyncRunner = new AsyncFacebookRunner(facebook);

		gpsTracker = new GPSTracker(this);
		KatalogDB = new DBHandler(this);

		username = (EditText) findViewById(R.id.editText1);
		password = (EditText) findViewById(R.id.editText2);
		forgetpassword = (TextView) findViewById(R.id.textView4);
		btnregister = (Button) findViewById(R.id.btnregister);
		facebooklogin = (Button) findViewById(R.id.authButton);
		rememberme = (CheckBox) findViewById(R.id.rememberme);
		login= (Button)findViewById(R.id.btnlogin);
		
		login.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				login(v);

			}
		});

		forgetpassword.setClickable(true);

		forgetpassword.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent forgetpasswordIn = new Intent(LoginActivity.this,
						ForgetPasswordActivity.class);
				startActivity(forgetpasswordIn);

			}
		});

		facebooklogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				fbLogin();
			}
		});

		btnregister.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent registerIn = new Intent(LoginActivity.this,
						RegisterActivity.class);
				startActivity(registerIn);

			}
		});

		/*
		 * // this is where we call the native code String hello =
		 * invokeNativeFunction();
		 * 
		 * new AlertDialog.Builder(this).setMessage(hello).show();
		 */

	}

	public void login(View view) {
		/*
		 * if (username.getText().toString().equals("admin") &&
		 * password.getText().toString().equals("admin")) {
		 */
		
		
		String Password = password.getText().toString();
		String Username = username.getText().toString();
		
		new HttpAsyncTask(Username)
		.execute(Utilities.serverURL +"users/authenticate/"+ Username+ "/" +Password);
		
		
		//DBHandler db = new DBHandler(this);
		//String Password = db.getPassword(username.getText().toString());
	
	}
	
	
	
	
	
	private class HttpAsyncTask extends AsyncTask<String, Void, String> {
		
		String Username;
		
		public HttpAsyncTask(String username) {
			this.Username = username;
		}

		@Override
		protected String doInBackground(String... urls) {
			return POST(urls[0]);
		}

		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {
			
			
			if (result.equals("true")) {
				Utilities.userName = Username;
				if (rememberme.isChecked()) {
					SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);

					Editor editor = sharedPreferences.edit();

					editor.putBoolean("REMEMBER",true);
					editor.putString("EMAIL",username.getText().toString());
					editor.putString("PASSWORD",password.getText().toString());
					editor.commit();

				}
				String userinfo = username.getText().toString();
				Intent homePageIntent = new Intent(LoginActivity.this, HomeActivity.class);
				homePageIntent.putExtra("USER", userinfo);
				startActivity(homePageIntent);
			}

			// }
			else {
				Toast.makeText(getApplicationContext(), "Wrong Credentials",
						Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	
	
	public String POST(String url) {
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
				result = convertInputStreamToString(inputStream);
				
				
			} else
				result = "Did not work!";

		} catch (Exception e) {
			Log.d("InputStream", e.getLocalizedMessage());
		}
         
		return result;
		
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
	
		
		inputStream.close();
		return result;

	}
	
	

	@SuppressWarnings("deprecation")
	public void fbLogin() {
		Log.d(TAG, "fbLogin");
		mPrefs = getPreferences(MODE_PRIVATE);
		String access_token = mPrefs.getString("access_token", null);
		long expires = mPrefs.getLong("access_expires", 0);

		if (access_token != null) {
			facebook.setAccessToken(access_token);
		}

		if (expires != 0) {
			facebook.setAccessExpires(expires);
		}

		if (!facebook.isSessionValid()) {
			facebook.authorize(this,
					new String[] { "email", "publish_stream" },
					new DialogListener() {

						@Override
						public void onCancel() {
							// Function to handle cancel event
						}

						@Override
						public void onComplete(Bundle values) {
							// Function to handle complete event
							// Edit Preferences and update facebook acess_token
							SharedPreferences.Editor editor = mPrefs.edit();
							editor.putString("access_token",
									facebook.getAccessToken());
							editor.putLong("access_expires",
									facebook.getAccessExpires());
							editor.commit();
							// Toast.makeText(LoginActivity.this,"User Logged In",Toast.LENGTH_LONG).show();
							getProfileInformation();
						}

						@Override
						public void onError(DialogError error) {
							// Function to handle error

						}

						@Override
						public void onFacebookError(FacebookError fberror) {
							// Function to handle Facebook errors

						}

					});
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	// / this method will be called when user submits Facebook credentials
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		facebook.authorizeCallback(requestCode, resultCode, data);
	}

	@SuppressWarnings("deprecation")
	public void getProfileInformation() {
		// check if GPS enabled

		mAsyncRunner.request("me", new RequestListener() {
			@Override
			public void onComplete(String response, Object state) {
				Log.d("Profile", response);
				String json = response;
				try {
					JSONObject profile = new JSONObject(json);
					// getting name of the user
					final String firstname = profile.getString("first_name");
					final String lastname = profile.getString("last_name");
					// getting email of the user
					final String email = profile.getString("email");
					user.setEmail_id(profile.getString("email"));
					user.setPassword("");
					user.setAddress(gpsTracker
							.getAddressLine(LoginActivity.this));
					user.setPhone_number("");
					user.setUser_name(profile.getString("name"));
					System.out.println(user.toString());
					long id = KatalogDB.addUser(user);
/*					runOnUiThread(new Runnable() {

						@Override
						public void run() {

							Toast.makeText(
									getApplicationContext(),
									"Name: " + firstname + "   " + lastname
											+ "\nEmail: " + email,
									Toast.LENGTH_LONG).show();
						}

					});*/

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			public void onIOException(IOException e, Object state) {
			}

			public void onFileNotFoundException(FileNotFoundException e,
					Object state) {
			}

			public void onMalformedURLException(MalformedURLException e,
					Object state) {
			}

			public void onFacebookError(FacebookError e, Object state) {
			}
		});
	}

}
