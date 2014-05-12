package com.cs9033.katalogkiller;

import com.cs9033.katalogkiller.models.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity {

	private static String TAG = "HomeActivity";

	private Button cameraButton;
	private Button catalogButton;
	private Button faqButton;
	private Button leadeBoardButton;
	private TextView txtusername;
	private User user;
	
	private static final int SCANNER_ACTIVITY = 1111; 
	private static final int VIEW_ALL_REQUEST=100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		user=getIntent().getParcelableExtra("USER");
		//System.out.println(user.toString());

		bindComponents();
		addListeners();
	}

	private void bindComponents() {

		cameraButton = (Button) findViewById(R.id.camera_button);
		catalogButton = (Button) findViewById(R.id.catalog_button);
		faqButton = (Button) findViewById(R.id.faq_button);
		leadeBoardButton = (Button) findViewById(R.id.leaderboard_button);
		
		

	}

	private void addListeners() {

		cameraButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent scannerActivity = new Intent(HomeActivity.this, CameraActivity.class);
	      	    startActivityForResult(scannerActivity, SCANNER_ACTIVITY);
			}
		});

		catalogButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent Viewallrequest = new Intent(HomeActivity.this, ViewAllRequestActivity.class);
	      	    startActivityForResult(Viewallrequest, VIEW_ALL_REQUEST);
			}
		});

		faqButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

		leadeBoardButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId())
        {
        case R.id.action_settings:
            // Single menu item is selected do something
            // Ex: launching new activity/screen or show alert message
            Toast.makeText(HomeActivity.this, "Setting is Selected", Toast.LENGTH_SHORT).show();
            return true;
 
        case R.id.action_profile:
            Toast.makeText(HomeActivity.this, "Profile is Selected", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(HomeActivity.this,UserProfile.class);
            i.putExtra("USER",user);
			startActivity(i);
            return true;
  
        default:
            return super.onOptionsItemSelected(item);
        }
	}
}
