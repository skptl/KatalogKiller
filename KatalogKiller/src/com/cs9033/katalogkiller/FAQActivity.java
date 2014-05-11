package com.cs9033.katalogkiller;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class FAQActivity extends Activity{
	
	WebView browser;
	@Override
	  public void onCreate(Bundle icicle) {
	    super.onCreate(icicle);
	    setContentView(R.layout.faqstaticlayout);
	    browser=(WebView)findViewById(R.id.webkit);
	    
	    browser.loadData("<html><body>Enter the FAQ Data</body></html>",
	              "text/html", "UTF-8");
	  }

}
