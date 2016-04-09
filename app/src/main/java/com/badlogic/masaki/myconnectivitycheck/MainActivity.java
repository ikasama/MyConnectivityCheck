package com.badlogic.masaki.myconnectivitycheck;

import android.app.usage.NetworkStatsManager;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.badlogic.masaki.myconnectivitycheck.network.NetworkDetector;
import com.badlogic.masaki.myconnectivitycheck.network.NetworkReceiver;
import com.badlogic.masaki.myconnectivitycheck.network.OnNetworkStateChangedListener;

public class MainActivity extends AppCompatActivity implements OnNetworkStateChangedListener{

    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mReceiver == null) {
            mReceiver = new NetworkReceiver(this);
        }
        Log.d(NetworkReceiver.TAG, "NetworkReceiver has been registered");
//        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        registerReceiver(mReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mReceiver != null) {
//            LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
            unregisterReceiver(mReceiver);
            Log.d(NetworkReceiver.TAG, "NetworkReceiver has been unregistered");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNetworkConnected() {
        Log.d(NetworkReceiver.TAG, "OnNetworkConnected called");
    }

    @Override
    public void onNetworkDisconnected() {
        Log.d(NetworkReceiver.TAG, "OnNetworkDisconnected called");
    }

    @Override
    public void onNetworkAvailable() {
        Log.d(NetworkReceiver.TAG, "OnNetworkAvailable called");
    }
}
