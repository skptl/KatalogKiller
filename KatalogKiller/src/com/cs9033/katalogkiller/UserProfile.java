package com.cs9033.katalogkiller;

import com.cs9033.katalogkiller.models.User;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserProfile extends Activity {
	
	private TextView username;
	private TextView useremail;
	private EditText usernumber;
	private EditText useraddress;
	
	private User user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		
		username=(TextView) findViewById(R.id.username);
		useremail=(TextView) findViewById(R.id.emailid);
		usernumber=(EditText) findViewById(R.id.phonenumber);
		useraddress=(EditText) findViewById(R.id.address);

		user=getIntent().getParcelableExtra("USER");
		username.setText(user.getUser_name());
		useremail.setText(user.getEmail_id());
		usernumber.setText(user.getPhone_number());
		useraddress.setText(user.getAddress());
		
		System.out.println(user.toString());
	}

}
