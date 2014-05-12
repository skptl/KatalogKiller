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
	    
	    browser.loadData("<html><title>---FAQ---</title></head><body><p><strong> Katalog Killer App</strong> </p> <p> Steps to follow: </p><p>1. Click your catalogue image to unsubscribe </p><p>2. Decode the Image </p><p>3. If Decode successful, Select suggested options </p><p>4. If decode not successful, Please enter the company name manually </p><p>5. Check Request Information under Catalogue Button on the Home Screen</p></body></html>",
	              "text/html",  "UTF-8");
	  }

}
