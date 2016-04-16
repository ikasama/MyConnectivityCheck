package com.badlogic.masaki.myconnectivitycheck.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * simple receiver class that receives connectivity-change actions
 * Created by shojimasaki on 2016/04/09.
 */
public class NetworkReceiver extends BroadcastReceiver {

    public static final String TAG = NetworkReceiver.class.getSimpleName();

    /**
     * callback interface called when network state has changed
     */
    private final WeakReference<OnNetworkStateChangedListener> mListener;

    /**
     * constructor
     * @param listener callback interface which methods will be called when network state has changed
     */
    public NetworkReceiver(@Nullable OnNetworkStateChangedListener listener) {
        super();
        mListener = new WeakReference<>(listener);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        /*
        if the callback reference is null, the methods don't be called.
         */
        OnNetworkStateChangedListener callback = mListener.get();
        if(callback == null) {
            return;
        }

        final NetworkState networkState = NetworkDetector.INSTANCE.getNetworkState(context);

        /*
        calls callback methods according to the currently active network state
         */
        switch (networkState) {
            case OFFLINE:
                Log.d(TAG, "network state has changed to : " + networkState.name());
                callback.onNetworkDisconnected();
                break;

            case WIFI_AVAILABLE:
            case MOBILE_AVAILABLE:
                Log.d(TAG, "network state has changed to : " + networkState.name());
                callback.onNetworkAvailable();
                break;

            case WIFI_CONNECTED:
            case MOBILE_CONNECTED:
                Log.d(TAG, "network state has changed to : " + networkState.name());
                callback.onNetworkConnected();
                break;
        }
    }
}
