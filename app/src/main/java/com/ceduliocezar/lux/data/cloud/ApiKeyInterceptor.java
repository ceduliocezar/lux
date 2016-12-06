package com.ceduliocezar.lux.data.cloud;

import android.content.Context;

import com.ceduliocezar.lux.R;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ceduliocezar on 06/12/16.
 */

public class ApiKeyInterceptor implements Interceptor {

    public static final String API_KEY_PARAM_KEY = "api_key";
    private final Context context;

    @Inject
    public ApiKeyInterceptor(Context context) {
        this.context = checkNotNull(context, "context can not be null");
    }

    public Response intercept(Chain chain) throws IOException {

        String apiKey = context.getString(R.string.MOVIE_DB_API_KEY);

        Request originalRequest = chain.request();

        HttpUrl url = originalRequest.url()
                .newBuilder()
                .addQueryParameter(API_KEY_PARAM_KEY, apiKey)
                .build();

        originalRequest = originalRequest.newBuilder().url(url).build();

        return chain.proceed(originalRequest);
    }
}
