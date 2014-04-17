package com.cs9033.katalogkiller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegisterActivity extends Activity {

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
