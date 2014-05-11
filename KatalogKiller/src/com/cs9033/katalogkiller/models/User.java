package com.cs9033.katalogkiller.models;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{

	




	private String user_id;
	private String user_name;
	private String email_id;
	private String password;
	private String phone_number;
	private String Address;
	private List<Subscription> subscriptionlist;
	
	
	
	
	public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
		public User createFromParcel(Parcel p) {
			return new User(p);
		}

		public User[] newArray(int size) {
			return new User[size];
		}
	};
	
	
	
	public User(Parcel p) {
		// TODO Auto-generated constructor stub
		this.user_id = p.readString();
		this.user_name = p.readString();
		this.email_id = p.readString();
		this.password= p.readString();
		this.Address= p.readString();
		List<Subscription> tripMembers = new ArrayList<Subscription>();
		p.readTypedList(tripMembers, Subscription.CREATOR);
		
	}

	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(this.user_id);
		dest.writeString(this.user_name);
		dest.writeString(this.email_id);
		dest.writeString(this.password);
		dest.writeString(this.Address);
		dest.writeString(this.phone_number);
		
	}
	
	
	

	public User(String user_id, String user_name, String email_id,
			String password, String phone_number, String address,
			List<Subscription> subscriptionlist) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.email_id = email_id;
		this.password = password;
		this.phone_number = phone_number;
		Address = address;
		this.subscriptionlist = subscriptionlist;
	}


	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public List<Subscription> getSubscriptionlist() {
		return subscriptionlist;
	}

	public void setSubscriptionlist(List<Subscription> subscriptionlist) {
		this.subscriptionlist = subscriptionlist;
	}

	
	
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name
				+ ", email_id=" + email_id + ", password=" + password
				+ ", phone_number=" + phone_number + ", Address=" + Address
				+ ", subscriptionlist=" + subscriptionlist + "]";
	}

	

}
