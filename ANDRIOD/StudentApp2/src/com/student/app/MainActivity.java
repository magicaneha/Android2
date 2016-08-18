package com.student.app;

import java.util.ArrayList;

import personal.Student;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.firstapp.R;

public class MainActivity extends Activity {

	Button btnList, btnNew;
	ArrayList<Student> slist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);

		slist=new ArrayList<Student>();
		
		btnNew = (Button) findViewById(R.id.btnNew);
		btnList = (Button) findViewById(R.id.btnList);

		btnList.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent in = new Intent(MainActivity.this, Student_list.class);
				in.putExtra("slist", slist);
				startActivity(in);				
			}
		});
		
		btnNew.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent s = new Intent(MainActivity.this, Registration.class);				
				startActivityForResult(s, 101);
				
							
			}
		});

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode== RESULT_OK && requestCode==101)
		{
			Student s = (Student) data.getExtras().get("stud");
			slist.add(s);
		}
	}
}