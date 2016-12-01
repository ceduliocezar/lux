package com.ceduliocezar.lux.data.cloud;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by ceduliocezar on 01/12/16.
 */

public class ConnectivityCheckInterceptor implements Interceptor {

    private ConnectivityChecker connectivityChecker;

    public ConnectivityCheckInterceptor(ConnectivityChecker connectivityChecker) {
        this.connectivityChecker = connectivityChecker;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        if (notConnected()) {
            throw new NoConnectivityException();
        }

        return chain.proceed(chain.request());
    }

    private boolean notConnected() {
        return !connectivityChecker.connected();
    }
}
