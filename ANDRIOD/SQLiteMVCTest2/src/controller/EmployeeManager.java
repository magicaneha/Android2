package controller;

import java.util.ArrayList;

import android.content.ContentValues;
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
	
	
	public boolean regEmployee(Employee emp) {
			
		db= helper.getWritableDatabase();
		ContentValues  cv = new ContentValues();
		cv.put("eno", emp.getEno());
		cv.put("ename", emp.getEname());
		cv.put("basic", emp.getBasic());
		cv.put("email", emp.getEmail());
		
		long res = db.insert(MyHelper.TBL_EMPLOYEE, null, cv);
		if(res==-1)
			return false;
		else
			return true;
	}
	
	public ArrayList<Employee> getAllEmployees() {
		ArrayList<Employee> elist = new ArrayList<Employee>();
		
		db = helper.getWritableDatabase();
		
		//Cursor cr = db.rawQuery("select * from "+MyHelper.TBL_EMPLOYEE, null);
		//Cursor cr= db.rawQuery("select * from "+MyHelper.TBL_EMPLOYEE+" where basic>=? and ename=?", new String[]{"8000","Yash"});
		
		Cursor cr = db.query(MyHelper.TBL_EMPLOYEE, null, null, null, null, null, null);
		//Cursor cr = db.query(MyHelper.TBL_EMPLOYEE, null, null, null, null, null, "ename desc");
		//Cursor cr = db.query(MyHelper.TBL_EMPLOYEE, null, "basic>?", new String[]{"10000"}, null, null, "ename desc");
		
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
	
	public boolean deleteEmployee(Employee emp) {
		 
		db = helper.getWritableDatabase();
		int res = db.delete(MyHelper.TBL_EMPLOYEE, "eno=?", new String[]{emp.getEno()+""});
		if(res>0)
			return true;
		else
			return false;
	}
	public boolean updateEmployee(Employee emp) {
		 
		db = helper.getWritableDatabase();
		ContentValues  cv = new ContentValues();
		cv.put("ename", emp.getEname());
		cv.put("basic", emp.getBasic());
		cv.put("email", emp.getEmail());
		
		
		
		int res = db.update(MyHelper.TBL_EMPLOYEE, cv, "eno=?", new String[]{emp.getEno()+""});
		if(res>0)
			return true;
		else
			return false;
	}
}
