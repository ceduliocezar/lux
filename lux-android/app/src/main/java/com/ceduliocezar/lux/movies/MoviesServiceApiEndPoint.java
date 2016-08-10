package com.ceduliocezar.lux.movies;

import android.content.Context;
import android.util.Log;

import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.data.Movie;
import com.ceduliocezar.lux.data.MovieAPI;
import com.ceduliocezar.lux.data.MovieTransport;
import com.ceduliocezar.lux.data.MovieRESTApi;

import java.util.List;

import retrofit2.Call;

/**
 * Created by cedulio on 08/08/2016.
 */
public class MoviesServiceApiEndpoint {

    private final Context context;

    public MoviesServiceApiEndpoint(Context applicationContext){
        this.context = applicationContext.getApplicationContext();
    }

    public List<Movie> loadMovies(int page) throws java.io.IOException {

        MovieRESTApi service = MovieAPI.getInstance(context);

        Call<MovieTransport> response = service.orderByRate(context.getString(R.string.MOVIE_DB_API_KEY), page);
        MovieTransport movieTransport = response.execute().body();

        return movieTransport.getResults();
    }

}
