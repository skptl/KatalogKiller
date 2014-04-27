package com.cs9033.katalogkiller;

import android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity {
	
	private EditText edtPersonName;
	private EditText editEmailId;
	private EditText edtPassword;
	private EditText edtPhoneNumber;
	private Button btnRegister;
	
	

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.register);
	}
	
	public void onRegister(View v){
		Intent i=new Intent(this,HomeActivity.class);
		startActivity(i);
	}

}
