package com.ceduliocezar.lux.data.cloud;

import java.io.IOException;

/**
 * Created by ceduliocezar on 01/12/16.
 */

public class NoConnectivityException extends IOException {
    @Override
    public String getMessage() {
        return "No network available, please check your WiFi or Data connection";
    }
}
