package com.cs9033.katalogkiller.utilities;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cs9033.katalogkiller.models.Subscription;
import com.cs9033.katalogkiller.models.User;


public class DBHandler extends SQLiteOpenHelper {
	
	private static final String TAG = "DBHandler";
	
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
    private static final String USERID = "userid";
    private static final String SUBSCRIPTION_ID = "subscription_id";
    private static final String SUBSCRIPTION_STATUS = "subscription_status";
    private static final String SUBSCRIPTION_NAME = "subscription_name";
    
    
    
    
 // Creating Database Tables TABLE_USER and TABLE_USER_SUBSCRIBE and TABLE_SUBSCRIPTION
	

 	private static final String TABLE_USER_CREATE = "create table "
 			+ TABLE_USER + "(" + USER_ID+ " integer primary key autoincrement, " 
 			+ USER_NAME + " text not null, "
 			+ USER_EMAIL + " text not null unique," 
 			+ USER_PASSWORD + " text not null,"
 			+ USER_PHONE_NUMBER + " text not null,"
 			+ USER_ADDRESS+" text not null);";
    

	/*private static final String TABLE_USER_SUBSCRIBE_CREATE = "create table "
 			+ TABLE_USER_SUBSCRIBE + "(" 
 			+ SUBSCRIPTION_ID + " integer primary key autoincrement,"
		    + USERID + " text not null,"
			+ "FOREIGN KEY ("+USERID+") REFERENCES "+TABLE_USER+" ("+USER_ID+"),"
 			+ SUBSCRIPTION_STATUS + " text not null,"
 			+ SUBSCRIPTION_NAME + " text not null);";*/
	

	
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
		//db.execSQL(TABLE_USER_SUBSCRIBE_CREATE);
		Log.i(TAG,"Tables Created");
		
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_SUBSCRIBE);
		onCreate(db);

	}

	
	
	//Add USER
	

	public long addUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(USER_NAME, user.getUser_name()); 
		values.put(USER_EMAIL, user.getEmail_id()); 
		values.put(USER_PASSWORD, user.getPassword()); 
		values.put(USER_PHONE_NUMBER, user.getPhone_number()); 
		values.put(USER_ADDRESS, user.getAddress()); 

		

		long id = db.insert(TABLE_USER, null, values);
		 
		db.close();
		return id;
	}
	
	//Add User Subscription
	
	public long addUserAndSubscription(User user) {
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
		
/*String selectQuery = "SELECT  * FROM " + TABLE_USER_SUBSCRIBE;
	    
	    SQLiteDatabase db = this.getReadableDatabase();
	    
	    Cursor cursor = db.rawQuery(selectQuery, null);
	    
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			Subscription subs = new Subscription(cursor.getString(0), 
					cursor.getString(1), cursor.getString(2));
			subcription.add(subs);
		}*/
		Subscription sub1= new Subscription("0","HCL Tech","True");
		Subscription sub2= new Subscription("1","IBM","True");
		Subscription sub3= new Subscription("2","MICROSOFT","True");
		Subscription sub4= new Subscription("3","GOOGLE","True");
		Subscription sub5= new Subscription("4","EBAY","True");
		
		Subscription sub6= new Subscription("5","Samsung","True");
		Subscription sub7= new Subscription("6","Apple","True");
		Subscription sub8= new Subscription("7","Yahoo","True");
		Subscription sub9= new Subscription("8","Bloomberg","True");
		Subscription sub10= new Subscription("9","Quora","True");
		
		subcription.add(sub1);
		subcription.add(sub2);
		subcription.add(sub3);
		subcription.add(sub4);
		subcription.add(sub5);
		subcription.add(sub6);
		subcription.add(sub7);
		subcription.add(sub8);
		subcription.add(sub9);
		subcription.add(sub10);
		
		return subcription;
	}
	
	
//	Get Password
	
	public String getPassword(String username ) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cur = db.rawQuery("select USER_PASSWORD from UserTable where USER_EMAIL=? ",new String[]{username});
		if (cur.moveToFirst()) {
			String tid = cur.getString(0);
			Log.i("LOG", tid);
		}
		db.close(); 
		return cur.getString(0);
	}
	
	public User userInfo(String username){
		User user = new User(null, null, null, null, null, null, null);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cur = db.rawQuery("select * from UserTable where USER_EMAIL=? ",new String[]{username});
		if (cur.moveToFirst()) {
			
			user.setUser_name(cur.getString(1));
			user.setEmail_id(cur.getString(2));
			user.setAddress(cur.getString(5));
			user.setPhone_number(cur.getString(4));
			System.out.println(user.toString());

		}
		db.close(); 
		return user;
		
	}
	
	
	
	
//	Get All Processed Subscription for a User
	
	public ArrayList<Subscription> getAllProcessedSubscriptionUser() {
		ArrayList<Subscription> subcription = new ArrayList<Subscription>();
		
		/*SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER_SUBSCRIBE, new String[] { USERID,
				SUBSCRIPTION_ID, SUBSCRIPTION_STATUS,SUBSCRIPTION_NAME}, SUBSCRIPTION_STATUS + "=?",
				new String[] { String.valueOf(true) }, null, null, null, null);
        
	    
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			Subscription subs = new Subscription(cursor.getString(0), 
					cursor.getString(1), cursor.getString(2));
			subcription.add(subs);
		}*/
		Subscription sub1= new Subscription("0","HCL Tech","True");
		Subscription sub2= new Subscription("1","IBM","True");
		Subscription sub3= new Subscription("2","MICROSOFT","True");
		Subscription sub4= new Subscription("3","GOOGLE","True");
		Subscription sub5= new Subscription("4","EBAY","True");
		
		subcription.add(sub1);
		subcription.add(sub2);
		subcription.add(sub3);
		subcription.add(sub4);
		subcription.add(sub5);
		
		return subcription;
		
	}
	
	
	
	
//	Get All Pending Subscription for a User
	
	public ArrayList<Subscription> getPendingSubscriptionUser() {
		ArrayList<Subscription> subcription = new ArrayList<Subscription>();
		
		/*SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER_SUBSCRIBE, new String[] { USERID,
				SUBSCRIPTION_ID, SUBSCRIPTION_STATUS,SUBSCRIPTION_NAME}, SUBSCRIPTION_STATUS + "=?",
				new String[] { String.valueOf(false) }, null, null, null, null);
        
	    
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			Subscription subs = new Subscription(cursor.getString(0), 
					cursor.getString(1), cursor.getString(2));
			subcription.add(subs);
		}*/
		Subscription sub1= new Subscription("0","Samsung","True");
		Subscription sub2= new Subscription("1","Apple","True");
		Subscription sub3= new Subscription("2","Yahoo","True");
		Subscription sub4= new Subscription("3","Bloomberg","True");
		Subscription sub5= new Subscription("4","Quora","True");
		
		subcription.add(sub1);
		subcription.add(sub2);
		subcription.add(sub3);
		subcription.add(sub4);
		subcription.add(sub5);
		
		return subcription;
	}
	
	
	
	
	/*public Subscription getSubscriptionDetail(String username, String subscriptionname)
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
	
*/	

	
}
