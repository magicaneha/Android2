package com.example.testcontentprovider;



import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Delete").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Uri myUri = Uri.parse("content://studenturi");
								
				ContentResolver res = getContentResolver();
				res.delete(myUri, "_id=?", new String[]{"2"});
				
				
				Toast.makeText(MainActivity.this, "Student Delete data sent", 5).show();
				return false;
			}
		});
		
		menu.add("New Student").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
				build.setTitle("Registration");
				build.setIcon(R.drawable.ic_launcher);
				
				LayoutInflater linf = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
				final View v = linf.inflate(R.layout.newstud, null);
							
				build.setView(v);
				build.setPositiveButton("Save", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						EditText editName = (EditText) v.findViewById(R.id.editName);
						EditText editFees = (EditText) v.findViewById(R.id.editFees);
						
						String name = editName.getText().toString();
						float fees = Float.parseFloat(editFees.getText().toString());
						
						ContentValues cv = new ContentValues();
						cv.put("sname", name);
						cv.put("fees", fees);
						
						Uri myUri = Uri.parse("content://studenturi");
						
						
						ContentResolver res = getContentResolver();
						res.insert(myUri, cv);
						
						
						Toast.makeText(MainActivity.this, "Student data sent", 5).show();
						
					}
				});
				build.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});
				
				build.show();
				
				return false;
			}
		});
		return true;
	}

}
