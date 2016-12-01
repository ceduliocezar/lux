package com.ceduliocezar.lux.data.cloud;


import com.ceduliocezar.lux.data.backdrop.BackdropTransport;
import com.ceduliocezar.lux.data.genre.GenreTransport;
import com.ceduliocezar.lux.data.movie.Movie;
import com.ceduliocezar.lux.data.movie.MovieTransport;
import com.ceduliocezar.lux.data.video.VideosTransport;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDBRESTApi {
    @GET("discover/movie?sort_by=popularity.desc")
    Call<MovieTransport> orderByPopularity(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("genre/movie/list")
    Call<GenreTransport> getGenres(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos")
    Call<VideosTransport> getVideos(@Path("movie_id") Integer movieId, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Call<Movie> getMovie(@Path("movie_id") Integer movieId, @Query("api_key") String apikey);

    @GET("movie/{movie_id}/images")
    Call<BackdropTransport> getMovieImages(@Path("movie_id") Integer movieId, @Query("api_key") String apikey);
}