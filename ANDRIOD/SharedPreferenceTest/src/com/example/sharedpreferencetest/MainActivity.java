package com.example.sharedpreferencetest;


import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText editUname,editPass;
	Button btnSignIn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		editUname = (EditText) findViewById(R.id.editUname);
		editPass =  (EditText) findViewById(R.id.editPass);
		btnSignIn = (Button) findViewById(R.id.btnSignIn);
		
		btnSignIn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//String uname = editUname.getText().toString();
				//String pass = editPass.getText().toString();
				
				SharedPreferences sp= getSharedPreferences("credential", MODE_PRIVATE);
				String u=sp.getString("username", "");
				String p=sp.getString("password", "");
				
				editUname.setText(u);
				editPass.setText(p);
				//SharedPreferences.Editor edit = sp.edit();
				//edit.putString("username", uname);
				//edit.putString("password", pass);
				//edit.commit();
				Toast.makeText(MainActivity.this, "SP created", 5).show();
			}
		});
	}
}
