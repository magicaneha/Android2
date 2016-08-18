package com.example.sqlitemvc;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button btnTest;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnTest=(Button) findViewById(R.id.btnTest);
		btnTest.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MyHelper helper = new MyHelper(MainActivity.this, MyHelper.DBNAME, null, MyHelper.VERSION);
				SQLiteDatabase db = helper.getWritableDatabase();
			}
		});
	}


}
