package model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyHelper extends SQLiteOpenHelper {

	public static final String DBNAME = "EmpDB";
	public static final int VERSION = 2;
	public static final String TBL_EMPLOYEE="Employee";
	Context ctx;
	public MyHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		ctx=context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table Employee(eno Integer, ename Text, basic Real)");
		db.execSQL("insert into Employee values(101,'Raj',8900) ");
		
		Toast.makeText(ctx, "On Create", 5).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("alter table Employee add email Text");
		Toast.makeText(ctx, "On Upgrade", 5).show();
	}

}
