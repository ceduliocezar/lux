package com.ceduliocezar.lux.pickcgenre;

import com.ceduliocezar.lux.data.Genre;
import com.ceduliocezar.lux.data.GenresRepository;

import java.util.List;

/**
 * Created by cedulio on 03/08/2016.
 */
public class GenrePickerPresenter implements GenrePickerContract.UserActionsListener {

    private GenresRepository genresRepository;
    private GenrePickerContract.View genrePickerView;

    public GenrePickerPresenter(GenresRepository genresRepository, GenrePickerContract.View genrePickerView){
        this.genresRepository = genresRepository;
        this.genrePickerView = genrePickerView;
    }

    @Override
    public void selectGenre(Genre genre) {
        genresRepository.removeGenreAsFavorite(genre);
        genrePickerView.animateGenreSelection(genre);
    }

    @Override
    public void unSelectGenre(Genre genre) {
        genrePickerView.animateGenreDeSelection(genre);
        genresRepository.removeGenreAsFavorite(genre);
    }

    @Override
    public void loadGenres(boolean forceUpdate) {

        genrePickerView.setProgressIndicator(true);

        genresRepository.getAllGenres(new GenresRepository.LoadGenresCallback() {
            @Override
            public void onLoadGenres(List<Genre> genres) {
                genrePickerView.setProgressIndicator(false);
                genrePickerView.showGenres(genres);
            }
        });
    }
}
