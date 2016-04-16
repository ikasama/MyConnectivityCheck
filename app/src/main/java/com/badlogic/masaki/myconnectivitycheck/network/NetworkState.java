package com.badlogic.masaki.myconnectivitycheck.network;

/**
 * represents simple network states
 * Created by shojimasaki on 2016/04/09.
 */
public enum NetworkState {
    /**
     * represents the state of wifi-connected
     */
    WIFI_CONNECTED,

    /**
     * represents the state of wifi-available
     */
    WIFI_AVAILABLE,

    /**
     * represents the state of MN-connected
     */
    MOBILE_CONNECTED,

    /**
     * represents the state of MN-available
     */
    MOBILE_AVAILABLE,

    /**
     * represents the state of offline
     */
    OFFLINE,
}
