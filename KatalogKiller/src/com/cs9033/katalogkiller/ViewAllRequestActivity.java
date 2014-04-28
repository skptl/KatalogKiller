package com.cs9033.katalogkiller;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class ViewAllRequestActivity extends TabActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewallrequest);
        TabHost tabHost = getTabHost();       
      
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("View All Requests").setContent(new Intent(this, ViewAllResultActivity.class)));
      
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("View Processed Results").setContent(new Intent(this, ViewProcessedResultActivity.class)));
        
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("View Pending Requests").setContent(new Intent(this, ViewPendingResultActivity.class)));
        
        
        tabHost.setCurrentTab(1); 
    }
}
