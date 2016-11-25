package com.ceduliocezar.lux.data;


import com.ceduliocezar.lux.data.genre.GenreTransport;
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
    Call<VideosTransport> getVideos(@Query("api_key") String apiKey, @Path("movie_id") Integer
            movieId);


}