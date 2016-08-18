package com.example.staticfrag2demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Frag1 extends Fragment 
{
	TextView tv;
	Context ctx;
	
   @Override
   public void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
  }
   
   @Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		ctx = activity;
	}
   
   @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
   {
	     View vw = inflater.inflate(R.layout.frag1,null);
	     
	     tv = (TextView) vw.findViewById(R.id.f1tv);
	     Button bt = (Button) vw.findViewById(R.id.f1showbt);
	     
	     
	     bt.setOnClickListener(new OnClickListener() 
	     {
			@Override
			public void onClick(View v) 
			{
			   tv.setText("Indore");				
			}
		});
	     
		return vw;
	}
}
