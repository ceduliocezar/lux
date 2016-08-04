package com.ceduliocezar.lux.pickcgenre;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.ceduliocezar.lux.Injection;
import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.data.Genre;

import java.util.ArrayList;
import java.util.List;


public class GenrePickerFragment extends Fragment implements GenrePickerContract.View {

    private GridView gridView;
    private GenrePickerContract.UserActionsListener actionsListener;
    private FavoriteGenreAdapter adapter;

    public GenrePickerFragment() {

    }

    public static GenrePickerFragment newInstance() {
        return new GenrePickerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new FavoriteGenreAdapter(new ArrayList<Genre>(0));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_genre_picker, container, false);

        gridView = (GridView) view.findViewById(R.id.genre_grid);
        gridView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        actionsListener.loadGenres(false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        actionsListener = new GenrePickerPresenter(Injection.providesGenreRepository(), this);
    }

    @Override
    public void showGenres(List<Genre> genres) {
        adapter.genres.clear();
        adapter.genres.addAll(genres);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadGenresError() {
        Log.d("TAG","showLoadGenresError");
    }

    @Override
    public void setProgressIndicator(boolean active) {
        Log.d("TAG","setProgressIndicator = " + active);
    }

    @Override
    public void animateGenreSelection(Genre genre) {
        gridView.invalidateViews();
    }

    @Override
    public void animateGenreDeSelection(Genre genre) {
        gridView.invalidateViews();
    }
}
