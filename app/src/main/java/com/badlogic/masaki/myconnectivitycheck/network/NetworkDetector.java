package com.badlogic.masaki.myconnectivitycheck.network;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Utility for monitoring network state.
 * Created by shojimasaki on 2016/04/09.
 */
public enum NetworkDetector {

    INSTANCE;

    public static final String TAG = NetworkDetector.class.getSimpleName();

    public final NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return mgr.getActiveNetworkInfo();
    }

    public final boolean isOnline(Context context) {
        NetworkInfo info =  NetworkDetector.INSTANCE.getActiveNetworkInfo(context);
        return (info != null) && (info.isConnected());
    }

    public final boolean isWifiConnected(Context context) {
        NetworkInfo info = NetworkDetector.INSTANCE.getActiveNetworkInfo(context);
        if(info != null) {
            return (info.isConnected()) && info.getType() == ConnectivityManager.TYPE_WIFI;
        }
        return false;
    }

    public final boolean isMobileConnected(Context context) {
        NetworkInfo info = NetworkDetector.INSTANCE.getActiveNetworkInfo(context);
        if(info != null) {
            return (info.isConnected()) && info.getType() == ConnectivityManager.TYPE_MOBILE;
        }
        return false;
    }

    public final NetworkState getNetworkState(Context context) {
        NetworkInfo info = NetworkDetector.INSTANCE.getActiveNetworkInfo(context);

        if(info == null) {
            return NetworkState.OFFLINE;
        }

        switch(info.getType()) {
            case ConnectivityManager.TYPE_WIFI:
                return info.isConnected() ? NetworkState.WIFI_CONNECTED : NetworkState.WIFI_AVAILABLE;

            case ConnectivityManager.TYPE_MOBILE:
                return info.isConnected() ? NetworkState.MOBILE_CONNECTED : NetworkState.MOBILE_AVAILABLE;

            default:
                throw new RuntimeException("NetworkDetector supports only ConnectivityManager.TYPE_WIFI and ConnectivityManager.TYPE_MOBILE");
        }
    }
}
