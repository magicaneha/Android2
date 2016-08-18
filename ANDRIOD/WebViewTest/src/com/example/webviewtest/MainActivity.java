package com.example.webviewtest;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {
	WebView wv;
	Button btnGo;
	AutoCompleteTextView editURL;
	ProgressBar pbLoading;
	
	private  void bindAutoCompleteTextView()
	{
		ArrayList<String> slist=new ArrayList<String>();
		slist.add("http://google.com");
		slist.add("http://gmail.com");
		slist.add("http://yahoo.co.in");
		slist.add("http://irctc.co.in");
		slist.add("http://indianrail.gov.in");
		slist.add("http://maps.google.com");
		ArrayAdapter<String> adap = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,slist);
		editURL.setAdapter(adap);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pbLoading=(ProgressBar) findViewById(R.id.pbLoading);
		wv=(WebView) findViewById(R.id.wv);
		btnGo=(Button) findViewById(R.id.btnGo);
		editURL=(AutoCompleteTextView) findViewById(R.id.editURL);
		
		bindAutoCompleteTextView();
		
		
		wv.setWebViewClient(new MyNativeClient());
		
		
		btnGo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String url = editURL.getText().toString();
				
				wv.loadUrl(url);
				Toast.makeText(MainActivity.this, "Webpage is loading...", 5).show();
			}
		});
	}

	class MyNativeClient extends WebViewClient
	{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			
			return super.shouldOverrideUrlLoading(view, url);
		}
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			// TODO Auto-generated method stub
			super.onPageStarted(view, url, favicon);
			pbLoading.setVisibility(ProgressBar.VISIBLE);
		}
		
		@Override
		public void onPageFinished(WebView view, String url) {
			// TODO Auto-generated method stub
			super.onPageFinished(view, url);
			pbLoading.setVisibility(ProgressBar.INVISIBLE);
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
		if(keyCode==KeyEvent.KEYCODE_BACK)
		{
			if(wv.canGoBack())
			{
				wv.goBack();
				return false;
			}
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		getMenuInflater().inflate(R.menu.main, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	
}
