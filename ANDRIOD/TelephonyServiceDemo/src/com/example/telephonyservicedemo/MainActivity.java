package com.example.telephonyservicedemo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.android.internal.telephony.ITelephony;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity  {
	int callstate=0;
	String number="";
	TelephonyManager tm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btnStart = (Button) findViewById(R.id.btnStart);
		
		btnStart.setOnClickListener(new  OnClickListener() {
			@Override
			public void onClick(View v) {
				 tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
				tm.listen(new MyPhoneListener(), PhoneStateListener.LISTEN_CALL_STATE);
			}
		});
	}
	class MyPhoneListener extends PhoneStateListener
	{
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			// TODO Auto-generated method stub
			super.onCallStateChanged(state, incomingNumber);
			callstate = state;
			number=incomingNumber;
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:
				Toast.makeText(MainActivity.this, "IDLE", 5).show();
				break;
			case TelephonyManager.CALL_STATE_RINGING:
				new MyThread().start();
				
				Toast.makeText(MainActivity.this, "RINGING", 5).show();
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				
				Toast.makeText(MainActivity.this, "OFFHOOK", 5).show();
				break;
			default:
				break;
			}
		}
	}
	class MyThread extends Thread
	{
		@Override
		public void run() {
			super.run();
			try {
				Thread.sleep(5000);
				
				if(callstate==TelephonyManager.CALL_STATE_RINGING)
				{
				//	disconnect
					 Class cls = Class.forName(tm.getClass().getName());
					 Method met = cls.getDeclaredMethod("getITelephony");
					 met.setAccessible(true);
					 ITelephony itel = (ITelephony) met.invoke(tm);
					 itel.endCall();
					 
					 
					 SmsManager sm = SmsManager.getDefault();
					 
					 sm.sendTextMessage(number, "5554", "I am busy now, call you later", null, null);
					 
				}
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
