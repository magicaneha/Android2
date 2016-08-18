package com.example.sqlitemvctest;

import java.util.ArrayList;

import model.Employee;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import controller.EmployeeManager;

public class EmployeeList extends ListActivity {
	ListView lv;
	ArrayList<Employee> elist;
	ArrayAdapter<Employee> adap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		lv=getListView();
		
		
		updateList();
		
	}
	
	private void updateList()
	{
		EmployeeManager em = new EmployeeManager(EmployeeList.this);
		elist = em.getAllEmployees();
		
		adap=new ArrayAdapter<Employee>(EmployeeList.this, android.R.layout.simple_list_item_1,elist);
		
		lv.setAdapter(adap);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		menu.add("Add Employee").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				
				Intent in= new Intent(EmployeeList.this, MainActivity.class);
				startActivityForResult(in,101);
				
				
				return false;
			}
		});
		
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode==101 && resultCode==RESULT_OK)
		{
			Log.e("rec", "data received");
			updateList();
		}
		
	}
	
}
