package com.ceduliocezar.lux.movie.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ceduliocezar.lux.Injection;
import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.custom.ui.EndlessScrollListener;
import com.ceduliocezar.lux.data.genre.Genre;
import com.ceduliocezar.lux.data.genre.GenresRepository;
import com.ceduliocezar.lux.data.movie.Movie;
import com.ceduliocezar.lux.data.movie.MoviesRepository;
import com.ceduliocezar.lux.data.poster.PosterProvider;
import com.ceduliocezar.lux.movie.detail.MovieDetailActivity;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cedulio on 05/06/16.
 */
public class MoviesFragment extends Fragment implements MoviesContract.View {


    private MovieAdapter adapter;
    private List<Genre> genres = new ArrayList<>();

    private SwipeRefreshLayout swipeContainer;

    private MoviesContract.UserActionsListener userActionsListener;
    private int maxPage = 0;
    private GridView gridView;


    public MoviesFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new MovieAdapter(new ArrayList<Movie>());

        MoviesRepository moviesRepository = Injection.providesMoviesRepository(getContext());
        GenresRepository genresRepository = Injection.providesGenreRepository(getContext());

        userActionsListener = new MoviesPresenter(moviesRepository, genresRepository, this);
    }

    @Override
    public void onResume() {
        super.onResume();

        userActionsListener.loadMovies();
        userActionsListener.loadGenres();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    public static MoviesFragment newInstance() {
        MoviesFragment fragment = new MoviesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);

        initGridView(rootView);
        initSwipeContainer(rootView);

        return rootView;
    }

    private void initSwipeContainer(View rootView) {
        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override

            public void onRefresh() {
                startRefresh();
            }

        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,

                android.R.color.holo_green_light,

                android.R.color.holo_orange_light,

                android.R.color.holo_red_light);
    }

    private void initGridView(View rootView) {
        gridView = (GridView) rootView.findViewById(R.id.movie_grid);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                showMovieDetail(position);
            }
        });
        gridView.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                if (page <= maxPage) {
                    userActionsListener.loadPage(page);
                    return true;
                } else {
                    return false;
                }

            }
        });
    }

    private void showMovieDetail(int position) {
        Intent intent = MovieDetailActivity.getIntent(getActivity(), ((Movie) adapter.getItem
                (position)).getId());
        startActivity(intent);
    }

    private void startRefresh() {
        adapter.clear();
        adapter.notifyDataSetChanged();
        userActionsListener.loadMovies();
    }

    @Override
    public void showActivityIndicator() {
        swipeContainer.setRefreshing(true);
    }

    @Override
    public void hideActivityIndicator() {
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void showMovies(List<Movie> movies, int currentPage, int maxPage) {
        this.maxPage = maxPage;

        adapter.clear();
        adapter.addAll(movies);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(Throwable e) {
        Log.d("error", Log.getStackTraceString(e));
        Toast.makeText(getActivity(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void appendPage(List<Movie> movies, int currentPage) {
        adapter.addAll(movies);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadGenres(List<Genre> genres) {
        this.genres = genres;
        this.gridView.invalidateViews();
    }

    @Override
    public void showNoMoviesFoundView() {
        // TODO: 26/11/16  
    }

    public void showPageLoad() {
        getView().findViewById(R.id.movie_load_progress).setVisibility(View.VISIBLE);
    }

    public void hidePageLoad() {
        getView().findViewById(R.id.movie_load_progress).setVisibility(View.GONE);
        swipeContainer.setRefreshing(false);
    }

    private class MovieAdapter extends BaseAdapter {

        private final PosterProvider posterProvider;
        private List<Movie> movies;

        public MovieAdapter(List<Movie> movies) {
            this.movies = movies;
            this.posterProvider = Injection.providesPosterProvider();
        }

        @Override
        public int getCount() {
            return movies.size();
        }

        @Override
        public Object getItem(int position) {
            return movies.get(position);
        }

        @Override
        public long getItemId(int position) {
            return movies.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Movie movie = movies.get(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, null);
            }

            initImageView(convertView, movie);
            initGenreView(convertView, movie);
            initTitleView(convertView, movie);
            initYearView(convertView, movie);
            initRateView(convertView, movie);

            return convertView;
        }

        private void initRateView(View convertView, Movie movie) {
            TextView tvRateNumber = (TextView) convertView.findViewById(R.id.movie_rate_number);
            tvRateNumber.setText(String.valueOf(movie.getVoteAverage()));
        }

        private void initYearView(View convertView, Movie movie) {
            TextView tvYear = (TextView) convertView.findViewById(R.id.movie_year);
            tvYear.setText(getReleaseYear(movie));
        }

        private void initTitleView(View convertView, Movie movie) {
            TextView tvTitle = (TextView) convertView.findViewById(R.id.movie_title);
            tvTitle.setText(movie.getTitle());
        }

        private void initGenreView(View convertView, Movie movie) {
            TextView tvGenres = (TextView) convertView.findViewById(R.id.movie_genre);
            tvGenres.setText(getGenres(movie));
        }

        private void initImageView(View convertView, Movie movie) {
            ImageView imageView = (ImageView) convertView.findViewById(R.id.movie_image);

            posterProvider.loadImage(movie.getPosterPath(), imageView, getContext());
        }

        private String getReleaseYear(Movie movie) {


            if (movie.getReleaseDate().isEmpty()) {
                return "";
            }

            return movie.getReleaseDate().substring(0, 4);
        }

        public void addAll(List<Movie> movies) {
            this.movies.addAll(movies);
            notifyDataSetChanged();
        }

        public void clear() {
            this.movies.clear();
        }
    }

    private String getGenres(Movie movie) {


        List<String> genresString = new ArrayList<>();
        for (Genre genre : genres) {
            for (Integer id : movie.getGenreIds()) {
                if (genre.getId() == id) {
                    genresString.add(genre.getName());
                }
            }
        }
        return StringUtils.join(genresString, ", ");
    }
}