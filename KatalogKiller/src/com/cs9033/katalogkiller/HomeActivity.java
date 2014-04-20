package com.cs9033.katalogkiller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends Activity {

	private static String TAG = "HomeActivity";

	private Button cameraButton;
	private Button catalogButton;
	private Button faqButton;
	private Button leadeBoardButton;
	
	private static final int SCANNER_ACTIVITY = 1111; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);

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
				Intent scannerActivity = new Intent(HomeActivity.this, ScannerActivity.class);
	      	    startActivityForResult(scannerActivity, SCANNER_ACTIVITY);
			}
		});

		catalogButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

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

}
