package com.example.sqlitemvctest2;

import java.util.ArrayList;

import model.Employee;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Toast;
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
		
		registerForContextMenu(lv);
		
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
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		
		MenuInflater minf = getMenuInflater();
		minf.inflate(R.menu.mymenu, menu);
		
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		int index = info.position;
		
		Employee emp = elist.get(index);
		
		switch(item.getItemId())
		{
			case R.id.mnuDelete:
				EmployeeManager em = new EmployeeManager(EmployeeList.this);
				if(em.deleteEmployee(emp))
				{
					Toast.makeText(EmployeeList.this, "Employee Deleted", 5).show();
					updateList();
				}
				else
					Toast.makeText(EmployeeList.this, "try later....", 5).show();
				break;
			case R.id.mnuView:
				break;
			case R.id.mnuEdit:
				
				doEdit(emp);
				
				break;
		}
		
		
		return super.onContextItemSelected(item);
	}
	
	private void doEdit(Employee emp)
	{
		LayoutInflater linf = getLayoutInflater();
		View v = linf.inflate(R.layout.activity_main, null);
		final EditText editEno =  (EditText) v.findViewById(R.id.editEno);
		final EditText editEname =  (EditText) v.findViewById(R.id.editEname);
		final EditText editBasic =  (EditText) v.findViewById(R.id.editBasic);
		final EditText editEmail =  (EditText) v.findViewById(R.id.editEmail);
		
		editEno.setText(emp.getEno()+"");
		editEname.setText(emp.getEname());
		editBasic.setText(emp.getBasic()+"");
		editEmail.setText(emp.getEmail());
		
		
		
		
		
		AlertDialog.Builder build = new AlertDialog.Builder(EmployeeList.this);
		build.setTitle("Edit Employee");
		build.setIcon(R.drawable.ic_launcher);
		build.setView(v);
		build.setPositiveButton("Update", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				int eno =Integer.parseInt(editEno.getText().toString());
				String ename = editEname.getText().toString();
				float basic = Float.parseFloat(editBasic.getText().toString());
				String email = editEmail.getText().toString();
				
				Employee e = new Employee(eno, ename, email, basic);
				
				EmployeeManager em = new EmployeeManager(EmployeeList.this);
				if(em.updateEmployee(e))
				{
					Toast.makeText(EmployeeList.this, "Employee Updated", 5).show();
					updateList();
				}
				else
				{
					Toast.makeText(EmployeeList.this, "try later", 5).show();
				}
			}
		});
		build.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		
		build.show();
	}
}
