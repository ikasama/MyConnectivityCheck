package com.badlogic.masaki.myconnectivitycheck.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

/**
 * Utility for monitoring network state.
 * Created by shojimasaki on 2016/04/09.
 */
public enum NetworkDetector {

    /**
    uses enum singleton
     */
    INSTANCE;

    public static final String TAG = NetworkDetector.class.getSimpleName();

    /**
     * gets the currently active network info
     * @param context Context
     * @return  details about the currently active default data network.
     */
    public final NetworkInfo getActiveNetworkInfo(@NonNull Context context) {
        final ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return mgr.getActiveNetworkInfo();
    }

    /**
     * checks if the device is online
     * @param context Context
     * @return true if the device is online
     */
    public final boolean isOnline(@NonNull Context context) {
        final NetworkInfo info =  NetworkDetector.INSTANCE.getActiveNetworkInfo(context);
        return (info != null) && (info.isConnected());
    }

    /**
     * checks if the device is connected with Wi-Fi
     * @param context Context
     * @return true if the device is wifi-connected
     */
    public final boolean isWifiConnected(@NonNull Context context) {
        final NetworkInfo info = NetworkDetector.INSTANCE.getActiveNetworkInfo(context);
        if(info != null) {
            return (info.isConnected()) && info.getType() == ConnectivityManager.TYPE_WIFI;
        }
        return false;
    }

    /**
     * checks if the device is connected with MN
     * @param context Context
     * @return true if the device is mobile-connected
     */
    public final boolean isMobileConnected(@NonNull Context context) {
        final NetworkInfo info = NetworkDetector.INSTANCE.getActiveNetworkInfo(context);
        if(info != null) {
            return (info.isConnected()) && info.getType() == ConnectivityManager.TYPE_MOBILE;
        }
        return false;
    }

    /**
     * gets the device's network state currently active
     * @param context Context
     * @return currently appropriate network state
     */
    public final NetworkState getNetworkState(@NonNull Context context) {
        final NetworkInfo info = NetworkDetector.INSTANCE.getActiveNetworkInfo(context);

        /*
         *if info is null, that means the device is offline
         */
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
