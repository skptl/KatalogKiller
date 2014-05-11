package com.cs9033.katalogkiller;

import com.cs9033.katalogkiller.models.User;
import com.cs9033.katalogkiller.utilities.DBHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserProfile extends Activity {
	
	private TextView username;
	private TextView useremail;
	private EditText usernumber;
	private EditText useraddress;
	private Button update;
	
	private DBHandler dbhelper; 
	
	private User user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		dbhelper=new DBHandler(this);
		username=(TextView) findViewById(R.id.username);
		useremail=(TextView) findViewById(R.id.emailid);
		usernumber=(EditText) findViewById(R.id.phonenumber);
		useraddress=(EditText) findViewById(R.id.address);
		update=(Button) findViewById(R.id.updateprofile);

		user=getIntent().getParcelableExtra("USER");
		username.setText(user.getUser_name());
		useremail.setText(user.getEmail_id());
		usernumber.setText(user.getPhone_number());
		useraddress.setText(user.getAddress());
		
		System.out.println(user.toString());
	
	
		update.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View v) {
			int row=dbhelper.updateInfo(useremail.getText().toString(),usernumber.getText().toString(),useraddress.getText().toString());
			System.out.println(row);	
			}
		});

}
}
