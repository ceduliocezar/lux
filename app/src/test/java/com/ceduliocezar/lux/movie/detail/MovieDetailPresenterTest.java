package com.ceduliocezar.lux.movie.detail;

import android.content.Context;

import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.data.movie.Movie;
import com.ceduliocezar.lux.data.movie.MoviesRepository;
import com.ceduliocezar.lux.data.video.Video;
import com.ceduliocezar.lux.data.video.VideosRepository;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by TECBMCCS on 25/11/16.
 */
public class MovieDetailPresenterTest {


    private static List<Video> VIDEOS = Lists.newArrayList(new Video(), new Video());

    @Mock
    private VideosRepository videosRepository;

    @Mock
    private MoviesRepository moviesRepository;

    @Mock
    private MovieDetailContract.View view;

    @Mock
    private Movie movie;

    private MovieDetailPresenter movieDetailPresenter;

    @Captor
    private ArgumentCaptor<MoviesRepository.LoadMovieCallback> loadMovieCallbackArgumentCaptor;

    @Captor
    ArgumentCaptor<VideosRepository.LoadVideosCallback> loadVideosCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<Integer> movieIdArgumentCaptor;

    @Mock
    private Context context;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        movieDetailPresenter = new MovieDetailPresenter(view,
                context,
                videosRepository,
                moviesRepository);
    }

    @Test
    public void loadMovie() throws Exception {

        int movieId = 0;

        movieDetailPresenter.loadMovie(movieId);

        verify(moviesRepository).getMovie(movieIdArgumentCaptor.capture(), loadMovieCallbackArgumentCaptor.capture());
        loadMovieCallbackArgumentCaptor.getValue().onLoadMovie(movie);

        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view).showLoading();
        inOrder.verify(view).hideLoading();
        inOrder.verify(view).showMovie(movie);
    }

    @Test
    public void loadMovie_null_movie() throws Exception {

        int movieId = 0;

        when(context.getString(R.string.error_unable_to_load_movie)).thenReturn("FAKE_MESSAGE");

        movieDetailPresenter.loadMovie(movieId);

        verify(moviesRepository).getMovie(movieIdArgumentCaptor.capture(), loadMovieCallbackArgumentCaptor.capture());
        loadMovieCallbackArgumentCaptor.getValue().onLoadMovie(null);

        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view).showLoading();
        inOrder.verify(view).hideLoading();
        inOrder.verify(view).showError(any(String.class));
    }

    @Test
    public void loadVideos() throws Exception {

        int movieId = 0;

        movieDetailPresenter.loadVideos(movieId);

        verify(videosRepository).getMovies(movieIdArgumentCaptor.capture(), loadVideosCallbackArgumentCaptor.capture());

        loadVideosCallbackArgumentCaptor.getValue().onLoadVideos(VIDEOS);

        InOrder inOrder = Mockito.inOrder(view);

        inOrder.verify(view).showLoadingVideos();
        inOrder.verify(view).hideLoadingVideos();
        inOrder.verify(view).showVideos(VIDEOS);

    }

    @Test
    public void loadVideos_empty_list() throws Exception {

        int movieId = 0;

        movieDetailPresenter.loadVideos(movieId);

        verify(videosRepository).getMovies(movieIdArgumentCaptor.capture(), loadVideosCallbackArgumentCaptor.capture());

        loadVideosCallbackArgumentCaptor.getValue().onLoadVideos(Collections.<Video>emptyList());

        InOrder inOrder = Mockito.inOrder(view);

        inOrder.verify(view).showLoadingVideos();
        inOrder.verify(view).hideLoadingVideos();
        inOrder.verify(view).hideContainerVideos();

    }

    @Test
    public void loadVideos_null_list() throws Exception {

        int movieId = 0;

        movieDetailPresenter.loadVideos(movieId);

        verify(videosRepository).getMovies(movieIdArgumentCaptor.capture(), loadVideosCallbackArgumentCaptor.capture());

        loadVideosCallbackArgumentCaptor.getValue().onLoadVideos(null);

        InOrder inOrder = Mockito.inOrder(view);

        inOrder.verify(view).showLoadingVideos();
        inOrder.verify(view).hideLoadingVideos();
        inOrder.verify(view).hideContainerVideos();
    }

}