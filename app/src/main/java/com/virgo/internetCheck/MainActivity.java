package com.virgo.internetCheck;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.virgo.internetcheckmodul.Connectivity;

public class MainActivity extends AppCompatActivity implements Connectivity.ConnectivityListener {
    Connectivity connectivity;
    int interval = 3;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectivity = new Connectivity(this, interval, this);
        connectivity.startConnectivityCheck();
    }


    @Override
    public void connect() {
        Log.d(TAG + ": ", "Connected");
    }

    @Override
    public void disConnect() {
        Log.d(TAG + ": ", "Dis-Connected");
    }

    @Override
    public void noInternet() {
        Log.d(TAG + ": ", "No Internet");
    }
}
