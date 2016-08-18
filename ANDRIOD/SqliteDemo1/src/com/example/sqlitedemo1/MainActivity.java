package com.example.sqlitedemo1;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button btnCreateDB,btnCreateTable,btnInsert;
	SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnCreateDB=(Button) findViewById(R.id.btnCreateDB);
		btnCreateTable=(Button) findViewById(R.id.btnCreateTable);
		btnInsert=(Button) findViewById(R.id.btnInsert);
		
		btnInsert.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				db.execSQL("insert into Employee values(101,'Kalpit',15000)");
				Toast.makeText(MainActivity.this, "Employee Added", 5).show();
			}
		});
		
		btnCreateDB.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				db = openOrCreateDatabase("MyDB", MODE_PRIVATE, null);
				Toast.makeText(MainActivity.this, "Database open", 5).show();
//				db.close();
			}
		});
		
		btnCreateTable.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				 db = openOrCreateDatabase("MyDB", MODE_PRIVATE, null);
				db.execSQL("create table Employee (eno Integer, ename Text, basic Real)");
				Toast.makeText(MainActivity.this, "Table is ready now", 5).show();
//				db.close();
			}
		});
	}
}
