package com.ceduliocezar.lux.data;

import com.ceduliocezar.lux.data.cloud.ConnectivityCheckInterceptor;
import com.ceduliocezar.lux.data.cloud.ConnectivityChecker;
import com.ceduliocezar.lux.data.cloud.NoConnectivityException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import okhttp3.Interceptor;

import static org.mockito.Mockito.when;

/**
 * Created by ceduliocezar on 01/12/16.
 */
public class ConnectivityCheckInterceptorTest {

    private ConnectivityCheckInterceptor interceptor;

    @Mock
    private Interceptor.Chain chain;

    @Mock
    private ConnectivityChecker checker;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        this.interceptor = new ConnectivityCheckInterceptor(checker);
    }

    @Test(expected = NoConnectivityException.class)
    public void intercept_not_connected() throws Exception {

        when(checker.connected()).thenReturn(false);

        interceptor.intercept(chain);
    }

    @Test()
    public void intercept_connected() throws Exception {

        when(checker.connected()).thenReturn(true);
        interceptor.intercept(chain);
    }

}