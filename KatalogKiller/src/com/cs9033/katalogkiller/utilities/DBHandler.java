package com.cs9033.katalogkiller.utilities;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.cs9033.katalogkiller.models.Subscription;
import com.cs9033.katalogkiller.models.User;
import com.nyu.cs9033.eta.models.Trip;


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
    private static final String SUBSCRIPTION_NAME = "subscription_name";
    
    
    
    
 // Creating Database Tables TABLE_USER and TABLE_USER_SUBSCRIBE and TABLE_SUBSCRIPTION
	

 	private static final String TABLE_USER_CREATE = "create table "
 			+ TABLE_USER + "(" + USER_ID+ " integer primary key autoincrement, " 
 			+ USER_NAME + " text not null, "
 			+ USER_EMAIL + " text not null," 
 			+ USER_PASSWORD + "text not null,"
 			+ USER_PHONE_NUMBER + "text not null,"
 			+ USER_ADDRESS+"text not null);";
    

	private static final String TABLE_USER_SUBSCRIBE_CREATE = "create table "
 			+ TABLE_USER_SUBSCRIBE + "(" + USERID+ " text not null, FOREIGN KEY ("+USERID+") REFERENCES "+TABLE_USER+" ("+USER_ID+") ,"
 			+ SUBSCRIPTION_ID + " integer primary key autoincrement,"
 			+ SUBSCRIPTION_STATUS + " text not null,"
 			+ SUBSCRIPTION_NAME + " text not null,);";
	

	
	public DBHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	
	
	public DBHandler(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(TABLE_USER_CREATE);
		db.execSQL(TABLE_USER_SUBSCRIBE_CREATE);
		
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_SUBSCRIBE);
		onCreate(db);

	}

	
	//Add User Subscription
	
	public long addTrip(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(USER_NAME, user.getUser_name()); 
		values.put(USER_EMAIL, user.getEmail_id()); 
		values.put(USER_PASSWORD, user.getPassword()); 
		values.put(USER_PHONE_NUMBER, user.getPhone_number()); 
		values.put(USER_ADDRESS, user.getAddress()); 

		

		long id = db.insert(TABLE_USER, null, values);

		for (Subscription subscription : user.getSubscriptionlist()) {
			values = new ContentValues();
			values.put(USERID, id); 
			values.put(SUBSCRIPTION_ID, subscription.getSubscription_id()); 
			values.put(SUBSCRIPTION_STATUS, subscription.getSubscription_status());
			values.put(SUBSCRIPTION_NAME, subscription.getSubscription_name());
			db.insert(TABLE_USER_SUBSCRIBE, null, values);
		}

		db.close(); 

		return id;
	}
	
	//	Get All Subscription for a User
	
	public ArrayList<Subscription> getAllSubscriptionUser() {
		ArrayList<Subscription> subcription = new ArrayList<Subscription>();
		
String selectQuery = "SELECT  * FROM " + TABLE_USER_SUBSCRIBE;
	    
	    SQLiteDatabase db = this.getReadableDatabase();
	    
	    Cursor cursor = db.rawQuery(selectQuery, null);
	    
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			Subscription subs = new Subscription(cursor.getString(0), 
					cursor.getString(1), cursor.getString(2));
			subcription.add(subs);
		}
          return subcription;
	}
	
	
	public Subscription getSubscriptionDetail(String username, String subscriptionname)
	{
		SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER_SUBSCRIBE, new String[] { USERID,
				SUBSCRIPTION_ID, SUBSCRIPTION_STATUS,SUBSCRIPTION_NAME}, SUBSCRIPTION_NAME + "=?",
				new String[] { String.valueOf(subscriptionname) }, null, null, null, null);
        
        if (cursor != null)
			cursor.moveToFirst();
        
        String SubscriptionId= cursor.getString(1);
		String SubscriptionStatus = cursor.getString(2);
		String SubscriptionName = cursor.getString(3);
		
		Subscription subs = new Subscription(SubscriptionId,SubscriptionName,SubscriptionStatus);
		return subs;
		
		
	}
	
	

	
}
