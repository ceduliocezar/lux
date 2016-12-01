package com.ceduliocezar.lux.data.cloud;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ceduliocezar.lux.R;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by cedulio on 05/06/16.
 */
public class MovieAPIFactory {


    public static MovieDBRESTApi create(@NonNull Context context) {

        checkNotNull(context, "context can not be null");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.MOVIE_DB_BASE_URL))
                .client(createCustomClient(context))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieDBRESTApi service = retrofit.create(MovieDBRESTApi.class);

        return service;
    }

    private static OkHttpClient createCustomClient(Context context) {

        ConnectivityCheckInterceptor connectivityCheckInterceptor = createConnectivityInterceptor(context);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(connectivityCheckInterceptor)
                .build();

        return okHttpClient;
    }

    @NonNull
    private static ConnectivityCheckInterceptor createConnectivityInterceptor(Context context) {
        ConnectivityChecker connectivityChecker = new ConnectivityChecker(context);
        return new
                ConnectivityCheckInterceptor(connectivityChecker);
    }


}
