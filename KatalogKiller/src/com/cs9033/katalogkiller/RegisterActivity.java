package com.cs9033.katalogkiller;

import android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends Activity {
	
	private EditText edtPersonName;
	private EditText editEmailId;
	private EditText edtPassword;
	private EditText edtPhoneNumber;
	private TextView txtMember;
	private Button btnRegister;
	
	
	
	

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.register);
		 
		 edtPersonName = (EditText)findViewById(R.id.editName);
		 editEmailId = (EditText)findViewById(R.id.editemailid);
		 edtPassword = (EditText)findViewById(R.id.editPassword);
		 edtPhoneNumber = (EditText)findViewById(R.id.editPhonenumber);
		 txtMember = (TextView)findViewById(R.id.textalreadyamember);
		 btnRegister = (Button)findViewById(R.id.btnregister);

		 txtMember.setClickable(true);
	        
		 txtMember.setOnClickListener(new View.OnClickListener(){
	            public void onClick(View v){
	            	Intent forgetpasswordIn = new Intent(RegisterActivity.this, LoginActivity.class);
	      	      startActivity(forgetpasswordIn);
	      	      
	            }
	        });
		 
		 btnRegister.setOnClickListener(new View.OnClickListener(){
	            public void onClick(View v){
	            	Intent intHome=new Intent(RegisterActivity.this,HomeActivity.class);
	        		startActivity(intHome);
	      	      
	            }
	        });
	}


}
