package com.example.sqlitemvctest;

import controller.EmployeeManager;
import model.Employee;
import model.MyHelper;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	EditText editEno,editEname,editBasic,editEmail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		editEno=(EditText) findViewById(R.id.editEno);
		editEname=(EditText) findViewById(R.id.editEname);
		editBasic=(EditText) findViewById(R.id.editBasic);
		editEmail=(EditText) findViewById(R.id.editEmail);
		
		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	
		menu.add("Register").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				
				int eno =Integer.parseInt(editEno.getText().toString());
				String ename = editEname.getText().toString();
				float basic = Float.parseFloat(editBasic.getText().toString());
				String email = editEmail.getText().toString();
				
				Employee emp = new Employee(eno, ename, email, basic);
				
				EmployeeManager em = new EmployeeManager(MainActivity.this);
				em.regEmployee(emp);
				
				
				return false;
			}
		});
		
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		
		setResult(RESULT_OK);
		
		super.finish();
	}
}
