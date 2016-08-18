package com.example.notificationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secview);
		
		Intent in =	getIntent();
		String str = in.getStringExtra("data");
		
		TextView tvData = (TextView) findViewById(R.id.tvData);
		tvData.setText(str);
	}
}
