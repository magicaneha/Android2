package com.example.studentapplication;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class MyContentProvider extends ContentProvider {

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		Log.e("delete", "trying");
		MyDBHelper helper = new MyDBHelper(getContext(), "STUDENTDB", null, 1);
		SQLiteDatabase db=helper.getWritableDatabase();
		
		db.delete("student", selection, selectionArgs);
								
		db.close();
		Log.e("delete", "OK");
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		Log.e("insert", "trying");
		MyDBHelper helper = new MyDBHelper(getContext(), "STUDENTDB", null, 1);
		SQLiteDatabase db=helper.getWritableDatabase();
		
		db.insert("student", null, values);
								
		db.close();
		Log.e("insert", "OK");
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
