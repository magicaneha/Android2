package com.example.notificationtest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	EditText editMsg;
	Button btnSend;
	int i=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		editMsg = (EditText) findViewById(R.id.editMsg);
		btnSend = (Button) findViewById(R.id.btnSend);
		
		btnSend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String data= editMsg.getText().toString();
				
				Intent in= new Intent(MainActivity.this, SecondActivity.class);
				in.putExtra("data", data);
				PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 101, in, PendingIntent.FLAG_ONE_SHOT);
				
				
				
				Notification noti = new Notification(R.drawable.ic_launcher	, "New notification", System.currentTimeMillis());
				noti.setLatestEventInfo(MainActivity.this, "MY INFO", data, pi);
				
				
			    NotificationManager nfm =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			    nfm.notify(++i, noti);
				
			}
		});
	}
}
