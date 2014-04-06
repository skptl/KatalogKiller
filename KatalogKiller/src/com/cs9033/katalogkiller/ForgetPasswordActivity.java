package com.cs9033.katalogkiller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetPasswordActivity extends Activity {
	
	private EditText edtemail;
	private EditText edtemail2;
	private Button btnsubmit;
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
		btnsubmit= (Button)findViewById(R.id.btnforgetlogin);
		
		btnsubmit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
            	Emailid1= edtemail.getText().toString();
            	Emailid2= edtemail2.getText().toString();
            	if(Emailid1.equals(Emailid2))
            	{
            	Intent forgetpasswordIn = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
	      	      startActivity(forgetpasswordIn);
            	}
            	else
            	{
            		 Toast.makeText(getApplicationContext(), "Password didn't match, Please re-type",
            			      Toast.LENGTH_SHORT).show();
            	}
            }
        });
	}
	

}
