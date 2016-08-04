package com.ceduliocezar.lux.pickcgenre;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.data.Genre;

import java.util.List;

/**
 * Created by ceduliocezar on 03/08/2016.
 */
public class FavoriteGenreAdapter extends BaseAdapter {

    List<Genre> genres;

    public FavoriteGenreAdapter( List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public int getCount() {
        return genres.size();
    }

    @Override
    public Object getItem(int position) {
        return genres.get(position);
    }

    @Override
    public long getItemId(int position) {
        return genres.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        Movie movie = genres.get(position);
//
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, null);
        }
//
//        ImageView imageView = (ImageView) convertView.findViewById(R.id.movie_image);
//
//        Picasso.with(MoviesFragment.this.getActivity()).
//                load("http://image.tmdb.org/t/p/w500" + movie.getPosterPath()).
//                into(imageView);
//
//
//        TextView tvGenres = (TextView) convertView.findViewById(R.id.movie_genre);
//        tvGenres.setText(getGenres(movie));
//
//        TextView tvTitle = (TextView) convertView.findViewById(R.id.movie_title);
//        tvTitle.setText(movie.getTitle());
//
//
//        TextView tvYear = (TextView) convertView.findViewById(R.id.movie_year);
//        tvYear.setText(getReleaseYear(movie));
//
//        TextView tvRateNumber = (TextView) convertView.findViewById(R.id.movie_rate_number);
//        tvRateNumber.setText(String.valueOf(movie.getVoteAverage()));
//
//        if (movies.size() - position == 1) {
//            new LoadData(currentPage + 1, true).execute();
//        }

        return convertView;
    }
}