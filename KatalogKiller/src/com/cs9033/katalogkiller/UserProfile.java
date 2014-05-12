package com.cs9033.katalogkiller;

import com.cs9033.katalogkiller.models.User;
import com.cs9033.katalogkiller.utilities.DBHandler;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfile extends Activity {
	
	private TextView username;
	private TextView useremail;
	private EditText usernumber;
	private EditText useraddress;
	private Button update;
	
	private DBHandler dbhelper; 
	
	private User userupdate;
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
		
		String useremailtext=getIntent().getStringExtra("EMAIL");
		System.out.println("USernnam==="+useremailtext);
		Cursor cur=dbhelper.userProfileInfo(useremailtext);
		username.setText(cur.getString(1));
		useremail.setText(cur.getString(2));
		
		usernumber.setText(cur.getString(4));
		useraddress.setText(cur.getString(5));
		
		System.out.println(cur.toString());
	
	
		update.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View v) {
			int row=dbhelper.updateInfo(useremail.getText().toString(),usernumber.getText().toString(),useraddress.getText().toString());
			System.out.println(row);	
			if(row==1){
			Toast.makeText(UserProfile.this,"Profile Updated",Toast.LENGTH_SHORT).show();	
			Intent i=new Intent(UserProfile.this,HomeActivity.class);
			startActivity(i);
			}
			else
			{
				Toast.makeText(UserProfile.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();	
			}
			}
		});

}
}
