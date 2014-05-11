package com.cs9033.katalogkiller;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs9033.katalogkiller.models.Subscription;

public class ItemListBaseAdapter extends BaseAdapter{
	private static ArrayList<Subscription> itemDetailsrrayList;

	private Integer[] imgid = {
			R.drawable.btn_black,
			R.drawable.camera_icon,
			R.drawable.googleplus,
			R.drawable.facebook,
			R.drawable.googleplus,
			R.drawable.ic_launcher,
			R.drawable.splash,
			R.drawable.twitter,
			R.drawable.fbsignin,
			R.drawable.btn_black,
	};
	private LayoutInflater l_Inflater;
	public ItemListBaseAdapter(Context context, ArrayList<Subscription> results) {
		
		  itemDetailsrrayList = results;
		
		  l_Inflater = LayoutInflater.from(context);
		
		 }
	public int getCount() {
		
		  return itemDetailsrrayList.size();
		
		 }
		
		 
		
		 public Object getItem(int position) {
		
		  return itemDetailsrrayList.get(position);
		
		 }
		
		 
		
		 public long getItemId(int position) {
		
		  return position;
		
		 }
		
		 
		
		 public View getView(int position, View convertView, ViewGroup parent) {
		
		  ViewHolder holder;
		
		  if (convertView == null) {
		
		   convertView = l_Inflater.inflate(R.layout.item_details_view, null);
		
		   holder = new ViewHolder();
		
		   holder.Name = (TextView) convertView.findViewById(R.id.name);
		
		   holder.Status = (TextView) convertView.findViewById(R.id.status);
		
		   holder.itemImage = (ImageView) convertView.findViewById(R.id.photo);
		
		 
		
		   convertView.setTag(holder);
		
		  } else {
		
		   holder = (ViewHolder) convertView.getTag();
		
		  }
	
		   
		
		  holder.Name.setText(itemDetailsrrayList.get(position).getSubscription_name());
		
		  holder.Status.setText(itemDetailsrrayList.get(position).getSubscription_status());
		  
		 
		  holder.itemImage.setImageResource(imgid[position]);	
		//  holder.itemImage.setImageResource(imgid[Integer.parseInt(itemDetailsrrayList.get(position).getSubscription_id())]);		
		  return convertView;
		
		 }
		
		 
		
		 static class ViewHolder {
		
		  TextView Name;

		  TextView Status;
		
		  ImageView itemImage;
		
		 }



	


	

}
