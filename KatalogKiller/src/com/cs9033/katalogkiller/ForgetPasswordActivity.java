package com.cs9033.katalogkiller;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class ForgetPasswordActivity extends Activity {
	
	private EditText edtemail;
	private EditText edtemail2;
	private String Emailid1;
	private String Emailid2;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgetpassword);
		edtemail = (EditText)findViewById(R.id.editemailid);
		edtemail2 = (EditText)findViewById(R.id.editemailid2);
		
	}
	

}
