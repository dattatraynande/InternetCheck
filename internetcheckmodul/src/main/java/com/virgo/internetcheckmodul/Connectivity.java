package com.virgo.internetcheckmodul;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class Connectivity {

    ConnectivityListener connectivityListener;
    Context context;
    ConnectivityManager cm;
    private String TAG = "Connectivity";
    private String url = "http://clients3.google.com/generate_204";
    private HttpURLConnection urlc = null;
    private Timer mTimer = null;
    private int interval;

    public Connectivity(Context context, int interval, ConnectivityListener mainActivity) {
        this.context = context;
        this.interval = interval;
        this.connectivityListener = mainActivity;
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public void startConnectivityCheck() {
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new CheckForConnection(), 0, interval * 1000);
    }

    private synchronized void isInternetAvailable() {
        try {
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                //Log.d(TAG + ": ", "WiFi connection available");
                if (hasInternetAccess()) {
                    //Log.d(TAG + " : ", "Internet available :");
                    connectivityListener.connect();
                } else {
                    //Log.d(TAG + ": ", "No Internet :");
                    connectivityListener.noInternet();
                }
            } else {
                connectivityListener.disConnect();
                //Log.d(TAG + ": ", "Not connected");
            }
        } catch (Exception e) {
            Log.e(TAG + "", "Error checking internet connection", e);
        }
    }

    private synchronized boolean hasInternetAccess() {
        try {
            if (urlc != null) {
                try {
                    urlc.disconnect();
                    urlc = null;
                } catch (Exception e) {
                }
            }
            urlc = (HttpURLConnection) (new URL(url).openConnection());
            urlc.setRequestProperty("User-Agent", "Android");
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(2000);
            urlc.setReadTimeout(2000);
            urlc.setRequestMethod("GET");
            urlc.setUseCaches(false);
            urlc.setAllowUserInteraction(false);
            urlc.connect();
            if (urlc.getResponseCode() == 204 &&
                    urlc.getContentLength() == 0) {
                urlc.disconnect();
                return true;
            }

            return false;

        } catch (IOException e) {
            return false;
        }catch (Exception e) {
            return false;
        }
    }

    public interface ConnectivityListener {
        void connect();

        void disConnect();

        void noInternet();

    }

    class CheckForConnection extends TimerTask {
        @Override
        public void run() {
            isInternetAvailable();
        }
    }
}
