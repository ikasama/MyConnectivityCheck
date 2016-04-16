package com.badlogic.masaki.myconnectivitycheck.network;

/**
 * interface that listens for network-state changes
 * Created by shojimasaki on 2016/04/09.
 */
public interface OnNetworkStateChangedListener {
    /**
     * called when the device is connected with network
     */
    void onNetworkConnected();

    /**
     * called when the device is disconnected with network
     */
    void onNetworkDisconnected();

    /**
     * called when network is available to the device
     */
    void onNetworkAvailable();
}
