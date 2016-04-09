package com.badlogic.masaki.myconnectivitycheck.network;

/**
 * Created by shojimasaki on 2016/04/09.
 */
public interface OnNetworkStateChangedListener {
    void onNetworkConnected();
    void onNetworkDisconnected();
    void onNetworkAvailable();
}
