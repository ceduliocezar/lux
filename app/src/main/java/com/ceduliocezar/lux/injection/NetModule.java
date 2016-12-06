package com.ceduliocezar.lux.injection;

import android.content.Context;

import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.data.cloud.ApiKeyInterceptor;
import com.ceduliocezar.lux.data.cloud.ConnectivityCheckInterceptor;
import com.ceduliocezar.lux.data.cloud.ConnectivityChecker;
import com.ceduliocezar.lux.data.cloud.MovieDBRESTApi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ceduliocezar on 06/12/16.
 */

@Module
public class NetModule {

    @Provides
    @Singleton
    ConnectivityChecker connectivityChecker(@Named("applicationContext") Context context) {
        return new ConnectivityChecker(context);
    }

    @Provides
    @Singleton
    @Named("connectivityCheckInterceptor")
    Interceptor connectivityCheckInterceptor(ConnectivityChecker connectivityChecker) {
        return new ConnectivityCheckInterceptor(connectivityChecker);
    }

    @Provides
    @Singleton
    @Named("apiKeyInterceptor")
    Interceptor apiKeyInterceptor(@Named("applicationContext") final Context context) {
        return new ApiKeyInterceptor(context);
    }

    @Provides
    @Singleton
    MovieDBRESTApi movieDBRESTApi(Converter.Factory converterFactory,
                                  @Named("movieDBBaseUrl") String baseUrl,
                                  OkHttpClient okHttpClient) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .build();

        MovieDBRESTApi movieDBRESTApi = retrofit.create(MovieDBRESTApi.class);

        return movieDBRESTApi;
    }

    @Provides
    @Singleton
    @Named("movieDBBaseUrl")
    String baseMovieDBUrl(@Named("applicationContext") Context context) {
        return context.getString(R.string.MOVIE_DB_BASE_URL);
    }

    @Provides
    @Singleton
    @Named("movieDBApiKey")
    String movieDBApiKey(@Named("applicationContext") Context context) {
        return context.getString(R.string.MOVIE_DB_API_KEY);
    }

    @Provides
    @Singleton
    OkHttpClient okHttpClient(@Named("connectivityCheckInterceptor") Interceptor connectivityCheckInterceptor,
                              @Named("apiKeyInterceptor") Interceptor apiKeyInterceptor) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(connectivityCheckInterceptor)
                .addInterceptor(apiKeyInterceptor)
                .build();

        return okHttpClient;
    }

    @Provides
    @Singleton
    Converter.Factory gsonConverter() {
        return GsonConverterFactory.create();
    }
}
