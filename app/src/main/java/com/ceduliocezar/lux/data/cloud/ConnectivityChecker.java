package com.ceduliocezar.lux.data.cloud;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ceduliocezar on 01/12/16.
 */

public class ConnectivityChecker {


    private final Context context;

    @Inject
    public ConnectivityChecker(@NonNull Context context) {
        this.context = checkNotNull(context, "context can not be null");
    }


    public boolean connected() {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Service
                .CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
