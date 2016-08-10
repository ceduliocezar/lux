package com.ceduliocezar.lux.movies;

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
import com.ceduliocezar.lux.data.Genre;
import com.ceduliocezar.lux.data.Movie;
import com.ceduliocezar.lux.moviedetail.MovieDetailActivity;

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

    public MoviesFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new MovieAdapter(new ArrayList<Movie>());
        userActionsListener = new MoviesPresenter(Injection.providesMoviesRepository(), this);
    }

    @Override
    public void onResume() {
        super.onResume();
        userActionsListener.loadMovies(false);
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

        GridView gridView = (GridView) rootView.findViewById(R.id.movie_grid);
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
                if(page<=maxPage){
                    userActionsListener.loadPage(page);
                    return true;
                }else{
                    return false;
                }

            }
        });


        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);

        // Setup refresh listener which triggers new data loading

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


        return rootView;
    }

    private void showMovieDetail(int position) {
        Intent intent = MovieDetailActivity.getIntent(getActivity(), (Movie) adapter.getItem(position));
        startActivity(intent);
    }

    private void startRefresh() {
        adapter.clear();
        adapter.notifyDataSetChanged();
        userActionsListener.loadMovies(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        loadNewPage();
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
    public void showMovieDetailUi(Integer movieId) {
        showMovieDetail(movieId);
    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(getActivity(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void appendPage(List<Movie> movies, int currentPage) {
        adapter.addAll(movies);
        adapter.notifyDataSetChanged();
    }

//    public class LoadData extends AsyncTask<Void, Void, Void> {
//
//        private final int pageIndexToLoad;
//        private final boolean lazy;
//        private List<Movie> movies;
//        private List<Genre> genres;
//
//        public LoadData(int pageIndexToLoad, boolean lazy) {
//            this.pageIndexToLoad = pageIndexToLoad;
//            this.lazy = lazy;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//            if (lazy) {
//                showLazyLoad();
//            }
//        }
//
//        @Override
//        protected Void doInBackground(Void... params) {
//
//            try {
//
//                MovieRESTApi service = MovieAPI.getInstance(getContext());
//
//
//                loadMovies(service);
//                loadGenres(service);
//
////                http://image.tmdb.org/t/p/w500/inVq3FRqcYIRl2la8iZikYYxFNR.jpg
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        private void loadGenres(MovieRESTApi service) throws IOException {
//            Call<GenreTransport> response = service.getGenres(getString(R.string.MOVIE_DB_API_KEY));
//            GenreTransport genreTransport = response.execute().body();
//
////            https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc?&api_key=4c2bf59aa34e0b47a51f9fe34a90c844
//
//            for (Genre genre : genreTransport.getGenres()) {
//                Log.d("debug", "genre=" + genre.getName());
//            }
//
//            this.genres = genreTransport.getGenres();
//        }
//
//        private void loadMovies(MovieRESTApi service) throws java.io.IOException {
//            Call<MovieTransport> response = service.orderByRate(getString(R.string.MOVIE_DB_API_KEY), pageIndexToLoad);
//            MovieTransport movieTransport = response.execute().body();
//
////            https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc?&api_key=4c2bf59aa34e0b47a51f9fe34a90c844
//
//            for (Movie movie : movieTransport.getResults()) {
//                Log.d("debug", "movie=" + movie.getTitle());
//            }
//
//            this.movies = movieTransport.getResults();
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//
//            if (movies != null) {
//                adapter.addAll(movies);
//            }
//
//            if (genres != null) {
//                MoviesFragment.this.genres = genres;
//            }
//
//            currentPage++;
//
//            if (getView() != null) {
//                hideLazyLoad();
//                esconderLoader();
//            }
//        }
//    }

    public void showLazyLoad() {
        getView().findViewById(R.id.movie_load_progress).setVisibility(View.VISIBLE);
    }

    public void hideLazyLoad() {
        getView().findViewById(R.id.movie_load_progress).setVisibility(View.GONE);
        swipeContainer.setRefreshing(false);
    }

    private class MovieAdapter extends BaseAdapter {

        private List<Movie> movies;

        public MovieAdapter(List<Movie> movies) {
            this.movies = movies;
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

            ImageView imageView = (ImageView) convertView.findViewById(R.id.movie_image);

//            Picasso.with(MoviesFragment.this.getActivity()).
//                    load("http://image.tmdb.org/t/p/w500" + movie.getPosterPath()).
//                    into(imageView);


            TextView tvGenres = (TextView) convertView.findViewById(R.id.movie_genre);
            tvGenres.setText(getGenres(movie));

            TextView tvTitle = (TextView) convertView.findViewById(R.id.movie_title);
            tvTitle.setText(movie.getTitle());


            TextView tvYear = (TextView) convertView.findViewById(R.id.movie_year);
            tvYear.setText(getReleaseYear(movie));

            TextView tvRateNumber = (TextView) convertView.findViewById(R.id.movie_rate_number);
            tvRateNumber.setText(String.valueOf(movie.getVoteAverage()));

            return convertView;
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