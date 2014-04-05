package com.example.katalogkiller;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class LeaderBoardActivity extends Activity {
	
	private static String TAG = "LeaderBoardActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leader_board);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
