package com.student.app;

import java.util.ArrayList;

import personal.Student;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class Student_list extends Activity {
	ArrayList<Student> slist;
	ArrayAdapter<Student> adap;
	ListView lv;
TextView tvCount;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.example.firstapp.R.layout.student_list);
		Intent in = getIntent();

		lv = (ListView) findViewById(com.example.firstapp.R.id.listView1);
		tvCount=(TextView) findViewById(com.example.firstapp.R.id.tvCount);
		slist = (ArrayList<Student>) getIntent().getExtras().get("slist");
		
		tvCount.setText(slist.size()+" record(s) found"); 
		
		adap = new ArrayAdapter<Student>(this,
				android.R.layout.simple_list_item_1, slist);
		lv.setAdapter(adap);
		registerForContextMenu(lv);

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);

		getMenuInflater().inflate(com.example.firstapp.R.menu.menuforlist, menu);		
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		/*
		 * ContextMenuInfo info = item.getMenuInfo();
		 * if(info.equals(tvMessage)); { String msg =
		 * tvMessage.getText().toString(); }
		 */

		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		int index = info.position;
		

		switch (item.getItemId()) {
		case com.example.firstapp.R.id.mnuView:
			/*
			Intent in = new Intent(Student_list.this, Student_detail.class);
			String a = slist.get(index).toString();
			in.putExtra("name", a);
			startActivity(in);
			*/		
			break;
		case com.example.firstapp.R.id.mnuEdit:
			
			Student s = slist.get(index);
			Intent in = new Intent(Student_list.this, EditStudent.class);
			in.putExtra("stud", s);
			startActivityForResult(in, index);
			
			
			Toast.makeText(Student_list.this, "Edit on " + slist.get(index), 5)
					.show();
			break;
		case com.example.firstapp.R.id.mnuDelete:
		
			if(index>=0)
			{
			slist.remove(index);
			adap.notifyDataSetChanged();
			
			
			Toast.makeText(Student_list.this, "Deleted",
					5).show();
			}
			break;
		}	

		return super.onContextItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode==RESULT_OK)
		{
			Student s = (Student) data.getExtras().get("newstud");
			slist.set(requestCode, s);
			adap.notifyDataSetChanged();
		}
	}
	
}
