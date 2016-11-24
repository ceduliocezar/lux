package com.ceduliocezar.lux.data.movie;


import com.ceduliocezar.lux.data.genre.GenreTransport;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDBRESTApi {
    @GET("discover/movie?sort_by=popularity.desc")
    Call<MovieTransport> orderByPopularity(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("genre/movie/list")
    Call<GenreTransport> getGenres(@Query("api_key") String apiKey);


}