package com.example.studentlistapp;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	int index=0;
	ListView lv;
	ArrayList<Student> slist;
	ArrayAdapter<Student> adap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lv=(ListView) findViewById(R.id.lv);
		
		slist=new ArrayList<Student>();
		slist.add(new Student(101, "Jiya", 6541230));
		slist.add(new Student(102, "Jai", 123456));
		slist.add(new Student(103, "Piyush", 100200));
		slist.add(new Student(104, "Ramesh", 300200));
		slist.add(new Student(105, "Suresh", 741258));
		
		adap = new ArrayAdapter<Student>(MainActivity.this, android.R.layout.simple_list_item_1,slist);
		
		lv.setAdapter(adap);
		
		registerForContextMenu(lv);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
				Student s = slist.get(pos);
				String info = s.getRno()+":"+s.getName()+":"+s.getFees();
				Toast.makeText(MainActivity.this, info, 5).show();
				
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add("New Student").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent in = new Intent(MainActivity.this, NewStudent.class);
				startActivityForResult(in, 101);
				
				return false;
			}
		});
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode==RESULT_OK && requestCode==101)
		{
			Student s = (Student) data.getSerializableExtra("stud");
			slist.add(s);
			adap.notifyDataSetChanged();
		}
		else if(resultCode==RESULT_OK && requestCode==102){
			Student s = (Student) data.getSerializableExtra("stud");
			slist.set(index, s);
			adap.notifyDataSetChanged();
		}
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		
		menu.add("Edit").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
								
				AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
				index = info.position;
				
				Student s = slist.get(index);
				Intent in = new Intent(MainActivity.this,EditStudent.class);
				in.putExtra("stud", s);
				startActivityForResult(in, 102);
				
				return false;
			}
		});
	}
}
