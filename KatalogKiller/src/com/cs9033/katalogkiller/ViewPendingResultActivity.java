package com.cs9033.katalogkiller;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cs9033.katalogkiller.models.Subscription;
import com.cs9033.katalogkiller.utilities.DBHandler;




public class ViewPendingResultActivity extends Activity{

	 private ListView listViewMembers;

	 	private ArrayList<String> arraylistsubscription;
	 	private ArrayList<Subscription> arraylistsubscriptionRaw;
	 	private ArrayAdapter<String> adapter;
	 	
	 	private DBHandler KatalogDB;
	 	
	 	private Activity activity = this;
	 	
	 	 public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.viewpendingrequest);
		        KatalogDB = new DBHandler(this);
				bindComponents();

	}
		 
		 	/**
				 * Method to bind all components to the controller.
				 */
				private void bindComponents() {

					arraylistsubscriptionRaw = KatalogDB.getPendingSubscriptionUser();
					
					arraylistsubscription = new ArrayList<String>();
					
					for (Subscription subs  : arraylistsubscriptionRaw) {
						arraylistsubscription.add(subs.getSubscription_name());
					}

					listViewMembers = (ListView) findViewById(R.id.viewpendingrequest);
					

					adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arraylistsubscription);
					listViewMembers.setAdapter(adapter);

				}
	}
