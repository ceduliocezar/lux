package com.ceduliocezar.lux;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ceduliocezar.lux.cloud.Genre;
import com.ceduliocezar.lux.cloud.GenreTransport;
import com.ceduliocezar.lux.cloud.Movie;
import com.ceduliocezar.lux.cloud.MovieTransport;
import com.ceduliocezar.lux.cloud.TheMovieDBAPI;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashActivity extends AppCompatActivity {

    private MovieAdapter adapter;
    private List<Genre> genres;
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        GridView gridView = (GridView) findViewById(R.id.movie_grid);

        adapter = new MovieAdapter(this, new ArrayList<Movie>());
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = MovieDetailActivity.getIntent(SplashActivity.this, (Movie)adapter.getItem(position));
                startActivity(intent);
            }
        });

        new LoadData(currentPage+1,false).execute();
    }


    public class LoadData extends AsyncTask<Void, Void, Void> {

        private final int pageIndexToLoad;
        private final boolean lazy;
        private List<Movie> movies;
        private List<Genre> genres;

        public LoadData(int pageIndexToLoad, boolean lazy){
            this.pageIndexToLoad = pageIndexToLoad;
            this.lazy = lazy;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if(lazy){
                mostrarLazyLoader();
            }
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(getString(R.string.MOVIE_DB_BASE_URL))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                TheMovieDBAPI service = retrofit.create(TheMovieDBAPI.class);


                loadMovies(service);
                loadGenres(service);

//                http://image.tmdb.org/t/p/w500/inVq3FRqcYIRl2la8iZikYYxFNR.jpg
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        private void loadGenres(TheMovieDBAPI service) throws IOException {
            Call<GenreTransport> response = service.getGenres(getString(R.string.MOVIE_DB_API_KEY));
            GenreTransport genreTransport = response.execute().body();

//            https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc?&api_key=4c2bf59aa34e0b47a51f9fe34a90c844

            for (Genre genre : genreTransport.getGenres()) {
                Log.d("debug", "genre=" + genre.getName());
            }

            this.genres = genreTransport.getGenres();
        }

        private void loadMovies(TheMovieDBAPI service) throws java.io.IOException {
            Call<MovieTransport> response = service.orderByRate(getString(R.string.MOVIE_DB_API_KEY),pageIndexToLoad);
            MovieTransport movieTransport = response.execute().body();

//            https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc?&api_key=4c2bf59aa34e0b47a51f9fe34a90c844

            for (Movie movie : movieTransport.getResults()) {
                Log.d("debug", "movie=" + movie.getTitle());
            }

            this.movies = movieTransport.getResults();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (movies != null) {
                adapter.addAll(movies);
            }

            if (genres != null) {
                SplashActivity.this.genres = genres;
            }

            currentPage++;

            esconderLazyLoader();
        }
    }

    private void mostrarLazyLoader() {
        findViewById(R.id.movie_load_progress).setVisibility(View.VISIBLE);
    }

    private void esconderLazyLoader() {
        findViewById(R.id.movie_load_progress).setVisibility(View.GONE);
    }

    private class MovieAdapter extends BaseAdapter {

        private final LayoutInflater inflater;

        private List<Movie> movies;

        public MovieAdapter(Context context, List<Movie> movies) {
            this.inflater = LayoutInflater.from(context);
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
                convertView = inflater.inflate(R.layout.movie_item, null);
            }

//            if (position % 2 == 0) {
//                ImageView imageView = (ImageView) convertView.findViewById(R.id.movie_image);
//
//                imageView.setImageResource(R.mipmap.img1);
//            }else{
//                ImageView imageView = (ImageView) convertView.findViewById(R.id.movie_image);
//
//                imageView.setImageResource(R.mipmap.img2);
//            }
            ImageView imageView = (ImageView) convertView.findViewById(R.id.movie_image);

            Picasso.with(SplashActivity.this).
                    load("http://image.tmdb.org/t/p/w500" + movie.getPosterPath()).
                    into(imageView);


            TextView tvGenres = (TextView) convertView.findViewById(R.id.movie_genre);
            tvGenres.setText(getGenres(movie));

            TextView tvTitle = (TextView) convertView.findViewById(R.id.movie_title);
            tvTitle.setText(movie.getTitle());


            TextView tvYear = (TextView) convertView.findViewById(R.id.movie_year);
            tvYear.setText(getReleaseYear(movie));

            TextView tvRateNumber = (TextView) convertView.findViewById(R.id.movie_rate_number);
            tvRateNumber.setText(String.valueOf(movie.getVoteAverage()));

            if(movies.size() - position == 1){
                new LoadData(currentPage+1,true).execute();
            }

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
