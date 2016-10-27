package com.ceduliocezar.lux.movies;

import com.ceduliocezar.lux.data.Movie;
import com.ceduliocezar.lux.movie.list.MoviesServiceApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cedulio on 08/08/2016.
 */
public class FakeMoviesServiceApiImpl implements MoviesServiceApi {

    private static final ArrayList<Movie> MOVIES_SERVICE_DATA = new ArrayList<>();
    private static final int MOVIE_DB_PAGE_SIZE = 20;
    private static final int MAX_NUM_PAGE = 5;
    private static final Integer[] FAKE_GENRES = {1, 2, 3, 4};
    private static final String FAKE_OVERVIEW = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do"
            + " eiusmod tempor incididunt ut labore et dolore magna aliqua. "
            + "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut"
            + " aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit "
            + "in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur "
            + "sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt "
            + "mollit anim id est laborum.";
    public static final String FAKE_TITLE = "MOVIE";
    private static final String FAKE_ORIGINAL_LANGUAGE = "en_US";
    private static final Double FAKE_POPULARITY = 9.0;
    private static final String FAKE_POSTER_PATH = "file:///android_asset/poster_test.jpg";
    private static final String FAKE_RELEASE_DATE = "2016-06-06";
    private static final Double FAKE_VOTE_AVERAGE = 7.0;
    private static final Integer FAKE_VOTE_COUNT = 200;

    @Override
    public void getMovies(int page, MoviesServiceCallback<List<Movie>> callback) {

        if (MOVIES_SERVICE_DATA.isEmpty()) {
            fakeMovies();
        }

        callback.onLoaded(MOVIES_SERVICE_DATA, page, MAX_NUM_PAGE);
    }

    private void fakeMovies() {
        for (int i = 0; i < MOVIE_DB_PAGE_SIZE; i++) {
            MOVIES_SERVICE_DATA.add(createFakeMovie(i));
        }
    }

    private Movie createFakeMovie(int i) {
        Movie movie = new Movie();
        movie.setAdult(false);
        movie.setBackdropPath("");
        movie.setGenreIds(Arrays.asList(FAKE_GENRES));
        movie.setId(i);
        movie.setOriginalLanguage(FAKE_ORIGINAL_LANGUAGE +i);
        movie.setOriginalTitle(FAKE_TITLE + i);
        movie.setOverview(FAKE_OVERVIEW);
        movie.setPopularity(FAKE_POPULARITY);
        movie.setPosterPath(FAKE_POSTER_PATH);
        movie.setReleaseDate(FAKE_RELEASE_DATE);
        movie.setTitle(FAKE_TITLE + i);
        movie.setVideo(false);
        movie.setVoteAverage(FAKE_VOTE_AVERAGE);
        movie.setVoteCount(FAKE_VOTE_COUNT);

        return movie;
    }
}
