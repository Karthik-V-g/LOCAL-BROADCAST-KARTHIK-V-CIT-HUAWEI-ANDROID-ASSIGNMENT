package com.example.localbroadcast;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;


public class MyIntentService extends IntentService {
    public static final String MY_SERVICE_INTENT = "MyServiceIntent";

    public MyIntentService() {
        super("MyIntentService");
    }

    protected void onHandleIntent(Intent intent) {
        String data = intent.getStringExtra("key");
        Log.i("clean", data);
        Toast.makeText(getApplicationContext(),data+" and it is received",Toast.LENGTH_LONG).show();
        try
        {Thread.sleep(5000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent ri = new Intent(MY_SERVICE_INTENT);
        ri.putExtra("key", "Message broadcasted from service to activity");
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(ri);


    }
}