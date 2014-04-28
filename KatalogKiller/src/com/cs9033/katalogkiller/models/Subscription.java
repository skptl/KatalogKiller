package com.cs9033.katalogkiller.models;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;


public class Subscription implements Parcelable{
	
	private String subscription_id;
	private String subscription_name;
	private String subscription_status;
	
	
	public static final Parcelable.Creator<Subscription> CREATOR = new Parcelable.Creator<Subscription>() {
		public Subscription createFromParcel(Parcel p) {
			return new Subscription(p);
		}

		public Subscription[] newArray(int size) {
			return new Subscription[size];
		}
	};
	
	
	public Subscription(Parcel p) {

		List<String> subsData = new ArrayList<String>();

		p.readStringList(subsData);
		this.subscription_id = subsData.get(0);
		this.subscription_name = subsData.get(1);
		this.subscription_status = subsData.get(2);

	}

	public Subscription(String subs_id, String subs_name, String subs_status) {
		this.subscription_name = subs_id;
		this.subscription_name = subs_name;
		this.subscription_status= subs_status;
	}
	
	
	
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		List<String> subsData = new ArrayList<String>();
		subsData.add(0, this.subscription_id);
		subsData.add(1, this.subscription_name);
		subsData.add(1, this.subscription_status);
		arg0.writeStringList(subsData);
		
	}
	


	public String getSubscription_id() {
		return subscription_id;
	}

	public void setSubscription_id(String subscription_id) {
		this.subscription_id = subscription_id;
	}

	public String getSubscription_name() {
		return subscription_name;
	}

	public void setSubscription_name(String subscription_name) {
		this.subscription_name = subscription_name;
	}

	public String getSubscription_status() {
		return subscription_status;
	}

	public void setSubscription_status(String subscription_status) {
		this.subscription_status = subscription_status;
	}

	

}
