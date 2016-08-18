package com.example.studentlistapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class NewStudent extends Activity {
	EditText editRno,editName,editFees;
	Student s;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newstud);
		editRno = (EditText) findViewById(R.id.editRno);
		editName = (EditText) findViewById(R.id.editName);
		editFees = (EditText) findViewById(R.id.editFees);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add("Save").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				int rno = Integer.parseInt(editRno.getText().toString());
				String name = editName.getText().toString();
				float fees = Float.parseFloat(editFees.getText().toString());
				
				s = new Student(rno, name, fees);
				
				Toast.makeText(NewStudent.this, "Saved", 5).show();
				return false;
			}
		});
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		Intent in = new Intent();
		in.putExtra("stud", s);
		setResult(RESULT_OK, in);
		
			
		super.finish();
	}
}
