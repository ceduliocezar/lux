package com.ceduliocezar.lux.data.cloud;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by ceduliocezar on 01/12/16.
 */

public class ConnectivityChecker {


    private final Context context;

    public ConnectivityChecker(Context context) {
        this.context = context;
    }


    public boolean connected() {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Service
                .CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
