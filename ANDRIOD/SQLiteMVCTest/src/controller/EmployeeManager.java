package controller;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import model.Employee;
import model.MyHelper;

public class EmployeeManager {

	MyHelper helper;
	SQLiteDatabase db;
	
	public EmployeeManager(Context context)
	{
		helper = new MyHelper(context, MyHelper.DBNAME, null, MyHelper.VERSION);
	}
	
	
	public void regEmployee(Employee emp) {
		int eno = emp.getEno();
		String ename = emp.getEname();
		float basic = emp.getBasic();
		String email = emp.getEmail();
		
		
		db= helper.getWritableDatabase();
		db.execSQL("insert into "+MyHelper.TBL_EMPLOYEE + " values ("+eno+",'"+ename+"',"+basic+",'"+email+"')");
		
	}
	
	public ArrayList<Employee> getAllEmployees() {
		ArrayList<Employee> elist = new ArrayList<Employee>();
		
		db = helper.getWritableDatabase();
		
		//Cursor cr = db.rawQuery("select * from "+MyHelper.TBL_EMPLOYEE, null);
		Cursor cr= db.rawQuery("select * from "+MyHelper.TBL_EMPLOYEE+" where basic>=? and ename=?", new String[]{"8000","Yash"});
		while(cr.moveToNext())
		{
			int eno = cr.getInt(0);
			String ename = cr.getString(1);
			float basic  =cr.getFloat(2);
			String email = cr.getString(3);
			
			Employee emp = new Employee(eno, ename, email, basic);
			
			elist.add(emp);
		}
		return elist;
	}
	
}
