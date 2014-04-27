package com.cs9033.katalogkiller.utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHandler extends SQLiteOpenHelper {
	
	 // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 4;
 
    // Database Name
    private static final String DATABASE_NAME = "KatalogKillerDB";
    
    // User Table table name
    private static final String TABLE_USER = "UserTable";
 
    // User Table Columns names
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";
    private static final String USER_EMAIL = "user_email";
    private static final String USER_PASSWORD = "user_password";
    private static final String USER_PHONE_NUMBER = "user_phone_number";
    private static final String USER_ADDRESS = "user_address";
    
    // User Subscription table name
    private static final String TABLE_USER_SUBSCRIBE= "UserSubscribeTable";
 
    // User Subscription Table Columns names
    private static final String USERID = "user_id";
    private static final String SUBSCRIPTION_ID = "subscription_id";
    private static final String SUBSCRIPTION_STATUS = "subscription_status";
    
    
    //Master Subscription table name
    
    private static final String TABLE_SUBSCRIPTION= "SubscriptionTable";
    
    // Master Subscription Table Columns names
    private static final String SUBSCRIPTIONID = "subscription_id";
    private static final String SUBSCRIPTION_NAME = "subscription_name";
    
    
    
    

	public DBHandler(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	



}
