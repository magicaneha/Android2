package com.example.staticfrag2demo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Frag2 extends Fragment
{
	EditText et1,et2;
	
   @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
     View vw = inflater.inflate(R.layout.frag2, null);
     
     et1 = (EditText) vw.findViewById(R.id.f2eT1);
     et2 = (EditText) vw.findViewById(R.id.f2eT2);
     Button bt = (Button) vw.findViewById(R.id.f2addBT);
     
     bt.setOnClickListener(new OnClickListener()
     {		
		@Override
		public void onClick(View v) 
		{
		   int x = Integer.parseInt(et1.getText().toString());
		   int y = Integer.parseInt(et2.getText().toString());
		   
		   int z = x + y;
		   
		   Toast.makeText(getActivity(), "Add  - " + z, Toast.LENGTH_LONG).show();
		}
	});
     
     
 	return vw;
}
}
