package com.ceduliocezar.lux.menu.genre;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.cloud.Genre;
import com.ceduliocezar.lux.cloud.GenreTransport;
import com.ceduliocezar.lux.cloud.MovieAPI;
import com.ceduliocezar.lux.cloud.TheMovieDBAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


public class MoviesByGenreFragment extends Fragment {

    private List<Genre> genres = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesGenreAdapter adapter;
    private LinearLayoutManager mLayoutManager;

    public MoviesByGenreFragment() {
    }

    public static MoviesByGenreFragment newInstance() {
        MoviesByGenreFragment fragment = new MoviesByGenreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_movies_by_genre, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.genre_list);

        adapter = new MoviesGenreAdapter(genres);
        recyclerView.setAdapter(adapter);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {

            loadGenres(MovieAPI.getInstance(getActivity()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadGenres(final TheMovieDBAPI service) throws IOException {

        new AsyncTask<Void, Void, Void>() {
            private List<Genre> loadedGenres;

            @Override
            protected Void doInBackground(Void... params) {

                try {
                    Call<GenreTransport> response = service.getGenres(getString(R.string.MOVIE_DB_API_KEY));
                    GenreTransport genreTransport = response.execute().body();

//            https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc?&api_key=4c2bf59aa34e0b47a51f9fe34a90c844

                    for (Genre genre : genreTransport.getGenres()) {
                        Log.d("debug", "genre=" + genre.getName());
                    }

                    loadedGenres = genreTransport.getGenres();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if (getView() != null) {
                    MoviesByGenreFragment.this.genres.addAll(loadedGenres);
                    adapter.notifyDataSetChanged();
                }
            }
        }.execute();
    }

}
