package com.cs9033.katalogkiller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

	private EditText username = null;
	private EditText password = null;
	private TextView forgetpassword;
	private Button login;
	private Button btnregister;

	private Button facebooklogin;
	//private Facebook facebook;
	//private AsyncFacebookRunner mAsyncRunner;
	String FILENAME = "AndroidSSO_data";
	private SharedPreferences mPrefs;

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

		

		username = (EditText) findViewById(R.id.editText1);
		password = (EditText) findViewById(R.id.editText2);
		forgetpassword = (TextView) findViewById(R.id.textView4);
		btnregister = (Button) findViewById(R.id.btnregister);
		facebooklogin = (Button) findViewById(R.id.authButton);

		forgetpassword.setClickable(true);

		forgetpassword.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent forgetpasswordIn = new Intent(LoginActivity.this,
						ForgetPasswordActivity.class);
				startActivity(forgetpasswordIn);

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
		if (username.getText().toString().equals("admin")
				&& password.getText().toString().equals("admin")) {
			Toast.makeText(getApplicationContext(), "Redirecting...",
					Toast.LENGTH_SHORT).show();

			Intent homePageIntent = new Intent(this, HomeActivity.class);
			homePageIntent.putExtra(trackUsername, "Username");
			startActivity(homePageIntent);

		} else {
			Toast.makeText(getApplicationContext(), "Wrong Credentials",
					Toast.LENGTH_SHORT).show();
		}
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
