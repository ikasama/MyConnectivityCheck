package com.badlogic.masaki.myconnectivitycheck.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by shojimasaki on 2016/04/09.
 */
public class NetworkReceiver extends BroadcastReceiver{
    public static final String TAG = NetworkReceiver.class.getSimpleName();

    private final OnNetworkStateChangedListener mListener;

    public NetworkReceiver(OnNetworkStateChangedListener listener) {
        super();
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        NetworkState networkState = NetworkDetector.INSTANCE.getNetworkState(context);

        /*
        if mListener is null, the callback methods don't be called.
         */
        if(mListener == null) {
            return;
        }

        switch (networkState) {
            case OFFLINE:
                Log.d(TAG, "network state has changed to : " + networkState.name());
                mListener.onNetworkDisconnected();
                break;

            case WIFI_AVAILABLE:
            case MOBILE_AVAILABLE:
                Log.d(TAG, "network state has changed to : " + networkState.name());
                mListener.onNetworkAvailable();
                break;

            case WIFI_CONNECTED:
            case MOBILE_CONNECTED:
                Log.d(TAG, "network state has changed to : " + networkState.name());
                mListener.onNetworkConnected();
                break;
        }
    }
}
