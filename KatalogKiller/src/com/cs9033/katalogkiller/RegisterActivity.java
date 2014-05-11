package com.cs9033.katalogkiller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cs9033.katalogkiller.models.User;
import com.cs9033.katalogkiller.utilities.DBHandler;
import com.cs9033.katalogkiller.utilities.Utilities;

public class RegisterActivity extends Activity {
	
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
		 
		 edtPersonName = (EditText)findViewById(R.id.editName);
		 editEmailId = (EditText)findViewById(R.id.editemailid);
		 edtPassword = (EditText)findViewById(R.id.editPassword);
		 edtPhoneNumber = (EditText)findViewById(R.id.editPhonenumber);
		 edtAddress=(EditText)findViewById(R.id.editAddress);
		 txtMember = (TextView)findViewById(R.id.textalreadyamember);
		 btnRegister = (Button)findViewById(R.id.btnregister);
		 KatalogDB = new DBHandler(this);

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
		  	      	    
		  	      	Intent registerIn = new Intent(RegisterActivity.this, LoginActivity.class);
		      	      startActivity(registerIn);
	            		
	            	}
	            }
	        });
	}


}
