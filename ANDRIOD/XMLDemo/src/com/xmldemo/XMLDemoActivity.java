package com.xmldemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlSerializer;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class XMLDemoActivity extends Activity {
	EditText e1,e2,e3;
	Button b1,b2;
	ArrayList<Employee> elist;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        e1=(EditText) findViewById(R.id.editText1);
        e2=(EditText) findViewById(R.id.editText2);
        e3=(EditText) findViewById(R.id.editText3);
        b1=(Button) findViewById(R.id.button1);
        b2=(Button) findViewById(R.id.button2);
        elist=new ArrayList<Employee>();
        
        b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent in =new Intent(XMLDemoActivity.this, ShowXML.class);
				startActivity(in);
			}
		});
        b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String name=e1.getText().toString();
				int age=Integer.parseInt(e2.getText().toString());
				float sal=Float.parseFloat(e3.getText().toString());
				
				Employee emm=new Employee(name, age, sal);
				
				elist.add(emm);
				
				File file= new File(Environment.getExternalStorageDirectory(), "myEmp.xml");
				
				try {
					FileOutputStream fos=new FileOutputStream(file);
					
					XmlSerializer ser=Xml.newSerializer();
					ser.setOutput(fos, "UTF-8");
					ser.startDocument(null, true);
					ser.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
					ser.startTag(null, "Employees");
					for(int i=0;i<elist.size();i++)
					{
						Employee emp = elist.get(i);
						ser.startTag(null, "Employee");
						
						ser.startTag(null, "name");
						ser.text(emp.getName());
						ser.endTag(null, "name");
						
						ser.startTag(null, "age");
						ser.text(emp.getAge()+"");
						ser.endTag(null, "age");
						
						ser.startTag(null, "sal");
						ser.text(emp.getSal()+"");
						ser.endTag(null, "sal");
						
						ser.endTag(null, "Employee");
					}
					ser.endTag(null, "Employees");
					ser.endDocument();
					ser.flush();
					fos.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
        });
    }
}