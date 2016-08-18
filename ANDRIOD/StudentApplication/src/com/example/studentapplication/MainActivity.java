package com.example.studentapplication;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ArrayAdapter<String> adap;
	ArrayList<String> slist;
	ListView lv;
	MyDBHelper helper;
	SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lv=(ListView) findViewById(R.id.lv);
		slist=new ArrayList<String>();
		adap=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,slist);		
		fillList();
	}

	void fillList()
	{
		adap.clear();
		helper = new MyDBHelper(this, "STUDENTDB", null, 1);
		db = helper.getWritableDatabase();
		Cursor cr = db.query("student", null, null, null, null, null, null);
		
		while(cr.moveToNext())
		{
			int id=cr.getInt(0);
			String sname = cr.getString(1);
			float fees = cr.getFloat(2);
			
			String info = id+":"+sname+":"+fees;
			adap.add(info);
		}
		db.close();
		lv.setAdapter(adap);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("New Student").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
				build.setTitle("Registration");
				build.setIcon(R.drawable.ic_launcher);
				
				LayoutInflater linf = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
				final View v = linf.inflate(R.layout.newstud, null);
							
				build.setView(v);
				build.setPositiveButton("Save", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						EditText editName = (EditText) v.findViewById(R.id.editName);
						EditText editFees = (EditText) v.findViewById(R.id.editFees);
						
						String name = editName.getText().toString();
						float fees = Float.parseFloat(editFees.getText().toString());
						
						db=helper.getWritableDatabase();
							
						ContentValues cv = new ContentValues();
						cv.put("sname", name);
						cv.put("fees", fees);
						db.insert("student", null, cv);
												
						db.close();
						Toast.makeText(MainActivity.this, "Student Inserted", 5).show();
						fillList();
					}
				});
				build.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});
				
				build.show();
				
				return false;
			}
		});
		return true;
	}

}
