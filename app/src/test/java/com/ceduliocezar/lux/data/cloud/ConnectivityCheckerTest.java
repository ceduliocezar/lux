package com.ceduliocezar.lux.data.cloud;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;

import com.ceduliocezar.lux.data.cloud.ConnectivityChecker;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by ceduliocezar on 01/12/16.
 */
public class ConnectivityCheckerTest {


    @Mock
    private Context context;
    private ConnectivityChecker checker;
    @Mock
    private ConnectivityManager connectivityManager;
    @Mock
    private android.net.NetworkInfo activeNetworkInfo;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        this.checker = new ConnectivityChecker(context);
    }

    @Test
    public void testConnected() {

        when(context.getSystemService(Service.CONNECTIVITY_SERVICE)).thenReturn
                (connectivityManager);
        when(connectivityManager.getActiveNetworkInfo()).thenReturn(activeNetworkInfo);
        when(activeNetworkInfo.isConnectedOrConnecting()).thenReturn(true);

        boolean connected = checker.connected();

        assertTrue(connected);

    }

    @Test
    public void test_not_connected() {

        when(context.getSystemService(Service.CONNECTIVITY_SERVICE)).thenReturn
                (connectivityManager);
        when(connectivityManager.getActiveNetworkInfo()).thenReturn(activeNetworkInfo);
        when(activeNetworkInfo.isConnectedOrConnecting()).thenReturn(false);

        boolean connected = checker.connected();

        assertFalse(connected);
    }

    @Test
    public void test_not_connected_null() {

        when(context.getSystemService(Service.CONNECTIVITY_SERVICE)).thenReturn
                (connectivityManager);
        when(connectivityManager.getActiveNetworkInfo()).thenReturn(null);

        boolean connected = checker.connected();

        assertFalse(connected);
    }

}