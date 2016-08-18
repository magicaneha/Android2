package com.example.studentapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDBHelper extends SQLiteOpenHelper {

	Context context;
	public MyDBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		this.context=context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("Create table student(_id INTEGER PRIMARY KEY AUTOINCREMENT,sname TEXT, fees REAL)");
		db.execSQL("insert into student(sname,fees) values('Amit',9000)");
		db.execSQL("insert into student(sname,fees) values('Anoop',7800)");
		
		Toast.makeText(context, "Database created", 5).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Toast.makeText(context, "Database changed", 5).show();
		
	}
	
}
