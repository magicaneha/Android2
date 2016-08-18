package com.student.app;

import com.example.firstapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Student_detail extends Activity {
	TextView tvDetail;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
	setContentView(R.layout.student_detail);
	tvDetail = (TextView) findViewById(R.id.tvDetail);
	Intent d = getIntent();
	String a = d.getStringExtra("name");
	tvDetail.setText(a);
	}

}
