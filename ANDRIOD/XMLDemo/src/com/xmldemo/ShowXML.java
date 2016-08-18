package com.xmldemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.R.xml;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Xml;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShowXML extends ListActivity {
	ListView lv;
	ArrayList<Employee> elist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		lv=getListView();
		elist=new ArrayList<Employee>();
		
		File file= new File(Environment.getExternalStorageDirectory(), "myEmp.xml");
		try {
			FileReader fr=new FileReader(file);
			
			XmlPullParserFactory fac=XmlPullParserFactory.newInstance();
			fac.setNamespaceAware(true);
			
			XmlPullParser pull=fac.newPullParser();
			pull.setInput(fr);
			
			int event = pull.getEventType();
			String name = null;
			int age = 0;
			float sal = 0;
			
			while(event!=XmlPullParser.END_DOCUMENT)
			{
				if(event==XmlPullParser.START_TAG && pull.getName().equals("Employee"))
				{
					while(true)
					{
						event=pull.next();
						if(event==XmlPullParser.END_TAG && pull.getName().equals("Employee"))
							break;
						if(event==XmlPullParser.START_TAG && pull.getName().equals("name"))
						{
							event=pull.next();
							name=pull.getText();
						}
						if(event==XmlPullParser.START_TAG && pull.getName().equals("age"))
						{
							event=pull.next();
							age=Integer.parseInt(pull.getText());
						}
						if(event==XmlPullParser.START_TAG && pull.getName().equals("sal"))
						{
							event=pull.next();
							sal= Float.parseFloat(pull.getText());
						}
					}
					Employee empl=new Employee(name, age, sal);
					elist.add(empl);
				}
				event=pull.next();
			}
			ArrayAdapter<Employee> adap=new ArrayAdapter<Employee>(this, android.R.layout.simple_list_item_1,elist);
			lv.setAdapter(adap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
