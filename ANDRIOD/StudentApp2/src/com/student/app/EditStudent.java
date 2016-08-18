package com.student.app;


import personal.Student;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.EditText;

import com.example.firstapp.R;

public class EditStudent extends Activity {
	EditText editRno,editName,editFees;
	Student s;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(com.example.firstapp.R.layout.registration);
		
		 s =  (Student) getIntent().getExtras().get("stud");
		
		editRno=(EditText) findViewById(R.id.etRoll);
		editName=(EditText) findViewById(R.id.etName);
		editFees=(EditText) findViewById(R.id.etFees);
		
		editRno.setText(s.getRno()+"");
		editName.setText(s.getName());
		editFees.setText(s.getFees()+"");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		menu.add("Update").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				
				s.setRno(Integer.parseInt(editRno.getText().toString()));
				s.setName(editName.getText().toString());
				s.setFees(Float.parseFloat(editFees.getText().toString()));
				
				Intent in = new Intent();
				in.putExtra("newstud", s);
				setResult(RESULT_OK, in);
				finish();
				
				return false;
			}
		});
		
		return super.onCreateOptionsMenu(menu);
	}

}
