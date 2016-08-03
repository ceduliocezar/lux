package com.ceduliocezar.lux.data;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheMovieDBAPI {
    @GET("discover/movie?sort_by=popularity.desc")
    Call<MovieTransport> orderByRate(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("genre/movie/list")
    Call<GenreTransport> getGenres(@Query("api_key") String apiKey);


}