package com.ceduliocezar.lux.pickcgenre;

import com.ceduliocezar.lux.data.Genre;

import java.util.List;

/**
 * Created by cedulio on 03/08/2016.
 */
public class GenrePickerContract {
    interface View {
        void showGenres(List<Genre> genres);

        void showLoadGenresError();

        void setProgressIndicator(boolean active);

        void animateGenreSelection(Genre genre);

        void animateGenreDeSelection(Genre genre);
    }

    interface UserActionsListener {

        void selectGenre(Genre genre);

        void unSelectGenre(Genre genre);

        void loadGenres(boolean forceUpdate);
    }
}
