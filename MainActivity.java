package com.example.localbroadcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private TextView tv;

   private BroadcastReceiver m=new BroadcastReceiver() {
       @Override
       public void onReceive(Context context, Intent intent) {
              String data=intent.getStringExtra("key");
           Toast.makeText(getApplicationContext(),data+" and it is received",Toast.LENGTH_LONG).show();
              tv.setText(data);
       }
   };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.view);



        Intent si=new Intent(this,MyIntentService.class);
        si.putExtra("key","Message sent from activity to service");
        startService(si);
        Toast.makeText(getApplicationContext(),"Message sent from activity to service",Toast.LENGTH_LONG).show();

    }
    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter i=new IntentFilter(MyIntentService.MY_SERVICE_INTENT);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(m,i);
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(m);
    }
}