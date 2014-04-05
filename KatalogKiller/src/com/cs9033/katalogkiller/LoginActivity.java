package com.cs9033.katalogkiller;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	private static String TAG = "LoginActivity";
	private String trackUsername;


	 private EditText  username=null;
    private EditText  password=null;
    private TextView forgetpassword;
    private Button login;
    
    
    
	/*// load the library - name matches jni/Android.mk 
	  static {
	    System.loadLibrary("KatalogKiller");
	  }
	 
	  // declare the native code function - must match ndkfoo.c
	  private native String invokeNativeFunction();
	    */
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.login);
	        username = (EditText)findViewById(R.id.editText1);
	        password = (EditText)findViewById(R.id.editText2);
	        forgetpassword = (TextView)findViewById(R.id.textView5);
	        login = (Button)findViewById(R.id.button1); 
	        forgetpassword.setClickable(true);
	        
	        forgetpassword.setOnClickListener(new View.OnClickListener(){
	            public void onClick(View v){
	                //Do Stuff
	            }
	        });
	        
	      /*  // this is where we call the native code
	        String hello = invokeNativeFunction();
	         
	        new AlertDialog.Builder(this).setMessage(hello).show(); 
	        */
	       
	    }
	  public void login(View view){
	      if(username.getText().toString().equals("admin") && 
	      password.getText().toString().equals("admin")){
	      Toast.makeText(getApplicationContext(), "Redirecting...", 
	      Toast.LENGTH_SHORT).show();
	      
	      Intent homePageIntent = new Intent(this, HomeActivity.class);
	      homePageIntent.putExtra(trackUsername, "Username");
	      startActivity(homePageIntent);
	      
	      
	   }	
	   else{
	      Toast.makeText(getApplicationContext(), "Wrong Credentials",
	      Toast.LENGTH_SHORT).show();
	   }
	  }
	     

	   

	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

