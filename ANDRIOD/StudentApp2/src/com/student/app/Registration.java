package com.student.app;

import personal.Student;

import com.example.firstapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends Activity {
EditText editRno,editName,editFees;
Student s;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);

		editRno=(EditText) findViewById(R.id.etRoll);
		editName=(EditText) findViewById(R.id.etName);
		editFees=(EditText) findViewById(R.id.etFees);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.regoptmenu, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		switch (id) {
		case R.id.mnuSave:
			int rno = Integer.parseInt(editRno.getText().toString());
			String name = editName.getText().toString();
			float fees = Float.parseFloat(editFees.getText().toString());
			
			 s = new Student(rno, name, fees);
			
			 Toast.makeText(Registration.this, "Student Saved", 5).show();
			 
			 //code for returning student object
			 Intent in = new Intent();
			 in.putExtra("stud", s);
			 setResult(RESULT_OK, in);
			 finish();
			 
			
			break;
		case R.id.mnuCancel:
			Toast.makeText(Registration.this, item.getTitle(), 5).show();
			
			setResult(RESULT_CANCELED);
			finish();
			break;
		}

		return super.onOptionsItemSelected(item);
	}

}