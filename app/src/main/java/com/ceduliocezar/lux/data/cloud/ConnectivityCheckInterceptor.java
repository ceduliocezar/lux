package com.ceduliocezar.lux.data.cloud;

import android.support.annotation.NonNull;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ceduliocezar on 01/12/16.
 */

public class ConnectivityCheckInterceptor implements Interceptor {

    private ConnectivityChecker connectivityChecker;

    @Inject
    public ConnectivityCheckInterceptor(@NonNull ConnectivityChecker connectivityChecker) {
        this.connectivityChecker =
                checkNotNull(connectivityChecker, "connectivity checker cannot be null");
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
