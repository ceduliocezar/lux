package com.ceduliocezar.lux.data.cloud;

import android.content.Context;

import com.ceduliocezar.lux.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by ceduliocezar on 06/12/16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Request.class, HttpUrl.class, okhttp3.HttpUrl.Builder.class, okhttp3.Request
        .Builder.class})
public class ApiKeyInterceptorTest {


    @Mock
    private Context context;

    private ApiKeyInterceptor interceptor;
    private Interceptor.Chain chain;
    private okhttp3.Request request;
    private okhttp3.HttpUrl url;
    private okhttp3.HttpUrl.Builder urlBuilder;
    @Mock
    private okhttp3.Request.Builder requestBuilder;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        chain = PowerMockito.mock(Interceptor.Chain.class);
        request = PowerMockito.mock(Request.class);
        url = PowerMockito.mock(HttpUrl.class);
        urlBuilder = PowerMockito.mock(okhttp3.HttpUrl.Builder.class);
        interceptor = new ApiKeyInterceptor(context);
    }

    @Test
    public void intercept() throws Exception {

        String fakeApiKey = "123456";

        Request request = new Request.Builder().url("http://www.fake.com").build();


        when(context.getString(R.string.MOVIE_DB_API_KEY)).thenReturn(fakeApiKey);
        when(chain.request()).thenReturn(request);
        when()

        Request.Builder spyRequestBuilder  = Mockito.spy(request.newBuilder());
        Request spyRequest  = Mockito.spy(spyRequestBuilder.build());

        interceptor.intercept(chain);

        String expected = fakeApiKey;
        String actual = spyRequest.url().queryParameter(ApiKeyInterceptor.API_KEY_PARAM_KEY);

        assertEquals(expected, actual);
    }

}