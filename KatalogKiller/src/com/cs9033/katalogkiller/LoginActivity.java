package com.cs9033.katalogkiller;


import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	private static String TAG = "LoginActivity";
	private static final String APP_ID="629800467114260";
	private String trackUsername;


	 private EditText  username=null;
    private EditText  password=null;
    private TextView forgetpassword;
    private Button login;
    private Button btnregister;
    private Button fblogin;
    
    Facebook fb=new Facebook(APP_ID);
    SharedPreferences pref;
    
    
    
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
	        
	        pref=getPreferences(MODE_PRIVATE);
	        String user_token=pref.getString("USER_TOKEN",null);
	        Long user_expires=pref.getLong("USER_EXPIRE",0);
	        
	        if(user_token!=null){
	        	user_token=fb.getAccessToken();
	        }
	        if(user_expires!=0){
	        	user_expires=fb.getAccessExpires();
	        }
	        
	        
	        username = (EditText)findViewById(R.id.editText1);
	        password = (EditText)findViewById(R.id.editText2);
	        forgetpassword = (TextView)findViewById(R.id.textView5);
	        login = (Button)findViewById(R.id.fbloginbutton); 
	        btnregister = (Button)findViewById(R.id.btnregister);
	        fblogin=(Button)findViewById(R.id.fbloginbutton);

	        forgetpassword.setClickable(true);
	        
	        forgetpassword.setOnClickListener(new View.OnClickListener(){
	            public void onClick(View v){
	            	Intent forgetpasswordIn = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
	      	      startActivity(forgetpasswordIn);
	      	      
	            }
	        });
	        
	        
	        btnregister.setOnClickListener(new View.OnClickListener(){
	            public void onClick(View v){
	            	Intent registerIn = new Intent(LoginActivity.this, RegisterActivity.class);
	      	      startActivity(registerIn);
	      	      
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
	     
	  @SuppressWarnings("deprecation")
		public void fblogin(View v){
			  if(fb.isSessionValid()){
				  Intent i=new Intent(LoginActivity.this,LeaderBoardActivity.class);
				  startActivity(i);  
			  }
			  else{
				  fb.authorize(LoginActivity.this,new DialogListener(){

					@Override
					public void onComplete(Bundle values) {
						Editor edit=pref.edit();
						edit.putString("USER_TOKEN",fb.getAccessToken());
						edit.putLong("USER_EXPIRE",fb.getAccessExpires());
						edit.commit();
						Toast.makeText(LoginActivity.this,"USER Logged In:  ",Toast.LENGTH_SHORT).show();
						
						//Do Something with User Data
					}

					@Override
					public void onFacebookError(FacebookError e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onError(DialogError e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onCancel() {
						// TODO Auto-generated method stub
						
					}
					  
				  });
			  }
		  }
		
		@SuppressWarnings("deprecation")
		@Override
		/// this method will be called when user submits Facebook credentials 
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
			fb.authorizeCallback(requestCode, resultCode, data);
		}
	   

	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

