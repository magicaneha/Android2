package com.example.paypaldemo;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.json.JSONException;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {
	private static PayPalConfiguration config;
	
	GridView gv;
	ArrayList<String> plist;
	ArrayAdapter<String> adap;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        gv=(GridView) findViewById(R.id.gridView1);
        
        plist=new ArrayList<String>();
        plist.add("Mouse - $100");
        plist.add("Keyboard - $1200");
        plist.add("Monitor - $500");
        plist.add("Speaker - $700");
        plist.add("Pen Drive - $800");
        
        adap=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,plist);
        gv.setOnItemClickListener(this);
        gv.setAdapter(adap);
        
        // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
        // or live (ENVIRONMENT_PRODUCTION)
        
        config = new PayPalConfiguration()
        		.environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
        		.clientId("ARglhxDZJjz1JBybNTL5dTezVJ6H_HxqUndq14JDdAQCQ9NhXqwyTMWkHBFn");
        
        Intent intent = new Intent(this,PayPalService.class);
      	intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

      	startService(intent);


        
    }
    
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    	stopService(new Intent(this, PayPalService.class));
    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		float price=0;
		int position = arg2;
		
		switch (position) {
		case 0:
			price=100;
			break;
		case 1:
			price=1200;
			break;
		case 2:
			price=500;
			break;
		case 3:
			price=700;
			break;
		case 4:
			price=800;
			break;
		}
		
		Toast.makeText(MainActivity.this, price+"", 5).show();
		
		PayPalPayment payment = new PayPalPayment(new BigDecimal(price), "USD", "Mouse",PayPalPayment.PAYMENT_INTENT_SALE);

		Intent intent = new Intent(this, PaymentActivity.class);

		intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

		startActivityForResult(intent, 0);
	}
   
	@Override
	protected void onActivityResult (int requestCode, int resultCode, Intent data) {
	    if (resultCode == Activity.RESULT_OK) {
	        PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
	        if (confirm != null) {
	            try {
	                Log.i("paymentExample", confirm.toJSONObject().toString(4));
	                // TODO: send 'confirm' to your server for verification.
	                // see 	https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
	                // for more details.

	            } catch (JSONException e) {
	                Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
	            }
	        }
	    }
	    else if (resultCode == Activity.RESULT_CANCELED) {
	        Log.i("paymentExample", "The user canceled.");
	    }
	    else if (resultCode == 	PaymentActivity.RESULT_EXTRAS_INVALID) {
	        Log.i("paymentExample", "An invalid payment was submitted. Please see the docs.");
	    }
	}

	
}
