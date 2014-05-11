package com.cs9033.katalogkiller;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.cs9033.katalogkiller.models.Subscription;
import com.cs9033.katalogkiller.utilities.DBHandler;

public class ViewAllResultActivity extends Activity{
	 private ListView listViewMembers;

 	private ArrayList<String> arraylistsubscription;
 	private ArrayList<Subscription> arraylistsubscriptionRaw;
 	private ArrayAdapter<String> adapter;
 	
 	private DBHandler KatalogDB;
 	
 	private Activity activity = this;

	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.viewallresult);
	        KatalogDB = new DBHandler(this);
			bindComponents();
	        
	       
	    }
		/**
		 * Method to bind all components to the controller.
		 */
		private void bindComponents() {

			arraylistsubscriptionRaw = KatalogDB.getAllSubscriptionUser();
			
			/*arraylistsubscription = new ArrayList<String>();
			
			for (Subscription subs  : arraylistsubscriptionRaw) {
				arraylistsubscription.add(subs.getSubscription_name());
			}*/
			
			
			
			

			listViewMembers = (ListView) findViewById(R.id.viewallrequest);
			listViewMembers.setAdapter(new ItemListBaseAdapter(this, arraylistsubscriptionRaw));
			listViewMembers.setOnItemClickListener(new OnItemClickListener() {
			
				public void onItemClick(AdapterView<?> a, View v, int position, long id) { 
	        		Object o = listViewMembers.getItemAtPosition(position);
	        		Subscription obj_itemDetails = (Subscription)o;
	        		Toast.makeText(ViewAllResultActivity.this, "You have chosen : " + " " + obj_itemDetails.getSubscription_name(), Toast.LENGTH_LONG).show();
	        	}  
			});
			
			
			
			
			//adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arraylistsubscription);
		//	listViewMembers.setAdapter(adapter);

		}
		
}
