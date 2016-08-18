package com.example.studentlistapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class EditStudent extends Activity {
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
		
		Intent in = getIntent();
		s = (Student) in.getSerializableExtra("stud");
		editRno.setText(s.getRno()+"");
		editName.setText(s.getName());
		editFees.setText(s.getFees()+"");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		menu.add("Update").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				int rno = Integer.parseInt(editRno.getText().toString());
				String name = editName.getText().toString();
				float fees = Float.parseFloat(editFees.getText().toString());
				
				s.setRno(rno);
				s.setName(name);
				s.setFees(fees);
				
				Toast.makeText(EditStudent.this, "Record updated", 5).show();
				
				return false;
			}
		});
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public void finish() {
	
		Intent in = new Intent();
		in.putExtra("stud", s);
		setResult(RESULT_OK, in);
				
		super.finish();
	}
}
